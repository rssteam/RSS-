package com.reader.rss.service.recommend.hot;

import com.reader.rss.pojo.Item;

import java.util.List;

public interface HotRecommenderService {

    List<Item> recommend();

}
