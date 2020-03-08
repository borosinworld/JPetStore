package persistence.impl;

import org.csu.mypetstore.domain.Sequence;
import org.csu.mypetstore.persistence.DBUtil;
import org.csu.mypetstore.persistence.SequenceDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SequenceDAOImpl implements SequenceDAO {
    private static String getSequence ="SELECT name, nextid FROM SEQUENCE WHERE NAME = ?";
    private static String updateSequence=" UPDATE SEQUENCE SET NEXTID = ? WHERE NAME = ?";
    @Override
    public Sequence getSequence(Sequence sequence) {
        try{
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(getSequence );
            preparedStatement.setString(1,sequence.getName());
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                sequence.setName(resultSet.getString(1));
                sequence.setNextId(resultSet.getInt(2));
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();;
        }
        return sequence;

    }

    @Override
    public void updateSequence(Sequence sequence) {
     try{
         Connection connection = DBUtil.getConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(updateSequence);
         preparedStatement.setString(2,sequence.getName());
         ResultSet resultSet = preparedStatement.executeQuery();
         if(resultSet.next()){
         preparedStatement.setInt(1,sequence.getNextId());
         }
         DBUtil.closeResultSet(resultSet);
         DBUtil.closePreparedStatement(preparedStatement);
         DBUtil.closeConnection(connection);
     }catch(Exception e){
         e.printStackTrace();
     }
    }
}
