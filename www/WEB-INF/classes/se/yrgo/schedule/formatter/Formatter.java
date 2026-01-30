package se.yrgo.schedule.formatter;

import se.yrgo.schedule.domain.Assignment;

import java.util.List;

public interface Formatter {
    public String format(List<Assignment> assignments);
}
