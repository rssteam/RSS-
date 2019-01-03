package com.reader.rss.controller;

import com.reader.rss.mapper.UserMapper;
import com.reader.rss.pojo.User;
import com.reader.rss.service.io.IJsfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class Testcontroller {
    @Autowired
    IJsfile jsfile;
    @Autowired(required = false)
    UserMapper usermapper;
    @RequestMapping("hello")
    public User index(String path) throws Exception{
//        jsfile.reShowHtml("https://"+path);
//        jsfile.IO("http://www."+path+".com/");
//        Thread.sleep(10000);
//        return jsfile.getXml("http://www.cnbeta.com/backend.php");
//          return jsfile.getXml("http://www.ifanr.com/feed");
//        System.out.println("1111111111111");
//        usermapper.addUser(new User("2","1","1","1","1991-1-1"));
        return  usermapper.selectByPrimaryKey("1");
    }
}
