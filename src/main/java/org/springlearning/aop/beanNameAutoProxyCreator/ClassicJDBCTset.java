package org.springlearning.aop.beanNameAutoProxyCreator;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class ClassicJDBCTset {

    public static void main(String[] args) {

        Connection conn = null;
        try {
        	
            conn = DriverManager.getConnection("jdbc:mysql://localhost/fang?useUnicode=true&characterEncoding=utf8&autoReconnect=true&connectTimeout=9000&socketTimeout=5000",
            			"root","root123456");
            //不能使用&amp;连接各个参数
/*            conn = DriverManager.getConnection("jdbc:mysql://localhost/fang?useUnicode=true&amp;characterEncoding=utf8&amp;autoReconnect=true&amp;connectTimeout=41000&amp;socketTimeout=5000",
            		"root","root123456");*/
            System.out.println(conn.getClass().getName());
            System.out.println(((com.mysql.jdbc.JDBC4Connection)conn).getURL());
            System.out.println(conn.getClientInfo());
            conn.setAutoCommit(false);
	        Statement statement = conn.createStatement();
	        statement.execute("insert into fang.qinglong(id,name) values(51,'hhh222erw');");
	        statement.execute("insert into xiang.baihu(id,date) values(36,'2015-11-20 10:35:26');");
	        conn.commit();
            System.out.println("执行完毕！");	
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                    conn = null;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
 
    }
    
}
