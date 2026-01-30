package se.yrgo.schedule;

import org.json.*;

import java.util.List;

public class JsonFormatter implements Formatter {

    @Override
    public String format(List<Assignment> assignments) {

        try {
            JSONObject jo = new JSONObject();
            JSONArray schedule = new JSONArray();

            for (Assignment assignment : assignments) {
                JSONObject jsonJob = new JSONObject();
                jsonJob.put("school_name", assignment.school().getName());
                jsonJob.put("school_address", assignment.school().getAddress());
                jsonJob.put("date", assignment.date());
                jsonJob.put("substitute", assignment.substitute().getName());
                schedule.put(jsonJob);
            }

            jo.put("schedule", schedule);
            return jo.toString();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
