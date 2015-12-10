package OverallGame;

import java.awt.Font;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import Game1.RipRapGame;
import Game2.CrabCatcherGame;
import Game3.Game3;

public class gameWindow implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private OverallGame bigGame;

	private JFrame	frame;
	private Image	game1Button;
	private Image	game2Button;
	private Image	game3Button;
	private JLabel	currentScore;
	private JButton viewHighScores;
	public	Timer	timer;
	public	ArrayList<Image>	screens;
	public	JLabel	currImage;
	public 	int		time	=	0;
	public JPanel	tempPanel;
	/**
	 * Launch the application.
	 */
	/*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					gameWindow window = new gameWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
*/
	/**
	 * Create the application.
	 */
	public gameWindow(OverallGame bigGame) {
		this.bigGame = bigGame;
		try {
			this.game1Button = ImageIO.read(new File("images/Button1Map.png")).getScaledInstance(OverallGame.frameWidth/3, OverallGame.frameHeight, 1);
			this.game2Button = ImageIO.read(new File("images/Button2Map.png")).getScaledInstance(OverallGame.frameWidth/3, OverallGame.frameHeight, 1);
			this.game3Button = ImageIO.read(new File("images/Button3Map.png")).getScaledInstance(OverallGame.frameWidth/3, OverallGame.frameHeight, 1);
		} catch(IOException e) {
			System.out.println("Read Error: " + e.getMessage());
		}
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				//System.out.println("game runnin is " + bigGame.getGamesRunning());
				if (bigGame.getGamesRunning() == 3) {
					bigGame.getGame3().onClick(e);
				}
				else if (bigGame.getGamesRunning() == 2) {
					//bigGame.getGame2().onClick(e);
					//System.out.println("using CrabCatcher's regular OnClick");
				}
			}
			public void mousePressed(MouseEvent e){
				if (bigGame.getGamesRunning() == 2) {
					bigGame.getGame2().onClick(e);
					//System.out.println("using CrabCatcher's MousePressed");
				}
			}
		});
		frame.setBounds(100, 100, OverallGame.frameWidth, OverallGame.frameHeight);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		JButton btnStartGame = new JButton();	
		ImageIcon imgGame1 = new ImageIcon(getGame1Button());
		btnStartGame.setIcon(imgGame1);
		btnStartGame.setOpaque(false);
		btnStartGame.setContentAreaFilled(false);
		btnStartGame.setBorderPainted(true);
		btnStartGame.setBounds(0, 50, frame.getContentPane().getWidth()/3, frame.getContentPane().getHeight()-50);
		frame.getContentPane().add(btnStartGame);
		btnStartGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bigGame.setGamesRunning(1);
				gameIntros();
			}
		});
		
		
		JButton btnStartGame_1 = new JButton();
		ImageIcon imgGame2 = new ImageIcon(getGame2Button());
		btnStartGame_1.setIcon(imgGame2);
		btnStartGame_1.setOpaque(false);
		btnStartGame_1.setContentAreaFilled(false);
		btnStartGame_1.setBorderPainted(true);
		btnStartGame_1.setBounds(frame.getContentPane().getWidth()/3, 50, frame.getContentPane().getWidth()/3, frame.getContentPane().getHeight()-50);
		frame.getContentPane().add(btnStartGame_1);
		btnStartGame_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (bigGame.getGamesComplete()[0] == true) {
					bigGame.setGamesRunning(2);
					gameIntros();
				}
				else {
					JOptionPane.showMessageDialog(null, "Complete game 1 in order to play this game");
				}
			}
		});
		
		
		JButton btnStartGame_2 = new JButton();
		ImageIcon imgGame3 = new ImageIcon(getGame3Button());
		btnStartGame_2.setIcon(imgGame3);
		btnStartGame_2.setOpaque(false);
		btnStartGame_2.setContentAreaFilled(false);
		btnStartGame_2.setBorderPainted(true);
		if(2*frame.getContentPane().getWidth()/3 > frame.getContentPane().getWidth()/3 +frame.getContentPane().getWidth()/3){
			btnStartGame_2.setBounds(2*frame.getContentPane().getWidth()/3-1, 50, frame.getContentPane().getWidth()/3, frame.getContentPane().getHeight()-50);
		}
		else{
			btnStartGame_2.setBounds(2*frame.getContentPane().getWidth()/3, 50, frame.getContentPane().getWidth()/3, frame.getContentPane().getHeight()-50);
		}
		btnStartGame_2.setFont(new Font("Serif", Font.PLAIN, 50));
		frame.getContentPane().add(btnStartGame_2);
		btnStartGame_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (bigGame.getGamesComplete()[1] == true) {
					bigGame.setGamesRunning(3);
					gameIntros();
				}
				else {
					JOptionPane.showMessageDialog(null, "Complete the other games to play  the final game");
				}
			}
		});
		JButton activateAll = new JButton("Unlock all Games");
		activateAll.setBounds(2*frame.getContentPane().getWidth()/3, 0, frame.getContentPane().getWidth()/3, 50);
		activateAll.setFont(new Font("Serif", Font.PLAIN, 50));
		frame.getContentPane().add(activateAll);
		activateAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean [] newComplete = new boolean[3];
				newComplete[0] = true;
				newComplete[1] = true;
				newComplete[2] = true;
				bigGame.setGamesComplete(newComplete);
			}
		});
		viewHighScores = new JButton("View High Scores");
		viewHighScores.setBounds(0, 0, frame.getContentPane().getWidth()/3, 50);
		viewHighScores.setFont(new Font("Serif", Font.PLAIN, 50));
		frame.getContentPane().add(viewHighScores);
		viewHighScores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, bigGame.getHighscores());
			}
		});
		
		currentScore = new JLabel("Overall Score: " + bigGame.getOverallScore());
		currentScore.setBounds(new Rectangle(frame.getContentPane().getWidth()/3,0,frame.getContentPane().getWidth()/3,50));
		currentScore.setHorizontalAlignment((int)JPanel.CENTER_ALIGNMENT);
		currentScore.setFont(new Font("Serif", Font.PLAIN, 50));
		currentScore.setVisible(true);
		frame.getContentPane().add(currentScore);
		frame.repaint();
	}
	public void gameIntros() {
		screens	=	new	ArrayList<Image>();
		if (bigGame.getGamesRunning() == 1) {
			try	{
				Image	img	=	ImageIO.read(new File("images/game3background.png")).getScaledInstance(OverallGame.frameWidth, OverallGame.frameHeight, 1);
				Image	img2=	ImageIO.read(new File("images/ocean_background.jpg")).getScaledInstance(getFrame().getWidth(), getFrame().getHeight(), 1);
				Image	img3=	ImageIO.read(new File("images/game3background.png")).getScaledInstance(getFrame().getWidth(), getFrame().getHeight(), 1);
				screens.add(img);
				screens.add(img2);
				screens.add(img3);
			}
			catch(IOException e) {
				System.out.println("Read Error: " + e.getMessage());
			}
		}
		else if(bigGame.getGamesRunning() == 2) {
			try	{
				Image	img	=	ImageIO.read(new File("images/game3background.png")).getScaledInstance(OverallGame.frameWidth, OverallGame.frameHeight, 1);
				Image	img2=	ImageIO.read(new File("images/ocean_background.jpg")).getScaledInstance(getFrame().getWidth(), getFrame().getHeight(), 1);
				Image	img3=	ImageIO.read(new File("images/game3background.png")).getScaledInstance(getFrame().getWidth(), getFrame().getHeight(), 1);
				screens.add(img);
				screens.add(img2);
				screens.add(img3);
			}
			catch(IOException e) {
				System.out.println("Read Error: " + e.getMessage());
			}
		}
		else {
			try	{
				Image	img	=	ImageIO.read(new File("images/g3Opening1.png")).getScaledInstance(OverallGame.frameWidth, OverallGame.frameHeight, 1);
				Image	img2=	ImageIO.read(new File("images/g3Opening2.png")).getScaledInstance(OverallGame.frameWidth, OverallGame.frameHeight, 1);
				Image	img3=	ImageIO.read(new File("images/g3Opening3.png")).getScaledInstance(OverallGame.frameWidth, OverallGame.frameHeight, 1);
				screens.add(img);
				screens.add(img2);
				screens.add(img3);
			}
			catch(IOException e) {
				System.out.println("Read Error: " + e.getMessage());
			}
		}
		if (screens.size()	>	0)	{
			ActionListener animator	=	new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					gameAnim();
				}};
			timer	=	new Timer(100, animator);
			tempPanel	=	(JPanel)getFrame().getContentPane();
			tempPanel.setVisible(false);
			getFrame().setContentPane(new JPanel());
			currImage	=	new JLabel(new ImageIcon(screens.get(0)));
			currImage.setBounds(0, 0, OverallGame.frameWidth, OverallGame.frameHeight);
			getFrame().getContentPane().add(currImage);
			getFrame().getContentPane().setVisible(true);
			timer.start();
		}
	}
	
	public void gameAnim() {
		time++;
		if (time	%	12	==	0) {
			if (time	%	36	==	0){
				timer.stop();
				time	=	0;
				getFrame().setContentPane(tempPanel);
				if (bigGame.getGamesRunning() == 1) {
					bigGame.setGame1(new RipRapGame(20, bigGame, frame));
					bigGame.getGame1().run();
				}
				else if (bigGame.getGamesRunning() == 2) {
					bigGame.setGame2(new CrabCatcherGame(0, null, 0, 0, 0, null, 0, false, bigGame, frame));
					bigGame.getGame2().startGame();
				}
				else {
					bigGame.setGame3(new Game3(bigGame));
				}
			}
			else	if	(screens.size()	>=	time/12){
				currImage.setVisible(false);
				currImage	=	new	JLabel(new	ImageIcon(screens.get(time/12)));
				currImage.setVisible(true);
				getFrame().add(currImage);
			}
		}
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
		gameWindow obj = null ;
		try {	
			FileInputStream fis = new FileInputStream(fileName);
			ObjectInputStream ois = new ObjectInputStream(fis);
			obj = (gameWindow)ois.readObject();
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

	public Image getGame1Button() {
		return game1Button;
	}

	public void setGame1Button(Image game1Button) {
		this.game1Button = game1Button;
	}

	public Image getGame2Button() {
		return game2Button;
	}

	public void setGame2Button(Image game2Button) {
		this.game2Button = game2Button;
	}

	public Image getGame3Button() {
		return game3Button;
	}

	public void setGame3Button(Image game3Button) {
		this.game3Button = game3Button;
	}

	public JLabel getCurrentScore() {
		return currentScore;
	}

	public void setCurrentScore(JLabel currentScore) {
		this.currentScore = currentScore;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public JButton getViewHighScores() {
		return viewHighScores;
	}

	public void setViewHighScores(JButton viewHighScores) {
		this.viewHighScores = viewHighScores;
	}
	
	
}
