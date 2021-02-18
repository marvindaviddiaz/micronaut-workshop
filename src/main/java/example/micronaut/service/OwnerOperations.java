package example.micronaut.service;

import example.micronaut.bo.Owner;
import example.micronaut.bo.Pet;
import example.micronaut.util.Logged;

import java.util.Collection;

public interface OwnerOperations {

    @Logged
    Collection<Owner> getInitialOwners();

    void addOwner(Owner owner);

    Pet getPet(String owner, String pet);

    Collection<Pet> getPets(String owner);

    Collection<Pet> getPetsWithHeath(String owner, Pet.PetHealth health);
}