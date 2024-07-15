package com.example.demo.Service;

import com.example.demo.DTO.CustomerDTO;
import com.example.demo.DTO.CustomerSaveDTO;
import com.example.demo.DTO.CustomerUpdateDTO;

import java.util.List;

public interface CustomerService {

    String addCustomer(CustomerSaveDTO customerdto);

    List<CustomerDTO> getAllCustomer();

    String updateCustomers(CustomerUpdateDTO customerupdatedto);

    boolean deletecustomer(int id);

    boolean existcustomer(CustomerDTO customerdto);
}
