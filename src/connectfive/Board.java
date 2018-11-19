/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connectfive;

import static java.lang.System.*;

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

        outerloop:
        for(int i = currRow - 1; i < currRow + 2; i++){
            for(int j = currCol - 1; j < currCol + 2; j++){
                if(i == currRow && j == currCol){
                    break outerloop;
                }
                if(i >= 0 && i < board.length && currCol >= 0 && currCol < board[0].length ){
                    int x = i;
                    int y = j;
                    int dx = i - currRow;
                    int dy = j - currCol;
                    int counter = 0;
                    while(x < board.length && y < board[0].length && x >= 0 && y >= 0 && 
                            board[x][y] == identity){
                            System.out.println("inc");
                            counter++;
                            x += dx;
                            y += dy; 
                    }
                    x = i - dx;
                    y = j - dy;
                    while(x < board.length && y < board[0].length && x >= 0 && y >= 0 && 
                            board[x][y] == identity){
                            counter++;
                            x -= dx;
                            y -= dy; 
                    }
                    out.println(counter);
                    if(counter >= WIN_CONDITION){
                        return true;
                    }
                }
            }
        }
        return false;
    }
 
    public void reset(){
        for(int i = 0; i < board.length; i++){
            for (int j = 0; j < board[0].length ; j++){
                board[i][j] = 0;
            }
        }
    }
    
    @Override
    public String toString(){
        String b = "";
        for(int[] r : board){
            for(int c : r){
                b += c + " ";
            }
            b += "\n";
        }
        return b; 
    }
    
}
