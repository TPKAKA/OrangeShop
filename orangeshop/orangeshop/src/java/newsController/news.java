/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package newsController;

import dal.NewsDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.News;
import model.NewsCategory;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "news", urlPatterns = {"/news"})
public class news extends HttpServlet {

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
        NewsDAO dao = new NewsDAO();

        //select new post
        List<News> fiveNewPost = dao.get5NewPost();
        request.setAttribute("fiveNewPost", fiveNewPost);

        //select category
        List<NewsCategory> newsCategories = dao.getNewsCategories();
        request.setAttribute("newsCategories", newsCategories);
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
        processRequest(request, response);

        NewsDAO dao = new NewsDAO();

        String c = (String) request.getParameter("category");
        if (c == null || c.isEmpty()) {
            c = "1";
        }
        int category = Integer.parseInt(c);

        //paging
        String page = (String) request.getParameter("page");
        if (page == null || page.isEmpty()) {
            page = "1";
        }
        int pageInput = Integer.parseInt(page);
        int newsPerPage = Integer.parseInt(getServletContext().getInitParameter("newsPerPage"));

        //number of page
        int numOfPage = dao.getNumOfPage(category, newsPerPage);
        request.setAttribute("numOfPage", numOfPage);

        //select news
        List<News> news = dao.getNewsByCategoryId(category, pageInput, newsPerPage);
        request.setAttribute("news", news);

        request.getRequestDispatcher("news.jsp").forward(request, response);
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
        NewsDAO dao = new NewsDAO();
        String newsName = request.getParameter("newsName");
        List<News> news = dao.searchNewsByName(newsName);
        request.setAttribute("news", news);
        request.getRequestDispatcher("news.jsp").forward(request, response);
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