import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameStatus {
    List<Bullet> bullets;
    List<Tank> tanks;
    List<Message> messages;

    GameStatus() {
        bullets = new ArrayList<>();
        tanks = new ArrayList<>();
        messages = new ArrayList<>();
        tanks.add(new Tank(1));
        tanks.add(new Tank(2));
        tanks.add(new Tank(3));
        tanks.add(new Tank(4));
        bullets.add(new Bullet(1));
        bullets.add(new Bullet(2));
        bullets.add(new Bullet(3));
        bullets.add(new Bullet(4));
        messages.add(new Message(1));
        messages.add(new Message(2));
        messages.add(new Message(3));
        messages.add(new Message(4));
    }

    public synchronized void setTankWithBullet(int connectionNumber, Color tankColor, String direction, Point position, Point bulletPosition, Point bulletMovement) {
        for (Tank tank : tanks) {
            if (tank.getConnectionNumber() == connectionNumber) {
                tank.tankColor = tankColor;
                tank.direction = direction;
                tank.position = position;
            }
        }
        for (Bullet bullet : bullets) {
            bullet.position = bulletPosition;
            bullet.movement = bulletMovement;
        }
    }

    public synchronized void setTank(int connectionNumber, Color tankColor, String direction, Point position) {
        for (Tank tank : tanks) {
            if (tank.getConnectionNumber() == connectionNumber) {
                tank.tankColor = tankColor;
                tank.direction = direction;
                tank.position = position;
            }
        }
    }

    public synchronized boolean checkForBullet(int connectionNumber) {
        Boolean returnedValue = false;
        for (Bullet bullet : bullets) {
            if (bullet.getConnectionNumber() == connectionNumber) {
                if (bullet.position.x == 0 && bullet.position.y == 0) returnedValue = false;
                else returnedValue = true;
            }
        }
        return returnedValue;
    }

    synchronized int getBulletPosX(int connectionNumber) {
        for (Bullet bullet : bullets) {
            if (bullet.getConnectionNumber() == connectionNumber) {
                return bullet.position.x;
            }
        }
        return 0;
    }

    synchronized int getBulletPosY(int connectionNumber) {
        for (Bullet bullet : bullets) {
            if (bullet.getConnectionNumber() == connectionNumber) {
                return bullet.position.y;
            }
        }
        return 0;
    }

    synchronized int getBulletMovX(int connectionNumber) {
        for (Bullet bullet : bullets) {
            if (bullet.getConnectionNumber() == connectionNumber) {
                return bullet.movement.x;
            }
        }
        return 0;
    }

    synchronized int getBulletMovY(int connectionNumber) {
        for (Bullet bullet : bullets) {
            if (bullet.getConnectionNumber() == connectionNumber) {
                return bullet.movement.y;
            }
        }
        return 0;
    }

    synchronized int eraseBullet(int connectionNumber) {
        for (Bullet bullet : bullets) {
            if (bullet.getConnectionNumber() == connectionNumber) {
                bullet.movement = new Point(0, 0);
                bullet.position = new Point(0, 0);
            }
        }
        return 0;
    }

    public void setMessages(String message) {
        for (Message mes : messages) {
            mes.message = message;
        }
    }

    synchronized String getMessage(int connectionNumber) {
        String returnedValue = "";
        for (Message mes : messages) {
            if (mes.getConnectionNumber() == connectionNumber) {
                if (mes.message.equals("")) returnedValue="";
                else {
                    returnedValue =mes.message;
                    mes.message = "";
                }
            }
        }
        return returnedValue;
    }
}

