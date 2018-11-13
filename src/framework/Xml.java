package framework;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.openqa.selenium.By;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Xml {
	public static Document loadXml() {
		String path="Repoisitory//Obj.xml";
		File f = new File(path);
		Document Doc = null;
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			Doc = db.parse(f);

		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Doc;
	}

	private static String[] getObjIdentification(String tagName) {
		String[] propertyval = new String[2];
		Document Doc = loadXml();
		NodeList nodVal = Doc.getElementsByTagName(tagName);
		int num = nodVal.getLength();
		if (num > 1) {
			System.out.println("the obj are found more than one");
			System.exit(0);
		} else if (num == 0) {
			System.out.println("no obj are found");
			System.exit(0);
		} else {
			Element elem = (Element) nodVal.item(0);
			if (elem.hasAttribute("locator")) {
				propertyval[0]=elem.getAttribute("locator");
			} else {
				System.out.println("no attribute has foun");
				System.exit(0);
			}
			if (elem.hasAttribute("value")) {
				propertyval[1]=elem.getAttribute("value");
			} else {
				System.out.println("no attribute has foun");
				System.exit(0);
			}

		}
		return propertyval;
	}
	
	public static By get_by_from_Xml(String tagName) {
		By by=null;
		String[] values= getObjIdentification(tagName);
		
		
		switch (values[0].toLowerCase()) {
		case "id":
			by= By.id(values[1]);
			break;
		case "name":
			by= By.name(values[1]);
			break;
		case "tagname":
			
			break;
		case "linktext":
			
			break;
		case"patrtialinktext":
			
			break;
		case"xpath":
			by= By.xpath(values[1]);
			
		break;
		case"css":
			
			break;
			
		case"classname":
			
			break;
			
		default:
			break;
		}
		System.out.println(by);
		return by;

	}
	public static void main(String[] args) {
		loadXml();
		get_by_from_Xml("loginUserName");
	}
}
