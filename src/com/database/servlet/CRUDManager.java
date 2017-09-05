package com.database.servlet;

import com.list.servlet.CreateYear;
import com.list.servlet.Staff;
import com.list.servlet.Year;

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
            String sql = "CREATE  TABLE YEAR_"+year+"(degree VARCHAR(25), branch VARCHAR(25),semester int,s_section VARCHAR(25),subject_code VARCHAR(25), staff_name VARCHAR(225),subject_name VARCHAR(225), s_year VARCHAR(25), sem_type VARCHAR(25))";
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


public static void insert(List<CreateYear> createYear, String  yr){

        int year = Integer.parseInt(yr);

    Connection connection =null;
    Statement statement = null;
    PreparedStatement preparedStatement=null;
    ResultSet resultSet = null;


    dataSource = DBConnection.ConnectDatabase();

    try {


        connection = dataSource.getConnection();


        for(CreateYear list: createYear){

            String sql = "INSERT INTO YEAR_"+year+" VALUES (?,?,?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement1 = connection.prepareStatement(sql);

            preparedStatement1.setString(1,list.getDegree());
            preparedStatement1.setString(2,list.getBranch());
            preparedStatement1.setDouble(3,list.getSemester());
            preparedStatement1.setString(4,list.getSection());
            preparedStatement1.setString(5,list.getSubjectCode());
            preparedStatement1.setString(6,list.getStaffName());
            preparedStatement1.setString(7,list.getSubjectName());
            preparedStatement1.setString(8,list.getYear());
            preparedStatement1.setString(9,list.getSemtype());

            preparedStatement1.executeUpdate();

        }





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

public static void publish(String pYear, String pSem)
{

    Connection connection =null;
    Statement statement = null;
    PreparedStatement preparedStatement=null;
    ResultSet resultSet = null;


    dataSource = DBConnection.ConnectDatabase();

    try {


        connection =dataSource.getConnection();
        String sql = "CREATE  TABLE IF NOT EXISTS fr_"+pYear+"_"+pSem+"(subject_code VARCHAR(25), question_no int, sa int,a int, n int,d int, sd int,total int , overall FLOAT)";
        statement = connection.createStatement();
        statement.executeUpdate(sql);

         sql = "CREATE  TABLE IF NOT EXISTS sr_"+pYear+"_"+pSem+"(subject_code VARCHAR(25), question_no int, sa int,a int, n int,d int, sd int,total int , overall FLOAT)";
        statement = connection.createStatement();
        statement.executeUpdate(sql);


        //truncate existing active link
        sql = "TRUNCATE TABLE active_link";
        statement = connection.createStatement();
        statement.executeUpdate(sql);



        //create new active link
        sql = "INSERT  INTO active_link(active_year, active_sem) VALUES (?,?)";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, pYear);
        preparedStatement.setString(2,pSem);

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

public static boolean checkActiveLink(){



    Connection connection =null;
    Statement statement = null;
    PreparedStatement preparedStatement=null;
    ResultSet resultSet = null;


    dataSource = DBConnection.ConnectDatabase();

    try {


        connection =dataSource.getConnection();
        String sql = "SELECT * FROM active_link";
        statement = connection.createStatement();
        resultSet = statement.executeQuery(sql);


        int count = 0;

        while (resultSet.next()){
            ++count;
        }
        if(count!=0){
            return true;

        }
        else{
            return false;
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


    return false;
}

public static String[] getActiveYear(){

    Connection connection =null;
    Statement statement = null;
    PreparedStatement preparedStatement=null;
    ResultSet resultSet = null;


    String[] array = new String[2];
    dataSource = DBConnection.ConnectDatabase();

    try {


        connection =dataSource.getConnection();
        String sql = "SELECT * FROM active_link";
        statement = connection.createStatement();
        resultSet = statement.executeQuery(sql);


        while(resultSet.next()){

            array[0] = resultSet.getString("active_year");
            array[1] = resultSet.getString("active_sem");
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




    return array;

}

public static List<Staff> getStaffDetails(String dept, String sem, String sec){


    Connection connection =null;
    Statement statement = null;
    PreparedStatement preparedStatement=null;
    ResultSet resultSet = null;


    List<Staff> staff = new ArrayList<>();

    String activeYear="";
    dataSource = DBConnection.ConnectDatabase();

    try {


        connection =dataSource.getConnection();
        String sql = "SELECT * FROM active_link";
        statement = connection.createStatement();
        resultSet = statement.executeQuery(sql);


        while(resultSet.next()){

           activeYear = resultSet.getString("active_year");

        }

        sql = "SELECT * FROM year_"+activeYear+" WHERE branch='"+dept+"' AND semester = '"+sem+"' AND s_section = '"+sec+"' ";
        statement = connection.createStatement();
        resultSet = statement.executeQuery(sql);

        while(resultSet.next()){

           String staffname = resultSet.getString("staff_name");
           String subcode = resultSet.getString("subject_code");
           String subname = resultSet.getString("subject_name");

           Staff s = new Staff(staffname,subname,subcode);
           staff.add(s);

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




    return staff;


}


}
