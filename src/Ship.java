public class Ship {
	String name;
	int size;
	int health;
	boolean deployed;

	public Ship(String name, int size, int health, boolean deployed) {
		this.name = name;
		this.size = size;
		this.health = health;
		this.deployed = deployed;

	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public String getName() {
		return this.name;
	}

	public void setHealth(String name) {
		this.name = name;
	}

	public boolean getDeployed() {
		return deployed;
	}

	public void setDeployed(boolean deployed) {
		this.deployed = deployed;
	}

	public String toString() {
		return this.name + " health: " + this.health + "\n";
	}

}