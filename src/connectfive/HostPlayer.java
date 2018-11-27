/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connectfive;

import java.net.*;
import java.io.*;

/**
 *
 * @author nickw
 */
public class HostPlayer implements Player{
    
    public boolean status;
    private ServerSocket server;
    private Socket client;
    BufferedReader in;
    PrintWriter out;
    
    public HostPlayer(int port){
        status = true;
        
        try{
            server = new ServerSocket(port);
            client = server.accept();
            in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            out = new PrintWriter(client.getOutputStream(), true);
        }catch(IOException e){
            System.out.println("Error while communicating with client");
        }
        
    }
    
    @Override
    public void close(){
        try{
            in.close();
            out.close();
        }catch(IOException e){
            System.out.println("Error closing I/O stream");
        }
    }
    
    public boolean playMove(int row, int col, int num){
        try{
            if(Game.board.getBoard()[row][col] == 0){
                Game.board.set(row, col, num);
                return true;
            }else{
                System.out.println("Spot Already Taken!");
                return false;
            }
        }catch(ArrayIndexOutOfBoundsException | NumberFormatException e){
            return false;
        }
    }    

    @Override
    public void play() {
        if(status){
            String input = Game.getInput();
            String[] inputs = input.split(",");
            
            if(playMove(Integer.parseInt(inputs[0]), Integer.parseInt(inputs[1]), ConnectFive.SERVER_HOST_ID)){
                Game.printBoard();
                if(Game.board.checkGameOver(Integer.parseInt(inputs[0]), Integer.parseInt(inputs[1]), ConnectFive.SERVER_HOST_ID)){
                    Game.setStage(false);
                    Game.handleGameOver();
                }
                out.println(input);
                status = false;
            }else{
                play();
            }
        }else{
            try {
                String input;
                while((input = in.readLine()) != null){
                    String[] inputs = input.split(",");
                    if(inputs.length == 2){
                        playMove(Integer.parseInt(inputs[0]), Integer.parseInt(inputs[1]), ConnectFive.CLIENT_ID);
                        Game.printBoard();
                        if(Game.board.checkGameOver(Integer.parseInt(inputs[0]), Integer.parseInt(inputs[1]), ConnectFive.CLIENT_ID)){
                            Game.setStage(false);
                            Game.handleGameOver();
                        }
                        status = true;
                        break;
                    }
                }
            }catch(IOException e){
                System.out.println("Error reading input from client");
                System.exit(0);
            }
        }
    }
    
}
