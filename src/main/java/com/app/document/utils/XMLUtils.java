package com.app.document.utils;

import com.app.document.entity.DocumentEntity;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.w3c.dom.Document;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.stax.StAXSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@UtilityClass
@Slf4j
public class XMLUtils {
    private static Schema schema;

    static {
        try {
            Resource resource = new ClassPathResource("document.xsd");
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            schema = factory.newSchema(resource.getFile());
        } catch (SAXException e) {
            log.error("SAXException", e);
            throw new RuntimeException(e);
        } catch (IOException e) {
            log.error("IOException", e);
            throw new RuntimeException(e);
        }
    }

    public static boolean validate(File xmlFile) {
        boolean flag = false;
        try {
            if (schema != null) {
                Validator validator = schema.newValidator();
                XMLInputFactory inputFactory = XMLInputFactory.newInstance();
                FileInputStream inputStream = new FileInputStream(xmlFile);
                XMLStreamReader xmlStreamReader = inputFactory.createXMLStreamReader(inputStream);
                StAXSource source = new StAXSource(xmlStreamReader);
                validator.validate(source);
                flag = true;
            }
        } catch (Exception e) {
            log.error("Exception occurred", e);
        }
        return flag;
    }

    public static DocumentEntity getDocumentEntity(File xmlFile) throws Exception {
        try {

            System.out.println("1");
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            System.out.println("2");
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            System.out.println("3");
            Document xmlDocument = builder.parse(xmlFile);
            System.out.println("4");
            XPath xPath = XPathFactory.newInstance().newXPath();
            System.out.println("5");
            Text newsPaper = (Text) xPath.compile("//newspaperName/text()").evaluate(xmlDocument, XPathConstants.NODESET);
            System.out.println("6");
            System.out.println(newsPaper.getData());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
