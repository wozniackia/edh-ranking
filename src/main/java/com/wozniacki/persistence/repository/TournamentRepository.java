package com.wozniacki.persistence.repository;

import com.wozniacki.persistence.entity.Tournament;
import io.micronaut.data.annotation.Query;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;

@JdbcRepository(dialect = Dialect.POSTGRES)
public interface TournamentRepository extends CrudRepository<Tournament, Integer> {
    @Query(value = "select * from tournament order by tournament_date desc limit :limit ")
    Iterable<Tournament> findNewest(int limit);
}
