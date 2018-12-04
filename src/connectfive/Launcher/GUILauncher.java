/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connectfive.Launcher;

import java.awt.Canvas;
import java.awt.Dimension;
import javax.swing.JFrame;

/**
 * Launches and sets up the JFrame
 * @author nickw
 */
public class GUILauncher extends JFrame{
    
    //Lower level container to draw on
    private Canvas canvas;

    public GUILauncher(String name, int width, int height){
        super(name);
        setSize(width, height);
        
        //Initializes canvas
        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(width, height));
        canvas.setMaximumSize(new Dimension(width, height));
        canvas.setMinimumSize(new Dimension(width, height));
        canvas.setFocusable(false); 
         
        //Terminates the program when the frame is closed. This 
        //should be adjusted with concurency
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        //Center the frame
        setLocationRelativeTo(null);
        //Set the frame visible and displays it
        setVisible(true);
        add(canvas);
        //Resize the frame
        pack();
        
    }
    
}
