package runner;

import dom.DomParser;
import model.Country;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import sax.CountriesHandler;
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
    public static final String COUNTRIES_XML = "Countries.xml";

    public static void main(String[] args) throws SAXException, ParserConfigurationException, IOException, XMLStreamException {
        System.out.println("***************************SAXParser****************************");
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        SAXParser saxParser = saxParserFactory.newSAXParser();
        CountriesHandler countriesHandler = new CountriesHandler();
        saxParser.parse(new File(COUNTRIES_XML), countriesHandler);
        List<Country> countriesList = countriesHandler.getCountriesList();
        countriesList.forEach(country -> System.out.println(country));
        System.out.println("***************************STAxParser****************************");
        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        XMLEventReader xmlEventReader = xmlInputFactory.createXMLEventReader(new FileInputStream(COUNTRIES_XML));
        countriesList = new StaxParser().parse(xmlEventReader);
        countriesList.forEach(country -> System.out.println(country));
        System.out.println("***************************DOMParser****************************");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document document = dBuilder.parse(COUNTRIES_XML);
        countriesList = new DomParser().parse(document);
        countriesList.forEach(country -> System.out.println(country));
    }
}

