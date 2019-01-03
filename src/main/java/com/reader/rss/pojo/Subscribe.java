package com.reader.rss.pojo;

public class Subscribe {
    private Integer subId;

    private Integer siteId;

    private Integer groupId;

    private String accountId;

    public Subscribe(Integer subId, Integer siteId, Integer groupId, String accountId) {
        this.subId = subId;
        this.siteId = siteId;
        this.groupId = groupId;
        this.accountId = accountId;
    }

    public Subscribe() {
        super();
    }

    public Integer getSubId() {
        return subId;
    }

    public void setSubId(Integer subId) {
        this.subId = subId;
    }

    public Integer getSiteId() {
        return siteId;
    }

    public void setSiteId(Integer siteId) {
        this.siteId = siteId;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId == null ? null : accountId.trim();
    }
}