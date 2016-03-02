import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.print.attribute.standard.JobName;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class CVCGUI extends JFrame implements ActionListener {
	JPanel p1Side = new JPanel(new GridLayout(2, 1));
	JPanel p2Side = new JPanel(new GridLayout(2, 1));
	JPanel topPanel = new JPanel(new GridLayout(1, 1));
	JProgressBar p1LBar;
	JProgressBar p1DH;
	JProgressBar p1SH;
	JProgressBar p1AH;
	JProgressBar p1BH;
	JProgressBar p2LBar;
	JProgressBar p2DH;
	JProgressBar p2SH;
	JProgressBar p2AH;
	JProgressBar p2BH;

	String[] P1StringShipArray;
	String[] P2StringShipArray;

	JButton[] p1DisplayGrid;
	JButton[] p2DisplayGrid;

	Game game;
	int count = 1;

	int GRID_SIZE = 9;

	public CVCGUI(Game game) throws InterruptedException {
		this.game = game;
		P1StringShipArray = game.p1.setAIShipGrid();
		P2StringShipArray = game.p2.setAIShipGrid();
		intiFrame();
	}

	private void intiFrame() throws InterruptedException {
		this.setTitle("BattleShip");
		p1Side.add(setP1StringToButton(P1StringShipArray));
		p1Side.add(setP1StateusGrid());
		p2Side.add(setP2StringToButton(P2StringShipArray));
		p2Side.add(setP2StateusGrid());
		topPanel.add(p1Side);
		topPanel.add(p2Side);
		p1DisplayGrid[0].setText("P1");
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

	private void updateBars() {
		p1LBar.setValue(game.p1.lives);
		p1SH.setValue(game.p1.s.health);
		p1AH.setValue(game.p1.a.health);
		p1DH.setValue(game.p1.d.health);
		p1BH.setValue(game.p1.b.health);

		p2LBar.setValue(game.p2.lives);
		p2SH.setValue(game.p2.s.health);
		p2AH.setValue(game.p2.a.health);
		p2DH.setValue(game.p2.d.health);
		p2BH.setValue(game.p2.b.health);

	}

	private Container setP2StateusGrid() {
		JPanel tempJPanel = new JPanel(new BorderLayout());
		JPanel grid = new JPanel(new GridLayout(10, 1));
		JLabel p2LJLabel = new JLabel();
		p2LJLabel.setText("Boats");
		p2LBar = new JProgressBar(0, 4);
		p2LBar.setValue(game.p2.lives);
		JLabel p2DL = new JLabel();
		p2DL.setText("Destroyer");
		p2DH = new JProgressBar(0, 3);
		p2DH.setValue(game.p2.d.health);
		JLabel p2SL = new JLabel();
		p2SL.setText("Submarine");
		p2SH = new JProgressBar(0, 3);
		p2SH.setValue(game.p2.s.health);
		JLabel p2AL = new JLabel();
		p2AL.setText("Aircraft Carrier");
		p2AH = new JProgressBar(0, 5);
		p2AH.setValue(game.p2.a.health);
		JLabel p2BL = new JLabel();
		p2BL.setText("Battleship");
		p2BH = new JProgressBar(0, 4);
		p2BH.setValue(game.p2.b.health);

		grid.add(p2LJLabel);
		grid.add(p2LBar);
		grid.add(p2DL);
		grid.add(p2DH);
		grid.add(p2SL);
		grid.add(p2SH);
		grid.add(p2AL);
		grid.add(p2AH);
		grid.add(p2BL);
		grid.add(p2BH);
		tempJPanel.add(grid, BorderLayout.NORTH);

		return tempJPanel;

	}

	private void setGameBoard() {
		game.p1DisplayGrid = p1DisplayGrid;
		game.p2DisplayGrid = p2DisplayGrid;

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

	private void initThread() {
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

		while (game.playing) {
			Thread.sleep(700);
			updateBars();
			if (game.p1.lives == 0) {
				game.playing = false;
				game.p1Win = false;
				System.out.println("p2 wins");

				// System.exit(1);
			} else if (game.p2.lives == 0) {
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
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String classname = getClassName(e.getSource());
		if (classname.equals("JButton")) {
			JButton button = (JButton) (e.getSource());

			if (button.getText().equals("A") || button.getText().equals("B")
					|| button.getText().equals("D")
					|| button.getText().equals("S")) {
				((JButton) e.getSource()).setBackground(Color.RED);

			} else {
				((JButton) e.getSource()).setBackground(Color.BLACK);

			}
		}
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
			buttonArray[i].setBackground(Color.WHITE);

		}

		setGuiSquare(buttonArray, 1, 0, "a");
		buttonArray[9].setEnabled(false);
		buttonArray[9].setBackground(Color.WHITE);
		setGuiSquare(buttonArray, 2, 0, "b");
		buttonArray[18].setEnabled(false);
		buttonArray[18].setBackground(Color.WHITE);
		setGuiSquare(buttonArray, 3, 0, "c");
		buttonArray[27].setEnabled(false);
		buttonArray[27].setBackground(Color.WHITE);
		setGuiSquare(buttonArray, 4, 0, "d");
		buttonArray[36].setEnabled(false);
		buttonArray[36].setBackground(Color.WHITE);
		setGuiSquare(buttonArray, 5, 0, "e");
		buttonArray[45].setEnabled(false);
		buttonArray[45].setBackground(Color.WHITE);
		setGuiSquare(buttonArray, 6, 0, "f");
		buttonArray[54].setEnabled(false);
		buttonArray[54].setBackground(Color.WHITE);
		setGuiSquare(buttonArray, 7, 0, "g");
		buttonArray[63].setEnabled(false);
		buttonArray[63].setBackground(Color.WHITE);
		setGuiSquare(buttonArray, 8, 0, "h");
		buttonArray[72].setEnabled(false);
		buttonArray[72].setBackground(Color.WHITE);
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