package com.reader.rss.service.redisservice;

import com.reader.rss.pojo.Item;
import com.reader.rss.pojo.Site;

import java.util.List;
/**缓存中保留当前各网页的最新Item和活跃的Site，
*对缓存中的Site每十秒更新一次Items，无操作一小时后过期
**/
public interface Iredisservice {
    void removeByKey(String key);//删除某 key
    <T> T getByKey(String mapkey,String key,Class<T> tClass);//mapkey为"map"+siteid,key为url
    void setValue(Item value,long time_s);//Item存储
//    void setValue(String key,String value);
//    void setValue(String key,Object value);
    void setValue(String key,String value,long time_s);//Site存储
    void updateItemValue(List<Item> list,Site site);//更新某网站所有Item
    boolean isExists(String key);//判断是否存在某Key，非集合
    <T> List<T> getMapWithoutUpdateExpire(String mapkey,Class<T> tClass);//获取某map集合所有cache,不更新
    <T> List<T> getMap(String mapkey,Class<T> tClass);//获取某map集合所有cache
    Item updateItemAttrubite(Item item);//更新某条目内容
    Site addSite(Site site);//新增Site，不写入cache
    void updateSite(Site site);//更新某Site
    void removeSite(int siteid);//删除某Site
    List<Site> preUpdate();//去除cache中过期的Site
    List<Item> getSiteItems(int siteid);//得到某 网站所有当前Item
    Site getSite(int siteid);//得到某Site
    boolean isExistsMapKey(String mapkey,String key);//判断map集合中是否存在某key
    Item getItem(int itemid);//得到某item,from database
    Item getItem(int itemid,int siteid);//得到某item,先查缓存，在数据库
    <T> T getByKeyWithoutUpdateExpire(String mapkey,String key,Class<T> tClass);//获取后不更新Expire时间
}
