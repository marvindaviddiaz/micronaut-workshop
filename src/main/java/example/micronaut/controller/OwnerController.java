package example.micronaut.controller;

import example.micronaut.bo.Owner;
import example.micronaut.bo.Pet;
import example.micronaut.service.OwnerOperations;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;

import javax.annotation.Nullable;
import javax.validation.Valid;
import java.util.Collection;

@Controller("/owners")
public class OwnerController {
    private final OwnerOperations ownerService;

    public OwnerController(OwnerOperations ownerService) {
        this.ownerService = ownerService;
    }

    @Get("/")
    public Collection<Owner> getOwners() {
        return ownerService.getInitialOwners();
    }

    @Get("/{name}")
    Owner get(String name) { // name variable should match {name}
        return null;
    }

    @Post("/")
    public Owner add(@Valid @Body Owner owner) {
        ownerService.addOwner(owner);
        return owner;
    }

    @Get("/{owner}/pets{?health}")
    Collection<Pet> getPets(String owner, @Nullable Pet.PetHealth health) {
        if (health != null) {
            return ownerService.getPetsWithHeath(owner, health);
        } else {
            return ownerService.getPets(owner);
        }
    }

    @Get("/{owner}/pets/{pet}")
    Pet getPet(String owner, String pet) {
        return ownerService.getPet(owner, pet);
    }

}