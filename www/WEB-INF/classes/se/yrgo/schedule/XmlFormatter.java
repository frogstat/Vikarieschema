package se.yrgo.schedule;
import javax.xml.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import org.w3c.dom.*;
import java.util.List;

public class XmlFormatter implements Formatter{

    @Override
    public String format(List<Assignment> assignments) {

        return "[ { \\\"some-key\\\": \\\"some-value\\\" } ]";
    }

}
