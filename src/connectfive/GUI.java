/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connectfive;

import java.awt.Canvas;
import java.awt.Dimension;
import javax.swing.JFrame;

/**
 *
 * @author nickw
 */
public class GUI {
     private JFrame frame;
    //The game(Character, Map ,etc) will be drawn on this using graphics 
    //components
    private Canvas canvas;
    
    private final String title;
    private final int width;
    private final int height;
    
    /**
     * 
     * @param title The name will be displayed on the frame
     * @param width
     * @param height 
     */
    public GUI(String title, int width, int height){
        this.title = title;
        this.height = height;
        this.width = width;
        
        createDisplay();
    }
    
    /**
     * Set attributes of the frame and canvas
     */
    private void createDisplay(){
        frame = new JFrame(title);
        frame.setSize(width,height);
        
        //Terminates the program when the frame is closed. This 
        //should be adjusted with concurency
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        
        //Center the frame
        frame.setLocationRelativeTo(null);
        
        //Set the frame visible and displays it
        frame.setVisible(true);
        
        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(width, height));
        canvas.setMaximumSize(new Dimension(width, height));
        canvas.setMinimumSize(new Dimension(width, height));
        canvas.setFocusable(false); 
        
        frame.add(canvas);
        
        //Resize the frame
        frame.pack();
    }
    
    public Canvas getCanvas(){
        return canvas;
    }
    
    public JFrame getFrame(){
        return frame;
    }
}
