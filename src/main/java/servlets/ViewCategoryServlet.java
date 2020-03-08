package servlets;

import org.csu.mypetstore.domain.Category;
import org.csu.mypetstore.domain.Product;
import org.csu.mypetstore.service.CatalogService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class ViewCategoryServlet extends HttpServlet {
    private String categoryId;
    private static final  String VIEW_CATEGORY = "/WEB-INF/jsp/catalog/Category.jsp";
    private String parameter1;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       /* parameter1=request.getParameter("parameter1");
        ServletContext application = request.getServletContext();
        application.setAttribute("parameter1",parameter1);

*/
        categoryId = request.getParameter("categoryId");
        CatalogService catalogService = new CatalogService();
        Category category = catalogService.getCategory(categoryId);
        List<Product> productList = catalogService.getProductListByCategory(categoryId);

        HttpSession httpSession = request.getSession();
        httpSession.setAttribute("category",category);
        httpSession.setAttribute("productList",productList);

        request.getRequestDispatcher(VIEW_CATEGORY).forward(request,response);
    }
}
