

package model;


import java.util.ArrayList;
import java.util.List;


public class Cart {
private List<Item> items;

    public Cart() {
        items = new ArrayList<>();
    }

    public Cart(List<Item> items) {
        this.items = items;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
     

private Item getItemById(int id){
    for(Item i: items)
        if(i.getProduct().getProductid()==id&& i.getImage().getProductid()==id)
            return i;
            return null;
    
}
    public int getQuantityById(int id){
        return getItemById(id).getQuantity();
    }
    //them vao cart
    public void addItem(Item t){
        //da co trong cart
        if(getItemById(t.getProduct().getProductid())!=null){
            Item i=getItemById(t.getProduct().getProductid());
            i.setQuantity(i.getQuantity()+t.getQuantity());
        }else{
            //chua co trong cart
            items.add(t);
        }
    }
    //xoa san pham khoi gio hang
    public void removeItem(int id){
        if(getItemById(id)!=null);
        items.remove(getItemById(id));
    }
   
    //tổng tiền của cả giỏ hàng – sẽ add vào bảng Order

    public double getTotalMoney() {
    double total = 0;
    for (Item item : items) {
        Product product = item.getProduct();
        if (product != null) {
            double price = product.getPrice();
            int quantity = item.getQuantity();
            total += price * quantity;
        }
    }
    return total;
}
 public void updateQuantity(int newQuantity, int productid) {
        for (Item item : items) {
            if (item.getProduct().getProductid() == productid) {
                int oldQuantity = item.getQuantity();
            double oldPrice = item.getProduct().getPrice();
            double newPrice = (double)newQuantity / oldQuantity * oldPrice;
            
            item.setQuantity(newQuantity);
            item.getProduct().setPrice(newPrice);
            return;
                
            }
        }
    }
   
}
