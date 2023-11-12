package com.wozniacki.controller;

import com.wozniacki.persistence.entity.Player;
import com.wozniacki.persistence.repository.PlayerRepository;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import io.micronaut.http.server.cors.CrossOrigin;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Secured(SecurityRule.IS_ANONYMOUS)
@Controller("api/v1/player")
@RequiredArgsConstructor
@CrossOrigin
@Slf4j
public class PlayerController {

    private final PlayerRepository playerRepository;

    @Get
    public Iterable<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

    @Get("/{username}")
    public Player getPlayerById(@PathVariable String username) {
        return playerRepository.findByUsername(username).orElse(null);
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

    @Post("/register")
    public HttpResponse<Player> registerPlayer(@QueryValue Optional<String> firstName,
                                  @QueryValue Optional<String> lastName,
                                  @QueryValue Optional<String> username,
                                  @QueryValue Optional<String> password) {
        if (firstName.isPresent() && lastName.isPresent() && username.isPresent() && password.isPresent()) {
            var player = Player.builder()
                    .username(username.get())
                    .password(password.get())
                    .firstName(firstName.get())
                    .lastName(lastName.get())
                    .matches(0)
                    .wins(0)
                    .build();
            playerRepository.save(player);
            return HttpResponse.created(player);
        } else {
            return HttpResponse.badRequest();
        }
    }
}
