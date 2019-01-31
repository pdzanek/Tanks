import javax.swing.*;
import java.awt.*;

public class Chat extends JPanel {
    private int width,height;
    public Chat(int width, int height){
        this.width=width;
        this.height=height;
    }
    public void paint(Graphics g){
        g.setColor(Color.red);
        g.fillRect(0,0,width,height);
    }
}
