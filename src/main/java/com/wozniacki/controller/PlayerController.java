package com.wozniacki.controller;

import com.wozniacki.persistence.entity.Player;
import com.wozniacki.persistence.repository.PlayerRepository;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
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
        return playerRepository.findAll();
    }

    @Get("/{id}")
    public Player getPlayerById(@PathVariable int id) {
        return playerRepository.findById(id).orElse(null);
    }

    @Post("/delete/{id}")
    public Iterable<Player> deletePlayerById(@PathVariable int id) {
        playerRepository.deleteById(id);
        return playerRepository.findAll();
    }

    @Get("/top")
    public Iterable<Player> getTopPlayers(@QueryValue Optional<Integer> limit) {
        return playerRepository.findTopPlayers(limit.orElse(10));
    }

    @Post("/add")
    public HttpResponse addPlayer(@QueryValue Optional<String> firstName,
                                  @QueryValue Optional<String> lastName,
                                  @QueryValue Optional<String> nickname) {
        if (firstName.isPresent() && lastName.isPresent()) {
            var player = Player.builder()
                    .nickname(nickname.orElse(""))
                    .firstName(firstName.get())
                    .lastName(lastName.get())
                    .build();
            playerRepository.save(player);
            return HttpResponse.created(player);
        } else {
            return HttpResponse.badRequest();
        }
    }
}
