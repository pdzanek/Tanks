import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ProtocolPacket implements Serializable {
    private static final long serialVersionUID = 6529685098267757690L;
    String message="";
    int positionX;
    int positionY;
    String direction;
    Color tankColor;
    int connectionNumber;
    java.util.List<Point> positions;
    java.util.List<String> directions;
    List<Color> colors;
    int bulletPositionX=0;
    int bulletPositionY=0;
    int bulletMovementX=0;
    int bulletMovementY=0;

    public ProtocolPacket(String message){
        this.message=message;
    }

    public ProtocolPacket(GameStatus gs,int bulletPositionX, int bulletPositionY, int bulletMovementX, int bulletMovementY,int connectionNumber, String message){
        this.message=message;
        this.bulletPositionX=bulletPositionX;
        this.bulletPositionY=bulletPositionY;
        this.bulletMovementX=bulletMovementX;
        this.bulletMovementY=bulletMovementY;
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
