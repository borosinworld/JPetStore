package servlets;

import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.domain.Product;
import org.csu.mypetstore.service.AccountService;
import org.csu.mypetstore.service.CatalogService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class SaveCountServlet extends HttpServlet {
    private static final String NEW_ACCOUNT = "/WEB-INF/jsp/account/NewAccountForm.jsp";
    private static final String MAIN = "/WEB-INF/jsp/account/NewAccountForm.jsp";
    private AccountService accountService;
    private CatalogService catalogService;

    private Account account;
    private List<Product> myList;
    private boolean authenticated;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        account.setPassword(request.getParameter("password"));
        account.setUsername(request.getParameter("username"));
        account.setFirstName(request.getParameter("firstname"));
        account.setLastName(request.getParameter("lastname"));

        account.setPhone(request.getParameter("phone"));
        account.setEmail(request.getParameter("email"));

        account.setCountry(request.getParameter("country"));
        account.setState(request.getParameter("state"));
        account.setZip(request.getParameter("zip"));
        account.setCity(request.getParameter("city"));
        account.setAddress1(request.getParameter("address1"));
        account.setAddress2(request.getParameter("address2"));

        accountService.insertAccount(account);
        account = accountService.getAccount(account.getUsername());
        HttpSession session = request.getSession();
        session.setAttribute("account", account);
        if (account == null) {
            request.getRequestDispatcher(NEW_ACCOUNT).forward(request,response);
        } else {
            request.getRequestDispatcher(MAIN).forward(request, response);
        }
    }
}
