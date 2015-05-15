package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

public class JHighScoresPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	
	DefaultTableModel mdlHighScores;
	
	public JHighScoresPanel(final Snake2 snake2) {
		JTable tblHighScores = new JTable();
		String[][] settings = {};
		String[] columnNames = {snake2.lang.UI_PLAYERNAME, snake2.lang.UI_SCORE};
		mdlHighScores = new DefaultTableModel(settings, columnNames);
		JScrollPane spHighScores = new JScrollPane();
		JButton btnMenu = new JButton(snake2.lang.UI_MENU);
		JLabel lblHighScores = new JLabel(snake2.lang.UI_HIGHSCORES.toUpperCase());
		JButton btnReset = new JButton(snake2.lang.UI_RESETHIGHSCORES);
		
		tblHighScores.setEnabled(false);
		spHighScores.setViewportView(tblHighScores);
		
		for(int i = 0; i < snake2.settings.HIGHSCORE.length; i++) {
			String[] rowsettings = {snake2.settings.NAMES[i], String.valueOf(snake2.settings.HIGHSCORE[i])};
			mdlHighScores.addRow(rowsettings);
		}
		
		tblHighScores.setModel(mdlHighScores);
		
		//Fonts
		lblHighScores.setFont(snake2.FONT_SMALL_TITLE);
		tblHighScores.setFont(snake2.FONT_TEXT);
		tblHighScores.getTableHeader().setFont(snake2.FONT_TEXT);
		btnMenu.setFont(snake2.FONT_MENUTEXT);
		btnReset.setFont(snake2.FONT_MENUTEXT);
		
		//Layout
		setLayout(new GridBagLayout());
		GridBagConstraints c  = new GridBagConstraints();
		
		c.gridx = 0;
		c.gridy = 0;
		add(lblHighScores, c);
		
		c.gridx = 0;
		c.gridy = 1;
		c.ipadx = 170;
		c.ipady = 100;
		add(spHighScores, c);
		
		c.gridx = 0;
		c.gridy = 2;
		c.ipadx = 0;
		c.ipady = 0;
		c.insets = new Insets(20, 0, 0, 0);
		add(btnReset, c);
		
		c.gridx = 0;
		c.gridy = 3;
		add(btnMenu, c);
		
		//ActionListeners
		ActionListener goMenu = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				snake2.pnlHighScores.setVisible(false);
				snake2.pnlMenu.setVisible(true);
			}
		};
		
		ActionListener goReset = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int sure = JOptionPane.showConfirmDialog(snake2.mainFrame, snake2.lang.UI_SURE_DELET_HIGHSCORES, snake2.lang.UI_SURE_DELET, JOptionPane.YES_NO_OPTION);
				if(sure == 0) {
					snake2.settings.resetHIGHSCORE();
					updatedContent(snake2);
				}
			}
		};
		
		btnMenu.addActionListener(goMenu);
		btnReset.addActionListener(goReset);
		
		setVisible(false);
	}
	
	public void updatedContent(Snake2 snake2) {
		
		int length = mdlHighScores.getRowCount();
		for(int i = length-1; i >= 0; i--) {
			mdlHighScores.removeRow(i);	
		}
		
		for(int i = 0; i < snake2.settings.HIGHSCORE.length; i++) {
			String[] rowsettings = {snake2.settings.NAMES[i], String.valueOf(snake2.settings.HIGHSCORE[i])};
			mdlHighScores.addRow(rowsettings);
		}
	}
}
