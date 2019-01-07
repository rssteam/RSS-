package com.reader.rss.service.redisservice;
import com.reader.rss.mapper.ItemMapper;
import com.reader.rss.mapper.SiteMapper;
import com.reader.rss.pojo.Item;
import com.reader.rss.pojo.Site;
import com.reader.rss.service.io.IJsfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
public class RedisService implements Iredisservice {
    private static final int  expire = 1*60*60;//Site缓存时间一小时，获取后自动更新时间
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired(required = false)
    private ItemMapper itemMapper;
    @Autowired(required = false)
    private SiteMapper siteMapper;
    @Autowired
    IJsfile jsfile;
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
            if(mapkey.equals("mapsite")){
                setValue(key,res,expire);
//                System.out.println(Jutil.convertString2Obj(res,tClass));
            }
            return Jutil.convertString2Obj(res,tClass);
        }
        return null;
    }

    //获取后不更新Expire时间
    @Override
    public <T> T getByKeyWithoutUpdateExpire(String mapkey,String key,Class<T> tClass) {
        String res = (String) redisTemplate.opsForHash().get(mapkey,key);
        if(res != null){
            return Jutil.convertString2Obj(res,tClass);
        }
        return null;
    }
    @Override
    public <T> List<T> getMap(String mapkey,Class<T> tClass) {
        List<T> list = new ArrayList<>();
        Set set = redisTemplate.opsForHash().keys(mapkey);
        Iterator iterator = set.iterator();
        while (iterator.hasNext()){
            list.add(getByKey(mapkey,(String)iterator.next(),tClass));
        }
        return list;
    }

    @Override
    public <T> List<T> getMapWithoutUpdateExpire(String mapkey, Class<T> tClass) {
        List<T> list = new ArrayList<>();
        Set set = redisTemplate.opsForHash().keys(mapkey);
        Iterator iterator = set.iterator();
        while (iterator.hasNext()){
            list.add(getByKeyWithoutUpdateExpire(mapkey,(String)iterator.next(),tClass));
        }
        return list;
    }

    @Override
    public void setValue(Item value,long time_s) {
        String string = Jutil.convertObj2String(value);
        redisTemplate.opsForHash().put("map"+value.getSiteId(),"item"+value.getItemId(),Jutil.convertObj2String(value));
    }
/*
    @Override
    public void setValue(String key, String value) {
        redisTemplate.opsForValue().set(key,value);
    }

    @Override
    public void setValue(String key, Object value) {
        redisTemplate.opsForValue().set(key,Jutil.convertObj2String(value));
    }*/

    @Override
    public boolean isExists(String key) {
        return redisTemplate.hasKey(key);
    }



    @Override
    public void updateItemValue(List<Item> list,Site site) {
        String key = "";
        Map<String,Item> map = new HashMap<>();
        Item var;
        for(Item item:list)
            map.put(item.getItemUrl(),item);//写入hashmap
        Set set = redisTemplate.opsForHash().keys("map"+site.getSiteId());
        Iterator iterator = set.iterator();
        while(iterator.hasNext()){
            key = (String) iterator.next();
            var = getByKeyWithoutUpdateExpire("map"+site.getSiteId(),key,Item.class);
            if(map.containsKey(var.getItemUrl())){
                map.remove(var.getItemUrl());
            }
            else{
                redisTemplate.opsForHash().delete("map"+site.getSiteId(),key);
            }
        }
        List<Item> values = new ArrayList<>(map.values());
        for(int i=0;i< values.size();++i){
            var = values.get(i);
            var.setSiteId(site.getSiteId());
            var.setItemDate(new Date());
            var.setItemIcon(site.getSiteIcon());
            itemMapper.insert(var);
            var = itemMapper.selectNewItem();
            redisTemplate.opsForHash().put("map"+site.getSiteId(),"item"+var.getItemId(),Jutil.convertObj2String(var));
        }
    }

    @Override
    public Item updateItemAttrubite(Item item) {
        itemMapper.updateByPrimaryKey(item);
        setValue(item,expire);
        return itemMapper.selectByPrimaryKey(item.getItemId());
    }

    @Override
    public void setValue(String key, String value, long time_s) {
        redisTemplate.opsForHash().put("mapsite",key,value);
        redisTemplate.opsForValue().set(key,value,time_s,TimeUnit.SECONDS);
    }

    @Override
    public Site addSite(Site site) {
        if(siteMapper.getSiteByUrl(site.getSiteUrl()).size() == 0) {
            site.setSiteIcon(jsfile.getIcon(site.getSiteUrl()));
            siteMapper.insert(site);
            site = siteMapper.getSiteByUrl(site.getSiteUrl()).get(0);
            setValue("site" + site.getSiteId(), Jutil.convertObj2String(site), expire);
            return site;
        }
        System.out.println("重复的SiteUrl");
        return siteMapper.getSiteByUrl(site.getSiteUrl()).get(0);
    }

    @Override
    public void updateSite(Site site) {
        siteMapper.updateByPrimaryKey(site);
        setValue("site"+site.getSiteId(),Jutil.convertObj2String(site),expire);
    }

    @Override
    public void removeSite(int siteid) {
//        Site site = siteMapper.selectByPrimaryKey(key);
        removeByKey("site"+siteid);
        redisTemplate.opsForHash().delete("mapsite","site"+siteid);
        siteMapper.deleteByPrimaryKey(siteid);
    }

    @Override
    public List<Site> preUpdate() {
//        System.out.println("preUpdate");
        List<Site> list = getMapWithoutUpdateExpire("mapsite",Site.class);
        for(int i = 0;i < list.size();++i){
            if(!isExists("site"+list.get(i).getSiteId())){
                redisTemplate.opsForHash().delete("mapsite","site"+list.get(i).getSiteId());
                list.remove(i);
                i--;
            }
        }
        return list;
    }

    @Override
    public boolean isExistsMapKey(String mapkey, String key) {
        return redisTemplate.opsForHash().hasKey(mapkey,key);
    }

    @Override
    public List<Item> getSiteItems(int siteid) {
//        System.out.println(siteid);
        if(isExists("map"+siteid)) return getMap("map"+siteid,Item.class);
        List<Item> list = itemMapper.selectBysiteid(siteid);
        updateItemValue(list,getSite(siteid));
        return list;
    }

    @Override
    public Site getSite(int siteid) {
        Site site = null;
        if(!isExists("site"+siteid)){
            site = siteMapper.selectByPrimaryKey(siteid);
            preUpdate();
            setValue("site"+siteid,Jutil.convertObj2String(site),expire);
        }
        else {
            site = getByKey("mapsite","site"+siteid,Site.class);
        }
        return site;
    }

    @Override
    public Item getItem(int itemid) {
       return itemMapper.selectByPrimaryKey(itemid);
    }

    @Override
    public Item getItem(int itemid, int siteid) {
        if(isExistsMapKey("map"+siteid,"item"+itemid)) {
            Item item = getByKey("map" + siteid, "item" + itemid, Item.class);
            return item;
        }
            Item item = itemMapper.selectByPrimaryKey(itemid);
            setValue(item,expire);
            return item;

    }
}
