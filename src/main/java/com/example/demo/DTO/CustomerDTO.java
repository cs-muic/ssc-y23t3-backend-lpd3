package com.example.demo.DTO;


public class CustomerDTO {
    private int customerID;

    private String customername;

    private String password;


    public CustomerDTO(int customerID, String customername, String password) {
        this.customerID = customerID;
        this.customername = customername;
        this.password = password;
    }

    public CustomerDTO() {
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
        return "CustomerDTO{" +
                "customerID=" + customerID +
                ", customername='" + customername + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
