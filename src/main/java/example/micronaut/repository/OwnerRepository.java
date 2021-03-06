package example.micronaut.repository;

import edu.umd.cs.findbugs.annotations.NonNull;
import example.micronaut.bo.Owner;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;

import java.util.Collection;

@JdbcRepository(dialect = Dialect.ORACLE)
public interface OwnerRepository extends CrudRepository<Owner, Long> {
    @NonNull
    @Override
    Collection<Owner> findAll();
}
