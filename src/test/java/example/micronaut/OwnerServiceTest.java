package example.micronaut;

import example.micronaut.bo.Owner;
import example.micronaut.service.OwnerService;
import io.micronaut.context.ApplicationContext;
import io.micronaut.context.exceptions.BeanInstantiationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.validation.constraints.AssertTrue;
import java.util.Collection;
import java.util.Map;

public class OwnerServiceTest {

    @Test
    void testOwners() {
        Map<String, Object> configuration = Map.of(
                "owners.fred.name", "Fred",
                "owners.fred.age", "20",
                "owners.barney.name", "Barney",
                "owners.barney.age", "30"
        );
        try (ApplicationContext context = ApplicationContext.run(configuration)) {
            OwnerService ownerService = context.getBean(OwnerService.class);
            Collection<Owner> initialOwners = ownerService.getInitialOwners();
            Assertions.assertEquals(2,
                    initialOwners.size()
            );

            Assertions.assertTrue(
                    initialOwners.stream().anyMatch(o -> o.getName().equals("Fred"))
            );
        }
    }

    @Test
    void testOwnersUnderAgeFail() {
        Map<String, Object> configuration = Map.of(
                "owners.fred.name", "Fred",
                "owners.fred.age", "10",
                "owners.barney.name", "Barney",
                "owners.barney.age", "30"
        );
        try (ApplicationContext context = ApplicationContext.run(configuration)) {
            OwnerService ownerService = context.getBean(OwnerService.class);
            Collection<Owner> initialOwners = ownerService.getInitialOwners();
            Assertions.assertEquals(2,
                    initialOwners.size()
            );

            Assertions.assertTrue(
                    initialOwners.stream().anyMatch(o -> o.getName().equals("Fred"))
            );
        } catch (Exception e) {
            Assertions.assertTrue(e instanceof BeanInstantiationException);
        }
    }
}
