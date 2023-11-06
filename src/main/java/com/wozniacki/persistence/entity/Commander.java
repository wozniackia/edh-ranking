package com.wozniacki.persistence.entity;

import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedEntity
public class Commander {
    @Id
    private Integer id;
    private String name;
    private String imageUrl;
    private Integer owner;
    private Integer matches;
    private Integer wins;
}
