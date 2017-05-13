package com.dzsy.controller;

import com.dzsy.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

//采用注解的方式，可以明确地定义该类为处理请求的Controller类
@Controller
@RequestMapping("/user")
public class UserController {

    @Resource(name="userService")
    private UserService service;

    //用于定义一个请求映射，value为请求的url，值为 / 说明，该请求首页请求，method用以指定该请求类型，一般为get和post；
    @RequestMapping()
    public String user(HttpSession httpSession) {
        if (httpSession.getAttribute("username") == null)
            return "user";
        else if (httpSession.getAttribute("activated") == null)
            return "activate";
        else
            return "error";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ModelAndView logout(HttpSession httpSession) {
        httpSession.removeAttribute("username");
        httpSession.removeAttribute("activationCode");
        httpSession.removeAttribute("activated");

        ModelAndView modelAndView = new ModelAndView("redirect:/");

        return modelAndView;
    }

    /*
     * 返回String类型的结果
     * 检查用户名的合法性,如果用户已经存在，返回false，否则返回true(返回json数据，格式为{"valid",true})
     */
    @RequestMapping(value = "/ajax_exist", method = RequestMethod.POST)
    public @ResponseBody String ajaxExist(@RequestParam String username) {
        if (service.isUserExist(username))
            return "{\"valid\":false}";
        else
            return "{\"valid\":true}";
    }

    @RequestMapping(value = "/ajax_login", method = RequestMethod.POST)
    public @ResponseBody String ajaxLogin(@RequestParam String username, @RequestParam String password, HttpSession httpSession) {
        if (!service.isUserExist(username))
            return "username doesn't exist"; //用户名不存在
        else if (!service.isPasswordMatching(username, password))
            return "password doesn't match"; //密码错误
        else {
            httpSession.setAttribute("username", username);
            String result = service.getActivationCode(username);
            if (result == null) {
                httpSession.setAttribute("activated", true);
                return "log in"; //登录成功
            }
            else {
                httpSession.setAttribute("activationCode", result);
                return "need activate"; //需要激活
            }
        }
    }

    @RequestMapping(value = "/ajax_activate", method = RequestMethod.POST)
    public @ResponseBody String ajaxActivate(@RequestParam String activation, HttpSession httpSession) {
        if (httpSession.getAttribute("username") == null || httpSession.getAttribute("activationCode") == null)
            return "error";
        else if (!activation.equals(httpSession.getAttribute("activationCode")))
            return "wrong activation code";
        else {
            httpSession.setAttribute("activated", true);
            return "success";
        }
    }

    @RequestMapping(value = "/ajax_register", method = RequestMethod.POST)
    public @ResponseBody String register(@RequestParam String username, @RequestParam String password, @RequestParam String email, HttpSession httpSession) {
        if (httpSession.getAttribute("username") != null) {
            if (httpSession.getAttribute("activated") == null) {
                return "already log in and need activate";
            }
            else return "already activated";
        }
        else if (service.isUserExist(username))
            return "username exist";
        else if (service.isEmailExist(email))
            return "email exist";
        else {
            String activation = service.Register(username, password, email);
            httpSession.setAttribute("activationCode", activation);
            httpSession.setAttribute("username", username);
            return "success";
        }
    }
}