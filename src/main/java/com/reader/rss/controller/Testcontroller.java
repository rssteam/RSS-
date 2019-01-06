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
        Site site = new Site("网站:喷嚏网","http://www.dapenti.com/blog/rss2.asp?name=xilei","");
        site = storageXml.addNewSite(site);
        storageXml.updateAllSite();
        List<Item> list = storageXml.getSiteItems(site.getSiteId());
        return list;
//        return iredisservice.getByKey("map"+17,"https://www.cnbeta.com/articles/tech/805373.htm",Item.class);
    }
}
