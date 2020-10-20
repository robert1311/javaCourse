/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.re.bracket.dto;

import java.util.Objects;

/**
 *
 * @author rober
 */
public class User {

    private String userName;
    private boolean isAdmin;
    private String notifications;

    public User(String username){
        this.userName = username;
    }
    
    public String getUserName() {
        return userName;
    }

//    public void setUserName(String userName) {
//        this.userName = userName;
//    }

    public boolean isIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public String getNotifications() {
        return notifications;
    }

    public void 
        setNotifications(String notifications) {
        this.notifications = notifications;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.userName);
        hash = 97 * hash + (this.isAdmin ? 1 : 0);
        hash = 97 * hash + Objects.hashCode(this.notifications);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        if (this.isAdmin != other.isAdmin) {
            return false;
        }
        if (!Objects.equals(this.userName, other.userName)) {
            return false;
        }
        if (!Objects.equals(this.notifications, other.notifications)) {
            return false;
        }
        return true;
    }
    
    

}
