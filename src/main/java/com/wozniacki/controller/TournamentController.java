package com.wozniacki.controller;

import com.wozniacki.persistence.entity.Tournament;
import com.wozniacki.persistence.repository.TournamentRepository;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import io.micronaut.http.server.cors.CrossOrigin;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.Optional;

@Controller("api/v1/tournament")
@RequiredArgsConstructor
@CrossOrigin
@Slf4j
public class TournamentController {

    private final TournamentRepository tournamentRepository;

    @Get
    public Iterable<Tournament> getAllTournaments() {
        return tournamentRepository.findAll();
    }

    @Get("/{id}")
    public Tournament getTournamentById(@PathVariable int id) {
        return tournamentRepository.findById(id).orElse(null);
    }

    @Get("/last")
    public Iterable<Tournament> getNewestTournaments(@QueryValue Optional<Integer> limit) {
        return tournamentRepository.findNewest(limit.orElse(5));
    }

    @Post("/delete/{id}")
    public Iterable<Tournament> deleteTournamentById(@PathVariable int id) {
        tournamentRepository.deleteById(id);
        return tournamentRepository.findAll();
    }

    @Post("/add")
    public HttpResponse<Tournament> addTournament(@QueryValue Optional<LocalDateTime> tournamentDate,
                                  @QueryValue Optional<String> tournamentName,
                                  @QueryValue Optional<String> tournamentLocation,
                                  @QueryValue Optional<Integer> tournamentPrice,
                                  @QueryValue Optional<Integer> availableParticipants) {
        if (tournamentDate.isPresent() && tournamentName.isPresent() && tournamentLocation.isPresent()) {
            var tournament = Tournament.builder()
                    .tournamentDate(tournamentDate.get())
                    .tournamentName(tournamentName.get())
                    .tournamentLocation(tournamentLocation.get())
                    .tournamentPrice(tournamentPrice.orElse(10))
                    .availableParticipants(availableParticipants.orElse(10))
                    .build();
            tournamentRepository.save(tournament);
            return HttpResponse.created(tournament);
        } else {
            return HttpResponse.badRequest();
        }
    }
}
