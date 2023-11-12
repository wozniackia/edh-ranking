package com.wozniacki.persistence.entity;

import io.micronaut.data.annotation.GeneratedValue;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@MappedEntity
@Serdeable
@Builder
public class Tournament {
    @Id
    @GeneratedValue
    private Integer id;
    @Nullable
    private LocalDateTime tournamentDate;
    @Nonnull
    private String tournamentName;
    @Nonnull
    private String tournamentLocation;
    private int tournamentPrice = 10;
    private List<Integer> registeredParticipants;
    private int availableParticipants = 10;
}
