/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package connectfive;

import java.net.*;
import java.io.*;

/**
 * A HostPlayer is specific to the player that host the game.
 * The Host hosts the java.net.ServerSocket and listens for client request to 
 * open a channel, then proceed to the game.
 * @author nickw
 */
public class HostPlayer implements Player{
    
    //Indicates which player is playing. 
    public boolean status;
    
    //Server end socket
    private ServerSocket server;
    //Client socket
    private Socket client;
    //Reads input from client socket
    BufferedReader in;
    //Writes output to client socket
    PrintWriter out;
    
    public HostPlayer(int port){
        //Host's player gets first move
        status = true;
        
        try{
            //Open socket on port
            server = new ServerSocket(port);
            //Wait for client request to create the client socket
            client = server.accept();
            //Get I/O stream from client socket
            in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            out = new PrintWriter(client.getOutputStream(), true);
        }catch(IOException e){
            System.out.println("Error while communicating with client");
        }
        
    }
    
    /**
     * Closes all I/O streams
     */
    @Override
    public void close(){
        try{
            in.close();
            out.close();
        }catch(IOException e){
            System.out.println("Error closing I/O stream");
        }
    }
    
    /**
     * Play a specific move by setting the indexed position on board to a specified value
     * @param row
     * @param col
     * @param num the value to set the board to
     * @return whether this play is successful, false if position is occupied
     */
    public boolean playMove(int row, int col, int num){
        try{
            if(Game.board.getBoard()[row][col] == 0){
                //If its unoccupied, proceed
                Game.board.set(row, col, num);
                return true;
            }else{
                //If occupied
                System.out.println("Spot Already Taken!");
                return false;
            }
        }catch(ArrayIndexOutOfBoundsException | NumberFormatException e){
            //If invalid position or invalid format
            return false;
        }
    }    

    /**
     * Defines all behavior of HostPlayer while the game is running, including 
     * playing a move, waiting, determining winner, etc
     */
    @Override
    public void play() {
        //If it's HostPlayer's turn
        if(status){
            //Get a valid input from player and process it
            String input = Game.getInput();
            String[] inputs = input.split(",");
            
            //Play a move, if it's unoccupied
            if(playMove(Integer.parseInt(inputs[0]), 
                        Integer.parseInt(inputs[1]), 
                        Launcher.SERVER_HOST_ID)){
                Game.printBoard();
                //Check if game has ended
                if(Game.board.checkGameOver(Integer.parseInt(inputs[0]), 
                   Integer.parseInt(inputs[1]), 
                   Launcher.SERVER_HOST_ID)){
                    Game.setStage(false);
                    Game.handleGameOver();
                }
                //Sends to the client a valid input
                out.println(input);
                //Hands turn to Client
                status = false;
            }else{
                //Recursively play until a valid move is made
                play();
            }
        //If it's ClientPlayer's turn
        }else{
            try {
                String input;
                //Reading input from client
                while((input = in.readLine()) != null){
                    //Recieves a valid input from the client
                    String[] inputs = input.split(",");
                    if(inputs.length == 2){
                        playMove(Integer.parseInt(inputs[0]), 
                                 Integer.parseInt(inputs[1]), 
                                 Launcher.CLIENT_ID);
                        Game.printBoard();
                        if(Game.board.checkGameOver(Integer.parseInt(inputs[0]), 
                                                    Integer.parseInt(inputs[1]), 
                                                    Launcher.CLIENT_ID)){
                            Game.setStage(false);
                            Game.handleGameOver();
                        }
                        //Hands the turn over to Host
                        status = true;
                        //Once a cient move has been made, stop reading from stream
                        break;
                    }
                }
            }catch(IOException e){
                System.out.println("Error reading input from client");
            }
        }
    }

    @Override
    public int getID() {
        return Launcher.SERVER_HOST_ID;
    }
    
}
