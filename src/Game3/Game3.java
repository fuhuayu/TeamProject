package Game3;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.Timer;
import javax.swing.JLabel;
import javax.swing.JPanel;

import OverallGame.OverallGame;

/**
 * @author Brendan, Danielle, David, Huayu and Zhanglong
 * @version 0.1
 * @since   2015-11-02
 * The Game Handler for the final game
 * This will handle all logic, Input and Graphics for the final game
 */
public class Game3 implements java.io.Serializable{
	private double 	time 	;
	private int 	score	;
	private int 	money   ;
	private	int		tickCount;
	private ArrayList<Plant> 	plants ;
	private ArrayList<Runoff> 	enemies ;
	private ArrayList<Mussel> 	mussels ;
	private ArrayList<Tile> 	tiles ;
	private boolean gameRunning ;
	private boolean gameOver   ;
	private OverallGame bigGame;
	private JPanel gamePanel ;
	private JPanel bigGamePanel;
	private JFrame gameFrame;
	private JLabel timeAndScore;
	private long startTime ;
	private Timer timer;
	
	/**
	 * Game Constructor
	 * Produces the initial game state 
	 * (time = 300.0, score = 0, money = 100, no plants or runoff, 5 Mussels in pseudorandom positions)
	 * @param bigGame - The handler for the entire game
	 */
	public Game3(OverallGame bigGame) {
		this.time	=	40.0	;
		this.score	=	0	;
		this.money	=	100	;
		/*
		 * In the current build,  plants are placed at [0, 0], [0 1] and [1,0]
		 */
		this.plants	=	new ArrayList<Plant>();
		this.tiles	= 	new ArrayList<Tile>() ; 
		for (int i = 0 ; i < 28 ; i++) {
			this.tiles.add(new Tile(i/7, i%7));
		}
		this.enemies	=	new ArrayList<Runoff>();
		this.mussels	=	new ArrayList<Mussel>();
		this.gameRunning	=	true;
		this.gameOver	=	false;
		this.bigGame	=	bigGame;
		this.gameFrame 		=	bigGame.getGameWindow().getFrame();
		addMussel();	addMussel();
		addMussel();	addMussel();
		this.startTime 	= System.currentTimeMillis();
		this.tickCount = 0;
		initPanel(gameFrame);
		timer.start();
	}

