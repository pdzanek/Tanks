import java.awt.*;

public class Bullet {
    Point position;
    Point movement;
    int connectionNumber;
    Bullet(Point position,Point movement){
        this.position=position;
        this.movement=movement;
    }
    Bullet(int connectionNumber){
        this.connectionNumber=connectionNumber;
        this.position=new Point(0,0);
        this.movement=new Point(0,0);
    }
    public int getConnectionNumber(){
        return connectionNumber;
    }
}
