package com.yasso.dfbb.oracle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author guochaung
 * @version 1.0
 * @date 2020/12/10 14:21
 */
public class ConnectOracle {

    static String IP = "47.114.176.254";
    static String PORT = "3111";
    static String DATABASE = "helowin";
    static String USERNAME = "scott";
    static String USERPWD = "guochuang";
    static String DRIVER = "oracle.jdbc.driver.OracleDriver";
    static String SQL = "UPDATE DEMO SET TIME = to_date('2020-09-08','yyyy-MM-dd HH24:mi:ss') WHERE CODE = 1";
    public static void executeSQL() {
        String URL = "jdbc:oracle:thin:@" + IP + ":" + PORT + "/" + DATABASE;

        try {
            Class.forName(DRIVER);
            Connection connection = DriverManager.getConnection(URL, USERNAME, USERPWD);
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            int i = preparedStatement.executeUpdate();
            System.out.println(i);
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

//    public static void main(String[] args) {
//        executeSQL();
//    }






}