	/**
	 * Createst eh game panel and sets up the timer
	 * @param frame
	 */
	public void initPanel(JFrame frame) {
		this.bigGamePanel	= (JPanel)frame.getContentPane();
		this.gamePanel		= new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				for (Mussel current : getMussels()) {
					g.drawImage(current.getMusselDrawing(), current.getXLoc(), current.getYLoc(), null);
				}
				for (Tile current : getTiles()) {
					g.drawImage(current.getImage(), current.getCol()*130 + 320, current.getRow()*130 + 70, null);
				}
			}
		};
		gamePanel.setLayout(null);
		//return button
		JButton Button = new JButton("Return");
		Button.setBounds(0, 600, 100, 50);
		Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				endGame();
			}});
		gamePanel.add(Button);
		
		timeAndScore = new JLabel("Time:"+(int)getTime()+"    Score:"+getScore() + "    Money:"+getMoney());
		timeAndScore.setBounds(0,0,frame.getWidth(),30);
		timeAndScore.setFont(new Font("Serif", Font.PLAIN, 30));
		gamePanel.add(timeAndScore);
		final int timerInterval = 100;
		timer = new Timer(timerInterval, new ActionListener(){
	    	public void actionPerformed(ActionEvent e) {
	    		setTime(getTime() - (double)(timerInterval)/1000);
	    		update();
	    		moveRunoff();
	    		setTickCount((getTickCount()+1)%500);
			}
	    });
		gameFrame.setContentPane(gamePanel);
	}
	
	/**
	 * Updates the game state and checks for player input
	 * This includes the time remaining, character actions and movement, and updating the score and money
	 */
	public void update() {
		if (getTime() % 10 < 0.1) {
			addScore(10);
		}
		for (Mussel current : getMussels()) {
			current.grow();
		}
		if (getTickCount() % 10 == 0) {
			timeAndScore.setText("Time:"+(int)getTime()+"    Score:"+getScore() + "    Money:"+getMoney());
			
			
		}
		if (getTickCount() % 5 == 0) {
			Random rand = new Random();
			if (rand.nextInt(10) > 7) {
				addRunoff();
			}
		}
		gamePanel.repaint();
		gameFrame.setVisible(true);
		if(getTime()<=0){
			endGame();
		}
		
	    
		
	}
	
	/**
	 * The player can click on the button to exit the game OR
	 * Click on a tile and the method will prompt the player for which plant they want to place
	 * If the player clicks on a plant to buy, this will prompt the player to place the plant
	 * If the player clicks on a Mussel, the mussel is harvested for money and a new mussel appears
	 * If the player clicks on the exit button, the game will pause and prompt them to exit
	 * WITHOUT sending the score or setting the game complete boolean to true
	 * @param e - The location and nature of the user's click
	 */
	public void onClick(MouseEvent e) {
		int xLoc = e.getX();
		int yLoc = e.getY();
		System.out.println("xLocation: " + xLoc + "  yLocation: " + yLoc);
		Mussel removal = null;
		if (xLoc < 328) {
			for (Mussel current : getMussels()) {
				if (current.getStage() ==  100) {
					if ((xLoc > current.getXLoc() && xLoc < current.getXLoc() + 264) &&
						(yLoc > current.getYLoc() && yLoc < current.getYLoc() + 160)) {
						addScore(50); addMoney(100);
						removal = current;
					}
				}
			}
			if (removal != null) {
				addMussel();
				getMussels().remove(removal);
			}
		}
		else {
			int row = (yLoc - 100)	/130 	;
			int col = (xLoc - 328)	/130	;
			System.out.println("Row: " + row + "  Col: " + col);
			if (getMoney() >= 100) {
				addMoney(-100);
				addPlant(row, col, "Grass");
			}
		}
		
 	}
	
	/**
	 * Adds a plant to the current game from a player choice in a menu screen
	 * Plants can only be bought if the player has enough money
	 * the args will be passed by the useMenu method
	 * @param row - the row of the plant to be made
	 * @param col - the col of the plant to be made
	 * @param type - the type of plant to be placed
	 */
	public void addPlant(int row, int col, String type) {
		getTiles().set(7*row+col, new Plant(row, col, type));
	}
	
	/**
	 *  Adds some runoff to the game at a random point
	 *  this is called periodically based on difficulty
	 *  update will tell the function where to place the runoff
	 *  @param row - the row of the runoff to be made
	 *  @param col - the column of the runoff to be made
	 */
	public void addRunoff() {
		Random rand = new Random() ;
		int row = rand.nextInt(4);
		getEnemies().add(new Runoff(row, 6)) ;
		getTiles().set(7*row+6, getEnemies().get(getEnemies().size() - 1));
	}
	
	
	/**
	 * Moves each runoff if some time has passed (3s right now)
	 * If the runoff reaches the end it kills a mussel
	 * If the runoff reaches a plant it fights the plant
	 */
	public void moveRunoff() {
		Iterator<Runoff> it = getEnemies().iterator();
		while (it.hasNext()) {
			Runoff current = it.next() ;
			int col = current.getCol() ;
			int row = current.getRow() ;
			if (current.getTicksSinceMoved() > 30) {
				current.setTicksSinceMoved(0);
				if (col > 0)
					if (!(getTiles().get(7*row+col-1) instanceof Plant || getTiles().get(7*row+col-1) instanceof Runoff)) {
						getTiles().set(7*row+col - 1, current);
						getTiles().set(7*row+col, new Tile(row, col));
						current.setCol(col - 1);
					}
					else {
						for (Plant currentP : getPlants()) {
							if ((currentP.getCol()  == col-1) && currentP.getRow() == row) {
								System.out.println("YOOOO");
								battle(currentP, current);
							}
						}
					}
				else {
					if (getMussels().size() > 0) {
						getMussels().remove(getMussels().size()-1);
					}
					it.remove();
					getTiles().set(7*row+col, new Tile(row, col));
				}
			}
			current.setTicksSinceMoved(current.getTicksSinceMoved() + 1);
		}
		
	}

	/**
	 * Computes a fight between a plant and some runoff (Health - Strength)
	 * If the runoff dies it gives the player score and money
	 * If the runoff dies it removes the runoff from the lane
	 * @param plant - the plant object that the runoff ran into
	 * @param runoff- the runoff object that ran into the plant
	 */
	public void battle(Plant plant, Runoff runoff) {
		plant.setHealth(plant.getHealth() - runoff.getStrength());
		if (plant.getHealth() <= 0) {
			getPlants().remove(plant);
			getTiles().set(plant.getRow()*7 +plant.getCol(), new Tile(plant.getRow(), plant.getCol()));
		}
		runoff.setHealth(runoff.getHealth() - plant.getStrength());
		if (runoff.getHealth() <= 0) {
			getEnemies().remove(runoff) ;
			getTiles().set(runoff.getRow()*7 +runoff.getCol(), new Tile(runoff.getRow(), runoff.getCol()));
		}
	}
	
	
	/**
	 * Creates a new mussel and adds it to the game in a random, non colliding position
	 */
	public void addMussel() {
		Random rand = new Random();
		int xLoc	=	rand.nextInt(getGameFrame().getWidth()/5 - 90)	; 
		int yLoc	=	rand.nextInt(getGameFrame().getHeight() - 200) + 50	;
		Rectangle newMussel = new Rectangle(xLoc,yLoc,132,80);
		Rectangle curMussel = new Rectangle(0, 0,132,80);
		for (int i = 0 ; i < getMussels().size();) {
			//System.out.println("are  u stuck??/");
			Mussel current = getMussels().get(i);
			newMussel.setBounds(xLoc,yLoc,132,80);
			curMussel.setBounds(current.getXLoc(), current.getYLoc(),132,80);
			if (newMussel.intersects(curMussel)) {
				xLoc = rand.nextInt(getGameFrame().getWidth()/5 - 90);
				yLoc = rand.nextInt(350) + 50;
				i=0;
			}
			else {i++;}
				
		}
		getMussels().add(new Mussel(xLoc, yLoc));
	}
	
	/**
	 * Adds the given amount to the player's money
	 * Used for testing and for mussel collection
	 * @param amount - the amount of money to be added
	 */
	public void addMoney(int amount) {
		this.money += amount;
		timeAndScore.setText("Time:"+(int)getTime()+"    Score:"+getScore() + "    Money:"+getMoney());
	}
	
	/**
	 * If the game ends, passes the score to the overall score
	 * And sets the overall shell to the running state and calls the update method on the overall shell
	 */
	public void endGame() {
		getBigGame().setOverallScore(getBigGame().getOverallScore() + getScore());
		getBigGame().setGamesRunning(0);
		timer.stop();
		gameFrame.setContentPane(bigGamePanel);
		gameFrame.setVisible(true);
	}
	
	/**
	 * Adds a score to the player's score
	 * @param score - the score to be added
	 */
	public void addScore(int score) {
		this.score += score;
		timeAndScore.setText("Time:"+(int)getTime()+"    Score:"+getScore() + "    Money:"+getMoney());
	}
	
	/**
	 * Getters And Setters 
	 */
	public double getTime() {
		return time;
	}

	public void setTime(double time) {
		this.time = time;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public int getTickCount() {
		return tickCount;
	}

	public void setTickCount(int tickCount) {
		this.tickCount = tickCount;
	}

	public ArrayList<Plant> getPlants() {
		return plants;
	}

	public void setPlants(ArrayList<Plant> plants) {
		this.plants = plants;
	}

	public ArrayList<Runoff> getEnemies() {
		return enemies;
	}

	public void setEnemies(ArrayList<Runoff> enemies) {
		this.enemies = enemies;
	}

	public ArrayList<Mussel> getMussels() {
		return mussels;
	}

	public void setMussels(ArrayList<Mussel> mussels) {
		this.mussels = mussels;
	}

	public ArrayList<Tile> getTiles() {
		return tiles;
	}

	public void setTiles(ArrayList<Tile> tiles) {
		this.tiles = tiles;
	}

	public boolean isGameRunning() {
		return gameRunning;
	}

	public void setGameRunning(boolean gameRunning) {
		this.gameRunning = gameRunning;
	}

	public boolean getGameOver() {
		return gameOver;
	}

	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}

	public OverallGame getBigGame() {
		return bigGame;
	}

	public void setBigGame(OverallGame bigGame) {
		this.bigGame = bigGame;
	}

	public JPanel getGamePanel() {
		return gamePanel;
	}

	public void setGamePanel(JPanel gamePanel) {
		this.gamePanel = gamePanel;
	}

	public JPanel getBigGamePanel() {
		return bigGamePanel;
	}

	public void setBigGamePanel(JPanel bigGamePanel) {
		this.bigGamePanel = bigGamePanel;
	}

	public JFrame getGameFrame() {
		return gameFrame;
	}

	public void setGameFrame(JFrame gameFrame) {
		this.gameFrame = gameFrame;
	}

	public JLabel getTimeAndScore() {
		return timeAndScore;
	}

	public void setTimeAndScore(JLabel timeAndScore) {
		this.timeAndScore = timeAndScore;
	}

	public long getStartTime() {
		return startTime;
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	public Timer getTimer() {
		return timer;
	}

	public void setTimer(Timer timer) {
		this.timer = timer;
	}
	
	public String toString(){
		return "Game3 [ Time: "+time+", Score: "+score+", Money: "+money+"\n"+plants.toString()+"\n"
				+enemies.toString()+"\n"+mussels.toString()+"\n"+", Game Over: "+gameOver+", Big Game: "+bigGame
				+", Timer"+timer+"]";
	}

	/**
	 * Generic Constructor For use with reinitializing with serializeable
	 * @param time
	 * @param score
	 * @param money
	 * @param plants
	 * @param enemies
	 * @param mussels
	 * @param gameRunning
	 * @param gameOver
	 * @param bigGame
	 * @param gamePanel
	 * @param bigGamePanel
	 * @param gameFrame
	 * @param timeAndScore
	 * @param startTime
	 * @param timer
	 */
	public Game3(double time, int score, int money, int tickCount, ArrayList<Plant> plants, ArrayList<Runoff> enemies,
			ArrayList<Mussel> mussels, boolean gameRunning, boolean gameOver, OverallGame bigGame, JPanel gamePanel,
			JPanel bigGamePanel, JFrame gameFrame, JLabel timeAndScore, long startTime, Timer timer) {
		super();
		this.time = time;
		this.score = score;
		this.money = money;
		this.tickCount = tickCount;
		this.plants = plants;
		this.enemies = enemies;
		this.mussels = mussels;
		this.gameRunning = gameRunning;
		this.gameOver = gameOver;
		this.bigGame = bigGame;
		this.gamePanel = gamePanel;
		this.bigGamePanel = bigGamePanel;
		this.gameFrame = gameFrame;
		this.timeAndScore = timeAndScore;
		this.startTime = startTime;
		this.timer = timer;
	}
	
	
}
