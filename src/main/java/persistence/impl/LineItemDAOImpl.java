package persistence.impl;

import org.csu.mypetstore.domain.LineItem;
import org.csu.mypetstore.persistence.DBUtil;
import org.csu.mypetstore.persistence.LineItemDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class LineItemDAOImpl implements LineItemDAO {
    private static final String  getLineItemsByOrderId ="SELECT\n"+
            "      ORDERID,\n"+
            "      LINENUM AS lineNumber,\n"+
            "      ITEMID,\n"+
            "      QUANTITY,\n"+
            "      UNITPRICE\n"+
            "    FROM LINEITEM\n"+
            "    WHERE ORDERID = ?";
    private static  final String insertLineItem= " INSERT INTO LINEITEM (ORDERID, LINENUM, ITEMID, QUANTITY, UNITPRICE)\n" +
            "    VALUES (?, ?, ?, ?, ?)";

    @Override
    public List<LineItem> getLineItemsByOrderId(int orderId) { //orederID
        List<LineItem> itemList = new ArrayList<>();
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(getLineItemsByOrderId);
            preparedStatement.setInt(1,orderId);

            ResultSet resultset = preparedStatement.executeQuery();
            while (resultset.next()) {
                //在这个Item表中包含着每个item的一些属性，以及product的一些属性
                //从两个表中获取了数据Item表以及Product
                LineItem lineItem = new LineItem();
                lineItem.setOrderId(resultset.getInt(1));
                lineItem.setLineNumber(resultset.getInt(2));
                lineItem.setItemId(resultset.getString(3));
                lineItem.setQuantity(resultset.getInt(4));
                lineItem.setUnitPrice(resultset.getBigDecimal(5));

                itemList.add(lineItem);
                DBUtil.closeResultSet(resultset);
                DBUtil.closePreparedStatement(preparedStatement);
                DBUtil.closeConnection(connection);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return itemList;
    }

    @Override
    public void insertLineItem(LineItem lineItem) {
     try{Connection connection = DBUtil.getConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(insertLineItem);
         preparedStatement.setInt(1,lineItem.getOrderId());
         preparedStatement.setInt(2,lineItem.getLineNumber());
         preparedStatement.setString(3,lineItem.getItemId());
         preparedStatement.setInt(4,lineItem.getQuantity());
         preparedStatement.setBigDecimal(5,lineItem.getUnitPrice());

         DBUtil.closePreparedStatement(preparedStatement);
         DBUtil.closeConnection(connection);

     }catch(Exception e){
         e.printStackTrace();
     }
    }
}
