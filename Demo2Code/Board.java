// delete package
//package teamassignment;

import java.util.Arrays;

/**
 *
 * @author DanielSikstrom
 */
//make a main method to run the program
public class Board {

    public String[][] stringBoard;
    public int finishX, finishY, startX, startY;
    public MapNode[][] board;
    public int ROWS;
    public int COLUMNS;
    public int playerStartRow;
    public int playerStartCol;
    //to see if a space is open use stringBoard[column][row].equals("#") or stringBoard[column][row].equals(" ")
    //only use once to make the default stringBoard
    public Board(int x, int y) {
      this.stringBoard = new String[x][y];
      this.board  = new MapNode[x][y];
      ROWS = x;
      COLUMNS = y;
      for (int i = 0; i < ROWS; i++) {
        for (int j = 0; j < COLUMNS; j++) {
          board[i][j] = new MapNode(i,j);
        }
      }
    }

    public int getRows(){
      return ROWS;
    }
    public int getColumns() {
      return COLUMNS;
    }

    public void emptyBoard() {
        for (int i = 0; i < this.stringBoard.length; i++) {
            for (int j = 0; j < this.stringBoard[0].length; j++) {
                this.stringBoard[i][j] = "W";
                if (i == 0 || j == 0 || i == this.stringBoard.length - 1 || j == this.stringBoard[0].length - 1) {
                    this.stringBoard[i][j] = " ";
                }
            }
        }
    }

