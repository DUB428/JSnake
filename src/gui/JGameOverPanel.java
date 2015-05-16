package gui;

import objects.LangStringGroup;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JGameOverPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	JLabel lblReason, lblScore, lblHighScore, lblName;
	JButton btnHighScore;
	JTextField tFieldName;
	LangStringGroup lsg;
	int score;
	public JGameOverPanel(final Snake2 snake2) {
		lsg = snake2.lang.getStrings("JGameOverPanel");
		JLabel lblGameOver = new JLabel(lsg.getString("GameOver").toUpperCase());
		lblReason = new JLabel();
		lblScore = new JLabel();
		lblHighScore = new JLabel(lsg.getString("NewHighscore").toUpperCase());
		JButton btnAgain = new JButton(lsg.getString("Again"));
		JButton btnMenu = new JButton(lsg.getString("Menu"));
		btnHighScore = new JButton(lsg.getString("ShowAllHighscore"));
		lblName = new JLabel(lsg.getString("YourName") + ":");
		tFieldName = new JTextField(10);
		
		tFieldName.setText(snake2.settings.DEFAULTNAME);
		
		lblHighScore.setVisible(false);
		btnHighScore.setVisible(false);
		
		//Fonts
		lblGameOver.setFont(snake2.FONT_TITLE);
		lblReason.setFont(snake2.FONT_SMALL_TITLE);
		lblScore.setFont(snake2.FONT_TEXT);
		lblHighScore.setFont(snake2.FONT_TEXT);
		btnAgain.setFont(snake2.FONT_TEXT);
		btnMenu.setFont(snake2.FONT_TEXT);
		btnHighScore.setFont(snake2.FONT_TEXT);
		lblName.setFont(snake2.FONT_TEXT);
		tFieldName.setFont(snake2.FONT_TEXT);
		
		//Layout
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		c.gridx = 0;
		c.gridy = 1;
		add(lblGameOver, c);

		c.gridx = 0;
		c.gridy = 2;
		add(lblReason, c);
		
		c.gridx = 0;
		c.gridy = 3;
		add(lblScore, c);
		
		c.gridx = 0;
		c.gridy = 4;
		add(lblHighScore, c);
		
		c.gridx = 0;
		c.gridy = 5;
		add(lblName, c);
		
		c.gridx = 1;
		c.gridy = 5;
		add(tFieldName, c);
		
		c.gridx = 0;
		c.gridy = 7;
		add(btnAgain, c);
		
		c.gridx = 0;
		c.gridy = 8;
		add(btnMenu, c);
		
		c.gridx = 0;
		c.gridy = 9;
		add(btnHighScore, c);
		
		//ActionListeners
		ActionListener goAgain = new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//add Score
				snake2.settings.addHIGHSCORE(score, tFieldName.getText());snake2.pnlGame.btnStart.setText(lsg.getString("Start"));
				
				snake2.isRunning = false;
				snake2.snake.respawn(snake2);
				snake2.gameOver = 'n';
				snake2.food.respawn(snake2);
				snake2.pnlField.repaint();
				snake2.pnlGameOver.setVisible(false);
				snake2.pnlGame.setVisible(true);
				//add Score
				snake2.settings.addHIGHSCORE(score, tFieldName.getText());
				
			}
		};
		
		ActionListener goMenu = new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//add Score
				snake2.settings.addHIGHSCORE(score, tFieldName.getText());
				
				snake2.pnlGame.btnStart.setText(lsg.getString("Start"));
				snake2.isRunning = false;
				snake2.snake.respawn(snake2);
				snake2.gameOver = 'n';
				snake2.food.respawn(snake2);
				snake2.pnlField.repaint();
//				snake2.mainFrame.setSize(snake2.settings.MENUWIDTH, snake2.settings.MENUHEIGHT);
				snake2.pnlGameOver.setVisible(false);
				snake2.pnlMenu.setVisible(true);
				
				
			}
		};
		
		ActionListener goHighScore = new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//add Score
				snake2.settings.addHIGHSCORE(score, tFieldName.getText());
				
				snake2.pnlGame.btnStart.setText(lsg.getString("Start"));
				snake2.isRunning = false;
				snake2.snake.respawn(snake2);
				snake2.gameOver = 'n';
				snake2.food.respawn(snake2);
				snake2.pnlField.repaint();
				snake2.pnlHighScores.updatedContent(snake2);
				snake2.pnlGameOver.setVisible(false);
				snake2.pnlHighScores.setVisible(true);
				
				
			}
		};
		
		btnAgain.addActionListener(goAgain);
		btnMenu.addActionListener(goMenu);
		btnHighScore.addActionListener(goHighScore);
		
		setVisible(false);
	}
	
	public void showGameOver(Snake2 snake2, char gameOver, int score, boolean highScore) {
		if(gameOver == 'w') {
			lblReason.setText(lsg.getString("DeathWall"));
		} else if(gameOver == 's') {
			lblReason.setText(lsg.getString("DeathSelf"));
		}
		
		lblScore.setText(String.valueOf(score));
		
		lblHighScore.setVisible(highScore);
		btnHighScore.setVisible(highScore);
		lblName.setVisible(highScore);
		revalidate();
		this.score = score;
	}
}
