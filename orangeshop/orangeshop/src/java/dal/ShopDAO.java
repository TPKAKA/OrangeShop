package dal;

import java.math.BigDecimal;
import model.ProductCategory;
import model.Image;
import model.Product;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import utils.DBContext;

public class ShopDAO extends DBContext {

    public List<Product> getAllProduct() {
        List<Product> list = new ArrayList<>();
        String sql = "select productid, name, description, price, categoryid, totalproduct from products ";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getInt(5), rs.getInt(6)));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public List<Image> getAllIMG() {
        List<Image> image = new ArrayList<>();
        String sql = "select * from imglink;";

        try {

            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                image.add(new Image(rs.getInt("imgid"), rs.getString("imglink"), rs.getInt("productid")));

            }

        } catch (SQLException e) {

        }

        return image;
    }

    public List<ProductCategory> getAllCategory() {
        List<ProductCategory> listC = new ArrayList<>();
        String sql = "select *  from Categories";
        try {

            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listC.add(new ProductCategory(rs.getInt(1), rs.getString(2)));
            }
        } catch (SQLException e) {

        }
        return listC;
    }

    public Product getLast() {
        String sql = "select top 1 * from products\n"
                + "order by productid desc";
        try {

            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getInt(5), rs.getInt(6));
            }
        } catch (SQLException e) {

        }
        return null;
    }

    public List<Product> getProductByCategory(String categoryid, int index) {
        List<Product> list = new ArrayList<>();
        String sql = " SELECT productid, name, description, price, categoryid, totalproduct \n" +
"                FROM Products \n" +
"               WHERE categoryid = ? AND status = 1\n" +
"                ORDER BY productid\n" +
"                OFFSET ? ROWS FETCH NEXT 12 ROWS ONLY ";
        try {

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, categoryid);
            ps.setInt(2, (index - 1) * 12);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getInt(5), rs.getInt(6)));
            }
        } catch (SQLException e) {

        }
        return list;
    }

    public Product getProductById(String productid) {

        String sql = "select productid, name, description, price, categoryid, totalproduct from Products where productid = ?";
        try {

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, productid);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getInt(5), rs.getInt(6));
            }
        } catch (SQLException e) {

        }
        return null;
    }
    
    public ProductCategory getCategoriesByCateId(String cateID){
        String sql = "Select *  from Categories where categoryid = ?";
        try {

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, cateID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return new ProductCategory(rs.getInt(1), rs.getString(2));
            }
        } catch (SQLException e) {

        }
      return null;  
    }

    public Image getImgByProductId(String productid) {

        String sql = "SELECT * from imglink where productid = ?";
        try {

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, productid);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return new Image(rs.getInt("imgid"), rs.getString("imglink"), rs.getInt("productid"));
            }
        } catch (SQLException e) {

        }
        return null;
    }

    public ProductCategory getCategoryById(String categoryid) {

        String sql = "select * from Categories where categoryid = ?;";
        try {

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, categoryid);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return new ProductCategory(rs.getInt(1), rs.getString(2));
            }
        } catch (SQLException e) {

        }
        return null;
    }

    public ProductCategory getCategoryNameByProductId(String productid) {
        String sql = "SELECT c.categoryid, c.categoryname\n"
                + "FROM Products p\n"
                + "JOIN Categories c ON p.categoryid = c.categoryid\n"
                + "WHERE p.productid = ?;";

        try {

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, productid);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int categoryid = rs.getInt("categoryid");
                String categoryname = rs.getString("categoryname");
                return new ProductCategory(categoryid, categoryname);
            }
        } catch (SQLException e) {

        }

        return null;
    }

