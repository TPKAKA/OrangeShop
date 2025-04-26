/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Account;
import utils.DBContext;

/**
 *
 * @author ACER
 */
// Select all user
public class AdminDao extends DBContext {

//    public List<Account> getAllAccount() {
//        List<Account> list = new ArrayList<>();
//        String sql = "SELECT [userid],[fullname]  ,[roleId]   ,[dob]     ,[email]      ,[status]    ,[address] \n"
//                + "  ,[phone]    ,[image] \n"
//                + "  FROM [dbo].[user] FROM [user]";
//        try {
//            PreparedStatement ps = connection.prepareStatement(sql);
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()) {
//
//                list.add( new Account(rs.getInt(1), rs.getString(2), rs.getInt(3),
//                        rs.getString(4), rs.getString(5), rs.getInt(6), rs.getString(7), rs.getString(8), rs.getString(9)));
//            }
//        } catch (SQLException e) {
//            System.out.println(e);
//        }
//        return list;
//    }
    // get all account by user roll 
     public List<Account> getAllAccountByRoleID( int rollid) {
        List<Account> list = new ArrayList<>();
        String sql = "SELECT [userid],[fullname]  ,[roleId]   ,[dob]     ,[email]      ,[status]    ,[address] \n"
                + "  ,[phone]    ,[image] \n"
                + "  FROM [dbo].[user]  where [roleId] = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, rollid);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                list.add( new Account(rs.getInt(1), rs.getString(2), rs.getInt(3),
                        rs.getString(4), rs.getString(5), rs.getInt(6), rs.getString(7), rs.getString(8), rs.getString(9)));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }
    // Update User

    public void editStatusUser(int status, int userid) {
        String sql = "    UPDATE [dbo].[user]\n"
                + "SET [status] = ?\n"
                + "WHERE [userId] = ?;\n"
                + "";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setInt(1, status);

            ps.setInt(2, userid);

            ps.executeUpdate();
        } catch (SQLException e) {
        }
    }

    // Update Rollid 
//    public void editRollidUser(int roleID, int userid) {
//        String sql = "    UPDATE [dbo].[user]\n"
//                + "SET [roleId] = ?\n"
//                + "WHERE [userId] = ?;\n"
//                + "";
//        try {
//            PreparedStatement ps = connection.prepareStatement(sql);
//
//            ps.setInt(1, roleID);
//
//            ps.setInt(2, userid);
//
//            ps.executeUpdate();
//        } catch (SQLException e) {
//        }
//    }
    // update xem email có bị trùng không 
    public Account checkAccountExistByUseridAndEmail(String email, String phone) {
        String sql = "SELECT [userid],[fullname]  ,[roleId]   ,[dob]     ,[email]      ,[status]    ,[address] \n"
                + "  ,[phone]    ,[image] \n"
                + "  FROM [dbo].[user] WHERE [email] = ? OR [phone] = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, email);
            ps.setString(2, phone);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
               return new Account(rs.getInt(1), rs.getString(2), rs.getInt(3),
                        rs.getString(4), rs.getString(5), rs.getInt(6), rs.getString(7), rs.getString(8), rs.getString(9));
            }
        } catch (SQLException e) {
            System.out.println(e); // handle the exception appropriately, don't ignore it
        }
        return null;
    }
   // Select  [email] , [phone]  FROM [user] where userid = 50  
    public Account checkEmailOrPhoneByUserid(int userid) {
        String sql = "Select  [email] , [phone]  FROM [user] where userid = ? ";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setInt(1, userid);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                 return new Account(userid, "",0,
                        "", rs.getString(1),0, "", rs.getString(2),"");
            }
        } catch (SQLException e) {
            System.out.println(e); // handle the exception appropriately, don't ignore it
        }
        return null;
    }
    public Account checkAccountExistByUserid(int userid) {
        String sql = "SELECT [userid],[fullname]  ,[roleId]   ,[dob]     ,[email]      ,[status]    ,[address] \n"
                + "  ,[phone] ,[image] \n"
                + "  FROM [dbo].[user] WHERE [email] = ? OR [phone] = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setInt(1, userid);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                return new Account(rs.getInt(1), rs.getString(2), rs.getInt(3),
                        rs.getString(4), rs.getString(5), rs.getInt(6), rs.getString(7), rs.getString(8), rs.getString(9));
            }
        } catch (SQLException e) {
            System.out.println(e); // handle the exception appropriately, don't ignore it
        }
        return null;
    }

    public void Updateuser( String fullname, String dob, String address, int userid, String fileName) {
        String sql = "UPDATE [dbo].[user]\n"
                + "   SET \n"           
                + "      ,[fullname] = ?\n"
                + "      ,[dob] = ?\n"
                + "      ,[address] = ?\n"
                + "      ,[image] = ?\n"
                + " WHERE userid = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);

           
            ps.setString(1, fullname);
            ps.setString(2, dob);
            ps.setString(3, address);
            ps.setString(4, fileName);
            ps.setInt(5,userid );
            ps.executeUpdate();
        } catch (SQLException e) {
        }
    }
