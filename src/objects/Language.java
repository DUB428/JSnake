package objects;

import gui.Snake2;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.ArrayList;

public class Language {

	ArrayList<LangStringGroup> LSGPanels = new ArrayList<>();
	ArrayList<String> strPanels = new ArrayList<>();

	File langFile;
	public Language(Snake2 snake2) {
		langFile = new File("lang_" + snake2.settings.LANG + ".xml");
		readStrings();
		
	}

	public Language(Snake2 snake2, String lang) {
		langFile = new File("lang_" + lang + ".xml");
		readStrings();
	}

	public void changeLanguage(String newLang){
		//changes the language. Will result that a new xml-File will be generated, as soon as writeStrings() will be called
		//used in JNewLangPanel
		langFile = new File("lang_" + newLang + ".xml");
	}
	
	public boolean readStrings() {
	//reads all strings out of the xml-file from all panels and writes it in the variables
		Document doc = null;
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder;
			dBuilder = dbFactory.newDocumentBuilder();
			doc = dBuilder.parse(langFile);
			dBuilder = dbFactory.newDocumentBuilder();
			doc.getDocumentElement().normalize();

			NodeList nList = doc.getElementsByTagName("Panel");	//NodeList with all the Panels
			for(int i = 0; i < nList.getLength(); i++) {
				Element element = (Element) nList.item(i);
				//creates new LangStringGroup with the Name of the Panel
				LangStringGroup lsg = new LangStringGroup(element.getAttribute("id"));
				//add all the Strings of the Panel into the LangStringGroup
				for(int j = 0; j < element.getElementsByTagName("String").getLength(); j++) {
					Element element1 = (Element)element.getElementsByTagName("String").item(j);
					lsg.addString(element1.getAttribute("id"), element1.getTextContent());
				}
				addLangStringGroup(lsg);
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return false;
		}

		return  true;
	}

	public void writeStringsAndCreateFile() {
		//writes all what is written in the variables(what you may have changed there since the last readStrings())
		//in a new xml-file
		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

			//root element
			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("UserInterface");
			doc.appendChild(rootElement);

			//panel elements
			for(int i = 0; i < LSGPanels.size(); i++) {
				Element panel1 = doc.createElement("Panel");
				panel1.setAttribute("id", strPanels.get(i));
				rootElement.appendChild(panel1);

				//String elements
				String[] allNames = LSGPanels.get(i).getAllNames();
				String[] allContent = LSGPanels.get(i).getAllContent();
				for(int j = 0; j < allNames.length; j++) {
					Element string = doc.createElement("String");
					string.appendChild(doc.createTextNode(allContent[j]));
					string.setAttribute("id", allNames[j]);
					panel1.appendChild(string);
				}
			}

			//write the contetn into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transfromer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(langFile);
			transfromer.transform(source, result);
		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		}
	}

	public LangStringGroup getStrings(String panel) {
		//returns all strings of a given panel in the default language back. It takes it from the variables
		//if panel does not exist, it returns an empty LangStringGroup
		if(strPanels.indexOf(panel) == -1) {
			//add created LangStringGroup, so it only will be created empty once and the amount of strings get countable
			LangStringGroup lsg = new LangStringGroup(panel);
			addLangStringGroup(lsg);
			return lsg;
		}
		return LSGPanels.get(strPanels.indexOf(panel));
	}

	/*public LangStringGroup getStrings(String panel, String language) {
		//returns all strings of a given panel in the given language. It takes it from the variables


	}*/

	public void forgetString (String panel) {
		//removes a String from a panel
	}

	public void addLangStringGroup(LangStringGroup lsg) {
		//adds a new LangStringGroup
		LSGPanels.add(lsg);
		strPanels.add(lsg.getPanelName());
	}

	public void changeLangStringGroup(LangStringGroup newLsg, String oldPanel) {
		//change a LangStringGroup
		int index = strPanels.indexOf(oldPanel);
		LSGPanels.set(index, newLsg);
	}

	public int getAmountStrings() {
		int amount = 0;
		for(int i = 0; i < LSGPanels.size(); i++) {
			amount += LSGPanels.get(i).getAmountStrings();
		}
		return amount;
	}

	public int getAmountPanels() {
		return strPanels.size();
	}

	public String[] getAllPanelsName() {
		String[] panels = new String[strPanels.size()];
		for(int i = 0; i < strPanels.size(); i++) {
			panels[i] = strPanels.get(i);
		}
		return panels;
	}

	public String[] getAllStringsContent() {
		//returns all content of all Strings in one single array (used in the JNewLangPanel)
		String[] allContent = new String[getAmountStrings()];
		int arrayPos = 0;
		for(int i = 0; i < LSGPanels.size(); i++) {
			String[] allContentLSG = LSGPanels.get(i).getAllContent();
			for(int j = 0; j < LSGPanels.get(i).getAmountStrings(); j++) {
				allContent[arrayPos] = allContentLSG[j];
				arrayPos++;
			}
		}
		return allContent;
	}

	public Language getEmptyLanguage(Snake2 snake2, String lang) {
		//returns an Language (this class) with all Strings but without an content and the langFile set to an other language
		Language newLang = new Language(snake2, lang);
		//copy all the LangStringGroups into the newLang but without the content
		for(int i = 0; i < LSGPanels.size(); i++) {
			LangStringGroup lsg = new LangStringGroup(strPanels.get(i));
			String[] allNames = LSGPanels.get(i).getAllNames();
			for(int j = 0; j < allNames.length; j++) {
				lsg.addString(allNames[j], "");
			}
			newLang.addLangStringGroup(lsg);
		}
		return newLang;
	}

	public void checkforDoubles() {
		//check if there are any panels having the same name and in those panels any Strings are having the same name
		ArrayList<Integer> indexDoubles = new ArrayList<>();
		for(int i = 0; i < strPanels.size(); i++) {
			for(int j = 0; j < i; j++) {
				if(strPanels.get(i) == strPanels.get(j)) {
					indexDoubles.add(i);
				}
			}
			for(int j = i+1; j < strPanels.size(); j++) {
				if(strPanels.get(i) == strPanels.get(j)) {
					indexDoubles.add(i);
				}
			}
		}
		System.out.println(indexDoubles);
	}
}
