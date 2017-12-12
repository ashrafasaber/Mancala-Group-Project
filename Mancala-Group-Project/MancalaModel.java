

import java.util.ArrayList;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


/**
 * This class acts as a Model in the MVC Structure
 * @author SRS 
 *
 */
public class MancalaModel {
	// An arraylist to hold all changelisteners
	private ArrayList<ChangeListener> changeListeners;
	// A GamePlayer type variable to highlight the current turn (current player)
	private GamePlayer currentTurn;
	// A GameStatus variable to highlight whether the game has Started, is in
	// process or has ended
	private GameStatus currentGameStatus;
	// The total number of Pits on the Mancala Board
	public final int numberOfPits = 14;
	// The total number of Stones per pit as well as the savedPits (which hold the
	// status of previous pits)
	private int[] pits, savedPits;
	boolean freeTurn;

	// The ScorePit of Player A
	public static final int playerAScorePit = 6;
	// The ScorePit of Player B
	public static final int playerBScorePit = 13;
	// The total Allowable Undos per player
	private static final int allowableUndos = 3;
	// The counter for each player's undos
	private int playerA_Undos = 0, playerB_Undos = 0;
	// Boolean variable to highlight whether or not the Undos have expired
	private boolean undosExpired;

	private boolean stonesStolen ;
	// MancalaModel Constructor
	/**
	 * Constructor for the Model
	 */
	MancalaModel() {
		currentTurn = GamePlayer.PLAYER_A; // setting the GamePlayer to Player A
		currentGameStatus = GameStatus.GAME_STARTED; // setting the GameStatus to GameStarted
		// finalStone=false; // Setting the finalStone to False since the game is in the
		// beginning
		undosExpired = false; // Setting the Undos expired to False since the game is in the beginning
		playerA_Undos = 0; // The Undos used by each player is 0 at the very beginning of the game
		playerB_Undos = 0; // The Undos used by each player is 0 at the very beginning of the game
		pits = new int[numberOfPits]; // Setting the array size of pits to the numberOfPits on the board
		changeListeners = new ArrayList<ChangeListener>(); // initializing the ArrayList of change listeners
		freeTurn = false;

	}

	/**
	 * Returns the GamePlayer of the CurrentTurn
	 * 
	 * @return currentTurn: current GamePlayer
	 */
	public GamePlayer getPlayerTurn() {
		return currentTurn;
	}

	/**
	 * This function changes the current turn and switches the player
	 */
	public void changeTurn() {
		if (currentTurn == GamePlayer.PLAYER_B) {
			currentTurn = GamePlayer.PLAYER_A;
		}
		else if (currentTurn == GamePlayer.PLAYER_A) {
			currentTurn = GamePlayer.PLAYER_B;
		}
	}

	/**
	 * This function takes the pitNumber and returns the player that owns it
	 * 
	 * @param pitNumber
	 * @return the player name
	 */
	public GamePlayer pitOwner(int pitNumber) {
		GamePlayer owner = null;
		if (pitNumber <= playerAScorePit && pitNumber >= 0) {
			owner = GamePlayer.PLAYER_A;
		} else if (pitNumber > playerAScorePit && pitNumber <= playerBScorePit) {
			owner = GamePlayer.PLAYER_B;
		}
		return owner;
	}

	/*
	 * Describes how the pits are numbered
	 * Pits Numbering:
	 *     12  11  10   9   8   7 
	 *  13                         6
	 *      0   1   2   3   4   5 
	 */
	/**
	 * This function return the opposite pit
	 */
	public int mirrorPit(int pitNumber) {
		int mirroredPit = 0;

		if (pitNumber <= 12 && pitNumber >= 0) {
			mirroredPit = 12 - pitNumber;
		} else {
			mirroredPit=pitNumber-12;
		}

		return mirroredPit;
	}

