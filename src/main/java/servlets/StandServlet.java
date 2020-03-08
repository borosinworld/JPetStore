package servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class StandServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String categoryId = request.getParameter("categoryid");
        System.out.println(categoryId+"fdsfdsfdsfdsfvyuvjvhjbkbkbk")  ;    //CatalogService catalogService = new CatalogService();
        /*Category category = catalogService.getCategory(categoryId);
        List<Product> productList = catalogService.getProductListByCategory(categoryId);

        HttpSession httpSession = request.getSession();
        httpSession.setAttribute("category",category);
        httpSession.setAttribute("productList",productList);

        response.setContentType("text/xml");
        PrintWriter out = response.getWriter();


            out.println("<msg>Exist</msg>");

            out.println("<msg>NotExist</msg>");

        out.flush();
        out.close();*/




    }






}
