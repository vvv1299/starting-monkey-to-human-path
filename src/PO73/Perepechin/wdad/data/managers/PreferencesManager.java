package PO73.Perepechin.wdad.data.managers;

import PO73.Perepechin.wdad.utils.PreferencesManagerConstants;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
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
import java.util.Properties;

public class PreferencesManager {
    public static String APPCONFIG_FILE_PATH = "src/PO73/Perepechin/wdad/resources/configuration/appconfig.xml";
    public static List<String> propertyKeys;
    private static PreferencesManager ourInstance = new PreferencesManager();

    private Document document;

    private PreferencesManager() {
        propertyKeys = new ArrayList<>();
        propertyKeys.add(PreferencesManagerConstants.CREATE_REGISTRY_KEY);
        propertyKeys.add(PreferencesManagerConstants.REGISTRY_ADDRESS_KEY);
        propertyKeys.add(PreferencesManagerConstants.REGISTRY_PORT_KEY);
        propertyKeys.add(PreferencesManagerConstants.POLICY_PATH_KEY);
        propertyKeys.add(PreferencesManagerConstants.USE_CODEBASE_ONLY_KEY);
        propertyKeys.add(PreferencesManagerConstants.CLASS_PROVIDER_KEY);

        parseXML();
    }

    public static PreferencesManager getInstance() {
        return ourInstance;
    }

    @Deprecated
    public boolean getCreateRegistry() {
        Node createRegistryNode = findNode("createregistry");
        return createRegistryNode.getTextContent().equals("yes");
    }

    @Deprecated
    public void setCreateRegistry(boolean createRegistry) {
        Node createRegistryNode = findNode("createregistry");
        createRegistryNode.setTextContent(createRegistry ? "yes" : "no");
        saveXML();
    }

    @Deprecated
    public String getRegistryAddress() {
        Node registryAddressNode = findNode("registryaddress");
        return registryAddressNode.getTextContent();
    }

    @Deprecated
    public void setRegistryAddress(String address) {
        Node registryAddressNode = findNode("registryaddress");
        registryAddressNode.setTextContent(address);
        saveXML();
    }

    @Deprecated
    public int getRegistryPort() {
        Node registryPortNode = findNode("registryport");
        return Integer.parseInt(registryPortNode.getTextContent());
    }

    @Deprecated
    public void setRegistryPort(int port) {
        Node registryPortNode = findNode("registryport");
        registryPortNode.setTextContent(Integer.toString(port));
        saveXML();
    }

    public void setProperty(String key, String value) {
        Node node = getNode(key);
        node.setNodeValue(value);
        saveXML();
    }

    public String getProperty(String key) {
        return getNode(key).getNodeValue();
    }

    public void setProperties(Properties prop) {
        prop.stringPropertyNames().forEach(key -> setProperty(key, prop.getProperty(key)));
        saveXML();
    }

    public Properties getProperties() {
        Properties properties = new Properties();
        propertyKeys.forEach(key -> properties.setProperty(key, getProperty(key)));
        return properties;
    }

    public void addBindedObject(String name, String className) {
        Node serverNode = getNode("appconfig.rmi.server");
        Node bindedObjectNode = document.createTextNode("");
        ((Element) bindedObjectNode).setAttribute("name", name);
        ((Element) bindedObjectNode).setAttribute("class", className);
        serverNode.appendChild(bindedObjectNode);
        saveXML();
    }

    public void removeBindedObject(String name) {
        Node serverNode = getNode("appconfig.rmi.server");
        List<Node> serverNodeChildNodes = getChildNodes(serverNode);
        for (int i = 0; i < serverNodeChildNodes.size(); i++) {
            Node childNode = serverNodeChildNodes.get(i);
            if (childNode.getNodeName().equals("bindedobject") &&
                    childNode.getAttributes().getNamedItem("name").getNodeValue().equals(name)) {
                serverNode.removeChild(childNode);
            }
        }
        saveXML();
    }

    private Node getNode(String path) {
        String[] pathNodes = path.split(".");
        Node node = document.getElementsByTagName(pathNodes[0]).item(0);
        for (int i = 1; i < pathNodes.length; i++) {
            node = getChildNodes(node).get(0);
        }
        return node;
    }

    private List<Node> getChildNodes(Node node) {
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

    private Node findNode(String name) {
        return document.getElementsByTagName(name).item(0);
    }

    private void parseXML() {
        try {
            document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new File(APPCONFIG_FILE_PATH));
        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }
    }

    private void saveXML() {
        try {
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "3");
            transformer.transform(new DOMSource(document), new StreamResult(new File(APPCONFIG_FILE_PATH)));
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }
}