//    public List<Product> searchByName(String txtSearch) {
//        List<Product> list = new ArrayList<>();
//        String query = "select productid, name, description, price, categoryid from Products where [name] like ? ;";
//        try {
//            
//            PreparedStatement ps = connection.prepareStatement(query);
//            ps.setString(1, "%" + txtSearch + "%");
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()) {
//                list.add(new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getInt(5)));
//            }
//        } catch (Exception e) {
//
//        }
//        return list;
//    }
    
     public List<Product> searchByName(String txtSearch) {
        String[] newsWords = txtSearch.split("\\s+");
        List<Product> list = new ArrayList<>();
        String sql = "SELECT productid, name, description, price, categoryid FROM Products WHERE name LIKE ? AND status= 1";
        for (int i = 1; i < newsWords.length; i++) {
            sql += " UNION " + "SELECT productid, name, description, price, categoryid FROM Products WHERE name LIKE ?";
        }
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            int paramIndex = 1;
            for (String s : newsWords) {
                ps.setString(paramIndex++, "%" + s + "%");
            }

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getInt(5)));
            }

        } catch (SQLException e) {

        }

        return list;
    }

    public List<Product> getProductsSorted(boolean ascending, int index, int ProductPerpage) {
        List<Product> list = new ArrayList<>();
        String sortOrder = ascending ? "ASC" : "DESC";
        String sql = "select productid, name, description, price, categoryid, totalproduct from products where status = 1 ORDER BY price " + sortOrder + " OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, (index - 1) * ProductPerpage);
            ps.setInt(2, ProductPerpage);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getInt(5), rs.getInt(6)));
            }
        } catch (SQLException e) {
            // Handle SQLException
        }
        return list;
    }

    public List<Product> getProductAZ(int index, int ProductPerPage) {
        List<Product> list = new ArrayList<>();
        String sql = "select productid, name, description, price, categoryid, totalproduct from products where status = 1 ORDER BY name Offset ? rows fetch next ? rows only ";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, (index - 1) * ProductPerPage);
            ps.setInt(2, ProductPerPage);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getInt(5), rs.getInt(6)));
            }
        } catch (SQLException e) {

        }
        return list;
    }
// dem so luong product

    public int getTotalProduct() {
        String sql = "select count(*) from Products";
        try {

            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {

        }
        return 0;
    }

    public int getTotalProductByCate(String categoryid) {
        String sql = "SELECT count(*) \n"
                + "FROM Products \n"
                + "WHERE categoryid = ?\n";
        try {

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, categoryid);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {

        }
        return 0;
    }

    public List<Product> PageProducts(int index, int ProductPerPage) {
        List<Product> list = new ArrayList<>();
        String sql = "select productid, name, description, price, categoryid, totalproduct from products where status = 1 order by productid\n" +
"               Offset ? rows fetch next ? rows only ";
        try {

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, (index - 1) * ProductPerPage);
            ps.setInt(2,ProductPerPage);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getInt(5), rs.getInt(6)));
            }
        } catch (SQLException e) {

        }
        return list;
    }
