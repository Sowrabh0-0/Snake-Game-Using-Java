package snakegame;


import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.ImageIcon;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;




/**
 * @author sowrabh
 *
 */
public class GamePanel extends JPanel implements ActionListener,KeyListener{
	
	
	// Declaring the array for the position of the body snake 
	private int[] snakexlength=new int[750];
	private int[] snakeylength=new int[750];
	
	//Declaring the body length of the snake
	private int lengthofsnake=3;
	
	//Declaring the boolean type for direction of the snake
	private boolean left_snake=false;
	private boolean right_snake=true;
	private boolean up_snake=false;
	private boolean down_snake=false;
	
	//Declaring the fruit in the array
	private int[] xPos= {25,50,75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,525,550,575,600,625,650,675,700,725,750,775,800,825,850};
	private int[] yPos= {75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,525,550,575,600,625};
	
		
	//Declaring variable named random to print the fruit in random position in the snake board
	private Random random=new Random();
	private int fruitx,fruity;
	
	
	//Declaring the variable to check the initial stage of the snake 
	private int moves=0;
	
	//adding the score variable to calculate the score 
	private int score=0;

	//adding the game over variable
	private boolean gameOver=false;
	
	
	//Declaring the variable for drawing every 100ms of the snake 
	private Timer timer;//using the timer type for drawing the snake
	private final int DELAY=115; //Assigning the delay for drawing the snake 
	
	//Adding the game title
	private ImageIcon snaketitle = new ImageIcon(getClass().getResource("snaketitle.jpg"));
	
	//Adding the body snake image
	private ImageIcon leftmouth = new ImageIcon(getClass().getResource("leftmouth.png"));
	private ImageIcon rightmouth = new ImageIcon(getClass().getResource("rightmouth.png"));
	private ImageIcon upmouth = new ImageIcon(getClass().getResource("upmouth.png"));
	private ImageIcon downmouth = new ImageIcon(getClass().getResource("downmouth.png"));
	private ImageIcon snakeimage = new ImageIcon(getClass().getResource("snakeimage.png"));
	private ImageIcon enemy = new ImageIcon(getClass().getResource("enemy.png"));
	
	
	//creating sound 
	Sound sound =new Sound();
	
	
	GamePanel(){
		
		//adding the keylistener to use the keys 
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(true);
		//initialization of the timer in the constructor
		javax.swing.Timer t = new javax.swing.Timer(DELAY, this);
		t.start();
		
		//we are setting the fruit method 
		newFruit();
	
	}
	
	private void newFruit() {
		fruitx=xPos[random.nextInt(34)];
		fruity=yPos[random.nextInt(23)];
		
		for(int i=lengthofsnake-1;i>=0;i--) {
			if(snakexlength[i]==fruitx && snakeylength[i]==fruity) {
				newFruit();
			}
		}
		
		
	}
	

