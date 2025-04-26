/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.user;

import dal.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.User;
import utils.MD5;

/**
 *
 * @author Dell E7450
 */
@WebServlet(name="ProfileSecurity", urlPatterns={"/ProfileSecurity"})
public class ProfileSecurity extends HttpServlet {
   
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
            out.println("<title>Servlet ProfileSecurity</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ProfileSecurity at " + request.getContextPath () + "</h1>");
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
        try {
            String UserID = request.getParameter("uid");
            UserDAO userdao = new UserDAO();
            User user = userdao.getUserbyUserID(UserID);
            if (user != null) {
            request.setAttribute("user", user);
            request.getRequestDispatcher("view/user/profile/profileSecurity.jsp").forward(request, response);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProfileSecurity.class.getName()).log(Level.SEVERE, null, ex);
        }
        
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
        String email = request.getParameter("email");
        String UserID = request.getParameter("uid");
        String pass = request.getParameter("pass");
        String re_pass = request.getParameter("re_pass");

        if (!pass.equals(re_pass)) {    
            request.setAttribute("mess", "Password not match");
            response.sendRedirect("ProfileSecurity?uid=" + UserID + "&mess=Password+not+match");
        } else {
               
                try {
                    UserDAO userdao = new UserDAO();
                    userdao.updatePass( email, new MD5().getMD5Password(pass));
                    request.setAttribute("messSuccess", "Change password success");
                    response.sendRedirect("ProfileSecurity?uid=" + UserID + "&messSuccess=Change+password+success");
                } catch (NoSuchAlgorithmException | SQLException ex) {
                    Logger.getLogger(ProfileSecurity.class.getName()).log(Level.SEVERE, null, ex);
                }

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
