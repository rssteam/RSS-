package com.reader.rss.pojo;

import java.util.Date;

public class Item {
    private Integer itemId;

    private String itemTitle;

    private String itemUrl;

    private Integer siteId;

    private Integer favNum;

    private Integer likeNum;

    private Date itemDate;

    private String itemIcon;

    private String itemDes;

    private String itemPic;

    public Item(Integer itemId, String itemTitle, String itemUrl, Integer siteId, Integer favNum, Integer likeNum, Date itemDate, String itemIcon, String itemDes, String itemPic) {
        this.itemId = itemId;
        this.itemTitle = itemTitle;
        this.itemUrl = itemUrl;
        this.siteId = siteId;
        this.favNum = favNum;
        this.likeNum = likeNum;
        this.itemDate = itemDate;
        this.itemIcon = itemIcon;
        this.itemDes = itemDes;
        this.itemPic = itemPic;
    }

    public Item() {
        super();
    }
    public Item(Content content) {
//       this.itemTitle= content.getTitle();
//        this.itemUrl=content.getUrl();
//        content.getDate();
//        content.getDescr();
        this(null,content.getTitle(),content.getUrl(),null,0,0,null,content.getIcon(),content.getDescr(),content.getImg());

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

    public Date getItemDate() {
        return itemDate;
    }

    public void setItemDate(Date itemDate) {
        this.itemDate = itemDate;
    }

    public String getItemIcon() {
        return itemIcon;
    }

    public void setItemIcon(String itemIcon) {
        this.itemIcon = itemIcon == null ? null : itemIcon.trim();
    }

    public String getItemDes() {
        return itemDes;
    }

    public void setItemDes(String itemDes) {
        this.itemDes = itemDes == null ? null : itemDes.trim();
    }

    public String getItemPic() {
        return itemPic;
    }

    public void setItemPic(String itemPic) {
        this.itemPic = itemPic == null ? null : itemPic.trim();
    }

}