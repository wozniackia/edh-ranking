package com.wozniacki.persistence.entity;

import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedEntity
@Builder
public class Commander {
    @Id
    private Integer id;
    private String cName;
    private String imageUrl;
    private Integer matches;
    private Integer wins;
}
