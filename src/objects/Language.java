package objects;

import gui.Snake2;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;

import org.w3c.dom.Document;

public class Language {

	public ArrayList<LangStringGroup> panels = new ArrayList<>();
	
	File langFile;
	
	public Language(Snake2 snake2) {
		langFile = new File("local_" + snake2.settings.LANG + ".xml");

		for(int i = 0; i < panels.size(); i++) {
			readStrings(panels.get(i).getPanelName());
		}
		
	}
	
	public LangStringGroup readStrings(String panel) {
	//reads all strings out of the xml-file from a panel and writes it in the variables
		Document doc = null;
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder;
			dBuilder = dbFactory.newDocumentBuilder();
			doc = dBuilder.parse(langFile);
			dBuilder = dbFactory.newDocumentBuilder();
			doc.getDocumentElement().normalize();

			doc.
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public void writeStrings(String panel) {
	//writes all what is written in the variables (what you may have changed there since the last readStrings())
	//in the xml-file

	}

	public LangStringGroup getStrings(String panel) {
	//returns all strings of a given panel in the default language back. It takes it from the variables
	}

	public LangStringGroup getStrings(String panel, String language) {
	//returns all strings of a given panel in the given language. It takes it from the variables
	}

	public void forgetString (String panel) {
	//removes a String from a panel
	}
}
