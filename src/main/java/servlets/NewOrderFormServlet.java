package servlets;

import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.domain.Cart;
import org.csu.mypetstore.domain.Order;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class NewOrderFormServlet extends HttpServlet {
    private static final String NEW_ORDER = "/WEB-INF/jsp/order/NewOrderForm.jsp";
    private static final String VIEW_SIGNON = "/WEB-INF/jsp/account/SignonForm.jsp";
    private static final String ERROR = "/WEB-INF/jsp/account/Error.jsp";
    private String username;
    private List<Order> orderList;
    private Order order;
    private Account account;
    private String value;
    private Cart cart;
    private String message;
    Boolean shippingAddressRequired = false;
    Boolean confirmed;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        account = (Account)session.getAttribute("account");
        cart = (Cart)session.getAttribute("cart");


        order = new Order();
        shippingAddressRequired = false;
        confirmed = false;
        orderList = null;
        if (account == null ) {
            value = "You must sign on before attempting to check out.  Please sign on and try checking out again.";
            session.setAttribute("value", value);



            request.getRequestDispatcher(VIEW_SIGNON).forward(request, response);
        }

        else if (cart != null) {
            order.initOrder(account, cart);
            session.setAttribute("shippingAddressRequired",shippingAddressRequired);
            session.setAttribute("order",order);
            request.getRequestDispatcher(NEW_ORDER).forward(request, response);
        } else {
            //setMessage("An order could not be created because a cart could not be found.");
            message = "An order could not be created because a cart could not be found.";
            session.setAttribute("message", message);
            request.getRequestDispatcher(ERROR).forward(request, response);
        }

        /* HttpSession session = context.getRequest().getSession();
    AccountActionBean accountBean = (AccountActionBean) session.getAttribute("/actions/Account.action");
    CartActionBean cartBean = (CartActionBean) session.getAttribute("/actions/Cart.action");

    clear();
    if (accountBean == null || !accountBean.isAuthenticated()) {
      setMessage("You must sign on before attempting to check out.  Please sign on and try checking out again.");
      return new ForwardResolution(AccountActionBean.class);
    } else if (cartBean != null) {
      order.initOrder(accountBean.getAccount(), cartBean.getCart());
      return new ForwardResolution(NEW_ORDER);
    } else {
      setMessage("An order could not be created because a cart could not be found.");
      return new ForwardResolution(ERROR);
    }*/




        ////////





        }
    }

