/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connectfive;

import java.io.*;
import java.net.*;

/**
 *
 * @author nickw
 */
public class ConnectFive {

    public static final int SERVER_HOST_ID = 1;
    public static final int CLIENT_ID = 2;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Player p;
        Game g = new Game(new Board(10,10));
        
        switch (args.length) {
            case 1:
                p = (Player)new HostPlayer(Integer.parseInt(args[0]));
                break;
            case 2:
                p = (Player)new ClientPlayer(args[0], Integer.parseInt(args[1]));
                break;
            default:
                throw new IllegalArgumentException();
        }
        
        while(Game.getState()){
            p.play();
        }
        
        p.close();
        
    }
    
}
