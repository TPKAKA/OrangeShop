/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import model.Account;
import model.News;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Customer;
import model.Image;
import model.Product;
import model.ProductCategory;
import model.ShopInformation;
import utils.DBContext;

/**
 *
 * @author Admin
 */
public class HomeDAO extends DBContext {
    // get all account

    public List<Account> getAllAccount() {
        String sql = "SELECT * FROM [user] where [roleId] = 3;";
        List<Account> ac = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Account acc = new Account(rs.getInt(1), rs.getString(2), rs.getInt(3),
                        rs.getString(4), rs.getString(5), rs.getInt(6), rs.getString(7), rs.getString(8), rs.getString(9));
                ac.add(acc); 
            }

        } catch (SQLException e) {

        }

        return ac;
    }
    // get all img 

    public List<Image> getAllIMG() {
        String sql = "SELECT * from imglink";
        List<Image> image = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Image img = new Image(rs.getInt("imgid"), rs.getString("imglink"), rs.getInt("productid"));
                image.add(img);
            }

        } catch (SQLException e) {

        }

        return image;
    }
    // get news latest

     public List<News> getBanner(int groupid) {
        String sql = "SELECT [ID]\n"
                + "      ,[Userid]\n"
                + "      ,[content]\n"
                + "      ,[image]\n"
                + "      ,[groupId]\n"
                + "      ,[Newsname]\n"
                + "  FROM [DONETHAT9].[dbo].[News] WHERE [groupId] = ?";
        List<News> list = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, groupid);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                News n = new News(rs.getInt(1), rs.getString(6), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getInt(2));
                list.add(n);
            }

        } catch (SQLException e) {

        }

        return list;
    }
// get order Product

    public List<Product> getAllProducts() {
        String sql = "SELECT TOP 8 * from Products where status = 1";
        List<Product> products = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                 Product product = new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getInt(5),rs.getInt(6));
                products.add(product);
            }

        } catch (SQLException e) {

        }

        return products;
    }

    // get Product by Categoryid
    public List<ProductCategory> getAllCategoryID() {
        String sql = "SELECT * from Categories";
        List<ProductCategory> cat = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ProductCategory cate = new ProductCategory(rs.getInt(1), rs.getString(2));
                cat.add(cate);
            }

        } catch (SQLException e) {
            System.out.println(e);
        }

        return cat;
    }

    public List<Product> getProductCategories(int categoryid) {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM Products where categoryid = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, categoryid);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product product = new Product(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getInt(5),rs.getInt(6));
                products.add(product);
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
        return products;
    }

    public List<Customer> getAllCustomer() {
        String sql = "SELECT * from Customers ";
        List<Customer> customer = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Customer cust = new Customer(rs.getInt(1), rs.getInt(2), rs.getString(3));
                customer.add(cust);
            }

        } catch (SQLException e) {

        }

        return customer;
    }

    public List<ShopInformation> getAllShop() {
        String sql = "select  * from ShopInfomation";
        List<ShopInformation> shop = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ShopInformation sh = new ShopInformation(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6));
                shop.add(sh);
            }

        } catch (SQLException e) {

        }

        return shop;
    }

    public static void main(String[] args) {
        HomeDAO dao = new HomeDAO();
        List<ShopInformation> list = dao.getAllShop();
        for (ShopInformation o : list) {
            System.out.println(o);
        }
    }
}
