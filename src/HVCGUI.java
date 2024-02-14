import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

public class HVCGUI extends JFrame implements ActionListener {

	// bring in parameter for player use paratmers in main to start game

//	JPanel playerSide = new JPanel(new BorderLayout());
//	JPanel topPanel = new JPanel(new GridLayout(1, 1));
//	JPanel AISide = new JPanel(new BorderLayout());
	JTextArea tA = new JTextArea();
	JScrollPane scroll;
	
	
	String[] P1StringShipArray;
	String[] P2StringShipArray;
	

	JButton[] p1FireGrid;
	JButton[] p1DisplayGrid;
	JButton[] p2DisplayGrid;
	
	Game game;
	int count = 1;

	int GRID_SIZE = 9;
	
	
	
	JPanel playerSide = new JPanel(new GridLayout(2, 1));
	JPanel AISide = new JPanel(new GridLayout(2, 1));
	JPanel topPanel = new JPanel(new GridLayout(1, 1));
	JProgressBar p1LBar;
	JProgressBar p1DH;
	JProgressBar p1SH;
	JProgressBar p1AH;
	JProgressBar p1BH;
	
	
	
	

	public HVCGUI(Game game) throws InterruptedException {
		this.game = game;
		P1StringShipArray = game.p1.setAIShipGrid();
		P2StringShipArray = game.p2.setAIShipGrid();
		intiFrame();
	}
	
	
	private void intiFrame() throws InterruptedException {
		
		this.setTitle("BattleShip");
		playerSide.add(createFirePane());
//		disablePlayerFireGrid();
		playerSide.add(setP1StringToButton(P1StringShipArray));
		// add player ship placement
		AISide.add(setP2StringToButton(P2StringShipArray));
		AISide.add(setP1StateusGrid());
		topPanel.add(playerSide);
		topPanel.add(AISide);
		p1DisplayGrid[0].setText("DG");
		p2DisplayGrid[0].setText("P2");
		
		this.setContentPane(topPanel);
		this.setSize(750, 470);
		this.setVisible(true);
		setGameBoard();
		
		initThread();

	}
	
	private Container setP1StateusGrid() {
		JPanel tempJPanel = new JPanel(new BorderLayout());
		JPanel grid = new JPanel(new GridLayout(10, 1));
		JLabel p1LJLabel = new JLabel();
		p1LJLabel.setText("Boats");
		p1LBar = new JProgressBar(0, 4);
		p1LBar.setValue(game.p1.lives);
		JLabel p1DL = new JLabel();
		p1DL.setText("Destroyer");
		p1DH = new JProgressBar(0, 3);
		p1DH.setValue(game.p1.d.health);
		JLabel p1SL = new JLabel();
		p1SL.setText("Submarine");
		p1SH = new JProgressBar(0, 3);
		p1SH.setValue(game.p1.s.health);
		JLabel p1AL = new JLabel();
		p1AL.setText("Aircraft Carrier");
		p1AH = new JProgressBar(0, 5);
		p1AH.setValue(game.p1.a.health);
		JLabel p1BL = new JLabel();
		p1BL.setText("Battleship");
		p1BH = new JProgressBar(0, 4);
		p1BH.setValue(game.p1.b.health);

		grid.add(p1LJLabel);
		grid.add(p1LBar);
		grid.add(p1DL);
		grid.add(p1DH);
		grid.add(p1SL);
		grid.add(p1SH);
		grid.add(p1AL);
		grid.add(p1AH);
		grid.add(p1BL);
		grid.add(p1BH);
		tempJPanel.add(grid, BorderLayout.NORTH);
		return tempJPanel;

	}
	
	private Container setP2StateusGrid() {
		JPanel tempJPanel = new JPanel(new BorderLayout());
		JPanel grid = new JPanel(new GridLayout(10, 1));
		JLabel p2LJLabel = new JLabel();
		p2LJLabel.setText("Boats");
		
		JLabel p2DL = new JLabel();
		p2DL.setText("Destroyer");
		
		JLabel p2SL = new JLabel();
		p2SL.setText("Submarine");
		
		JLabel p2AL = new JLabel();
		p2AL.setText("Aircraft Carrier");
		
		JLabel p2BL = new JLabel();
		p2BL.setText("Battleship");
		
		grid.add(p2LJLabel);
		
		grid.add(p2DL);
		
		grid.add(p2SL);
		grid.add(p2AL);
		grid.add(p2BL);
		tempJPanel.add(grid, BorderLayout.NORTH);

		return tempJPanel;

	}

