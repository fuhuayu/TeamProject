package Game2;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.Border;

import Game3.Mussel;
import OverallGame.OverallGame;


/**
 * @author Dwegrzyn
 *
 */
public class CrabCatcherGame implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 200L;
	/**
	 * 
	 */
	//FIELDS
	private double time; 
	private double speed;
	private ArrayList<Animal> animals; 
	private ArrayList<ResultAnimation> resultAnims;
	private int score;
	private int lives = 3;
	private double gameLengthInMilliseconds = 30 * 1000; //how long is this game?
	private MouseAdapter mouseListener;
	private int maxAnimalsOnScreen = 16; 
	private boolean gameOver = false;
	private OverallGame bigGame;
	private JFrame frame;
	private JPanel panel;
	private Timer timer;
	private JPanel bigpan;
	private JLabel TS;
	private Image[] crabImages;
	private Image[] mittencrabImages;
	private Image[] fishImagesRight;
	private Image[] fishImagesLeft;
	private Image backgroundImage;
	private Image netImage;
	private static int mittencrabScoreEffect = 100;
	private static int crabScoreEffect = -75;
	private static int fishScoreEffect = -50;
	private JProgressBar timeBar;
	private int resultSize = 100;// CHANGE THIS TO SET IN GAME
	private static int frameRate = 500; //frames per interval in milliseconds

	/**The all-parameter constructor for crab catcher game. Not used publicly.
	 * @param time
	 * @param speed
	 * @param animals
	 * @param score
	 * @param lives
	 * @param gameLength
	 * @param mouseListener
	 * @param maxAnimalsOnScreen
	 * @param gameOver
	 * @param bigGame
	 * @param frame
	 * @param panel
	 * @param timer
	 * @param bigpan
	 * @param tS
	 * @param crabImage
	 * @param mittencrabImage
	 * @param fishImage
	 */
	private CrabCatcherGame(double time, double speed, ArrayList<Animal> animals,
			int score, int lives, double gameLength,
			MouseAdapter mouseListener, int maxAnimalsOnScreen,
			boolean gameOver, OverallGame bigGame, JFrame frame, JPanel panel,
			Timer timer, JPanel bigpan, JLabel tS, Image[] crabImage,
			Image[] mittencrabImage, Image[] fishImage) {
		super();
		this.time = time;
		this.speed = speed;
		this.animals = animals;
		this.score = score;
		this.lives = lives;
		this.gameLengthInMilliseconds = gameLength;
		this.mouseListener = mouseListener;
		this.maxAnimalsOnScreen = maxAnimalsOnScreen;
		this.gameOver = gameOver;
		this.bigGame = bigGame;
		this.frame = frame;
		this.panel = panel;
		this.timer = timer;
		this.bigpan = bigpan;
		TS = tS;
		this.crabImages = crabImage;
		this.mittencrabImages = mittencrabImage;
		this.fishImagesRight = fishImage;
	}



	//CONSTRUCTOR	
	/**
	 * @param time - how long the game has been running
	 * @param speed - rate of gameplay
	 * @param animals - array of all animals in the mini game
	 * @param score - player's score for this mini game
	 * @param lives - number of lives remaining (default is 3)
	 * @param gameLength - time limit for the game
	 * @param mouseListener - handles mouse clicks, checks if user clicked animal
	 * @param maxAnimalsOnScreen - the maximum number of animals that can appear onscreen
	 * @param bigGame - the overall game that this mini game is a part of
	 * @param frame - the frame the game is drawn in
	 */
	//----------------------------------------------------------------UPDATE TO CALL SUPER!
	public CrabCatcherGame(double speed, ArrayList<Animal> animals,
			int score, int lives, double gameLength,
			MouseAdapter mouseListener, int maxAnimalsOnScreen,
			boolean gameOver, OverallGame bigGame, JFrame frame) {
		super();
		this.time = 0;
		this.animals = animals;
		this.resultAnims = new ArrayList<ResultAnimation>();
		this.score = score;
		this.mouseListener = mouseListener;
		this.gameOver = gameOver;
		this.bigGame = bigGame;
		this.frame = frame;
		loadAnimalImages();
	}

	
	
	//METHODS
	/**returns the image in the path images/filename
	 * sets imageWidth and imageHeight
	 * @param filename the name of the file to be loaded
	 * @return 
	 */
	public Image loadImage(String filename) {
		Image image = null;
		try {
			image = ImageIO.read(new File("images/" + filename));
		} catch(IOException e) {
			System.out.println("Read Error: " + e.getMessage());
		}
		if (filename.equals("ocean_background.jpg")){
			image = image.getScaledInstance(bigGame.frameWidth, bigGame.frameHeight, 0);
		}
		else if (filename.equals("net.png")){
			int height = (int)(bigGame.frameHeight/3.6 + 10);
			int width = (int) (height*1.25);
			image = image.getScaledInstance(width, height, 0);
		}
		else{
			//need to customize animal sizes
			int height = (int)(bigGame.frameHeight/3.6);
			int width = (int) (height*1.25);
			image = image.getScaledInstance(width, height, 0);
			}
		return image;
	}
	
	public void loadAnimalImages(){
		mittencrabImages = new Image[2];
		mittencrabImages[0] = loadImage("mittencrab1.png");	
		mittencrabImages[1] = loadImage("mittencrab2.png");	
		
		crabImages = new Image[2];
		crabImages[0] = loadImage("maincrab1.png");	
		crabImages[1] = loadImage("maincrab2.png");	
		
		fishImagesRight = new Image[2];
		fishImagesRight[0] = loadImage("fish.png");
		fishImagesRight[1] = loadImage("fish.png");
		
		
		fishImagesLeft = new Image[2];
		fishImagesLeft[0] = loadImage("fish_left.png");
		fishImagesLeft[1] = loadImage("fish_left.png");
		
		backgroundImage = loadImage("ocean_background.jpg");
		netImage = loadImage("net.png");
		
	}
	
	/**
	 * Updates the game's timed elements
	 */
	public void updateGame(){
		//check if lives == 0, or time = gameLength, which cause gameOver
		if (time >= gameLengthInMilliseconds){
    		//System.out.println("time is " + time + ">= " + gameLength);
			endGame();
		}
			
		for (int i = animals.size()-1; i >= 0; i--) {
		Animal current = animals.get(i);	
			//tick all animals
			current.onTick(this);
			if (current.isOffScreen()){
				reAddAnimal(current);
				//System.out.println("An offscreen animal was re-added");
			}
		}
		
		//update anims
		Iterator<ResultAnimation> it = resultAnims.iterator();
		while(it.hasNext()){
			it.next().update((int)time);
		}
	}
	
	/**
	 * Sets the game's variables to defaults and begins the timer
	 */
	public void startGame(){
		//on start button pressed
		//plays intro with instructions?
		//initialize game - sets variables to defaults, generates animals 
		generateAnimals();
		//for (Animal each : animals){System.out.println("img width: " + each.getImageWidth());}
		System.out.println("--game length: " + this.gameLengthInMilliseconds);
		//initialize panel
		initPanel();	
		System.out.println("panel initialized");
		//sets content to this game's panel
		bigpan=(JPanel) frame.getContentPane();
		frame.setContentPane(this.panel);
		frame.setVisible(true);
		System.out.println("--panel set to visible");
		timer.start();	
		System.out.println("timer started");
	}
	
	/**initializes the game's JPanel and layout
	 * establishes the paintComponent method to be later called in repaint()
	 * @return
	 */
	public boolean initPanel(){
		
		//layout and draw things
		panel = new JPanel(){
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				//draw background
				g.drawImage(backgroundImage, 0, 0, null);
				//draw animals from newest to oldest (new animals will show behind ones)
				for (int i = animals.size()-1; i >= 0; i--) {
					Animal animal = animals.get(i);
						g.drawImage(animal.getImage(), animal.getXloc(), animal.getYloc(), null);
						//if caught, draw net on top
						if (animal.isCaught()){
							g.drawImage(netImage, animal.getXloc(), animal.getYloc(), null);
							reAddAnimal(animal);		
					}										
				}
				
				//Remove completed animations
				Iterator<ResultAnimation> it = resultAnims.iterator();
				while(it.hasNext()){
					ResultAnimation current = it.next();
					if (current.isComplete()){it.remove();}
					else{
						g.drawImage(current.getCurrentImage(), current.getXloc(), current.getYloc(), null);
					}
				}
				
				//draw timer
				double x = 100 * getTime()/getGameLength();
				float green   = (float) (x > 50 ? 1-2 * (x-50)/100.0 : 1.0);
				float red = (float) (x > 50 ? 1.0 : 2 * x/100.0);
				Color timerColor = new Color(red, green, 0);
				g.setColor(timerColor);
				g.fillArc(0, 0, OverallGame.frameWidth/8, OverallGame.frameWidth/8, 90, 360-(int)(360.0*getTime()/getGameLength()));
				
			}
		};
		
		panel.setLayout(null);
		//return button
		JButton Button = new JButton("Return");
		Button.setBounds(0, 600, 100, 50);
		Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				endGame();
			}});
		panel.add(Button);
		
		
		//Score
		TS = new JLabel("Score: "+this.score, SwingConstants.CENTER);
		TS.setOpaque(true);
		TS.setBackground(new Color(223, 196, 99));
		TS.setBounds((int)(OverallGame.frameWidth/7), 0, (int)(bigGame.frameWidth/7), (int)(bigGame.frameWidth/16));
		TS.setFont(new Font("Serif", Font.PLAIN, 30));
		
		Border raisedbevel = BorderFactory.createRaisedBevelBorder();
		Border loweredbevel = BorderFactory.createLoweredBevelBorder();
		TS.setBorder(BorderFactory.createCompoundBorder(raisedbevel, loweredbevel));
		panel.add(TS);
		
	
		//declare timer
		//int timerTimeInMilliSeconds = 1000;
		final int timerTimeInMilliSeconds = 33;
	    timer = new javax.swing.Timer(timerTimeInMilliSeconds, new ActionListener(){
	    	public void actionPerformed(ActionEvent e) {
	    		time += timerTimeInMilliSeconds;
	    		updateGame();
	    		updatePanel();
	    		//System.out.println("time: " + time);
			}
	    });
		
		return true;
	}
	
	/**called on each tick, this updates panel visuals (text and graphics)
	 * @return
	 */
	public boolean updatePanel(){
		//visual updates
		TS.setText("Score: "+this.score);	
		frame.repaint();
		
		return true;
		
	}
	
	/**
	 * Ends mini game and saves score to big game. Cues mini game closing scene.
	 */
	public void endGame(){
		//play closing scene
		//.....
		//send score to send to big game
		bigGame.setOverallScore(bigGame.getOverallScore() + score);
		bigGame.getGameWindow().getCurrentScore().setText("Overall Score: " + bigGame.getOverallScore());
		//stop timer
		timer.stop();
		//set big game running to true
		bigGame.setGamesRunning(0);
		//return to big game
		frame.setContentPane(bigpan);
		frame.getContentPane().setVisible(true);
	}
	
	/**
	 * Constructs the max animals to place on screen 
	 * sets properties randomly for each animal
	 */
	public void generateAnimals(){
		//constructs the max number of animals to place on screen
		animals = new ArrayList<Animal>();
		for (int i=0; i < maxAnimalsOnScreen; i++){			
			animals.add(makeRandomAnimal());
		}
	}
	
	
	/**
	 * @return a random animal
	 */
	public Animal makeRandomAnimal(){
		Random r = new Random();
		int duration = 7 - r.nextInt(5);
		//boolean visible = r.nextBoolean();//*****changed this//
		boolean visible = true;
		
		int typenum = r.nextInt(100);
		String type = "crab";
		int effect = crabScoreEffect;
		Image[] img = crabImages;	
		
		if (typenum >= 0 && typenum <= 55){
			type = "mittencrab"; 
			effect = mittencrabScoreEffect; 
			img = mittencrabImages;
			}
		
		else if (typenum < 75){
			type = "fish"; 
			effect = fishScoreEffect;
			img = fishImagesRight;
			}
		
		//keeps animals in frame
		//int xloc = r.nextInt(bigGame.frameWidth - img.getWidth(null));
		//int yloc = r.nextInt(bigGame.frameHeight - img.getHeight(null));
		
		Animal animal = new Animal(0, 0, type, effect, duration, visible);
		animal.setImages(img); //note: also sets image height and width
		animal.setRandomXDir();
		//make sure fish goes in correct direction
		if(animal.getXdir() == -1 && animal.getTypeOfAnimal().equals("fish")){
			animal.setImages(fishImagesLeft);
		}
		//animal.setStep(50 - r.nextInt(30));
		setOffScreenLoc(animal);
		return animal;
	}
	
	
	/**
	 * @param animal the animal to be added to the game's list
	 */
	public void addAnimal(Animal animal){
		//for testing purposes
		//adds animal to game's animal list
		animals.add(animal);
	}
	
	/**Returns true if that animal does NOT overlap with an animal in the existing animal list
	 * @param animal
	 * @return
	 */
	public boolean uniqueLocation(Animal animal){
		boolean locTaken = false;
		for (Animal a: animals){
			locTaken = locTaken || a.overlapsWith(animal);
			//if (a.overlapsWith(animal)){System.out.println("OVERLAP: " + animal.toString() + " has its location taken by: " + a.toString());}
		}
		return !locTaken; //is the location unique (not taken)?
		
	}
	
	/**makes sure animal is in a unique location, resetting location if needed
	 * @param newAnimal
	 */
	public Animal setUniqueLocAnimal(Animal newAnimal){
		//set up random location generator
		Random r = new Random();
		
		boolean uniqueLoc = false;
		//until we have added the animal..
		while (!uniqueLoc){
			//true if it if it has a unique location
			if (uniqueLocation(newAnimal)){
				//animals.add(newAnimal);
				uniqueLoc = true;
			}
			//otherwise reset its location and try again
			else{
				newAnimal.setXloc(r.nextInt(bigGame.frameWidth));
				newAnimal.setYloc(r.nextInt(bigGame.frameHeight));
			}
		}
		return newAnimal;
	}
	
	
	////////////////////////////////////////////////////////////////////
	/**Sets the animal beyond the left or right border according to its xdir
	 * @param newAnimal
	 * @return
	 */
	public Animal setOffScreenLoc(Animal newAnimal){
		Random r = new Random();
		//default = moving right, put on left border
		int xloc = 0 - newAnimal.imageWidth;
		int yloc = r.nextInt(bigGame.frameHeight - newAnimal.imageHeight);
		
		//if moving left, put on right border
		if (newAnimal.getXdir() == -1){
			xloc = bigGame.frameWidth;
		}
		
		newAnimal.setXloc(xloc);
		newAnimal.setYloc(yloc);
		return newAnimal;
	}
	
	/**Checks if the user clicked an animal and update game accordingly
	 * @param event the mouse event being processed
	 */
	public void onClick(MouseEvent event){
		//if getAnimalClicked() returns an Animal,
		//System.out.println("YOU CLICKED.");
		Animal animal = getAnimalClicked(event.getX(), event.getY());
		System.out.println("GETANIMALCLICKED RETURNED: " + animal);
		//if (animal != null && animal.isVisible()){ //*****changed this//
		if (animal!=null && !animal.isCaught()){
			//hide animal and add animal's scoreEffect to game score (not going below 0)
			System.out.println("!!!you clicked on a " + animal.getTypeOfAnimal());
			animal.setCaught(true);
			updateScore(animal.getScoreEffect());
			boolean positive = animal.getScoreEffect() > 0;
			this.resultAnims.add(new ResultAnimation(resultSize, positive, event.getX(), event.getY()));
			updatePanel(); //repaint the frame and display score change
			System.out.println("--> score changed by " + animal.getScoreEffect());	
			}
		
		else{
			System.out.println(".......you missed!");
			System.out.println(animals);
			System.out.println("click was at ("+ event.getX() + ", " + event.getY()+ ")");
		}
		
	}
	
	/**removes animal from list and re-adds it in a new location
	 * @param animal
	 */
	public void reAddAnimal(Animal animal){
		animal.setVisible(true); //*****changed this//
		animals.remove(animal);
		animal.regenerateAnimal(bigGame.frameWidth, bigGame.frameHeight); //regenerate as a new random animal
		animal.setCaught(false);
		animal.setOffScreen(false);
		animal.setRandomXDir();
		if(animal.getTypeOfAnimal().equals("fish")){
			if(animal.getXdir() == 1){
				animal.setImages(fishImagesRight);
			}
			else{animal.setImages(fishImagesLeft);}
		}
		animal = setOffScreenLoc(animal);
		animals.add(animal);
	}
	
	
	/**Method for testing click events
	 * @param x
	 * @param y
	 */
	public void onClickTest(int x, int y){	
		//if getAnimalClicked() returns an Animal,
		//System.out.println("YOU CLICKED.");
		Animal animal = getAnimalClicked(x, y);
		if (animal != null && animal.isVisible()){
			//hide animal and add animal's scoreEffect to game score (not going below 0)
			System.out.println("!!!you clicked on a " + animal.getTypeOfAnimal());
			animal.setVisible(false);
			updateScore(animal.getScoreEffect());
			//updatePanel(); //repaint the frame and display score change
			System.out.println("--> score changed by " + animal.getScoreEffect());
			animals.remove(animal);
			animal.regenerateAnimal(800, 800); //regenerate as a new random animal
			animals.add(setUniqueLocAnimal(animal));
			
			}
		else{
			System.out.println(".......you missed!");
			System.out.println(animals);
			System.out.println("click was at ("+ x + ", " + y + ")");
		}
	
	}
	
	/** checks whether there is an animal at the given x y coordinates
	 * @param x the x coordinate of the mouse click
	 * @param y the y coordinate of the mouse click
	 * @return
	 */
	public Animal getAnimalClicked(int x, int y){
		//return the animal if user clicked animal, else return null;
		//add some kind of tolerance
		Animal clicked = null;
		//System.out.println("animals: " + animals);
		//oldest Animals are drawn on top and in the front of the list
		for (int i=0; i < animals.size(); i++){
			//System.out.println("height: " + animal.getImageHeight() + ", width: " + animal.getImageWidth());
			Animal animal = animals.get(i);
			if(x <= animal.getXloc() + animal.getImageWidth() && x >= animal.getXloc()
					&& y <= animal.getYloc() + animal.getImageHeight()
					&& y >= animal.getYloc()){
				clicked = animal;
				//System.out.println("!!!you clicked on a " + animal.getTypeOfAnimal());
				break;
			}
		}
		return clicked;
	}

	
	/**
	 * updates score with given positive/negative effect, making sure score does not go below zero
	 */
	public void updateScore(int effect){
		if (score + effect >= 0){
			score += effect;
		}
		else{
			setScore(0);
		}
	}
	
	
	@Override
	public String toString() {
		return "CrabCatcherGame [time=" + time + ", speed=" + speed
				+ ", animals=" + animals.toString() + ", score=" + score
				+ ", lives=" + lives + ", gameLength=" + gameLengthInMilliseconds
				+ ", maxAnimalsOnScreen=" + maxAnimalsOnScreen + ", gameOver="
				+ gameOver + ", bigGame=" + bigGame + ", timer=" + timer + "]";
	}
	
	
	
	//SERIALIZATION
	/**
	 * Method to serialize Crab Catcher Game
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
		OverallGame obj = null ;
		try {	
			FileInputStream fis = new FileInputStream(fileName);
			ObjectInputStream ois = new ObjectInputStream(fis);
			obj = (OverallGame)ois.readObject();
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
	
	
	//GETTERS & SETTERS
	public double getTime() {
		return time;
	}


	public void setTime(double time) {
		this.time = time;
	}


	public double getSpeed() {
		return speed;
	}


	public void setSpeed(double speed) {
		this.speed = speed;
	}


	public ArrayList<Animal> getAnimals() {
		return animals;
	}


	public void setAnimals(ArrayList<Animal> animals) {
		this.animals = animals;
	}


	public int getScore() {
		return score;
	}


	public void setScore(int score) {
		this.score = score;
	}

	public int getLives() {
		return lives;
	}


	public void setLives(int lives) {
		this.lives = lives;
	}


	public double getGameLength() {
		return gameLengthInMilliseconds;
	}


	public void setGameLength(double gameLength) {
		this.gameLengthInMilliseconds = gameLength;
	}


	public MouseListener getMouseListener() {
		return mouseListener;
	}


	public void setMouseListener(MouseAdapter mouseListener) {
		this.mouseListener = mouseListener;
	}


	public int getMaxAnimalsOnScreen() {
		return maxAnimalsOnScreen;
	}


	public void setMaxAnimalsOnScreen(int maxAnimalsOnScreen) {
		this.maxAnimalsOnScreen = maxAnimalsOnScreen;
	}


	public boolean isGameOver() {
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



	public JFrame getFrame() {
		return frame;
	}



	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	public JPanel getPanel() {
		return panel;
	}

	public void setPanel(JPanel panel) {
		this.panel = panel;
	}

	public Timer getTimer() {
		return timer;
	}

	public void setTimer(Timer timer) {
		this.timer = timer;
	}
	

}
