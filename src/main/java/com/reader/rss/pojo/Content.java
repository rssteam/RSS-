package com.reader.rss.pojo;

public class Content {
    private String title;
    private String url;
    private String descr;
    private String img;
    private String date;
    private  String icon;

    public Content() {
    }

    public Content(String title, String url, String descr, String img, String date, String icon) {
        this.title = title;
        this.url = url;
        this.descr = descr;
        this.img = img;
        this.date = date;
        this.icon = icon;
    }

    @Override
    public String toString() {
        return "Content{" +
                "title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", descr='" + descr + '\'' +
                ", img='" + img + '\'' +
                ", date='" + date + '\'' +
                ", icon='" + icon + '\'' +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
