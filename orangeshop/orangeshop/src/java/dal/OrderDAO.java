package dal;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import model.Cart;
import model.Image;
import model.Item;
import model.Orders;
import model.Product;
import model.User;
import utils.DBContext;

public class OrderDAO extends DBContext {

    PreparedStatement ps = null;
    ResultSet rs = null;

    public void addOrder(User u, Cart cart, int statuspayment, String statusorder, String phone, String address) {
        LocalDate curDate = java.time.LocalDate.now();
        String date = curDate.toString();
        try {
            // Thêm dữ liệu vào bảng Order
            String sql = "INSERT INTO [dbo].[Orders] VALUES (?, ?, ?, ?, ?,?,?)";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, u.getUserID());
            ps.setString(2, date);
            ps.setDouble(3, cart.getTotalMoney());
            ps.setInt(4, statuspayment);
            ps.setString(5, statusorder);
            ps.setString(6, phone);
            ps.setString(7, address);
            ps.executeUpdate();

// Lấy ID của Order vừa thêm
            String sqlGetOrderId = "SELECT TOP 1 orderid FROM Orders ORDER BY orderid DESC";
            ps = connection.prepareStatement(sqlGetOrderId);
            rs = ps.executeQuery();
            if (rs.next()) {
                int orderid = rs.getInt("orderid"); // Lấy orderid từ kết quả truy vấn

                // Thêm dữ liệu vào bảng Orderitems
                for (Item i : cart.getItems()) {
                    String sqlInsertOrderItem = "INSERT INTO [Orderitems] VALUES (?, ?, ?, ?)";
                    ps = connection.prepareStatement(sqlInsertOrderItem);
                    ps.setInt(1, orderid);
                    ps.setInt(2, i.getProduct().getProductid());
                    ps.setInt(3, i.getQuantity());
                    double total = i.getQuantity() * i.getProduct().getPrice();
                    ps.setDouble(4, total);
                    ps.executeUpdate();
                }
            }

            String sql3 = "update Products set totalproduct = totalproduct - ?\n"
                    + "                    where productid = ?";
            ps = connection.prepareStatement(sql3);
            for (Item i : cart.getItems()) {
                ps.setInt(1, i.getQuantity());
                ps.setInt(2, i.getProduct().getProductid());
                ps.executeUpdate();
            }

        } catch (SQLException e) {

        }
    }

    public int getOrderItemIDbyUser(int rol) {
        String sql = "SELECT TOP 1 o.orderid \n"
                + "FROM [user] a\n"
                + "JOIN Orders b ON b.customerid = a.userid\n"
                + "JOIN Orderitems o ON o.orderid = b.orderid  \n"
                + "WHERE a.userid = ?\n"
                + "ORDER BY o.orderitemid DESC;";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, rol);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
        }
        return 0;
    }

    public void updateUserField(int userId, String fieldName, String value) throws SQLException {
        String sql = "UPDATE [user] SET " + fieldName + " = ? WHERE userid = ?";
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, value);
            ps.setInt(2, userId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
    }

    public void getOrderIDDesc(int userid) {
        try {
            String sqlGetOrderIDDesc = "SELECT TOP 1 orderid FROM Orders WHERE customerid = ? ORDER BY orderid DESC;";
            ps = connection.prepareStatement(sqlGetOrderIDDesc);
            ps.setInt(1, userid);
            rs = ps.executeQuery();

            // Fetch the result from ResultSet
            int orderiddesc = 0;
            if (rs.next()) {
                orderiddesc = rs.getInt("orderid");
            } else {
                // Handle the case when no order is found
                // For now, you can just return, or throw an exception, or handle it in another appropriate way
                return;
            }

            String sqlUpdate = "UPDATE Orders SET statuspayment = 1 WHERE orderid = ?";
            ps = connection.prepareStatement(sqlUpdate);
            ps.setInt(1, orderiddesc); // Using the obtained order ID
            ps.executeUpdate(); // executeUpdate() for UPDATE queries

            // Close resources (PreparedStatement and ResultSet) properly
            rs.close();
            ps.close();
        } catch (SQLException e) {
            // Handle the SQLException
            e.printStackTrace(); // Print or log the Nameexception, or handle it in another appropriate way
        }
    }

    public List<Orders> getOrder() {
        String sql = "SELECT o.orderid, u.fullname AS customer_name, o.orderdate, o.totalamount, o.statuspayment, o.statusorder\n"
                + "FROM Orders o\n"
                + "INNER JOIN [user] u ON o.customerid = u.userid ";
        List<Orders> orderrs = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Orders orderssss = new Orders();
                orderssss.setOrderid(rs.getInt("orderid"));
                User user = new User();
                user.setFullName(rs.getString("customer_name"));
                orderssss.setUser(user);
                orderssss.setDate(rs.getDate("orderdate"));
                orderssss.setTotalamount(rs.getDouble("totalamount"));
                orderssss.setStatuspayment(rs.getInt("statuspayment"));
                orderssss.setStatusorder(rs.getString("statusorder"));
                // Thêm orders vào danh sách order
                orderrs.add(orderssss);
            }

        } catch (SQLException e) {

        }

        return orderrs;
    }

    public List<Orders> getOrderbyName(String txtSearch) {
        String sql = "SELECT o.orderid, u.fullname AS customer_name, o.orderdate, o.totalamount, o.statuspayment, o.statusorder\n"
                + "FROM Orders o\n"
                + "INNER JOIN [user] u ON o.customerid = u.userid\n"
                + "WHERE u.fullname LIKE ?";
        List<Orders> order = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, "%" + txtSearch + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Orders orders = new Orders();
                orders.setOrderid(rs.getInt("orderid"));
                User user = new User();
                user.setFullName(rs.getString("customer_name"));
                // Đặt tên người dùng cho đối tượng đơn hàng
                orders.setUser(user);
                orders.setDate(rs.getDate("orderdate"));
                orders.setTotalamount(rs.getDouble("totalamount"));
                orders.setStatuspayment(rs.getInt("statuspayment"));
                orders.setStatusorder(rs.getString("statusorder"));
                // Thêm orders vào danh sách order
                order.add(orders);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return order;
    }

    public List<Orders> getOrderPaging(int index1) {
        String sql = "SELECT o.orderid, u.fullname AS customer_name, o.orderdate, o.totalamount, o.statuspayment, o.statusorder\n"
                + "FROM Orders o\n"
                + "INNER JOIN [user] u ON o.customerid = u.userid\n"
                + "ORDER BY orderid\n"
                + "OFFSET ? ROWS\n"
                + "FETCH FIRST 10 ROWS ONLY;";
        List<Orders> order = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, (index1 - 1) * 10);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Orders orders = new Orders();
                orders.setOrderid(rs.getInt("orderid"));
                User user = new User();
                user.setFullName(rs.getString("customer_name"));
                orders.setUser(user);
                orders.setDate(rs.getDate("orderdate"));
                orders.setTotalamount(rs.getDouble("totalamount"));
                orders.setStatuspayment(rs.getInt("statuspayment"));
                orders.setStatusorder(rs.getString("statusorder"));
                // Thêm orders vào danh sách order
                order.add(orders);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return order;
    }

    public int getNumberPage() {
        String sql = "select count(*) from Orders";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int total = rs.getInt(1);
                int countPage = 0;
                countPage = total / 10;
                if (total % 10 != 0) {
                    countPage++;

                }
                return countPage;
            }
        } catch (Exception e) {
        }
        return 0;
    }

    public List<Orders> getOrderbyCusID(int customerid) {
        String sql = "SELECT o.orderid, o.orderdate, o.totalamount, p.[name] AS product_name, i.imglink, o.statuspayment, u.address "
                + "FROM [dbo].[Orders] AS o "
                + "JOIN [dbo].[user] u ON o.customerid = u.userid "
                + "JOIN [dbo].[Orderitems] AS oi ON o.orderid = oi.orderid "
                + "JOIN [dbo].[Products] p ON oi.foodid = p.productid "
                + "JOIN [dbo].imglink i ON p.productid = i.productid "
                + "WHERE u.userid = ?";
        List<Orders> cid = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, customerid);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int orderId = rs.getInt("orderid");
                Date orderDate = rs.getDate("orderdate");
                double totalAmount = rs.getDouble("totalamount");
                String productName = rs.getString("product_name");
                String imgLink = rs.getString("imglink");
                int statusPayment = rs.getInt("statuspayment");
                String address = rs.getString("address");

                Orders order = new Orders();
                order.setOrderid(orderId);
                order.setDate(orderDate);
                order.setTotalamount(totalAmount);
                order.setStatuspayment(statusPayment);

                User user = new User();
                user.setAddress(address);
                order.setUser(user);

                // Assuming you want to include product details in the Order object
                Product product = new Product();
                product.setName(productName);
                Image img = new Image();
                img.setImglink(imgLink);
                order.setProduct(product);

                cid.add(order);
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Print error if any
        }

        return cid;
    }

    public List<Orders> getOrderbyOrderID(int orderid) {
        String sql = "SELECT \n"
                + "    o.orderdate,\n"
                + "    o.totalamount,\n"
                + "    p.[name] AS product_name,\n"
                + "    p.productid  AS product_id,\n"
                + "    i.imglink,\n"
                + "    o.statuspayment,\n"
                + "    u.address,\n"
                + "    u.phone \n"
                + "FROM \n"
                + "    [dbo].[Orders] AS o \n"
                + "JOIN \n"
                + "    [dbo].[user] u ON o.customerid = u.userid \n"
                + "JOIN \n"
                + "    [dbo].[Orderitems] AS oi ON o.orderid = oi.orderid \n"
                + "JOIN \n"
                + "    [dbo].[Products] p ON oi.foodid = p.productid \n"
                + "JOIN \n"
                + "    [dbo].[imglink] i ON p.productid = i.productid \n"
                + "WHERE \n"
                + "    o.orderid = ? \n"
                + "ORDER BY \n"
                + "    oi.orderid DESC;";
        List<Orders> oid = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, orderid);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Date orderDate = rs.getDate("orderdate");
                double totalAmount = rs.getDouble("totalamount");
                String productName = rs.getString("product_name");
                int productid  = rs.getInt("product_id");
                String imgLink = rs.getString("imglink");
                int statusPayment = rs.getInt("statuspayment");
                String address = rs.getString("address");
                String phone = rs.getString("phone"); // Lấy giá trị phone từ ResultSet

                // Tạo đối tượng Orders
                Orders order = new Orders();
                order.setOrderid(orderid);
                order.setDate(orderDate);
                order.setTotalamount(totalAmount);
                order.setStatuspayment(statusPayment);

                // Tạo đối tượng User
                User user = new User();
                user.setAddress(address);
                user.setPhone(phone); 
                order.setUser(user);

                // Tạo đối tượng Product
                Product product = new Product();
                product.setName(productName);
                Image img = new Image();
                img.setImglink(imgLink);
                order.setProduct(product);
                product.setProductid(productid);

                oid.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return oid;
    }

    public boolean updateOrderStatus(int orderid, String statusorder) {
        String sql = "UPDATE Orders SET statusorder = ? WHERE orderid = ?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, statusorder);
            pstmt.setInt(2, orderid);
            int rowsUpdated = pstmt.executeUpdate();
            pstmt.close();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Double getTodayRevenue(String type) {
        Double revenue = 0.0;
        String sql = "SELECT ISNULL(SUM(totalamount), 0)\n"
                + "FROM [dbo].[Orders]\n"
                + "WHERE orderdate = CAST(GETDATE() AS DATE)";
        if (type.equals("actual")) {
            sql += " AND statusorder = 'Approved'";
        }
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                revenue = rs.getDouble(1);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return revenue;
    }

    public Integer getTodayOrders(String type) {
        Integer orders = 0;
        String sql = "SELECT COUNT(*)\n"
                + "FROM [dbo].[Orders]\n"
                + "WHERE orderdate = CAST(GETDATE() AS DATE)";
        if (type.equals("completed")) {
            sql += " AND statusorder = 'Approved'";
        }
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                orders = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return orders;
    }

//public void updateUserPhon(int userId, String value) throws SQLException {
//    String sql = "UPDATE [user] SET phone = ? WHERE userid = ?";
//    PreparedStatement ps = null;
//    try {
//        ps = connection.prepareStatement(sql);
//        ps.setString(1, value);
//        ps.setInt(2, userId);
//        ps.executeUpdate();
//    } catch (SQLException e) {
//        e.printStackTrace();
//    } finally {
//        if (ps != null) {
//            ps.close();
//        }
//    }
//}
//public void updateUserAddress(int userId, String value) throws SQLException {
//    String sql = "UPDATE [user] SET  a = ? WHERE userid = ?";
//    PreparedStatement ps = null;
//    try {
//        ps = connection.prepareStatement(sql);
//        ps.setString(1, value);
//        ps.setInt(2, userId);
//        ps.executeUpdate();
//    } catch (SQLException e) {
//        e.printStackTrace();
//    } finally {
//        if (ps != null) {
//            ps.close();
//        }
//    }
//}
//public void updateUserField(int userId, String fieldName, String value) throws SQLException {
//    String sql = "UPDATE [user] SET " + fieldName + " = ? WHERE userid = ?";
//    PreparedStatement ps = null;
//    try {
//        ps = connection.prepareStatement(sql);
//        ps.setString(1, value);
//        ps.setInt(2, userId);
//        ps.executeUpdate();
//    } catch (SQLException e) {
//        e.printStackTrace();
//    } finally {
//        if (ps != null) {
//            ps.close();
//        }
//    }
//}
//
//
//    public List<Order> getOrderInfo() {
//        List<Order> list = new ArrayList<>();
//        String sql = "SELECT o.orderid, u.fullname, o.orderdate, o.totalamount, o.statuspayment\n"
//                + "                FROM Orders o \n"
//                + "               INNER JOIN [user] u ON o.customerid = u.userid";
//        try {
//            ps = connection.prepareStatement(sql);
//            rs = ps.executeQuery();
//            while (rs.next()) {
//                User u = new User(rs.getString(1), rs.getString(2), rs.getString(3));
//                
//                list.add(new Order(rs.getInt(1), u, rs.getDate(3), rs.getDouble(4), rs.getInt("statuspayment"), rs.getString(6)));
//            }
//        } catch (Exception e) {
//        }
//        return list;
//    }
////
//    public Order getOrder() {
//        List<Order> list = new ArrayList<>();
//        String sql = "select top 1 orderid, orderdate, totalamount from [Orders] order by orderid desc";
//        try {
//            ps = connection.prepareStatement(sql);
//            rs = ps.executeQuery();
//            while (rs.next()) {
//                return (new Order(rs.getInt("orderid"), rs.getDate("date"), rs.getDouble("totalamount")));
//            }
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//        return null;
//    }
//
//    public List<Orderitem> getDetail(int orderid) {
//        List<Orderitem> list = new ArrayList<>();
//        String sql = " SELECT d.orderitemid, p.productid, p.name, i.imglink, d.quantity, d.subtotal\n"
//                + "                FROM Orderitems d \n"
//                + "                INNER JOIN Products p ON d.foodid = p.productid \n"
//                + "                INNER JOIN imglink i ON i.productid = p.productid \n"
//                + "                WHERE d.orderid = ?";
//        try {
//            ps = connection.prepareStatement(sql);
//            ps.setInt(1, orderid);
//            rs = ps.executeQuery();
//            while (rs.next()) {
//                Product p = new Product(rs.getInt(1), rs.getString(2));
//                Image i = new Image(rs.getString("imglink"));
//                list.add(new Orderitem(rs.getInt(1), p, rs.getInt(4), rs.getDouble(5)));
//            }
//        } catch (Exception e) {
//        }
//        return list;
//    }
//
//    public void updatePayment(int orderid, int statuspayment) {
//        String sql = "update Orders set statuspayment= ? where orderid = ?";
//        try {
//
//            ps = connection.prepareStatement(sql);
//            ps.setInt(2, orderid);
//            ps.setInt(1, statuspayment);
//            ps.executeUpdate();
//        } catch (Exception e) {
//        }
//    }
//
//    public List<Order> getOrderByID(int user_id) {
//        List<Order> list = new ArrayList<>();
//        String sql = "SELECT o.orderid, u.address, o.orderdate, o.totalamount, statuspayment\n"
//                + "                FROM Orders o\n"
//                + "                INNER JOIN [user] u ON o.customerid = u.userid\n"
//                + "               WHERE o.customerid = ?";
//        try {
//
//            ps = connection.prepareStatement(sql);
//            ps.setInt(1, user_id);
//            rs = ps.executeQuery();
//            while (rs.next()) {
//                User u = new User(rs.getString(1), rs.getString(2), rs.getString(3));
//                list.add(new Order(rs.getInt(1), u, rs.getDate(3), rs.getDouble(4), rs.getInt("satuspayment")));
//            }
//        } catch (Exception e) {
//        }
//        return list;
//    }
//
//    public List<Order> getOrderByDay() {
//        List<Order> list = new ArrayList<>();
//        String sql = "select o.orderid, u.fullname,u.phone,u.address,o.orderdate,o.totalamount,statuspayment from Orders o \n"
//                + "               inner join [user] u on o.customerid = u.userid \n"
//                + "              where orderdate = cast(getdate() as Date)";
//        try {
//            ps = connection.prepareStatement(sql);
//            rs = ps.executeQuery();
//            while (rs.next()) {
//                User u = new User(rs.getString(1), rs.getString(2), rs.getString(3));
//
//                list.add(new Order(rs.getInt(1), u, rs.getDate(3), rs.getDouble(4), rs.getInt("statuspayment")));
//            }
//        } catch (Exception e) {
//        }
//        return list;
//    }
    public static void main(String[] args) {
        OrderDAO dao = new OrderDAO();
//        dao.getOrderIDDesc(48);
        List<Orders> list = dao.getOrderbyCusID(48);
        for (Orders o : list) {
            System.out.println(o);
        }
    }
}
