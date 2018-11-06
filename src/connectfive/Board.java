/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connectfive;

/**
 *
 * @author samuelliu
 */
public class Board {
    private int[][] board;
    public static final int WIN_CONDITION = 5;
    public Board(int row, int col){
        board = new int[row][col];
        for(int i = 0; i < row; i++){
            for (int j = 0; j < col ; j++){
                board[i][j] = 0;
            }
        }
    }
    
    public void set(int row, int col, int identity){
        board[row][col] = identity;
    }
    
    public int[][] getBoard(){
        return board;
    }
    
    public boolean checkGameOver(int currRow, int currCol, int identity){
        for(int i = currRow - 1; i < currRow + 2; currRow++){
            for(int j = currCol - 1; j < currCol + 2 && !(i == currRow && j == currCol); currCol++){
                if(i >= 0 && i < board.length && currCol >= 0 && currCol < board[0].length ){
                    int x = i;
                    int y = j;
                    int dx = i - currRow;
                    int dy = j - currCol;
                    int counter = 1;
                    while(x < board.length && y < board[0].length && x >= 0 && y >= 0 && 
                            board[x][y] == identity){
                            counter++;
                            x += dx;
                            y += dy; 
                    }
                    x = i;
                    y = j;
                    while(x < board.length && y < board[0].length && x >= 0 && y >= 0 && 
                            board[x][y] == identity){
                            counter++;
                            x -= dx;
                            y -= dy; 
                    }
                    if(counter >= WIN_CONDITION){
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
}
