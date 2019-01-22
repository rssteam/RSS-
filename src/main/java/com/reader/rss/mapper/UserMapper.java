package com.reader.rss.mapper;
import org.apache.ibatis.annotations.Mapper;
import com.reader.rss.pojo.User;

import java.util.List;

@Mapper
public interface UserMapper {
    int deleteByPrimaryKey(String accountId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String accountId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User selectNewUser();
    List<User> login(String accountId, String password);
}