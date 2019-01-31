import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class LoadConfigFile {
    static int portNumber;
    static int maxClientsCount;

    public LoadConfigFile(){
        File file = new File("./src/config.xml");
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
                .newInstance();
        DocumentBuilder documentBuilder = null;
        try {
            documentBuilder = documentBuilderFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        Document document = null;
        try {
            document = documentBuilder.parse(file);
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        portNumber = Integer.parseInt(document.getElementsByTagName("serverPort").item(0).getTextContent());
        maxClientsCount =Integer.parseInt(document.getElementsByTagName("maxClientsCount").item(0).getTextContent());
    }
}
