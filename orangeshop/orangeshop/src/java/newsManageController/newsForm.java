/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package newsManageController;

import dal.NewsDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;
import java.util.List;
import model.News;
import model.NewsCategory;
import utils.DownloadFile;
import utils.Validator;

/**
 *
 * @author ADMIN
 */
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2,
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 20)
@WebServlet(name = "newsForm", urlPatterns = {"/newsform"})
public class newsForm extends HttpServlet {

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
            out.println("<title>Servlet newsForm</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet newsForm at " + request.getContextPath() + "</h1>");
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
        NewsDAO dao = new NewsDAO();
        String id = request.getParameter("id");
        if (id != null && !id.isEmpty()) {
            News news = dao.getNewsById(Integer.parseInt(id));
            news.setContent(news.getContent().replaceAll("<br>", "\n"));
            request.setAttribute("news", news);
        }
        
        request.setAttribute("verifyTitle", request.getParameter("verifyTitle"));
        request.setAttribute("verifyImage", request.getParameter("verifyImage"));
        request.setAttribute("verifyContent", request.getParameter("verifyContent"));
        
        List<NewsCategory> newsCategories = dao.getNewsCategories();
        request.setAttribute("newsCategories", newsCategories);
        request.getRequestDispatcher("newsform.jsp").forward(request, response);
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
        String newsCategory = request.getParameter("newsCategory");
        String newsName = request.getParameter("newsName");
        String newsContent = request.getParameter("newsContent").replaceAll("\n", "<br>");

        DownloadFile dow = new DownloadFile();
        Part newsImagePath = request.getPart("newsImage");
        String imageName = dow.extractFileName(newsImagePath);
        imageName = imageName.replaceAll(" ", "");

        Validator validator = new Validator();
        String verifyTitle = validator.checkString(newsName);
        String verifyImage = validator.checkImage(newsImagePath);
        String verifyContent = validator.checkString(newsContent);

        NewsDAO dao = new NewsDAO();
        List<NewsCategory> newsCategories = dao.getNewsCategories();
        request.setAttribute("newsCategories", newsCategories);
        String id = request.getParameter("id");
        if (verifyTitle != null || verifyImage != null || verifyContent != null) {
            request.setAttribute("verifyTitle", verifyTitle);
            request.setAttribute("verifyImage", verifyImage);
            request.setAttribute("verifyContent", verifyContent);
            if(id == null || id.isEmpty()){
                request.getRequestDispatcher("newsform.jsp").forward(request, response);
            }else{
                request.setAttribute("id", id);
                response.sendRedirect("newsform");
            }
        } else {
            try {//không thực hiện nếu update -> đã có đường dẫn ảnh
                //lấy ảnh được up lên về
                newsImagePath.write(dow.getFolderUpload("blog").getAbsolutePath() + File.separator + imageName);
            } catch (IOException e) {

            }
            //check add or edit
            if (id == null || id.isEmpty()) {
                dao.addNews(48, Integer.parseInt(newsCategory), newsName, newsContent, imageName);
            } else {
                dao.updateNews(Integer.parseInt(id), 48, Integer.parseInt(newsCategory), newsName, newsContent, imageName);
            }
            response.sendRedirect("newsmanagement?categoryId=" + newsCategory);
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
