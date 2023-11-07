package com.wozniacki.persistence.entity;

import io.micronaut.data.annotation.GeneratedValue;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.serde.annotation.Serdeable;
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
    private String nickname;
    private String firstName;
    private String lastName;
    private int wins;
}
