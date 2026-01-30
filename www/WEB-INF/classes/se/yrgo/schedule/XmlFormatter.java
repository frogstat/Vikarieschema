package se.yrgo.schedule;

import javax.print.Doc;
import javax.xml.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;

import org.w3c.dom.*;

import java.io.StringWriter;
import java.util.List;

public class XmlFormatter implements Formatter {

    @Override
    public String format(List<Assignment> assignments) {
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document doc = documentBuilder.newDocument();
            Element rootElement = doc.createElement("schedules");
            doc.appendChild(rootElement);

            for (Assignment assignment : assignments) {
                Element assignmentElement = doc.createElement("schedule");

                Element substitute = doc.createElement("substitute");
                substitute.appendChild(doc.createTextNode(assignment.substitute().getName()));

                Element school = doc.createElement("school");
                Element schoolName = doc.createElement("school_name");
                schoolName.appendChild(doc.createTextNode(assignment.school().getName()));
                Element schoolAddress = doc.createElement("school_address");
                schoolAddress.appendChild(doc.createTextNode(assignment.school().getAddress()));
                school.appendChild(schoolName);
                school.appendChild(schoolAddress);

                Element date = doc.createElement("date");
                date.appendChild(doc.createTextNode(assignment.date()));

                assignmentElement.appendChild(substitute);
                assignmentElement.appendChild(school);
                assignmentElement.appendChild(date);
                rootElement.appendChild(assignmentElement);
            }

            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            StringWriter writer = new StringWriter();
            transformer.transform(new DOMSource(doc), new StreamResult(writer));
            return writer.toString();


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
