package com.reader.rss.pojo;

public class UserGroup {
    private Integer groupId;

    private String groupTitle;

    private String accountId;

    public UserGroup(Integer groupId, String groupTitle, String accountId) {
        this.groupId = groupId;
        this.groupTitle = groupTitle;
        this.accountId = accountId;
    }

    public UserGroup() {
        super();
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getGroupTitle() {
        return groupTitle;
    }

    public void setGroupTitle(String groupTitle) {
        this.groupTitle = groupTitle == null ? null : groupTitle.trim();
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId == null ? null : accountId.trim();
    }
}