	/**
	 * Checking if the pit is Accessible to Play-> The pit is playable
	 * 
	 * @param pitNumber
	 * @return
	 */
	public boolean pitAccessible(int pitNumber) {
		boolean accessible = true;
		if (pits[pitNumber] == 0) {
			accessible = false;
		} // Checking if pit is empty
		if (currentGameStatus == GameStatus.GAME_ENDED) {
			accessible = false;
		} // Checking it the game has ended
		if (pitOwner(pitNumber) != currentTurn) {
			accessible = false;
		} // Checking if it is not the current player's pit
		if (pitNumber == playerAScorePit || pitNumber == playerBScorePit) {
			accessible = false;
		} // Checking if it is a ScorePit
		if (pitNumber < 0) {
			accessible = false;
		} // checks if below limits
		if (pitNumber > numberOfPits) {
			accessible = false;
		} // checks if above limit
		return accessible;
	}

	/**
	 * This Function attahes changeListeners to the changeListener array
	 * 
	 * @param l
	 */
	public void attach(ChangeListener l) {
		changeListeners.add(l);
	}

	/**
	 * This function update changeListeners
	 */
	public void updateListeners() {
		for (ChangeListener l : changeListeners) {
			l.stateChanged(new ChangeEvent(this));
		}
	}

	/**
	 * This function sets the number of stones on the MancalaBoard
	 * 
	 * @param numberOfStones
	 */
	public void setStones(int numberOfStones) {
		for (int i = 0; i < pits.length; i++) {
			if (isScorePit(i) == false) {
				pits[i] = numberOfStones;
			} else{pits[i]=0;}
		}
		this.updateListeners();
	}

	/**
	 * This function sets the game status
	 */
	public void setGameStatus(String gameStatus) {
		// GAME_STARTED,GAME_INPROCESS, GAME_ENDED;
		if (gameStatus.equals("Game Started")) {
			currentGameStatus = GameStatus.GAME_STARTED;
		} else if (gameStatus.equals("Game in Process")) {
			currentGameStatus = GameStatus.GAME_INPROCESS;
		} else if (gameStatus.equals("Game Ended")) {
			currentGameStatus = GameStatus.GAME_ENDED;
		}
	}

	/**
	 * This function sets the number of stones at a specific pit
	 * 
	 * @param pitNumber
	 * @param numberOfStones
	 */
	public void setStonesAtPit(int pitNumber, int numberOfStones) {
		pits[pitNumber] = numberOfStones;
	}

	/**
	 * This function undos game play
	 */
	public void undoGamePlay() {
		boolean flag = false;

		if (undosExpired) {
			return;
		}
		if (currentTurn == GamePlayer.PLAYER_A) 
		{
			if (freeTurn && undosAllowed(GamePlayer.PLAYER_A)) {
				playerA_Undos++;
				flag = true;
			} else if (!freeTurn && undosAllowed(GamePlayer.PLAYER_B)) {
				playerB_Undos++;

				currentTurn = GamePlayer.PLAYER_B; 
				flag = true;
			}


		} else if (currentTurn == GamePlayer.PLAYER_B) 
		{
			if (freeTurn && undosAllowed(GamePlayer.PLAYER_A)) {
				playerB_Undos++;
				flag = true;
			} else if ((!freeTurn) && undosAllowed(GamePlayer.PLAYER_A)) {
				playerA_Undos++;


				currentTurn = GamePlayer.PLAYER_A; 
				flag = true;
			} 



		}

		if (flag) {
			undosExpired = true;
			this.lastSetup();
			this.updateListeners();

		}
	}


	/**
	 * Checks if undos are allowed
	 * 
	 * @param player
	 * @return
	 */
	public boolean undosAllowed(GamePlayer player) {
		boolean allowed = false;
		if (player == GamePlayer.PLAYER_A) {
			allowed =  playerA_Undos< allowableUndos ;
		} else if (player == GamePlayer.PLAYER_B) {
			allowed =  playerB_Undos< allowableUndos ;
		}
		return allowed;
	}

	/**
	 * Return Player Pits without The scorePit values
	 * 
	 * @param player
	 * @return
	 */
	public int totalPlayerPits(GamePlayer player) {

		int playerPits = 0;

		if (player == GamePlayer.PLAYER_A) {
			for (int i = 0; i <= playerAScorePit; i++) {
				playerPits += pits[i];
			}
		} else if (player == GamePlayer.PLAYER_B) {
			for (int i = 7; i <= playerBScorePit; i++) {
				playerPits += pits[i];
			}
		}

		return playerPits;
	}

