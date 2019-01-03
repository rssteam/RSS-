package com.reader.rss.pojo;

public class Content {
    private String title;
    private String url;
    private String descr;
    private String author;
    private String date;

    @Override
    public String toString() {
        return "Content{" +
                "title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", descr='" + descr + '\'' +
                ", author='" + author + '\'' +
                ", date='" + date + '\'' +
                '}';
    }

    public Content(String title, String url, String descr,  String date, String author) {
        this.title = title;
        this.url = url;
        this.descr = descr;
        this.author = author;
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
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