	private void updateBars() {
		p1LBar.setValue(game.p1.lives);
		p1SH.setValue(game.p1.s.health);
		p1AH.setValue(game.p1.a.health);
		p1DH.setValue(game.p1.d.health);
		p1BH.setValue(game.p1.b.health);

		
	}
	
	
	public Container setP1StringToButton(String[] temp) {
		int numButtons = GRID_SIZE * GRID_SIZE;
		JPanel grid = new JPanel(new GridLayout(GRID_SIZE, GRID_SIZE));
		p1DisplayGrid = new JButton[numButtons];
		for (int i = 0; i < numButtons; i++) {
			p1DisplayGrid[i] = new JButton("");
			p1DisplayGrid[i].setActionCommand("" + i);
			p1DisplayGrid[i].addActionListener(this);
			p1DisplayGrid[i].setBackground(Color.BLUE);
			p1DisplayGrid[i].setOpaque(true);
			grid.add(p1DisplayGrid[i]);
		}

		for (int i = 0; i < 81; i++) {
			if (temp[i] == ("A")) {
				p1DisplayGrid[i].setText("A");
			} else if (temp[i] == ("B")) {
				p1DisplayGrid[i].setText("B");
			} else if (temp[i] == ("D")) {
				p1DisplayGrid[i].setText("D");
			} else if (temp[i] == ("S")) {
				p1DisplayGrid[i].setText("S");
			}
		}

		setHeadings(p1DisplayGrid);
		return grid;

	}
	
	public Container setP2StringToButton(String[] temp) {
		int numButtons = GRID_SIZE * GRID_SIZE;
		JPanel grid = new JPanel(new GridLayout(GRID_SIZE, GRID_SIZE));
		p2DisplayGrid = new JButton[numButtons];
		for (int i = 0; i < numButtons; i++) {
			p2DisplayGrid[i] = new JButton("");
			p2DisplayGrid[i].setActionCommand("" + i);
			p2DisplayGrid[i].addActionListener(this);
			p2DisplayGrid[i].setBackground(Color.BLUE);
			p2DisplayGrid[i].setOpaque(true);
			grid.add(p2DisplayGrid[i]);
		}

		for (int i = 0; i < 81; i++) {
			if (temp[i] == ("A")) {
				p2DisplayGrid[i].setText("A");
			} else if (temp[i] == ("B")) {
				p2DisplayGrid[i].setText("B");
			} else if (temp[i] == ("D")) {
				p2DisplayGrid[i].setText("D");
			} else if (temp[i] == ("S")) {
				p2DisplayGrid[i].setText("S");
			}
		}

		setHeadings(p2DisplayGrid);
		p2DisplayGrid[0].setText("CG");
		return grid;

	}
	
	private void setGameBoard() {
		game.p1DisplayGrid = p1DisplayGrid;
		game.p2DisplayGrid = p2DisplayGrid;

	}



//	private void intiFrame() throws InterruptedException {
//
//		this.setTitle("BattleShip");
//		tA.setEditable(false);
//		scroll = new JScrollPane(tA);
//		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
//		playerSide.add(createFirePane(), BorderLayout.NORTH);
//		disablePlayerFireGrid();
//		playerSide.add(setHumanStringToButton(P1StringShipArray), BorderLayout.SOUTH);
//		AISide.add(setAIStringToButton(P2StringShipArray), BorderLayout.NORTH);
//		hideAIGrid();
//		AISide.add(scroll, BorderLayout.CENTER);
//		topPanel.add(playerSide);
//		topPanel.add(AISide);
//		tA.setText("Welcome to battleShip place your ships");
//		disanblePlayerDisGrid();
//		this.setContentPane(topPanel);
//		this.setSize(800, 800);
//		this.setVisible(true);
//		initThread();
//
//		
//	}

	public void hideAIGrid() {
		for (int i = 0; i < p2DisplayGrid.length; i++) {
			p2DisplayGrid[i].setVisible(false);
		}

	}

	public void showAIGrid() {
		for (int i = 0; i < p2DisplayGrid.length; i++) {
			p2DisplayGrid[i].setVisible(true);
		}
	}

