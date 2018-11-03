/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connectfive;

/**
 *
 * @author nickw
 */
public class Player {
    
    private Game game;
    private String name;
    private Board board;

    public Player(String name) {
        this.name = name;
    }
    
    public boolean playMove(int row, int col, int num){
        try{
            board.set(row, col, num);
            return true;
        }catch(ArrayIndexOutOfBoundsException e){
            return false;
        }
    }
    
}
