/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connectfive;

import java.io.*;
import java.net.*;
import java.util.Scanner;

/**
 *
 * @author nickw
 */
public class Game{
    
    private static Scanner in;
        
    private static boolean running;
    public static Board board;
    
    public Game(Board board){
        in = new Scanner(System.in);
        this.board = board;
        running = true;
    }
    
    public static boolean getState(){
        return running;
    }
    
    public static void setStage(boolean state){
        running = state;
    }
    
    public static String getInput(){
        
            System.out.print("Enter row, col :");
            String input = in.next();
            input = input.trim();
            String[] inputs = input.split(",");
            
            if(inputs.length != 2){
                return getInput();
            }
            
            return input;
            
    }

    public static void handleGameOver(){
        System.out.print("Another Game? y/n");
        String input = in.next();
        if(input.length() != 1){
            System.out.println("Please only enter 1 character \'y\' or \'n\'!");
            handleGameOver();
        }else{
            if(input.toCharArray()[0] == 'y'){
                board.reset();
                running = true;
            }else{
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
