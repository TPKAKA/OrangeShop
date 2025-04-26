/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package productController;

import dal.ShopDAO;
import model.ProductCategory;
import model.Image;
import model.Product;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "SortControl", urlPatterns = {"/sort"})
public class SortControl extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String indexPage = request.getParameter("index");

        if (indexPage == null) {
            indexPage = "1";
        }
        int index = Integer.parseInt(indexPage);
        ShopDAO dao = new ShopDAO();

        List<Product> list;
        List<ProductCategory> listC = dao.getAllCategory();
        String sortOption = request.getParameter("sortOption");

        if (sortOption != null && !sortOption.isEmpty()) {
            boolean ascending = true;

            if (sortOption.equals("lowToHigh")) {
                list = dao.getProductsSorted(true, index,12);
            } else if (sortOption.equals("highToLow")) {
                list = dao.getProductsSorted(false, index,12);
            } else if (sortOption.equals("az")) {
                list = dao.getProductAZ(index,12);
                ascending = true; // Assume ascending order for alphabetical sorting
            } else {
                // Default case if no recognized sort option is provided
                list = dao.PageProducts(index,12);
            }

        } else {
            // Default case if no sort option is provided
            list = dao.PageProducts(index,12);
        }

        List<Image> image = dao.getAllIMG();
        int count = dao.getTotalProduct();
        int endPage = count / 12;

        if (count % 12 != 0) {
            endPage++;
        }

        request.setAttribute("endP", endPage);
        request.setAttribute("img", image);
        request.setAttribute("listP", list);
        request.setAttribute("listCC", listC);
        request.getRequestDispatcher("shop.jsp").forward(request, response);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Product Control Servlet";
    }
}
