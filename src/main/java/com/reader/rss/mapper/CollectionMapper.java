package com.reader.rss.mapper;

import com.reader.rss.pojo.Collection;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CollectionMapper {
    int deleteByPrimaryKey(Integer colId);

    int insert(Collection record);

    int insertSelective(Collection record);

    Collection selectByPrimaryKey(Integer colId);

    int updateByPrimaryKeySelective(Collection record);

    int updateByPrimaryKey(Collection record);

    List<Collection> getCollectionsByUid(String accountId);

    void deleteCollectionsByUid(String uid,int itemid);
    List<Collection> checkCollection(Integer itemId,String accountId);
}