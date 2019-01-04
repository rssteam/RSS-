package com.reader.rss.pojo;

import java.util.Date;

public class Item {
    private Integer itemId;

    private String itemTitle;

    private String itemUrl;

    private Integer siteId;

    private Integer favNum;

    private Integer likeNum;

    private Date itemDatae;

    public Item(Integer itemId, String itemTitle, String itemUrl, Integer siteId, Integer favNum, Integer likeNum, Date itemDatae) {
        this.itemId = itemId;
        this.itemTitle = itemTitle;
        this.itemUrl = itemUrl;
        this.siteId = siteId;
        this.favNum = favNum;
        this.likeNum = likeNum;
        this.itemDatae = itemDatae;
    }

    @Override
    public String toString() {
        return "Item{" +
                "itemId=" + itemId +
                ", itemTitle='" + itemTitle + '\'' +
                ", itemUrl='" + itemUrl + '\'' +
                ", siteId=" + siteId +
                ", favNum=" + favNum +
                ", likeNum=" + likeNum +
                ", itemDatae=" + itemDatae +
                '}';
    }

    public Item(String itemTitle,String url) {
        this.itemTitle = itemTitle;
        this.itemUrl = url;
    }

    public Item(String itemTitle, String itemUrl, Integer siteId, Integer favNum, Integer likeNum) {
        this.itemTitle = itemTitle;
        this.itemUrl = itemUrl;
        this.siteId = siteId;
        this.favNum = favNum;
        this.likeNum = likeNum;
    }

    public Item(Content content){
        this.itemTitle = content.getTitle();
        this.itemUrl = content.getUrl();
        this.favNum = 0;
        this.likeNum = 0;
        this.siteId = 1;
        this.itemDatae = null;
    }
    public Item() {
        super();
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getItemTitle() {
        return itemTitle;
    }

    public void setItemTitle(String itemTitle) {
        this.itemTitle = itemTitle == null ? null : itemTitle.trim();
    }

    public String getItemUrl() {
        return itemUrl;
    }

    public void setItemUrl(String itemUrl) {
        this.itemUrl = itemUrl == null ? null : itemUrl.trim();
    }

    public Integer getSiteId() {
        return siteId;
    }

    public void setSiteId(Integer siteId) {
        this.siteId = siteId;
    }

    public Integer getFavNum() {
        return favNum;
    }

    public void setFavNum(Integer favNum) {
        this.favNum = favNum;
    }

    public Integer getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(Integer likeNum) {
        this.likeNum = likeNum;
    }

    public Date getItemDatae() {
        return itemDatae;
    }

    public void setItemDatae(Date itemDatae) {
        this.itemDatae = itemDatae;
    }
}