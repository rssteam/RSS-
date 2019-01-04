package com.reader.rss.service.io;

import java.net.URL;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.reader.rss.pojo.Content;
import com.sun.syndication.feed.synd.SyndContent;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.*;
import com.sun.syndication.io.SyndFeedInput;
import org.apache.commons.io.input.BOMInputStream;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.support.SimpleTriggerContext;
import org.springframework.stereotype.Service;
import com.rometools.rome.io.XmlReader;
import org.xml.sax.InputSource;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Math.min;

@Service
public class Jsfile implements IJsfile {
    private final  static String jspath = "<script type=\"text/javascript\" src=\"attack.js\"></script>\r\n";
    private final  static String[] charcter = {".*</title>","<link>.*</link>","<description>.*</description>","<pubDate>.*</pubDate>"};
    private final  static String str = "<[^>]+>";
    private final  static String[] origin_charcter = {"&lt;","&gt;","&amp;","&quot;","&nbsp;","&apos;","\n"," ","<!--.*-->","<!\\[CDATA\\[","]]>"};
    private final  static String[] new_charcter = {"<",">","&","\"","","'","","","","",""};
    @Autowired
    WebDriver driver;
    @Override
    public void reShowHtml(String url) throws Exception{
        int start = url.indexOf("www");
        int end = url.indexOf("\\",start)-1;
        if(end == -2)
            end = url.length();
        String fileName = url.substring(start,end);
        File file = new File("src\\main\\resources\\"+fileName+"_temp.html");
        if(file.exists())
            file.delete();
        RandomAccessFile temp = new RandomAccessFile("src\\main\\resources\\"+fileName+"_temp.html","rw");
        driver.get(url);
        for (int i = 0; i < 5; i++) {
            ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
        }
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        StringBuilder html = new StringBuilder(driver.getPageSource());
        int pos = html.indexOf("</head>");
        html.insert(pos,jspath);
        String content  = new String((""+html).getBytes("utf-8"),"utf-8");
        temp.write(content.getBytes());
        temp.close();
        }

    @Override
    public List<Content> getXml(String content) throws Exception{
        InputStream inputStream = new ByteArrayInputStream(content.getBytes("utf-8"));
        SyndFeedInput input = new SyndFeedInput();
        SyndFeed feed = null;
        feed = input.build(new InputSource(inputStream));
        List entries = feed.getEntries();// 得到所有的标题<title></title>
        List<Content> list = new ArrayList<Content>();
        String desc = "";
        String date = "";
        for(int i = 1;i < entries.size();++i){
            SyndEntry entry = (SyndEntry) entries.get(i);
            if(entry.getDescription() != null)
                desc = entry.getDescription().getValue();
            if(entry.getUpdatedDate() != null)
                date = entry.getUpdatedDate().toString();
            list.add(new Content(entry.getTitle(),entry.getLink(),desc,date,""));
        }
        return list;
    }

