package com.reader.rss.service.recommend.userCF;


import java.util.List;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;


public class MyItemBasedRecommender {

    public List<RecommendedItem> myItemBasedRecommender(int userID,int size){
        List<RecommendedItem> recommendations = null;
        try {
            DataModel model = MyDataModel.myDataModel();//构造数据模型
            ItemSimilarity similarity = new PearsonCorrelationSimilarity(model);//计算内容相似度
            Recommender recommender = new GenericItemBasedRecommender(model, similarity);//构造推荐引擎
            recommendations = recommender.recommend(userID, size);//得到推荐结果
            for (RecommendedItem recommendation : recommendations) {
                System.out.println(recommendation);
            }

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return recommendations;
    }

    public static void main(String args[]) throws Exception {
        MyItemBasedRecommender rec=new MyItemBasedRecommender();
        List<RecommendedItem> res=rec.myItemBasedRecommender(1,4);



    }

}
