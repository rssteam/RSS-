package com.reader.rss.service.resolvexml;

import com.reader.rss.mapper.CollectionMapper;
import com.reader.rss.mapper.SiteMapper;
import com.reader.rss.mapper.SubscribeMapper;
import com.reader.rss.mapper.UserMapper;
import com.reader.rss.pojo.*;
import com.reader.rss.service.io.IJsfile;
import com.reader.rss.service.redisservice.Iredisservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StorageXml implements IStorageXml {
    private List<Site> siteList = new ArrayList<>();
    @Autowired
    private IJsfile jsfile;
    @Autowired
    private Iredisservice redisservice;
    @Autowired(required =false)
    private SiteMapper siteMapper;
    @Autowired(required = false)
    private SubscribeMapper subscribeMapper;
    @Autowired(required = false)
    private CollectionMapper collectionMapper;
    @Autowired(required = false)
    private UserMapper userMapper;
    //更新某网站的Item
    @Override
    public void updateRssSource(Site site) {
        List<Item> list = new ArrayList<>(convertXmltoItem(site.getSiteUrl()));
        redisservice.updateItemValue(list,site);
    }

    @Override
    @Async
    @Scheduled(fixedDelay = 100)
    public void updateSite() {
        Site site = null;
        synchronized (siteList){
            if(siteList.size() > 0) {
                System.out.println("缓存中Site数目："+siteList.size());
                site = siteList.get(0);
                siteList.remove(0);
            }
        }
        if(site != null) {
            List<Item> list = new ArrayList<>(convertXmltoItem(site.getSiteUrl()));
            synchronized (redisservice) {
                System.out.println("更新中...");
                redisservice.updateItemValue(list, site);
            }
        }
    }



    @Override
    public List<Item> convertXmltoItem(String url) {
        List<Item> res = new ArrayList<>();
        List<Content> list = jsfile.reslovHtml(url);
        for(Content content:list){
            res.add(new Item(content));
        }
        return res;
    }

    //取得所有网站更新页面
    @Override
    @Scheduled(fixedDelay = 10000)
    public void updateAllSite() {
//        List<Site> list = new ArrayList<>(redisservice.preUpdate());
        synchronized (siteList) {
            siteList.addAll(redisservice.preUpdate());
        }
/*        for(Site site:list){
            updateRssSource(site);
        }*/
    }

    //取得某网站Site
    @Override
    public Site getOneSite(int siteid) {
        Site site = redisservice.getSite(siteid);
        if(site != null)return site;
        System.out.println("空Site");
        return null;
    }

    //获取用户关注的所有Item
    @Override
    public List<Item> getUserItems(String uid) {
        List<Site> list = getUserSites(uid);
        List<Item> list_item = new ArrayList<>();
        for(int i = 0;i < list.size();++i){
            list_item.addAll(getSiteItems(list.get(i).getSiteId()));
        }
        return list_item;
    }

    //获取用户关注的所有Site
    @Override
    public List<Site> getUserSites(String uid) {
        List<Subscribe> list =  subscribeMapper.getSubscribeByUid(uid);
        List<Site> list_site = new ArrayList<>();
        for(int i = 0;i < list.size();++i){
            list_site.add(getOneSite(list.get(i).getSiteId()));
        }
        return list_site;
    }

    //获取某网页所有Item
    @Override
    public List<Item> getSiteItems(int siteid) {
        List<Item> list = redisservice.getSiteItems(siteid);
        if(list.size() > 0)return list;
        System.out.println("空Item");
        return null;
    }

    @Override
    public Site addNewSite(Site site) {
        return  redisservice.addSite(site);
    }

    //取关网站
    @Override
    public boolean removeUserFavSite(String uid, int siteid) {
        if(subscribeMapper.getSubscribeByUid(uid) != null){
            subscribeMapper.deleteById(uid,siteid);
            return  true;
        }
        return false;
    }

    //取消收藏
    @Override
    public boolean removeUserFavItem(String uid, int itemid) {
        if(collectionMapper.getCollectionsByUid(uid) != null){
            collectionMapper.deleteCollectionsByUid(uid,itemid);
            return  true;
        }
        return false;
    }

    //获取已有的所有网站
    @Override
    public List<Site> getAllSite() {
        return siteMapper.getAllSite();
    }

    //用户注册
    @Override
    public String registerUser(User user) {
        userMapper.insert(user);
        return userMapper.selectNewUser().getAccountId();
    }

    //关注网站
    @Override
    public boolean favSite(String uid, int siteid) {
        return false;
    }

    //收藏Item
    @Override
    public boolean favItem(String uid, int Itemid) {
        return false;
    }

    //更新Item点赞，收藏等
    @Override
    public Item updateItem(Item item) {
        redisservice.updateItemAttrubite(item);
        return getOneItem(item.getSiteId(),item.getItemId());
    }

    @Override
    public Item getOneItem(int itemid) {
        return redisservice.getItem(itemid);
    }
    @Override
    public Item getOneItem(int siteid,int itemid) {
        return redisservice.getItem(siteid,itemid);
    }
    //得到用户信息
    @Override
    public User getUserInfo(String uid) {
        return userMapper.selectByPrimaryKey(uid);
    }
}
