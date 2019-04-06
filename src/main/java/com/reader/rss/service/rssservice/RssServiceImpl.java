package com.reader.rss.service.rssservice;

import com.reader.rss.mapper.CollectionMapper;
import com.reader.rss.mapper.UserMapper;
import com.reader.rss.pojo.Collection;
import com.reader.rss.pojo.User;
import com.reader.rss.pojo.UserGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.reader.rss.mapper.*;

import java.security.acl.Group;
import java.util.List;

@Service
public class RssServiceImpl implements  RssService {
    @Autowired(required = false)
    UserMapper userMapper;
    @Autowired(required = false)
    CollectionMapper collectionMapper;
    @Autowired(required = false)
    UserGroupMapper userGroupMapper;
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
    public UserGroup addUserGroup(UserGroup group){
        if( userGroupMapper.getByTitle(group.getGroupTitle()).size()==0)
            userGroupMapper.insert(group);
        return userGroupMapper.getByTitle(group.getGroupTitle()).get(0);
    }
}
