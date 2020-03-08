package servlets;

import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.service.AccountService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ViewEditAccountFormServlet extends HttpServlet {
    private static final  String EDITACCOUNT = "/WEB-INF/jsp/account/EditAccountForm.jsp";
    private String username;
    private Account account;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
   username = request.getParameter("username");
   //System.out.println(username+"fdsfdsfdadfadsfsdfadf");      //测试使用
        AccountService accountService = new AccountService();
        account = accountService.getAccount(username);
        HttpSession session = request.getSession();
        session.setAttribute("account",account);

     request.getRequestDispatcher(EDITACCOUNT).forward(request,response);
    }
}
