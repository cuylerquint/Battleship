import javax.swing.JButton;

public class Game {
	Player p1;
	Player p2;
	boolean playing;
	boolean p1Turn;
	boolean p1Win;
	boolean isPicking;
	JButton[] p1DisplayGrid;
	JButton[] p2DisplayGrid;
	boolean hit;
	

	public Game(Player p1, Player p2) {
		this.p1 = p1;
		this.p2 = p2;
		this.playing = true;
		this.p1Turn = true;
		this.isPicking = true;
		

	}

	public void handle(int temp, int player) {
		
		if (player == 1) {
			if (p2DisplayGrid[temp].getText().equals("D")) {
				p2.d.health = p2.d.health - 1;
				if (p2.d.health == 0) {
					p2.lives = p2.lives - 1;
				}

			} else if (p2DisplayGrid[temp].getText().equals("S")) {
				
				p2.s.health = p2.s.health - 1;
				if (p2.s.health == 0) {
					p2.lives = p2.lives - 1;
				}
				
			} else if (p2DisplayGrid[temp].getText().equals("A")) {
				p2.a.health = p2.a.health - 1;
				if (p2.a.health == 0) {
					p2.lives = p2.lives - 1;
				}
				
			} else if (p2DisplayGrid[temp].getText().equals("B")) {
				p2.b.health = p2.b.health - 1;
				if (p2.b.health == 0) {
					p2.lives = p2.lives - 1;
				}

			}
			
		} else if (player == 2 ) { //p2 at p1 ships
			
			if (p1DisplayGrid[temp].getText().equals("D")) {
				p1.d.health = p1.d.health - 1;
				if (p1.d.health == 0) {
					p1.lives = p1.lives - 1;
				}
				
			} else if (p1DisplayGrid[temp].getText().equals("S")) {
				p1.s.health = p1.s.health - 1;
				if (p1.s.health == 0) {
					p1.lives = p1.lives - 1;
				}
				
			} else if (p1DisplayGrid[temp].getText().equals("A")) {
				p1.a.health = p1.a.health - 1;
				if (p1.a.health == 0) {
					p1.lives = p1.lives - 1;
				}
				
			} else if (p1DisplayGrid[temp].getText().equals("B")) {
				p1.b.health = p1.b.health - 1;
				if (p1.b.health == 0) {
					p1.lives = p1.lives - 1;
				}

			}

		}
		
	}
}
