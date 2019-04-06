package com.reader.rss.service.rssservice;

import com.reader.rss.pojo.Collection;
import com.reader.rss.pojo.User;

import java.util.List;

public interface RssService {
    public User findByID(String userID);
    public List<Collection> checkCollection(Integer itemId,String accountId);
}
