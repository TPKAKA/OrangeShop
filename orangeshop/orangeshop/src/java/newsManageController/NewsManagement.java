/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package newsManageController;

import dal.NewsDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import model.News;
import model.NewsCategory;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "newsManagement", urlPatterns = {"/newsmanagement"})
public class NewsManagement extends HttpServlet {

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
        NewsDAO dao = new NewsDAO();
        
        List<NewsCategory> newsCategories = dao.getNewsCategories();
        request.setAttribute("newsCategories", newsCategories);

        String cateId = request.getParameter("categoryId");
        request.setAttribute("newsCategoryId", cateId);
        int categoryId;
        if (cateId == null || cateId.isEmpty()) {
            categoryId = dao.getMinCategoryId();
        } else {
            categoryId = Integer.parseInt(cateId);
        }
        request.setAttribute("category", categoryId);
        
        //paging
        String page = (String) request.getParameter("page");
        int pageInput;
        int recordPerPageManagement = Integer.parseInt(getServletContext().getInitParameter("recordPerPageManagement"));
        int numberOfPage = dao.maxNewsPageManagement(categoryId, recordPerPageManagement);
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
        
        Map<News, String> newsList = dao.getNewsManagement(categoryId, pageInput, recordPerPageManagement);
        request.setAttribute("newsList", newsList);

        request.getRequestDispatcher("news-management.jsp").forward(request, response);
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
        String newsName = request.getParameter("newsName");
        NewsDAO dao = new NewsDAO();
        Map<News, String> newsList = dao.searchNewsManagement(newsName);
        request.setAttribute("newsList", newsList);
        List<NewsCategory> newsCategories = dao.getNewsCategories();
        request.setAttribute("newsCategories", newsCategories);
        request.getRequestDispatcher("news-management.jsp").forward(request, response);
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
