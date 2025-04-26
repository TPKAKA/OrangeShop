/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;



/**
 *
 * @author Dell E7450
 */

public class UserRole {

    private int RoleID;
    private String Name;
 public UserRole(int RoleID, String Name) {
        this.RoleID = RoleID;
        this.Name = Name;
    }
    
    public UserRole() {
    }

    public int getRoleID() {
        return RoleID;
    }

    public void setRoleID(int RoleID) {
        this.RoleID = RoleID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    @Override
    public String toString() {
        return "UserRole{" + "RoleID=" + RoleID + ", Name=" + Name + '}';
    }

   
}
