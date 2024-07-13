package com.example.demo.Service;

import com.example.demo.Customerrepo.CustomerRepo;
import com.example.demo.DTO.CustomerDTO;
import com.example.demo.DTO.CustomerSaveDTO;
import com.example.demo.DTO.CustomerUpdateDTO;
import com.example.demo.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class CustomerServiceIMPL implements CustomerService {

    @Autowired
    private CustomerRepo customerRepo;

    @Override
    public String addCustomer(CustomerSaveDTO customerdto) {
        List<CustomerDTO> currlist = getAllCustomer();
        boolean exist = false;
        for(CustomerDTO curr: currlist){
            if (Objects.equals(curr.getCustomername(), customerdto.getCustomername()) && Objects.equals(curr.getPassword(), customerdto.getPassword())) {
                exist = true;
                break;
            }
        }
        if(!exist) {
            Customer customer = new Customer(
                    customerdto.getCustomername(),
                    customerdto.getPassword()
            );
            customerRepo.save(customer);
            return customer.getCustomername();
        } else {
            return null;
        }
    }

    @Override
    public List<CustomerDTO> getAllCustomer() {
        List<Customer> getcustomers = customerRepo.findAll();
        List<CustomerDTO> customerDTOList = new ArrayList<>();
        for(Customer a: getcustomers){
            CustomerDTO customerdto =  new CustomerDTO(
                    a.getCustomerID(),
                    a.getCustomername(),
                    a.getPassword()
            );
            customerDTOList.add(customerdto);
        }
        return customerDTOList;
    }

    @Override
    public String updateCustomers(CustomerUpdateDTO customerupdatedto) {
        if(customerRepo.existsById(customerupdatedto.getCustomerID())){
            Customer customer = customerRepo.getById(customerupdatedto.getCustomerID());
            customer.setCustomername(customerupdatedto.getCustomername());
            customer.setPassword(customerupdatedto.getPassword());
            customerRepo.save(customer);
        }else{
            System.out.println("DNE User");
        }
        return null;
    }

    @Override
    public boolean deletecustomer(int id) {
        if(customerRepo.existsById(id)){
            customerRepo.deleteById(id);
        } else {
            System.out.println("customer id not found");
        }
        return true;
    }

    @Override
    public boolean existcustomer(CustomerDTO customerdto) {
        List<CustomerDTO> currlist = getAllCustomer();
        for(CustomerDTO curr: currlist){
            if (Objects.equals(curr.getCustomername(), customerdto.getCustomername()) && Objects.equals(curr.getPassword(), customerdto.getPassword())) {
                return true;
            }
        }
        return false;
    }

}
