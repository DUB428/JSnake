package gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class JMenuPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	public JMenuPanel(final Snake2 snake2) {
		JLabel lblTitle = new JLabel(snake2.settings.TITLE);
		JButton btnSnake = new JButton(snake2.lang.UI_START_GAME);
		JButton btnSettings = new JButton(snake2.lang.UI_SETTINGS);
		JButton btnBestScores = new JButton(snake2.lang.UI_HIGHSCORES);
		JButton btnExit = new JButton(snake2.lang.UI_EXIT);
		
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
