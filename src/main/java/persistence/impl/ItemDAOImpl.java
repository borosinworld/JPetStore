package persistence.impl;

import org.csu.mypetstore.domain.Item;
import org.csu.mypetstore.domain.Product;
import org.csu.mypetstore.persistence.DBUtil;
import org.csu.mypetstore.persistence.ItemDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ItemDAOImpl implements ItemDAO {
    private static final String getItemListByProductString ="   SELECT I.ITEMID,LISTPRICE,UNITCOST,SUPPLIER AS supplierId,I.PRODUCTID AS \"product.productId\",NAME AS \"product.name\",DESCN AS \"product.description\",CATEGORY AS \"product.categoryId\",STATUS,ATTR1 AS attribute1,ATTR2 AS attribute2,ATTR3 AS attribute3,ATTR4 AS attribute4,ATTR5 AS attribute5 FROM ITEM I, PRODUCT P WHERE P.PRODUCTID = I.PRODUCTID AND I.PRODUCTID = ?";
    private static final String getItemString = "select I.ITEMID,LISTPRICE,UNITCOST,SUPPLIER AS supplierId,I.PRODUCTID AS \"product.productId\",NAME AS \"product.name\",DESCN AS \"product.description\",CATEGORY AS \"product.categoryId\",STATUS,ATTR1 AS attribute1,ATTR2 AS attribute2,ATTR3 AS attribute3,ATTR4 AS attribute4,ATTR5 AS attribute5,QTY AS quantity from ITEM I, INVENTORY V, PRODUCT P where P.PRODUCTID = I.PRODUCTID and I.ITEMID = V.ITEMID and I.ITEMID = ?" ;
    private static final String getInventoryQuantityString = " SELECT QTY AS value FROM INVENTORY WHERE ITEMID = ?";
    private static final String updateInventoryQuantityString = "UPDATE INVENTORY SET QTY = QTY - ? WHERE ITEMID = ?";
    @Override
    public void updateInventoryQuantity(Map<String, Object> param) {
        try{
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(updateInventoryQuantityString );
            String itemId = param.keySet().iterator().next();
            Integer increment = (Integer) param.get(itemId);
            preparedStatement.setInt(1, increment.intValue());
            preparedStatement.setString(2, itemId);
            preparedStatement.executeUpdate();
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);}
        catch (Exception e){
        e.printStackTrace();
    }
    }

    @Override
    public int getInventoryQuantity(String itemId) {
        int result = -1;
        try {
            Connection connection = DBUtil.getConnection();//得到练级啊
            PreparedStatement preparedstatement = connection.prepareStatement(getInventoryQuantityString);//得到相应的从数据库中得到的数据
            preparedstatement.setString(1, itemId);//设置一些参数的值
            //
            ResultSet resultset = preparedstatement.executeQuery();
            if (resultset.next()) {
                result = resultset.getInt(1);
            }
            DBUtil.closeResultSet(resultset);
            DBUtil.closePreparedStatement(preparedstatement);
            DBUtil.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();

        }
        return result;
    }
    //////不是你这个地方老是报错等我实现功能在解决你
    @Override
    public List<Item> getItemListByProduct(String productId) {
        List<Item> itemList = new ArrayList<>();//需要返回的对象一定需要实现在try-catch快之前进行声明
        try{
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(getItemListByProductString);
            preparedStatement.setString(1, productId);


            ResultSet resultset = preparedStatement.executeQuery();
            while (resultset.next()) {
                //在这个Item表中包含着每个item的一些属性，以及product的一些属性
                //从两个表中获取了数据Item表以及Product
                Item item = new Item();
                item. setItemId(resultset.getString(1));
                item. setListPrice(resultset.getBigDecimal(2));
                item. setUnitCost(resultset.getBigDecimal(3));
                item. setSupplierId(resultset. getInt(4));
                Product product = new Product();
                product.setProductId(resultset.getString(5));
                product. setName(resultset.getString(6));
                product.setDescription(resultset.getString(7));
                product.setCategoryId(resultset.getString(8));
                item.setProduct(product);
                item.setStatus(resultset.getString(9));
                item.setAttribute1(resultset.getString(10));
                item.setAttribute2(resultset.getString(11));
                item.setAttribute3(resultset.getString(12));
                item.setAttribute4(resultset.getString(13));
                item.setAttribute5(resultset.getString(14));
                itemList. add(item);
            }
            DBUtil.closeResultSet(resultset);
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
           }catch (Exception e) {
                e.printStackTrace();
            }
            return itemList;
    }

    @Override
    public Item getItem(String itemId) {
        Item item = null;
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedstatement = connection.prepareStatement(getItemString);
            preparedstatement.setString(1, itemId);
            ResultSet resultset = preparedstatement.executeQuery();
            if (resultset.next()) {
                item = new Item();

                item.setItemId(resultset.getString(1));
                item.setListPrice(resultset.getBigDecimal(2));
                item.setUnitCost(resultset.getBigDecimal(3));
                item.setSupplierId(resultset.getInt(4));
                Product product = new Product();
                product.setProductId(resultset.getString(5));
                product.setName(resultset.getString(6));
                product.setDescription(resultset.getString(7));
                product.setCategoryId(resultset.getString(8));
                item.setProduct(product);
                item.setStatus(resultset.getString(9));
                item.setAttribute1(resultset.getString(10));
                item.setAttribute2(resultset.getString(11));
                item.setAttribute3(resultset.getString(12));
                item.setAttribute4(resultset.getString(13));
                item.setAttribute5(resultset.getString(14));

                // itemList.add(item);

                DBUtil.closeResultSet(resultset);
                DBUtil.closePreparedStatement(preparedstatement);
                DBUtil.closeConnection(connection);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return item ;
    }

}
