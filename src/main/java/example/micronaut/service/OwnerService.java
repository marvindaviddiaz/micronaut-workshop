package example.micronaut.service;

import example.micronaut.bo.Owner;
import example.micronaut.bo.Pet;
import example.micronaut.config.OwnerConfiguration;
import example.micronaut.repository.OwnerRepository;
import example.micronaut.repository.PetRepository;
import example.micronaut.util.Logged;
import io.micronaut.context.event.StartupEvent;
import io.micronaut.runtime.event.annotation.EventListener;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Singleton;
import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.stream.Collectors;

@Singleton
public class OwnerService implements OwnerOperations {

    private final OwnerRepository ownerRepository;
    private final PetRepository petRepository;
    private final List<OwnerConfiguration> ownerConfigurations;

    OwnerService(OwnerRepository ownerRepository,
                 PetRepository petRepository,
                 List<OwnerConfiguration> ownerConfigurations) {
        this.ownerRepository = ownerRepository;
        this.petRepository = petRepository;
        this.ownerConfigurations = ownerConfigurations;
    }

    @EventListener
    @Transactional
    void init(StartupEvent startupEvent) {
        if (ownerRepository.count() == 0) {
            for (OwnerConfiguration ownerConfiguration : ownerConfigurations) {
                Owner owner = ownerConfiguration.create();
                ownerRepository.save(owner);
                List<Pet> pets = ownerConfiguration.getPets().stream().map(n ->
                        new Pet(n, owner)
                ).collect(Collectors.toList());
                petRepository.saveAll(pets);
            }
        }
    }

    @Override
    @Logged
    public Collection<Owner> getInitialOwners() {
        return ownerRepository.findAll();
    }

    @Override
    @Transactional
    public void addOwner(Owner owner) {
        ownerRepository.save(owner);
    }

    @Override
    public Pet getPet(String owner, String pet) {
        return petRepository.findByNameAndOwnerName(pet, owner);
    }

    @Override
    public Collection<Pet> getPets(String owner) {
        return petRepository.findByOwnerName(owner);
    }

    @Override
    public Collection<Pet> getPetsWithHeath(String owner, Pet.PetHealth health) {
        return petRepository.findByOwnerNameAndHealth(owner, health);
    }


    @PostConstruct
    void created() {
        System.out.println("OwnerService created");
    }

    @PreDestroy
    void destroyed() {
        System.out.println("OwnerService destroyed");
    }

}
