package servlets;

import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.domain.Product;
import org.csu.mypetstore.service.AccountService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class SaveAccountServlet extends HttpServlet {
    private static final  String MAIN = "/WEB-INF/jsp/catalog/Main.jsp";
    private Account account = new Account();
    private String username;
    private String password;
    private String email;
    private String firstname;
    private  String lastname;
    private String address1;
    private String address2;
    private String city;
    private String state;
    private String zip;
    private String country;
    private String phone;
    private String favouriteCategoryId;
    private String languagePreference;
    private List<Product> myList;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        AccountService accountService = new AccountService();
        //.
        // CatalogService catalogService = new CatalogService();

       username = request.getParameter("username");
       password = request.getParameter("password");
        email = request.getParameter("email");
        firstname = request.getParameter("firstname");
        lastname= request.getParameter("lastname");
        address1 = request.getParameter("address1");
        address2 = request.getParameter("address2");
        city = request.getParameter("city");
        state = request.getParameter("state");
       zip = request.getParameter("zip");
       country= request.getParameter("country");
       phone= request.getParameter("phone");
        favouriteCategoryId = request.getParameter("favouriteCategoryId");
        languagePreference = request.getParameter("languagePreference");
        //accountService.insertAccount(account);
        account.setUsername(username);
        account.setPassword(password);

        account.setFavouriteCategoryId(favouriteCategoryId);
        account.setLanguagePreference(languagePreference);

        account.setEmail(email);
        account.setFirstName(firstname);
        account.setLastName(lastname);

        //.setStatus(request.getParameter("status"));

        account.setAddress1(address1);
        account.setAddress2(address2);

        account.setCity(city);
        account.setState(state);
        account.setZip(zip);
        account.setCountry(country);

        account.setPhone(phone);
       // account.setLanguagePreference(request.getParameter("languagepreference"));

        accountService.updateAccount(account);
        request.getRequestDispatcher(MAIN).forward(request,response);

    }
}
