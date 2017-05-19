package com.dzsy.controller;

import com.dzsy.entity.News;
import com.dzsy.entity.NewsColumn;
import com.dzsy.entity.NewsSkeleton;
import com.dzsy.service.NewsColumnService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

//采用注解的方式，可以明确地定义该类为处理请求的Controller类
@Controller
public class NewsColumnController {

    @Resource(name="newsColumnService")
    private NewsColumnService service;

    private static final int columnsPageCount = 9;
    private static final int newsPageCount = 12;

    private static final Map<String, String> mapNameLabel = new HashMap<String , String>(){{
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
    private static final Map<String, String> mapLabelName = new HashMap<String , String>(){{
        put("politics", "时政");
        put("world", "国际");
        put("information", "信息化");
        put("fortune", "财经");
        put("house", "房产");
        put("military", "军事");
        put("hkmac", "港澳");
        put("taiwan", "台湾");
        put("entertainment", "娱乐");
        put("fashion", "时尚");
        put("sports", "体育");
        put("automobile", "汽车");
        put("technology", "科技");
        put("food", "食品");
        put("money", "金融");
        put("legal", "法治");
        put("overseas", "海外");
        put("education", "教育");
        put("energy", "能源");
        put("personnel", "人事");
        put("sike", "思客");
        put("health", "健康");
        put("yuqing", "舆情");
        put("public", "公益");
        put("industry", "产业园");
        put("finance", "证券");
        put("media", "传媒");
    }};
    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @RequestMapping(value="/column",method = RequestMethod.GET)
    public ModelAndView getColumns() {
        return new ModelAndView("redirect:/column/1");
    }

    @RequestMapping(value="/column/{page}", method = {RequestMethod.GET})
    public ModelAndView goColumnsPage(@PathVariable(value="page") Integer page, HttpSession httpSession){

        int totalPage = (service.getColumnsTotalCount() - 1) / columnsPageCount + 1;
        if (page < 1 || page > totalPage)
            return new ModelAndView("redirect:/error");
        List columnsList = service.getColumnsPage((page-1) * columnsPageCount, columnsPageCount);
        List<NewsColumn> list = new LinkedList<>();

        ModelAndView modelAndView = new ModelAndView();
        if (httpSession.getAttribute("activated") != null && httpSession.getAttribute("username") != null) {
            modelAndView.setViewName("columns_online");
            String username = httpSession.getAttribute("username").toString();
            for (Object column : columnsList)
                list.add(new NewsColumn(column.toString(), service.isFollowd(username, column.toString()), mapNameLabel.get(column.toString())));
        }
        else {
            modelAndView.setViewName("columns");
            for (Object column : columnsList)
                list.add(new NewsColumn(column.toString(), false, mapNameLabel.get(column.toString())));
        }

        modelAndView.addObject("list", list);
        modelAndView.addObject("count", columnsPageCount);
        modelAndView.addObject("start", (page-1) * columnsPageCount);
        modelAndView.addObject("page", page);
        modelAndView.addObject("totalPage", totalPage);

        return modelAndView;
    }

    @RequestMapping(value="/latest",method = RequestMethod.GET)
    public ModelAndView getLatest0() {
        return new ModelAndView("redirect:/latest/1");
    }

    @RequestMapping(value="/latest/{page}", method = {RequestMethod.GET})
    public ModelAndView getLatestPage(@PathVariable(value="page") Integer page, HttpSession httpSession){

        int totalPage = (service.getNewsTotalCount("") - 1) / newsPageCount  + 1;
        if (page < 1 || page > totalPage)
            return new ModelAndView("redirect:/error");
        List<Object[]> newsList = service.getNewsPage("",(page-1) * newsPageCount, newsPageCount);
        List<NewsSkeleton> list = new LinkedList<>();
        for (Object[] object : newsList) {
            list.add(new NewsSkeleton((Integer)object[0], (String)object[1], simpleDateFormat.format((Timestamp)object[2])));
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("list", list);
        modelAndView.addObject("count", newsPageCount);
        modelAndView.addObject("start", (page-1) * newsPageCount);
        modelAndView.addObject("page", page);
        modelAndView.addObject("totalPage", totalPage);

        if (httpSession.getAttribute("activated") != null) {
            modelAndView.setViewName("latest_online");
        }
        modelAndView.setViewName("latest");
        return modelAndView;
    }

    @RequestMapping(value="/news/{news_id}",method = RequestMethod.GET)
    public ModelAndView showNews(@PathVariable(value="news_id") Integer ID, HttpSession httpSession) {
        ModelAndView modelAndView = new ModelAndView();
        News news = service.getNewsInfo(ID);
        if (news == null)
            return new ModelAndView("redirect:/error");
        modelAndView.addObject("id", news.getNewsID());
        modelAndView.addObject("time", simpleDateFormat.format(news.getTime()));
        modelAndView.addObject("url", news.getUrl());
        modelAndView.addObject("source", news.getSource());
        modelAndView.addObject("newsColumn", news.getNewsColumn());
        modelAndView.addObject("columnLabel", mapNameLabel.get(news.getNewsColumn()));
        modelAndView.addObject("title", news.getTitle());
        modelAndView.addObject("body", news.getBody());

        if (httpSession.getAttribute("activated") != null) {
            if (httpSession.getAttribute("username") == null)
                return new ModelAndView("redirect:/error");
            //to do : add into the visited record

            modelAndView.setViewName("news_online");
        }
        modelAndView.setViewName("news");
        return modelAndView;
    }

    @RequestMapping(value="/searchTitle/{page}", method = {RequestMethod.POST})
    public ModelAndView doSearchTitlePage(String searchItem, @PathVariable(value="page") Integer page, HttpSession httpSession){

        int totalPage = (service.getSearchTitleTotalCount(searchItem) - 1) / newsPageCount  + 1;
        if (page < 1 || page > totalPage)
            return new ModelAndView("redirect:/error");
        List<Object[]> newsList = service.getSearchTitleNewsPage(searchItem,(page-1) * newsPageCount, newsPageCount);
        List<NewsSkeleton> list = new LinkedList<>();
        for (Object[] object : newsList) {
            list.add(new NewsSkeleton((Integer)object[0], (String)object[1], simpleDateFormat.format((Timestamp)object[2])));
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("list", list);
        modelAndView.addObject("count", newsPageCount);
        modelAndView.addObject("start", (page-1) * newsPageCount);
        modelAndView.addObject("page", page);
        modelAndView.addObject("totalPage", totalPage);
        modelAndView.addObject("searchItem", searchItem);

        if (httpSession.getAttribute("activated") != null) {
            modelAndView.setViewName("search_title_result_online");
        }
        modelAndView.setViewName("search_title_result");
        return modelAndView;
    }

    @RequestMapping(value="/searchBody/{page}", method = {RequestMethod.POST})
    public ModelAndView doSearchBodyPage(String searchItem, @PathVariable(value="page") Integer page, HttpSession httpSession){

        int totalPage = (service.getSearchBodyTotalCount(searchItem) - 1) / newsPageCount  + 1;
        if (page < 1 || page > totalPage)
            return new ModelAndView("redirect:/error");
        List<Object[]> newsList = service.getSearchBodyNewsPage(searchItem,(page-1) * newsPageCount, newsPageCount);
        List<NewsSkeleton> list = new LinkedList<>();
        for (Object[] object : newsList) {
            list.add(new NewsSkeleton((Integer)object[0], (String)object[1], simpleDateFormat.format((Timestamp)object[2])));
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("list", list);
        modelAndView.addObject("count", newsPageCount);
        modelAndView.addObject("start", (page-1) * newsPageCount);
        modelAndView.addObject("page", page);
        modelAndView.addObject("totalPage", totalPage);
        modelAndView.addObject("searchItem", searchItem);

        if (httpSession.getAttribute("activated") != null) {
            modelAndView.setViewName("search_body_result_online");
        }
        modelAndView.setViewName("search_body_result");
        return modelAndView;
    }

    @RequestMapping(value="/{columnLabel}",method = RequestMethod.GET)
    public ModelAndView goColumn0(@PathVariable(value="columnLabel") String columnLabel, HttpSession httpSession) {
        return new ModelAndView("redirect:/{columnLabel}/1");
    }

    @RequestMapping(value="/{columnLabel}/{page}",method = RequestMethod.GET)
    public ModelAndView goColumn(@PathVariable(value="columnLabel") String columnLabel, @PathVariable(value="page") Integer page, HttpSession httpSession) {
        String columnName = mapLabelName.get(columnLabel.toString());
        if (columnName == null) {
            return new ModelAndView("redirect:/error");
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("columnLabel", columnLabel);
        modelAndView.addObject("columnName", columnName);
        modelAndView.addObject("isFollowd", false);

        int totalPage = (service.getNewsTotalCount(columnName) - 1) / newsPageCount  + 1;
        if (page < 1 || page > totalPage)
            return new ModelAndView("redirect:/error");
        List<Object[]> newsList = service.getNewsPage(columnName,(page-1) * newsPageCount, newsPageCount);
        List<NewsSkeleton> list = new LinkedList<>();
        for (Object[] object : newsList) {
            list.add(new NewsSkeleton((Integer)object[0], (String)object[1], simpleDateFormat.format((Timestamp)object[2])));
        }

        modelAndView.addObject("list", list);
        modelAndView.addObject("count", newsPageCount);
        modelAndView.addObject("start", (page-1) * newsPageCount);
        modelAndView.addObject("page", page);
        modelAndView.addObject("totalPage", totalPage);

        if (httpSession.getAttribute("activated") != null) {
            String username = httpSession.getAttribute("username").toString();
            if (username == null || columnName == null)
                return new ModelAndView("redirect:/error");
            modelAndView.addObject("isFollowed", service.isFollowd(username, columnName));
            modelAndView.setViewName("singlecolumn_online");
            return modelAndView;
        }
        modelAndView.setViewName("singlecolumn");
        return modelAndView;
    }


}

