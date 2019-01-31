import java.awt.*;

public class OtherTank {
    Point position = new Point(1300, 1300);
    int height = 40;
    int width = 40;
    String direction="up";
    Color tankColor=Color.WHITE;

   public void setPositionColorAndDirection(Point position, Color tankColor, String direction) {
        this.position=position;
        this.tankColor=tankColor;
        this.direction=direction;
    }

    public void render(Graphics g) throws Exception {
        g.setColor(tankColor);
        g.fillRect(position.x - width / 2, position.y - height / 2, width, height);
        switch (this.direction) {
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
