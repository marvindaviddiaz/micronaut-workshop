package example.micronaut.repository;

import example.micronaut.bo.Pet;
import io.micronaut.data.annotation.Join;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;

import java.util.Collection;

@JdbcRepository(dialect = Dialect.ORACLE)
public interface PetRepository extends CrudRepository<Pet, Long> {

    @Join("owner")
    Collection<Pet> findByOwnerName(String owner);

    @Join("owner")
    Pet findByNameAndOwnerName(String pet, String owner);

    @Join("owner")
    Collection<Pet> findByOwnerNameAndHealth(String owner, Pet.PetHealth health);
}