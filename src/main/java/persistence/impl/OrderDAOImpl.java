package persistence.impl;

import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.domain.Cart;
import org.csu.mypetstore.domain.Order;
import org.csu.mypetstore.persistence.DBUtil;
import org.csu.mypetstore.persistence.OrderDAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.List;

public class OrderDAOImpl implements OrderDAO {
    private static String getOrder ="select\n" +
            "    BILLADDR1 AS billAddress1,\n" +
            "    BILLADDR2 AS billAddress2,\n" +
            "    BILLCITY,\n" +
            "    BILLCOUNTRY,\n" +
            "    BILLSTATE,\n" +
            "    BILLTOFIRSTNAME,\n" +
            "    BILLTOLASTNAME,\n" +
            "    BILLZIP,\n" +
            "    SHIPADDR1 AS shipAddress1,\n" +
            "    SHIPADDR2 AS shipAddress2,\n" +
            "    SHIPCITY,\n" +
            "    SHIPCOUNTRY,\n" +
            "    SHIPSTATE,\n" +
            "    SHIPTOFIRSTNAME,\n" +
            "    SHIPTOLASTNAME,\n" +
            "    SHIPZIP,\n" +
            "    CARDTYPE,\n" +
            "    COURIER,\n" +
            "    CREDITCARD,\n" +
            "    EXPRDATE AS expiryDate,\n" +
            "    LOCALE,\n" +
            "    ORDERDATE,\n" +
            "    ORDERS.ORDERID,\n" +
            "    TOTALPRICE,\n" +
            "    USERID AS username,\n" +
            "    STATUS\n" +
            "    FROM ORDERS, ORDERSTATUS\n" +
            "    WHERE ORDERS.ORDERID = ?\n" +
            "    AND ORDERS.ORDERID = ORDERSTATUS.ORDERID";
    private static String getOrdersByUsername = "SELECT\n" +
            "    BILLADDR1 AS billAddress1,\n" +
            "    BILLADDR2 AS billAddress2,\n" +
            "    BILLCITY,\n" +
            "    BILLCOUNTRY,\n" +
            "    BILLSTATE,\n" +
            "    BILLTOFIRSTNAME,\n" +
            "    BILLTOLASTNAME,\n" +
            "    BILLZIP,\n" +
            "    SHIPADDR1 AS shipAddress1,\n" +
            "    SHIPADDR2 AS shipAddress2,\n" +
            "    SHIPCITY,\n" +
            "    SHIPCOUNTRY,\n" +
            "    SHIPSTATE,\n" +
            "    SHIPTOFIRSTNAME,\n" +
            "    SHIPTOLASTNAME,\n" +
            "    SHIPZIP,\n" +
            "    CARDTYPE,\n" +
            "    COURIER,\n" +
            "    CREDITCARD,\n" +
            "    EXPRDATE AS expiryDate,\n" +
            "    LOCALE,\n" +
            "    ORDERDATE,\n" +
            "    ORDERS.ORDERID,\n" +
            "    TOTALPRICE,\n" +
            "    USERID AS username,\n" +
            "    STATUS\n" +
            "    FROM ORDERS, ORDERSTATUS\n" +
            "    WHERE ORDERS.USERID = ?\n" +
            "    AND ORDERS.ORDERID = ORDERSTATUS.ORDERID\n" +
            "    ORDER BY ORDERDATE";
    private static String insertOrder ="INSERT INTO ORDERS (ORDERID, USERID, ORDERDATE, SHIPADDR1, SHIPADDR2, SHIPCITY, SHIPSTATE,\n" +
            "                        SHIPZIP, SHIPCOUNTRY, BILLADDR1, BILLADDR2, BILLCITY, BILLSTATE, BILLZIP, BILLCOUNTRY,\n" +
            "                        COURIER, TOTALPRICE, BILLTOFIRSTNAME, BILLTOLASTNAME, SHIPTOFIRSTNAME, SHIPTOLASTNAME,\n" +
            "                        CREDITCARD, EXPRDATE, CARDTYPE, LOCALE)\n" +
            "    VALUES(?, ?,?,?,?  ,?,?,?,?,? ,?,?,?,?,?  ,?,?,?,?,? ,?,?,?,?,?)";