public void Updateusernoimage(String fullname, String dob, String address, int userid) {
    String sql = "UPDATE [dbo].[user]\n"
            + "SET \n"              
            + "    [fullname] = ?,\n"
            + "    [dob] = ?,\n"
            + "    [address] = ?\n"
            + "WHERE userid = ?";
    try {
        PreparedStatement ps = connection.prepareStatement(sql);

        ps.setString(1, fullname);
        ps.setString(2, dob);
        ps.setString(3, address);
        ps.setInt(4, userid);
        ps.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace(); // In ra lỗi nếu có
    }
}

    // insert user
    public void insertUser(String userRoll, String mail, String pass, String phone, String name, String image, String dob, String address) {
        String sql = "INSERT INTO [dbo].[user] "
                + "([roleId], [email], [password], [phone], [fullname], [image], [dob], [address]) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);

            // Điền giá trị cho roleId và password
            ps.setString(1, userRoll);
            ps.setString(2, mail);
            ps.setString(3, pass);
            ps.setString(4, phone);
            ps.setString(5, name);
            ps.setString(6, image);
            ps.setString(7, dob);
            ps.setString(8, address);

            ps.executeUpdate();
        } catch (SQLException e) {

            System.out.println(e);  // Thông báo lỗi ra console hoặc xử lý tùy ý
        }
    }

    public List<Account> getAllAccountByid(int userid) {
        List<Account> list = new ArrayList<>();
        String sql = "SELECT [userid],[fullname]  ,[roleId]   ,[dob]     ,[email]      ,[status]    ,[address] \n"
                + "  ,[phone]    ,[image] \n"
                + "  FROM [dbo].[user] where userid =?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, userid);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                list.add(new Account(rs.getInt(1), rs.getString(2), rs.getInt(3),
                        rs.getString(4), rs.getString(5), rs.getInt(6), rs.getString(7), rs.getString(8), rs.getString(9)));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    //Delete News vi no dinh khoa ngoại bang nay 
