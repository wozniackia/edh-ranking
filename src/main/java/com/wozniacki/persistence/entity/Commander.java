package com.wozniacki.persistence.entity;

import io.micronaut.data.annotation.GeneratedValue;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.annotation.Nonnull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedEntity
@Serdeable
@Builder
public class Commander {
    @Id
    @GeneratedValue
    private Integer id;
    @Nonnull
    private String cName;
    @Nonnull
    private String imageUrl;
    private Integer matches = 0;
    private Integer wins = 0;
}
