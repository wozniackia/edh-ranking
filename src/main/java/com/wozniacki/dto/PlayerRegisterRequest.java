package com.wozniacki.dto;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;

import java.util.Optional;

@Introspected
@Serdeable
public record PlayerRegisterRequest(Optional<String> firstName,
                                    Optional<String> lastName,
                                    Optional<String> username,
                                    Optional<String> password) {
}
