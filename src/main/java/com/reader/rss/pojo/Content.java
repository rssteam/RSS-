package com.reader.rss.pojo;

public class Content {
    private String title;
    private String url;
    private String descr;
    private String img;
    private String date;

    @Override
    public String toString() {
        return "Content{" +
                "title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", descr='" + descr + '\'' +
                ", author='" + img + '\'' +
                ", date='" + date + '\'' +
                '}';
    }

    public Content(String title, String url, String descr,  String date, String img) {
        this.title = title;
        this.url = url;
        this.descr = descr;
        this.img = img;
        this.date = date;
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

    public String getAuthor() {
        return img;
    }

    public void setAuthor(String author) {
        this.img = author;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
