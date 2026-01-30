package se.yrgo.schedule.servlet;

import se.yrgo.schedule.database.AccessException;
import se.yrgo.schedule.database.ParamParser;
import se.yrgo.schedule.domain.Assignment;
import se.yrgo.schedule.domain.Assignments;
import se.yrgo.schedule.domain.AssignmentsFactory;
import se.yrgo.schedule.format.Formatter;
import se.yrgo.schedule.format.FormatterFactory;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.*;
import javax.servlet.http.*;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * <p>Listens to requests on localhost:8080/v1/ and accepts the following parameters:
 * <ul>
 * <li> none - lists all schedules for all teachers </li>
 * <li> substitute_id - the ID for a substitute teacher you want to list the schedult for</li>
 * <li> day - the day (YYYY-mm-dd) you want to see the schedule for</li>
 * </ul>
 * <p>The substitute_id and day parameters can be combined or used alone.</p>
 */
public class ScheduleServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String format = request.getParameter("format");

        if (format != null && format.equals("xml")) {
            response.setContentType("application/xml");
        } else if (format != null && format.equals("json")) {
            response.setContentType("application/xml");
        }

        // Read the request as UTF-8
        request.setCharacterEncoding(UTF_8.name());

        // Parse the arguments - see ParamParser class
        ParamParser parser = new ParamParser(request);
        // Set the content type (using the parser)
        response.setContentType(parser.contentType());
        // To write the response, we're using a PrintWriter
        response.setCharacterEncoding(UTF_8.name());
        PrintWriter out = response.getWriter();
        // Get access to the database, using a factory
        // Assignments is an interface - see Assignments interface
        Assignments db = AssignmentsFactory.getAssignments();
        // Start with an empty list (makes code easier)
        List<Assignment> assignments = new ArrayList<>();
        // Call the correct method, depending on the parser's type value
        try {
            StringBuilder table;
            assignments = switch (parser.type()) {
                case ALL -> db.all();
                case TEACHER_ID_AND_DAY -> db.forTeacherAt(parser.teacherId(), parser.day());
                case DAY -> db.at(parser.day());
                case TEACHER_ID -> db.forTeacher(parser.teacherId());
            };
        } catch (AccessException e) {
            out.println("Error fetching data: " + e.getMessage());
            System.err.println("Error: " + e);
            e.printStackTrace();
        }

        try {
            // Get a formatter, by asking the parser for the format (defaults to HTML)
            Formatter formatter = FormatterFactory.getFormatter(parser.format());
            // Format the result to the format according to the parser:
            String result = formatter.format(assignments);
            out.println(result);

            if (result.equals("[]") || result.equals("<schedules></schedules>")) {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            }

        } catch (IllegalArgumentException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.println("<html><head><title>Format error</title></head>");
            out.println("<body>400 - BAD REQUEST.\nFormat missing or not supported");
            out.println(" - We support xml and json</body>");
            out.println("</html>");
        }
        // Print the result and close the PrintWriter
        out.close();
    }


}
