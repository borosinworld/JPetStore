package servlets;

import java.io.IOException;

public class MainServlet extends javax.servlet.http.HttpServlet {
    private static final  String MAIN = "/WEB-INF/jsp/catalog/Main.jsp";
    private String signin;
    private String signon;
    private String myaccount;
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
         doGet(request,response);
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {


        System.out.println("fdsfdsfdfad");
        request.getRequestDispatcher(MAIN).forward(request,response);
}
}
