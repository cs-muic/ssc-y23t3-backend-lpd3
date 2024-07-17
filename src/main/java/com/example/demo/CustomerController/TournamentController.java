package com.example.demo.CustomerController;

import com.example.demo.DTO.TournamentDTO;
import com.example.demo.DTO.TournamentSaveDTO;
import com.example.demo.DTO.TournamentUpdateDTO;
import com.example.demo.Service.TournamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/v1/tournament")
public class TournamentController {

    @Autowired
    private TournamentService tournamentservice;

    @PostMapping(path = "/exist")
    public boolean exists(@RequestBody TournamentDTO tournamentdto) {
        return tournamentservice.existTournament(tournamentdto);
    }

    @GetMapping(path = "/mytournaments/{username}")
    public List<TournamentDTO> listmytournys(@PathVariable(value = "username") String username) {
        return tournamentservice.getTournamentbyusername(username);
    }

    @GetMapping(path = "/list/{category}")
    public List<TournamentDTO> listparticipants (@PathVariable(value = "category") int category){
        List<TournamentDTO> currlist = new ArrayList<>();
        for (TournamentDTO a: getTournaments()){
            if(a.getCategory() == category){
                currlist.add(a);
            }
        }
        return currlist;
    }


    @PostMapping(path = "/save")
    public String saveTournament(@RequestBody TournamentSaveDTO tournamentdto) {
        return tournamentservice.addTournament(tournamentdto);
    }

    @GetMapping(path = "/getAllTournaments")
    public List<TournamentDTO> getTournaments() {
        return tournamentservice.getAllTournaments();
    }


    @PostMapping(path = "/update")
    public String updateTournament(@RequestBody TournamentUpdateDTO tournamentupdatedto) {
        return tournamentservice.updateTournaments(tournamentupdatedto);
    }

    @DeleteMapping(path = "/deleteTournament/{gameID}")
    public String deleteTournament(@PathVariable(value = "gameID") int gameID) {
        tournamentservice.deleteTournament(gameID);
        return "deleted";
    }
}
