// Import statements
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.stage.Stage;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.shape.*;
import java.util.ArrayList;
import javafx.scene.input.KeyEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Font;

public class Display extends Application {
    // javafx method required to run the graphics
    @Override
    public void start(Stage primaryStage) {
      // all instance variables and objects required to run program
      Board myMaze = new Board(25,25);
      myMaze.emptyBoard();
      myMaze.randomBoard();
      myMaze.createNodeBoard();
      PathCreator pathCheck = new PathCreator(myMaze);
      //myMaze.createNodeBoard();
      Player player = new Player(myMaze, myMaze.playerStartRow, myMaze.playerStartCol);
      int playerRow = player.getRow();
      int playerCol = player.getCol();
      SeekerEnemy enemy1 = new SeekerEnemy(myMaze, 1.4, 1, 20);
      SeekerEnemy enemy2 = new SeekerEnemy(myMaze, 1.4, myMaze.getRows() - 2, 20);
      SeekerEnemy enemy3 = new SeekerEnemy(myMaze, 1.4, (int)(myMaze.getRows()/2), myMaze.getColumns() - 2);
      /*while (!pathCheck.checkForPath(playerRow, playerCol, enemy1.getRow(), enemy1.getCol()) || !pathCheck.checkForPath(playerRow, playerCol, enemy2.getRow(), enemy2.getCol()) || !pathCheck.checkForPath(playerRow, playerCol, enemy3.getRow(), enemy3.getCol())) {
        myMaze.emptyBoard();
        myMaze.randomBoard();
        myMaze.createNodeBoard();
        pathCheck = new PathCreator(myMaze);
      }*/

      GameState gameState = new GameState();
      PieceTracker myTracker = new PieceTracker(myMaze);
      SurroundingsChecker surch = new SurroundingsChecker();


      // creation of root and gridpanes for displaying graphics
      StackPane root = new StackPane();
      GridPane base = new DrawBase(myMaze).drawBase();
      GridPane pieceView = new GridPane();

      // creating the initial view of all pieces on maze
      myTracker.updateTracker(myMaze);
      for (int x = 0; x < myMaze.getRows(); x++) {
        for (int y = 0; y < myMaze.getColumns(); y++) {
          pieceView.add(myTracker.tracker[x][y], y, x);
        }
      }
      // adding to the root so gridpanes are displayed
      root.getChildren().add(base);
      root.getChildren().add(pieceView);
      Scene scene = new Scene(root);
      // setting stage variables
      primaryStage.setTitle("Maze Game");
      primaryStage.setScene(scene);
      primaryStage.show();
      // setting the on key pressed for the scene to be an event handler object
      scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
        boolean running = true;
        @Override
        public void handle(KeyEvent event) {
          // halts the running if the player is stuck
          if (surch.checkAroundPlayer(myMaze, player.getRow(), player.getCol()).size() == 0) {
            running = false;
            Label loseText = new Label("You Lose!");
            loseText.setFont(Font.font("Tahoma", FontWeight.NORMAL, 100));
            base.add(loseText, 0, 0);
          }
          // gets key input from the player if the player has not won or lost yet
          if (running) {
            if(event.getCode() == KeyCode.UP && surch.checkEmpty(myMaze, player.getRow()-1, player.getCol())) {
              player.moveUp();
              handleEnemyMoves();
              refreshView();
            }
            else if(event.getCode() == KeyCode.DOWN && surch.checkEmpty(myMaze, player.getRow()+1, player.getCol())) {
              player.moveDown();
              handleEnemyMoves();
              refreshView();
            }
            else if(event.getCode() == KeyCode.RIGHT && surch.checkEmpty(myMaze, player.getRow(), player.getCol()+1)) {
              player.moveRight();
              handleEnemyMoves();
              refreshView();
            }
            else if(event.getCode() == KeyCode.LEFT && surch.checkEmpty(myMaze, player.getRow(), player.getCol()-1)) {
              player.moveLeft();
              handleEnemyMoves();
              refreshView();
            }
          }
          event.consume();
          }

          /**
          * handles all enemy moves and checks for game win or lose
          * @param none
          */
          public void handleEnemyMoves() {
            if (gameState.checkWin(myMaze)) {
              running = false;
              pieceView.getChildren().clear();
              base.getChildren().clear();
              Label winText = new Label("You Win!!!");
              winText.setFont(Font.font("Tahoma", FontWeight.NORMAL, 100));
              base.add(winText, 0, 0);
            } else {
              enemy1.takeTurn(player.getRow(), player.getCol());
              enemy2.takeTurn(player.getRow(), player.getCol());
              enemy3.takeTurn(player.getRow(), player.getCol());
              if (gameState.checkLose(myMaze)) {
                running = false;
                base.getChildren().clear();
                Label loseText = new Label("You Lose!");
                loseText.setFont(Font.font("Tahoma", FontWeight.NORMAL, 100));
                base.add(loseText, 0, 0);
              }
            }
          }

          /**
          * refreshes the pieceview gridpane so player and enemy positions are updated
          * @param none
          */
          public void refreshView() {
            if (running || gameState.checkLose(myMaze)) {
              myTracker.updateTracker(myMaze);
              pieceView.getChildren().clear();
              for (int x = 0; x < myMaze.getRows(); x++) {
                for (int y = 0; y < myMaze.getColumns(); y++) {
                  pieceView.add(myTracker.tracker[x][y], y, x);
                }
              }
            }
          }
      });
    }
  }
