package com.dzsy.service;

import com.dzsy.dao.ActivatingDAO;
import com.dzsy.dao.UserDAO;
import com.dzsy.entity.Activation;
import com.dzsy.entity.User;
import org.springframework.beans.factory.annotation.Autowired;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

/**
 * Created by positif on 06/05/2017.
 */

public class UserService {
    @Autowired
    private UserDAO userDao;
    @Autowired
    private ActivatingDAO activatingDao;

    public boolean isUserExist(String username) {
        return userDao.isUserExist(username);
    }
    public boolean isEmailExist(String email) { return userDao.isEmailExist(email); }

    public  boolean isPasswordMatching(String username, String password) {
        return userDao.isPasswordMatching(username, password);
    }

    public String getActivationCode(String username) {
        return activatingDao.getActivationCode(username);
    }

    public String Register(String username, String password, String email) {


        Random random = new Random();
        StringBuffer activationCode = new StringBuffer();
        for (int i = 0; i < 6; i++)
            activationCode.append(random.nextInt(10));

        ///邮件的内容
        StringBuffer sb=new StringBuffer("亲爱的 " + username + " 您好，您的激活码是 " +
                "<span style=\"border:1px; background:#CAFF70; color:#458B00\">" + activationCode + "</span>" +
                " ，请尽快验证。");

        //发送邮件
        User user = new User(username, password, email);
        Activation activation = new Activation(username, activationCode.toString());

        userDao.addUser(user);
        activatingDao.addActivating(activation);

        sendEmail(email, sb.toString());
        return activationCode.toString();
    }

    private void sendEmail(String to, String content) {
// 1. 创建参数配置, 用于连接邮件服务器的参数配置
        Properties props = new Properties();                    // 参数配置
        props.setProperty("mail.transport.protocol", "smtp");   // 使用的协议（JavaMail规范要求）
        props.setProperty("mail.smtp.host", "smtp.yeah.net");   // 发件人的邮箱的 SMTP 服务器地址
        props.setProperty("mail.smtp.auth", "true");            // 需要请求认证

        // PS: 某些邮箱服务器要求 SMTP 连接需要使用 SSL 安全认证 (为了提高安全性, 邮箱支持SSL连接, 也可以自己开启),
        //     如果无法连接邮件服务器, 仔细查看控制台打印的 log, 如果有有类似 “连接失败, 要求 SSL 安全连接” 等错误,
        //     打开下面 /* ... */ 之间的注释代码, 开启 SSL 安全连接。

        // SMTP 服务器的端口 (非 SSL 连接的端口一般默认为 25, 可以不添加, 如果开启了 SSL 连接,
        //                  需要改为对应邮箱的 SMTP 服务器的端口, 具体可查看对应邮箱服务的帮助,
        //                  QQ邮箱的SMTP(SLL)端口为465或587, 其他邮箱自行去查看)
        final String smtpPort = "587";
        props.setProperty("mail.smtp.port", smtpPort);
        props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.socketFactory.port", smtpPort);
        props.setProperty("mail.smtp.starttls.enable", "true");


        final String username = "xinxinnews@yeah.net";
        final String password = "shouquanma123456";

        // 2. 根据配置创建会话对象, 用于和邮件服务器交互
        Session session = Session.getDefaultInstance( props , new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication( username , password );
            }
        });
        session.setDebug(true);                                 // 设置为debug模式, 可以查看详细的发送 log

        // 3. 创建一封邮件
        MimeMessage message = new MimeMessage(session);

        try {
            message.setFrom(new InternetAddress(username, "新新新闻网", "UTF-8"));
            message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(to, "用户", "UTF-8"));
            message.setSubject("欢迎使用新新新闻网", "UTF-8");
            message.setContent(content, "text/html;charset=UTF-8");
            message.setSentDate(new Date());
            message.saveChanges();


            // 4. 根据 Session 获取邮件传输对象
            Transport transport = session.getTransport();

            // 5. 使用 邮箱账号 和 密码 连接邮件服务器, 这里认证的邮箱必须与 message 中的发件人邮箱一致, 否则报错
            //
            //    PS_01: 成败的判断关键在此一句, 如果连接服务器失败, 都会在控制台输出相应失败原因的 log,
            //           仔细查看失败原因, 有些邮箱服务器会返回错误码或查看错误类型的链接, 根据给出的错误
            //           类型到对应邮件服务器的帮助网站上查看具体失败原因。
            //
            //    PS_02: 连接失败的原因通常为以下几点, 仔细检查代码:
            //           (1) 邮箱没有开启 SMTP 服务;
            //           (2) 邮箱密码错误, 例如某些邮箱开启了独立密码;
            //           (3) 邮箱服务器要求必须要使用 SSL 安全连接;
            //           (4) 请求过于频繁或其他原因, 被邮件服务器拒绝服务;
            //           (5) 如果以上几点都确定无误, 到邮件服务器网站查找帮助。
            //
            //    PS_03: 仔细看log, 认真看log, 看懂log, 错误原因都在log已说明。
            transport.connect(username, password);
            // 6. 发送邮件, 发到所有的收件地址, message.getAllRecipients() 获取到的是在创建邮件对象时添加的所有收件人, 抄送人, 密送人
            transport.sendMessage(message, message.getAllRecipients());

            // 7. 关闭连接
            //之后有时间设计成一次连接发多个邮件
            transport.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteActivation(String username) {
        activatingDao.deleteActivation(username);
    }

    public UserDAO getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDAO userDao) {
        this.userDao = userDao;
    }

    public ActivatingDAO getActivatingDao() {
        return activatingDao;
    }

    public void setActivatingDao(ActivatingDAO activatingDao) {
        this.activatingDao = activatingDao;
    }

}