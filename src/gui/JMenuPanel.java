package gui;

import objects.LangStringGroup;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JMenuPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	LangStringGroup lsg;

	public JMenuPanel(final Snake2 snake2) {
		lsg = snake2.lang.getStrings("JMenuPanel");
		JLabel lblTitle = new JLabel(snake2.settings.TITLE);
		JButton btnSnake = new JButton(lsg.getString("StartGame"));
		JButton btnSettings = new JButton(lsg.getString("Settings"));
		JButton btnBestScores = new JButton(lsg.getString("Highscores"));
		JButton btnExit = new JButton(lsg.getString("Exit"));
		
		//Fonts
		lblTitle.setFont(snake2.FONT_TITLE);
		btnSnake.setFont(snake2.FONT_MENUTEXT);
		btnSettings.setFont(snake2.FONT_MENUTEXT);
		btnBestScores.setFont(snake2.FONT_MENUTEXT);
		btnExit.setFont(snake2.FONT_MENUTEXT);
		
		//Layout
		setLayout(new GridLayout(5, 1, 0, 10));
		add(lblTitle);
		add(btnSnake);
		add(btnSettings);
		add(btnBestScores);
		add(btnExit);
		
		//ActionListeners
		ActionListener goSnake = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				snake2.mainFrame.setSize(snake2.displayWidth, snake2.displayHeight+snake2.settings.BARWIDHT); //+snake2.BARWIDHT für den Rahmen des OS oben
				snake2.pnlMenu.setVisible(false);
				snake2.pnlGame.setVisible(true);
			}
		};
		
		ActionListener goSettings = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				snake2.pnlMenu.setVisible(false);
				snake2.pnlSettings.setVisible(true);
			}
		};
		
		ActionListener goBestScores = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				snake2.pnlMenu.setVisible(false);
				snake2.pnlHighScores.setVisible(true);
			}
		};
		
		ActionListener goExit = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				snake2.isOpened = false;
			}
		};
		
		btnExit.addActionListener(goExit);
		btnSnake.addActionListener(goSnake);
		btnSettings.addActionListener(goSettings);
		btnBestScores.addActionListener(goBestScores);
		
		setVisible(false);
	}
}
