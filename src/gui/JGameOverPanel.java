package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class JGameOverPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	JLabel lblReason, lblScore, lblHighScore, lblName;
	JButton btnHighScore;
	JTextField tFieldName;
	int score;
	public JGameOverPanel(final Snake2 snake2) {
		JLabel lblGameOver = new JLabel(snake2.lang.UI_GAMEOVER.toUpperCase());
		lblReason = new JLabel();
		lblScore = new JLabel();
		lblHighScore = new JLabel(snake2.lang.UI_NEWHIGHSCORE.toUpperCase());
		JButton btnAgain = new JButton(snake2.lang.UI_AGAIN);
		JButton btnMenu = new JButton(snake2.lang.UI_MENU);
		btnHighScore = new JButton(snake2.lang.UI_SHOWALLHIGHSCORE);
		lblName = new JLabel(snake2.lang.UI_YOURNAME + ":");
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
				snake2.settings.addHIGHSCORE(score, tFieldName.getText());snake2.pnlGame.btnStart.setText(snake2.lang.UI_START);
				
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
				
				snake2.pnlGame.btnStart.setText("Start");
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
				
				snake2.pnlGame.btnStart.setText(snake2.lang.UI_START);
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
			lblReason.setText(snake2.lang.UI_DEATHWALL);
		} else if(gameOver == 's') {
			lblReason.setText(snake2.lang.UI_DEATHSELF);
		}
		
		lblScore.setText(String.valueOf(score));
		
		lblHighScore.setVisible(highScore);
		btnHighScore.setVisible(highScore);
		lblName.setVisible(highScore);
		revalidate();
		this.score = score;
	}
}
