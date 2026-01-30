package se.yrgo.schedule.domain;

import se.yrgo.schedule.database.DatabaseAssignments;

public class AssignmentsFactory {
    private AssignmentsFactory() {
    }

    public static Assignments getAssignments() {
        return new DatabaseAssignments();
    }

}
