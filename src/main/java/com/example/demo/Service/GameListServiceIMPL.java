package com.example.demo.Service;

import com.example.demo.Customerrepo.GameListRepo;
import com.example.demo.DTO.GameListDTO;
import com.example.demo.DTO.GameListSaveDTO;
import com.example.demo.DTO.GameListUpdateDTO;
import com.example.demo.entity.GameListDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class GameListServiceIMPL implements GameListService{

    @Autowired
    private GameListRepo customerRepo;

    @Override
    public GameListDTO getCustomer(GameListDTO customerdto) {
        if(existcustomer(customerdto)){
            return customerdto;
        }
        return null;
    }

    @Override
    public String addCustomer(GameListSaveDTO customerdto) {
        List<GameListDTO> currlist = getAllCustomer();
        boolean exist = false;
        for (GameListDTO curr : currlist) {
            if (Objects.equals(curr.getGamename(), customerdto.getGamename())) {
                exist = true;
                break;
            }
        }
        if (!exist) {
            GameListDatabase customer = new GameListDatabase(
                    customerdto.getGamename(),
                    customerdto.getGamelink(),
                    customerdto.getParticipantnumber(),
                    customerdto.getTeamname(),
                    customerdto.getTotaluser()
            );
            customerRepo.save(customer);
            return customer.getGamename();
        }
        return null;
    }

    @Override
    public List<GameListDTO> getAllCustomer() {
        List<GameListDatabase> getcustomers = customerRepo.findAll();
        List<GameListDTO> customerDTOList = new ArrayList<>();
        for(GameListDatabase a: getcustomers){
            GameListDTO customerdto =  new GameListDTO(
                    a.getListID(),
                    a.getGamename(),
                    a.getGamelink(),
                    a.getParticipantnumber(),
                    a.getTeamname(),
                    a.getTotaluser()
            );
            customerDTOList.add(customerdto);
        }
        return customerDTOList;
    }

    @Override
    public String updateCustomers(GameListUpdateDTO customerupdatedto) {
        if(customerRepo.existsById(customerupdatedto.getListID())){
            GameListDatabase customer = customerRepo.getById(customerupdatedto.getListID());
            customer.setGamename(customerupdatedto.getGamename());
            customer.setGamelink(customerupdatedto.getGamelink());
            customer.setParticipantnumber(customerupdatedto.getParticipantnumber());
            customer.setTeamname(customerupdatedto.getTeamname());
            customer.setTotaluser(customerupdatedto.getTotaluser());
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
    public int countnumber(int id) {
        int curr = 0;
        for(GameListDTO a: getAllCustomer()){
            if(Objects.equals(a.getListID(),id)){
                curr = curr + 1;
            }
        }
        return curr;
    }

    @Override
    public boolean exists(String customerdto) {
        List<GameListDTO> currlist = getAllCustomer();
        for(GameListDTO curr: currlist){
            if (Objects.equals(curr.getParticipantnumber(), customerdto)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean existcustomer(GameListDTO customerdto) {
        List<GameListDTO> currlist = getAllCustomer();
        for(GameListDTO curr: currlist){
            if (Objects.equals(curr.getGamename(), customerdto.getGamename())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<GameListDTO> gettourny(String username) {
        List<GameListDTO> currlist = getAllCustomer();
        List<GameListDTO> resultlist = new ArrayList<>();
        for(GameListDTO curr: currlist){
            if(Objects.equals(curr.getParticipantnumber(), username)){
                resultlist.add(curr);
            }
        }
        return resultlist;
    }
}
