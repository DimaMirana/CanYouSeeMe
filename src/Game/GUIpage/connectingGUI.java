
package Game.GUIpage;

import java.awt.Color;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;



public class connectingGUI extends JFrame
{
    JPanel panel;
    JLabel image = new JLabel();
    JLabel text = new JLabel();
    ImageIcon ic;
    
    public connectingGUI()
    {
        panel = (JPanel) getContentPane();
        setSize(405, 330);
        setTitle("Connecting...");
        ic = new ImageIcon(this.getClass().getResource("connecting.gif"));
        image.setIcon(ic);
        panel.add(image , java.awt.BorderLayout.CENTER);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);
        text.setText("            Waiting for 2nd Player...");
        text.setForeground(Color.YELLOW);
        text.setFont(new java.awt.Font("Comic Sans MS" , Font.BOLD , 18 ));
        panel.add(text , java.awt.BorderLayout.SOUTH);
        panel.setBackground(Color.RED);
    }
    
}
