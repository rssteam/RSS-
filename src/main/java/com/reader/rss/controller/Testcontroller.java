package com.reader.rss.controller;

import com.reader.rss.mapper.SiteMapper;
import com.reader.rss.mapper.UserMapper;
import com.reader.rss.pojo.Item;
import com.reader.rss.pojo.Site;
import com.reader.rss.pojo.User;
import com.reader.rss.service.io.IJsfile;
import com.reader.rss.service.redisservice.Iredisservice;
import com.reader.rss.service.resolvexml.IStorageXml;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Testcontroller {
    @Autowired
    IJsfile jsfile;
    @Autowired
    Iredisservice iredisservice;
    @Autowired(required = false)
    SiteMapper siteMapper;
    @Autowired
    IStorageXml storageXml;
    @RequestMapping("hello")
    /*
     @Autowired(required = false)
    UserMapper usermapper;*/
    public List<Item> index(String path) throws Exception{
//        jsfile.reShowHtml("https://"+path);
//        jsfile.IO("http://www."+path+".com/");
//        Thread.sleep(10000);
//        return jsfile.getXml("http://www.cnbeta.com/backend.php");
//          return jsfile.getXml("http://www.ifanr.com/feed");
//        System.out.println("1111111111111");
//        usermapper.addUser(new User("2","1","1","1","1991-1-1"));
//        return  usermapper.selectByPrimaryKey("1");
/*        Site site = new Site();
        site.setSiteUrl("http://www.cnbeta.com/backend.php");
        http://www.ft.com/rss/home/asia
        http://blog.sina.com.cn/rss/1092672395.xml
        iredisservice.addSite(site);
        storageXml.updateAllSite();*/
        Site site = new Site("网站:艾帆网","http://www.ifanr.com/feed","");
        site = storageXml.addNewSite(site);
        Site site1 = new Site("网站:cnbeta","http://www.cnbeta.com/backend.php","");
        site = storageXml.addNewSite(site1);
//        storageXml.updateAllSite();
//        List<Item> list = storageXml.getSiteItems(site.getSiteId());
/*        storageXml.getOneSite(1);
        storageXml.getOneSite(2);
        storageXml.addNewSite(site);
        Thread.sleep(5000);*/
        List<Item> list = iredisservice.getMap("map"+1,Item.class);
        list.addAll(iredisservice.getMap("map"+2,Item.class));
        return list;
//        return iredisservice.getByKey("map"+17,"https://www.cnbeta.com/articles/tech/805373.htm",Item.class);
    }
}
