package com.example.demo.DTO;

public class CustomerSaveDTO {

    private String customername;

    private String password;


    public CustomerSaveDTO(String customername, String password) {
        this.customername = customername;
        this.password = password;
    }

    public CustomerSaveDTO() {
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
                ", customername='" + customername + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
