import java.awt.*;

public class PlayerTank {
    private Tanks game;
    int height = 40;
    int width = 40;
    volatile String direction;
    volatile Point position;
    static int connectionNumber;
    static Color tankColor;

    public PlayerTank(Tanks game, int connectionNumber) {
        this.game = game;
        this.connectionNumber = connectionNumber;
        if (connectionNumber == 1) {
            this.position = new Point(610, 305);
            this.direction = "up";
            this.tankColor = Color.black;
        } else if (connectionNumber == 2) {
            this.position = new Point(-615, 305);
            this.direction = "right";
            this.tankColor = Color.red;
        } else if (connectionNumber == 3) {
            this.position = new Point(-615, -335);
            this.direction = "down";
            this.tankColor = Color.green;
        } else {
            this.position = new Point(610, -335);
            this.direction = "left";
            this.tankColor = Color.pink;
        }
    }


    public void render(Graphics g) throws Exception {

        g.setColor(tankColor);
        g.fillRect(position.x - width / 2, position.y - height / 2, width, height);
        switch (direction) {
            case "left":
                g.fillRect(position.x - width / 2 - 20, position.y - height / 2 + 16, width, height / 8);
                break;
            case "right":
                g.fillRect(position.x - width / 2 + 20, position.y - height / 2 + 16, width, height / 8);
                break;
            case "up":
                g.fillRect(position.x - width / 2 + 18, position.y - height / 2 - 20, width / 8, height);
                break;
            case "down":
                g.fillRect(position.x - width / 2 + 18, position.y - height / 2 + 20, width / 8, height);
                break;
            default: {
                throw new Exception("Wrong direction Exception");
            }
        }
    }
}