package framework;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.openqa.selenium.By;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Utility {
	/*
	 * public static void main(String[] args) {
	 * Readdata("TestData/ENV_Data.properties"); }
	 */
	
	private static ThreadLocal<Utility> uthread=new ThreadLocal<>();
	public static void set(Utility data) {
		uthread.set(data);
	}
	public static Utility get() {
		return uthread.get();
	}
	
	public static HashMap<String, String> Readdata(String path) {
		Data.alldata = new HashMap<>();

		File f = new File(path);
		if (f.exists()) {
			try {
				FileReader reader = new FileReader(f);
				Properties prop = new Properties();
				prop.load(reader);
				Set<Object> allkeys = prop.keySet();
				// System.out.println(allkeys);
				for (Object values : allkeys) {
					String str = prop.getProperty(values.toString());
					Data.alldata.put(values.toString(), str);
					// System.out.println(stt);
					// System.out.println(str);

				}
				// System.out.println(alldata.get("browser"));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			System.out.println("Given File Path Does not exist");
		}

		return Data.alldata;
	}

	public static Document loadXml() {
		String path = "Repoisitory/Obj.xml";
		File f = new File(path);
		Document d = null;

		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();

			d = db.parse(f);
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
		return d;

	}

	public static String[] getOjectIdentification(String tname) {
		String[] propval = new String[2];
		Document doc = loadXml();
		NodeList nodes = doc.getElementsByTagName(tname);
		int len = nodes.getLength();
		if (len > 1) {
			System.out.println("The TagName are found more than one");
		} else if (len == 0) {
			System.out.println("No Tagname found");
		} else {
			Element elem = (Element) nodes.item(0);
			if (elem.hasAttribute("locator")) {
				propval[0] = elem.getAttribute("locator");
			} else {
				System.out.println("no locatores found");
				System.exit(0);
			}
			if (elem.hasAttribute("value")) {
				propval[1] = elem.getAttribute("value");
			} else {
				System.out.println("No Attributes Found");
				System.exit(0);
			}
		}
		return propval;
	}

	public static By Get_by_from_xml(String tagname) {
		By by = null;
		String[] values = getOjectIdentification(tagname);
		switch (values[0].toLowerCase()) {
		case "id":
			by = By.id(values[1]);
			break;
		case "Name":
			by = By.name(values[1]);
			break;
		case "tagname":
			by = By.tagName(values[1]);
			break;
		case "css locator":
			by = By.cssSelector(values[1]);
			break;
		case "linktext":
			by = By.linkText(values[1]);
			break;
		case "partial linkText":
			by = By.partialLinkText(values[1]);
			break;
		case "class":
			by = By.className(values[1]);
			break;
		case "xpath":
			by = By.xpath(values[1]);
			break;
		default:
			System.out.println("No locator found");
			break;
		}
		//System.out.println(by);
		return by;

	}
}