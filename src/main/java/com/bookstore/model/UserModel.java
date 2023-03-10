package com.bookstore.model;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class UserModel {
    private int id;
    private String username;
    private String password;
    private String status;
    private String name;
    private String email;
    private String phone;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String role;
    
    // Constructor
   
    
    // Getters and setters
    public int getId() {
        return id;
    }
    
    public UserModel(int id, String username, String password, String status, String name, String email, String phone,
        LocalDateTime createdAt, LocalDateTime updatedAt, String role) {
        }
        
        public UserModel(int int1, String string, String string2, String string3, String string4, String string5,
        String string6, Timestamp timestamp, Timestamp timestamp2, String string7) {
              this.id = id;
              this.username = username;
              this.password = password;
              this.status = status;
              this.name = name;
              this.email = email;
              this.phone = phone;
              this.createdAt = createdAt;
              this.updatedAt = updatedAt;
              this.role = role;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPhone() {
        return phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
    
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
    
    public String getRole() {
        return role;
    }
    
    public void setRole(String role) {
        this.role = role;
    }
}

