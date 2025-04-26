/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.user;

import dal.UserDAO;
import model.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.MD5;

/**
 *
 * @author ADMIN
 */
public class RegisterWithPhoneSuccess extends HttpServlet {

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
            out.println("<title>Servlet RegisterWithPhoneSuccess</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RegisterWithPhoneSuccess at " + request.getContextPath() + "</h1>");
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
        // Lấy giá trị số điện thoại và họ tên từ tham số request
        String phone = request.getParameter("phone");
        String fullname = request.getParameter("fullname");
        // Mật khẩu mặc định
        String password = "";
        // Ảnh đại diện mặc định
        String defaultImg = "https://upload.wikimedia.org/wikipedia/commons/9/99/Sample_User_Icon.png";
        try {
            try {
                // Call UserDAO
                UserDAO ud = new UserDAO();
                // Thuc hien ADD tai khoan duoc dang ky boi sđt vao DB
                ud.insertUserByPhone( new MD5().getMD5Password(password),phone, fullname, defaultImg);
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(RegisterWithPhoneSuccess.class.getName()).log(Level.SEVERE, null, ex);
            }
           
            User user = new UserDAO().getUserByPhone(phone);
            // Dựa vào vai trò của người dùng, gán thông tin người dùng vào session attribute tương ứng
            switch (user.getUserRole_RoleID()) {
                case 1:
                    request.getSession().setAttribute("admin", user);
                    break;
                case 2:
                    request.getSession().setAttribute("staff", user);
                    break;
                case 3:
                    request.getSession().setAttribute("customer", user);
                    break;
                case 4:
                    request.getSession().setAttribute("guest", user);
                    break;
                default: {
                }
            }
            request.getSession().setAttribute("user_id", user.getUserID());
            // Chuyển hướng đến trang chu
            response.sendRedirect("home");
        } catch (Exception e) {
            e.printStackTrace();
        }
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
