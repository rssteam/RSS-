package com.reader.rss.service.rssservice;

import com.reader.rss.mapper.CollectionMapper;
import com.reader.rss.mapper.ItemMapper;
import com.reader.rss.mapper.UserMapper;
import com.reader.rss.pojo.Collection;
import com.reader.rss.pojo.Item;
import com.reader.rss.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class RssServiceImpl implements  RssService {
    @Autowired(required = false)
    UserMapper userMapper;
    @Autowired(required = false)
    CollectionMapper collectionMapper;
    @Autowired(required = false)
    ItemMapper itemMapper;


    @Override
    public User findByID(String userID) {
        User user=userMapper.selectByPrimaryKey(userID);
         return  user;
    }

    @Override
    public List<Collection> checkCollection(Integer itemId, String accountId) {
        List<Collection> collections=collectionMapper.checkCollection(itemId,accountId);
        return collections;
    }

    @Override
    public int register(User user) {
             return  userMapper.insertSelective(user);
    }

    @Override
    public List<User> login(String accountId, String password) {
        List<User> users=userMapper.login(accountId,password);
        return  users;
    }
    @Override
    public List<Item> getAllItems() {
        List<Item> items=itemMapper.selectAllItem();
        items.sort(Comparator.comparing(Item::getLikeNum).thenComparing(Item::getFavNum).thenComparing(Item::getItemDate).reversed());
         if (items.size()>=50){
            return items.subList(0,50);
        }else {
             return  items;
         }

    }

}
