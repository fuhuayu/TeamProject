package Game3;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.Timer;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JProgressBar;

import OverallGame.OverallGame;

/**
 * @author Brendan, Danielle, David, Huayu and Zhanglong
 * @version 0.1
 * @since   2015-11-02
 * The Game Handler for the final game
 * This will handle all logic, Input and Graphics for the final game
 */
public class Game3 implements java.io.Serializable{
	private static final long serialVersionUID = 300L;
	public  static final int scalor = (OverallGame.frameHeight < OverallGame.frameWidth) ? OverallGame.frameHeight/8 : OverallGame.frameWidth/8;
	public 	static final int xOffset= (OverallGame.frameWidth/3);
	public 	static final int yOffset= (OverallGame.frameHeight/6);
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
	private static Image background;
	private JPopupMenu menu;
	private JProgressBar timeBar;
	private ArrayList<JLabel> coins;
	private JLabel	totalCoin = null;
	JMenuItem grass = new JMenuItem("Grass");
	JMenuItem mangrove = new JMenuItem("Mangrove");
	
	
	
	/**
	 * Game Constructor
	 * Produces the initial game state 
	 * (time = 300.0, score = 0, money = 100, no plants or runoff, 5 Mussels in pseudorandom positions)
	 * @param bigGame - The handler for the entire game
	 */
	public Game3(OverallGame bigGame) {
		this.time	=	50.0	;
		this.score	=	0	;
		this.money	=	900	;
		this.plants	=		new ArrayList<Plant>();
		this.enemies	=	new ArrayList<Runoff>();
		this.mussels	=	new ArrayList<Mussel>();
		this.tiles	= 		new ArrayList<Tile>() ; 
		for (int i = 0 ; i < 28 ; i++) {
			this.tiles.add(new Tile(i/7, i%7));
		}
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
		this.menu = new JPopupMenu();
		menu.add(grass);
		menu.add(mangrove);
		this.background = null;
		this.coins = new ArrayList<JLabel>();
		addMoney(0);
		try {
			this.background = ImageIO.read(new File("images/game3Background.png")).getScaledInstance(gameFrame.getWidth(), gameFrame.getHeight(), 1);
		} catch(IOException e) {
			System.out.println("Read Error: " + e.getMessage());
		}
		
		
		
	}

	/**
	 * Creates the game panel and sets up the timer
	 * @param frame
	 */
	public void initPanel(JFrame frame) {
		this.bigGamePanel	= (JPanel)frame.getContentPane();
		this.gamePanel		= new JPanel() {
			private static final long serialVersionUID = 310L;
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(Game3.getBackground(), 0, 0, null);
				for (Mussel current : getMussels()) {
					g.drawImage(current.getMusselDrawing(), current.getXLoc(), current.getYLoc(), null);
				}
				for (Tile current : getTiles()) {
					if (current instanceof Plant) {
						g.drawImage(current.getImage(), current.getCol()*(scalor) + xOffset, current.getRow()*(scalor) + yOffset, null);
					}
					else  {
						g.drawImage(current.getImage(), current.getCol()*(scalor) + xOffset, current.getRow()*(scalor) + yOffset, null);
					}
				}
				for (Runoff current : getEnemies()) {
					for(int i = 0 ; i < current.getImages().size(); i++) {
						g.drawImage(current.getImages().get(i), current.getFront() + scalor*i, current.getRow()*(scalor) + yOffset, null);
					}
				}
				
			}
		};
		gamePanel.setLayout(null);
		//return button
		JButton Button = new JButton("Return");
		Button.setBounds(OverallGame.frameWidth-scalor, 0, scalor, scalor);
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
		
		//time bar
				timeBar = new JProgressBar(0, (int)(time*100));
				timeBar.setValue((int)(time*100));
				timeBar.setString("TIME");
				timeBar.setStringPainted(true);
				timeBar.setBounds(0, 30, bigGame.frameWidth/4, bigGame.frameHeight/20);
				gamePanel.add(timeBar);
		
