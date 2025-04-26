package model;



public class Product {
    private int productid;
    private String name;
    private String description;
    private double price;
    private int categoryid;
    private int totalproduct;
    private boolean status;
    
    public Product() {
    }

    public Product(int productid, String name) {
        this.productid = productid;
        this.name = name;
    }

    public Product(int productid, String name, String description, double price, int categoryid, int totalproduct) {
        this.productid = productid;
        this.name = name;
        this.description = description;
        this.price = price;
        this.categoryid = categoryid;
        this.totalproduct = totalproduct;
    }

    public Product(int productid, String name, String description, double price, int categoryid) {
        this.productid = productid;
        this.name = name;
        this.description = description;
        this.price = price;
        this.categoryid = categoryid;
    }

    public int getProductid() {
        return productid;
    }

    public void setProductid(int productid) {
        this.productid = productid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(int categoryid) {
        this.categoryid = categoryid;
    }

    public int getTotalproduct() {
        return totalproduct;
    }

    public void setTotalproduct(int totalproduct) {
        this.totalproduct = totalproduct;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    

    
}
