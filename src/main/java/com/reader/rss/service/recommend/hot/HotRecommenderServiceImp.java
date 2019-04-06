package com.reader.rss.service.recommend.hot;

import com.reader.rss.pojo.Item;
import com.reader.rss.mapper.ItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
/**
hit:5天内的点击量
 */

@Service
public class HotRecommenderServiceImp implements HotRecommenderService {
    private int recommendNum;

    @Autowired(required = false)
    ItemMapper itemMapper;

    public List<Item> hottestNews;

    public HotRecommenderServiceImp() {
        this.recommendNum=5;
    }

    @Override
    public List<Item> recommend(){
        System.out.println("hhhh3");

        List<Item> items=itemMapper.selectByHit();
        System.out.println("hhhh4");
        System.out.println(items.size());
        for(int i = 0; i < recommendNum; i++){
            hottestNews.add(items.get(i));
        }
        return hottestNews;
    }

    public static void main(String[] args){
        HotRecommenderServiceImp hot=new HotRecommenderServiceImp();
        System.out.println("hhhh1");
        List<Item> l=hot.recommend();
        System.out.println("hhhh2");
        for(Item ll:l)
            System.out.println(ll);

    }

}
