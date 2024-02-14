import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Player {
	int difficulty;
	boolean isHuman;
	Random rG;
	int lives;
	String[] SSA;
	boolean isVertical;
	public int GRID_SIZE = 9;
	int randomPos;
	ArrayList<Ship> ships = new ArrayList<Ship>();
	ArrayList<Integer> compShotsFired = new ArrayList<Integer>(64);
	ArrayList<Integer> dHits = new ArrayList<>(3);
	ArrayList<Integer> sHits = new ArrayList<>(3);
	ArrayList<Integer> bHits = new ArrayList<>(5);
	ArrayList<Integer> aHits = new ArrayList<>(4);

	ArrayList<Integer> lv3Shots = new ArrayList<>(10);

	boolean dIsVert;
	boolean sIsVert;
	boolean bIsVert;
	boolean aIsVert;

	Ship d;
	Ship s;
	Ship b;
	Ship a;

	public Player(boolean isHuman, int difficulty) {
		lives = 4;
		this.isHuman = isHuman;
		this.difficulty = difficulty;
		this.SSA = new String[81];

		for (int i = 0; i < 81; i++) {
			SSA[i] = " ";

		}
		this.d = new Ship("Destroyer", 3, 3, false);
		ships.add(d);
		this.s = new Ship("Submarine", 3, 3, false);
		ships.add(s);
		this.a = new Ship("Aircraft Carrier", 5, 5, false);
		ships.add(a);
		this.b = new Ship("Battleship", 4, 4, false);
		ships.add(b);
		addL3Hits();

	}

	private void addL3Hits() {
		lv3Shots.add(12);
		lv3Shots.add(15);
		lv3Shots.add(19);
		lv3Shots.add(22);
		lv3Shots.add(25);
		lv3Shots.add(29);
		lv3Shots.add(32);
		lv3Shots.add(35);
		lv3Shots.add(39);
		lv3Shots.add(42);
		lv3Shots.add(46);
		lv3Shots.add(49);
		lv3Shots.add(52);
		lv3Shots.add(56);
		lv3Shots.add(59);
		lv3Shots.add(62);
		lv3Shots.add(66);
		lv3Shots.add(69);
		lv3Shots.add(73);
		lv3Shots.add(76);
		lv3Shots.add(79);
	}

	/**
	 * 
	 * @return String array holding valid postions of ships
	 * 
	 *         This method goes through the AI ships list and deploys them to
	 *         the array, after valid postion is set the ship is set to deployed
	 */
	public String[] setAIShipGrid() {
		for (Ship i : ships) {
			if (i.getDeployed() == false) {// not deployed

				setShip(i);
			}

		}

		return SSA;
	}

	private void setShip(Ship ship) {
		Random rand = new Random();
		randomPos = randInt(10, 80);
		int col = randomPos % GRID_SIZE;
		switch (ship.getName()) {
		case ("Aircraft Carrier"): {
			isVertical = false;
			if (isVertical == true) { // places the ship vertically
				if (vertValid(44, randomPos, 5) == true) {
					intiDSA(randomPos, "A", 5, isVertical); // if valid index
															// paints to array
					ship.setDeployed(true); // sets ship to deployed so for loop
											// can continue
				} else {
					while (ship.getDeployed() == false) { // if first index was
															// already taken
															// keep trying until
															// vaild
						if (!vertValid(44, randomPos, 5)) {
							randomPos = rand.nextInt(71) + 10;
						} else {
							intiDSA(randomPos, "A", 5, isVertical);
							ship.setDeployed(true);
							break;
						}
					}
				}
			} else { // same logic is taken place, but if the ship is placed
						// horizontally

				if (horzValid(col, 5, randomPos) == true) {
					intiDSA(randomPos, "A", 5, isVertical);
					ship.setDeployed(true);
				} else {
					while (ship.getDeployed() == false) {
						if (!horzValid(col, 5, randomPos)) {
							randomPos = rand.nextInt(71) + 10;
							col = randomPos % GRID_SIZE;
						} else {
							intiDSA(randomPos, "A", 5, isVertical);
							ship.setDeployed(true);
							break;
						}
					}
				}

			}
			break;
		}
		case ("Battleship"): {
			isVertical = true;
			if (isVertical == true) {
				if (vertValid(53, randomPos, 4) == true) {
					intiDSA(randomPos, "B", 4, isVertical);
					ship.setDeployed(true);
				} else {
					while (ship.getDeployed() == false) {
						if (!vertValid(53, randomPos, 4)) {
							randomPos = rand.nextInt(71) + 10;
							col = randomPos % GRID_SIZE;
						} else {
							intiDSA(randomPos, "B", 4, isVertical);
							ship.setDeployed(true);
							break;
						}
					}
				}
			} else {
				if (horzValid(col, 4, randomPos) == true) {
					intiDSA(randomPos, "B", 4, isVertical);
					ship.setDeployed(true);
				} else {
					if (horzValid(col, 4, randomPos) == true) {
						intiDSA(randomPos, "B", 4, isVertical);
						ship.setDeployed(true);
					} else {
						while (ship.getDeployed() == false) {
							if (!horzValid(col, 4, randomPos)) {
								randomPos = rand.nextInt(71) + 10;
								col = randomPos % GRID_SIZE;
							} else {
								intiDSA(randomPos, "B", 4, isVertical);
								ship.setDeployed(true);
								break;
							}
						}
					}
				}
			}
			break;
		}
		case ("Submarine"): {// 62,3
			isVertical = true;
			if (isVertical == true) {
				if (vertValid(62, randomPos, 3) == true) {
					intiDSA(randomPos, "S", 3, isVertical);
					ship.setDeployed(true);
				} else {
					while (ship.getDeployed() == false) {
						if (!vertValid(62, randomPos, 3)) {
							randomPos = rand.nextInt(71) + 10;
						} else {
							intiDSA(randomPos, "S", 3, isVertical);
							ship.setDeployed(true);
							break;
						}
					}
				}
			} else {
				if (horzValid(col, 3, randomPos) == true) {
					intiDSA(col, "S", 3, isVertical);
					ship.setDeployed(true);
				} else {
					if (horzValid(col, 3, randomPos) == true) {
						intiDSA(randomPos, "S", 3, isVertical);
					} else {
						while (ship.getDeployed() == false) {
							if (!horzValid(col, 3, randomPos)) {
								randomPos = rand.nextInt(71) + 10;
								col = randomPos % GRID_SIZE;
							} else {
								intiDSA(randomPos, "S", 3, isVertical);
								ship.setDeployed(true);
								break;
							}
						}
					}
				}
			}
			break;
		}

		case ("Destroyer"): {// 62,3
			isVertical = true;
			if (isVertical == true) {
				if (vertValid(62, randomPos, 3) == true) {
					intiDSA(randomPos, "D", 3, isVertical);
					ship.setDeployed(true);
				} else {
					// tryNew
					while (ship.getDeployed() == false) {
						if (!vertValid(62, randomPos, 3)) {
							randomPos = rand.nextInt(71) + 10;
						} else {
							intiDSA(randomPos, "D", 3, isVertical);
							ship.setDeployed(true);
							break;
						}
					}
				}
			} else {
				if (horzValid(col, 3, randomPos) == true) {
					intiDSA(col, "D", 3, isVertical);
					ship.setDeployed(true);
				} else {
					if (horzValid(col, 5, randomPos) == true) {
						intiDSA(randomPos, "D", 3, isVertical);
					} else {
						while (ship.getDeployed() == false) {
							if (!horzValid(col, 3, randomPos)) {
								randomPos = rand.nextInt(71) + 10;
								col = randomPos % GRID_SIZE;
							} else {
								intiDSA(randomPos, "D", 3, isVertical);
								ship.setDeployed(true);
								break;
							}
						}
					}
				}
			}
			break;
		}
		}
	}

	/**
	 * 
	 * @param randomPos
	 * @param string
	 * @param shipSize
	 * @param isVertical
	 * 
	 *            after the checking is done for a vaild postion this sets the
	 *            vaild indexs for the ship size
	 */
	private void intiDSA(int randomPos, String string, int shipSize,
			boolean isVertical) {
		if (isVertical == true) {
			for (int i = 0; i < shipSize; i++) {
				SSA[randomPos + (i * 9)] = string;
			}
		} else {
			for (int i = 0; i < shipSize; i++) {
				SSA[randomPos + i] = string;
			}
		}
	}

	/**
	 * 
	 * @param col
	 * @param shipSize
	 * @param randomPos
	 * @return checks to see if a horizontal placement can be made
	 */
	private boolean horzValid(int col, int shipSize, int randomPos) {
		boolean empty = false;
		if ((col % 9) < shipSize && (col % 9) != 0) {
			for (int i = 0; i < shipSize; i++) {
				if (hasChar(randomPos + i) == true) {
					return false;
				} else {
					empty = true;
				}
			}
		}
		return empty;
	}

	/**
	 * 
	 * @param index
	 * @param randomPos
	 * @param size
	 * @return
	 * 
	 *         checks to see if a vertical placement can be made // index is
	 *         greatest highest it can be without going off grid
	 */
	private boolean vertValid(int index, Integer randomPos, Integer size) {
		boolean empty = false;
		if (randomPos < index && (randomPos % 9) != 0) {
			for (int i = 0; i < size; i++) {
				if (hasChar(randomPos + (i * 9)) == true) {
					return false;
				} else {
					empty = true;
				}
			}
		}
		return empty;
	}

	/**
	 * 
	 * @param randomPos
	 * @return boolean returning if index has a char of not
	 */
	private boolean hasChar(int randomPos) {
		if (SSA[randomPos].contains("B") || SSA[randomPos].contains("S")
				|| SSA[randomPos].contains("D") || SSA[randomPos].contains("A")) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * 
	 * @return
	 * 
	 *         checks for if all ships have been deployed
	 */
	public boolean playerDeployed() {
		if ((d.getDeployed() == true) && (s.getDeployed() == true)
				&& (b.getDeployed() == true) && (a.getDeployed() == true)) {
			return true;
		} else {
			return false;
		}
	}

	public static int randInt(int min, int max) {
		Random rand = new Random();
		int randomNum = rand.nextInt((max - min) + 1) + min;
		return randomNum;
	}

	public int fire(Game game, int difficulty) {
		int AIShot = 0;
		if (difficulty == 3) {
			AIShot = level3(game);
		} else if (difficulty == 2) { // return -1 to deam fire a level one shot
			AIShot = level2(game, false);
		} else {
			AIShot = level1(game);
		}
		addHits(game, AIShot);
		compShotsFired.add(AIShot);
		return AIShot;

	}

	private int level3(Game game) {
		int temp;
		int index;
		int AIShot = level2(game, true);
		System.out.println("AIshot lv2 from lv 3: " + AIShot);
		if (AIShot == 0) {
			Collections.shuffle(lv3Shots);
			temp = lv3Shots.get(0);
			index = 0;
			if (compShotsFired.contains(temp)) {
				temp = lv3Shots.get(1);
				index = 1;
				if (compShotsFired.contains(temp)) {
					temp = lv3Shots.get(2);
					index = 2;
					if (compShotsFired.contains(temp)) {
						temp = lv3Shots.get(3);
						index = 3;
						if (compShotsFired.contains(temp)) {
							temp = lv3Shots.get(4);
							index = 4;
						}
					}
				}
				AIShot = temp;
				lv3Shots.remove(index);
			} else {
				AIShot = lv3Shots.get(0);
				lv3Shots.remove(0);
			}
		}
		
		System.out.println("aishot froml3 :" + AIShot);
		return AIShot;
	}

	private int level2(Game game, boolean fromLevel3) {
		System.out.println("lv 2 p1 turn start*******: " + game.p1Turn);
		int neighborShot = 0;
		if (game.p1Turn == true) {
			if (game.p2.dHits.size() == 1) {
				System.out.println("game.p2.dHits: " + game.p2.dHits);
				neighborShot = tryNeighbor(game.p2.dHits.get(0));
			} else if (game.p2.dHits.size() == 2) {
				System.out.println("game.p2.dHits: " + game.p2.dHits);
				setOrent(game.p2.dHits, "D");
				if (dIsVert == true) {
					neighborShot = game.p2.dHits.get(0) + 9;
					if (neighborShot > 80
							|| compShotsFired.contains(neighborShot)) {
						neighborShot = game.p2.dHits.get(0) - 9;
						if (neighborShot < 8
								|| compShotsFired.contains(neighborShot)) {
							neighborShot = game.p2.dHits.get(0) + 18;
							if (neighborShot > 80
									|| compShotsFired.contains(neighborShot)) {
								neighborShot = game.p2.dHits.get(0) - 18;
								if (neighborShot < 8
										|| compShotsFired
												.contains(neighborShot)) {
									neighborShot = game.p2.dHits.get(0) + 27;
									if (neighborShot > 80
											|| compShotsFired
													.contains(neighborShot)) {
										neighborShot = game.p2.dHits.get(0) - 27;

									}
								}
							}
						}
					}

				}

			} else if (game.p2.sHits.size() == 1) {
				System.out.println("game.p2.sHits: " + game.p2.sHits);
				neighborShot = tryNeighbor(game.p2.sHits.get(0));

			} else if (game.p2.sHits.size() == 2) {
				System.out.println("sHits: " + game.p2.sHits);
				setOrent(game.p2.sHits, "S");
				if (sIsVert = true) {
					neighborShot = game.p2.sHits.get(0) + 9;
					if (neighborShot > 80
							|| compShotsFired.contains(neighborShot)) {
						neighborShot = game.p2.sHits.get(0) - 9;
						if (neighborShot < 8
								|| compShotsFired.contains(neighborShot)) {
							neighborShot = game.p2.sHits.get(0) + 18;
							if (neighborShot > 80
									|| compShotsFired.contains(neighborShot)) {
								neighborShot = game.p2.sHits.get(0) - 18;
								if (neighborShot < 8
										|| compShotsFired
												.contains(neighborShot)) {
									neighborShot = game.p2.sHits.get(0) + 27;
									if (neighborShot > 80
											|| compShotsFired
													.contains(neighborShot)) {
										neighborShot = game.p2.sHits.get(0) - 27;

									}
								}
							}
						}
					}
				}

			} else if (game.p2.aHits.size() == 1) {
				System.out.println("game.p2.aHits: " + game.p2.aHits);
				neighborShot = tryNeighbor(game.p2.aHits.get(0));

			} else if (game.p2.aHits.size() == 2) {
				System.out.println("game.p2.aHits: " + game.p2.aHits);
				setOrent(game.p2.aHits, "A");
				if (aIsVert == false) {
					neighborShot = game.p2.aHits.get(0) + 1;
					if (neighborShot % 9 == 0
							|| compShotsFired.contains(neighborShot)) {
						neighborShot = game.p2.aHits.get(0) - 1;
						if (neighborShot % 9 == 0
								|| compShotsFired.contains(neighborShot)) {
							neighborShot = game.p2.aHits.get(0) + 2;
							if (neighborShot % 9 == 0
									|| compShotsFired.contains(neighborShot)) {
								neighborShot = game.p2.aHits.get(0) - 2;
								if (neighborShot % 9 == 0
										|| compShotsFired
												.contains(neighborShot)) {
									neighborShot = game.p2.aHits.get(0) + 3;
									if (neighborShot % 9 == 0
											|| compShotsFired
													.contains(neighborShot)) {
										neighborShot = game.p2.aHits.get(0) - 3;

									}
								}
							}
						}
					}
				}

			} else if (game.p2.aHits.size() == 3) {
				System.out.println("game.p2.aHits: " + game.p2.aHits);
				if (aIsVert == false) {
					neighborShot = game.p2.aHits.get(0) + 1;
					if (neighborShot % 9 == 0
							|| compShotsFired.contains(neighborShot)
							|| neighborShot > 80) {
						neighborShot = game.p2.aHits.get(0) - 1;
						if (neighborShot % 9 == 0
								|| compShotsFired.contains(neighborShot)) {
							neighborShot = game.p2.aHits.get(0) + 2;
							if (neighborShot % 9 == 0
									|| compShotsFired.contains(neighborShot)
									|| neighborShot > 80) {
								neighborShot = game.p2.aHits.get(0) - 2;
								if (neighborShot % 9 == 0
										|| compShotsFired
												.contains(neighborShot)) {
									neighborShot = game.p2.aHits.get(0) + 3;
									if (neighborShot % 9 == 0
											|| compShotsFired
													.contains(neighborShot)
											|| neighborShot > 80) {
										neighborShot = game.p2.aHits.get(0) - 3;

									}
								}
							}
						}
					}
				}

			} else if (game.p2.aHits.size() == 4) {
				System.out.println("game.p2.aHits: " + game.p2.aHits);
				if (aIsVert == false) {
					neighborShot = game.p2.aHits.get(0) + 1;
					if (neighborShot % 9 == 0
							|| compShotsFired.contains(neighborShot)
							|| neighborShot > 80) {
						neighborShot = game.p2.aHits.get(0) - 1;
						if (neighborShot % 9 == 0
								|| compShotsFired.contains(neighborShot)) {
							neighborShot = game.p2.aHits.get(0) + 2;
							if (neighborShot % 9 == 0
									|| compShotsFired.contains(neighborShot)
									|| neighborShot > 80) {
								neighborShot = game.p2.aHits.get(0) - 2;
								if (neighborShot % 9 == 0
										|| compShotsFired
												.contains(neighborShot)) {
									neighborShot = game.p2.aHits.get(0) + 3;
									if (neighborShot % 9 == 0
											|| compShotsFired
													.contains(neighborShot)
											|| neighborShot > 80) {
										neighborShot = game.p2.aHits.get(0) - 3;
										if (neighborShot % 9 == 0
												|| compShotsFired
														.contains(neighborShot)
												|| neighborShot <= 8) {
											neighborShot = game.p2.aHits.get(0) + 4;
											if (neighborShot % 9 == 0
													|| compShotsFired
															.contains(neighborShot)
													|| neighborShot > 80) {
												neighborShot = game.p2.aHits
														.get(0) - 4;
											}
										}
									}
								}
							}
						}
					}
				}

			} else if (game.p2.bHits.size() == 1) {
				System.out.println("game.p2.bHits: " + game.p2.bHits);
				neighborShot = tryNeighbor(game.p2.bHits.get(0));

			} else if (game.p2.bHits.size() == 2) {
				System.out.println("game.p2.bHits: " + game.p2.bHits);
				setOrent(game.p2.bHits, "B");
				if (bIsVert = true) {
					neighborShot = game.p2.bHits.get(0) + 9;
					if (neighborShot > 80
							|| compShotsFired.contains(neighborShot)) {
						neighborShot = game.p2.bHits.get(0) - 9;
						if (neighborShot < 8
								|| compShotsFired.contains(neighborShot)) {
							neighborShot = game.p2.bHits.get(0) + 18;
							if (neighborShot > 80
									|| compShotsFired.contains(neighborShot)) {
								neighborShot = game.p2.bHits.get(0) - 18;
								if (neighborShot < 8
										|| compShotsFired
												.contains(neighborShot)) {
									neighborShot = game.p2.bHits.get(0) + 27;
									if (neighborShot > 80
											|| compShotsFired
													.contains(neighborShot)) {
										neighborShot = game.p2.bHits.get(0) - 27;

									}
								}
							}
						}
					}
				}

			} else if (game.p2.bHits.size() == 3) {
				System.out.println("game.p2.bHits: " + game.p2.bHits);
				System.out.println("forth battle ship shot");
				if (bIsVert = true) {
					neighborShot = game.p2.bHits.get(0) + 9;
					if (neighborShot > 80
							|| compShotsFired.contains(neighborShot)) {
						neighborShot = game.p2.bHits.get(0) - 9;
						if (neighborShot < 8
								|| compShotsFired.contains(neighborShot)) {
							neighborShot = game.p2.bHits.get(0) + 18;
							if (neighborShot > 80
									|| compShotsFired.contains(neighborShot)) {
								neighborShot = game.p2.bHits.get(0) - 18;
								if (neighborShot < 8
										|| compShotsFired
												.contains(neighborShot)) {
									neighborShot = game.p2.bHits.get(0) + 27;
									if (neighborShot > 80
											|| compShotsFired
													.contains(neighborShot)) {
										neighborShot = game.p2.bHits.get(0) - 27;

									}
								}
							}
						}
					}
				}

			} else if (fromLevel3 == false) {
				System.out
						.println("lv 2 p1 turn finish*******: " + game.p1Turn);
				return level1(game);
			}

		} else if (game.p1Turn == false) {
			if (game.p1.dHits.size() == 1) {
				System.out.println("game.p1.dHits: " + game.p1.dHits);
				neighborShot = tryNeighbor(game.p1.dHits.get(0));
			} else if (game.p1.dHits.size() == 2) {
				System.out.println("game.p1.dHits: " + game.p1.dHits);
				setOrent(game.p1.dHits, "D");
				if (dIsVert == true) {
					neighborShot = game.p1.dHits.get(0) + 9;
					if (neighborShot > 80
							|| compShotsFired.contains(neighborShot)) {
						neighborShot = game.p1.dHits.get(0) - 9;
						if (neighborShot < 8
								|| compShotsFired.contains(neighborShot)) {
							neighborShot = game.p1.dHits.get(0) + 18;
							if (neighborShot > 80
									|| compShotsFired.contains(neighborShot)) {
								neighborShot = game.p1.dHits.get(0) - 18;
								if (neighborShot < 8
										|| compShotsFired
												.contains(neighborShot)) {
									neighborShot = game.p1.dHits.get(0) + 27;
									if (neighborShot > 80
											|| compShotsFired
													.contains(neighborShot)) {
										neighborShot = game.p1.dHits.get(0) - 27;

									}
								}
							}
						}
					}
				}

			} else if (game.p1.sHits.size() == 1) {
				System.out.println("game.p1.sHits: " + game.p1.sHits);
				neighborShot = tryNeighbor(game.p1.sHits.get(0));

			} else if (game.p1.sHits.size() == 2) {
				System.out.println("game.p1.sHits: " + game.p1.sHits);
				setOrent(game.p1.sHits, "S");
				if (sIsVert = true) {
					neighborShot = game.p1.sHits.get(0) + 9;
					if (neighborShot > 80
							|| compShotsFired.contains(neighborShot)) {
						neighborShot = game.p1.sHits.get(0) - 9;
						if (neighborShot < 8
								|| compShotsFired.contains(neighborShot)) {
							neighborShot = game.p1.sHits.get(0) + 18;
							if (neighborShot > 80
									|| compShotsFired.contains(neighborShot)) {
								neighborShot = game.p1.sHits.get(0) - 18;
								if (neighborShot < 8
										|| compShotsFired
												.contains(neighborShot)) {
									neighborShot = game.p1.sHits.get(0) + 27;
									if (neighborShot > 80
											|| compShotsFired
													.contains(neighborShot)) {
										neighborShot = game.p1.sHits.get(0) - 27;

									}
								}
							}
						}
					}
				}

			} else if (game.p1.aHits.size() == 1) {
				System.out.println("game.p1.aHits: " + game.p1.aHits);
				neighborShot = tryNeighbor(game.p1.aHits.get(0));

			} else if (game.p1.aHits.size() == 2) {
				System.out.println("game.p1.aHits: " + game.p1.aHits);
				setOrent(game.p1.aHits, "A");
				if (aIsVert == false) {
					neighborShot = game.p1.aHits.get(0) + 1;
					if (neighborShot % 9 == 0
							|| compShotsFired.contains(neighborShot)) {
						neighborShot = game.p1.aHits.get(0) - 1;
						if (neighborShot % 9 == 0
								|| compShotsFired.contains(neighborShot)) {
							neighborShot = game.p1.aHits.get(0) + 2;
							if (neighborShot % 9 == 0
									|| compShotsFired.contains(neighborShot)) {
								neighborShot = game.p1.aHits.get(0) - 2;
								if (neighborShot % 9 == 0
										|| compShotsFired
												.contains(neighborShot)) {
									neighborShot = game.p1.aHits.get(0) + 3;
									if (neighborShot % 9 == 0
											|| compShotsFired
													.contains(neighborShot)) {
										neighborShot = game.p1.aHits.get(0) - 3;

									}
								}
							}
						}
					}
				}

			} else if (game.p1.aHits.size() == 3) {
				System.out.println("game.p1.aHits: " + game.p1.aHits);
				if (aIsVert == false) {
					neighborShot = game.p1.aHits.get(0) + 1;
					if (neighborShot % 9 == 0
							|| compShotsFired.contains(neighborShot)
							|| neighborShot > 80) {
						neighborShot = game.p1.aHits.get(0) - 1;
						if (neighborShot % 9 == 0
								|| compShotsFired.contains(neighborShot)) {
							neighborShot = game.p1.aHits.get(0) + 2;
							if (neighborShot % 9 == 0
									|| compShotsFired.contains(neighborShot)
									|| neighborShot > 80) {
								neighborShot = game.p1.aHits.get(0) - 2;
								if (neighborShot % 9 == 0
										|| compShotsFired
												.contains(neighborShot)) {
									neighborShot = game.p1.aHits.get(0) + 3;
									if (neighborShot % 9 == 0
											|| compShotsFired
													.contains(neighborShot)
											|| neighborShot > 80) {
										neighborShot = game.p1.aHits.get(0) - 3;

									}
								}
							}
						}
					}
				}

			} else if (game.p1.aHits.size() == 4) {
				System.out.println("game.p1.aHits: " + game.p1.aHits);
				if (aIsVert == false) {
					neighborShot = game.p1.aHits.get(0) + 1;
					if (neighborShot % 9 == 0
							|| compShotsFired.contains(neighborShot)
							|| neighborShot > 80) {
						neighborShot = game.p1.aHits.get(0) - 1;
						if (neighborShot % 9 == 0
								|| compShotsFired.contains(neighborShot)) {
							neighborShot = game.p1.aHits.get(0) + 2;
							if (neighborShot % 9 == 0
									|| compShotsFired.contains(neighborShot)
									|| neighborShot > 80) {
								neighborShot = game.p1.aHits.get(0) - 2;
								if (neighborShot % 9 == 0
										|| compShotsFired
												.contains(neighborShot)) {
									neighborShot = game.p1.aHits.get(0) + 3;
									if (neighborShot % 9 == 0
											|| compShotsFired
													.contains(neighborShot)
											|| neighborShot > 80) {
										neighborShot = game.p1.aHits.get(0) - 3;
										if (neighborShot % 9 == 0
												|| compShotsFired
														.contains(neighborShot)
												|| neighborShot <= 8) {
											neighborShot = game.p1.aHits.get(0) + 4;
											if (neighborShot % 9 == 0
													|| compShotsFired
															.contains(neighborShot)
													|| neighborShot > 80) {
												neighborShot = game.p1.aHits
														.get(0) - 4;
											}
										}
									}
								}
							}
						}
					}
				}

			} else if (game.p1.bHits.size() == 1) {
				System.out.println("game.p1.bHits: " + game.p1.bHits);
				neighborShot = tryNeighbor(game.p1.bHits.get(0));

			} else if (game.p1.bHits.size() == 2) {
				System.out.println("game.p1.bHits: " + game.p1.bHits);
				setOrent(game.p1.bHits, "B");
				if (bIsVert = true) {
					neighborShot = game.p1.bHits.get(0) + 9;
					if (neighborShot > 80
							|| compShotsFired.contains(neighborShot)) {
						neighborShot = game.p1.bHits.get(0) - 9;
						if (neighborShot < 8
								|| compShotsFired.contains(neighborShot)) {
							neighborShot = game.p1.bHits.get(0) + 18;
							if (neighborShot > 80
									|| compShotsFired.contains(neighborShot)) {
								neighborShot = game.p1.bHits.get(0) - 18;
								if (neighborShot < 8
										|| compShotsFired
												.contains(neighborShot)) {
									neighborShot = game.p1.bHits.get(0) + 27;
									if (neighborShot > 80
											|| compShotsFired
													.contains(neighborShot)) {
										neighborShot = game.p1.bHits.get(0) - 27;

									}
								}
							}
						}
					}
				}

			} else if (game.p1.bHits.size() == 3) {
				System.out.println("game.p1.bHits: " + game.p1.bHits);
				System.out.println("forth battle ship shot");
				if (bIsVert = true) {
					neighborShot = game.p1.bHits.get(0) + 9;
					if (neighborShot > 80
							|| compShotsFired.contains(neighborShot)) {
						neighborShot = game.p1.bHits.get(0) - 9;
						if (neighborShot < 8
								|| compShotsFired.contains(neighborShot)) {
							neighborShot = game.p1.bHits.get(0) + 18;
							if (neighborShot > 80
									|| compShotsFired.contains(neighborShot)) {
								neighborShot = game.p1.bHits.get(0) - 18;
								if (neighborShot < 8
										|| compShotsFired
												.contains(neighborShot)) {
									neighborShot = game.p1.bHits.get(0) + 27;
									if (neighborShot > 80
											|| compShotsFired
													.contains(neighborShot)) {
										neighborShot = game.p1.bHits.get(0) - 27;

									}
								}
							}
						}
					}
				}

			} else if (fromLevel3 == false) {
				System.out
						.println("lv 2 p1 turn finish*******: " + game.p1Turn);
				return level1(game);
			}
		}
		System.out.println("lv 2 p1 turn finish*******: " + game.p1Turn);
		return neighborShot;
	}

	private void setOrent(ArrayList<Integer> hits, String ship) {
		System.out.println("getting orentation");
		if ("D".equals(ship)) {
			if (hits.get(0) + 1 == hits.get(1)
					|| hits.get(0) - 1 == hits.get(1)) {
				dIsVert = false;
			} else {
				dIsVert = true;
			}
			System.out.println("d vert:" + dIsVert);
		} else if ("S".equals(ship)) {
			if (hits.get(0) + 1 == hits.get(1)
					|| hits.get(0) - 1 == hits.get(1)) {
				sIsVert = false;
			} else {
				sIsVert = true;
			}
			System.out.println("s isvert:" + sIsVert);
		} else if ("B".equals(ship)) {
			if (hits.get(0) + 1 == hits.get(1)
					|| hits.get(0) - 1 == hits.get(1)) {
				bIsVert = false;
			} else {
				bIsVert = true;
			}
			System.out.println("b isvert:" + bIsVert);
		} else if ("A".equals(ship)) {
			if (hits.get(0) + 1 == hits.get(1)
					|| hits.get(0) - 1 == hits.get(1)) {
				aIsVert = false;
			} else {
				aIsVert = true;
			}
			System.out.println("a isvert:" + aIsVert);
		}
	}

	/**
	 * 
	 * @param hitShot
	 *            shot that a ship has been hit with
	 * @return
	 */

	private int tryNeighbor(Integer hitShot) { // R: + 1, L:- 1, D :+ 9, U: - 9
		int tryShot = 0;
		if (hitShot == 10) {
			System.out.println("top left");
			tryShot = hitShot + 1;
			if (compShotsFired.contains(tryShot)) {
				tryShot = hitShot + 9;
			}
		} else if (hitShot == 17) {
			System.out.println("top right");
			tryShot = hitShot - 1;
			if (compShotsFired.contains(tryShot)) {
				tryShot = hitShot + 9;
			}
		} else if (hitShot == 73) {
			System.out.println("bottom left");
			tryShot = hitShot + 1;
			if (compShotsFired.contains(tryShot)) {
				tryShot = hitShot - 9;
			}
		} else if (hitShot == 80) {
			System.out.println("top right");
			tryShot = hitShot - 1;
			if (compShotsFired.contains(tryShot)) {
				tryShot = hitShot - 9;
			}
		} else if (hitShot < 17) {
			System.out.println("top most");
			tryShot = hitShot + 1;
			if (compShotsFired.contains(tryShot)) {
				tryShot = hitShot + 9;
				if (compShotsFired.contains(tryShot)) {
					tryShot = hitShot - 1;
				}
			}
		} else if (hitShot % 9 == 1) {
			System.out.println("left most");
			tryShot = hitShot - 9;
			if (compShotsFired.contains(tryShot)) {
				tryShot = hitShot + 1;
				if (compShotsFired.contains(tryShot)) {
					tryShot = hitShot + 9;
				}
			}
		} else if (hitShot % 9 == 8) {
			System.out.println("left most");
			tryShot = hitShot - 9;
			if (compShotsFired.contains(tryShot)) {
				tryShot = hitShot + 1;
				if (compShotsFired.contains(tryShot)) {
					tryShot = hitShot + 9;
				}
			}
		} else if (hitShot > 73 && hitShot < 80) {
			System.out.println("bottom most");
			tryShot = hitShot + 1;
			if (compShotsFired.contains(tryShot)) {
				tryShot = hitShot - 1;
				if (compShotsFired.contains(tryShot)) {
					tryShot = hitShot - 9;
				}
			}
		} else {
			System.out.println("center all");
			tryShot = hitShot + 1;
			if (compShotsFired.contains(tryShot)) {
				tryShot = hitShot + 9;
				if (compShotsFired.contains(tryShot)) {
					tryShot = hitShot - 1;
					if (compShotsFired.contains(tryShot)) {
						tryShot = hitShot - 9;
					}
				}
			}
		}
		System.out.println("hitShot :" + hitShot + " trySHot:" + tryShot);
		return tryShot;
	}

	private int level1(Game game) {
		System.out.println("lv 1 p1 turn: " + game.p1Turn);
		rG = new Random();
		int AIShot = rG.nextInt(81);
		while (compShotsFired.contains(AIShot) || (AIShot % 9 == 0)
				|| AIShot / 9 == 0) {
			AIShot = rG.nextInt(81);
		}
		return AIShot;
	}

	private void addHits(Game game, int AIShot) {
		if (game.p1Turn == true) {
			if (game.p2DisplayGrid[AIShot].getText().equals("D")) {
				game.p2.dHits.add(AIShot);
			} else if (game.p2DisplayGrid[AIShot].getText().equals("S")) {
				game.p2.sHits.add(AIShot);

			} else if (game.p2DisplayGrid[AIShot].getText().equals("B")) {
				game.p2.bHits.add(AIShot);

			} else if (game.p2DisplayGrid[AIShot].getText().equals("A")) {
				game.p2.aHits.add(AIShot);
			}
		} else if (game.p1Turn == false) {
			if (game.p1DisplayGrid[AIShot].getText().equals("D")) {
				game.p1.dHits.add(AIShot);
			} else if (game.p1DisplayGrid[AIShot].getText().equals("S")) {
				game.p1.sHits.add(AIShot);

			} else if (game.p1DisplayGrid[AIShot].getText().equals("B")) {
				game.p1.bHits.add(AIShot);

			} else if (game.p1DisplayGrid[AIShot].getText().equals("A")) {
				game.p1.aHits.add(AIShot);
			}
		}
	}
}
