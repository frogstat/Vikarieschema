package se.yrgo.schedule;

import org.json.*;

import java.util.List;

public class JsonFormatter implements Formatter {

    @Override
    public String format(List<Assignment> assignments) {

        if (assignments.isEmpty()) {
            return "[]";
        }

        try {
            JSONArray JSON = new JSONArray();
            JSONObject jo = new JSONObject();

            for (Assignment assignment : assignments) {
                JSONObject jsonAssignment = new JSONObject();
                JSONObject school = new JSONObject();
                JSONObject substitute = new JSONObject();

                jsonAssignment.put("date", assignment.date());
                school.put("school_name", assignment.school().getName());
                school.put("address", assignment.school().getAddress());
                jsonAssignment.put("school", school);
                substitute.put("substitute", assignment.substitute().getName());
                jsonAssignment.put("substitute", substitute);
                JSON.put(jsonAssignment);
            }

            jo.put("schedule", JSON);
            return jo.toString(2);

        } catch (Exception e) {

            throw new RuntimeException(e);
        }
    }

}
