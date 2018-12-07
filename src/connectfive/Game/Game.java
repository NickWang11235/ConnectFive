/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connectfive.Game;

import java.util.Scanner;

/**
 * This is a instance of a game, includes the board status and methods that is 
 * crucial to determining the status of a game.
 * @author nickw
 */
public class Game{
    
    //Input from console
    private static Scanner in;
        
    private static boolean running;
    public static Board board;
    
    public Game(Board board){
        in = new Scanner(System.in);
        Game.board = board;
        running = true;
    }
    
    public static boolean getState(){
        return running;
    }
    
    public static void setStage(boolean state){
        running = state;
    }
    
    /**
     * Retrieves a valid input from console
     * @return the validated input String
     */
    public static String getInput(){
            //Prompts the user for input
            System.out.print("Enter row, col :");
            String input = in.next();
            input = input.trim();
            String[] inputs = input.split(",");
            
            //Checks if the input conforms to a certain format
            if(inputs.length != 2){
                //Recursively propmts the user until a vaild input is recieved
                return getInput();
            }
            
            return input;
            
    }

    /**
     * Handles what is to be done after a game is over
     */
    public static void handleGameOver(){
        //Prompt the player for another game
        System.out.print("Another Game? y/n");
        String input = in.next();
        //Validates the input
        if(input.length() != 1){
            System.out.println("Please only enter 1 character \'y\' or \'n\'!");
            //Recursively prompt until a valid input is recieved            
            handleGameOver();
        }else{
            //If another game is wanted
            if(input.toCharArray()[0] == 'y'){
                //Start over
                board.reset();
                running = true;
            }else{
                //Terminates the program
                System.exit(0);
            }
        }
    }
            
    public static void printBoard(){
        System.out.println(board);
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
