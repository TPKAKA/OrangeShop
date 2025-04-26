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
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.util.List;

/**
 *
 * @author ADMIN
 */
//@WebServlet(name = "DetailControl", urlPatterns = {"/detail"})
public class DetailControl extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Homemanagement</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Homemanagement at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();

        String productid = request.getParameter("productid");
        String cateID = request.getParameter("categoryid");
        String indexPage = request.getParameter("index");
        session.setAttribute("cateID", cateID);
        session.setAttribute("productid", productid);
        ShopDAO dao = new ShopDAO();

        Product p = dao.getProductById(productid);
        Image i = dao.getImgByProductId(productid);
        ProductCategory c = dao.getCategoryNameByProductId(productid);
        ProductDetail productDetail = new ProductDetail();
        productDetail.setProduct(p);
        productDetail.setImage(i);
        productDetail.setCategory(c);
        List<Product> list = dao.getProductByCategory(cateID, 1);
        List<ProductCategory> listC = dao.getAllCategory();
        List<Image> image = dao.getAllIMG();

        session.setAttribute("listP", list);
        session.setAttribute("listCC", listC);
        session.setAttribute("detail", productDetail);
        session.setAttribute("img", image);
        response.sendRedirect("shop-details.jsp");
       
//   request.getRequestDispatcher("shop-details.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
