/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;


/**
 *
 * @author Dell E7450
 */

public class User {

//    private int UserID;
//    private int UserRole_RoleID;
//    private String Email;
//    private String Facebook;
//    private String Password;
//    private String Phone;
//    private String FullName;
//    private String Image;
//    private Date Dob;
//    private String Address;
//    private int AddressId;
//    private String GmailID;
//    private String FacebookID;
//    private int Status;
//    private String CodeVerify;
//    private String bio;
//    private UserRole userRole;
    private int userID;
    private int userRole_RoleID;
    private String email;
    private String facebook;
    private String password;
    private String phone;
    private String fullName;
    private String image;
    private Date dob;
    private String address;
    private int addressId;
    private String gmailID;
    private String facebookID;
    private int status;
    private String codeVerify;
    private String bio;
    private UserRole userRole;

//    public User(int UserID, int UserRole_RoleID, String Email, String Facebook, String Password, String Phone, String FullName, String Image, Date Dob, String Address, int AddressId, String GmailID, String FacebookID, int Status, String CodeVerify, String bio) {
//        this.UserID = UserID;
//        this.UserRole_RoleID = UserRole_RoleID;
//        this.Email = Email;
//        this.Facebook = Facebook;
//        this.Password = Password;
//        this.Phone = Phone;
//        this.FullName = FullName;
//        this.Image = Image;
//        this.Dob = Dob;
//        this.Address = Address;
//        this.AddressId = AddressId;
//        this.GmailID = GmailID;
//        this.FacebookID = FacebookID;
//        this.Status = Status;
//        this.CodeVerify = CodeVerify;
//        this.bio = bio;
//    }

    public User(int userID, int userRole_RoleID, String email, String facebook, String password, String phone, String fullName, String image, Date dob, String address, int addressId, String gmailID, String facebookID, int status, String codeVerify, String bio, UserRole userRole) {
        this.userID = userID;
        this.userRole_RoleID = userRole_RoleID;
        this.email = email;
        this.facebook = facebook;
        this.password = password;
        this.phone = phone;
        this.fullName = fullName;
        this.image = image;
        this.dob = dob;
        this.address = address;
        this.addressId = addressId;
        this.gmailID = gmailID;
        this.facebookID = facebookID;
        this.status = status;
        this.codeVerify = codeVerify;
        this.bio = bio;
        this.userRole = userRole;
    }

    public User() {
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getUserRole_RoleID() {
        return userRole_RoleID;
    }

    public void setUserRole_RoleID(int userRole_RoleID) {
        this.userRole_RoleID = userRole_RoleID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public String getGmailID() {
        return gmailID;
    }

    public void setGmailID(String gmailID) {
        this.gmailID = gmailID;
    }

    public String getFacebookID() {
        return facebookID;
    }

    public void setFacebookID(String facebookID) {
        this.facebookID = facebookID;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCodeVerify() {
        return codeVerify;
    }

    public void setCodeVerify(String codeVerify) {
        this.codeVerify = codeVerify;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    @Override
    public String toString() {
        return "User{" + "userID=" + userID + ", userRole_RoleID=" + userRole_RoleID + ", email=" + email + ", facebook=" + facebook + ", password=" + password + ", phone=" + phone + ", fullName=" + fullName + ", image=" + image + ", dob=" + dob + ", address=" + address + ", addressId=" + addressId + ", gmailID=" + gmailID + ", facebookID=" + facebookID + ", status=" + status + ", codeVerify=" + codeVerify + ", bio=" + bio + ", userRole=" + userRole + '}';
    }
   

    
   
}
