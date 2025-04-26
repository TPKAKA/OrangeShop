/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package productController;

import dal.DiscountDAO;
import dal.ShopDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.Cart;
import model.Discount;
import model.Image;
import model.Item;
import model.Product;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "Cart", urlPatterns = {"/Cart"})
public class CartControl extends HttpServlet {

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
        response.setContentType("application/json");

        HttpSession session = request.getSession(true);
        String action = request.getParameter("action");
        if (action == null) {

            model.Cart cart = null;
            Object o = session.getAttribute("cart");
            if (o != null) {
                cart = (model.Cart) o;
            } else {
                cart = new model.Cart();
            }
            String Squantity = request.getParameter("quantity");
            String productid = request.getParameter("productid");

            try {
                int quantity = Integer.parseInt(Squantity);
                ShopDAO pdao = new ShopDAO();
                Product product = pdao.getProductById(productid);
                Image image = pdao.getImgByProductId(productid);
                Item item = new Item(product, image, quantity);
                cart.addItem(item);

            } catch (Exception e) {
            }
            List<Item> list = cart.getItems();
            session.setAttribute("cart", cart);
            session.setAttribute("total", cart.getTotalMoney());
            session.setAttribute("size", list.size());

            response.sendRedirect("shop-details.jsp");

        } if (action != null && action.equalsIgnoreCase("add")) { 
            model.Cart cart = null;
            Object o = session.getAttribute("cart");
            if (o != null) {
                cart = (model.Cart) o;
            } else {
                cart = new model.Cart();
            }
            String productid = request.getParameter("productid");

            try {
                ShopDAO pdao = new ShopDAO();
                Product product = pdao.getProductById(productid);
                Image image = pdao.getImgByProductId(productid);
                Item item = new Item(product, image, 1);
                cart.addItem(item);

            } catch (Exception e) {
            }
            List<Item> list = cart.getItems();
            session.setAttribute("cart", cart);
            session.setAttribute("total", cart.getTotalMoney());
            session.setAttribute("size", list.size());

            response.sendRedirect("page");
        }if (action != null && action.equalsIgnoreCase("addnow")) { 
            model.Cart cart = null;
            Object o = session.getAttribute("cart");
            if (o != null) {
                cart = (model.Cart) o;
            } else {
                cart = new model.Cart();
            }
            String productid = request.getParameter("productid");

            try {
                ShopDAO pdao = new ShopDAO();
                Product product = pdao.getProductById(productid);
                Image image = pdao.getImgByProductId(productid);
                Item item = new Item(product, image, 1);
                cart.addItem(item);

            } catch (Exception e) {
            }
            List<Item> list = cart.getItems();
            session.setAttribute("cart", cart);
            session.setAttribute("total", cart.getTotalMoney());
            session.setAttribute("size", list.size());

            response.sendRedirect("checkout");
        }
        if (action != null && action.equalsIgnoreCase("showcart")) {                   
            response.sendRedirect("shoping-cart.jsp");

        }

        if (action != null && action.equals("deletecart")) {
            model.Cart cart = null;
            Object o = session.getAttribute("cart");
            if (o != null) {
                cart = (model.Cart) o;
            } else {
                cart = new model.Cart();
            }
            int productid = Integer.parseInt(request.getParameter("productid"));
            cart.removeItem(productid);
            List<Item> list = cart.getItems();
            session.setAttribute("cart", cart);
            session.setAttribute("total", cart.getTotalMoney());
            session.setAttribute("size", list.size());
            response.sendRedirect("shoping-cart.jsp");
        }
        if (action != null && action.equals("updateCart")) {
           model.Cart cart = null;
            Object o = session.getAttribute("cart");
            if (o != null) {
                cart = (model.Cart) o;
            } else {
                cart = new model.Cart();
            }
            String Squantity = request.getParameter("quantity").trim();
           String Sproductid = request.getParameter("productid");
           int quantity,productid;
           try{
               productid = Integer.parseInt(Sproductid);
               quantity = Integer.parseInt(Squantity);
               if((quantity == -1)&&(cart.getQuantityById(productid)<=1)){
                   cart.removeItem(productid);
               }else{
                   ShopDAO dao = new ShopDAO();
                   Product p = dao.getProductById(Sproductid);
                   Image i = dao.getImgByProductId(Sproductid);
                   Item t = new Item(p, i, quantity);
                   cart.addItem(t);
               }
           }catch(NumberFormatException e){
               System.out.println(e);
           }
            List<Item> list = cart.getItems();
            session.setAttribute("cart", cart);
            session.setAttribute("total", cart.getTotalMoney());
            session.setAttribute("size", list.size());
              response.sendRedirect("shoping-cart.jsp");
              
        }if (action != null && action.equals("discount")) {
           String discountCode = request.getParameter("discountCode");
            try {
                DiscountDAO discountDAO = new DiscountDAO();
                Discount discount = discountDAO.getDiscountByCode(discountCode);
        if (discount != null) {
    
    Cart cart = (Cart) request.getSession().getAttribute("cart");
    if (cart != null) {
        double discountPercent = discount.getDiscountPercent();
        double total = cart.getTotalMoney();
        double discountAmount = total * discountPercent / 100.0;
        double newTotal = total - discountAmount;
        
       
        session.setAttribute("total", newTotal);
        
       
        request.setAttribute("discountPercent", discountPercent);
        request.getRequestDispatcher("shoping-cart.jsp").forward(request, response);
        
        response.sendRedirect("shoping-cart.jsp");
    } 
} else {
    request.setAttribute("errorMessage", "Discount code does not exist");
    request.getRequestDispatcher("shoping-cart.jsp").forward(request, response);
}

            } catch (Exception e) {
                
            }
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
