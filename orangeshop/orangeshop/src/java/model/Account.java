/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;



/**
 *
 * @author ACER
 */
public class Account {
   private int userid; //1 
   private String name ; //7
   private int roleID;//2
   private String dob;//9
   private String email;//3
   private int status;//14
   private String address; //10
   private String phone; // 6
   private String image ; // 8 
    public Account() {
    }

    public Account(int userid, String name, int roleID, String dob, String email, int status, String address, String phone, String image) {
        this.userid = userid;
        this.name = name;
        this.roleID = roleID;
        this.dob = dob;
        this.email = email;
        this.status = status;
        this.address = address;
        this.phone = phone;
        this.image = image;
    }

   

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRoleID() {
        return roleID;
    }

    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Account{" + "userid=" + userid + ", name=" + name + ", roleID=" + roleID + ", dob=" + dob + ", email=" + email + ", status=" + status + ", address=" + address + ", phone=" + phone + ", image=" + image + '}';
    }


}
