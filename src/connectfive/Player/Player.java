/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connectfive.Player;

/**
 * Player interface that determines how player behaves
 * @author nickw
 */
public interface Player {
    
    //This method describes the state a player is in while the game is not lost.
    //It handles how to play a move, as well as waiting for its turn
    void play();
    
    //Closes I/O streams
    void close();
}