	public void paint (Graphics g) {
		super.paint(g); //to change the body of generated methods
		
		g.setColor(Color.WHITE);
		g.drawRect(24,10,851,55); //Draws the title border white
		g.drawRect(24,74,851,576); //Draws the game board border
		
		
		snaketitle.paintIcon(this, g, 25, 11); //game title
		g.setColor(Color.BLACK); //Adding the board color
		if(score == 0) {
			g.setColor(Color.BLACK);
		}
		else if( score <= 10) {
			g.setColor(Color.DARK_GRAY);
		}
		else if(score <=15) {
			g.setColor(Color.YELLOW);
		}
		else if(score <=20) {
			g.setColor(Color.WHITE);
		}
		else {
			g.setColor(Color.BLACK);
		}
		
		g.fillRect(25,75,850,575); //Drawing the main board 
		
		
		//Drawing the snake 
		if (moves==0) {
			snakexlength[0]=100;  //position of the head of the snake 
			snakexlength[1]=75;   //position of the body of the snake 
			snakexlength[2]=50;
			
			snakeylength[0]=100;  //position of the head of the snake from the top which is from y axis 
			snakeylength[1]=100;   //position of the body of the snake from the top which is from y axis
			snakeylength[2]=100;
			
		}
		
		
		//Drawing the head of the snake
		if (left_snake) {
			leftmouth.paintIcon(this, g, snakexlength[0], snakeylength[0]);
			
		}
		if (right_snake) {
			rightmouth.paintIcon(this, g, snakexlength[0], snakeylength[0]);
			
		}
		if (up_snake) {
			upmouth.paintIcon(this, g, snakexlength[0], snakeylength[0]);
			
		}
		if (down_snake) {
			downmouth.paintIcon(this, g, snakexlength[0], snakeylength[0]);
			
		}
		
		
		//Drawing the body of the snake
		for(int i=1;i<lengthofsnake;i++) {
			snakeimage.paintIcon(this, g, snakexlength[i],snakeylength[i]);
		}
		
		//Drawing the fruit 
		enemy.paintIcon(this, g, fruitx, fruity);	
		
		//Drawing the game over
		if(gameOver) {
			g.setColor(Color.WHITE);
			g.setFont(new Font("Arial",Font.BOLD,50));
			g.drawString("Game Over", 300, 300);
			
			g.setFont(new Font("Arial",Font.PLAIN,20));
			g.drawString("Press ENTER to restart", 320, 350);
		}
		
		//Drawing score and length
		
		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial",Font.PLAIN,14));
		g.drawString("Score: "+score,750,30);
		g.drawString("Length: "+lengthofsnake,750,50);
		
		g.dispose(); //after the image got printed it will remove the image 
		
	}
	//moving the snake 
	public void actionPerformed(ActionEvent e) {
		
		//using if statement for moving the snake from x axis 
		for (int i=lengthofsnake-1;i>0;i--)//using with for loop we will able to move the body of the snake also 
		{
			snakexlength[i]=snakexlength[i-1];
			snakeylength[i]=snakeylength[i-1];
		}
		if(left_snake) {
			snakexlength[0]=snakexlength[0]-25;
		}
		if(right_snake) {
			snakexlength[0]=snakexlength[0]+25;
		}
		
		//using if statement for moving the snake from y axis
		if(up_snake) {
			snakeylength[0]=snakeylength[0]-25;	
		}
		if(down_snake) {
			snakeylength[0]=snakeylength[0]+25;
		}
		//creating the condition for snake to come the other side of the board
		if (snakexlength[0]>850) {
			snakexlength[0]=25;
		}
		if (snakexlength[0]<25) {
			snakexlength[0]=850;
		}
		
		if (snakeylength[0]>625) {
			snakeylength[0]=75;
		}
		if (snakeylength[0]<75) {
			snakeylength[0]=625;
		}
		//creating the method for eating the fruit
	
		collidesWithFruit();
		collidesWithBody();
		
		repaint();
		
	}
	
	public void playSE(int i) {
		sound.setFile(i);
		sound.play();
	}
	

	@Override
	public void keyPressed(KeyEvent e){
		if(e.getKeyCode()==KeyEvent.VK_ENTER) {
			restart();
		}
		if(e.getKeyCode()==KeyEvent.VK_LEFT && (!right_snake)){
			left_snake=true;
			right_snake=false;
			up_snake=false;
			down_snake=false;
			moves++;
		}
		if(e.getKeyCode()==KeyEvent.VK_RIGHT && (!left_snake)){
			left_snake=false;
			right_snake=true;
			up_snake=false;
			down_snake=false;
			moves++;
		}
		if(e.getKeyCode()==KeyEvent.VK_UP && (!down_snake)){
			left_snake=false;
			right_snake=false;
			up_snake=true;
			down_snake=false;
			moves++;
		}
		if(e.getKeyCode()==KeyEvent.VK_DOWN && (!up_snake)){
			left_snake=false;
			right_snake=false;
			up_snake=false;
			down_snake=true;
			moves++;
		}
		
	}
	private void collidesWithFruit() {
		if(snakexlength[0]==fruitx && snakeylength[0]==fruity) {
			newFruit();
			playSE(0);
			lengthofsnake++;
			score+=2;
		}
		
	}
	private void collidesWithBody() {
		for(int i=lengthofsnake-1;i>0;i--) {
			if(snakexlength[i]==snakexlength[0] && snakeylength[i]==snakeylength[0]) {
				javax.swing.Timer t = new javax.swing.Timer(DELAY, this);
				t.stop();
				gameOver=true;
				playSE(1);
			}
		}
		
	}
	private void restart() {
		gameOver=false;
		moves=0;
		score=0;
		lengthofsnake=3;
		left_snake=false;
		right_snake=true;
		up_snake=false;
		down_snake=false;
		javax.swing.Timer t = new javax.swing.Timer(DELAY, this);
		t.start();
		repaint();
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
		
	}
}
