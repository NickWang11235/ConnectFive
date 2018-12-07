/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connectfive.Player;

import connectfive.Game.Game;
import connectfive.Launcher.Launcher;
import java.net.*;
import java.io.*;

/**
 * A ClientPlayer is specific to the player that connects to a host.
 * The Client only hosts one Socket that reads and writes to the Host
 * @author nickw
 */
public class ClientPlayer implements Player{
 
    //Indicates which player is playing. 
    public boolean status;
    //Client end socket
    private Socket client;
    //Reads input from server socket
    private BufferedReader in;
    //Writes output to server socket
    private PrintWriter out;
    
    public ClientPlayer(String host, int port) {
        //Client player gets second move
        status = false;

        try{
            //Connect to socket at host on port
            client = new Socket(host, port);
            //Get I/O stream from socket
            in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            out = new PrintWriter(client.getOutputStream(), true);
        }catch(UnknownHostException e){
            System.out.printf("Unable to connect host %s on port %d", host, port);
        }catch(IOException e){
            System.out.println("Error while communicating to client");
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

    @Override
    public void play() {
        //If it's ClientPlayer's turn
        if(status){
            //Get a valid input from player and process it
            String input = Game.getInput();
            String[] inputs = input.split(",");
            
            //Play a move, if it's unoccupied
            if(playMove(Integer.parseInt(inputs[0]), Integer.parseInt(inputs[1]), Launcher.CLIENT_ID)){
                Game.printBoard();
                //Check if game has ended
                if(Game.board.checkGameOver(Integer.parseInt(inputs[0]), Integer.parseInt(inputs[1]), Launcher.CLIENT_ID)){
                    Game.setStage(false);
                    Game.handleGameOver();
                }
                //Sends to the host a valid input
                out.println(input);
                //Hands turn to Host
                status = false;
            }else{
                //Recursively play until a valid move is made
                play();
            }
        //If it's HostPlayer's turn
        }else{
            try {
                String input;
                //Reading input from host
                while((input = in.readLine()) != null){
                    //Recieves a valid input from the client
                    String[] inputs = input.split(",");
                    if(inputs.length == 2){
                        playMove(Integer.parseInt(inputs[0]), Integer.parseInt(inputs[1]), Launcher.SERVER_HOST_ID);
                        Game.printBoard();
                        if(Game.board.checkGameOver(Integer.parseInt(inputs[0]), Integer.parseInt(inputs[1]), Launcher.CLIENT_ID)){
                            Game.setStage(false);
                            Game.handleGameOver();
                        }
                        //Hands the turn over to Client
                        status = true;
                        //Once a move has been made, stop reading from stream
                        break;
                    }
                }
            }catch(IOException e){
                System.out.println("Error reading input from host");
            }
        }
    }
}
