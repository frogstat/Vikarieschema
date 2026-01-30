package se.yrgo.schedule;

import javax.xml.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;

import org.w3c.dom.*;

import java.util.List;

public class XmlFormatter implements Formatter {

    @Override
    public String format(List<Assignment> assignments) {

        return """
                <?xml version="1.0"?>
                <customers>
                   <customer id="55000">
                      <name>Charter Group</name>
                      <address>
                         <street>100 Main</street>
                         <city>Framingham</city>
                         <state>MA</state>
                         <zip>01701</zip>
                      </address>
                      <address>
                         <street>720 Prospect</street>
                         <city>Framingham</city>
                         <state>MA</state>
                         <zip>01701</zip>
                      </address>
                      <address>
                         <street>120 Ridge</street>
                         <state>MA</state>
                         <zip>01760</zip>
                      </address>
                   </customer>
                </customers>
                """;
    }

}