//    public String getTotalPrice(int orderID) {
//        String sql = "SELECT \n"
//                + "      [totalamount]\n"
//                + "  FROM [dbo].[Orders]\n"
//                + "  where orderid = ? ";
//        String res = "";
//        try {
//            PreparedStatement ps = connection.prepareStatement(sql);
//            ps.setInt(1, orderID);
//            ResultSet rs = ps.executeQuery();
//            Locale locale = new Locale("vi", "VN");
//            NumberFormat number = NumberFormat.getCurrencyInstance(locale);
//            while (rs.next()) {
//                res = number.format(Double.parseDouble(rs.getString("TotalPrice")));
//            }
//
//        } catch (Exception ex) {
//            System.out.println("Get TotalPrice: " + ex.getMessage());
//        }
//        return res;
//
//    }

    public String getTotalProductPrice(int orderID) {
        String sql = "SELECT subtotal AS TotalPrice FROM [dbo].[Orderitems] WHERE orderid = ?";
        String res = "";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, orderID);
            ResultSet rs = ps.executeQuery();
            Locale locale = new Locale("vi", "VN");
            NumberFormat number = NumberFormat.getCurrencyInstance(locale);
            while (rs.next()) {
                double totalPrice = rs.getDouble("TotalPrice");
                res = number.format(totalPrice);
            }
        } catch (Exception ex) {
            System.out.println("Get TotalPrice: " + ex.getMessage());
        }
        return res;
    }
    
    public void changeStatusProduct(int id) {
        String sql = "UPDATE [dbo].[Products]\n"
                + "   SET [status] = ((SELECT [status] FROM [dbo].[Products] WHERE [productid] = ?) + 1) % 2\n"
                + " WHERE [productid] = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            st.setInt(2, id);
            ResultSet rs = st.executeQuery();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    public int getMinCategoryId() {
        int id = 0;
        String sql = "SELECT MIN([categoryid]) FROM [dbo].[Categories]";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return id;
    }
    
    public int maxProductPageManagement(int categoryId, int recordPerPageManagement) {
        int numOfRow = 0;
        String sql = "SELECT COUNT(*) AS TOTALROWS FROM [dbo].[Products]\n"
                + "WHERE [categoryid] = ?;";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, categoryId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                numOfRow = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        int numOfPage = (numOfRow + recordPerPageManagement - 1) / recordPerPageManagement;
        return numOfPage;
    }

    public Map<Product, Integer> getProductManagement(int categoryId, int page, int recordPerPage) {
        Map<Product, Integer> map = new LinkedHashMap<>();
        String sql = "SELECT [productid]\n"
                + "      ,[name]\n"
                + "      ,[price]\n"
                + "      ,[totalproduct]\n"
                + "	  ,[status]\n"
                + "      ,COALESCE(SUM([quantity]), 0) AS sold\n"
                + "FROM [dbo].[Products] a\n"
                + "LEFT JOIN [dbo].[Orderitems] b ON a.productid = b.foodid\n"
                + "WHERE [categoryid] = ?\n"
                + "GROUP BY [productid], [name], [price], [totalproduct], [status]\n"
                + "ORDER BY a.[productid] DESC\n"
                + "OFFSET ? ROWS\n"
                + "FETCH NEXT ? ROWS ONLY;";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, categoryId);
            ps.setInt(2, (page - 1) * recordPerPage);
            ps.setInt(3, recordPerPage);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setProductid(rs.getInt("productid"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getDouble("price"));
                product.setTotalproduct(rs.getInt("totalproduct"));
                product.setStatus(rs.getBoolean("status"));
                map.put(product, rs.getInt(6));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return map;
    }

    public Map<Product, Integer> searchProductManagement(String productName) {
        String[] newsWords = productName.split("\\s+");
        Map<Product, Integer> map = new LinkedHashMap<>();
        String sql = "SELECT a.*, COALESCE(SUM([quantity]), 0) AS sold, COUNT(*) AS totalOccurance FROM(\n";
        for (int i = 0; i < newsWords.length; i++) {
            sql += "SELECT [productid],[name],[price],[totalproduct],[status]\n"
                    + "FROM [dbo].[Products] a\n"
                    + "WHERE [name] LIKE N'%" + newsWords[i] + "%'\n";
            if (i < newsWords.length - 1) {
                sql += "UNION ALL \n";
            }
        }
        sql += ") AS a\n"
                + "LEFT JOIN Orderitems b ON b.foodid = a.productid\n"
                + "GROUP BY [productid],[name],[price],[totalproduct],[status]\n"
                + "ORDER BY totalOccurance DESC";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                p.setProductid(rs.getInt("productid"));
                p.setName(rs.getString("name"));
                p.setPrice(rs.getDouble("price"));
                p.setTotalproduct(rs.getInt("totalproduct"));
                p.setStatus(rs.getBoolean("status"));
                map.put(p, rs.getInt("sold"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return map;
    }
    
    public List<Image> getImageByProductId(int id) {
        List<Image> list = new ArrayList<>();
        String sql = "SELECT [imgid]\n"
                + "      ,[imglink]\n"
                + "      ,[productid]\n"
                + "FROM [DONETHAT].[dbo].[imglink]\n"
                + "WHERE [productid] = ?";
        try {

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Image(rs.getInt(1), rs.getString(2), rs.getInt(3)));
            }
        } catch (SQLException e) {

        }
        return list;
    }

    public void addProduct(int category, String name, String description, BigDecimal price, int addQuantity) {
        String sql = "INSERT INTO [dbo].[Products]\n"
                + "           ([categoryid]\n"
                + "		   ,[name]\n"
                + "           ,[description]\n"
                + "           ,[price]\n"
                + "           ,[totalproduct])\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, category);
            st.setString(2, name);
            st.setString(3, description);
            st.setBigDecimal(4, price);
            st.setInt(5, addQuantity);
            st.executeQuery();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public int getMaxProductId() {
        String sql = "SELECT MAX(productid) FROM Products";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return 0;
    }

    public void addProductImages(int productId, String imageName) {
        String sql = "INSERT INTO [dbo].[imglink]\n"
                + "           ([imglink]\n"
                + "           ,[productid])\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, imageName);
            st.setInt(2, productId);
            st.executeQuery();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void updateProduct(int productId, int category, String name, String description, BigDecimal price, int addQuantity) {
        String sql = "UPDATE [dbo].[Products]\n"
                + "   SET [name] = ?\n"
                + "      ,[description] = ?\n"
                + "      ,[price] = ?\n"
                + "      ,[categoryid] = ?\n"
                + "      ,[totalproduct] = ((SELECT [totalproduct] FROM Products WHERE [productid] = ?) + ?\n"
                + " WHERE [productid] = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, name);
            st.setString(2, description);
            st.setBigDecimal(3, price);
            st.setInt(4, category);
            st.setInt(5, productId);
            st.setInt(6, addQuantity);
            st.setInt(6, productId);
            st.executeQuery();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void deleteOldProductImages(int productId) {
        String sql = "DELETE FROM [dbo].[imglink]\n"
                + "      WHERE [productid] = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, productId);
            st.executeQuery();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    public Map<Product, Integer> topSellingProduct(int numb) {
        Map<Product, Integer> map = new LinkedHashMap<>();
        String sql = "SELECT TOP(?) productid, name, SUM(quantity) AS sold\n"
                + "FROM [dbo].[Products]\n"
                + "JOIN [dbo].[Orderitems] ON [dbo].[Orderitems].foodid = [dbo].[Products].productid\n"
                + "JOIN [dbo].[Orders] ON [dbo].[Orders].orderid = [dbo].[Orderitems].orderid\n"
                + "WHERE YEAR(orderdate) = YEAR(GETDATE()) AND MONTH(orderdate) = MONTH(GETDATE())\n"
                + "GROUP BY productid, name\n"
                + "ORDER BY sold DESC";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, numb);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setProductid(rs.getInt("productid"));
                product.setName(rs.getString("name"));
                map.put(product, rs.getInt("sold"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return map;
    }
    
    public List<ProductCategory> searchProductCategoryByName(String productCategoryName) {
        String[] words = productCategoryName.split("\\s+");
        List<ProductCategory> list = new ArrayList<>();
        String sql = "SELECT *\n"
                + "FROM [dbo].[Categories]\n"
                + "WHERE ";
        for(int i = 0; i < words.length; i++){
            sql += " [categoryname] LIKE N'%" + words[i] + "%'\n";
            if(i < words.length - 1)
                sql += " OR \n";
        }
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(new ProductCategory(rs.getInt(1), rs.getString(2)));
            }
        } catch (SQLException e) {

        }
        return list;
    }
    
    public ProductCategory getProductCategoryById(int id) {
        String sql = "SELECT *\n"
                + "FROM [dbo].[Categories]\n"
                + "WHERE [categoryid] = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return new ProductCategory(rs.getInt(1), rs.getString(2));
            }
        } catch (SQLException e) {

        }
        return null;
    }
    
    public void addProductCategory(String addProductCategory) {
        String sql = "INSERT INTO [dbo].[Categories] ([categoryname]) VALUES (?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, addProductCategory);
            st.executeQuery();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    public void updateProductCategory(int id, String productCategoryName) {
        String sql = "UPDATE [dbo].[Categories]\n"
                + "   SET [categoryname] = ?\n"
                + " WHERE [categoryid] = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, productCategoryName);
            st.setInt(2, id);
            st.executeQuery();
        } catch (SQLException e) {

        }
    }

    
//    public static void main(String[] args) throws Exception {
//        ShopDAO dao = new ShopDAO();
//        List<Products> list = dao.PageProducts(2);
//        List<Categories> listC = dao.getAllCategory();
//        for (Product p : list) {
//        System.out.println(p);
//         }
//       int count = dao.getTotalProduct();
//        System.out.println(count/12);
//
//        }
//        ProductCategory result = dao.getCategoryNameByProductId("1");
//        System.out.println("Category ID: " + result.getCategoryid());
//        System.out.println("Category Name: " + result.getCategoryname());
//    }
    public static void main(String[] args) {
       ShopDAO dao = new ShopDAO();
     ProductCategory a   =dao.getCategoriesByCateId("2");
        System.out.println(a);
        
    }
}
