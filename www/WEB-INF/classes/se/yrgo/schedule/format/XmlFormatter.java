package se.yrgo.schedule.format;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;

import org.w3c.dom.*;
import se.yrgo.schedule.domain.Assignment;

import java.io.StringWriter;
import java.util.List;

public class XmlFormatter implements Formatter {

    @Override
    public String format(List<Assignment> assignments) {
        if (assignments.isEmpty()) {
            return "<schedules></schedules>";
        }

        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document doc = documentBuilder.newDocument();
            Element rootElement = doc.createElement("schedules");
            doc.appendChild(rootElement);

            for (Assignment assignment : assignments) {
                Element assignmentElement = doc.createElement("schedule");
                assignmentElement.setAttribute("date", assignment.date());

                Element substitute = doc.createElement("substitute");
                substitute.appendChild(doc.createTextNode(assignment.substitute().getName()));

                Element school = doc.createElement("school");
                Element schoolName = doc.createElement("school_name");
                schoolName.appendChild(doc.createTextNode(assignment.school().getName()));
                Element schoolAddress = doc.createElement("school_address");
                schoolAddress.appendChild(doc.createTextNode(assignment.school().getAddress()));
                school.appendChild(schoolName);
                school.appendChild(schoolAddress);

                assignmentElement.appendChild(substitute);
                assignmentElement.appendChild(school);
                rootElement.appendChild(assignmentElement);
            }

            StringWriter xml = new StringWriter();
            TransformerFactory transformerFactory =
                    TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer
                    .setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(xml);
            transformer.transform(source, result);
            return xml.toString();

        } catch (ParserConfigurationException | TransformerException e) {
            return "XML Error";
        }
    }

}