	/**
	 * Checks if the player pits are empty and then returns a boolean (disregards
	 * mancala)
	 * 
	 * @return
	 */
	public boolean playerPitsEmptyCheck(GamePlayer player) {
		boolean empty = false;
		int playerPits = 0;
		playerPits = totalPlayerPits(player);
		empty = (playerPits == 0);

		return empty;
	}

	/**
	 * Prints a player Score; the stones in the Mancala
	 * 
	 * @param gamePlayer
	 * @return
	 */
	public int printPoints(GamePlayer gamePlayer) {
		int score = 0;
		if (gamePlayer == GamePlayer.PLAYER_A) {
			score = pits[playerAScorePit];
		}
		if (gamePlayer == GamePlayer.PLAYER_B) {
			score = pits[playerBScorePit];
		}
		return score;
	}

	/**
	 * Checks if the owner of pit is current player
	 * 
	 * @param pitNumber
	 * @return
	 */
	public boolean ownerIsCurrentPlayer(int pitNumber) {
		GamePlayer owner = pitOwner(pitNumber);
		boolean isCurrentPlayer = false;
		if (owner == getCurrentTurn()) {
			isCurrentPlayer = true;
		}
		return isCurrentPlayer;
	}

	/**
	 * Checks if 1 stone is left in the pit
	 * @param pitNumber
	 * @return
	 */
	public boolean oneStoneLeft(int pitNumber) {
		int stonesLeft = pits[pitNumber];
		boolean oneStoneLeft = false;
		if (stonesLeft == 1) {
			oneStoneLeft = true;
		}
		return oneStoneLeft;
	}

	/**
	 * Check if there are stones in the opposite pit
	 * @param pitNumber
	 * @return
	 */
	public boolean stonesInOppositePit(int pitNumber) {
		int oppositePit = mirrorPit(pitNumber);
		int stonesOpposite = pits[oppositePit];
		boolean stonesInOppPit = false;
		if (stonesOpposite > 0) {
			stonesInOppPit = true;
		}
		return stonesInOppPit;
	}

	/**
	 * Takes current pitNumber and indicates whether or not players should be
	 * changed
	 * 
	 * @param pitNumber
	 */
	public void checkFreeTurn(int pitNumber) {
		boolean endedInMancala = false, endedInEmptyPit = false, freeTurn = false;
		stonesStolen=false;
		endedInMancala = lgMancalaCheck(pitNumber);
		endedInEmptyPit = lgEmptyPitCheck(pitNumber);

		if (endedInMancala) {
			freeTurn = true;
		}

		if (endedInEmptyPit) {
			freeTurn = false;
			stealStones(pitNumber);
			stonesStolen=true;
			changeTurn();
		}
	}
	/**
	 * Checks the last stone played: whether it ended in a Mancala || if stones were stolen
	 * @param pitNumber
	 */
	public void checkLastGamePlay(int pitNumber){
		boolean flag=false ;
		flag=lgMancalaCheck(pitNumber);

		if(!flag || stonesStolen ){
			changeTurn();
		}
	}

	/**
	 * Get pit's owner mancala. Returns index of array of pits
	 */
	public int getPitOwnerMancala(int pitNumber) {
		GamePlayer owner = pitOwner(pitNumber);
		int mancalaIndex = 0;
		if (owner == GamePlayer.PLAYER_A) {
			mancalaIndex = playerAScorePit;
		} else if (owner == GamePlayer.PLAYER_B) {
			mancalaIndex = playerBScorePit;
		}
		return mancalaIndex;
	}

	/**
	 * Sets a pit value to zero->turns it to an empty pit
	 * 
	 * @param pitNumber
	 */
	public void resetPit(int pitNumber) {
		pits[pitNumber] = 0;
	}

	/**
	 * Steals stones in opposite pit, then adds them to stones in current pit, then
	 * moves them to mancala
	 * 
	 * @param pitNumber
	 */
	public void stealStones(int pitNumber) {

		if(isScorePit(pitNumber))
		{return;}
		// get stones in current pit
		int currentPitStones = pits[pitNumber];
		// get stones in opposite pit
		int oppositePitStones = pits[mirrorPit(pitNumber)];
		// check the current Player
		int stolenStones = oppositePitStones + currentPitStones;
		// reset stones in current pit
		resetPit(pitNumber);
		resetPit(mirrorPit(pitNumber));
		// get pit owner
		int targetMancala = getPitOwnerMancala(pitNumber);
		pits[targetMancala] += stolenStones;
	}

