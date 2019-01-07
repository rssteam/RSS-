package com.reader.rss.service.redisservice;

import com.reader.rss.pojo.Item;
import com.reader.rss.pojo.Site;

import java.util.List;

public interface Iredisservice {
    void removeByKey(String key);
    <T> T getByKey(String mapkey,String key,Class<T> tClass);//mapkey为"map"+siteid,key为url
    void setValue(Item value,long time_s);
//    void setValue(String key,String value);
//    void setValue(String key,Object value);
    void setValue(String key,String value,long time_s);
    void updateItemValue(List<Item> list,Site site);
    boolean isExists(String key);
    <T> List<T> getMap(String mapkey,Class<T> tClass);
    Item updateItemAttrubite(Item item);
    Site addSite(Site site);
    void updateSite(Site site);
    void removeSite(int siteid);
    List<Site> preUpdate();
    List<Item> getSiteItems(int siteid);
    Site getSite(int siteid);
    boolean isExistsMapKey(String mapkey,String key);
    Item getItem(int itemid);
    Item getItem(int itemid,int siteid);
    <T> T getByKeyWithoutUpdateExpire(String mapkey,String key,Class<T> tClass);
}
