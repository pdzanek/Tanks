import java.awt.*;

class Block {
    private Color blockColor=Color.darkGray;
    Point position = new Point(0,0);
    int width=50;
    int height=50;

    void render(Graphics g){
        g.setColor(blockColor);
        g.fillRect(position.x,position.y,width,height);

        for(int i=0;i<height/4;i++){
            g.setColor(blockColor.darker());
            g.drawLine(position.x+i,position.y+height-i,position.x+width-1,position.y+height-i);
            g.drawLine(position.x+width-1-i,position.y+i,position.x+width-1-i,position.y+height);

            g.setColor(blockColor.brighter());
            g.drawLine(position.x,position.y+i,position.x+width-1-i,position.y+i);
            g.drawLine(position.x+i,position.y+height-i,position.x+i,position.y);
        }
    }
}
