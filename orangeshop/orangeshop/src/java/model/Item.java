

package model;


public class Item {
 Product product;
 Image image;
 int quantity;

    public Item() {
    }

    public Item(Product product, Image image, int quantity) {
        this.product = product;
        this.image = image;
        this.quantity = quantity;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    
   

}
