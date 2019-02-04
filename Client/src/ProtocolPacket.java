import java.awt.*;
import java.io.Serializable;
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
    public ProtocolPacket(PlayerTank playerTank,Bullet bulletFired, String message) {
        this.positionX = playerTank.position.x;
        this.positionY = playerTank.position.y;
        this.direction = playerTank.direction;
        this.tankColor = playerTank.tankColor;
        this.connectionNumber = playerTank.connectionNumber;
        this.message = message;
        this.bulletPositionX=bulletFired.position.x;
        this.bulletPositionY=bulletFired.position.y;
        this.bulletMovementX=bulletFired.movement.x;
        this.bulletMovementY=bulletFired.movement.y;
    }

        public ProtocolPacket(PlayerTank playerTank, String message){
            this.positionX = playerTank.position.x;
            this.positionY = playerTank.position.y;
            this.direction = playerTank.direction;
            this.tankColor = playerTank.tankColor;
            this.connectionNumber = playerTank.connectionNumber;
            this.message = message;

    }
    public String getMessage(){
        return message;
    }

}
