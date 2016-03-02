import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.*;

import javax.swing.*;

import java.util.ArrayList;
import java.util.Random;

public class MenuGui implements ActionListener {
	public ArrayList<Integer> userShotsFired, compShotsFired;
	static JFrame frame = new JFrame("Battleships");
	Random rGP = new Random();
	Random rGA = new Random();
	JFrame CVCGUI = new JFrame();
	
	
	static JFrame resultFrame = new JFrame("AI vs. AI Difficulty Select");
	static JFrame CVCDIFF = new JFrame("AI vs. AI Difficulty Select");
	JRadioButton p1E = new JRadioButton("Easy");
	JRadioButton p1M = new JRadioButton("Medium");
	JRadioButton p1H = new JRadioButton("Hard");
	
	JRadioButton p2E = new JRadioButton("Easy");
	JRadioButton p2M = new JRadioButton("Medium");
	JRadioButton p2H = new JRadioButton("Hard");

	int p1Difficulty;
	int p2Difficulty;
	

	public JMenuBar createMenu() {
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("Menu");
		JMenuItem CVC;
		JMenuItem HVC;
		menuBar.add(menu);
		CVC = new JMenuItem("AI vs. AI");
		CVC.addActionListener(this);
		HVC = new JMenuItem("Human vs. AI");
		HVC.addActionListener(this);
		menu.add(CVC);
		menu.add(HVC);
		menu.addSeparator();
		return menuBar;
	}

	/**
	 * First frame shown to the user
	 */
	private static void baseGUI() {

		frame = new JFrame("Battleship");
		ImageIcon image = new ImageIcon("bshipimage.png");
		JLabel imageL = new JLabel();
		imageL.setIcon(image);
		frame.add(imageL);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		MenuGui base = new MenuGui();
		frame.setJMenuBar(base.createMenu());
		frame.pack();
		frame.setVisible(true);

	}

	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				baseGUI();
			}

		});
	}

	/**
	 * This method creates and inti all of our classes, after a game object is
	 * created two players are created and from this the AI player uses the
	 * setAIship algorithm to derive a String[] that holds random valid ships
	 * positions. Then this String array is passed into GameGui object for the
	 * proper grids to be read
	 */
	// // public void NewGameAI() {
	//
	// String[] AIShipGrid = new String[81];
	//
	// // AIShipGrid = game.AI.setAIShipGrid();
	// // GameGui PVCFrame = new GameGui(AIShipGrid);
	//
	// }

	private void NewGameCVC() throws InterruptedException {

		initCVCDIFF();

		// CVCDIFF.setContentPane(contentPane);

		// pop up off choice to give new gui parmeters for new game type
		// once parameters are meet create new gui class
		// maybe pass game object into the new class

	}

	private void initCVCDIFF() throws InterruptedException {
		CVCDIFF = new JFrame("AI vs. AI Difficulty Select");
		JPanel topPanel = new JPanel(new GridLayout(5, 1));
		
		JLabel p1L = new JLabel("Player 1");
		JLabel p2L = new JLabel("Player 2");
		JButton start = new JButton("Start");
		start.addActionListener(this);
		JPanel p1RB = new JPanel(new FlowLayout());
		JPanel p2RB = new JPanel(new FlowLayout());
		
		
		

		ButtonGroup group1 = new ButtonGroup();
		group1.add(p1E);
		group1.add(p1M);
		group1.add(p1H);
		p1RB.add(p1E);
		p1RB.add(p1M);
		p1RB.add(p1H);
		
		ButtonGroup group2 = new ButtonGroup();
		group2.add(p2E);
		group2.add(p2M);
		group2.add(p2H);
		p2RB.add(p2E);
		p2RB.add(p2M);
		p2RB.add(p2H);
		
		
	
		topPanel.add(p1L);
		topPanel.add(p1RB);
		topPanel.add(p2L);
		topPanel.add(p2RB);
		topPanel.add(start);
		
		
		CVCDIFF.setContentPane(topPanel);
		CVCDIFF.setSize(250, 200);
		CVCDIFF.setVisible(true);
		

		// CVCGUI frame = new CVCGUI(game);

		// TODO Auto-generated method stub

	}


	public void actionPerformed(ActionEvent e) {
		String classname = getClassName(e.getSource());

		if (classname.equals("JMenuItem")) {
			JMenuItem menusource = (JMenuItem) (e.getSource());
			String menutext = menusource.getText();

			if (menutext.equals("AI vs. AI")) {
				try {
					NewGameCVC();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			} else if (menutext.equals("Human vs. AI")) {
				try {
					NewGameHVC();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		if (classname.equals("JButton")) {
			JButton button = (JButton) (e.getSource());
			if (button.getText().equals("New Game")){
				resultFrame.dispose();
				CVCGUI.dispose();
				
			}
			if (button.getText().equals("Start")){
				
				Player p1 = new Player(true, 0);
				Player p2 = new Player(true, 0);
				
				Game game = new Game(p1, p2);
				
				game.p1.difficulty = setDiff(1);
				game.p2.difficulty = setDiff(2);
				
				try {
					CVCGUI = new CVCGUI(game);
//					CVCGUI frame = new CVCGUI(game);
					if(game.playing == false){
						displayResult(game);
					}

				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		}

	}
	
	
	private void displayResult(Game game) {
		resultFrame = new JFrame();
		JPanel topPanel = new JPanel(new GridLayout(2, 1));
		JLabel textJLabel = new JLabel();
		JButton newGameButton = new JButton("New Game");
		newGameButton.addActionListener(this);
		if(game.p1Win == true){
			textJLabel.setText("Player 1 Wins");
		}else{
			textJLabel.setText("Player 2 Wins");
		}
		
		
		
		topPanel.add(textJLabel);
		topPanel.add(newGameButton);
		resultFrame.setContentPane(topPanel);
		resultFrame.setSize(250, 200);
		resultFrame.setVisible(true);
		
	}


	private int setDiff(int temp) {
		if(temp == 1){
			if(p1E.isSelected()){
				return 1;
			}else if(p1M.isSelected()){
				return 2;
			}else if(p1H.isSelected()){
				return 3;
			}
		}else{
			if(p2E.isSelected()){
				return 1;
			}else if(p2M.isSelected()){
				return 2;
			}else if(p2H.isSelected()){
				return 3;
			}
		}
		return -1;
	}
	
	

	private void NewGameHVC() throws InterruptedException {

		Player p1 = new Player(true, 0);
		Player p2 = new Player(true, 0);

		Game game = new Game(p1, p2);
		HVCGUI frame = new HVCGUI(game);

	}

	protected String getClassName(Object o) {
		String classString = o.getClass().getName();
		int dotIndex = classString.lastIndexOf(".");
		return classString.substring(dotIndex + 1);
	}

}
