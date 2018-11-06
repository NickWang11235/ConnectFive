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
            Thread t = new Thread(new Game(10, 10, new Player("Nick", 1), new Player("Sam" ,2)));
            
            t.start();
        }catch(Game.PlayerAmountException e){
            System.exit(0);
        }
        
        
    }
    
}