	/**
	 * Checks if the last stone played ended in a Mancala
	 */
	public boolean lgMancalaCheck(int pitNumber) {
		boolean endedInMancala = false;
		boolean isScorePit = isScorePit(pitNumber);
		boolean isPlayer = ownerIsCurrentPlayer(pitNumber);

		if (isPlayer && isScorePit) {
			endedInMancala = true;
		}
		return endedInMancala;
	}

	/**
	 * Checks if the last stone ended in an empty Pit
	 */
	public boolean lgEmptyPitCheck(int pitNumber) {
		boolean endedInEmptyPit = false;
		boolean isPlayer = ownerIsCurrentPlayer(pitNumber);
		boolean oneL = oneStoneLeft(pitNumber);
		boolean moreStonesInOpp = stonesInOppositePit(pitNumber);

		if (isPlayer && oneL) {
			endedInEmptyPit = true;
		}  

		return endedInEmptyPit;
	}
	/**
	 * This fn resets all undos for each player
	 */
	public void resetUndos() {
		if (currentTurn.equals(GamePlayer.PLAYER_A)) {
			playerB_Undos = 0;
		} else {
			playerA_Undos = 0;
		}
	}
	/**
	 * This function gets the number of stones at a particular pit
	 * @param pitNumber
	 * @return
	 */
	public int getStonesAtPit(int pitNumber) {
		int x = pits[pitNumber];
		return x;
	}

	/**
	 * This function moves the stones and distributes them across pit
	 * This is the main function of the game
	 * @param pitNumber
	 */
	public void distributeStones(int pitNumber) {

		// save current status
		this.saveCurrent();
		// reset Undos Expired
		undosExpired = false;
		// reset Undos per player
		resetUndos();
		// setting stones to the number of stones in the specified pitNumber
		int stones = getStonesAtPit(pitNumber);
		// setting the number of stones on the selected pit to zero
		// clearing the selected pit from all stones
		setStonesAtPit(pitNumber, 0);
		// setting the current pit to the pitNumber
		int currentPit = pitNumber;
		// while stones are more than 0, increment the current pit index
		// if the currentPit number is > the total number Of Pits, reset the CurrentPit
		// index to 0 to do another
		// loop through the mancala
		// if the pit is a ScorePit and
		while (stones > 0) {
			currentPit++;
			if (currentPit >= numberOfPits) {
				currentPit = 0;
			}
			if (isScorePit(currentPit) && pitOwner(currentPit) != currentTurn) {
				continue;
			}
			pits[currentPit] = pits[currentPit] + 1;
			stones--;
		}
		//
		checkFreeTurn(currentPit);
		checkLastGamePlay(currentPit);
		// check if pits are empty, game status is finished
		if (playerPitsEmptyCheck(GamePlayer.PLAYER_A) && playerPitsEmptyCheck(GamePlayer.PLAYER_B)) {
			currentGameStatus = GameStatus.GAME_ENDED;
		}
		// Checking if app pits are empty -> thus game has ended
		this.updateListeners();
	}

	/**
	 * Boolean Check if the pit is a Score Pit
	 * 
	 * @param pitNumber
	 * @return
	 */
	public boolean isScorePit(int pitNumber) {
		boolean isScorePit = false;
		if (pitNumber == playerAScorePit || pitNumber == playerBScorePit) {
			isScorePit = true;
		}
		return isScorePit;
	}


	/**
	 * Returns and prints the pits array
	 * @return
	 */
	public int[] getPits() {
		return pits;
	}

	/**
	 * Returns the game status
	 * @return
	 */
	public GameStatus getGameStatus() {
		return currentGameStatus;
	}

	/**
	 * Gets the current turn
	 * @return
	 */
	public GamePlayer getCurrentTurn() {
		return currentTurn;
	}
	/**
	 * holds the most recent game setup:stone distribution
	 */
	public void lastSetup() {
		pits = savedPits.clone();
	}

	/**
	 * This function saves the current distribution of stones on the Mancal Board
	 */
	public void saveCurrent() {
		savedPits = pits.clone();
	}
}