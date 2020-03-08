package persistence.impl;

import org.csu.mypetstore.domain.Daily;
import org.csu.mypetstore.persistence.DBUtil;
import org.csu.mypetstore.persistence.DailyDAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;

public class DailyDAOImpl implements DailyDAO {
    private static String insertDaily="INSERT INTO DAILY(ACCOUNT, CATEGORY,PRODUCT, ITEM,CART,DATE,ORDER,SIGNOUTDATE) VALUES (?,?,?,?,?,?,?,?)";

    @Override
    public void insertDaily(Daily daily) {

        try {

            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(insertDaily);
            preparedStatement.setString(1,daily.getAccount());
            preparedStatement.setString(2,daily.getCategory());
            preparedStatement.setString(3,daily.getProduct());
            preparedStatement.setString(4,daily.getItem());
            preparedStatement.setString(5,daily.getCart());
            preparedStatement.setDate(6,(Date)daily.getDate());
            preparedStatement.setString(7,daily.getOrder());
            preparedStatement.setDate(8,(Date)daily.getSignoutdate());
            preparedStatement.executeUpdate();
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);


        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
