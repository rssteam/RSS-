package com.reader.rss.service.rssservice;

import com.reader.rss.pojo.Collection;
import com.reader.rss.pojo.Item;
import com.reader.rss.pojo.User;

import java.util.List;

public interface RssService {
    public User findByID(String userID);
    public List<Collection> checkCollection(Integer itemId,String accountId);
    public List<User> login(String accountId,String password);
    public int register(User user);
    public List<Item> getAllItems();
}
