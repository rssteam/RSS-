package com.reader.rss.pojo;

import java.util.Date;

public class User {
    private String accountId;

    private String pwd;

    private String name;

    private String image;

    private Date birth;

    public User(String accountId, String pwd, String name, String image, Date birth) {
        this.accountId = accountId;
        this.pwd = pwd;
        this.name = name;
        this.image = image;
        this.birth = birth;
    }

    public User() {
        super();
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId == null ? null : accountId.trim();
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd == null ? null : pwd.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image == null ? null : image.trim();
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }
}