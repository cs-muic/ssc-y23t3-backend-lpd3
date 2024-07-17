package com.example.demo.Customerrepo;


import com.example.demo.entity.GameListDatabase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@EnableJpaRepositories

@Repository
public interface GameListRepo extends JpaRepository<GameListDatabase, Integer> {
}
