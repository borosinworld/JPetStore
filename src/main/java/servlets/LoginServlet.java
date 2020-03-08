package servlets;

import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.domain.Daily;
import org.csu.mypetstore.domain.Product;
import org.csu.mypetstore.service.AccountService;
import org.csu.mypetstore.service.CatalogService;
import org.csu.mypetstore.service.DailyService;
import org.csu.mypetstore.service.LogService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

public class LoginServlet extends HttpServlet {
    private static final String VIEW_SIGNON = "/WEB-INF/jsp/account/SignonForm.jsp";
    private static final String MAIN = "/WEB-INF/jsp/catalog/Main.jsp";
    private Account account = null;
    private AccountService accountService;
    private CatalogService catalogService;
    private String username;
    private String password;
    private String value;
    private List<Product> myList;
    private boolean authenticated;
    private DailyService dailyService;
    private Daily daily;
    private Date date;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        username = request.getParameter("username");
        password = request.getParameter("password");
        HttpSession session = request.getSession();
        //验证码
        String code = request.getParameter("ValiImage");
        String code2 = (String)session.getAttribute("Valicode");
        System.out.println("验证码"+code+"code"+code2+"code2");            //测试使用
       if (!code.equals(code2)) {
            value="your valicode is not right";
            session.setAttribute("value", value);
            request.getRequestDispatcher(VIEW_SIGNON).forward(request,response);
        }
        accountService = new AccountService();
        account = accountService.getAccount(username, password);
        //System.out.println("account"+account.getCity()+account.getState());
        //System.out.println(account.getState()+"account.getState()");


       if (account.getCity() == null) {
            value = "Invalid username or password.  Signon failed.";
           session.setAttribute("value", value);
            //clear();

            request.getRequestDispatcher(VIEW_SIGNON).forward(request,response);
        } else {
            //需要设置sign in 为sign out 并且时点击login的时候触发该型号
           Date date= new Date(System.currentTimeMillis());
           String pattern="yyyy/MM/dd HH时mm分ss秒";
           SimpleDateFormat sdf= new SimpleDateFormat(pattern);
           String dater=sdf.format(date);

           if(account != null){
               HttpServletRequest httpRequest= request;
               String strBackUrl = "http://" + request.getServerName() + ":" + request.getServerPort()
                       + httpRequest.getContextPath() + httpRequest.getServletPath() + "?" + (httpRequest.getQueryString());

               System.out.println(strBackUrl);

               LogService logService = new LogService();
               String logInfo = logService.logInfo(" ") + strBackUrl + " 用户登录";
               logService.insertLogInfo(account.getUsername(), logInfo);
           }
          //daily.setDate(date);            //添加后程序出错
          //daily.setAccount(username);     //

//            Account account = accountService.getAccount(username, password);

           dailyService = new DailyService();
           dailyService.insertDaily(daily);
            account.setPassword(null);
            session.setAttribute("account",account);

            session.setAttribute("username",account.getUsername());
            //catalogService = new CatalogService();
            //myList = catalogService.getProductListByCategory(account.getFavouriteCategoryId());
            //authenticated = true;
            //HttpSession session = request.getSession();
            //session.setAttribute("signin",null);
            //session.setAttribute("signon","sign on");
            //session.setAttribute("Myaccount","My Account");
            //session.setAttribute("");
            request.getRequestDispatcher(MAIN).forward(request,response);
        }

    }
}
