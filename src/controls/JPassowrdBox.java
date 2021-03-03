
package controls;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPasswordField;


public class JPassowrdBox extends JPasswordField{
    public JPassowrdBox(int size){
        super(size);
        setOpaque(false);
    }
    @Override
    protected void paintComponent(Graphics g){
        g.setColor(getBackground());
        g.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, 20, 20);
        super.paintComponent(g);
    }
    
    @Override
    protected void paintBorder(Graphics g){
        g.setColor(Color.BLUE);
        g.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 20, 20);
        super.paintComponent(g);
    }
    
}