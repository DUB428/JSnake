package test;

import java.util.ArrayList;

public class Test {

	public static void main(String[] args) {
		//Language lang = new Language();

		ArrayList<String> list = new ArrayList<>();
		System.out.println(list.indexOf("test"));
		
/*		try {
			File xlmFile = new File("settings.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(xlmFile);
			dBuilder = dbFactory.newDocumentBuilder();
			doc.getDocumentElement().normalize();
			
			System.out.println(doc.getDocumentElement().getNodeName());
			NodeList nList = doc.getElementsByTagName("staff");
			Element element = (Element) nList.item(0);
			System.out.println(element.getAttribute("id"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
*/	}

}
