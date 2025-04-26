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

/**
 *
 * @author ADMIN
 */
public class RegisterWithPhone extends HttpServlet {

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
            out.println("<title>Servlet RegisterWithPhone</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RegisterWithPhone at " + request.getContextPath() + "</h1>");
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
        request.getRequestDispatcher("/view/user/login_register/register_phone.jsp").forward(request, response);
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
        String phone = request.getParameter("phone");
        String fullname = request.getParameter("fullname");
        try {
            // Kiểm tra và xử lý các trường hợp nhập liệu họ và tên và số điện thoại
            if (fullname.equals("")) {
                // Nếu không nhập họ và tên
                request.setAttribute("msgName", "Yêu cầu nhập họ và tên");
            }
            if (new UserDAO().getAllPhones().contains(phone)) {
                // Nếu số điện thoại tồn tại trong hệ thống
                request.setAttribute("fail", "Số điện thoại đã tồn tại trong hệ thống.");
            } else if (phone.isEmpty()) {
                // Nếu số điện thoại trống
                request.setAttribute("failempty", "Vui lòng nhập số điện thoại.");
            } else if (!phone.matches("\\d{10}")) {
                // Nếu số điện thoại không đúng định dạng 10 chữ số
                request.setAttribute("failformat", "Số điện thoại không hợp lệ. Vui lòng kiểm tra và nhập lại.");
            } else {
                if (fullname.equals("")) {
                    request.setAttribute("msgName", "Yêu cầu nhập họ và tên");
                } else {
                    request.setAttribute("success", "Success");
                }
            }
            // Gán giá trị số điện thoại và họ tên vào thuộc tính của request
            request.setAttribute("phone", phone);
            request.setAttribute("fullname", fullname);
            // Chuyển hướng đến trang "register_phone.jsp" để hiển thị kết quả đăng ký
            request.getRequestDispatcher("/view/user/login_register/register_phone.jsp").forward(request, response);
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
