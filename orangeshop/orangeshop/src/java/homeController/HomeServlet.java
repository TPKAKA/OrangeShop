/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package homeController;

import dal.HomeDAO;
import dal.OrderDAO;
import model.Account;
import model.Image;
import model.News;
import model.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import model.ProductCategory;
import model.Customer;
import model.ShopInformation;
import model.User;

/**
 *
 * @author Admin
 */
@WebServlet(name = "HomeServlet", urlPatterns = {"/home"})
public class HomeServlet extends HttpServlet {

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
        HomeDAO productDAO = new HomeDAO();
        List<Product> product = productDAO.getAllProducts();
    
    
        request.getParameter("");
        request.setAttribute("pr", product);
        HttpSession session = request.getSession();
        User customer = (User) session.getAttribute("customer");
        if (customer != null) {
            int a = customer.getUserID();
            String vnp_ResponseCode = request.getParameter("vnp_ResponseCode");
            if (vnp_ResponseCode != null && vnp_ResponseCode.equals("00")) {
                OrderDAO orderDAO = new OrderDAO();
                orderDAO.getOrderIDDesc(a);
                String messs = "Your payment was updated";
                request.setAttribute("messs", messs);
            }
        }

        List<Image> image = productDAO.getAllIMG();
        request.setAttribute("img", image);

        List<News> news = productDAO.getBanner(1);
        request.setAttribute("n", news);

        List<News> menu = productDAO.getBanner(16);
        request.setAttribute("m", menu);

        List<News> time = productDAO.getBanner(17);
        request.setAttribute("time", time);
        
        List<News> address = productDAO.getBanner(18);
        request.setAttribute("address", address);
        
        List<News> mail = productDAO.getBanner(19);
        request.setAttribute("mail", mail);
        
        List<News> phone = productDAO.getBanner(20);
        request.setAttribute("phone", phone);

        List<Account> ac = productDAO.getAllAccount();
        request.setAttribute("a", ac);

        List<ProductCategory> cat = productDAO.getAllCategoryID();
        request.setAttribute("c", cat);

        List<Customer> cust = productDAO.getAllCustomer();
        request.setAttribute("cus", cust);

        List<ShopInformation> sh = productDAO.getAllShop();
        request.setAttribute("shop", sh);      
        String mess = request.getParameter("mess");
        if(mess!= null){
            mess = "Access denied";
                   request.setAttribute("mess", mess);
        }
 
//         List<Product> productbycate = productDAO.getProductCategories(int cate);
//        request.setAttribute("pcate", productbycate);
        request.getRequestDispatcher("index.jsp").forward(request, response);
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
        processRequest(request, response);
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
