package snakegame;
import java.awt.Color;

import javax.swing.JFrame;
public class Main {
	public static void main (String[] args) {
		JFrame frame=new JFrame("Snake Game");//Creating the game frame 
		frame.setBounds(10,10,905,700); //Creating the boundary for the game
		frame.setResizable(false); //assigning the game resizing to false 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		
		GamePanel panel =new GamePanel(); //creating game panel
		
		frame.add(panel); //adding the component 
		panel.setBackground(Color.RED); //adding background color for the panel
		
		frame.setVisible(true);  //default the jframe will be not visible 
		
		
		
	    
		
				
	}

}
