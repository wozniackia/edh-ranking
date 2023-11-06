package com.wozniacki.controller;

import com.wozniacki.persistence.entity.Player;
import com.wozniacki.persistence.repository.PlayerRepository;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.QueryValue;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@Controller("api/v1/player")
@RequiredArgsConstructor
public class PlayerController {

    private final PlayerRepository playerRepository;

    @Get
    public Iterable<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

    @Get("/top")
    public Iterable<Player> getTopPlayers(@QueryValue Optional<Integer> limit) {
        return playerRepository.findTopPlayers(limit.orElse(10));
    }
}
