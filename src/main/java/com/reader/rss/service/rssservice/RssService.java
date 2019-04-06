package com.reader.rss.service.rssservice;

import com.reader.rss.pojo.Collection;
import com.reader.rss.pojo.Subscribe;
import com.reader.rss.pojo.User;
import com.reader.rss.pojo.UserGroup;

import java.security.acl.Group;
import java.util.List;

public interface RssService {
    public User findByID(String userID);
    public List<Collection> checkCollection(Integer itemId,String accountId);
    public UserGroup addUserGroup(UserGroup group);
}