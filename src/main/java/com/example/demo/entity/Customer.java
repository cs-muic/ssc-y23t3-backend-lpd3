package com.example.demo.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @Column(name = "customerID", length = 50)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int customerID;

    @Column(name = "customername", length = 50)
    private String customername;

    @Column(name = "password", length = 50)
    private String password;

    public Customer(int customerID, String customername, String password) {
        this.customerID = customerID;
        this.customername = customername;
        this.password = password;
    }

    public Customer() {
    }

    public Customer(String customername, String password) {
        this.customername = customername;
        this.password = password;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getCustomername() {
        return customername;
    }

    public void setCustomername(String customername) {
        this.customername = customername;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerID=" + customerID +
                ", customername='" + customername + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
