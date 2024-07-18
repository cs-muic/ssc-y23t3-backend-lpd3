package com.example.demo.Service;


import com.example.demo.DTO.*;

import java.util.List;

public interface GameListService {
    GameListDTO getCustomer(GameListDTO customerdto);

    String addCustomer(GameListSaveDTO customerdto);

    List<GameListDTO> getAllCustomer();

    String updateCustomers(GameListUpdateDTO customerupdatedto);

    boolean deletecustomer(int id);

    int countnumber(int id);

    boolean exists(String customerdto);

    boolean existcustomer(GameListDTO customerdto);

    List<GameListDTO> gettourny(String username);
}
