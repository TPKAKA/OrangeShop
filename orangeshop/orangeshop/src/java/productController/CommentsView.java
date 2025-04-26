/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package productController;

import dal.CommentDao;
import model.Comments;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.User;

/**
 *
 * @author Dell E7450
 */
public class CommentsView extends HttpServlet {
   
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
            out.println("<title>Servlet Comments</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Comments at " + request.getContextPath () + "</h1>");
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
        try {
            String productid = request.getParameter("productid");
            CommentDao dao = new CommentDao();
            String details = request.getParameter("details");

            List<Comments> list = dao.getAllComment(productid);
            request.setAttribute("listComment", list);
            User u = null;
            if (request.getSession().getAttribute("customer") != null) {
                u = (User) request.getSession().getAttribute("customer");
            } else if (request.getSession().getAttribute("guest") != null) {
                u = (User) request.getSession().getAttribute("guest");
            } else if (request.getSession().getAttribute("staff") != null) {
                u = (User) request.getSession().getAttribute("staff");
            } else if (request.getSession().getAttribute("admin") != null) {
                u = (User) request.getSession().getAttribute("admin");  
            } 
            if (u != null) {
                if (request.getParameter("cmtid") == null) {
                    int id_comment = new CommentDao().insertComment(productid, u.getUserID(), details);
                    new CommentDao().updateOrigin(id_comment);
                } else {
                    int UserID = Integer.parseInt(request.getParameter("reply_id"));
                    int id_comment = Integer.parseInt(request.getParameter("cmtid"));
                    new CommentDao().insertReply(productid, u.getUserID(), details, id_comment, UserID);
                }
            }
            request.getRequestDispatcher("commentsview.jsp" ).forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(CommentsView.class.getName()).log(Level.SEVERE, null, ex);
        }
  
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
