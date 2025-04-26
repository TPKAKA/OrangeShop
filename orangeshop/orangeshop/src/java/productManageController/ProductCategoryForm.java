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
import model.ProductCategory;
import utils.Validator;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "ProductCategoryForm", urlPatterns = {"/productcategoryform"})
public class ProductCategoryForm extends HttpServlet {

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
            out.println("<title>Servlet newsCategoryForm</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet newsCategoryForm at " + request.getContextPath() + "</h1>");
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
        ShopDAO dao = new ShopDAO();
        String id = request.getParameter("id");
        if (id != null && !id.isEmpty()) {
            ProductCategory category = dao.getProductCategoryById(Integer.parseInt(id));
            request.setAttribute("category", category);
        }
        request.setAttribute("verify", request.getParameter("verify"));
        request.getRequestDispatcher("productcategoryform.jsp").forward(request, response);
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
        ShopDAO dao = new ShopDAO();
        String categoryId = request.getParameter("categoryId");
        String productCategoryName = request.getParameter("productCategoryName");
        Validator validator = new Validator();
        String verifyString = validator.checkString(productCategoryName);
        if (verifyString != null) {
            if (categoryId == null || categoryId.isEmpty()) {
                request.setAttribute("verify", verifyString);
                request.getRequestDispatcher("productcategoryform.jsp").forward(request, response);
            } else {
                try{
                    response.sendRedirect("productcategoryform?id=" + Integer.parseInt(categoryId) + "&verify=" + verifyString);
                }catch(IOException e){
                    
                }
            }
            
        } else {
            if (categoryId == null || categoryId.isEmpty()) {
                dao.addProductCategory(productCategoryName);
            } else {
                try {
                    dao.updateProductCategory(Integer.parseInt(categoryId), productCategoryName);
                } catch (NumberFormatException e) {

                }
            }
            response.sendRedirect("productcategoriesmanagement");
        }
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
