package PO73.Perepechin.wdad.utils;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class XmlHelper {
    private File xmlFile;
    private Document document;

    public XmlHelper() {
    }

    public XmlHelper(File xmlFile) {
        setXmlFile(xmlFile);
    }

    public File getXmlFile() {
        return xmlFile;
    }

    public void setXmlFile(File xmlFile) {
        this.xmlFile = xmlFile;
        parseXML();
    }

    protected Document getDocument() {
        return document;
    }

    protected Node findChild(Node node, String nodeName) {
        for (Node childNode : getChildNodes(node)) {
            if (childNode.getNodeName().equals(nodeName)) return childNode;
        }
        return null;
    }

    protected Node findNode(String name) {
        return document.getElementsByTagName(name).item(0);
    }

    protected List<Node> getChildNodes(Node node) {
        ArrayList<Node> childNodes = new ArrayList<>();
        NodeList nodeList = node.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node childNode = nodeList.item(i);
            if (childNode.getNodeType() == Node.ELEMENT_NODE) {
                childNodes.add(childNode);
            }
        }
        return childNodes;
    }

    protected void parseXML() {
        try {
            document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(xmlFile);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }

    protected void saveXML() {
        try {
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "3");
            transformer.transform(new DOMSource(document), new StreamResult(xmlFile));
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }
}
