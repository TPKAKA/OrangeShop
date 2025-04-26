/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.user;

import dal.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.User;
import utils.FileUtil;

/**
 *
 * @author Dell E7450
 */
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1, // 1 MB /// kich thuoc giu tren bo nho neu  vuot qua se l;uwu vao dia
        maxFileSize = 1024 * 1024 * 10, // 30 MB // kich thuoc toi da cho phep upload 
        maxRequestSize = 1024 * 1024 * 100 // 100 MB // tonh kich thuoc toi da
)
public class UserProfile extends HttpServlet {
    private UserDAO userDao = new UserDAO();
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
//        try (PrintWriter out = response.getWriter()) {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet UserProfile</title>");  
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet UserProfile at " + request.getContextPath () + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
//        }
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
            String userid = request.getParameter("uid");
            User user = userDao.getUserbyUserID(userid);
            if (user != null) {
            request.setAttribute("user", user);
            request.getRequestDispatcher("view/user/profile/userProfile.jsp").forward(request, response);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserProfile.class.getName()).log(Level.SEVERE, null, ex);
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
        
        try {
            
            String FullName = request.getParameter("FullName");
            String Address = request.getParameter("Address");
//            String Phone = request.getParameter("Phone");
            String userid = request.getParameter("uid");
            
            // Upload Avatar
            Part AvatarPart = request.getPart("Image");
            if (AvatarPart != null && AvatarPart.getSize() > 0) {
            String originalAvatarFileName = AvatarPart.getSubmittedFileName();// Lấy tên gốc của tệp video
            String fileAvatarName = Paths.get(originalAvatarFileName).getFileName().toString();// Tạo tên tệp duy nhất để lưu trữ trên máy chủ
            String uploadAvatarPath = getServletContext().getRealPath("/") + "assets/img/userAvatar/";// Đường dẫn tới thư mục lưu trữ video trên máy chủ
            File uploadDir = new File(uploadAvatarPath);// Kiểm tra nếu thư mục lưu trữ không tồn tại, thì tạo mới
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            String fileAvatarPath = uploadAvatarPath + File.separator + fileAvatarName;// Tạo đường dẫn lưu trữ trên máy chủ
            AvatarPart.write(fileAvatarPath);// Lưu tệp video vào thư mục trên máy chủ
            String Avatar = "assets/img/userAvatar/" + fileAvatarName;
            
            UserDAO userdao = new UserDAO();
            userdao.UpdateLogin(FullName, Address, Avatar, userid);
            }else{
                userDao.UpdateLoginWithoutAvatar(FullName, Address, userid);
            }
            request.setAttribute("messSuccess", "Update Profile success");
            response.sendRedirect("UserProfile?uid=" + userid + "&messSuccess=Update+Profile+success" );
            
        } catch (SQLException ex) {
            Logger.getLogger(UserProfile.class.getName()).log(Level.SEVERE, null, ex);
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
