package com.reader.rss.service.redisservice;

import com.reader.rss.pojo.Item;
import com.reader.rss.pojo.Site;

import java.util.List;

public interface Iredisservice {
    void removeByKey(String key);
    <T> T getByKey(String mapkey,String key,Class<T> tClass);//mapkey为"map"+siteid,key为url
    void setValue(String key,Item value,long time_s);
    void setValue(String key,String value);
    void setValue(String key,Object value);
    void updateValue(List<Item> list,Site site);
    boolean isExists(String key);
    <T> List<T> getSiteItem(String mapkey);
    void updateAttrubite(Item item);
}
