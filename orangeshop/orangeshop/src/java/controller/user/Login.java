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
import jakarta.servlet.http.HttpSession;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import model.User;
import utils.MD5;
import utils.Mailer;

/**
 *
 * @author Dell E7450
 */
public class Login extends HttpServlet {

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
            out.println("<title>Servlet UserLogin</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UserLogin at " + request.getContextPath() + "</h1>");
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
        // Log out -> home
        if (request.getSession().getAttribute("admin") != null || request.getSession().getAttribute("staff") != null
                || request.getSession().getAttribute("customer") != null || request.getSession().getAttribute("guest") != null) {
            request.getSession().removeAttribute("admin");
            request.getSession().removeAttribute("staff");
            request.getSession().removeAttribute("customer");
            request.getSession().removeAttribute("guest");
            request.getSession().removeAttribute("User");
            response.sendRedirect("home");
        } else {
            // Log in
            request.getRequestDispatcher("/view/user/login_register/login.jsp").forward(request, response);
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
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String defau_pass = "";
        try {
            try {
                defau_pass = new MD5().getMD5Password("");
                password = new MD5().getMD5Password(password);
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (email.equals(defau_pass)) {
                request.setAttribute("msgEmail", "Yêu cầu nhập email");
            }
            if (password.equals(defau_pass)) {
                request.setAttribute("msgPass", "Yêu cầu nhập mật khẩu");
            }
            if (password.equals(defau_pass) || email.equals(defau_pass)) {
                request.getRequestDispatcher("/view/user/login_register/login.jsp").forward(request, response);
            } else {
                UserDAO ud = new UserDAO();
                User user = ud.getUser(email, password);
                if (user != null) {
                    if (user.getStatus() == 0) {
//                        User u = new UserDAO().getUserByEmail(email);
//                        String code = String.valueOf(new Random().nextInt(10000));
//                        Mailer.send(email, "Yêu cầu xác thực tài khoản OrangeFood", "Mã xác thực là: " + code);
//                        new UserDAO().updateCode(u.getUserID(), code);
//                        response.sendRedirect("verify?email=" + email + "&action=verify_login");
                        request.setAttribute("message", "Tài khoản của bạn đang bị khóa");
                    request.getRequestDispatcher("/view/user/login_register/login.jsp").forward(request, response);
                    } else {
                        HttpSession session = request.getSession();                  
                        session.setMaxInactiveInterval(10000);
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
                        response.sendRedirect("home");
                    }

                } else {
                    request.setAttribute("message", "Email hoặc mật khẩu không chính xác");
                    request.getRequestDispatcher("/view/user/login_register/login.jsp").forward(request, response);
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
