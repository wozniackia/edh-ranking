package com.wozniacki.persistence.repository;

import com.wozniacki.persistence.entity.Commander;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;

@JdbcRepository(dialect = Dialect.POSTGRES)
public interface CommanderRepository extends CrudRepository<Commander, Integer> {
}
