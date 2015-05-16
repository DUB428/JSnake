package gui;

import objects.LangStringGroup;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
 * The debugmode information labels are not all using strings from Language class
 */
public class JGamePanel extends JPanel{
	private static final long serialVersionUID = 1L;
	
	JButton btnStart;
	JLabel lblPosX;
	JLabel lblPosY;
	JLabel lblDirection;
	JLabel lblScore;
	JLabel lblFrameSize;
	JLabel lblFieldSize;
	JLabel lblSizeOfAField;

	LangStringGroup lsg;
	
	public JGamePanel(final Snake2 snake2) {
		lsg = snake2.lang.getStrings("JGamePanel");
		btnStart = new JButton(lsg.getString("Start"));
		final JButton btnSlowMotion = new JButton(lsg.getString("SlowmotionOn"));
		final JButton btnMenu = new JButton(lsg.getString("Menu"));
		lblPosX = new JLabel(lsg.getString("PosX") + ": ");
		lblPosY = new JLabel(lsg.getString("PosY") + ": ");
		lblDirection = new JLabel(lsg.getString("Direction") + ": ");
		lblScore = new JLabel(lsg.getString("Score") + ": ");
		lblFrameSize = new JLabel("FrameSize: ");
		lblFieldSize = new JLabel("FieldSize: ");
		lblSizeOfAField = new JLabel("SizeOfAField: ");
		
		//Fonts
		btnStart.setFont(snake2.FONT_MENUTEXT);
		btnSlowMotion.setFont(snake2.FONT_MENUTEXT);
		btnMenu.setFont(snake2.FONT_MENUTEXT);
		lblScore.setFont(snake2.FONT_TEXT);
		lblPosX.setFont(snake2.FONT_SMALL_TEXT);
		lblPosY.setFont(snake2.FONT_SMALL_TEXT);
		lblDirection.setFont(snake2.FONT_SMALL_TEXT);
		lblFrameSize.setFont(snake2.FONT_SMALL_TEXT);
		lblFieldSize.setFont(snake2.FONT_SMALL_TEXT);
		lblSizeOfAField.setFont(snake2.FONT_SMALL_TEXT);
		
		//set Visibility of the positions and direction labels and Slowmotionmode button, so they are off, it deactivated
		btnSlowMotion.setVisible(snake2.settings.DEBUGMODE);
		lblPosX.setVisible(snake2.settings.DEBUGMODE);
		lblPosY.setVisible(snake2.settings.DEBUGMODE);
		lblDirection.setVisible(snake2.settings.DEBUGMODE);
		lblFrameSize.setVisible(snake2.settings.DEBUGMODE);
		lblFieldSize.setVisible(snake2.settings.DEBUGMODE);
		lblSizeOfAField.setVisible(snake2.settings.DEBUGMODE);
		
		//Layout
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		c.gridx = 0;
		c.gridy = 0;
		c.ipadx = 20;
		add(btnStart,c);
		
		c.gridx = 0;
		c.gridy = 1;
		c.ipadx = 20;
		add(btnSlowMotion,c);
		
		
		c.gridx = 0;
		c.gridy = 3;
		c.ipadx = 20;
		add(btnMenu,c);
		

		c.gridx = 0;
		c.gridy = 4;
		c.ipadx = 20;
		add(lblScore,c);
		
		c.gridx = 0;
		c.gridy = 5;
		c.ipadx = 20;
		add(lblPosX,c);
		
		c.gridx = 0;
		c.gridy = 6;
		c.ipadx = 20;
		add(lblPosY,c);
		
		c.gridx = 0;
		c.gridy = 7;
		c.ipadx = 20;
		add(lblDirection,c);
		
		c.gridx = 0;
		c.gridy = 8;
		c.ipadx = 20;
		add(lblFrameSize,c);
		
		c.gridx = 0;
		c.gridy = 9;
		c.ipadx = 20;
		add(lblFieldSize,c);
		
		c.gridx = 0;
		c.gridy = 10;
		c.ipadx = 20;
		add(lblSizeOfAField,c);
		
		c.gridx = 1;
		c.gridy = 0;
		c.ipadx = snake2.pnlField.width;
		c.ipady = snake2.pnlField.height;
		c.gridheight = 15;
		add(snake2.pnlField ,c);
		
		//ActionListeners
		ActionListener goStart = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(snake2.gameOver != 'n') {
					btnStart.setText(lsg.getString("Pause"));
					snake2.isRunning = true;
					snake2.snake.respawn(snake2);
					snake2.gameOver = 'n';
					snake2.food.respawn(snake2);
					snake2.pnlField.repaint();
				}else if(snake2.isRunning) {
					btnStart.setText(lsg.getString("Start"));
					snake2.isRunning = false;
				} else {
					btnStart.setText(lsg.getString("Pause"));
					snake2.isRunning = true;
				}
			}
		};
		
		ActionListener goSlowMotion = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(snake2.slowMotion) {
					btnSlowMotion.setText(lsg.getString("SlowmotionOn"));
					snake2.settings.SLEEPTIME /= 20;
					snake2.slowMotion = false;
				} else {
					btnSlowMotion.setText(lsg.getString("SlowmotionOff"));
					snake2.settings.SLEEPTIME *= 20;
					snake2.slowMotion = true;
				}
			}
		};
		
		ActionListener goMenu = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				snake2.mainFrame.setSize(snake2.settings.MENUWIDTH, snake2.settings.MENUHEIGHT);
				if(snake2.isRunning) {
					btnStart.doClick();
				}
				snake2.pnlGame.setVisible(false);
				snake2.pnlMenu.setVisible(true);
			}
		};
		
		btnStart.addActionListener(goStart);
		btnSlowMotion.addActionListener(goSlowMotion);
		btnMenu.addActionListener(goMenu);
		
		setVisible(false);
	}

}
