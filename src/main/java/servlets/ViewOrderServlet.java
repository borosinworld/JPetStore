package servlets;

import org.csu.mypetstore.domain.Cart;
import org.csu.mypetstore.domain.Order;
import org.csu.mypetstore.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ViewOrderServlet extends HttpServlet {
    private static final String VIEW_ORDER = "/WEB-INF/jsp/order/ViewOrder.jsp";
    private Cart cart;
    private String orderId;
    private Order order;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //orderId = request.getParameter("orderId");
        HttpSession session = request.getSession();
        OrderService orderService = new OrderService();
        //Order order = orderService.getOrder(Integer.valueOf(orderId));
        order = (Order)session.getAttribute("order");
        //
        //需要添加判断order是否已经在原来的表中昂
        orderService.insertOrder(order);


        session.setAttribute("cart",null);
        // cartBean = (CartActionBean) session.getAttribute("/actions/Cart.action");
        //cartBean.clear();

        //setMessage("Thank you, your order has been submitted.");

        request.getRequestDispatcher(VIEW_ORDER).forward(request,response);



    /*    HttpSession session = context.getRequest().getSession();

    AccountActionBean accountBean = (AccountActionBean) session.getAttribute("accountBean");

    order = orderService.getOrder(order.getOrderId());

    if (accountBean.getAccount().getUsername().equals(order.getUsername())) {
      return new ForwardResolution(VIEW_ORDER);
    } else {
      order = null;
      setMessage("You may only view your own orders.");
      return new ForwardResolution(ERROR);
    }*/
    }
}
