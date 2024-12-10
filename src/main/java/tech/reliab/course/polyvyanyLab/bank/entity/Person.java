package tech.reliab.course.polyvyanyLab.bank.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Setter
@Getter
@AllArgsConstructor
public class Person {
    private static int currentId;
    protected int id;
    protected String name;
    protected LocalDate birthDate;

    public Person() {
        id = currentId++;
        name = "No name";
        birthDate = null;
    }

    public Person(Person person) {
        id = person.id;
        name = person.name;
        birthDate = person.birthDate;
    }

    public Person(String _name, LocalDate _birthDate) {
        id = currentId++;
        name = _name;
        birthDate = _birthDate;
    }

    @Override
    public String toString() {
        return "Person:{" +
                "\n id='" + getId() + "'" +
                ",\n name='" + getName() + "'" +
                ",\n birthDate='" + getBirthDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) + "'" +
                "\n}";
    }

    private void initWithDefaults() {
        name = "No name";
        birthDate = null;
    }
}