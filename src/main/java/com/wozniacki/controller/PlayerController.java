package com.wozniacki.controller;

import com.wozniacki.persistence.entity.Player;
import com.wozniacki.persistence.repository.PlayerRepository;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.QueryValue;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Controller("api/v1/player")
@RequiredArgsConstructor
@Slf4j
public class PlayerController {

    private final PlayerRepository playerRepository;

    @Get
    public Iterable<Player> getAllPlayers() {
        log.info("Entered getAllPlayers");
        return playerRepository.findAll();
    }

    @Get("/top")
    public Iterable<Player> getTopPlayers(@QueryValue Optional<Integer> limit) {
        log.info("Entered getTopPlayers");
        return playerRepository.findTopPlayers(limit.orElse(10));
    }
}
