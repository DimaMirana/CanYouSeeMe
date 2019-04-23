package Game.DisplaySettings;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;

public class displayGame
{
    private JFrame frame;
    private Canvas canvas;
    private String title;
    private int height;
    private int width;
    private JButton btn;
    
    public displayGame (String title , int width , int height)
    {
        this.title = title;
        this.width = width;
        this.height = height;
        
        createDisplay();
    }

    public void createDisplay()
    {
        frame = new JFrame(title);
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        //frame.setVisible(false);
        
        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(width, height));
        canvas.setMaximumSize(new Dimension(width, height));
        canvas.setMinimumSize(new Dimension(width, height));
        canvas.setFocusable(false);//aitar kaaj ki?
        canvas.setBackground(Color.cyan);
        frame.add(canvas);
        frame.pack();//aita ki?
        
    }
    
    public Canvas getCanvas()
    {
        return canvas;
    }
    
    public JFrame getFrame()
    {
        return frame;
    }
    
    public void setVisible(boolean visible)
    {
        frame.setVisible(visible);
    }
    
    public void colorEffect()//ai fntn er kaaj ki?
    {
        canvas.setBackground(Color.BLACK);
        try {
            Thread.sleep(50);
        } catch (InterruptedException ex) {
            Logger.getLogger(displayGame.class.getName()).log(Level.SEVERE, null, ex);
        }
        canvas.setBackground(Color.cyan);
        
        
    }
}
