import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ProtocolPacket implements Serializable {
    private static final long serialVersionUID = 6529685098267757690L;
    String message;
    Point position;
    int positionX;
    int positionY;
    String direction="";
    Color tankColor=null;
    int connectionNumber;
    List<Point> positions;
    List<String> directions;
    List<Color> colors;
    List<Bullet> bullets;

    public ProtocolPacket(String message){
        this.message=message;
    }

    public ProtocolPacket(GameStatus gs,int connectionNumber){
        positions=new ArrayList<>();
        directions=new ArrayList<>();
        colors = new ArrayList<>();
        for (Tank tank :gs.tanks
             ) {
            if(tank.connectionNumber!=connectionNumber) {
                positions.add(tank.position);
                directions.add(tank.direction);
                colors.add(tank.tankColor);
            }
        }
    }
    public String getMessage(){
        return message;
    }
}
