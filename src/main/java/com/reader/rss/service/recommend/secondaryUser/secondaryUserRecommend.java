package com.reader.rss.service.recommend.secondaryUser;

import com.reader.rss.service.recommend.userCF.MyDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;


public class secondaryUserRecommend {

    public long[] secondaryUserRecommender(int userID, int size) {
        long[] recommendUsers2={};
        int usersNum;
        try {
            DataModel model = MyDataModel.myDataModel();
            UserSimilarity similarity = new PearsonCorrelationSimilarity(model);//用PearsonCorrelation算法
            UserNeighborhood neighborhood = new NearestNUserNeighborhood(3, similarity, model);
            long[] recommendUsers=neighborhood.getUserNeighborhood(2);
            if(recommendUsers.length>size+1)
                usersNum=size;
            else usersNum=recommendUsers.length-1;
            recommendUsers2=new long[usersNum];
            for(int i=0;i<usersNum;i++){
                recommendUsers2[i]=recommendUsers[i+1];
            }

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return recommendUsers2;
    }

    public static void main(String args[]){
        secondaryUserRecommend sec=new secondaryUserRecommend();
        long[] users=sec.secondaryUserRecommender(1,3);
        for (long user : users) {
            System.out.println(user);
        }
    }
}