public class GameState {
	public int size = 22;

/** checks if player is over to finish square in maze
 * thus signaling a win if player has won return true
 * @param b
 * @return win
 */

public boolean checkWin(Board b) {
	boolean win = true;
	for (int i = 0; i < size; i++) {
	    for (int j = 0; j < size; j++) {
	        if (b.board[i][j] == "X") {
	        	win = false;
	        }
	    }
	}

return win;
}
/**check if Ai has moved on top of player this ending the game
 * if player has lost return true and reset the board
 *
 * @param b
 * @return lose
 */
public boolean checkLose(Board b ) {
	boolean lose = true;
	for (int i = 0; i < size; i++) {
	    for (int j = 0; j < size; j++) {
	        if (b.board[i][j] == "!") {
	        	lose = false;
	        }
	    }
	}
	/*if(lose == true) {
	b = new Board();
}*/

	return lose;
}
}
