package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

public class JNewLangPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	public JNewLangPanel(final Snake2 snake2) {
		
		JButton btnMenu = new JButton(snake2.lang.UI_MENU);
		JScrollPane scrlWords = new JScrollPane();
		JTable tblWords = new JTable();
		String[][] content = {};
		String[] columnNames = {snake2.lang.UI_CURRENTLANG, snake2.lang.UI_NEWLANG};
		DefaultTableModel mdlWords = new DefaultTableModel(content, columnNames);
		JButton btnAddLang = new JButton(snake2.lang.UI_ADDLANG);
		JLabel lblNewLang = new JLabel(snake2.lang.UI_NEWLANG_NAME);
		JTextField tFieldNewLang = new JTextField(10);
		JLabel lblNewLangShort = new JLabel(snake2.lang.UI_NEWLANG_NAME);
		JTextField tFieldNewLangShort = new JTextField(2);
		
/*		//reading the xmlFile and writing the strings of the current languages into the table
		Document doc = null;
		try {
			File xmlFile = new File("lang_DE.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder;
			dBuilder = dbFactory.newDocumentBuilder();
			doc = dBuilder.parse(xmlFile);
			dBuilder = dbFactory.newDocumentBuilder();
			doc.getDocumentElement().normalize();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
*/
		
		tblWords.setModel(mdlWords);
		scrlWords.setViewportView(tblWords);
		
		
		//Writing the strings of the current language into the table
		for(int i = 0;i < snake2.lang.allStrings.size(); i++) {
			String[] contentRow = {snake2.lang.allStrings.get(i), ""};
			mdlWords.addRow(contentRow);
		}
		
		//Fonts
		btnMenu.setFont(snake2.FONT_SMALL_TITLE);
		scrlWords.setFont(snake2.FONT_TEXT);
		btnAddLang.setFont(snake2.FONT_SMALL_TITLE);
		lblNewLang.setFont(snake2.FONT_TEXT);
		lblNewLangShort.setFont(snake2.FONT_TEXT);
		
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		c.gridx = 0;
		c.gridy = 2;
		c.anchor = GridBagConstraints.LINE_START;
		add(lblNewLang, c);
		
		c.gridx = 1;
		c.gridy = 2;
		c.anchor = GridBagConstraints.LINE_END;
		add(tFieldNewLang, c);
		
		c.gridx = 0;
		c.gridy = 3;
		c.anchor = GridBagConstraints.LINE_START;
		c.insets = new Insets(0, 0, 10, 0);
		add(lblNewLangShort, c);
		
		c.gridx = 1;
		c.gridy = 3;
		c.anchor = GridBagConstraints.LINE_END;
		add(tFieldNewLangShort, c);
		
		c.gridx = 0;
		c.gridy = 4;
		c.gridwidth = 2;
		c.anchor = GridBagConstraints.BASELINE;
		c.insets = new Insets(0, 0, 0, 0);
		add(scrlWords, c);
		
		c.gridx = 0;
		c.gridy = 5;
		c.gridwidth = 2;
		add(btnAddLang, c);
		
		c.gridx = 0;
		c.gridy = 6;
		c.gridwidth = 2;
		add(btnMenu, c);
		
		//ActionListener
		ActionListener goMenu = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				snake2.pnlNewLang.setVisible(false);
				snake2.pnlMenu.setVisible(true);
			}
		};
		
		ActionListener goAddLang = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		};
	
		btnMenu.addActionListener(goMenu);
		btnAddLang.addActionListener(goAddLang);
		
		setVisible(false);
	}
	
}