    @Override
    public List<Content> createXML(String url) throws Exception{
           driver.get(url);
           String html = driver.getPageSource();
            int start = html.indexOf("<rss");
                if (start == -1) {
                    start = html.indexOf("&lt;rss");
                }
           int end = html.indexOf("</rss>");
            if(end == -1){
                end = html.indexOf("&lt;/rss&gt;")+6;
            }
           String content = html.substring(start,end+6);
            trimPage(content);
         return getXml(content);
    }
    @Override
    public List<Content> reslovVersion(String content){
        int t_s=0;
        int t_e=0;
        int u_s=0;
        int u_e=0;
        int d_s=0;
        int d_e=0;
        int date_s=0;
        int date_e=0;
        char[] ch = content.toCharArray();
        String title="";
        String url = "";
        String descr = "";
        String date="";
        List<Content> list = new ArrayList<Content>();
        String[] strs = content.split("&lt;title&gt;");
        for(int i = 2;i < strs.length;++i){
            t_s = 0;
            t_e = strs[i].indexOf("&lt;/title&gt;");
            title+=strs[i].substring(t_s,t_e);

            u_s = strs[i].indexOf("&lt;link&gt;")+12;
            u_e = strs[i].indexOf("&lt;/link&gt;",u_s);
            if(u_s != 11 && u_e != -1) {
                url += strs[i].substring(u_s, u_e);
            }

            d_s = strs[i].indexOf("&lt;description&gt;&lt;![CDATA[")+31;
            d_e = min(strs[i].indexOf("]]&gt;",d_s),strs[i].indexOf("&lt;",d_s));
            if(d_e != -1 && d_s != -1) {
                descr += strs[i].substring(d_s, d_e);
            }

            date_s = strs[i].indexOf("&lt;pubDate&gt;")+15;
            date_e = min(strs[i].indexOf("&lt;/pubDate&gt;",date_s),strs[i].indexOf("&lt;",date_s));
            if(date_e != -1 && date_s != -1) {
                date += strs[i].substring(date_s, date_e);
            }

            list.add(new Content(title,url,descr,date,""));
            title="";
            url="";
            descr="";
            date="";
            u_s=0;
            d_s=0;
            date_s=0;
        }
        return list;
    }
    @Override
    public void p(String str){
        String string3 = "<[^>]+>";
        String url_patter = "";
        driver.get(str);
//        Pattern url = Pattern.compile("(&lt;link&gt;|<link>|<link />|&lt;link /&gt;){1}(<!\[CDATA\[){0,1}[^]<>;\"]*");
//        Matcher matcher = url.matcher(driver.getPageSource());
//        Pattern url2 = Pattern.compile("(http){1}.*");
        Pattern p = Pattern.compile(string3);
        String content = driver.getPageSource();

        Pattern desc = Pattern.compile("(<description>|<description />){1}.*(</description>){1}");
//        content = trimPage(content);
        System.out.println(content);
        Matcher matcher = desc.matcher(content);
//        Pattern url2 = Pattern.compile("(http){1}.*");
        Matcher matcher2,matcher3;
        int i = 0;
        int pos;
        String string="";
        String string2="";
        List<String> list = new ArrayList<String>();
        while(matcher.find()){
            string = matcher.group();
            System.out.println(string+"\n");
            matcher3 = p.matcher(string);
//            System.out.println(string);
//            System.out.println(matcher3.replaceAll("")+"+++++=+++++++");
/*            if((pos = string.indexOf("<![CDATA[")) != -1){
                string = string.substring(pos+9);
            }*/
//            if(string != "")
//            string = string.substring(13);
//            System.out.println(string);
        }
/*            while(matcher.find()){
                string2 = matcher.group();
//                System.out.println(string2);
                matcher2 = url2.matcher(string2);
                while (matcher2.find()) {
                    string = matcher2.group();
                    if ((pos = string.indexOf("&lt")) != -1) {
                        string = string.substring(0, pos);
                    }
                    list.add(string);
                    i++;
                }
        }
        System.out.println(list+" "+i);*/
    }

    @Override
    public String[] trimPage(String page) {
        Pattern pattern = null;
        String rss_source = "<rss[^>]*>.*</rss[^>]*>";
    for(int i = 0;i < new_charcter.length;++i) {
        pattern = Pattern.compile(origin_charcter[i]);
        Matcher matcher = pattern.matcher(page);
        page = matcher.replaceAll(new_charcter[i]);
    }
        page = page.split("<rss")[1];
        page = page.split("</rss>")[0];
//        System.out.println(page);
        String[] strings = page.split("<title>");
        return strings;
    }

    @Override
    public List<Content> reslovHtml(String url) {
        driver.get(url);
        String[] Items = trimPage(driver.getPageSource());
        String[] res = new String[charcter.length];
//        System.out.println(Items[2]);
        Pattern pattern = null;
        Matcher matcher = null;
        String res1 = "";
        List<Content> list = new ArrayList<Content>();
        for(int i = 1;i < Items.length;++i) {
            for(int j = 0;j < charcter.length;++j) {
                pattern = Pattern.compile(charcter[j]);
                matcher = pattern.matcher(Items[i]);
                if(matcher.find()) {
                    res1 = matcher.group();
                    pattern = Pattern.compile(str);
                    matcher = pattern.matcher(res1);
                    if(matcher.find())
                    res[j] = matcher.replaceAll("");
                    else res[j] = "";
                }
                else{
                    res[j] = "";
                }
            }
            list.add(new Content(res[0],res[1],res[2],res[3],""));
        }
        return list;
    }
}

