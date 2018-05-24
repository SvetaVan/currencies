package xpath;

import currencies.rates.CurrencyRateRepository;
import currencies.rates.CurrencyRateRepositoryImpl;
import currencies.rates.CurrencyRateService;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.io.IOException;

public class XPATHquery {


    public static void main(String[] args) {
        System.out.println("test");
        try {
            CurrencyRateService service = new CurrencyRateService();
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();//factory
            DocumentBuilder builder = builderFactory.newDocumentBuilder();//We will use this object to produce a DOM object tree from our xml
            File file= service.loadAllInXML() ;
            Document xmlDocument = builder.parse(file);//A Document (org.w3c.dom.Document) represents the entire XML document, is the root of
            // the document tree, provides our first access to data
            XPath xPath = XPathFactory.newInstance().newXPath();//From the XPath object we’ll access the expressions and execute them
            String expression = "/currenciesRates/currencyRate[2]";
            NodeList node = (NodeList) xPath.compile(expression).evaluate(xmlDocument,XPathConstants.NODESET);

            for(int i = 0; i<node.getLength();++i){
                System.out.println(node.item(i).getTextContent());
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
