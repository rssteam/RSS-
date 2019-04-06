package com.reader.rss.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.reader.rss.mapper.SiteMapper;
import com.reader.rss.mapper.SubscribeMapper;
import com.reader.rss.mapper.UserGroupMapper;
import com.reader.rss.mapper.UserMapper;
import com.reader.rss.pojo.*;
import com.reader.rss.service.resolvexml.IStorageXml;
import com.reader.rss.service.rssservice.RssService;

import netscape.javascript.JSObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Map;

@Controller
public class HomeController {
    @Autowired
    RssService rssService;
    @Autowired(required = false)
    UserMapper userMapper;
    @Autowired(required = false)
    UserGroupMapper userGroupMapper;
    @Autowired(required = false)
    SiteMapper siteMapper;

    @Autowired
    IStorageXml storageXml;
    @RequestMapping("/")
    public String goHome(Model model){
//        userMapper.insert(new User("789","","","",null));
//        storageXml.addNewSite(new Site("安全客"," https://api.anquanke.com/data/v1/rss",""));
//        storageXml.addNewSite(new Site("掘金","https://rsshub.app/juejin/category/frontend",""));
//        storageXml.addNewSite(new Site("VICE中国","http://www.vice.cn/index/rss/",""));
//        storageXml.addNewSite(new Site("极客时间","https://rsshub.app/geektime/column/48",""));
//        storageXml.addNewSite(new Site("开源中国"," https://rsshub.app/oschina/news",""));
//        storageXml.addNewSite(new Site("Dockone","https://rsshub.app/dockone/weekly",""));
//        userGroupMapper.insert(new UserGroup(null,"新闻","789"));
//        userGroupMapper.insert(new UserGroup(null,"学习","123"));
//        storageXml.subSite("123",14,4);
//        storageXml.subSite("123",8,2);
//        storageXml.subSite("123",9,5);
//        storageXml.subSite("123",10,2);
//        storageXml.subSite("123",11,1);
////        storageXml.subSite("123",12,4);
        User user=rssService.findByID("789");
        model.addAttribute("user",user);
         List<UserGroup>userGroups=userGroupMapper.selectByUid(user.getAccountId());//用户个人分组
         model.addAttribute("userGroups",userGroups);
        Map<String,List<Site>> userGroupSites=storageXml.getUserGroupSites(user.getAccountId());
        model.addAttribute("userGroupSites",userGroupSites);
        List<Site> userAllsites=storageXml.getUserSites(user.getAccountId());
        model.addAttribute("userAllsites",userAllsites);
        List<Site> allSites= siteMapper.getAllSite();
        model.addAttribute("allSites",allSites);
        return "home";
    }
    @ResponseBody
    @RequestMapping(value = "/chooseSite", method = RequestMethod.GET)
    public  List<Item> chooseSite( String siteId){
        List<Item> choosedSites=storageXml.getSiteItems(Integer.parseInt(siteId));
        return  choosedSites;
    }
    @ResponseBody
    @RequestMapping(value = "/chooseAllSite", method = RequestMethod.GET)
    public   List<Item> chooseAllSite(String accountId){
        List<Item> chooseAllSites=storageXml.getUserItems(accountId);
        return  chooseAllSites;
    }
    @ResponseBody
    @RequestMapping(value = "/likeSite", method = RequestMethod.GET)
    public   String  likeSite(String itemId){
        Item j=storageXml.getOneItem(Integer.parseInt(itemId));
        j.setLikeNum(j.getLikeNum()+1);
       Item item=storageXml.updateItem(j);
       return "成功";
    }
    @ResponseBody
    @RequestMapping(value = "/favSite", method = RequestMethod.GET)
    public   String favSite(String itemId,String uid){
        String tip="check";
        Item y=storageXml.getOneItem(Integer.parseInt(itemId));
        List<Collection> collections=rssService.checkCollection(y.getItemId(),uid);
        if (collections.size()==0){
            y.setFavNum(y.getFavNum()+1);
            storageXml.updateItem(y);
            storageXml.favItem(uid,Integer.parseInt(itemId));
        }else {
            tip="已收藏";
        }

       return tip;

    }
    @ResponseBody
    @RequestMapping(value = "/subscribe", method = RequestMethod.POST)
    public   String  subdefaultSite(@RequestBody String subDiction){
        Map<String,String> subMap= (Map) JSON.parse(subDiction);
        UserGroup group;
        Site site;
        if (subMap.get("groupTitle")!=null) {
            group=new UserGroup(null,subMap.get("groupTitle"),subMap.get("accountId"));
            group=rssService.addUserGroup(group);

        }else {
            group=userGroupMapper.selectByPrimaryKey(Integer.parseInt(subMap.get("groupId")));
        }

        if (subMap.get("siteId")!=null) {
            storageXml.subSite(subMap.get("accountId"), Integer.parseInt(subMap.get("siteId")), group.getGroupId());
        }else {
            Site newSite=new Site(subMap.get("siteTitle"),subMap.get("siteUrl"),"css");
            site=storageXml.addNewSite(newSite);
            storageXml.subSite(subMap.get("accountId"),site.getSiteId(),group.getGroupId());
        }

       return "成功";
    }
}
