

package model;


public class Discount {
private int DiscountID;
private int DiscountPercent;
private String DiscountCode;
private Product product;
private boolean DiscountStatus;

    public Discount() {
    }

    public Discount(int DiscountID, int DiscountPercent, String DiscountCode, Product product, boolean DiscountStatus) {
        this.DiscountID = DiscountID;
        this.DiscountPercent = DiscountPercent;
        this.DiscountCode = DiscountCode;
        this.product = product;
        this.DiscountStatus = DiscountStatus;
    }

    public Discount(int DiscountID, int DiscountPercent, String DiscountCode) {
        this.DiscountID = DiscountID;
        this.DiscountPercent = DiscountPercent;
        this.DiscountCode = DiscountCode;
    }

   

    public Discount(int DiscountID, int DiscountPercent, String DiscountCode, Product product) {
        this.DiscountID = DiscountID;
        this.DiscountPercent = DiscountPercent;
        this.DiscountCode = DiscountCode;
        this.product = product;
    }

    public int getDiscountID() {
        return DiscountID;
    }

    public void setDiscountID(int DiscountID) {
        this.DiscountID = DiscountID;
    }

    public int getDiscountPercent() {
        return DiscountPercent;
    }

    public void setDiscountPercent(int DiscountPercent) {
        this.DiscountPercent = DiscountPercent;
    }

    public String getDiscountCode() {
        return DiscountCode;
    }

    public void setDiscountCode(String DiscountCode) {
        this.DiscountCode = DiscountCode;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public boolean isDiscountStatus() {
        return DiscountStatus;
    }

    public void setDiscountStatus(boolean DiscountStatus) {
        this.DiscountStatus = DiscountStatus;
    }

}
