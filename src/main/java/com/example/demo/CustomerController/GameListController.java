package com.example.demo.CustomerController;

import com.example.demo.DTO.*;
import com.example.demo.Service.CustomerService;
import com.example.demo.Service.GameListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/v1/gamelist")
public class GameListController {

    @Autowired
    private GameListService customerservice;

    @PostMapping(path = "/exist")
    public boolean exists(@RequestBody GameListDTO customerdto){
        return customerservice.existcustomer(customerdto);
    }

    @GetMapping(path = "/getcurrtournaments/{username}")
    public List<GameListDTO> listtournaments(@PathVariable(value = "username") String username) {
        return customerservice.gettourny(username);

    }

    @GetMapping(path = "/getregis/{username}")
    public boolean exists(@PathVariable(value = "username") String username) {
        return customerservice.exists(username);

    }

    @PostMapping(path = "/getcustomer")
    public GameListDTO getCustomer(@RequestBody GameListDTO customerdto){
        return customerservice.getCustomer(customerdto);
    }

    @PostMapping(path = "/save")
    public String saveCustomer(@RequestBody GameListSaveDTO customerdto){
        return customerservice.addCustomer(customerdto);
    }

    @GetMapping(path = "/getAllCustomer")
    public List<GameListDTO> getCustomer(){
        return customerservice.getAllCustomer();
    }

    @GetMapping(path = "/getnumber/{id}")
    public int getnumber(@PathVariable(value = "id") int id) {
        return customerservice.countnumber(id);
    }

    @PostMapping(path = "/update")
    public String updateCustomer(@RequestBody GameListUpdateDTO customerupdatedto){
        return customerservice.updateCustomers(customerupdatedto);
    }

    @DeleteMapping(path = "/deletecustomer/{ListID}")
    public String deletecustomer(@PathVariable(value = "ListID") int ListID){
        customerservice.deletecustomer(ListID);
        return "deleted";
    }


}

