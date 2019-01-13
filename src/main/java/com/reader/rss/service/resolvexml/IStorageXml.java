package com.reader.rss.service.resolvexml;

import com.reader.rss.pojo.Item;
import com.reader.rss.pojo.Site;
import com.reader.rss.pojo.User;

import java.util.List;
import java.util.Map;

public interface IStorageXml {
    void updateRssSource(Site site);//更新某Site的Item
    List<Item> convertXmltoItem(String url);//爬取某Site的Xml并解析存储为Items
    void updateAllSite();//更新缓存中所有网页的Item，十秒一次
    Site getOneSite(int siteid);//由Siteid获取某Site
    List<Item> getUserItems(String uid);//获取用户收藏的所有Items
    List<Site> getUserSites(String uid);//获取用户关注的所有Sites
    Map<String,List<Site>> getUserGroupSites(String uid);//获取用户关注某个分组的所有Sites
    List<Item> getSiteItems(int siteid);//获取某网页最新的Items
    Site addNewSite(Site site);//增加新网站
    boolean removeUserFavSite(String uid,int siteid);//删除关注的Site
    boolean removeUserFavItem(String uid,int itemid);//删除收藏的Item
    List<Site> getAllSite();//获取所有Sites
    String registerUser(User user);//用户注册
    int subSite(String uid,int siteid,int groupid);//关注Site
    boolean favItem(String uid,int Itemid);//收藏Item
    Item updateItem(Item item);//更新Item
    User getUserInfo(String uid);//获取用户信息
    Item getOneItem(int itemid);//获取一条Item,从数据库
    Item getOneItem(int siteid,int itemid);//获取一条Item,从缓存或数据库
    void updateSite();//更新某Site的Item,定时任务
}
