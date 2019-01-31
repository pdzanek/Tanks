import java.awt.*;
import java.io.Serializable;
import java.util.List;

public class ProtocolPacket implements Serializable {
    private static final long serialVersionUID = 6529685098267757690L;
    String message;
    int positionX;
    int positionY;
    String direction;
    Color tankColor;
    int connectionNumber;
    java.util.List<Point> positions;
    java.util.List<String> directions;
    List<Color> colors;
    List<Bullet> bullets;

    public ProtocolPacket(String message){
        this.message=message;
    }
    public ProtocolPacket(List<Bullet> bullets,PlayerTank playerTank, String message){
        this.positionX= playerTank.position.x;
        this.positionY=playerTank.position.y;
        this.direction=playerTank.direction;
        this.tankColor=playerTank.tankColor;
        this.connectionNumber=playerTank.connectionNumber;
        this.message=message;
        this.bullets=bullets;

    }
    public String getMessage(){
        return message;
    }

}