    private static String insertOrderStatus =" INSERT INTO ORDERSTATUS (ORDERID, LINENUM, TIMESTAMP, STATUS)\n" +
            "    VALUES ?,?,?,?)";

    @Override
    public List<Order> getOrdersByUsername(String username) {
        List<Order> orderList = null;
        try{
            Connection connection = DBUtil.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(getOrdersByUsername);
            preparedStatement.setString(1,username);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Order order = new Order();
                order.setBillAddress1(resultSet.getString(1));
                order.setBillAddress2(resultSet.getString(2));
                order.setBillCity(resultSet.getString(3));
                order.setBillCountry(resultSet.getString(4));
                order.setBillState(resultSet.getString(5));

                order.setBillToFirstName(resultSet.getString(6));
                order.setBillToLastName(resultSet.getString(7));
                order.setBillZip(resultSet.getString(8));
                order.setShipAddress1(resultSet.getString(9));
                order.setShipAddress2(resultSet.getString(10));

                order.setShipCity(resultSet.getString(11));
                order.setShipCountry(resultSet.getString(12));
                order.setShipState(resultSet.getString(13));
                order.setShipToFirstName(resultSet.getString(14));
                order.setShipToLastName(resultSet.getString(15));

                order.setShipZip(resultSet.getString(16));
                order.setCardType(resultSet.getString(17));
                order.setCourier(resultSet.getString(18));
                order.setCreditCard(resultSet.getString(19));
                order.setExpiryDate(resultSet.getString(20));

                order.setLocale(resultSet.getString(21));
                order.setOrderDate(resultSet.getDate(22));
                order.setOrderId(resultSet.getInt(23));
                order.setTotalPrice(resultSet.getBigDecimal(24));
                order.setUsername(resultSet.getString(25));
                order.setStatus(resultSet.getString(26));

                orderList.add(order);

            }

        }catch(Exception e){
            e.printStackTrace();
        }
        return orderList;
    }

    @Override
    public Order getOrder(int orderId) {
        Order order = null;
        try{
            Connection connection = DBUtil.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(getOrder);
            preparedStatement.setInt(1,orderId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                order = new Order();
                order.setBillAddress1(resultSet.getString(1));
                order.setBillAddress2(resultSet.getString(2));
                order.setBillCity(resultSet.getString(3));
                order.setBillCountry(resultSet.getString(4));
                order.setBillState(resultSet.getString(5));

                order.setBillToFirstName(resultSet.getString(6));
                order.setBillToLastName(resultSet.getString(7));
                order.setBillZip(resultSet.getString(8));
                order.setShipAddress1(resultSet.getString(9));
                order.setShipAddress2(resultSet.getString(10));

                order.setShipCity(resultSet.getString(11));
                order.setShipCountry(resultSet.getString(12));
                order.setShipState(resultSet.getString(13));
                order.setShipToFirstName(resultSet.getString(14));
                order.setShipToLastName(resultSet.getString(15));

                order.setShipZip(resultSet.getString(16));
                order.setCardType(resultSet.getString(17));
                order.setCourier(resultSet.getString(18));
                order.setCreditCard(resultSet.getString(19));
                order.setExpiryDate(resultSet.getString(20));
                order.setLocale(resultSet.getString(21));

                order.setOrderDate(resultSet.getDate(22));
                order.setOrderId(resultSet.getInt(23));
                order.setTotalPrice(resultSet.getBigDecimal(24));
                order.setTotalPrice(resultSet.getBigDecimal(25));
                order.setStatus(resultSet.getString(26));


                DBUtil.closeResultSet(resultSet);
                DBUtil.closePreparedStatement(preparedStatement);
                DBUtil.closeConnection(connection);

            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return order;
    }

    @Override
    public void insertOrder(Order order) {
        try{
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(insertOrder);
            preparedStatement.setInt(1,order.getOrderId());
            preparedStatement.setString(2,order.getUsername());
            preparedStatement.setDate(3,null);
            preparedStatement.setString(4,order.getShipAddress1());
            preparedStatement.setString(5,order.getShipAddress2());

            preparedStatement.setString(6,order.getShipCity());
            preparedStatement.setString(7,order.getShipState());
            preparedStatement.setString(8,order.getShipZip());
            preparedStatement.setString(9,order.getShipCountry());
            preparedStatement.setString(10,order.getBillAddress1());

            preparedStatement.setString(11,order.getBillAddress2());
            preparedStatement.setString(12,order.getBillCity());
            preparedStatement.setString(13,order.getBillState());
            preparedStatement.setString(14,order.getBillZip());
            preparedStatement.setString(15,order.getBillCountry());

            preparedStatement.setString(16,order.getCourier());
            preparedStatement.setBigDecimal(17,order.getTotalPrice());
            preparedStatement.setString(18,order.getBillToFirstName());
            preparedStatement.setString(19,order.getBillToLastName());
            preparedStatement.setString(20,order.getShipToFirstName());
            System.out.println(order.getCreditCard()+"order.getCreditCard()");
            preparedStatement.setString(21,order.getBillToLastName());
            preparedStatement.setString(22,order.getCreditCard());
            preparedStatement.setString(23,order.getExpiryDate());
            preparedStatement.setString(24,order.getCardType());
            preparedStatement.setString(25,order.getLocale());
            preparedStatement.executeUpdate();
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @Override
    public void insertOrderStatus(Order order) {
        try{
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(insertOrderStatus);
            preparedStatement.setInt(1,order.getOrderId());
            preparedStatement.setInt(2, order.getOrderId());
            preparedStatement.setDate(3,(Date)order.getOrderDate());
            preparedStatement.setString(4,order.getStatus());
            preparedStatement.executeUpdate();
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public Order initOrder(Account account, Cart cart) {
        Order order=null;
        try{
            order = new Order();
            order.setBillAddress1(account.getAddress1());
            order.setBillAddress2(account.getAddress2());
            order.setBillCity(account.getCity());
            order.setBillCountry(account.getCountry());
            order.setBillState(account.getState());

            order.setBillToFirstName(account.getFirstName());
            order.setBillToLastName(account.getLastName());
            order.setBillZip(account.getZip());
            order.setShipAddress1(account.getAddress1());
            order.setShipAddress2(account.getAddress2());

            order.setShipCity(account.getCity());
            order.setShipCountry(account.getCountry());
            order.setShipState(account.getState());
            order.setShipToFirstName(account.getFirstName());
            order.setShipToLastName(account.getLastName());

            order.setShipZip(account.getZip());

            order.setCardType("default");
            order.setCourier("default");



            long l = System.currentTimeMillis();

            Date date = new Date(l);
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
            order.setExpiryDate(dateFormat.format(date));

            order.setLocale("locale");

            order.setOrderDate(date);


            order.setCreditCard("USR");
            order.setOrderId(1000);

            order.setTotalPrice(cart.getSubTotal());
            order.setStatus(account.getStatus());



        }catch (Exception e){
            e.printStackTrace();
        }
        return order;
    }
}
/*INSERT INTO ORDERSTATUS (ORDERID, LINENUM, TIMESTAMP, STATUS)\n" +
            "    VALUES (#{orderId,jdbcType=NUMERIC}, #{orderId,jdbcType=NUMERIC}, #{orderDate,jdbcType=TIMESTAMP}, #{status,jdbcType=VARCHAR*/
