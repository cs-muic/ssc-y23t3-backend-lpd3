package com.example.demo.Service;

import com.example.demo.DTO.*;
import org.springframework.context.annotation.Bean;

import java.util.List;

public interface TournamentService {


    List<TournamentDTO> getTournamentbyusername(String username);

    TournamentDTO getTournament(TournamentDTO tournamentdto);

    String addTournament(TournamentSaveDTO tournamentdto);

    List<TournamentDTO> getAllTournaments();

    List<TournamentDTO> participants(int category);

    String updateTournaments(TournamentUpdateDTO tournamentupdatedto);

    boolean deleteTournament(int id);

    boolean existTournament(TournamentDTO tournamentdto);
}

