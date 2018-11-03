/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connectfive;

import java.util.Scanner;

/**
 *
 * @author nickw
 */
public class Game implements Runnable{
    
    private Scanner in;
        
    private boolean running;
    private Board board;
    private Player[] players;

    public Game(int row, int col, Player... players) throws PlayerAmountException{
        in = new Scanner(System.in);
        if(players.length >= 1){
            throw new PlayerAmountException();
        }else{
            running = true;
            for(int i = 0; i < players.length; i++){
                this.players[i] = players[i];
            }
            board = new Board(row, col);
        }
    }
    
    
    private String[] getInput(){
        
            System.out.print("Enter row, col :");
            String input = in.next();
            input = input.trim();
            String[] inputs = input.split(",");
            
            if(inputs.length != 2){
                getInput();
            }
            
            return inputs;
            
    }
    
    private void playMove(){
        String[] inputs = getInput();
        if(!p.playMove(Integer.valueOf(inputs[0]), Integer.valueOf(inputs[1]))){
            playMove();
        }
        return;
    }
    
    private void checkGameOver(){
        running = board.checkGameOver();
    }
    
    private void handleGameOver(){
        System.out.print("Another Game? y/n");
        String input = in.next();
    }

    @Override
    public void run() {
        while(running){
            for(Player p : players){
                playMove();
                checkGameOver();
            }
            handleGameOver();
        }
        
        System.exit(0);
    }
    
    public static class PlayerAmountException extends Exception{
        
        public PlayerAmountException() {
        }

        public PlayerAmountException(String message) {
            super(message);
        }

        public PlayerAmountException(String message, Throwable cause) {
            super(message, cause);
        }

        public PlayerAmountException(Throwable cause) {
            super(cause);
        }

        public PlayerAmountException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
            super(message, cause, enableSuppression, writableStackTrace);
        }
                
    }
    
}
