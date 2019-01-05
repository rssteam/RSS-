package com.reader.rss.mapper;

import com.reader.rss.pojo.Site;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SiteMapper {
    int deleteByPrimaryKey(Integer siteId);

    int insert(Site record);

    int insertSelective(Site record);

    Site selectByPrimaryKey(Integer siteId);

    int updateByPrimaryKeySelective(Site record);

    int updateByPrimaryKey(Site record);

    List<Site> getAllSite();

    List<Site> getSiteByUrl(String url);
}