/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.math.BigDecimal;
import java.sql.Date;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;

/**
 *
 * @author Admin
 */
public class Orders {

    private int orderid;
    private String fullname;
    private Date date;
    private double totalamount;
    private int statuspayment;
    private String statusorder;
    public User user;
    private Orderitem orderitem;
    private Product product;
    private Image image;
    private String formattedDate;
    public static String convertToVietnameseDong(double totalamount) {
        Locale locale = new Locale("vi", "VN");
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
        Currency vietnameseDong = Currency.getInstance("VND");
        currencyFormatter.setCurrency(vietnameseDong);
        return currencyFormatter.format(totalamount);
    }

    public Orders() {
    }

    public Orders(int orderid, String fullname, Date date, double totalamount, int statuspayment, String statusorder, User user, Orderitem orderitem, Product product, Image image) {
        this.orderid = orderid;
        this.fullname = fullname;
        this.date = date;
        this.totalamount = totalamount;
        this.statuspayment = statuspayment;
        this.statusorder = statusorder;
        this.user = user;
        this.orderitem = orderitem;
        this.product = product;
        this.image = image;
    }

    public Orders(int aInt, String string, Date date, BigDecimal bigDecimal, int aInt0, int aInt1) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public int getOrderid() {
        return orderid;
    }

    public void setOrderid(int orderid) {
        this.orderid = orderid;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getTotalamount() {
        return totalamount;
    }

    public void setTotalamount(double totalamount) {
        this.totalamount = totalamount;
    }

    public int getStatuspayment() {
        return statuspayment;
    }

    public void setStatuspayment(int statuspayment) {
        this.statuspayment = statuspayment;
    }

    public String getStatusorder() {
        return statusorder;
    }

    public void setStatusorder(String statusorder) {
        this.statusorder = statusorder;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Orderitem getOrderitem() {
        return orderitem;
    }

    public void setOrderitem(Orderitem orderitem) {
        this.orderitem = orderitem;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public String getFormattedDate() {
        return formattedDate;
    }

    public void setFormattedDate(String formattedDate) {
        this.formattedDate = formattedDate;
    }

    @Override
    public String toString() {
        return "Orders{" + "orderid=" + orderid + ", fullname=" + fullname + ", date=" + date + ", totalamount=" + totalamount + ", statuspayment=" + statuspayment + ", statusorder=" + statusorder + ", user=" + user + ", orderitem=" + orderitem + ", product=" + product + ", image=" + image + '}';
    }

    

}
