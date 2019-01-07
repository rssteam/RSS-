package com.reader.rss.service.resolvexml;

import com.reader.rss.pojo.Item;
import com.reader.rss.pojo.Site;
import com.reader.rss.pojo.User;

import java.util.List;

public interface IStorageXml {
    void updateRssSource(Site site);
    List<Item> convertXmltoItem(String url);
    void updateAllSite();
    Site getOneSite(int siteid);
    List<Item> getUserItems(String uid);
    List<Site> getUserSites(String uid);
    List<Item> getSiteItems(int siteid);
    Site addNewSite(Site site);
    boolean removeUserFavSite(String uid,int siteid);
    boolean removeUserFavItem(String uid,int itemid);
    List<Site> getAllSite();
    String registerUser(User user);
    boolean favSite(String uid,int siteid);
    boolean favItem(String uid,int Itemid);
    Item updateItem(Item item);
    User getUserInfo(String uid);
    Item getOneItem(int itemid);
    Item getOneItem(int siteid,int itemid);
    void updateSite();
}
