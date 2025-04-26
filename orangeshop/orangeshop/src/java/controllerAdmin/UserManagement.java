/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllerAdmin;

import dal.AdminDao;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Account;

/**
 *
 * @author ACER
 */
@WebServlet(name = "CustomersManagement", urlPatterns = {"/manageaccount"})

public class UserManagement extends HttpServlet {

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
            out.println("<title>Servlet ManageAccount</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ManageAccount at " + request.getContextPath() + "</h1>");
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
        String txtSearch = request.getParameter("txt");
        String indexPage = request.getParameter("index");

        int index = 1;
        if (indexPage != null) {
            index = Integer.parseInt(indexPage);
        }
        String sort = request.getParameter("sort");

        if (sort == null) {
            List<Account> list = null;
            if (txtSearch == null || txtSearch.isEmpty()) {
                list = dao.getUserbyPaging(index, 3, 10);
            } else {
                int count = dao.getTotalAccountusebuysearch(3, txtSearch);
                int endPage = count / 10;
                if (count % 10 != 0) {
                    endPage++;
                }

                String[] splitResult = txtSearch.split("\\s+");
                String firstPart = splitResult[0];
                list = dao.getUserbyPagingsearch(firstPart, 3, index);
            }

            if (list == null || dao.getTotalAccountusebuysearch(3, txtSearch) == 0) {
                list = dao.getUserbyPaging(index, 3, 10); 
            }

            int count = dao.getTotalAccountuser(3);
            int endPage = count / 10;
            if (count % 10 != 0) {
                endPage++;
            }
            request.setAttribute("endP", endPage);
            request.setAttribute("listA", list);
            request.getRequestDispatcher("customermanagement.jsp").forward(request, response);
        } else {
            int a = Integer.parseInt(sort);
            List<Account> list = null;
            if (txtSearch == null || txtSearch.isEmpty()) {
                list = dao.getUserbyPaging(index, 3, a);
            } else {
                int count = dao.getTotalAccountusebuysearch(3, txtSearch);
                int endPage = count / a;
                if (count % a != 0) {
                    endPage++;
                }

                String[] splitResult = txtSearch.split("\\s+");
                String firstPart = splitResult[0];
                list = dao.getUserbyPagingsearch(firstPart, 3, index);
            }

            if (list == null || dao.getTotalAccountusebuysearch(3, txtSearch) == 0) {
                list = dao.getUserbyPaging(index, 3, a); 
            }

            int count = dao.getTotalAccountuser(3);
            int endPage = count / a;
            if (count % a != 0) {
                endPage++;
            }
            request.setAttribute("endP", endPage);
            request.setAttribute("listA", list);
            request.getRequestDispatcher("customermanagement.jsp").forward(request, response);

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
        AdminDao dao = new AdminDao();
//         String txtSearch = request.getParameter("txt");
        String indexPage = request.getParameter("index");

        int status = 3;
        int userId = Integer.parseInt(request.getParameter("userid"));
        String statuss = request.getParameter("status");
        if (statuss != null) {
            status = Integer.parseInt(statuss);
        }
   
        if (status != 3) {
           
            
            if (indexPage == null) {
                indexPage = "1";
            }
            int index = Integer.parseInt(indexPage);
            List<Account> listp = dao.getUserbyPaging(index, 3, 10);
            int count = dao.getTotalAccountuser(3);
            int endPage = count / 10;   
            if (count % 10 != 0) {
                endPage++;
            }
            request.setAttribute("endP", endPage);
            request.setAttribute("listA", listp);
            request.getAttribute("mess");
             dao.editStatusUser(status, userId);
            request.getRequestDispatcher("customermanagement.jsp").forward(request, response);
        } else {
           dao.deleteAccount(userId);
            
            if (indexPage == null) {
                indexPage = "1";
            }
            int index = Integer.parseInt(indexPage);
            List<Account> listp = dao.getUserbyPaging(index, 2, 10);
            int count = dao.getTotalAccountuser(2);
            int endPage = count / 10;
            if (count % 10 != 0) {
                endPage++;
            }
            request.setAttribute("endP", endPage);
            request.setAttribute("listA", listp);
            request.getAttribute("mess");
            request.getRequestDispatcher("staffmanagement.jsp").forward(request, response);
        }

    }

// Lấy trang hiện tại, nếu không có thì mặc định là 1
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
