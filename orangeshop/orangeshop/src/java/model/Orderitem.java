

package model;


import java.math.BigDecimal;


public class Orderitem {
private int orderitemid;
private int orderid;
Product product;
private int quantity;
private double subtotal;

    public Orderitem() {
    }

    public Orderitem(int orderitemid, int orderid, Product product, int quantity, double subtotal) {
        this.orderitemid = orderitemid;
        this.orderid = orderid;
        this.product = product;
        this.quantity = quantity;
        this.subtotal = subtotal;
    }

    public Orderitem(int orderitemid, Product product, int quantity, double subtotal) {
        this.orderitemid = orderitemid;
        this.product = product;
        this.quantity = quantity;
        this.subtotal = subtotal;
    }

    public int getOrderitemid() {
        return orderitemid;
    }

    public void setOrderitemid(int orderitemid) {
        this.orderitemid = orderitemid;
    }

    public int getOrderid() {
        return orderid;
    }

    public void setOrderid(int orderid) {
        this.orderid = orderid;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    @Override
    public String toString() {
        return "Orderitem{" + "orderitemid=" + orderitemid + ", orderid=" + orderid + ", product=" + product + ", quantity=" + quantity + ", subtotal=" + subtotal + '}';
    }

    

    
   


}
