package com.reader.rss.service.resolvexml;

import com.reader.rss.mapper.SiteMapper;
import com.reader.rss.pojo.Content;
import com.reader.rss.pojo.Item;
import com.reader.rss.pojo.Site;
import com.reader.rss.service.io.IJsfile;
import com.reader.rss.service.redisservice.Iredisservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StorageXml implements IStorageXml {
    @Autowired
    private IJsfile jsfile;
    @Autowired
    private Iredisservice redisservice;
    @Autowired(required =false)
    private SiteMapper siteMapper;
    @Override
    public void updateRssSource(Site site) {
        List<Item> list = convertXmltoItem(site.getSiteUrl());
        redisservice.updateValue(list,site);
    }

    @Override
    public List<Item> convertXmltoItem(String url) {
        List<Item> res = new ArrayList<>();
        List<Content> list = jsfile.reslovHtml(url);
        for(Content content:list){
            res.add(new Item(content));
        }
        return res;
    }

    //取得所有网站更新页面
    @Override
    public void updateAllSite() {
        List<Site> list = siteMapper.getAllSite();
        for(Site site:list){
            updateRssSource(site);
        }
    }
}
