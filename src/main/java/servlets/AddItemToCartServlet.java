package servlets;

import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.domain.Cart;
import org.csu.mypetstore.domain.Item;
import org.csu.mypetstore.service.CatalogService;
import org.csu.mypetstore.service.LogService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AddItemToCartServlet extends HttpServlet {
    //处理请求的跳转页面
    private CatalogService catalogService;
    //是否需要第哦啊用业务逻辑层
    private Cart cart ;
    //处理请求所需要的数据
    private String workingItemId;
    private static final String VIEW_CART = "/WEB-INF/jsp/cart/Cart.jsp";
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        workingItemId = request.getParameter("workingItemId");
        HttpSession session = request.getSession();
        cart = (Cart)session.getAttribute("cart");
        if(cart==null){
            cart = new Cart();
        }

        if(cart.containsItemId(workingItemId)){
            cart.incrementQuantityByItemId(workingItemId);
        }else{
            catalogService = new CatalogService();
            boolean isInStock = catalogService.isItemInStock(workingItemId);
            Item item = catalogService.getItem(workingItemId);
            cart.addItem(item,isInStock);
            session.setAttribute("cart", cart); //注意 修改
        }
        session.setAttribute("cart",cart);

        Account account = (Account)session.getAttribute("account");
        if(account != null){
            HttpServletRequest httpRequest= (HttpServletRequest) request;
            String strBackUrl = "http://" + request.getServerName() + ":" + request.getServerPort()
                    + httpRequest.getContextPath() + httpRequest.getServletPath() + "?" + (httpRequest.getQueryString());

            LogService logService = new LogService();
            Item item = catalogService.getItem(workingItemId);
            String logInfo = logService.logInfo(" ") + strBackUrl + " " + item + "数量+1 ";
            logService.insertLogInfo(account.getUsername(), logInfo);
        }

        request.getRequestDispatcher(VIEW_CART).forward(request,response);

    }
}
