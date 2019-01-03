package com.reader.rss.mapper;


import com.reader.rss.pojo.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    int deleteByPrimaryKey(String accountId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String accountId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}