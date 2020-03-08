package servlets;

import org.csu.mypetstore.domain.Cart;
import org.csu.mypetstore.domain.Item;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class RemoveItemFromCartServlet extends HttpServlet {
    private static String VIEW_ITEM ="/WEB-INF/jsp/catalog/Item.jsp";
    private static String ERROR_ITEM ="/WEB-INF/jsp/common/Error.jsp";
    private Cart cart ;
    //处理请求所需要的数据
    private String workingItemId;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     workingItemId = request.getParameter("workingItemId");
        HttpSession session = request.getSession();
        cart = (Cart)session.getAttribute("cart");
        Item item = cart.removeItemById(workingItemId);
        if(item==null){
            session.setAttribute("message","你没有去选中一个或者时不停的刷新");
            request.getRequestDispatcher(VIEW_ITEM).forward(request,response);
        }else{
            request.getRequestDispatcher(ERROR_ITEM).forward(request,response);
        }
    }
}
