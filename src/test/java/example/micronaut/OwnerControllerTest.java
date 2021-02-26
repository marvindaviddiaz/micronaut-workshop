package example.micronaut;

import example.micronaut.bo.Owner;
import example.micronaut.bo.Pet;
import example.micronaut.controller.OwnerController;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.annotation.Nullable;
import javax.inject.Inject;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@MicronautTest
public class OwnerControllerTest {
    @Inject
    OwnerController ownerController;

    @Inject
    OwnerClient ownerClient;

    // using http request
    @Test
    void testListOwner() {
        List<Owner> list = ownerClient.list();
        assertEquals(2, list.size());
    }

    @Client("/owners")
    interface OwnerClient {
        @Get("/")
        List<Owner> list();
        @Get("/{owner}/pets{?health}")
        Collection<Pet> getPets(String owner, @Nullable Pet.PetHealth health);
        @Get("/{owner}/pets/{pet}")
        Pet getPet(String owner, String pet);
    }

    @Test
    void testGetHealthPets() {
        Collection<Pet> pets = ownerClient.getPets("Barney", Pet.PetHealth.VACCINATED);
        assertEquals(
                1,
                pets.size()
        );
    }

//    @MockBean(OwnerOperations.class)
//    OwnerOperations ownerOperations() {
//        return new OwnerService(new ArrayList<>()) { // override
//            @Override
//            public Collection<Owner> getInitialOwners() {
//                return null;
//            }
//            @Override
//            public void addOwner(Owner owner) {
//                System.out.printf("test");
//            }
//        };
//    }
}
