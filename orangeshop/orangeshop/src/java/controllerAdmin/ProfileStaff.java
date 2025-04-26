/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllerAdmin;

import dal.AdminDao;
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
import model.Account;
import utils.FileUtil;

/**
 *
 * @author ACER
 */
//ProfileAccount
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1, // 1 MB /// kich thuoc giu tren bo nho neu  vuot qua se l;uwu vao dia
        maxFileSize = 1024 * 1024 * 30, // 30 MB // kich thuoc toi da cho phep upload 
        maxRequestSize = 1024 * 1024 * 100 // 100 MB // tonh kich thuoc toi da
)

@WebServlet(name = "ProfileAccount", urlPatterns = {"/ProfileAccount"})
public class ProfileStaff extends HttpServlet {

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
            out.println("<title>Servlet ProfileAccount</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ProfileAccount at " + request.getContextPath() + "</h1>");
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
        AdminDao dao = new AdminDao();
        int userid = Integer.parseInt(request.getParameter("pid"));

        List<Account> listA = dao.getAllAccountByid(userid);
        request.setAttribute("listA", listA);
        request.getRequestDispatcher("profilestaff.jsp").forward(request, response);
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
        AdminDao dao = new AdminDao();

        String userId = request.getParameter("userid");
        String roleid = request.getParameter("roleid");
        String na = request.getParameter("name");
        String mail = request.getParameter("mail");
        String dob = request.getParameter("dob");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        String pass = request.getParameter("pass");
        Part filePart = request.getPart("image"); // Lay file theo Field
        File f = new File("F:\\orangeshop12\\orangeshop12\\orangeshop3HANHCONCHUANGU\\orangeshop\\build\\web\\assets\\img");

        FileUtil.saveImage(filePart);
        FileUtil.saveFileToBuild(filePart);

        String fileName = filePart.getSubmittedFileName();  // lay fiel gooc 
        String lowerCaseFileName = fileName.toLowerCase();
        if (userId == null || userId.isEmpty()) {
            // List<Account> listA = dao.getAllAccountByid(userid);
            Account account = dao.checkAccountExistByUseridAndEmail(mail, phone);
            if (account != null) {
                String errorMessage = "Error: Your Email or Phone already exist!";
                request.setAttribute("mess", errorMessage);
                request.getRequestDispatcher("addstaff.jsp").forward(request, response);
            } else {
                boolean validEmailOrPhone = dao.checkEmailOrPhone(mail) || dao.checkEmailOrPhone(phone);
                boolean validImageFormat = lowerCaseFileName.endsWith(".png") || lowerCaseFileName.endsWith(".jpg");

                if (validEmailOrPhone && validImageFormat && filePart.getSize() > 0 && filePart != null) {
                    if (roleid.equals("3")) {
                        dao.insertUser("3", mail, pass, phone, na, fileName, dob, address);
                        String successMessage = "Insert customers successful!";
                        request.setAttribute("messs", successMessage);
                        request.getRequestDispatcher("addcustomers.jsp").forward(request, response);
                    }
                    if (roleid.equals("2")) {
                        dao.insertUser("2", mail, pass, phone, na, fileName, dob, address);
                        String successMessage = "Insert staff successful!";
                        request.setAttribute("messs", successMessage);
                        request.getRequestDispatcher("addstaff.jsp").forward(request, response);

                    }
                } else {
                    String errorMessage = "Error: ";

                    if (!validEmailOrPhone) {
                        errorMessage += "Invalid email or phone. ";
                    }
                    if (!validImageFormat) {
                        errorMessage += "Invalid image format. ";
                    }
                    request.setAttribute("mess", errorMessage);
                    request.getRequestDispatcher("addstaff.jsp").forward(request, response);
                }
            }
        } else {
           
                int userIds = Integer.parseInt(request.getParameter("userid"));

                
       
                Account account = dao.checkAccountExistByUseridAndEmail(mail, phone);


                if (account != null) {
                    boolean validImageFormat = lowerCaseFileName.endsWith(".png") || lowerCaseFileName.endsWith(".jpg");

           
                    if (!FileUtil.saveImage(filePart).equals(f) && filePart != null && filePart.getSize() > 0 && validImageFormat) {
                 
                        dao.Updateuser(na, dob, address, userIds, fileName);
                    } else if (FileUtil.saveImage(filePart).equals(f)|| filePart.getSize() == 0) {
                   
                        dao.Updateusernoimage(na, dob, address, userIds);
                    } else {
                     
                        String errorMessage = "Error: Invalid image format.";
                        request.setAttribute("mess", errorMessage);
                    }
                } else {
              
                    String errorMessage = "Error: Account not found.";
                    request.setAttribute("mess", errorMessage);
                }


                response.sendRedirect("ProfileAccount?pid=" + userIds);

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
