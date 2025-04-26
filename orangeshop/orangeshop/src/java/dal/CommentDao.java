/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Comments;
import utils.DBContext;

/**
 *
 * @author Dell E7450
 */

public class CommentDao extends DBContext{
    PreparedStatement ps = null;
    ResultSet rs = null;
    
    public List<Comments> getAllComment(String productid) throws SQLException {
        List<Comments> AllComment = new ArrayList<>();
        String sql ="SELECT \n" +
"    c.commentid,\n" +
"    u.Fullname AS UserFullname,\n" +
"    u.Image AS UserImage,\n" +
"    c.details,\n" +
"    c.vote,\n" +
"    c.level,\n"+ 
"    c.origin_comment_id,\n" +
"    c.cmt_image,\n" +
"    ru.Fullname AS ReplyUserFullname,\n" +
"    ru.Image AS ReplyUserImage\n" +
"FROM \n" +
"    Comment c\n" +
"INNER JOIN \n" +
"    [user] u ON c.userId = u.userId\n" +
"LEFT JOIN \n" +
"    [user] ru ON c.replyId = ru.userId\n" +
"INNER JOIN\n" +
"    Products p ON c.product_comment = p.productId\n" +
"WHERE \n" +
"    p.productId = ?;" ;
                try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, productid);
            rs = ps.executeQuery();
            while (rs.next()) {
                Comments b = new Comments(rs.getInt(1), rs.getInt(2), rs.getInt(3),
                         rs.getString(4), rs.getInt(5), rs.getInt(6), rs.getInt(7),rs.getInt(8), rs.getString(9));

                AllComment.add(b);
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
        return AllComment;
    }
    public int insertComment(String id, int userID, String comment) throws SQLException {
        
        String sql = "INSERT INTO Comment VALUES ( ?, ?, ?,null, 1, ?,null, null)";
        try {
            ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, id);
            ps.setInt(2, userID);
            ps.setString(3, comment);
            ps.setInt(4, userID);
            ps.executeUpdate();
            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                return generatedKeys.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Handle the exception appropriately
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
        return 0;
    }
    public void updateOrigin(int id) throws SQLException {
        String sql = "UPDATE Comment SET comment.origin_comment_id = ? WHERE Comment.commentId = ?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            // Handle the exception appropriately
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
    }

    public void insertReply(String id, int userID, String comment, int id_comment, int reply_to) throws SQLException {
        String sql = "INSERT INTO Comment VALUES (0, ?, ?, 2, ?, ?, ?)";
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, id);
            ps.setInt(2, userID);
            ps.setInt(3, id_comment);
            ps.setInt(4, reply_to);
            ps.setString(5, comment);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            // Handle the exception appropriately
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
    }
//    public static void main(String[] args) {
//        try {
//            new CommentDao().insertComment(2, 122, "hahahaha");
//        } catch (SQLException ex) {
//            Logger.getLogger(CommentDao.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
}
