package persistence.impl;

import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.persistence.AccountDAO;
import org.csu.mypetstore.persistence.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AccountDAOImpl implements AccountDAO {
    private static String getAccountByUsername = "SELECT\n" +
            "          SIGNON.USERNAME,\n" +
            "          ACCOUNT.EMAIL,\n" +
            "          ACCOUNT.FIRSTNAME,\n" +
            "          ACCOUNT.LASTNAME,\n" +
            "          ACCOUNT.STATUS,\n" +
            "          ACCOUNT.ADDR1 AS address1,\n" +
            "          ACCOUNT.ADDR2 AS address2,\n" +
            "          ACCOUNT.CITY,\n" +
            "          ACCOUNT.STATE,\n" +
            "          ACCOUNT.ZIP,\n" +
            "          ACCOUNT.COUNTRY,\n" +
            "          ACCOUNT.PHONE,\n" +
            "          PROFILE.LANGPREF AS languagePreference,\n" +
            "          PROFILE.FAVCATEGORY AS favouriteCategoryId,\n" +
            "          PROFILE.MYLISTOPT AS listOption,\n" +
            "          PROFILE.BANNEROPT AS bannerOption,\n" +
            "          BANNERDATA.BANNERNAME\n" +
            "    FROM ACCOUNT, PROFILE, SIGNON, BANNERDATA\n" +
            "    WHERE ACCOUNT.USERID = ?\n" +
            "      AND SIGNON.USERNAME = ACCOUNT.USERID\n" +
            "      AND PROFILE.USERID = ACCOUNT.USERID\n" +
            "      AND PROFILE.FAVCATEGORY = BANNERDATA.FAVCATEGORY";

    private static String getAccountByUsernameAndPassword = "    SELECT\n" +
            "      SIGNON.USERNAME,\n" +
            "      ACCOUNT.EMAIL,\n" +
            "      ACCOUNT.FIRSTNAME,\n" +
            "      ACCOUNT.LASTNAME,\n" +
            "      ACCOUNT.STATUS,\n" +
            "      ACCOUNT.ADDR1 AS address1,\n" +
            "      ACCOUNT.ADDR2 AS address2,\n" +
            "      ACCOUNT.CITY,\n" +
            "      ACCOUNT.STATE,\n" +
            "      ACCOUNT.ZIP,\n" +
            "      ACCOUNT.COUNTRY,\n" +
            "      ACCOUNT.PHONE,\n" +
            "      PROFILE.LANGPREF AS languagePreference,\n" +
            "      PROFILE.FAVCATEGORY AS favouriteCategoryId,\n" +
            "      PROFILE.MYLISTOPT AS listOption,\n" +
            "      PROFILE.BANNEROPT AS bannerOption,\n" +
            "      BANNERDATA.BANNERNAME\n" +
            "    FROM ACCOUNT, PROFILE, SIGNON, BANNERDATA\n" +
            "    WHERE ACCOUNT.USERID = ?\n" +
            "      AND SIGNON.PASSWORD = ?\n" +
            "      AND SIGNON.USERNAME = ACCOUNT.USERID\n" +
            "      AND PROFILE.USERID = ACCOUNT.USERID\n" +
            "      AND PROFILE.FAVCATEGORY = BANNERDATA.FAVCATEGORY";
    private static String updateAccount = "UPDATE ACCOUNT SET\n" +
            "      EMAIL = ?,\n" +
            "      FIRSTNAME = ?,\n" +
            "      LASTNAME = ?,\n" +
            "      STATUS = ?,\n" +
            "      ADDR1 = ?,\n" +
            "      ADDR2 = ?,\n" +
            "      CITY = ?,\n" +
            "      STATE = ?,\n" +
            "      ZIP = ?,\n" +
            "      COUNTRY = ?,\n" +
            "      PHONE = ?\n" +
            "    WHERE USERID = ?";
    private static String insertAccount = "INSERT INTO ACCOUNT (EMAIL, FIRSTNAME, LASTNAME, STATUS, ADDR1, ADDR2, CITY, STATE, ZIP, COUNTRY, PHONE, USERID) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";

    /////zhehfehfeofeahifds
    private static String insertProfile = "INSERT INTO PROFILE (LANGPREF, FAVCATEGORY, USERID) VALUES (?, ?, ?)";
    private static String updateProfile = " UPDATE PROFILE SET  LANGPREF = ?, FAVCATEGORY = ? WHERE USERID = ?";
    private static String updateSignon = "UPDATE SIGNON SET PASSWORD = ?\n" +
            "    WHERE USERNAME = ?";
    private static String insertSignon = "INSERT INTO SIGNON (PASSWORD,USERNAME) VALUES (?, ?)";


    @Override
    public boolean usernameIsExist(Account account) {
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(getAccountByUsername);
            preparedStatement.setString(1, account.getUsername());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Account getAccountByUsername(String username) {
        Account account = null;
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(getAccountByUsername);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                account = new Account();
                account.setUsername(resultSet.getString(1));
                account.setEmail(resultSet.getString(2));
                account.setFirstName(resultSet.getString(3));
                account.setLastName(resultSet.getString(4));
                account.setStatus(resultSet.getString(5));
                account.setAddress1(resultSet.getString(6));
                account.setAddress2(resultSet.getString(7));
                account.setCity(resultSet.getString(8));
                account.setState(resultSet.getString(9));
                account.setZip(resultSet.getString(10));
                account.setCountry(resultSet.getString(11));
                account.setPhone(resultSet.getString(12));
                account.setLanguagePreference(resultSet.getString(13));
                account.setFavouriteCategoryId(resultSet.getString(14));
                account.setListOption(resultSet.getBoolean(15));
                account.setBannerOption(resultSet.getBoolean(16));
                account.setBannerName(resultSet.getString(17));
                DBUtil.closeResultSet(resultSet);
                DBUtil.closePreparedStatement(preparedStatement);
                DBUtil.closeConnection(connection);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return account;
    }

    @Override
    public Account getAccountByUsernameAndPassword(Account account) {

        try {
            String username = account.getUsername();
            String password = account.getPassword();
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(getAccountByUsernameAndPassword);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();


            if (resultSet.next()) {
                account = new Account();
                account.setUsername(resultSet.getString(1));
                account.setEmail(resultSet.getString(2));
                account.setFirstName(resultSet.getString(3));
                account.setLastName(resultSet.getString(4));
                account.setStatus(resultSet.getString(5));
                account.setAddress1(resultSet.getString(6));
                account.setAddress2(resultSet.getString(7));
                account.setCity(resultSet.getString(8));
                account.setState(resultSet.getString(9));
                account.setZip(resultSet.getString(10));
                account.setCountry(resultSet.getString(11));
                account.setPhone(resultSet.getString(12));
                account.setLanguagePreference(resultSet.getString(13));
                account.setFavouriteCategoryId(resultSet.getString(14));
                account.setListOption(resultSet.getBoolean(15));
                account.setBannerOption(resultSet.getBoolean(16));
                account.setBannerName(resultSet.getString(17));
                DBUtil.closeResultSet(resultSet);
                DBUtil.closePreparedStatement(preparedStatement);
                DBUtil.closeConnection(connection);
            }


        } catch (Exception e) {

            e.printStackTrace();


        }
        return account;
    }

    @Override
    public void insertAccount(Account account) {//
        try {

            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(insertAccount);
            preparedStatement.setString(1, account.getEmail());
            preparedStatement.setString(2, account.getFirstName());
            preparedStatement.setString(3, account.getLastName());
            preparedStatement.setString(4, account.getStatus());
            preparedStatement.setString(5, account.getAddress1());
            preparedStatement.setString(6, account.getAddress2());
            preparedStatement.setString(7, account.getCity());
            preparedStatement.setString(8, account.getState());
            preparedStatement.setString(9, account.getZip());
            preparedStatement.setString(10, account.getCountry());
            preparedStatement.setString(11, account.getPhone());
            preparedStatement.setString(12, account.getUsername());
            preparedStatement.executeUpdate();
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);


        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public void insertProfile(Account account) {//insertProfile  VALUES (#{languagePreference}, #{favouriteCategoryId}, #{username})";
        try {


            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(insertProfile);
            preparedStatement.setString(1, account.getLanguagePreference());
            preparedStatement.setString(2, account.getFavouriteCategoryId());
            preparedStatement.setString(3, account.getUsername());
            preparedStatement.executeUpdate();
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insertSignon(Account account) {//updateProfile   insertSignon   insertSignon
        try {
            Connection connection = DBUtil.getConnection();


            PreparedStatement preparedStatement = connection.prepareStatement(insertSignon);
            preparedStatement.setString(1, account.getPassword());
            preparedStatement.setString(2, account.getUsername());
            preparedStatement.executeUpdate();
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateAccount(Account account) {//
        try {

            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(updateAccount);
            preparedStatement.setString(11, account.getUsername());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                preparedStatement.setString(1, account.getEmail());
                preparedStatement.setString(2, account.getFirstName());
                preparedStatement.setString(3, account.getLastName());
                preparedStatement.setString(4, account.getState());
                preparedStatement.setString(5, account.getAddress1());
                preparedStatement.setString(6, account.getAddress2());
                preparedStatement.setString(7, account.getCity());
                preparedStatement.setString(8, account.getState());
                preparedStatement.setString(9, account.getZip());
                preparedStatement.setString(9, account.getCountry());
                preparedStatement.setString(10, account.getPhone());
                preparedStatement.executeUpdate();
                DBUtil.closeResultSet(resultSet);
                DBUtil.closePreparedStatement(preparedStatement);
                DBUtil.closeConnection(connection);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void updateProfile(Account account) {//updateProfile
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(updateProfile);
            preparedStatement.setString(3, account.getUsername());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                preparedStatement.setString(1, account.getLanguagePreference());
                preparedStatement.setString(2, account.getFavouriteCategoryId());
                preparedStatement.executeUpdate();
                DBUtil.closeResultSet(resultSet);
                DBUtil.closePreparedStatement(preparedStatement);
                DBUtil.closeConnection(connection);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /*  "      LANGPREF = #{languagePreference},\n" +
            "      FAVCATEGORY = #{favouriteCategoryId}\n" +
            "    WHERE USERID = #{username}";*/

    @Override
    public void updateSignon(Account account) {//updateSignon
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(updateSignon);
            preparedStatement.setString(2, account.getUsername());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                preparedStatement.setString(1, account.getPassword());
                DBUtil.closeResultSet(resultSet);
                DBUtil.closePreparedStatement(preparedStatement);
                DBUtil.closeConnection(connection);
                preparedStatement.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


   /* public static void main(String[] args) {

            try {
                Connection connection = DBUtil.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(getAccountByUsername);
                preparedStatement.setString(1, "jee");
                ResultSet resultSet = preparedStatement.executeQuery();
                if(resultSet.next()){
                    System.out.println("you");

            }

            }catch (Exception e){
                e.printStackTrace();
            }
            System.out.println("meryou");
        }

*/
}