	public void disanblePlayerDisGrid() {
		for (int i = 0; i < p1DisplayGrid.length; i++) {
			p1DisplayGrid[i].setEnabled(false);
		}
	}

	public void enablePlayerDisGrid() {
		for (int i = 0; i < p1DisplayGrid.length; i++) {
			p1DisplayGrid[i].setEnabled(true);
		}
	}

	public void enablePlayerFireGrid() {
		for (int i = 0; i < p1FireGrid.length; i++) {
			p1FireGrid[i].setEnabled(true);
		}
	}

	public void disablePlayerFireGrid() {
		for (int i = 0; i < p1FireGrid.length; i++) {
			p1FireGrid[i].setEnabled(false);
		}
	}

	public Container createDisplayPane() {
		int numButtons = GRID_SIZE * GRID_SIZE;
		JPanel grid = new JPanel(new GridLayout(GRID_SIZE, GRID_SIZE));
		p1DisplayGrid = new JButton[numButtons];

		for (int i = 0; i < numButtons; i++) {
			p1DisplayGrid[i] = new JButton(" ");
			p1DisplayGrid[i].setActionCommand("" + i);
			p1DisplayGrid[i].addActionListener(this);
			p1DisplayGrid[i].setOpaque(true);
			grid.add(p1DisplayGrid[i]);

		}

		setHeadings(p1DisplayGrid);
		p1DisplayGrid[0].setText("DG");
		return grid;
	}
	
	public Container setHumanStringToButton(String[] temp) {
		int numButtons = GRID_SIZE * GRID_SIZE;
		JPanel grid = new JPanel(new GridLayout(GRID_SIZE, GRID_SIZE));
		p1DisplayGrid = new JButton[numButtons];
		for (int i = 0; i < numButtons; i++) {
			p1DisplayGrid[i] = new JButton("");
			p1DisplayGrid[i].setActionCommand("" + i);

			grid.add(p1DisplayGrid[i]);
		}

		for (int i = 0; i < 81; i++) {
			if (temp[i] == ("A")) {
				p1DisplayGrid[i].setText("A");
			} else if (temp[i] == ("B")) {
				p1DisplayGrid[i].setText("B");
			} else if (temp[i] == ("D")) {
				p1DisplayGrid[i].setText("D");
			} else if (temp[i] == ("S")) {
				p1DisplayGrid[i].setText("S");
			}
		}

		setHeadings(p1DisplayGrid);
		p1DisplayGrid[0].setText("DG");
		return grid;

	}


	public Container setAIStringToButton(String[] temp) {
		int numButtons = GRID_SIZE * GRID_SIZE;
		JPanel grid = new JPanel(new GridLayout(GRID_SIZE, GRID_SIZE));
		p2DisplayGrid = new JButton[numButtons];
		for (int i = 0; i < numButtons; i++) {
			p2DisplayGrid[i] = new JButton("");
			p2DisplayGrid[i].setActionCommand("" + i);

			grid.add(p2DisplayGrid[i]);
		}

		for (int i = 0; i < 81; i++) {
			if (temp[i] == ("A")) {
				p2DisplayGrid[i].setText("A");
			} else if (temp[i] == ("B")) {
				p2DisplayGrid[i].setText("B");
			} else if (temp[i] == ("D")) {
				p2DisplayGrid[i].setText("D");
			} else if (temp[i] == ("S")) {
				p2DisplayGrid[i].setText("S");
			}
		}

		setHeadings(p2DisplayGrid);
		p2DisplayGrid[0].setText("CG");
		return grid;

	}

	private void initThread() {
//		System.out.println("started thread");
		Thread worker = new Thread() {
			public void run() {
				try {
					startGame();
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
					}
				});
			}
		};
		worker.start();
	}

	private void startGame() throws InterruptedException {
		
		Thread.sleep(700);
		updateBars();
		if (game.p1.lives == 0) {
			game.playing = false;
			game.p1Win = false;
			System.out.println("p2 wins");
		}
			// System.exit(1);
		if (game.p2.lives == 0) {
			game.playing = false;
			game.p1Win = true;
			System.out.println("p1 wins");
			// System.exit(1);
		}
		if (game.p1Turn) {
			int p1Shot = game.p1.fire(game, game.p1.difficulty);
			p2DisplayGrid[p1Shot].doClick();
			game.handle(p1Shot, 1);
			game.p1Turn = false;
		} else {// p2 turn
			int p2Shot = game.p2.fire(game, game.p2.difficulty);
			p1DisplayGrid[p2Shot].doClick();
			game.handle(p2Shot, 2);
			game.p1Turn = true;

		}
	}


