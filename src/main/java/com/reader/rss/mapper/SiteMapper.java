package com.reader.rss.mapper;

import com.reader.rss.pojo.Site;

public interface SiteMapper {
    int deleteByPrimaryKey(Integer siteId);

    int insert(Site record);

    int insertSelective(Site record);

    Site selectByPrimaryKey(Integer siteId);

    int updateByPrimaryKeySelective(Site record);

    int updateByPrimaryKey(Site record);
}