/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import model.User;
import model.UserRole;
import utils.DBContext;

/**
 *
 * @author ADMIN
 */
public class UserDAO extends DBContext {

    PreparedStatement ps = null;
    ResultSet rs = null;

    public User getUser(String email, String password) throws SQLException {
        try {
            String sql = "SELECT * FROM [user] join userrole "
                    + "On [user].roleId = userrole.roleid "
                    + "Where email = ? and password = ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, password);
            rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5),
                        rs.getString(6), rs.getString(7), rs.getString(8),
                        rs.getDate(9), rs.getString(10), rs.getInt(11), rs.getString(12), rs.getString(13), rs.getInt(14),
                        rs.getString(15), rs.getString(16), new UserRole(rs.getInt(17), rs.getString(18)));
                return user;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
        }
        return null;
    }

    public User getUserByEmail(String email) throws SQLException {
        try {
            String sql = "SELECT [userid]\n"
                    + ",[user].[roleId]\n"
                    + ",[email]\n"
                    + ",[facebook]\n"
                    + ",[password]\n"
                    + ",[phone]\n"
                    + ",[fullname]\n"
                    + ",[image]\n"
                    + ",[dob]\n"
                    + ",[address]\n"
                    + ",[addressId]\n"
                    + ",[gmailid]\n"
                    + ",[facebookid]\n"
                    + ",[status]\n"
                    + ",[codeverify]\n"
                    + ",[bio]\n"
                    + ",[userrole].[roleid]\n"
                    + ",[userrole].[name]\n"
                    + "FROM [user] join userrole ON [user].roleId = userrole.RoleID Where email = ?";
            ps = connection.prepareStatement(sql);

            ps.setString(1, email);
            rs = ps.executeQuery();
            if (rs.next()) {
                User u = new User(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5),
                        rs.getString(6), rs.getString(7), rs.getString(8),
                        rs.getDate(9), rs.getString(10), rs.getInt(11), rs.getString(12), rs.getString(13), rs.getInt(14),
                        rs.getString(15), rs.getString(16), new UserRole(rs.getInt(17), rs.getString(18)));
                return u;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
        }
        return null;
    }

    public int insertSocialUser(User u) throws SQLException {
        int kt = 0;
        try {
            String sql = "INSERT INTO [user] ( Email, Password, FullName, Image, Dob, \n"
                    + " GmailID, Status, RoleID ) \n"
                    + "VALUES ( ?, ?, ?, ?, ?, ?, \n"
                    + " 1, 3)";
            ps = connection.prepareStatement(sql);
            ps.setString(1, u.getEmail());
            ps.setString(2, u.getPassword());
            ps.setString(3, u.getFullName());
            ps.setString(4, u.getImage());
            ps.setString(5, u.getDob().toString());
            ps.setString(6, u.getGmailID());
            kt = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
        return kt;
    }

    public void insertUser(String email, String password, String fullname, String defaultImg) throws SQLException {
        try {
            String sql = "INSERT INTO [user] \n"
                    + "(roleId, email, password, fullName, image, status )\n"
                    + "VALUES ( 3, ?, ?, ?, ?, 0)";

            ps = connection.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, password);
            ps.setString(3, fullname);
            ps.setString(4, defaultImg);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
    }

    public void insertUserByPhone(String phone, String password, String fullname, String defaultImg) throws SQLException {
        try {
            String sql = "INSERT INTO [user] (  RoleID,Password, Phone,  FullName, \n"
                    + "Image,Status ) \n"
                    + "VALUES ( 3, ?, ?, \n"
                    + " ?,?, 1)";
            ps = connection.prepareStatement(sql);
            ps.setString(1, phone);
            ps.setString(2, password);
            ps.setString(3, fullname);
            ps.setString(4, defaultImg);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
    }

    public User checkUser(String email) throws SQLException {
        try {
            String sql = "SELECT * FROM [user] join userrole\n"
                    + "ON [user].roleId = userrole.roleid where email = ?";

            ps = connection.prepareStatement(sql);
            ps.setString(1, email);
            rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5),
                        rs.getString(6), rs.getString(7), rs.getString(8),
                        rs.getDate(9), rs.getString(10), rs.getInt(11), rs.getString(12), rs.getString(13), rs.getInt(14),
                        rs.getString(15), rs.getString(16), new UserRole(rs.getInt(17), rs.getString(18)));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return null;
    }

    public void UpdateUserStatus(int userid, String status) throws SQLException {
        String sql = "update [user] set status = ? where userid = ?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(2, userid);
            ps.setString(1, status);
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
    }

    public void updateCode(int userid, String code) throws SQLException {
        String sql = " update [user] set codeverify = ? where userid = ?";

        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(2, userid);
            ps.setString(1, code);
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
    }

    public String getCode(int userid) throws SQLException {
        String sql = "Select codeverify from [user] where userid = ?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, userid);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return null;
    }

    public void updatePass(String email, String pass) throws SQLException {
        String sql = "update [user] set Password = ? where Email like ?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, pass);
            ps.setString(2, email);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
    }

    public int UpdateUserAfterLoginGG(User u) throws SQLException {
        int k = 0;
        try {
            String sql = "UPDATE [user] SET Image = ?, GmailID = ?, Status = ? WHERE UserId = " + u.getUserID();
            ps = connection.prepareStatement(sql);
            ps.setString(1, u.getImage());
            ps.setString(2, u.getGmailID());
            ps.setInt(3, u.getStatus());
            k = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
        return k;
    }

    public static void main(String[] args) {
        UserDAO userDAO = new UserDAO();
        try {
            // Test with an existing email
            String existingEmail = "geminithe2@gmail.com";
            User user = userDAO.getUserByEmail(existingEmail);
            if (user != null) {
                System.out.println("User found for email " + existingEmail + ": " + user);
            } else {
                System.out.println("No user found for email " + existingEmail);
            }

            // Test with a non-existing email
            String nonExistingEmail = "nonexisting@example.com";
            User user2 = userDAO.getUserByEmail(nonExistingEmail);
            if (user2 != null) {
                System.out.println("User found for email " + nonExistingEmail + ": " + user2);
            } else {
                System.out.println("No user found for email " + nonExistingEmail);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User getUser(int UserID) throws SQLException {
        try {
            String sql = "SELECT * FROM [user] join userrole "
                    + "On [user].roleId = userrole.RoleID "
                    + "and UserID = ?";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, UserID);
            rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5),
                        rs.getString(6), rs.getString(7), rs.getString(8),
                        rs.getDate(9), rs.getString(10), rs.getInt(11), rs.getString(12), rs.getString(13), rs.getInt(14),
                        rs.getString(15), rs.getString(16), new UserRole(rs.getInt(17), rs.getString(18)));
                return user;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return null;
    }

    public User getUserbyUserID(String UserID) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT * FROM [user] join userrole "
                    + "On [user].roleId = userrole.RoleID "
                    + "and UserID = ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, UserID);
            rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5),
                        rs.getString(6), rs.getString(7), rs.getString(8),
                        rs.getDate(9), rs.getString(10), rs.getInt(11), rs.getString(12), rs.getString(13), rs.getInt(14),
                        rs.getString(15), rs.getString(16), new UserRole(rs.getInt(17), rs.getString(18)));
                return user;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return null;
    }

    public void UpdateLogin(String FullName,String Address,String Image, String UserID) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "UPDATE [user] SET FullName = ?, \n"
                    + "                     Address = ?, \n"
                    + "                    Image = ? \n"
                    + "                    WHERE UserID = ? ";
            ps = connection.prepareStatement(sql);
            ps.setString(1, FullName);
            ps.setString(2, Address);
            ps.setString(3, Image);
            ps.setString(4, UserID);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
    }

    public void UpdateLoginWithoutAvatar( String FullName,String Address, String UserID) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "UPDATE [user] SET FullName = ?, \n"
                    + "                     Address = ?, \n"
                    + "                    WHERE UserID = ? ";
            ps = connection.prepareStatement(sql);
            ps.setString(1, FullName);
            ps.setString(2, Address);
            ps.setString(3, UserID);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
    }

    public User getUserByPhone(String phone) throws SQLException {
        try {
            String sql = "SELECT * FROM [user] join userrole ON [user].RoleID = userrole.RoleID and Phone = ? ";
            ps = connection.prepareStatement(sql);
            ps.setString(1, phone);
            rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5),
                        rs.getString(6), rs.getString(7), rs.getString(8),
                        rs.getDate(9), rs.getString(10), rs.getInt(11), rs.getString(12), rs.getString(13), rs.getInt(14),
                        rs.getString(15), rs.getString(16), new UserRole(rs.getInt(17), rs.getString(18)));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return null;
    }

    public void deleteUserByUserID(String UserID) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "DELETE FROM [user] where UserID =?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, UserID);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
        }
    }

    public int UpdateUserProfile(User u) throws SQLException {
        int k = 0;
        try {
            String sql = "update [user] set Phone=?,FullName=? where UserID = " + u.getUserID();
            ps = connection.prepareStatement(sql);
            ps.setString(1, u.getPhone());
            ps.setString(2, u.getFullName());
            ps.setInt(3, u.getUserID());
            k = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
        return k;
    }

    public List<String> getAllPhones() throws SQLException {
        List<String> list = new ArrayList<>();
        String sql = "Select Phone from [user] where Phone is not null and Phone != ''";
        try {
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(rs.getString(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return list;
    }

    public List<User> getAllUser() throws SQLException {
        List<User> list = new ArrayList<>();
        String sql = "SELECT * FROM [user]";
        try {
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new User(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5),
                        rs.getString(6), rs.getString(7), rs.getString(8),
                        rs.getDate(9), rs.getString(10), rs.getInt(11), rs.getString(12), rs.getString(13), rs.getInt(14),
                        rs.getString(15), rs.getString(16), new UserRole(rs.getInt(17), rs.getString(18))));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
        }
        return list;
    }
    
    public Map<User, Double> getLoyalCustomer(int numb) {
        Map<User, Double> map = new LinkedHashMap<>();
        String sql = "SELECT TOP(?) fullname, email, [dbo].[user].phone, SUM(totalamount) AS spend\n"
                + "FROM [dbo].[user]\n"
                + "JOIN [dbo].[Orders] ON [dbo].[Orders].customerid = [dbo].[user].userid\n"
                + "GROUP BY fullname, email, [dbo].[user].phone\n"
                + "ORDER BY spend DESC";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, numb);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setFullName(rs.getString("fullname"));
                user.setEmail(rs.getString("email"));
                user.setPhone(rs.getString("phone"));
                map.put(user, rs.getDouble("spend"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return map;
    }

}
