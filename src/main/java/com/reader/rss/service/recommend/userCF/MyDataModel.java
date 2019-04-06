package com.reader.rss.service.recommend.userCF;

import org.apache.mahout.cf.taste.impl.model.jdbc.MySQLJDBCDataModel;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.model.JDBCDataModel;
import com.mysql.cj.jdbc.MysqlDataSource;

public class MyDataModel {

    public static JDBCDataModel myDataModel() {
        MysqlDataSource dataSource = new MysqlDataSource();
        JDBCDataModel dataModel = null;
        try {

            dataSource.setServerName("localhost");
            dataSource.setUser("root");
            dataSource.setPassword("123456");
            dataSource.setDatabaseName("rssdb2");

            // use JNDI
            dataModel = new MySQLJDBCDataModel(dataSource, "userAction", "account_id", "site_id", "preference", null);

            DataModel model =dataModel;


        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return dataModel;
    }

}