package productController;

import dal.OrderDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Cart;
import model.User;

public class CheckoutControl extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        try {
            HttpSession session = request.getSession(true);
            OrderDAO dao = new OrderDAO();

            Cart cart = (Cart) session.getAttribute("cart");
            User user = (User) session.getAttribute("customer");
            String address = request.getParameter("address");
            String phone = request.getParameter("phone");
            String email = request.getParameter("email");
            if (user != null) {
                if (user.getAddress() == null && address != null && !address.isEmpty()) {
                    dao.updateUserField(user.getUserID(), "address", address);
                    user.setAddress(address);
                    session.setAttribute("customer", user); // Cập nhật thông tin người dùng trong session
                }
                if (user.getPhone() == null && phone != null && !phone.isEmpty()) {
                    dao.updateUserField(user.getUserID(), "phone", phone);
                    user.setPhone(phone);
                     session.setAttribute("customer", user);
                }
                if (user.getEmail() == null && email != null && !email.isEmpty()) {
                    dao.updateUserField(user.getUserID(), "email", email);
                    user.setEmail(email);
                }
            }
            if (cart != null && user != null) {
                dao.addOrder(user, cart, 0, "processing", phone, address);
                session.removeAttribute("cart");
                response.sendRedirect("vnpay.jsp");
            } else {
                response.sendRedirect("login");
            }
        } catch (Exception e) {
            e.printStackTrace();
           
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        User acc = (User) session.getAttribute("customer");
        if (acc != null) {
            request.getRequestDispatcher("checkout.jsp").forward(request, response);
        } else {
            response.sendRedirect("login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
