package PO73.Perepechin.wdad.learn.xml;

import PO73.Perepechin.wdad.data.model.Flat;
import PO73.Perepechin.wdad.data.model.Registration;
import PO73.Perepechin.wdad.utils.XmlHelper;
import org.w3c.dom.*;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.util.Calendar;

public class XmlTask extends XmlHelper {
    public double getBill(String street, int buildingNumber, int flatNumber) {
        Node flat = getFlatNode(street, buildingNumber, flatNumber);
        if (flat == null) return 0;
        double bill = 0;
        for (Node registrationNode : getChildNodes(flat)) {
            bill += parseRegistration(registrationNode).bill();
        }
        return bill;
    }

    public void setTariff(String tariffName, double newValue) {
        Node tariffs = getDocument().getElementsByTagName("tariffs").item(0);
        tariffs.getAttributes().getNamedItem(tariffName).setTextContent(Double.toString(newValue));
        saveXML();
    }

    public void addRegistration(String street, int buildingNumber, int flatNumber, int year, int month, double coldWater, double hotWater, double electricity, double gas) {
        Node flat = getFlatNode(street, buildingNumber, flatNumber);

        if (flat != null) {
            Element registration = getDocument().createElement("registration");
            registration.setAttribute("month", Integer.toString(month));
            registration.setAttribute("year", Integer.toString(year));

            Element coldWaterReg = getDocument().createElement("coldwater");
            coldWaterReg.setTextContent(Double.toString(coldWater));

            Element hotWaterReg = getDocument().createElement("hotwater");
            hotWaterReg.setTextContent(Double.toString(hotWater));

            Element electricityReg = getDocument().createElement("electricity");
            electricityReg.setTextContent(Double.toString(electricity));

            Element gasReg = getDocument().createElement("gas");
            gasReg.setTextContent(Double.toString(gas));

            registration.appendChild(coldWaterReg);
            registration.appendChild(hotWaterReg);
            registration.appendChild(electricityReg);
            registration.appendChild(gasReg);

            flat.appendChild(registration);

            saveXML();
        }
    }

    public Flat getFlat(String street, int buildingNumber, int flatNumber) {
        Flat flat = new Flat();
        Node flatNode = getFlatNode(street, buildingNumber, flatNumber);
        if (flatNode == null) return null;
        flat.setArea(Double.parseDouble(flatNode.getAttributes().getNamedItem("area").getNodeValue()));
        flat.setNumber(flatNumber);
        flat.setPersonsQuantity(Integer.parseInt(flatNode.getAttributes().getNamedItem("personsquantity").getNodeValue()));
        for (Node registrationNode : getChildNodes(flatNode)) {
            flat.addRegistration(parseRegistration(registrationNode));
        }
        return flat;
    }

    private Registration parseRegistration(Node registrationNode) {
        Registration registration = new Registration();
        int year = Integer.parseInt(registrationNode.getAttributes().getNamedItem("year").getNodeValue());
        int month = Integer.parseInt(registrationNode.getAttributes().getNamedItem("month").getNodeValue());

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        registration.setDate(calendar.getTime());

        NodeList registrationNodeChildNodes = registrationNode.getChildNodes();
        Node registrationNodeChild;
        for (int i = 0; i < registrationNodeChildNodes.getLength(); i++) {
            registrationNodeChild = registrationNodeChildNodes.item(i);
            if (registrationNodeChild.getNodeType() == Node.ELEMENT_NODE) {
                double value = Double.parseDouble(registrationNodeChild.getTextContent());
                switch (registrationNodeChild.getNodeName()) {
                    case "coldwater":
                        registration.setColdWater(value);
                        break;
                    case "hotwater":
                        registration.setHotWater(value);
                        break;
                    case "electricity":
                        registration.setElectricity(value);
                        break;
                    case "gas":
                        registration.setGas(value);
                }
            }
        }

        return registration;
    }

    private Node getFlatNode(String street, int buildingNumber, int flatNumber) {
        NodeList buildingsList = getDocument().getDocumentElement().getElementsByTagName("building");
        for (int i = 0; i < buildingsList.getLength(); i++) {
            Node building = buildingsList.item(i);
            NamedNodeMap buildingAttributes = building.getAttributes();

            //building check
            if (buildingAttributes.getNamedItem("street").getNodeValue().equals(street) &&
                    buildingAttributes.getNamedItem("number").getNodeValue().equals(Integer.toString(buildingNumber))) {
                for (Node flat : getChildNodes(building)) {
                    if (flat.getAttributes().getNamedItem("number").getNodeValue().equals(Integer.toString(flatNumber)))
                        return flat;
                }
            }
        }
        return null;
    }

    @Override
    protected void saveXML() {
        try {
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "3");
            transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, "housekeeper.dtd");
            transformer.transform(new DOMSource(getDocument()), new StreamResult(getXmlFile()));
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }
}
