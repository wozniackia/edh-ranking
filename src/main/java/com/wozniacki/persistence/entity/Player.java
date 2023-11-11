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

@Getter
@Setter
@MappedEntity
@Serdeable
@Builder
public class Player {
    @Id
    @GeneratedValue
    private Integer id;
    @Nullable
    private String nickname;
    @Nonnull
    private String firstName;
    @Nonnull
    private String lastName;
    private int matches = 0;
    private int wins = 0;
}
