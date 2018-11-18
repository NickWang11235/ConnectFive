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
    public static Board board;
    private Player[] players;

    public Game(Board board, Player... players) throws PlayerAmountException{
        in = new Scanner(System.in);
        if(players.length <= 1){
            throw new PlayerAmountException();
        }else{
            this.players = new Player[players.length];
            running = true;
            this.board = board;
            for(int i = 0; i < players.length; i++){
                this.players[i] = players[i];
            }
        }
    }
    
    private String[] getInput(){
        
            System.out.print("Enter row, col :");
            String input = in.next();
            input = input.trim();
            String[] inputs = input.split(",");
            
            if(inputs.length != 2){
                System.out.println("Invalid input! Enter a better value!");
                return null;
            }
            
            return inputs;
            
    }
    
    private void playMove(Player p){
        String[] inputs = getInput();
        if(inputs == null){
            playMove(p);
            return;
        }
        if(!p.playMove(Integer.valueOf(inputs[0]), Integer.valueOf(inputs[1]), p.id)){
            playMove(p);
            return;
        }
        if(board.checkGameOver(Integer.valueOf(inputs[0]), Integer.valueOf(inputs[1]),p.id)){
            running = false;
            handleGameOver();
        }
    }
     
    private void handleGameOver(){
        System.out.print("Another Game? y/n");
        String input = in.next();
        if(input.length() != 1){
            System.out.println("Please only enter 1 character \'y\' or \'n\'!");
            handleGameOver();
        }else{
            if(input.toCharArray()[0] == 'y'){
                running = true;
            }else{
                System.exit(0);
            }
        }
    }

    @Override
    public void run() {
        while(running){
            for(Player p : players){
                playMove(p);
                System.out.println(board);
            }
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
