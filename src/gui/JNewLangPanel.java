package gui;

import objects.LangStringGroup;
import objects.Language;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JNewLangPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	LangStringGroup lsg;
	DefaultTableModel[] mdlWords;
	Language newLang;

	public JNewLangPanel(final Snake2 snake2) {
		lsg = snake2.lang.getStrings("JNewLangPanel");
		String[][] content = {};
		String[] columnNames = {lsg.getString("CurrentLang"), lsg.getString("NewLang")};
		newLang = snake2.lang.getEmptyLanguage(snake2, "--");

		final JButton btnMenu = new JButton(lsg.getString("Menu"));
		JButton btnAddLang = new JButton(lsg.getString("AddLang"));
		JLabel lblNewLang = new JLabel(lsg.getString("NewLangName"));
		JTextField tFieldNewLang = new JTextField(10);
		JLabel lblNewLangShort = new JLabel(lsg.getString("NewLangName"));
		final JTextField tFieldNewLangShort = new JTextField(2);

		JTabbedPane tabbedPane = new JTabbedPane();
		//Create different JPanels with scrollPane and table. For each Panel one
		String[] panels = newLang.getAllPanelsName();
		JPanel[] pnlWords = new JPanel[newLang.getAmountPanels()];
		JScrollPane[] scrollPane = new JScrollPane[newLang.getAmountPanels()];
		JTable[] tblWords = new JTable[newLang.getAmountPanels()];
		mdlWords = new DefaultTableModel[newLang.getAmountPanels()];

		//Check for Doubles in the language
		//newLang.checkforDoubles();

		for(int i = 0; i < newLang.getAmountPanels(); i++) {
			//add panels to pnlWordsMain
			tblWords[i] = new JTable();
			mdlWords[i] = new DefaultTableModel(content, columnNames);
			scrollPane[i] = new JScrollPane();
			pnlWords[i] = new JPanel();

			//Writing the strings of the current language of the panel into the table
			String[] lsgNames = newLang.getStrings(panels[i]).getAllNames();
			for(int j = 0; j < lsgNames.length; j++) {
				String[] rowData = {lsgNames[j], ""};
				mdlWords[i].addRow(rowData);
			}
			tblWords[i].setModel(mdlWords[i]);
			pnlWords[i].add(scrollPane[i]);
			scrollPane[i].setViewportView(tblWords[i]);
			tabbedPane.add(pnlWords[i], panels[i]);
			scrollPane[i].setFont(snake2.FONT_TEXT);
		}
		
		//Fonts
		btnMenu.setFont(snake2.FONT_SMALL_TITLE);
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
		add(tabbedPane, c);
		
		c.gridx = 0;
		c.gridy = 5;
		c.gridwidth = 2;
		add(btnAddLang, c);
		
		c.gridx = 0;
		c.gridy = 6;
		c.gridwidth = 2;
		add(btnMenu, c);
		
		//ActionListener
		final ActionListener goMenu = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				snake2.pnlNewLang.setVisible(false);
				snake2.pnlMenu.setVisible(true);
			}
		};
		
		ActionListener goAddLang = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//change all LSGs
				if(tFieldNewLangShort.getText().length() != 2) {
					JOptionPane.showConfirmDialog(snake2.mainFrame, lsg.getString("LangShortNotDefined"), "!!", JOptionPane.ERROR_MESSAGE, JOptionPane.OK_OPTION);
					return;
				}
				//gets all the new content for each panel
				String[] allPanelsName = newLang.getAllPanelsName();
				for(int i = 0; i < newLang.getAmountPanels(); i++) {
					LangStringGroup lsg = newLang.getStrings(allPanelsName[i]);
					String[] allNames = lsg.getAllNames();
					for(int j = 0; j < lsg.getAmountStrings(); j++) {
						String newContent = mdlWords[i].getValueAt(j, 1).toString();
						lsg.changeStringContent(allNames[j], newContent);
					}
				}
				newLang.changeLanguage(tFieldNewLangShort.getText());
				newLang.writeStringsAndCreateFile();
				btnMenu.doClick();

			}
		};
	
		btnMenu.addActionListener(goMenu);
		btnAddLang.addActionListener(goAddLang);
		
		setVisible(false);
	}

	public void reloadList(Snake2 snake2) {
		//Rewriting the strings of the current language into the table
		//Clear mdlWords
		for(int i = 0; i < mdlWords.length; i++) {
			while(mdlWords[i].getRowCount() > 0) {
				mdlWords[i].removeRow(0);
			}
		}

		String[] panels = newLang.getAllPanelsName();
		for(int i = 0; i < newLang.getAmountPanels(); i++) {
			//Writing the strings of the current language of the panel into the table
			String[] lsgNames = newLang.getStrings(panels[i]).getAllNames();
			for (int j = 0; j < lsgNames.length; j++) {
				String[] rowData = {lsgNames[j], ""};
				mdlWords[i].addRow(rowData);
			}
		}
	}
	
}
