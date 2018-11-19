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
public class ConnectFive {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try{
            Board b = new Board(10,10);
            Thread t = new Thread(new Game(b , new HostPlayer("Nick", 1), new ConnectingPlayer("Sam" ,2)));
            t.start();
        }catch(Game.PlayerAmountException e){
            System.exit(0);
        }
        
        
    }
    
}
