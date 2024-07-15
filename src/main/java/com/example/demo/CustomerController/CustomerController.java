package com.example.demo.CustomerController;

import com.example.demo.DTO.CustomerDTO;
import com.example.demo.DTO.CustomerSaveDTO;
import com.example.demo.DTO.CustomerUpdateDTO;
import com.example.demo.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/v1/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerservice;

    @PostMapping(path = "/exist")
    public boolean exists(@RequestBody CustomerDTO customerdto){
        return customerservice.existcustomer(customerdto);
    }

    @PostMapping(path = "/save")
    public String saveCustomer(@RequestBody CustomerSaveDTO customerdto){
        return customerservice.addCustomer(customerdto);
    }

    @GetMapping(path = "/getAllCustomer")
    public List<CustomerDTO> getCustomer(){
        return customerservice.getAllCustomer();
    }

    @PostMapping(path = "/update")
    public String updateCustomer(@RequestBody CustomerUpdateDTO customerupdatedto){
        return customerservice.updateCustomers(customerupdatedto);
    }

    @DeleteMapping(path = "/deletecustomer/{id}")
    public String deletecustomer(@PathVariable(value = "id") int id){
        customerservice.deletecustomer(id);
        return "deleted";
    }


}
