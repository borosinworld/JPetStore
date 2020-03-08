package persistence;

import java.sql.*;

public class DBUtil {
    private static String driverString = "com.mysql.jdbc.Driver";
    private static String connectionString = "jdbc:mysql://127.0.0.1/mypetstore?useSSL=true";
    private static String username = "root";
    private static String password = "0000";

    public static Connection getConnection()throws Exception{
        Connection  connection = null;
        try {
            Class.forName(driverString);
            connection = DriverManager.getConnection(connectionString, username, password);
        }catch(Exception e){
            e.printStackTrace();
        }
        return connection;
    }
    public static  void closeStatement(Statement statement) throws Exception{
        if(statement != null)  {
            statement.close();
        }
    }

    public static void closePreparedStatent(PreparedStatement pStatement) throws Exception{
        pStatement.close();
    }

    public static void closePreparedStatement(PreparedStatement preparedStatement)throws Exception{
        preparedStatement.close();
    }
    public static void closeResultSet(ResultSet resultset)throws Exception{
        resultset.close();
    }
    public static void closeConnection(Connection connnection) throws Exception{
        if(connnection != null){
            connnection.close();
        }

    }
   /*public static void main(String[] args) throws Exception{
       Connection con = DBUtil.getConnection();
       System.out.println(con);
   }*/
}
