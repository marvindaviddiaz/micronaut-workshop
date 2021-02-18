package example.micronaut.config;

import example.micronaut.bo.Owner;
import io.micronaut.context.annotation.Context;
import io.micronaut.context.annotation.EachProperty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.Collections;
import java.util.List;

@EachProperty("owners")
@Context
public class OwnerConfiguration {

    private List<String> pets = Collections.emptyList();

    @NotBlank // validate config files
    private String name;

    @Min(18)
    private int age;

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Owner create() {
        return new Owner(name, age);
    }

    public List<String> getPets() {
        return pets;
    }

    public void setPets(List<String> pets) {
        this.pets = pets;
    }
}