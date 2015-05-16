package gui;

import objects.LangStringGroup;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JSettingsPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	LangStringGroup lsg;

	public JSettingsPanel(final Snake2 snake2) {
		lsg = snake2.lang.getStrings("JSettingsPanel");
		JLabel lblSettings = new JLabel(lsg.getString("Settings").toUpperCase());
		JButton btnMenu = new JButton(lsg.getString("Menu"));
		
		JLabel lblGameSettings = new JLabel(lsg.getString("GameSettings"));
		JLabel lblSleeptime = new JLabel(lsg.getString("Speed") + ": ");
		final JTextField tFieldSleeptime = new JTextField(5);
		
		JLabel lblResolutionSettings = new JLabel(lsg.getString("ResSettings"));
		final JCheckBox ckBoxMaxRes = new JCheckBox(lsg.getString("MaxRes"));
		JLabel lblWidth = new JLabel(lsg.getString("DisplayWidth") + ": ");
		final JTextField tFieldWidth = new JTextField(5);
		JLabel lblHeight = new JLabel(lsg.getString("DisplayHeight") + ": ");
		final JTextField tFieldHeight = new JTextField(5);
		
		JLabel lblLanguageSettings = new JLabel(lsg.getString("LangSettings"));
		JLabel lblLanguage = new JLabel(lsg.getString("Language"));
		String[] languages = snake2.settings.SUPPORTEDLANG;
		final JComboBox<String> comboLang = new JComboBox<>(languages);
		JButton btnNewLang = new JButton(lsg.getString("NewLang"));
		
		lblSleeptime.setToolTipText(lsg.getString("ExplSpeed"));
		comboLang.setToolTipText(lsg.getString("ExplLanguage"));
		lblLanguage.setToolTipText(lsg.getString("ExplLanguage"));
		ckBoxMaxRes.setToolTipText(lsg.getString("ExplLanguage"));
		lblWidth.setToolTipText(lsg.getString("ExplLanguage"));
		tFieldWidth.setToolTipText(lsg.getString("ExplLanguage"));
		lblHeight.setToolTipText(lsg.getString("ExplLanguage"));
		tFieldHeight.setToolTipText(lsg.getString("ExplLanguage"));
		
		//write the values in the TextFields
		tFieldSleeptime.setText(String.valueOf(snake2.settings.SLEEPTIME));
		ckBoxMaxRes.setSelected(snake2.settings.USEMAXRESOLUTION);
		tFieldWidth.setText(String.valueOf(snake2.settings.DISPWIDTH));
		tFieldHeight.setText(String.valueOf(snake2.settings.DISPHEIGHT));
		comboLang.setSelectedItem(snake2.settings.LANG);
		
		//Fonts
		lblSettings.setFont(snake2.FONT_SMALL_TITLE);
		btnMenu.setFont(snake2.FONT_MENUTEXT);
		
		lblGameSettings.setFont(snake2.FONT_SMALL_TITLE);
		lblSleeptime.setFont(snake2.FONT_TEXT);
		tFieldSleeptime.setFont(snake2.FONT_TEXT);
		
		lblResolutionSettings.setFont(snake2.FONT_SMALL_TITLE);
		ckBoxMaxRes.setFont(snake2.FONT_TEXT);
		lblWidth.setFont(snake2.FONT_TEXT);
		tFieldWidth.setFont(snake2.FONT_TEXT);
		lblHeight.setFont(snake2.FONT_TEXT);
		tFieldHeight.setFont(snake2.FONT_TEXT);
		
		lblLanguageSettings.setFont(snake2.FONT_SMALL_TITLE);
		lblLanguage.setFont(snake2.FONT_TEXT);
		comboLang.setFont(snake2.FONT_TEXT);
		btnNewLang.setFont(snake2.FONT_TEXT);
		
		//Layout 
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(0, 0, 20, 0);
		c.gridwidth = 3;
		add(lblSettings, c);
		
		c.gridx = 0;
		c.gridy = 1;
		c.insets = new Insets(0, 0, 0, 0);
		c.gridwidth = 2;
		add(lblGameSettings, c);
		
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 1;
		add(lblSleeptime, c);
		
		c.gridx = 1;
		c.gridy = 2;
		add(tFieldSleeptime, c);
		
		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 2;
		add(lblResolutionSettings, c);
		
		c.gridx = 0;
		c.gridy = 4;
		c.gridwidth = 2;
		add(ckBoxMaxRes, c);
		
		c.gridx = 0;
		c.gridy = 5;
		c.gridwidth = 1;
		add(lblWidth, c);
		
		c.gridx = 1;
		c.gridy = 5;
		add(tFieldWidth, c);
		
		c.gridx = 0;
		c.gridy = 6;
		add(lblHeight, c);
		
		c.gridx = 1;
		c.gridy = 6;
		add(tFieldHeight, c);
		
		c.gridx = 0;
		c.gridy = 7;
		c.gridwidth = 2;
		add(lblLanguageSettings, c);
		
		c.gridx = 0;
		c.gridy = 8;
		c.gridwidth = 1;
		add(lblLanguage, c);
		
		c.gridx = 1;
		c.gridy = 8;
		add(comboLang, c);
		
		c.gridx = 0;
		c.gridy = 9;
		c.gridwidth = 2;
		add(btnNewLang, c);
		
		c.gridx = 0;
		c.gridy = 10;
		c.insets = new Insets(10, 0, 0, 0);
		c.gridwidth = 3;
		add(btnMenu, c);
		
		//ActionListeners
		ActionListener goMenu = new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(comboLang.getSelectedItem() != snake2.settings.LANG) {
					JOptionPane.showConfirmDialog(snake2.mainFrame, lsg.getString("NeedsRestart"), "!!", JOptionPane.OK_OPTION);
				}
				//Write all new settings in the settings-class
				snake2.settings.SLEEPTIME = Integer.valueOf(tFieldSleeptime.getText());
				snake2.settings.USEMAXRESOLUTION = ckBoxMaxRes.isSelected();
				snake2.settings.DISPWIDTH = Integer.valueOf(tFieldWidth.getText());
				snake2.settings.DISPHEIGHT = Integer.valueOf(tFieldHeight.getText());
				snake2.settings.LANG = comboLang.getSelectedItem().toString();
				
				//Change panel back to the Menu-Panel
				snake2.pnlSettings.setVisible(false);
				snake2.pnlMenu.setVisible(true);
			}
		};
		
		ActionListener goNewLang = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				snake2.pnlNewLang.reloadList(snake2);
				snake2.pnlSettings.setVisible(false);
				snake2.pnlNewLang.setVisible(true);
			}
		};
		
		btnMenu.addActionListener(goMenu);
		btnNewLang.addActionListener(goNewLang);
		
		setVisible(false);
	}
}
