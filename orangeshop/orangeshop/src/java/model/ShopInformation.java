/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Admin
 */
public class ShopInformation {
    private int id;
    private String address;
    private String phone;
    private String mail;
    private String time;
    private int userID;

    public ShopInformation() {
    }

    public ShopInformation(int id, String address, String phone, String mail, String time, int userID) {
        this.id = id;
        this.address = address;
        this.phone = phone;
        this.mail = mail;
        this.time = time;
        this.userID = userID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    @Override
    public String toString() {
        return "ShopInformation{" + "id=" + id + ", address=" + address + ", phone=" + phone + ", mail=" + mail + ", time=" + time + ", userID=" + userID + '}';
    }

    
    
}
