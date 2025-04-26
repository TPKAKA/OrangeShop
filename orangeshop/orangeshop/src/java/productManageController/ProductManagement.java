/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package productManageController;

import dal.ShopDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import model.Product;
import model.ProductCategory;

/**
 *
 * @author ADMIN
 */
@WebServlet(name="ProductManagement", urlPatterns={"/productmanagement"})
public class ProductManagement extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ProductManagement</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ProductManagement at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        ShopDAO dao = new ShopDAO();
        List<ProductCategory> categoryList = dao.getAllCategory();
        request.setAttribute("categoryList", categoryList);
        
        String cateId = request.getParameter("categoryId");
        request.setAttribute("categoryId", cateId);
        int categoryId;
        if (cateId == null || cateId.isEmpty()) {
            categoryId = dao.getMinCategoryId();
        } else {
            categoryId = Integer.parseInt(cateId);
        }
        
        //paging
        String page = request.getParameter("page");
        int pageInput;
        int recordPerPageManagement = Integer.parseInt(getServletContext().getInitParameter("recordPerPageManagement"));
        int numberOfPage = dao.maxProductPageManagement(categoryId, recordPerPageManagement);
        if (page == null || page.isEmpty()) {
            pageInput = 1;
        }
        else if(Integer.parseInt(page) < 1){
            pageInput = numberOfPage;
        }
        else if(Integer.parseInt(page) > numberOfPage){
            pageInput = 1;
        }
        else{
            pageInput = Integer.parseInt(page);
        }
        request.setAttribute("page", pageInput);
        
        Map<Product, Integer> map = dao.getProductManagement(categoryId, pageInput, recordPerPageManagement);
        request.setAttribute("productList", map);
        request.getRequestDispatcher("product-management.jsp").forward(request, response);
    }

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String productName = request.getParameter("productName");
        ShopDAO dao = new ShopDAO();
        Map<Product, Integer> productList = dao.searchProductManagement(productName);
        request.setAttribute("productList", productList);
        List<ProductCategory> categoryList = dao.getAllCategory();
        request.setAttribute("categoryList", categoryList);
        request.getRequestDispatcher("product-management.jsp").forward(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
