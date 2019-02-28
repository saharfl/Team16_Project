// A Class for storing basic data on map nodes used in creating the path for the ai to follow
public class MapNode {
  public int row;
  public int column;
  public int hValue;
  public int gValue = 5;
  public int depth;
  public int cost;
  public MapNode parent;
  public boolean impassible = false;
  public boolean isPlayer = false;
  public boolean isEnemy = false;
  //Constructor for position, required inputs
  public MapNode(int x, int y) {
    row = x;
    column = y;
  }
  /**
  * for returning the cost.
  * @return "cost" of the node.
  */
  public int getCost() {
    return cost;
  }
  /**
  * for setting a nodes parent and calculating it's movement cost (gValue)
  * @param par the node which will be made the parent of this node.
  * @return none.
  */
  public void setParent(MapNode par) {
    parent = par;
    depth = par.depth + 1;
    gValue = depth * 5;
  }
  /**
  * for setting the cost based on hValue and gValue, must be used after both have been set already.
  */
  public void setCost(){
    cost = (3 * hValue) + gValue;
  }
}
