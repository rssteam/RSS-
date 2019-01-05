package com.reader.rss.service.redisservice;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.reader.rss.mapper.ItemMapper;
import com.reader.rss.pojo.Collection;
import com.reader.rss.pojo.Item;
import com.reader.rss.pojo.Site;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.*;
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
    public void setValue(String key, Item value,long time_s) {
        String string = Jutil.convertObj2String(value);
        redisTemplate.opsForHash().put("map"+value.getSiteId(),key,Jutil.convertObj2String(value));
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
    public void updateValue(List<Item> list,Site site) {
        String key = "";
        Map<String,Item> map = new HashMap<>();
        Item var;
        for(Item item:list)
            map.put(item.getItemUrl(),item);//写入hashmap
        Set set = redisTemplate.opsForHash().keys("map"+list.get(0).getSiteId());
        Iterator iterator = set.iterator();
        while(iterator.hasNext()){
            key = (String) iterator.next();
            if(map.containsKey(key)){
                map.remove(key);
            }
            else{
                redisTemplate.opsForHash().delete("map"+list.get(0).getSiteId(),key);
            }
        }
        List<Item> values = new ArrayList<>(map.values());
        for(int i=0;i< values.size();++i){
            var = values.get(i);
            var.setSiteId(site.getSiteId());
            var.setItemDatae(new Date());
            itemMapper.insert(var);
            var = itemMapper.selectNewItem();
            redisTemplate.opsForHash().put("map"+site.getSiteId(),var.getItemUrl(),Jutil.convertObj2String(var));
        }
    }

    @Override
    public void updateAttrubite(Item item) {
        itemMapper.updateByPrimaryKey(item);
        setValue(item.getItemUrl(),item,expire);
    }
}
