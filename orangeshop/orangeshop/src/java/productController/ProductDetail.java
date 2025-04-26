

package productController;

import model.ProductCategory;
import model.Image;
import model.Product;


public class ProductDetail {
    private Product product;
    private Image image;
    private ProductCategory category;

    public ProductDetail() {
    }

    public ProductDetail(Product product, Image image, ProductCategory category) {
        this.product = product;
        this.image = image;
        this.category = category;
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

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }

    
    
}