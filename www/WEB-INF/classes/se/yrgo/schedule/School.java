package se.yrgo.schedule;

import java.util.Objects;

public class School {

    private String name;
    private String address;

    public School(String name, String address) {
        this.name = name;
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        School school = (School) o;
        return Objects.equals(name, school.name) && Objects.equals(address, school.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, address);
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }
}
