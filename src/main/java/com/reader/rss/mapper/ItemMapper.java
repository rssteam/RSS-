package com.reader.rss.mapper;

import com.reader.rss.pojo.Item;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ItemMapper {
    int deleteByPrimaryKey(Integer itemId);

    int insert(Item record);

    int insertSelective(Item record);

    Item selectByPrimaryKey(Integer itemId);

    int updateByPrimaryKeySelective(Item record);

    int updateByPrimaryKey(Item record);

    Item selectNewItem();

    List<Item> selectBysiteid(int siteid);
    List<Item> selectAllItem();
}