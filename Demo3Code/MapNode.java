//package logic;
// A Class for storing basic data on map nodes used in creating the path for the ai to follow
public class MapNode {

  public int row;
  public int column;
  protected boolean passible = true;
  protected boolean isFinish = false;
  public boolean isPlayer = false;
  public boolean isEnemy = false;
  //Constructor for position, required inputs
  public MapNode(int x, int y) {
    row = x;
    column = y;
  }
  public boolean getPassability() {
    return passible;
  }
  public void setPassability(boolean pass) {
    passible = pass;
  }
  public void setFinish() {
    isFinish = true;
  }
  public boolean getFinish() {
    return isFinish;
  }
  public boolean checkEnemy() {
    return isEnemy;
  }
  public void setEnemy(boolean state) {
    isEnemy = state;
  }
  public boolean checkPlayer() {
    return isPlayer;
  }
  public void setPlayer(boolean state) {
    isPlayer = state;
  }
}

// TO DO on this file: remove isPlayer and isEnemy
