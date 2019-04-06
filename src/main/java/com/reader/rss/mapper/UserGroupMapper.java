package com.reader.rss.mapper;

import com.reader.rss.pojo.UserGroup;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserGroupMapper {
    int deleteByPrimaryKey(Integer groupId);

    int insert(UserGroup record);

    int insertSelective(UserGroup record);

    UserGroup selectByPrimaryKey(Integer groupId);

    List< UserGroup> getByTitle(String groupTitle);

    int updateByPrimaryKeySelective(UserGroup record);

    int updateByPrimaryKey(UserGroup record);
    List<UserGroup> selectByUid(String accoutId);
}