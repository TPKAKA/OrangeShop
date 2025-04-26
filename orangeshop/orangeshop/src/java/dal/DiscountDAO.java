

package dal;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Discount;
import model.Product;
import utils.DBContext;


public class DiscountDAO extends DBContext{
public Discount getDiscountByProductID(int productID)  {

        String sql = "SELECT DiscountID, DiscountPercent, DiscountCode FROM Discount WHERE ProductID = ?";
        try {

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, productID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return new Discount(rs.getInt("DiscountID"), rs.getInt("DiscountPercent"), rs.getString("DiscountCode"));
            }
        } catch (SQLException e) {

        }
        return null;
    }

    public Discount getDiscountByCode(String discountCode) {
    String sql = "SELECT DiscountID, DiscountPercent FROM Discount WHERE DiscountCode = ?";
    try {
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, discountCode);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            int discountId = rs.getInt("DiscountID");
            int discountPercent = rs.getInt("DiscountPercent");
            return new Discount(discountId, discountPercent, discountCode);
        }
    } catch (SQLException e) {
            }
    return null;
}
    }

