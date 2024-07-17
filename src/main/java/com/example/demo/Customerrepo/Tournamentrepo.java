package com.example.demo.Customerrepo;


import com.example.demo.entity.Tournament;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@EnableJpaRepositories

@Repository
public interface Tournamentrepo extends JpaRepository<Tournament, Integer> {
}
