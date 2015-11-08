package OverallGame;

import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JPanel;

import Game1.RipRapGame;
import Game2.CrabCatcherGame;
import Game3.Game3;

public class gameWindow {
	private OverallGame bigGame;

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OverallGame bigGame = new OverallGame();
					gameWindow window = new gameWindow(bigGame);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

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
			}
		});
		frame.setBounds(100, 100, 950, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton btnStartGame = new JButton("Start Game 1");
		btnStartGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bigGame.setGamesRunning(1);
				bigGame.setGame2(new RipRapGame(0, 0, 0, null, null, null, null, null, bigGame, null));
				bigGame.getGame1().update(frame);
			}
		});
		frame.getContentPane().add(btnStartGame, BorderLayout.WEST);
		
		JButton btnStartGame_1 = new JButton("Start Game 2");
		frame.getContentPane().add(btnStartGame_1, BorderLayout.CENTER);
		btnStartGame_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bigGame.setGamesRunning(1);
				bigGame.setGame1(new CrabCatcherGame(0, 0, null, 0, 0, 0, null, 0, false, bigGame, frame));
				bigGame.getGame2().update(frame);
			}
		});
		
		JButton btnStartGame_2 = new JButton("Start Game 3");
		frame.getContentPane().add(btnStartGame_2, BorderLayout.EAST);
		btnStartGame_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bigGame.setGamesRunning(3);
				bigGame.setGame3(new Game3(bigGame));
				bigGame.getGame3().update(frame);
			}
		});
		frame.setVisible(true);
	}

	public JFrame getFrame() {
		return frame;
	}
}
