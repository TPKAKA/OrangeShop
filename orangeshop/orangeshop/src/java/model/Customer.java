/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Admin
 */
public class Customer {
    private int customerid;
    private int accountid;
    private String review;

    public Customer() {
    }

    public Customer(int customerid, int accountid, String review) {
        this.customerid = customerid;
        this.accountid = accountid;
        this.review = review;
    }

    public int getCustomerid() {
        return customerid;
    }

    public void setCustomerid(int customerid) {
        this.customerid = customerid;
    }

    public int getAccountid() {
        return accountid;
    }

    public void setAccountid(int accountid) {
        this.accountid = accountid;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    @Override
    public String toString() {
        return "Customer{" + "customerid=" + customerid + ", accountid=" + accountid + ", review=" + review + '}';
    }
    
}
