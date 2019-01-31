import java.awt.*;

public class Tank {
    Point position= new Point(1300, 1300);
    String direction="up";
    Color tankColor=Color.WHITE;
    int connectionNumber;
    Tank(int connectionNumber){
        this.connectionNumber=connectionNumber;
    }
    public int getConnectionNumber(){
        return connectionNumber;
    }
}