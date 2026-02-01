package se.yrgo.schedule.domain;

import java.util.Objects;

/**
 * Represents a school with a name and a physical address.
 * This class is immutable after creation.
 */
public class School {

    private final String name;
    private final String address;

    /**
     * Creates a new School instance.
     *
     * @param name the name of the school
     * @param address the physical address of the school
     */
    public School(String name, String address) {
        this.name = name;
        this.address = address;
    }

    /**
     * Returns the name of the school.
     *
     * @return the school name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the address of the school.
     *
     * @return the school address
     */
    public String getAddress() {
        return address;
    }
}
