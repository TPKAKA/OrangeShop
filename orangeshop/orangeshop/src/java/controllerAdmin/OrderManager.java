package controllerAdmin;

import dal.OrderDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.Locale;
import model.Orders;

@WebServlet(name = "OrderManager", urlPatterns = {"/ordermanager"})
public class OrderManager extends HttpServlet {

    private OrderDAO orderDAO; // Đối tượng OrderDAO

    @Override
    public void init() throws ServletException {
        super.init();
        // Khởi tạo orderDAO với kết nối tới cơ sở dữ liệu
        orderDAO = new OrderDAO(/*Thêm thông tin kết nối cơ sở dữ liệu ở đây*/);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Orders> list = new ArrayList<>();

        String index1 = request.getParameter("index1");
        String txtSearch = request.getParameter("txt");

        int indexPage = 1; // Giá trị mặc định cho indexPage

        // Kiểm tra và chuyển đổi giá trị của index1 thành số nguyên
        if (index1 != null && !index1.isEmpty()) {
            try {
                indexPage = Integer.parseInt(index1);
            } catch (NumberFormatException e) {
                // Xử lý ngoại lệ nếu giá trị không hợp lệ
                e.printStackTrace();
                // Có thể cung cấp thông báo lỗi hoặc redirect về trang khác
            }
        }

        // Lấy danh sách đơn hàng từ cơ sở dữ liệu
        if (txtSearch != null && !txtSearch.isEmpty()) {
            // Nếu có văn bản tìm kiếm được cung cấp, lấy đơn hàng theo tên
            list = orderDAO.getOrderbyName(txtSearch);
        } else {
            // Nếu không có văn bản tìm kiếm được cung cấp, lấy đơn hàng cho phân trang
            list = orderDAO.getOrderPaging(indexPage);
        }

        // Định dạng ngày tháng trước khi gửi đến jsp
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE, dd MMMM yyyy");
        for (Orders orders : list) {
            
            if (orders.getDate() instanceof java.util.Date) {
                String formattedDate = sdf.format(orders.getDate());
                orders.setFormattedDate(formattedDate);
            } else {
                // Xử lý khi order.getDate() không phải là đối tượng Date
                // Ví dụ: không làm gì hoặc thông báo lỗi
            }
        }

        request.setAttribute("listA1", list);
        request.getRequestDispatcher("orderdone.jsp").forward(request, response);
    }

    public static String convertToVietnameseDong(double totalamount) {
        Locale locale = new Locale("vi", "VN");
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
        Currency vietnameseDong = Currency.getInstance("VND");
        currencyFormatter.setCurrency(vietnameseDong);
        return currencyFormatter.format(totalamount);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Lấy thông tin từ yêu cầu
        String orderIdStr = request.getParameter("orderId");
        String status = request.getParameter("status");

        // Kiểm tra null cho orderId và status
        if (orderIdStr != null && status != null) {
            try {
                int orderId = Integer.parseInt(orderIdStr);
                // Kiểm tra orderId có hợp lệ không (ví dụ: là số dương)
                if (orderId > 0) {
                    // Gọi phương thức updateOrderStatus từ orderDAO
                    boolean success = orderDAO.updateOrderStatus(orderId, status);

                    // Xử lý kết quả
                    if (success) {
                        // Trạng thái đã được cập nhật thành công
                        response.getWriter().write("Order status updated successfully");
                    } else {
                        // Xảy ra lỗi khi cập nhật trạng thái
                        response.getWriter().write("Failed to update order status");
                    }
                } else {
                    response.getWriter().write("Invalid orderId");
                }
            } catch (NumberFormatException e) {
                response.getWriter().write("Invalid orderId format");
            }
        } else {
            response.getWriter().write("One or more parameters are null");
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
