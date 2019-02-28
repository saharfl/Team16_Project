import java.util.ArrayList;
// Class for checking surroundings of tiles
public class SurroundingsChecker {
  // instance vars #################################
  public String playerPiece = "O";
  public ArrayList<String> directions = new ArrayList<String>();
  // constructors ##################################
  //base constructor for no args
  public SurroundingsChecker() {
  }
  //constructor for changing the playerpiece marker
  public SurroundingsChecker(String playerMark) {
    playerPiece = playerMark;
  }
  // methods #######################################
  /**
  * determines whether a tile is empty or not
  * @param state the map state being assesed
  * @param row,col the position of the tile being checked
  * @return true if empty false if not empty
  */
  public boolean checkEmpty(Board state, int row, int column) {
    boolean result = false;
    if (state.board[row][column] == "#" || state.board[row][column] == playerPiece) {
      result = true;
    }
    return result;
  }
  /**
  * uses checkEmpty() to asses the four borders of a given tile and return the empty directions
  * @param state the map state being assesed
  * @param myRow,myCol the position of the tile being checked around
  * @return a list of all the directions in which the tile is bordered by an empty space
  */
  public ArrayList<String> checkAround(Board state, int myRow, int myColumn) {
    directions.clear();
    if (checkEmpty(state,myRow+1,myColumn)) {directions.add("down");}
    if (checkEmpty(state,myRow-1,myColumn)) {directions.add("up");}
    if (checkEmpty(state,myRow,myColumn+1)) {directions.add("right");}
    if (checkEmpty(state,myRow,myColumn-1)) {directions.add("left");}
    return directions;
  }
  /**
  * calculates the "manhattan score" of a given tile location and goal.
  * @param myRow,myCol the position of the tile to asses score for.
  * @param goalRow,goalCol the location of the goal of the tile.
  * @return a number representetive of how far from the goal a given tile is.
  */
  public int manhattanScore(int myRow, int myCol, int goalRow, int goalCol) {
    int dr = Math.abs(goalRow - myRow);
    int dc = Math.abs(goalCol - myCol);
    return (dr + dc);
  }
  /**
  * A method only used for testing
  */
  public void printList(ArrayList<String> myList) {
    System.out.print("(");
    for (int i = 0; i < myList.size(); i++) {
      System.out.print(myList.get(i) + ", ");
    }
    System.out.print(")");
  }
}
