package servlets;

import org.csu.mypetstore.domain.Order;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;

public class ConfirmServlet extends HttpServlet {
    private static final String CONFIRM_ORDER = "/WEB-INF/jsp/order/ConfirmOrder.jsp";
    private static final String SHIPPING = "/WEB-INF/jsp/order/ShippingForm.jsp";
    private static final String VIEW_ORDER = "/WEB-INF/jsp/order/ViewOrder.jsp";
    private static final String ERROR ="/WEB-INF/jsp/common/Error.jsp";
    private boolean shippingAddressRequired = false;
    private Order order;
      String message;
    private String username;
    private Date orderDate;
    private String shipAddress1;
    private String shipAddress2;
    private String shipCity;
    private String shipState;
    private String shipZip;
    private String shipCountry;
    private String billAddress1;
    private String billAddress2;
    private String billCity;
    private String billState;
    private String billZip;
    private String billCountry;
    private String courier;
    private BigDecimal totalPrice;
    private String billToFirstName;
    private String billToLastName;
    private String shipToFirstName;
    private String shipToLastName;
    private String creditCard;
    private String expiryDate;
    private String cardType;
    private String locale;
    private String status;
      //private boolean
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //request.getRequestDispatcher(CONFIRM_ORDER ).forward(request,response);
        cardType = request.getParameter("cardType");
        creditCard = request.getParameter("creditCard");
        expiryDate = request.getParameter("expiryDate");

        billToFirstName = request.getParameter("billToFirstName ");
        billToLastName = request.getParameter("billToLastName");
        billAddress1 = request.getParameter("billAddress1");
        billAddress2 = request.getParameter("billAddress2");
        billCity =request.getParameter("billCity");
        billState = request.getParameter("billState");
        billCountry = request.getParameter("billCountry");
        billZip = request.getParameter("billZip");

        HttpSession session = request.getSession();
        //shippingAddressRequired = (Boolean)request.getParameter("shippingAddressRequired");
        //shippingAddressRequired = (Boolean)session.getAttribute("shippingAddressRequired");
        order = (Order) session.getAttribute("order");


        order.setExpiryDate(expiryDate);
        order.setCreditCard(creditCard);
        order.setCardType(cardType);
        order.setBillAddress1(billAddress1);
        order.setBillAddress2(billAddress2);
        order.setBillCity(billCity);
        order.setBillCountry(billCountry);
        order.setBillState(billState);
        order.setBillZip(billZip);
        order.setBillToFirstName(billToFirstName);
        order.setBillToLastName(billToLastName);
        if (shippingAddressRequired) {
            shippingAddressRequired = false;
            session.setAttribute("order",order);
            session.setAttribute("shippingAddressRequired ",false );
            request.getRequestDispatcher(SHIPPING).forward(request, response);
        } else {



            order.setShipAddress1(billAddress1);
            order.setShipAddress2(billAddress2);
            order.setShipCity(billCity);
            order.setShipCountry(billCountry);
            order.setShipState(billState);
            order.setShipZip(billZip);
            order.setShipToFirstName(billToFirstName);
            order.setShipToLastName(billToLastName);
            session.setAttribute("order",order);
            request.getRequestDispatcher(CONFIRM_ORDER).forward(request, response);
        }
        //return new ForwardResolution(CONFIRM_ORDER);
    }
       /* else if (getOrder() != null) {

                orderService.insertOrder(order);

               Cart cart = (Cart) session.getAttribute("cart");
                cart.clear();

                setMessage("Thank you, your order has been submitted.");

                request.getRequestDispatcher(VIEW_ORDER).forward(request,response);
            } else {
                message = "An error occurred processing your order (order was null).";
                request.getRequestDispatcher(ERROR).forward(request,response);
            }
    }
    /*   HttpSession session = context.getRequest().getSession();

    if (shippingAddressRequired) {
      shippingAddressRequired = false;
      return new ForwardResolution(SHIPPING);
    } else if (!isConfirmed()) {
      return new ForwardResolution(CONFIRM_ORDER);
    } else if (getOrder() != null) {

      orderService.insertOrder(order);

      CartActionBean cartBean = (CartActionBean) session.getAttribute("/actions/Cart.action");
      cartBean.clear();

      setMessage("Thank you, your order has been submitted.");

      return new ForwardResolution(VIEW_ORDER);
    } else {
      setMessage("An error occurred processing your order (order was null).");
      return new ForwardResolution(ERROR);
    }*/
}