//    public void deleteNews(int userId) {
//        String sql = "DELETE FROM [dbo].[News]\n"
//                + "      WHERE userid = ?";
//        try {
//            PreparedStatement ps = connection.prepareStatement(sql);
//            ps.setInt(1, userId);
//
//            ps.executeQuery();
//
//        } catch (SQLException e) {
//        }
//    }
    //Delete Account 

    public void deleteAccount(int userId) {
        String sql = "DELETE FROM [dbo].[user]\n"
                + "      WHERE userid = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, userId);

            ps.executeQuery();

        } catch (SQLException e) {
        }
    }

    // Search by name
    public List<Account> searchByName(String txtSearch) {
        List<Account> list = new ArrayList<>();
        String sql = "SELECT [userid],[fullname]  ,[roleId]   ,[dob]     ,[email]      ,[status]    ,[address] \n"
                + "  ,[phone]    ,[image] \n"
                + "  FROM [dbo].[user] Where [fullname] like ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, "%" + txtSearch + "%"); // Đặt giá trị cho tham số trước khi thực hiện truy vấn để ý cxhoxo này

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
               list.add(new Account(rs.getInt(1), rs.getString(2), rs.getInt(3),
                        rs.getString(4), rs.getString(5), rs.getInt(6), rs.getString(7), rs.getString(8), rs.getString(9)));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    // Upload fILE 
    public boolean updateUserAvatar(InputStream inputStream, int userID) {
        String sql = "UPDATE [dbo].[user]\n"
                + "   SET \n"
                + "     [image] = '?'\n"
                + "      \n"
                + " WHERE userid = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setBlob(1, inputStream);
            ps.setInt(2, userID);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
        }

        return false;
    }

    // getTotal Account by user or staff 
    public int getTotalAccountuser(int rol) {
        String sql = "Select count(*) from [user] where [roleId] = ?";
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

    // get Account by paging by staff or user
    public List<Account> getUserbyPaging(int index, int rol, int page) {
        List<Account> list = new ArrayList<>();
        String sql = "SELECT [userid],[fullname]  ,[roleId]   ,[dob]     ,[email]      ,[status]    ,[address] \n"
                + "  ,[phone]    ,[image] \n"
                + "  FROM [dbo].[user] WHERE [roleId] = ?\n"
                + "               ORDER BY userid \n"
                + "              OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, rol);
            ps.setInt(2, (index - 1) * page);
            ps.setInt(3, page);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

               list.add(new Account(rs.getInt(1), rs.getString(2), rs.getInt(3),
                        rs.getString(4), rs.getString(5), rs.getInt(6), rs.getString(7), rs.getString(8), rs.getString(9)));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;

    }

    public List<Account> ls = new ArrayList<>();

    public Account findByID(int userID) {
        for (Account info : ls) {
            if (info.getUserid() == userID) {
                return info;
            }
        }
        return null;
    }
// Validate Email or phone
    public boolean checkEmailOrPhone(String emailOrPhone) {
        if (!emailOrPhone.matches("\\w+@\\w+[.]\\w")) {  // @gmasil.com
            if (!emailOrPhone.matches("\\d{9,11}")) {
                return false;
            }
        }
        return true;
    }
    // count user bypaging 

    public int getTotalAccountusebuysearch(int rol, String txt) {
        String sql = "Select count(*) from [user] where ([roleId] = ? and ([fullname] LIKE ? OR [email] LIKE ? OR [phone] LIKE ?))";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, rol);
            ps.setString(2, "%" + txt + "%");
            ps.setString(3, "%" + txt + "%");
            ps.setString(4, "%" + txt + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
        }
        return 0;
    }
     // Paging by search 
   public List<Account> getUserbyPagingsearch(String txt, int rol, int index) {
    List<Account> list = new ArrayList<>();
    String sql = "SELECT [userid], [fullname], [roleId], [dob], [email], [status], [address], [phone], [image] "
               + "FROM [dbo].[user] "
               + "WHERE ([fullname] LIKE ? OR [email] LIKE ? OR [phone] LIKE ?) AND [roleId] = ? "
               + "ORDER BY userid "
               + "OFFSET ? ROWS FETCH NEXT 10 ROWS ONLY";
    try (PreparedStatement ps = connection.prepareStatement(sql)) {
        ps.setString(1, "%" + txt + "%");
        ps.setString(2, "%" + txt + "%");
        ps.setString(3, "%" + txt + "%");
        ps.setInt(4, rol);
        ps.setInt(5, (index - 1) * 10);
        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new Account(rs.getInt(1), rs.getString(2), rs.getInt(3),
                        rs.getString(4), rs.getString(5), rs.getInt(6), rs.getString(7), rs.getString(8), rs.getString(9)));
            }
        }
    } catch (SQLException e) {
        e.printStackTrace(); // In ra toàn bộ stack trace của lỗi
    }
    return list;
}
//    public static void main(String[] args) {
//        AdminDao dao = new AdminDao();
//        Account account = dao.checkAccountExistByUseridAndEmail(48, "thangpvhe171431@fpt.edu.vn", "123213");
//        
//        if (account != null) {
//            System.out.println(account);
//        } else {
//            System.out.println("Account not found.");
//        }
//    }
//    public static void main(String[] args) {
//        AdminDao dao = new AdminDao();
//        List<Account>list = dao.getAllAccountByRoleID(3);
//       for(Account o : list)
//        System.out.println(o);
//
//    }
//    
//    public static void main(String[] args) {
//        AdminDao dao = new AdminDao();
//        dao.deleteAccount(126);
//    }

}
