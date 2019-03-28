//package graphics;
/*

This file is just me playing arund with how graphics could work, definitely not useable
It's a mess and is missing so many pieces

I'm gonna experiment with this file, the usable graphics file will be the Display class


*/


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

public class GraphicsTesting extends Application {
    @Override
    public void start(Stage primaryStage) {
      Board myMaze = new Board(30,50);
      myMaze.randomBoard();
      Player player = new Player(myMaze, 10, 1);
      SeekerEnemy enemy1 = new SeekerEnemy(myMaze, 1.4, 1, 20);
      SeekerEnemy enemy2 = new SeekerEnemy(myMaze, 1.4, myMaze.getRows() - 2, 20);
      SeekerEnemy enemy3 = new SeekerEnemy(myMaze, 1.4, (int)(myMaze.getRows()/2), myMaze.getColumns() - 2);
      GameState gameState = new GameState();
      StackPane root = new StackPane();
      GridPane base = new DrawBase(myMaze).drawBase();
      GridPane pieceView = new GridPane();
      PieceTracker myTracker = new PieceTracker(myMaze);
      SurroundingsChecker surch = new SurroundingsChecker();

      myTracker.updateTracker(myMaze);
      for (int x = 0; x < myMaze.getRows(); x++) {
        for (int y = 0; y < myMaze.getColumns(); y++) {
          pieceView.add(myTracker.tracker[x][y], y, x);
        }
      }
      root.getChildren().add(base);
      root.getChildren().add(pieceView);
      Scene scene = new Scene(root);
      primaryStage.setTitle("Widget Demo");
      primaryStage.setScene(scene);
      primaryStage.show();
      System.out.println("how many?");
      scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
        boolean running = true;
        @Override
        public void handle(KeyEvent event) {
          if (surch.checkAroundPlayer(myMaze, player.getRow(), player.getCol()).size() == 0) {
            running = false;
            Label loseText = new Label("You Lose!");
            loseText.setFont(Font.font("Tahoma", FontWeight.NORMAL, 100));
            base.add(loseText, 0, 0);
          }
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
