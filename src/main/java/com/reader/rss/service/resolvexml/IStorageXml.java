package com.reader.rss.service.resolvexml;

import com.reader.rss.pojo.Item;
import com.reader.rss.pojo.Site;

import java.util.List;

public interface IStorageXml {
    void updateRssSource(Site site);
    List<Item> convertXmltoItem(String url);
    void updateAllSite();
}
