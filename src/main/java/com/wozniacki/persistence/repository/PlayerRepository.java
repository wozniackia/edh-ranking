package com.wozniacki.persistence.repository;

import com.wozniacki.persistence.entity.Player;
import io.micronaut.data.annotation.Query;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;

import java.util.Optional;

@JdbcRepository(dialect = Dialect.POSTGRES)
public interface PlayerRepository extends CrudRepository<Player, Integer> {

    @Query(value = "select * from player order by wins limit :limit ")
    Iterable<Player> findTopPlayers(int limit);

    Optional<Player> findByUsername(String username);
}
