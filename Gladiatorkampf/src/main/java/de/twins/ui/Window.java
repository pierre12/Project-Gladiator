package de.twins.ui;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Window extends JPanel{

	public Window(int width,int height,String title,Game game)
	{
		JFrame frame = new JFrame(title);
		//Sets the size of the window
		frame.setMinimumSize(new Dimension(width,height));
		frame.setMaximumSize(new Dimension(width,height));
		frame.setPreferredSize(new Dimension(width,height));
		
		//closes jframe when clicking the x button 
		//otherwise by clicking x button the window would just disappear but continue running
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		//Setting to null will display it in the midle of the screen
		frame.setLocationRelativeTo(null);
		frame.add(game);
		frame.setVisible(true);
		game.start();
		
	}
}