    public void randomBoard() {
        String[][] boardMemory = new String[this.stringBoard[0].length][this.stringBoard.length];
        int genStartColumn = 0;
        int genStartRow = 0;
        int genCurrentColumn = 100000; //set to something start row will never be
        int genCurrentRow = 100000; //set to something start row will never be
        int lastMove = 5;
        int backtrackMove = 5;
        int randomPick;
        int randomMove = 99; // 0 = N, 1 = E, 2 = S, 3 = W
        int repetitions = 0;
        int stuckInLoop = 0;
        boolean didBacktrack = false;
        boolean firstMove = true;
        boolean end = false;
        boolean canMove = false;
        boolean moved = false;
        while (!this.stringBoard[genStartColumn][genStartRow].equals("W")) {
            genStartColumn = (int) (Math.random() * this.stringBoard.length - 1);
            genStartRow = (int) (Math.random() * this.stringBoard[0].length - 1);
        }
        this.stringBoard[genStartColumn][genStartRow] = "#";

        while (!end) {
            stuckInLoop = 0;
            repetitions++;
            if (repetitions > 420) {
                end = true;
            }
            if (moved) {
                lastMove = randomMove;
            }

            //check if can move
            moved = false;
            randomMove = (int) (Math.random() * 4);

            if (firstMove) {
                genCurrentColumn = genStartColumn;
                genCurrentRow = genStartRow;
                if (randomMove == 0 && !this.stringBoard[genCurrentColumn - 1][genCurrentRow].equals(" ")) {
                    this.stringBoard[genCurrentColumn + 1][genCurrentRow] = " ";
                    this.stringBoard[genCurrentColumn][genCurrentRow + 1] = " ";
                    this.stringBoard[genCurrentColumn][genCurrentRow - 1] = " ";
                    this.stringBoard[genCurrentColumn + 1][genCurrentRow + 1] = " ";
                    this.stringBoard[genCurrentColumn + 1][genCurrentRow + -1] = " ";
                    moved = true;
                    genCurrentColumn -= 1;
                }
                if (randomMove == 1 && !this.stringBoard[genCurrentColumn][genCurrentRow + 1].equals(" ")) {
                    this.stringBoard[genCurrentColumn + 1][genCurrentRow] = " ";
                    this.stringBoard[genCurrentColumn - 1][genCurrentRow] = " ";
                    this.stringBoard[genCurrentColumn][genCurrentRow - 1] = " ";
                    this.stringBoard[genCurrentColumn + 1][genCurrentRow - 1] = " ";
                    this.stringBoard[genCurrentColumn - 1][genCurrentRow - 1] = " ";
                    moved = true;
                    genCurrentRow += 1;
                }
                if (randomMove == 2 && !this.stringBoard[genCurrentColumn + 1][genCurrentRow].equals(" ")) {
                    this.stringBoard[genCurrentColumn - 1][genCurrentRow] = " ";
                    this.stringBoard[genCurrentColumn][genCurrentRow + 1] = " ";
                    this.stringBoard[genCurrentColumn][genCurrentRow - 1] = " ";
                    this.stringBoard[genCurrentColumn - 1][genCurrentRow + 1] = " ";
                    this.stringBoard[genCurrentColumn - 1][genCurrentRow - 1] = " ";
                    moved = true;
                    genCurrentColumn += 1;
                }
                if (randomMove == 3 && !this.stringBoard[genCurrentColumn][genCurrentRow - 1].equals(" ")) {
                    this.stringBoard[genCurrentColumn - 1][genCurrentRow] = " ";
                    this.stringBoard[genCurrentColumn + 1][genCurrentRow] = " ";
                    this.stringBoard[genCurrentColumn][genCurrentRow + 1] = " ";
                    this.stringBoard[genCurrentColumn - 1][genCurrentRow + 1] = " ";
                    this.stringBoard[genCurrentColumn + 1][genCurrentRow + 1] = " ";
                    moved = true;
                    genCurrentRow -= 1;
                }
                firstMove = false;
            } else {

                //testing if can move to new spot
                canMove = false;
                if (this.stringBoard[genCurrentColumn + 1][genCurrentRow].equals("W")) {
                    canMove = true;
                } else if (this.stringBoard[genCurrentColumn - 1][genCurrentRow].equals("W")) {
                    canMove = true;
                } else if (this.stringBoard[genCurrentColumn][genCurrentRow + 1].equals("W")) {
                    canMove = true;
                } else if (this.stringBoard[genCurrentColumn][genCurrentRow - 1].equals("W")) {
                    canMove = true;
                }

                if (canMove) {
                    if (randomMove == lastMove) {
                        //moving in a straight line
                        if (randomMove == 0 && this.stringBoard[genCurrentColumn - 1][genCurrentRow].equals("W")) {
                            this.stringBoard[genCurrentColumn][genCurrentRow + 1] = " ";
                            this.stringBoard[genCurrentColumn][genCurrentRow - 1] = " ";
                            moved = true;
                            genCurrentColumn -= 1;
                        }
                        if (randomMove == 1 && this.stringBoard[genCurrentColumn][genCurrentRow + 1].equals("W")) {
                            this.stringBoard[genCurrentColumn + 1][genCurrentRow] = " ";
                            this.stringBoard[genCurrentColumn - 1][genCurrentRow] = " ";
                            moved = true;
                            genCurrentRow += 1;
                        }
                        if (randomMove == 2 && this.stringBoard[genCurrentColumn + 1][genCurrentRow].equals("W")) {
                            this.stringBoard[genCurrentColumn][genCurrentRow - 1] = " ";
                            this.stringBoard[genCurrentColumn][genCurrentRow + 1] = " ";
                            moved = true;
                            genCurrentColumn += 1;
                        }
                        if (randomMove == 3 && this.stringBoard[genCurrentColumn][genCurrentRow - 1].equals("W")) {
                            this.stringBoard[genCurrentColumn - 1][genCurrentRow] = " ";
                            this.stringBoard[genCurrentColumn + 1][genCurrentRow] = " ";
                            moved = true;
                            genCurrentRow -= 1;
                        }
                    } else {
                        //turning
                        if (lastMove == 0) {
                            if (randomMove == 1 && this.stringBoard[genCurrentColumn][genCurrentRow + 1].equals("W")) {
                                this.stringBoard[genCurrentColumn][genCurrentRow - 1] = " ";
                                this.stringBoard[genCurrentColumn - 1][genCurrentRow - 1] = " ";
                                this.stringBoard[genCurrentColumn - 1][genCurrentRow] = " ";
                                this.stringBoard[genCurrentColumn - 1][genCurrentRow + 1] = " ";
                                moved = true;
                                genCurrentRow += 1;

                            }
                            if (randomMove == 3 && this.stringBoard[genCurrentColumn][genCurrentRow - 1].equals("W")) {
                                this.stringBoard[genCurrentColumn][genCurrentRow + 1] = " ";
                                this.stringBoard[genCurrentColumn - 1][genCurrentRow + 1] = " ";
                                this.stringBoard[genCurrentColumn - 1][genCurrentRow] = " ";
                                this.stringBoard[genCurrentColumn - 1][genCurrentRow - 1] = " ";
                                moved = true;
                                genCurrentRow -= 1;
                            }
                        }
                        if (lastMove == 1) {
                            if (randomMove == 0 && this.stringBoard[genCurrentColumn - 1][genCurrentRow].equals("W")) {
                                this.stringBoard[genCurrentColumn][genCurrentRow + 1] = " ";
                                this.stringBoard[genCurrentColumn + 1][genCurrentRow] = " ";
                                this.stringBoard[genCurrentColumn - 1][genCurrentRow + 1] = " ";
                                this.stringBoard[genCurrentColumn + 1][genCurrentRow + 1] = " ";
                                moved = true;
                                genCurrentColumn -= 1;

                            }
                            if (randomMove == 2 && this.stringBoard[genCurrentColumn + 1][genCurrentRow].equals("W")) {
                                this.stringBoard[genCurrentColumn - 1][genCurrentRow] = " ";
                                this.stringBoard[genCurrentColumn - 1][genCurrentRow + 1] = " ";
                                this.stringBoard[genCurrentColumn][genCurrentRow + 1] = " ";
                                this.stringBoard[genCurrentColumn + 1][genCurrentRow + 1] = " ";
                                moved = true;
                                genCurrentColumn += 1;
                            }
                        }
                        if (lastMove == 2) {
                            if (randomMove == 1 && this.stringBoard[genCurrentColumn][genCurrentRow + 1].equals("W")) {
                                this.stringBoard[genCurrentColumn][genCurrentRow - 1] = " ";
                                this.stringBoard[genCurrentColumn + 1][genCurrentRow - 1] = " ";
                                this.stringBoard[genCurrentColumn + 1][genCurrentRow] = " ";
                                this.stringBoard[genCurrentColumn - 1][genCurrentRow + 1] = " ";
                                moved = true;
                                genCurrentRow += 1;

                            }
                            if (randomMove == 3 && this.stringBoard[genCurrentColumn][genCurrentRow - 1].equals("W")) {
                                this.stringBoard[genCurrentColumn][genCurrentRow + 1] = " ";
                                this.stringBoard[genCurrentColumn + 1][genCurrentRow + 1] = " ";
                                this.stringBoard[genCurrentColumn + 1][genCurrentRow] = " ";
                                moved = true;
                                genCurrentRow -= 1;
                            }
                        }
                        if (lastMove == 3) {
                            if (randomMove == 0 && this.stringBoard[genCurrentColumn - 1][genCurrentRow].equals("W")) {
                                this.stringBoard[genCurrentColumn + 1][genCurrentRow] = " ";
                                this.stringBoard[genCurrentColumn + 1][genCurrentRow - 1] = " ";
                                this.stringBoard[genCurrentColumn][genCurrentRow - 1] = " ";
                                this.stringBoard[genCurrentColumn + 1][genCurrentRow - 1] = " ";
                                moved = true;
                                genCurrentColumn -= 1;

                            }
                            if (randomMove == 2 && this.stringBoard[genCurrentColumn + 1][genCurrentRow].equals("W")) {
                                this.stringBoard[genCurrentColumn - 1][genCurrentRow] = " ";
                                this.stringBoard[genCurrentColumn - 1][genCurrentRow - 1] = " ";
                                this.stringBoard[genCurrentColumn][genCurrentRow - 1] = " ";

                                moved = true;
                                genCurrentColumn += 1;
                            }
                        }

                    }
                } else {
                    //cannot move
                    if (lastMove == 0 || lastMove == 2 || lastMove == 3) {
                        backtrackMove = Math.abs(lastMove - 2);
                    } else if (lastMove == 1) {
                        backtrackMove = lastMove + 2;
                    }
                    while (!canMove) {
                        stuckInLoop++;
                        if (stuckInLoop > 1000) {
                            end = true;
                        }
                        didBacktrack = false;
                        randomPick = (int) (Math.random() * 2);
                        canMove = false;
                        if (this.stringBoard[genCurrentColumn + 1][genCurrentRow].equals("W")) {
                            canMove = true;
                        } else if (this.stringBoard[genCurrentColumn - 1][genCurrentRow].equals("W")) {
                            canMove = true;
                        } else if (this.stringBoard[genCurrentColumn][genCurrentRow + 1].equals("W")) {
                            canMove = true;
                        } else if (this.stringBoard[genCurrentColumn][genCurrentRow - 1].equals("W")) {
                            canMove = true;
                        }

                        if (randomPick == 0) {
                            if (this.stringBoard[genCurrentColumn + 1][genCurrentRow].equals("#") && backtrackMove != 0) {
                                //System.out.println("Down");
                                genCurrentColumn += 1;
                                backtrackMove = 2;
                                didBacktrack = true;
                            } else if (this.stringBoard[genCurrentColumn - 1][genCurrentRow].equals("#") && backtrackMove != 2) {
                                //System.out.println("Up");
                                genCurrentColumn -= 1;
                                backtrackMove = 0;
                                didBacktrack = true;
                            }
                        }
                        if (randomPick == 1) {
                            if (this.stringBoard[genCurrentColumn][genCurrentRow + 1].equals("#") && backtrackMove != 3) {
                                //System.out.println("Right");
                                genCurrentRow += 1;
                                backtrackMove = 1;
                                didBacktrack = true;
                            } else if (this.stringBoard[genCurrentColumn][genCurrentRow - 1].equals("#") && backtrackMove != 1) {
                                //System.out.println("Left");
                                genCurrentRow -= 1;
                                backtrackMove = 3;
                                didBacktrack = true;
                            }
                        }

                        if (genCurrentColumn + 3 <= this.stringBoard.length && this.stringBoard[genCurrentColumn + 2][genCurrentRow].equals("W")) {
                            genCurrentColumn += 1;
                            lastMove = 2;
                            didBacktrack = true;
                        } else if (genCurrentColumn - 3 >= 0 && this.stringBoard[genCurrentColumn - 2][genCurrentRow].equals("W")) {
                            genCurrentColumn -= 1;
                            lastMove = 0;
                            didBacktrack = true;
                        } else if (genCurrentRow + 3 <= this.stringBoard.length && this.stringBoard[genCurrentColumn][genCurrentRow + 2].equals("W")) {
                            genCurrentRow += 1;
                            lastMove = 1;
                            didBacktrack = true;
                        } else if (genCurrentRow - 3 >= 0 && this.stringBoard[genCurrentColumn][genCurrentRow - 2].equals("W")) {
                            genCurrentRow -= 1;
                            lastMove = 3;
                            didBacktrack = true;
                        }

                        if (!didBacktrack) {
                            backtrackMove = 5;
                        }
                        this.stringBoard[genCurrentColumn][genCurrentRow] = "#";

                    }
                }

            }

            this.stringBoard[genCurrentColumn][genCurrentRow] = "#";
        }



        boolean b = true;
        for (int i = 0; i < this.stringBoard.length && b; i++) {
            for (int j = 0; j < this.stringBoard.length && b; j++) {
                if (this.stringBoard[j][i].equals("#")) {

                    this.stringBoard[j][i] = "!";
                    b = false;
                }
            }
        }

        b = true;
        for (int i = this.stringBoard.length - 1; i >= 0 && b; i--) {
            for (int j = this.stringBoard.length - 1; j >= 0 && b; j--) {
                if (this.stringBoard[j][i].equals("#")) {
                    this.stringBoard[j][i] = "O";
                    b = false;
                }
            }
        }

        for (int i = 0; i < this.stringBoard.length; i++) {
            for (int j = 0; j < this.stringBoard[0].length; j++) {
	         if (i == this.stringBoard.length - 2 || i == 1 || i == (this.stringBoard.length / 2)) {
		if(!this.stringBoard[i][j].equals("!")){
                    this.stringBoard[i][j] = "#";
}
                }
                randomPick = (int) (Math.random() * 3);
                if (this.stringBoard[i][j].equals("W")) {
                    this.stringBoard[i][j] = " ";
                }
                if (this.stringBoard[i][j].equals(" ")) {
                    if (randomPick == 0) {
                        this.stringBoard[i][j] = "#";
                    }
                }
                if (i == 0 || j == 0 || i == this.stringBoard.length - 1 || j == this.stringBoard[0].length - 1) {
                    this.stringBoard[i][j] = " ";
                }
            }
        }
    }

    public void createNodeBoard() {
      for (int i = 0; i < ROWS; i++) {
        for (int j = 0; j < COLUMNS; j++) {
          if (stringBoard[i][j].equals("#")) {
            board[i][j].setPassability(true);
          } else if (stringBoard[i][j].equals(" ")) {
            board[i][j].setPassability(false);
          } else if (stringBoard[i][j].equals("O")) {
            board[i][j].setFinish();
          } else if (stringBoard[i][j].equals("!")) {
            board[i][j].setPlayer(true);
            playerStartRow = i;
            playerStartCol = j;
          }
        }
      }
    }

//will print out the new stringBoard (must use every time to see changes to the stringBoard array
    public void printBoard() {
        System.out.println(Arrays.deepToString(this.stringBoard).replace("], ", "\n").replace("[[", "").replace("]]", "").replace("[", "").replace(",", ""));
    }
}
