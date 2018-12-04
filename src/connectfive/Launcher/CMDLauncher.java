/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connectfive.Launcher;

import connectfive.Game.Board;
import connectfive.Game.Game;
import connectfive.Player.HostPlayer;
import connectfive.Player.ClientPlayer;
import connectfive.Player.Player;

/**
 * Launches the backend algorithm, sends, and receives data.
 * @author nickw
 */
public class CMDLauncher implements Runnable{
    
    //command line arguments
    private String[] args;
    
    public CMDLauncher(String[] args){
        this.args = args;
    }
    
    @Override
    public void run() {
        //Player, could either be a client or a host
        Player p;
        //Create a board of size 18x18
        Game g = new Game(new Board(18, 18));

        //Determines whether the player is a host or a server
        switch (args.length) {
            case 1:
                p = (Player) new HostPlayer(Integer.parseInt(args[0]));
                break;
            case 2:
                p = (Player) new ClientPlayer(args[0], Integer.parseInt(args[1]));
                break;
            default:
                throw new IllegalArgumentException();
        }

        //While the game is running, play move
        while (Game.getState()) {
            p.play();
        }
        
        //Closes I/O streams.
        p.close();
    }
    
}
