package com.reader.rss.mapper;

import com.reader.rss.pojo.Subscribe;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SubscribeMapper {
    int deleteByPrimaryKey(Integer subId);

    int insert(Subscribe record);

    int insertSelective(Subscribe record);

    Subscribe selectByPrimaryKey(Integer subId);

    int updateByPrimaryKeySelective(Subscribe record);

    int updateByPrimaryKey(Subscribe record);
}