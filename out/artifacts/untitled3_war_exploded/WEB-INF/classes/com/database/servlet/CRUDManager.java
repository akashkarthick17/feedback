package com.database.servlet;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Akash on 03-09-2017.
 */
public class CRUDManager {

    private static DataSource dataSource;

    public static void create(int year){

        Connection connection =null;
        Statement statement = null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet = null;


       dataSource = DBConnection.ConnectDatabase();

        try {


            connection =dataSource.getConnection();
            String sql = "CREATE  TABLE YEAR_"+year+"(id int, sname VARCHAR(25))";
            statement = connection.createStatement();
            statement.executeUpdate(sql);

            //Insert year for odd sem
            sql = "INSERT  INTO TABLE_MAP VALUES (?,?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, year);
            preparedStatement.setString(2,"YEAR_"+year);

            preparedStatement.executeUpdate();




        } catch (SQLException e) {


            e.printStackTrace();

        }  finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }


    public static List<Year> fetch(){

        Connection connection =null;
        Statement statement = null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet = null;

        List<Year> listYear = new ArrayList<>();


        dataSource = DBConnection.ConnectDatabase();

        try {


            connection = dataSource.getConnection();
            String sql = "SELECT YEAR FROM TABLE_MAP";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()){

                Year y = new Year();
                 y.setYear(resultSet.getInt("year"));
                 listYear.add(y);

            }




        } catch (SQLException e) {


            e.printStackTrace();

        }  finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


        return listYear;
    }

    public static void delete(int year){

        Connection connection =null;
        Statement statement = null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet = null;


        dataSource = DBConnection.ConnectDatabase();

        try {


            connection = dataSource.getConnection();
            String sql = "DROP TABLE year_"+year;
            statement = connection.createStatement();
            statement.executeUpdate(sql);

            //Insert year for odd sem
            sql = "DELETE  FROM TABLE_MAP WHERE YEAR="+year;
            statement = connection.createStatement();
            statement.executeUpdate(sql);






        } catch (SQLException e) {


            e.printStackTrace();

        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }


}
