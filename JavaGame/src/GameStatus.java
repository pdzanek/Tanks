import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameStatus {
    List<Bullet> bullets;
    List<Tank> tanks;
    String chat = "";

    GameStatus() {
        bullets = new ArrayList<>();
        tanks = new ArrayList<>();
        tanks.add(new Tank(1));
        tanks.add(new Tank(2));
        tanks.add(new Tank(3));
        tanks.add(new Tank(4));
    }

    public synchronized void setTank(int connectionNumber, Color tankColor, String direction, Point position, List<Bullet> bullets) {
        this.bullets=bullets;
        for(Bullet bullet:bullets){
            System.out.println(bullet.position.x+" "+bullet.position.y);
        }
        for (Tank tank : tanks) {
            if (tank.getConnectionNumber() == connectionNumber) {
                tank.tankColor = tankColor;
                tank.direction = direction;
                tank.position = position;
            }
        }
    }
}