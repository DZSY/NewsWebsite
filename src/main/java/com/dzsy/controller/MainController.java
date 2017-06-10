package com.dzsy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

//采用注解的方式，可以明确地定义该类为处理请求的Controller类
@Controller
public class MainController {

    //用于定义一个请求映射，value为请求的url，值为 / 说明，该请求首页请求，method用以指定该请求类型，一般为get和post；
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String defaultPage(HttpSession httpSession) {
        return "home";
    }

    @RequestMapping(value = "/error", method = RequestMethod.GET)
    public String error(HttpSession httpSession) {
        return "error";
    }

    @RequestMapping(value = "/isOnline", method = RequestMethod.POST)
    public @ResponseBody String isOnline(HttpSession httpSession) {
        if (httpSession.getAttribute("username") != null)
            return "yes";
        else return "no";
    }

    @RequestMapping(value = "/isActivated", method = RequestMethod.POST)
    public @ResponseBody String isActivated(HttpSession httpSession) {
        if (httpSession.getAttribute("activated") != null)
            return "yes";
        else return "no";
    }

}