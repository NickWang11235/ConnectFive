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
    public final int id;
    
    public Player(String name, int id) {
        this.name = name;
        this.id = id;
    }
    
    public boolean playMove(int row, int col, int num){
        try{
            if(Game.board.getBoard()[row][col] == 0){
                Game.board.set(row, col, num);
                return true;
            }else{
                return false;
            }
        }catch(ArrayIndexOutOfBoundsException e){
            return false;
        }
    }
    
}
