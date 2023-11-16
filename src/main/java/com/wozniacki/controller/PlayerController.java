package com.wozniacki.controller;

import com.wozniacki.dto.PlayerRegisterRequest;
import com.wozniacki.helper.HashHelper;
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

@Secured(SecurityRule.IS_AUTHENTICATED)
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

    @Secured(SecurityRule.IS_ANONYMOUS)
    @Get("/{username}")
    public Player getPlayerById(@PathVariable String username) {
        return playerRepository.findByUsername(username).orElse(null);
    }

    @Post("/delete/{id}")
    public Iterable<Player> deletePlayerById(@PathVariable int id) {
        playerRepository.deleteById(id);
        return playerRepository.findAll();
    }

    @Secured(SecurityRule.IS_ANONYMOUS)
    @Get("/top")
    public Iterable<Player> getTopPlayers(@QueryValue Optional<Integer> limit) {
        return playerRepository.findTopPlayers(limit.orElse(10));
    }

    @Secured(SecurityRule.IS_ANONYMOUS)
    @Post("/register")
    public HttpResponse<Player> registerPlayer(@Body PlayerRegisterRequest registerRequest) {
        if (registerRequest.firstName().isPresent() && registerRequest.lastName().isPresent() && registerRequest.username().isPresent() && registerRequest.password().isPresent()) {
            var player = Player.builder()
                    .username(registerRequest.username().get())
                    .password(HashHelper.hashPassword(registerRequest.password().get()))
                    .firstName(registerRequest.firstName().get())
                    .lastName(registerRequest.lastName().get())
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
