package java8;

import java.time.LocalDate;
import java.util.Objects;

public class Person2 {

    private String name;
    private LocalDate dateOfBirth;

    public Person2(String name, LocalDate dateOfBirth) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public String toString() {
        return "Person2{" +
                "name='" + name + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person2 person2 = (Person2) o;
        return Objects.equals(name, person2.name) &&
                Objects.equals(dateOfBirth, person2.dateOfBirth);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, dateOfBirth);
    }
}
