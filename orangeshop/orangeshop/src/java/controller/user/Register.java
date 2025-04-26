/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.user;

import dal.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.User;
import utils.MD5;
import utils.Mailer;

/**
 *
 * @author Dell E7450
 */
public class Register extends HttpServlet {

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
            out.println("<title>Servlet Register</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Register at " + request.getContextPath() + "</h1>");
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
        if (request.getSession().getAttribute("admin") != null || request.getSession().getAttribute("staff") != null
                || request.getSession().getAttribute("customer") != null 
                || request.getSession().getAttribute("guest") != null) {

            response.sendRedirect("home");
        } else {
            request.getRequestDispatcher("/view/user/login_register/register.jsp").forward(request, response);
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
        String fullname = request.getParameter("fullname");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        try {
            UserDAO ud = new UserDAO();

            if (email.equals("")) {
                request.setAttribute("msgEmail", "Yêu cầu nhập email");

            } else {
                if (ud.checkUser(email) != null) {
                    request.setAttribute("msgEmail", "Email này đã được đăng ký");
                    email = "";
                } else {
                    String regexgm = "^[\\w.+\\-]+@gmail\\.com$";
                    if (email.matches(regexgm) == false) {
                        request.setAttribute("msgEmail", "Vui lòng bao gồm '@' trong địa chỉ email ");
                        email = "";
                    }
                }
            }

            if (password.equals("")) {
                request.setAttribute("msgPass", "Yêu cầu nhập mật khẩu");
            } else {
                if (password.contains(" ") || password.length() < 6) {
                    request.setAttribute("msgPass", "Yêu cầu mật khẩu có ít nhất 6 kí tự và không chứa khoảng trắng");
                    password = "";
                }
            }

            if (fullname.equals("")) {
                request.setAttribute("msgName", "Yêu cầu nhập họ và tên");
            }

            if (email.equals("") || password.equals("") || fullname.equals("")) {
                request.getRequestDispatcher("/view/user/login_register/register.jsp").forward(request, response);
            } else {
                if (ud.checkUser(email) != null) {
                    request.setAttribute("msgHave", "Email này đã được đăng ký");
                    request.getRequestDispatcher("/view/user/login_register/register.jsp").forward(request, response);
                } else {
                    try {
                        String defaultImg = "https://upload.wikimedia.org/wikipedia/commons/9/99/Sample_User_Icon.png";
                        ud.insertUser(email, new MD5().getMD5Password(password), fullname, defaultImg);

                        User u = ud.getUserByEmail(email);
                        String code = String.valueOf(new Random().nextInt(10000));
                        Mailer.send(email, "Yêu cầu xác thực tài khoản ", "Mã xác thực là: " + code);
                        ud.updateCode(u.getUserID(), code);

                    } catch (NoSuchAlgorithmException ex) {
                        Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    response.sendRedirect("verify?email=" + email + "&action=verify_register");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
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
