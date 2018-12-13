/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package connectfive;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Launches and sets up the JFrame
 * @author nickw
 */
public class GUILauncher extends JFrame{
    
    public static final int BLOCK_SIZE = 20;
    
    public static GUILauncher GUI;

    public GUILauncher(){
        super(Launcher.GAME_NAME + " - Player "+CMDLauncher.CMD.p.getID());
        addMouseListener();
        //Terminates the program when the frame is closed. This 
        //should be adjusted with concurency
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        //Center the frame
        setLocationRelativeTo(null);
        //Set the frame visible and displays it
        setVisible(true);
        add(new Panel());
        //Resize the frame
        pack();
        GUI = this;
    }
    
    private void addMouseListener(){
        addMouseListener(new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent e){
                Game.board.set(e.getX()/BLOCK_SIZE, e.getY()/BLOCK_SIZE, CMDLauncher.CMD.p.getID());
            }
        });
    }
    

    private class Panel extends JPanel{
        
        private Graphics g;
        
        public Panel(){
            g = getGraphics();
        }
        
        @Override
        public Dimension getPreferredSize(){
            return new Dimension(BLOCK_SIZE*Launcher.GAME_WIDTH, BLOCK_SIZE*Launcher.GAME_HEIGHT);
        }
        
        @Override
        protected void paintComponent(Graphics g){
            super.paintComponent(g);
            for(int i = 0; i < 18; i++){
                for(int j = 0; j < 18; j++){
                    if((i+j)%2 == 0){
                        g.setColor(Color.BLACK);
                    }
                    else{
                        g.setColor(Color.WHITE);
                    }
                    g.fillRect(i*BLOCK_SIZE, j*BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);
                }
            }
        }
        
        public void updateGUI(){
            int[][] board = Game.board.getBoard();
            for(int i = 0; i < 18; i++){
                for(int j = 0; j < 18; j++){
                    switch(board[i][j]){
                        case Launcher.SERVER_HOST_ID:
                            g.setColor(Color.RED);
                            g.fillRect(i*BLOCK_SIZE, j*BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);
                            break;
                        case Launcher.CLIENT_ID:
                            g.setColor(Color.YELLOW);
                            g.fillRect(i*BLOCK_SIZE, j*BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);
                            break;
                        default:
                            break;
                    }
                }
            }
        }
        
    }

}

