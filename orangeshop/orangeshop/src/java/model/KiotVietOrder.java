/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ACER
 */
public class KiotVietOrder {
   
    private String id;
     private String totalValue;
    private String purchaseDate;
    private String customerName;
    private String totalPayment;
    private String statusValue;

    public KiotVietOrder() {
    }

    public KiotVietOrder(String id, String totalValue, String purchaseDate, String customerName, String totalPayment, String statusValue) {
        this.id = id;
        this.totalValue = totalValue;
        this.purchaseDate = purchaseDate;
        this.customerName = customerName;
        this.totalPayment = totalPayment;
        this.statusValue = statusValue;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(String totalValue) {
        this.totalValue = totalValue;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getTotalPayment() {
        return totalPayment;
    }

    public void setTotalPayment(String totalPayment) {
        this.totalPayment = totalPayment;
    }

    public String getStatusValue() {
        return statusValue;
    }

    public void setStatusValue(String statusValue) {
        this.statusValue = statusValue;
    }

    @Override
    public String toString() {
        return "KiotVietOrder{" + "id=" + id + ", totalValue=" + totalValue + ", purchaseDate=" + purchaseDate + ", customerName=" + customerName + ", totalPayment=" + totalPayment + ", statusValue=" + statusValue + '}';
    }

   
     
    
}
