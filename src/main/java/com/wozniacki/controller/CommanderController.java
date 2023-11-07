package com.wozniacki.controller;

import com.wozniacki.persistence.entity.Commander;
import com.wozniacki.persistence.entity.Player;
import com.wozniacki.persistence.repository.CommanderRepository;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Controller("api/v1/commander")
@RequiredArgsConstructor
@Slf4j
public class CommanderController {

    private final CommanderRepository commanderRepository;

    @Get
    public Iterable<Commander> getAllCommanders() {
        return commanderRepository.findAll();
    }

    @Get("/{id}")
    public Commander getCommanderById(@PathVariable int id) {
        return commanderRepository.findById(id).orElse(null);
    }

    @Post("/delete/{id}")
    public Iterable<Commander> deleteCommanderById(@PathVariable int id) {
        commanderRepository.deleteById(id);
        return commanderRepository.findAll();
    }

    @Get("/top")
    public Iterable<Commander> getTopCommanders(@QueryValue Optional<Integer> limit) {
        return commanderRepository.findTopCommanders(limit.orElse(10));
    }

    @Post("/add")
    public HttpResponse addCommander(@QueryValue Optional<String> name,
                                  @QueryValue Optional<String> imageUrl) {
        if (name.isPresent() && imageUrl.isPresent()) {
            var commander = Commander.builder()
                    .cName(name.get())
                    .imageUrl(imageUrl.get())
                    .build();
            commanderRepository.save(commander);
            return HttpResponse.created(commander);
        } else {
            return HttpResponse.badRequest();
        }
    }
}
