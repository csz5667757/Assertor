package com.csz.assertor.jdbc;

import java.sql.*;

public class DBConnection {

    private static String driver = "com.mysql.jdbc.Driver";
    private static String url = "jdbc:mysql://localhost:3306/ssm?useUnicode=true&useSSL=true&characterEncoding=utf8&serverTimezone=UTC";
    private static String user = "root";
    private static String pwd = "123456";

    public static void main(String[] args) {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            System.out.println("加载驱动失败！");
            e.printStackTrace();
        }

        try {
            Connection connection = DriverManager.getConnection(url, user, pwd);
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from student_grade");
            int i =1;
            while (rs.next()) {
                String id = rs.getString(1);
                String name = rs.getString(2);
                String subject = rs.getString(3);
                String grade = rs.getString(4);
                System.out.print(id+" ");
                System.out.print(name+" ");
                System.out.print(subject+" ");
                System.out.print(grade+" ");
                System.out.println();
            }
            if (rs !=null){
                try{
                    rs.close();
                    stmt.close();
                    connection.close();
                }catch (SQLException ex){
                    ex.printStackTrace();
                }
            }
        } catch (SQLException ex) {
            System.out.println("查询失败....");
            ex.printStackTrace();
        }

    }
}
