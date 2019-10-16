package PO73.Perepechin.wdad.data.managers;

import PO73.Perepechin.wdad.utils.BindedObject;
import PO73.Perepechin.wdad.utils.PreferencesManagerConstants;
import PO73.Perepechin.wdad.utils.XmlHelper;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class PreferencesManager extends XmlHelper {
    public static String APPCONFIG_FILE_PATH = "src/PO73/Perepechin/wdad/resources/configuration/appconfig.xml";
    public static List<String> propertyKeys;
    private static PreferencesManager ourInstance = new PreferencesManager();

    private PreferencesManager() {
        propertyKeys = new ArrayList<>();
        propertyKeys.add(PreferencesManagerConstants.CREATE_REGISTRY_KEY);
        propertyKeys.add(PreferencesManagerConstants.REGISTRY_ADDRESS_KEY);
        propertyKeys.add(PreferencesManagerConstants.REGISTRY_PORT_KEY);
        propertyKeys.add(PreferencesManagerConstants.POLICY_PATH_KEY);
        propertyKeys.add(PreferencesManagerConstants.USE_CODEBASE_ONLY_KEY);
        propertyKeys.add(PreferencesManagerConstants.CLASS_PROVIDER_KEY);

        setXmlFile(new File(APPCONFIG_FILE_PATH));
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
        return getNode(key).getTextContent();
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
        Element bindedObjectElement = getDocument().createElement("bindedobject");
        bindedObjectElement.setAttribute("name", name);
        bindedObjectElement.setAttribute("class", className);
        serverNode.appendChild(bindedObjectElement);
        saveXML();
    }

    public void removeBindedObject(String name) {
        Node serverNode = getNode("appconfig.rmi.server");
        List<Node> serverNodeChildNodes = getChildNodes(serverNode);
        for (Node childNode : serverNodeChildNodes) {
            if (childNode.getNodeName().equals("bindedobject") &&
                    childNode.getAttributes().getNamedItem("name").getNodeValue().equals(name)) {
                serverNode.removeChild(childNode);
            }
        }
        saveXML();
    }

    public List<BindedObject> getBindedObjects() {
        List<BindedObject> bindedObjects = new ArrayList<>();
        List<Node> serverNodeChildNodes = getChildNodes(getNode("appconfig.rmi.server"));
        BindedObject bindedObject;
        for (Node node : serverNodeChildNodes) {
            if (node.getNodeName().equals("bindedobject")) {
                bindedObject = new BindedObject(node.getAttributes().getNamedItem("name").getNodeValue(),
                        node.getAttributes().getNamedItem("class").getNodeValue());
                bindedObjects.add(bindedObject);
            }
        }

        return bindedObjects;
    }

    private Node getNode(String path) {
        String[] pathNodes = path.split("\\.");
        Node node = getDocument().getElementsByTagName(pathNodes[0]).item(0);
        for (int i = 1; i < pathNodes.length; i++) {
            List<Node> childNodes = getChildNodes(node);
            for (Node childNode : childNodes) {
                if (childNode.getNodeName().equals(pathNodes[i])) {
                    node = childNode;
                    break;
                }
            }
        }
        return node;
    }
}