		timer = new Timer(timerInterval, new ActionListener(){
	    	public void actionPerformed(ActionEvent e) {
	    		setTime(getTime() - (double)(timerInterval)/1000);
	    		update();
	    		moveRunoff();
	    		setTickCount((getTickCount()+1)%500);
			}
	    });
		
		
		gameFrame.setContentPane(gamePanel);
		addRunoff();
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
			timeAndScore.setText("Time:"+(int)getTime()+"    Score:"+getScore() + "   Money:");
			
			
		}
		if (timeBar != null){timeBar.setValue((int) ((this.time*100) - this.tickCount));}
		if (getTickCount() % 6 == 0) {
			Random rand = new Random();
			if (rand.nextInt(10) > 8) {
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
		if (timer.isRunning() == false) {
			timer.start();
		}
		int xLoc = e.getX();
		int yLoc = e.getY();
		System.out.println("xLocation: " + xLoc + "  yLocation: " + yLoc);
		Mussel removal = null;
		if (xLoc < xOffset) {
			for (Mussel current : getMussels()) {
				if (current.getStage() ==  100) {
					if ((xLoc > current.getXLoc() && xLoc < current.getXLoc() + (18/5)*scalor) &&
						(yLoc > current.getYLoc() && yLoc < current.getYLoc() + 2*scalor)) {
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
		else if (getMoney() >= 100){
			final int row = (yLoc -	yOffset)	/scalor ;
			final int col = (xLoc - xOffset)	/scalor	;
			System.out.println("Row: " + row + "  Col: " + col);
			timer.stop();
			menu.show(e.getComponent(), xLoc, yLoc);
			grass.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					if(getTiles().get(7*row+col)instanceof Plant){
						grass.removeActionListener(this);
					}
					else {
						if(getMoney()>=100){
							addMoney(-100);
							addPlant(row,col,"Grass");
							timer.start();}
						else{ timer.start();}
					}
				}
			});
			mangrove.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					if (getTiles().get(7*row+col)instanceof Plant) {
						mangrove.removeActionListener(this);
					}
					else {
						if(getMoney()>=100){
							addMoney(-100);
							addPlant(row,col,"Mangrove");
							timer.start();}
						else{ timer.start();}
					}
				}
			});
			
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
		Plant plant = new Plant(row, col, type);
		getPlants().add(plant);
		getTiles().set(7*row+col, plant);
	}
	
	/**
	 *  Adds some runoff to the game at a random point
	 *  this is called periodically based on difficulty
	 *  update will tell the function where to place the runoff
	 *  @param row - the row of the runoff to be made
	 *  @param col - the column of the runoff to be made
	 */
	public void addRunoff() {
		if	(getEnemies().size()	<	4)	{
			System.out.println(getEnemies().size());
			Random rand = new Random() ;
			int row = rand.nextInt(4);
			for (int i = 0 ; i < getEnemies().size() ; i++) {
				if (row == getEnemies().get(i).getRow()) {
					row = rand.nextInt(4);
					i = 0;
				}
			}
			enemies.add(new Runoff(row, 6*scalor + xOffset)) ;
		}
		
	}
	
	
	/**
	 * Moves each runoff if some time has passed (3s right now)
	 * If the runoff reaches the end it kills a mussel
	 * If the runoff reaches a plant it fights the plant
	 */
	public void moveRunoff() {
		Iterator<Runoff> it = getEnemies().iterator();
		Runoff removal = null;
		while (it.hasNext()) {
			Runoff current = it.next() ;
			int col = (current.getFront() - xOffset)/scalor ;
			int row = current.getRow() ;
			if (current.getFront() > xOffset)
				if (getTiles().get(7*row+col) instanceof Plant) {
					Plant plant = (Plant)getTiles().get(7*row+col);
					battle((Plant)getTiles().get(7*row+col), current);
					if (current.getHealth().size() == 0) {
						removal = current;
					}
					if (plant.getHealth() <= 0) {
						getPlants().remove(plant);
						getTiles().set(plant.getRow()*7 +plant.getCol(), new Tile(plant.getRow(), plant.getCol()));
					}
				}
				else {
					if (col != (current.getFront()-1 - xOffset)/scalor) {
						current.grow();
					}
					current.setFront(current.getFront()-1);
				}
			else {
				if (getMussels().size() > 0) {
					getMussels().remove(getMussels().size()-1);
				}
				removal = current;
			}
			current.setTicksSinceMoved(current.getTicksSinceMoved() + 1);
		}
		if (removal != null) {
			getEnemies().remove(removal);
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
		runoff.getHealth().set(0, runoff.getHealth().get(0) - plant.getStrength());
		if(runoff.getHealth().get(0) < 0) {
			runoff.removeFront();
		}
	}
	
	
	/**
	 * Creates a new mussel and adds it to the game in a random, non colliding position
	 */
	public void addMussel() {
		Random rand = new Random();
		int tooManyTries = 0;
		int xLoc	=	rand.nextInt(xOffset	-	(18/5)*scalor);
		int yLoc	=	rand.nextInt(OverallGame.frameHeight-yOffset*3/4-2*scalor) + yOffset*3/4;
		Rectangle newMussel = new Rectangle(xLoc,yLoc,(9/5)*scalor,scalor);
		Rectangle curMussel = new Rectangle(0, 0,(9/5)*scalor,scalor);
		for (int i = 0 ; i < getMussels().size();) {
			Mussel current = getMussels().get(i);
			newMussel.setBounds(xLoc,yLoc,(9/5)*scalor,scalor);
			curMussel.setBounds(current.getXLoc(), current.getYLoc(),(9/5)*scalor,scalor);
			if (newMussel.intersects(curMussel)) {
				xLoc = rand.nextInt(xOffset	-	(18/5)*scalor);
				yLoc = rand.nextInt(OverallGame.frameHeight-yOffset*3/4-2*scalor) + yOffset*3/4;
				i=0;
				tooManyTries++;
			}
			else {i++;}
			if (tooManyTries > 50) {
				i = getMussels().size();
			}
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
		if (getMoney() <= 1000) {
			if(totalCoin != null) {
				totalCoin.setVisible(false);
				totalCoin = null;
			}
			while (getMoney() / 100 > coins.size()) {
				ImageIcon imi = new ImageIcon("images/coin.png");
				JLabel jl = new JLabel(imi);
				coins.add(jl);
				jl.setBounds(xOffset+(coins.indexOf(jl))*scalor/3, 0, scalor/3, scalor/3);
				gamePanel.add(jl);
			}
			while (getMoney() / 100 < coins.size() && coins.size() != 0) {
				gamePanel.remove(coins.get(coins.size()-1));
				coins.remove(coins.size() - 1);
			}
		}
		else if (coins.size() == 10){
			System.out.println("poo");
			if (coins.size() != 0) {
				for (JLabel current : coins) {
					gamePanel.remove(current);
					current.setVisible(false);
				}
				coins.removeAll(coins);
			}
			ImageIcon imi = new ImageIcon("images/coin.png");
			JLabel jl = new JLabel(imi);
			coins.add(jl);
			jl.setBounds(xOffset+(coins.indexOf(jl))*scalor/3, 0, scalor/3, scalor/3);
			gamePanel.add(jl);
			totalCoin = new JLabel(" X" + getMoney()/100);
			totalCoin.setBounds(((xOffset+scalor/3)+scalor/3),0,scalor/3,scalor/3);
			totalCoin.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,14));
			gamePanel.add(totalCoin);
			
		}
		else {
			if (totalCoin == null) {
				System.out.println("r");
				ImageIcon imi = new ImageIcon("images/coin.png");
				JLabel jl = new JLabel(imi);
				coins.add(jl);
				jl.setBounds(xOffset+(coins.indexOf(jl))*scalor/3, 0, scalor/3, scalor/3);
				gamePanel.add(jl);
				
			}
			else {
				totalCoin.setText(" X" + getMoney()/100);
			}
		}
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
		timeAndScore.setText("Time:"+(int)getTime()+"    Score:"+getScore() + "    Money:");
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
	
	public static Image getBackground() {
		return background;
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
	
	/**
	 * Method to serialize OverallGame, which contains the other games as params
	 * So this output will contain the serialized version of every object
	 * @param obj
	 * @param fileName
	 * @throws IOException
	 */
	public static void serialize(Object obj, String fileName) {
		try {
	        FileOutputStream fos = new FileOutputStream(fileName);
	        ObjectOutputStream oos = new ObjectOutputStream(fos);
	        oos.writeObject(obj);
	        fos.close();
		}
		catch (IOException e) {
			System.out.println("Read Error: " + e.getMessage());
		}
	}
	
	/**
	 * Method to read a game state from file and instantiate it. The reverse of the serialize function
	 * @param fileName
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static Object deserialize(String fileName) {
		Game3 obj = null ;
		try {	
			FileInputStream fis = new FileInputStream(fileName);
			ObjectInputStream ois = new ObjectInputStream(fis);
			obj = (Game3)ois.readObject();
			ois.close();
		}
		catch(IOException e) {
			System.out.println("Read Error: " + e.getMessage());
		}
		catch (ClassNotFoundException e){
			System.out.println("Read Error: " + e.getMessage());
		}
		return obj;
	}
	
}
