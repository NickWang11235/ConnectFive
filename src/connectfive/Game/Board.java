/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connectfive.Game;

/**
 * The underlying play board of ConnectFive game
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
    
    /**
     * Checks there is any 5 piece long consecutive vertical, horizontal, or 
     * diagonal around a center point 
     * A marker marks around the center point being checked, and keeps going 
     * outward in the direction the marker marks
     * @param currRow
     * @param currCol
     * @param identity the number to check match with
     * @return
     */
    public boolean checkGameOver(int currRow, int currCol, int identity){

        outerloop:
        //Loops through the immediat adjecent 8 spaces sourrounding the center piece
        //Column by column, then row by row.
        for(int i = currRow - 1; i < currRow + 2; i++){
            for(int j = currCol - 1; j < currCol + 2; j++){
                if(i == currRow && j == currCol){
                    //The loop loops onto the center position, meaning the row 
                    //above the center and the one space to the left of center
                    //has been checked, breaks the double for-loop
                    break outerloop;
                }
                //Checks if the index being checked is within the bound
                if(i >= 0 && i < board.length && currCol >= 0 && currCol < board[0].length ){
                    //Marks the current index
                    int x = i;
                    int y = j;
                    //Direction to be checked as change in x and y position
                    int dx = i - currRow;
                    int dy = j - currCol;
                    //Count the number of consecutive points
                    int counter = 0;
                    //Checks counting outward by adding dx and dy
                    while(x < board.length && y < board[0].length && x >= 0 && y >= 0 && 
                            board[x][y] == identity){
                            counter++;
                            x += dx;
                            y += dy; 
                    }
                    //Return the current index to center
                    x = i - dx;
                    y = j - dy;
                    //Checks counting inward by dubtracting dx and dy
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

    /**
     * Resets every element on board to 0
     */
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
