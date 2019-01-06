package com.reader.rss.service.io;

import com.reader.rss.pojo.Content;
import java.util.List;

public interface IJsfile {
    void reShowHtml(String url) throws Exception;//插入JS，生产temp文件，文件名取www*到第一个\或末尾
    List<Content> getXml (String url)throws Exception;
    List<Content> createXML(String filename)throws Exception;
    List<Content> reslovVersion(String content);
    void p(String str);
    String[] trimPage(String page);//去除注释等无关内容
    List<Content> reslovHtml(String url);//解析xml取出需要的Item
    String getTitleiconByPage(String source);//由html得到标题图标
    String getTitleiconByUrl(String Url);//由Url得到标题图标
    String getPicture(String page);
    String getIcon(String url);
}
