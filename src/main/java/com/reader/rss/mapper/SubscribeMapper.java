package com.reader.rss.mapper;

import com.reader.rss.pojo.Subscribe;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Mapper
public interface SubscribeMapper {
    int deleteByPrimaryKey(Integer subId);

    int insert(Subscribe record);

    int insertSelective(Subscribe record);

    Subscribe selectByPrimaryKey(Integer subId);

    int updateByPrimaryKeySelective(Subscribe record);

    int updateByPrimaryKey(Subscribe record);

    List<Subscribe> getSubscribeByUid(String accountId);
    List<Subscribe> getSubscribeByUidandGroupid(String accountId,int groupid);

    void deleteById(@Param("uid") String accountId, @Param("siteid") int siteid);
}