//		while (game.playing) {
//			if (SwingUtilities.isEventDispatchThread()) {
//				System.out.println("EDT ");
//			} else {
//				System.out.println("EDT false");
//
//			}
//			if (game.p1Turn) {
//				System.out.println("p1picking");
//
//			} else {
//				System.out.println("p2turn");
////				int aiShot = game.p2.fireRan();
////				p1DisplayGrid[aiShot].doClick();
////				p1DisplayGrid[aiShot].setText("x");
//				game.p1Turn = true;
//
//			}
//
//		}
//
//	}

	public Container createFirePane() {
		int numButtons = GRID_SIZE * GRID_SIZE;
		JPanel grid = new JPanel(new GridLayout(GRID_SIZE, GRID_SIZE));
		p1FireGrid = new JButton[numButtons];
		for (int i = 0; i < numButtons; i++) {
			p1FireGrid[i] = new JButton(" ");
			p1FireGrid[i].setActionCommand("" + i);
			p1FireGrid[i].addActionListener(this);
			p1FireGrid[i].setOpaque(true);
			grid.add(p1FireGrid[i]);
		}

		setHeadings(p1FireGrid);
		p1FireGrid[0].setText("FG");
		return grid;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		String classname = getClassName(e.getSource());
		if ("JButton".equals(classname)) {
			JButton button = (JButton) (e.getSource());
			if (game.p1Turn) {
				((JButton) e.getSource()).setBackground(Color.BLACK);
				game.p1Turn = false;
			} else {
				((JButton) e.getSource()).setBackground(Color.RED);
				game.p1Turn = true;

			}
		}
	}
	
	
	private void UpdateText() {
		tA.setText("\n\nP1 Des: " + game.p1.d.health + "\nP1 Sub : "
				+ game.p1.s.health + "\nP1 Air: " + game.p1.a.health
				+ "\nP1 Bat : " + game.p1.b.health + "\nP1 lives: "
				+ game.p1.lives + "\n\nP2 Des: " + game.p2.d.health
				+ "\nP2 Sub : " + game.p2.s.health + "\nP2 Air: "
				+ game.p2.a.health + "\nP2 Bat : " + game.p2.b.health
				+ "\nP2 lives: " + game.p2.lives);

	}

	protected String getClassName(Object o) {
		String classString = o.getClass().getName();
		int dotIndex = classString.lastIndexOf(".");
		return classString.substring(dotIndex + 1);
	}

	/**
	 * 
	 * @param buttonArray
	 *            sets the headings for the first row and column for the user to
	 *            read
	 */
	private void setHeadings(JButton[] buttonArray) {

		for (int i = 0; i < 9; i++) {

			setGuiSquare(buttonArray, 0, i, Integer.toString(i));
			buttonArray[i].setEnabled(false);

		}

		setGuiSquare(buttonArray, 1, 0, "a");
		buttonArray[9].setEnabled(false);
		setGuiSquare(buttonArray, 2, 0, "b");
		buttonArray[18].setEnabled(false);
		setGuiSquare(buttonArray, 3, 0, "c");
		buttonArray[27].setEnabled(false);
		setGuiSquare(buttonArray, 4, 0, "d");
		buttonArray[36].setEnabled(false);
		setGuiSquare(buttonArray, 5, 0, "e");
		buttonArray[45].setEnabled(false);
		setGuiSquare(buttonArray, 6, 0, "f");
		buttonArray[54].setEnabled(false);
		setGuiSquare(buttonArray, 7, 0, "g");
		buttonArray[63].setEnabled(false);
		setGuiSquare(buttonArray, 8, 0, "h");
		buttonArray[72].setEnabled(false);

	}

	/**
	 * only called from the set heading method, this just finishes the job for
	 * the user to see the index of the button array
	 */

	public boolean setGuiSquare(JButton[] ButtonArray, int row, int col,
			String c) {
		int bnum = row * GRID_SIZE + col;
		if (bnum >= (GRID_SIZE * GRID_SIZE)) {
			return false;
		} else {
			ButtonArray[bnum].setText(c);
		}
		return true;
	}

}
