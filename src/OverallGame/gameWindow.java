package OverallGame;

import java.awt.Font;

import javax.swing.JFrame;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import Game1.RipRapGame;
import Game2.CrabCatcherGame;
import Game3.Game3;

public class gameWindow {
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
		JButton btnStartGame = new JButton("Start Game 1");	
		btnStartGame.setBounds(0, 0, frame.getContentPane().getWidth()/3, frame.getContentPane().getHeight());
		btnStartGame.setFont(new Font("Serif", Font.PLAIN, 50));
		frame.getContentPane().add(btnStartGame);
		btnStartGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bigGame.setGameRunning(1);
				bigGame.setGame1(new RipRapGame(20, bigGame, frame));
				bigGame.getGame1().run();
			}
		});
		
		
		JButton btnStartGame_1 = new JButton("Start Game 2");
		btnStartGame_1.setBounds(frame.getContentPane().getWidth()/3, 0, frame.getContentPane().getWidth()/3, frame.getContentPane().getHeight());
		btnStartGame_1.setFont(new Font("Serif", Font.PLAIN, 50));
		frame.getContentPane().add(btnStartGame_1);
		btnStartGame_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bigGame.setGameRunning(1);
				bigGame.setGame2(new CrabCatcherGame(0, null, 0, 0, 0, null, 0, false, bigGame, frame));
				bigGame.getGame2().startGame();
			}
		});
		
		JButton btnStartGame_2 = new JButton("Start Game 3");
		btnStartGame_2.setBounds(2*frame.getContentPane().getWidth()/3, 0, frame.getContentPane().getWidth()/3, frame.getContentPane().getHeight());
		btnStartGame_2.setFont(new Font("Serif", Font.PLAIN, 50));
		frame.getContentPane().add(btnStartGame_2);
		btnStartGame_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bigGame.setGameRunning(3);
				bigGame.setGame3(new Game3(bigGame));
				bigGame.getGame3().update();
			}
		});
		frame.repaint();
	}
	
	public JFrame getFrame() {
		return frame;
	}

}
