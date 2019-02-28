import java.util.ArrayList;
import java.util.Scanner;
import javafx.application.Application;
public class Runner {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    Board myMaze = new Board();
    int theSpot = 0;
    myMaze.generateBoard();
    Move player = new Move();
    myMaze.board[player.currentColumn][player.currentRow] = "!";
    PathingHandler path = new PathingHandler(myMaze);
    AIMover enemy1 = new AIMover(myMaze, 5, 20, path);
    AIMover enemy2 = new AIMover(myMaze, 15, 20, path);
    AIMover enemy3 = new AIMover(myMaze, 7, 20, path);
    GameState gameState = new GameState();
    myMaze.printBoard();

    boolean quit = false;

    while (!quit && !gameState.checkWin(myMaze) && !gameState.checkLose(myMaze)) {
      player.makeAMove(myMaze);
      if (Math.random() > 0.95) {
        enemy1.randomMove();
      }
      if (Math.random() > 0.2 && !gameState.checkLose(myMaze)) {
        enemy1.usePath(player.currentColumn, player.currentRow);
      }



      if (Math.random() > 0.95) {
        enemy2.randomMove();
      }
      if (Math.random() > 0.2 && !gameState.checkLose(myMaze)) {
        enemy2.usePath(player.currentColumn, player.currentRow);
      }

      if (Math.random() > 0.95) {
        enemy3.randomMove();
      }
      if (Math.random() > 0.2 && !gameState.checkLose(myMaze)) {
        enemy3.usePath(player.currentColumn, player.currentRow);
      }

      //enemy1.hearingMove(player.currentColumn, player.currentRow);
      myMaze.printBoard();
    }
    if (gameState.checkWin(myMaze)) {
      System.out.println("You Won!");
    } else {
      System.out.println("You Lost!");
    }

  }
}
