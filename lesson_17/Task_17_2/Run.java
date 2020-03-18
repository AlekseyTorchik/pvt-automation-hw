package runner;

import dom.DomParser;
import model.User;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import sax.UsersHandler;
import stax.StaxParser;

import javax.xml.parsers.*;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

public class Run {
    public static final String USERDATA_XML = "UserData.xml";

    public static void main(String[] args) throws SAXException, ParserConfigurationException, IOException, XMLStreamException {
        System.out.println("***************************SAXParser****************************");
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        SAXParser saxParser = saxParserFactory.newSAXParser();
        UsersHandler usersHandler = new UsersHandler();
        saxParser.parse(new File(USERDATA_XML), usersHandler);
        List<User> users = usersHandler.getUsers();
        users.forEach(user -> System.out.println(user));
        System.out.println("***************************STAxParser****************************");
        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        XMLEventReader xmlEventReader = xmlInputFactory.createXMLEventReader(new FileInputStream(USERDATA_XML));
        users = new StaxParser().parse(xmlEventReader);
        users.forEach(user -> System.out.println(user));
        System.out.println("***************************DOMParser****************************");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document document = dBuilder.parse(USERDATA_XML);
        users = new DomParser().parse(document);
        users.forEach(user -> System.out.println(user));
    }
}
