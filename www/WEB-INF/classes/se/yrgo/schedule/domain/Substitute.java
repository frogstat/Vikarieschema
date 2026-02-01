package se.yrgo.schedule.domain;


/**
 * Represents a substitute with a name.
 * Instances of this class are immutable.
 */
public class Substitute {

    private final String name;

    /**
     * Creates a new Substitute.
     *
     * @param name the name of the substitute
     */
    public Substitute(String name) {
        this.name = name;
    }

    /**
     * Returns the name of the substitute.
     *
     * @return the substitute name
     */
    public String getName() {
        return name;
    }
}
