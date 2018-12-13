/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package connectfive;

/**
 * This is the launcher of the ConnectFive with GUI. It launches two threads, 
 * one to handle the backend calculations, one to display the GUI.
 * @author nickw
 */
public class Launcher {

    //Static ID of the server host player and client marker
    public static final int SERVER_HOST_ID = 1;
    public static final int CLIENT_ID = 2;
    //Static variables including JFrame title and size
    public static final String GAME_NAME = "ConnectFive Test";
    public static final int GAME_HEIGHT = 18;
    public static final int GAME_WIDTH = 18;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {        

        //Launches and starts CMD thread
        Thread t1 = new Thread(new CMDLauncher(args));
        t1.start();
        //Launches a second thread with invokeLater to work specifically with 
        //awt and swing components
        java.awt.EventQueue.invokeLater(() -> new GUILauncher());
            
        
    }
    
}
