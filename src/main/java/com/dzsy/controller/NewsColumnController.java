package com.dzsy.controller;

import com.dzsy.entity.NewsColumn;
import com.dzsy.service.NewsColumnService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

//采用注解的方式，可以明确地定义该类为处理请求的Controller类
@Controller
public class NewsColumnController {

    @Resource(name="newsColumnService")
    private NewsColumnService service;

    private static final int count = 9;

    private static final Map<String, String> labelMap = new HashMap<String , String>(){{
        put("时政",  "politics");
        put("国际",  "world");
        put("信息化","information");
        put("财经",  "fortune");
        put("房产",  "house");
        put("军事",  "military");
        put("港澳",  "hkmac");
        put("台湾",  "taiwan");
        put("娱乐",  "entertainment");
        put("时尚",  "fashion");
        put("体育",  "sports");
        put("汽车",  "automobile");
        put("科技",  "technology");
        put("食品",  "food");
        put("金融",  "money");
        put("法治",  "legal");
        put("海外",  "overseas");
        put("教育",  "education");
        put("能源",  "energy");
        put("人事",  "personnel");
        put("思客",  "sike");
        put("健康",  "health");
        put("舆情",  "yuqing");
        put("公益",  "public");
        put("产业园","industry");
        put("证券",  "finance");
        put("传媒",  "media");
    }};
    //用于定义一个请求映射，value为请求的url，值为 / 说明，该请求首页请求，method用以指定该请求类型，一般为get和post；
    @RequestMapping(value="/column",method = RequestMethod.GET)
    public ModelAndView getColumns(HttpSession httpSession) {
        if (httpSession.getAttribute("activated") == null)
            return new ModelAndView("redirect:/column/1");
        else return new ModelAndView("redirect:/column/1");//要改的
    }

    @RequestMapping(value="/column/{page}", method = {RequestMethod.GET})
    public ModelAndView goColumnsPage(@PathVariable(value="page") Integer page, HttpSession httpSession){

        int totalPage = (service.gettotalCount() - 1) / count + 1;
        if (page < 1 || page > totalPage)
            return new ModelAndView("redirect:/error");
        List columnsList = service.getPage((page-1) * count, count);
        List<NewsColumn> list = new LinkedList<>();

        ModelAndView modelAndView = new ModelAndView();
        if (httpSession.getAttribute("activated") != null && httpSession.getAttribute("username") != null) {
            modelAndView.setViewName("columns_online");
            String username = httpSession.getAttribute("username").toString();
            for (Object column : columnsList)
                list.add(new NewsColumn(column.toString(), service.isFollowd(username, column.toString()), labelMap.get(column.toString())));
        }
        else {
            modelAndView.setViewName("columns");
            for (Object column : columnsList)
                list.add(new NewsColumn(column.toString(), false,labelMap.get(column.toString())));
        }

        modelAndView.addObject("list", list);
        modelAndView.addObject("count", count);
        modelAndView.addObject("start", (page-1) * count);
        modelAndView.addObject("page", page);
        modelAndView.addObject("totalPage", totalPage);

        return modelAndView;
    }

    @RequestMapping(value="/{columnLabel}",method = RequestMethod.GET)
    public ModelAndView goColumn(@PathVariable(value="columnLabel") String columnLabel, HttpSession httpSession) {
        if (!labelMap.containsValue(columnLabel)) {
            return new ModelAndView("redirect:/error");
        }
        if (httpSession.getAttribute("activated") == null)
            return new ModelAndView("redirect:/column/1");
        else return new ModelAndView("redirect:/column/1");//要改的
    }
}

