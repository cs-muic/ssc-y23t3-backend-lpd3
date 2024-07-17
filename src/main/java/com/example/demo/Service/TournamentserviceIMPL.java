package com.example.demo.Service;

import com.example.demo.Customerrepo.Tournamentrepo;
import com.example.demo.entity.Tournament;
import com.example.demo.DTO.TournamentDTO;
import com.example.demo.DTO.TournamentSaveDTO;
import com.example.demo.DTO.TournamentUpdateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class TournamentserviceIMPL implements TournamentService {

    @Autowired
    private Tournamentrepo tournamentRepo;

    @Override
    public List<TournamentDTO> getTournamentbyusername(String username) {
        List<TournamentDTO> currlist = new ArrayList<>();
        for(TournamentDTO curr: getAllTournaments()){
            if (curr.getUsername() != null && Objects.equals(curr.getUsername(), username)){
                currlist.add(curr);
            }
        }
        return currlist;
    }

    @Override
    public TournamentDTO getTournament(TournamentDTO tournamentdto) {
        if (existTournament(tournamentdto)) {
            return tournamentdto;
        }
        return null;
    }

    @Override
    public String addTournament(TournamentSaveDTO tournamentdto) {
        Tournament tournament = new Tournament(
                tournamentdto.getUsername(),
                tournamentdto.getTeamname(),
                tournamentdto.getCategory(),
                tournamentdto.getName()
        );
        tournamentRepo.save(tournament);
        return tournament.getUsername();

    }

    @Override
    public List<TournamentDTO> getAllTournaments() {
        List<Tournament> getTournaments = tournamentRepo.findAll();
        List<TournamentDTO> tournamentDTOList = new ArrayList<>();
        for (Tournament a : getTournaments) {
            TournamentDTO tournamentDTO = new TournamentDTO(
                    a.getGameID(),
                    a.getName(),
                    a.getUsername(),
                    a.getTeamname(),
                    a.getCategory()
            );
            tournamentDTOList.add(tournamentDTO);
        }
        return tournamentDTOList;
    }

    @Override
    public List<TournamentDTO> participants (int category){
        List<TournamentDTO> currlist = new ArrayList<>();
        for (TournamentDTO a: getAllTournaments()){
            if (a.getCategory() == category){
                currlist.add(a);
            }
        }
        return currlist;
    }



    @Override
    public String updateTournaments(TournamentUpdateDTO tournamentupdatedto) {
        if (tournamentRepo.existsById(tournamentupdatedto.getGameID())) {
            Tournament tounament = tournamentRepo.getById(tournamentupdatedto.getGameID());
            tounament.setUsername(tournamentupdatedto.getUsername());
            tounament.setTeamname(tournamentupdatedto.getTeamname());
            tounament.setCategory(tournamentupdatedto.getCategory());
            tournamentRepo.save(tounament);
        } else {
            System.out.println("DNE Tournament");
        }
        return null;
    }

    @Override
    public boolean deleteTournament(int id) {
        if (tournamentRepo.existsById(id)) {
            tournamentRepo.deleteById(id);
        } else {
            System.out.println("tournament id not found");
        }
        return true;
    }

    @Override
    public boolean existTournament(TournamentDTO tournamentdto) {
        List<TournamentDTO> currlist = getAllTournaments();
        for (TournamentDTO curr : currlist) {
            if (Objects.equals(curr.getUsername(), tournamentdto.getUsername()) &&
                    Objects.equals(curr.getTeamname(), tournamentdto.getTeamname()) &&
                    Objects.equals(curr.getCategory(), tournamentdto.getCategory())) {
                return true;
            }
        }
        return false;
    }
}
