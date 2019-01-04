package com.reader.rss.service.resolvexml;

import com.reader.rss.pojo.Item;
import com.reader.rss.pojo.Site;

import java.util.List;

public interface IStorageXml {
    void updateRssSource(String url,int siteid);
    List<Item> convertXmltoItem(String url);
    void updateAllSite();
}
