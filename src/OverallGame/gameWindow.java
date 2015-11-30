package OverallGame;

import java.awt.Font;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import Game1.RipRapGame;
import Game2.CrabCatcherGame;
import Game3.Game3;

public class gameWindow implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private OverallGame bigGame;

	private JFrame frame;

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
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("game runnin is " + bigGame.getGamesRunning());
				if (bigGame.getGamesRunning() == 3) {
					bigGame.getGame3().onClick(e);
				}
				else if (bigGame.getGamesRunning() == 2) {
					bigGame.getGame2().onClick(e);
				}
			}
		});
		frame.setBounds(100, 100, this.bigGame.frameWidth, this.bigGame.frameHeight);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		JButton btnStartGame = new JButton();	
		ImageIcon imgGame1 = new ImageIcon("images/Game1Button.png");
		btnStartGame.setIcon(imgGame1);
		btnStartGame.setBounds(0, 50, frame.getContentPane().getWidth()/3, frame.getContentPane().getHeight()-50);
		frame.getContentPane().add(btnStartGame);
		btnStartGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bigGame.setGamesRunning(1);
				bigGame.setGame1(new RipRapGame(20, bigGame, frame));
				bigGame.getGame1().run();
			}
		});
		
		
		JButton btnStartGame_1 = new JButton();
		ImageIcon imgGame2 = new ImageIcon("images/Game2Button.png");
		btnStartGame_1.setIcon(imgGame2);
		btnStartGame_1.setBounds(frame.getContentPane().getWidth()/3, 50, frame.getContentPane().getWidth()/3, frame.getContentPane().getHeight()-50);
		frame.getContentPane().add(btnStartGame_1);
		btnStartGame_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (bigGame.getGamesComplete()[0] == true) {
					bigGame.setGamesRunning(2);
					bigGame.setGame2(new CrabCatcherGame(0, null, 0, 0, 0, null, 0, false, bigGame, frame));
					bigGame.getGame2().startGame();
				}
				else {
					JOptionPane.showMessageDialog(null, "Complete game 1 in order to play this game");
				}
			}
		});
		
		JButton btnStartGame_2 = new JButton();
		ImageIcon imgGame3 = new ImageIcon("images/Game3Button.png");
		btnStartGame_2.setIcon(imgGame3);
		btnStartGame_2.setBounds(2*frame.getContentPane().getWidth()/3, 50, frame.getContentPane().getWidth()/3, frame.getContentPane().getHeight()-50);
		btnStartGame_2.setFont(new Font("Serif", Font.PLAIN, 50));
		frame.getContentPane().add(btnStartGame_2);
		btnStartGame_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (bigGame.getGamesComplete()[1] == true) {
					bigGame.setGamesRunning(3);
					bigGame.setGame3(new Game3(bigGame));
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
		JButton viewHighScores = new JButton("View High Scores");
		viewHighScores.setBounds(0, 0, frame.getContentPane().getWidth()/3, 50);
		viewHighScores.setFont(new Font("Serif", Font.PLAIN, 50));
		frame.getContentPane().add(viewHighScores);
		viewHighScores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, bigGame.getHighscores());
			}
		});
		
		JLabel currentScore = new JLabel("Overall Score: " + bigGame.getOverallScore());
		currentScore.setBounds(new Rectangle(frame.getContentPane().getWidth()/3,0,frame.getContentPane().getWidth()/3,50));
		currentScore.setHorizontalAlignment((int)JPanel.CENTER_ALIGNMENT);
		currentScore.setFont(new Font("Serif", Font.PLAIN, 50));
		currentScore.setVisible(true);
		frame.getContentPane().add(currentScore);
		
		frame.repaint();
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
	
	public String toString() {
		return "Frame: " + getFrame();
	}
	
	public JFrame getFrame() {
		return frame;
	}

}
