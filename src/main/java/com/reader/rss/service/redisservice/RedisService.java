package com.reader.rss.service.redisservice;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.reader.rss.mapper.ItemMapper;
import com.reader.rss.pojo.Item;
import com.reader.rss.pojo.Site;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class RedisService implements Iredisservice {
    private static final int  expire = 24*60*60;
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired(required = false)
    private ItemMapper itemMapper;
    @Override
    public void removeByKey(String key) {
        if(redisTemplate.hasKey(key)){
            redisTemplate.delete(key);
        }
    }


    @Override
    public <T> T getByKey(String mapkey,String key,Class<T> tClass) {
        String res = (String) redisTemplate.opsForHash().get(mapkey,key);
        if(res != null){
            return Jutil.convertString2Obj(res,tClass);
        }
        return null;
    }

    @Override
    public <T> List<T> getSiteItem(String mapkey) {
        return (List)redisTemplate.opsForHash().entries(mapkey);
    }

    @Override
    public void setValue(String key, Item value,int siteid,long time_s) {
        String string = Jutil.convertObj2String(value);
//        redisTemplate.opsForValue().set(key,Jutil.convertObj2String(value),time_s, TimeUnit.SECONDS);
        redisTemplate.opsForHash().put("map"+siteid,key,Jutil.convertObj2String(value));
        redisTemplate.expire("map"+siteid,time_s,TimeUnit.SECONDS);
    }

    @Override
    public void setValue(String key, String value) {
        redisTemplate.opsForValue().set(key,value);
    }

    @Override
    public void setValue(String key, Object value) {
        redisTemplate.opsForValue().set(key,Jutil.convertObj2String(value));
    }

    @Override
    public boolean isExists(String key) {
        return redisTemplate.hasKey(key);
    }

    @Override
    public void updateValue(List<Item> list,int siteid) {
//        Map<String,String> map = new HashMap<>();
        for (Item item : list) {
            String str = Jutil.convertObj2String(item);
            Item item1 = Jutil.convertString2Obj(str,Item.class);
//            if(set.isMember(""+item.getSiteId(),item))continue;
//            if (isExists(item.getItemUrl())) continue;
//            map.put("map"+siteid,Jutil.convertObj2String(item));
            if(redisTemplate.opsForHash().hasKey("map"+siteid,item.getItemUrl()))continue;//缓存中已存在该条目
            item.setSiteId(siteid);
            item.setItemDatae(new Date());
            System.out.println(item);
            itemMapper.insert(item);//写数据库
            item = itemMapper.selectNewItem();
            setValue(item.getItemUrl(),item,siteid,expire);//写缓存
        }
    }

    @Override
    public void updateAttrubite(Item item) {
        itemMapper.updateByPrimaryKey(item);
        setValue(item.getItemUrl(),item,item.getSiteId(),expire);
    }
}
