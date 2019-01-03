package com.reader.rss.pojo;

public class Collection {
    private Integer colId;

    private Integer itemId;

    private String accountId;

    public Collection(Integer colId, Integer itemId, String accountId) {
        this.colId = colId;
        this.itemId = itemId;
        this.accountId = accountId;
    }

    public Collection() {
        super();
    }

    public Integer getColId() {
        return colId;
    }

    public void setColId(Integer colId) {
        this.colId = colId;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId == null ? null : accountId.trim();
    }
}