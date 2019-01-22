package com.reader.rss.controller;

import com.reader.rss.mapper.UserGroupMapper;
import com.reader.rss.mapper.UserMapper;
import com.reader.rss.pojo.*;
import com.reader.rss.service.resolvexml.IStorageXml;
import com.reader.rss.service.rssservice.RssService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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

    @Autowired
    IStorageXml storageXml;
    @RequestMapping("/home")
    public String goHome(Model model,HttpServletRequest request){
        User user= (User) request.getSession().getAttribute("user");
         model.addAttribute("user",user);
         List<UserGroup>userGroups=userGroupMapper.selectByUid(user.getAccountId());
         model.addAttribute("userGroups",userGroups);
        Map<String,List<Site>> userGroupSites=storageXml.getUserGroupSites(user.getAccountId());
        model.addAttribute("userGroupSites",userGroupSites);
        List<Site> userAllsites=storageXml.getUserSites(user.getAccountId());
        model.addAttribute("userAllsites",userAllsites);
        List<Item> allHotItems=rssService.getAllItems();
        model.addAttribute("allHotItems",allHotItems);
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
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public   String  login(Model model,User user, RedirectAttributes redirectAttributes, HttpServletRequest request ){
        String pwd=DigestUtils.md5DigestAsHex(user.getPwd().getBytes());
        List<User> users=rssService.login(user.getAccountId(),pwd);
        if (users.size()>1 || users.size()==0){
            model.addAttribute("erromessage","用户名或密码错误");
            return "index";
        }else {
            HttpSession session=request.getSession();
            session.setAttribute("user",users.get(0));
            return "redirect:/home";
        }
    }
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public   String  register(Model model,User user, RedirectAttributes redirectAttributes, HttpServletRequest request ){
        if(rssService.findByID(user.getAccountId())!= null){
            model.addAttribute("erromessage","用户名已存在");
            return "index";
        }else {
            String pwd = DigestUtils.md5DigestAsHex(user.getPwd().getBytes());
            user.setPwd(pwd);
            int registr=rssService.register(user);
            if (registr!=1){
                model.addAttribute("erromessage","注册失败");
                return "index";
            }else {
                HttpSession session=request.getSession();
                session.setAttribute("user",user);
                return "redirect:/home";
            }
        }
    }
    @RequestMapping("/logout")
    public   String  logout( HttpServletRequest request ) {
        HttpSession session = request.getSession();
        session.invalidate();
        return  "index";
    }
    @RequestMapping("/profile")
    public   String  profile( HttpServletRequest request ) {

        return  "profile";
    }
}
