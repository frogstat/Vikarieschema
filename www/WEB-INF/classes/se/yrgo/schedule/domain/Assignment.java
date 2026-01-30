package se.yrgo.schedule.domain;

/**
 * Represents an assignment for at substitute teacher,
 * with information on the date of the assignment (including
 * start time), the teacher's name and the school of the
 * assignment (the school where the teacher should teach).
 */
public class Assignment {
    private final Substitute substitute;
    private final School school;
    private final String date;

    /**
     * Creates a new Assignment
     *
     * @param substitute This assignment's teacher's name
     * @param date       The date of this Assignment
     * @param school     The school of this Assignment
     */
    public Assignment(School school, Substitute substitute, String date) {
        this.substitute = substitute;
        this.school = school;
        this.date = date;
    }

    /**
     * Returns this Assignment's teacher's name
     *
     * @return This Assignment's teacher's name
     */
    public Substitute substitute() {
        return this.substitute;
    }

    /**
     * Returns the date of this Assignment
     *
     * @return The date of this Assignment
     */
    public String date() {
        return this.date;
    }

    /**
     * Returns this Assignment's school
     *
     * @return This Assignment's school
     */
    public School school() {
        return this.school;
    }

    /**
     * This Assignment, represented as a String
     *
     * @return This Assignment represented as a String
     */
    @Override
    public String toString() {
        return " (" + substitute.getName() + ")" +
                " at " + school.getName() + ", " + school.getAddress();
    }
}
