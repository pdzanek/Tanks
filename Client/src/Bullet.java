import java.awt.*;
public class Bullet {
    private Tanks game;
    private int radius=5;
    Point position=new Point(0,0);
    Point movement;
    float speed=0.3f;

    public Bullet(Tanks game,String direction, Point position) {
        this.game = game;
        if (direction == "up") {
            movement = new Point(0, -1);
            this.position.translate(position.x + movement.x * radius / 2, position.y + movement.x * radius / 2-game.player.height);
        } else if (direction == "down") {
            movement = new Point(0, 1);
            this.position.translate(position.x + movement.x * radius / 2, position.y + movement.x * radius / 2+game.player.height);
        } else if (direction == "right") {
            movement = new Point(1, 0);
            this.position.translate(position.x+movement.x*radius/2+game.player.width,position.y+movement.y*radius/2-2);
        } else if (direction == "left"){
            movement = new Point(-1, 0);
            this.position.translate(position.x+movement.x*radius/2-game.player.width,position.y+movement.y*radius/2-2); }
        }
     public Bullet(Tanks game,Point position,Point movement){
        this.game=game;
        this.position=position;
        this.movement=movement;
     }

    public void tick(double deltatime){
       position.translate((int)(movement.x*(speed*deltatime)),(int)(movement.y*(speed*deltatime)));
       if(Math.abs(position.x)>Math.abs(game.width/2)) movement.x=0;
       if(Math.abs(position.y)>Math.abs(game.height/2)) movement.y=0;
       Rectangle playerHitbox = new Rectangle(game.player.position.x-game.player.width/2,game.player.position.y-game.player.height/2,game.player.width,game.player.height);
       Rectangle ballHitbox = new Rectangle(position.x-radius,position.y-radius, radius*2,radius*2);
       if(playerHitbox.intersects(ballHitbox)) {
           game.player.position.translate(1600, 1600);
           game.change=true;
       }
    }
    public void render(Graphics g){
        if(movement.x!=0 || movement.y!=0) {
            g.setColor(Color.white);
            g.fillOval(position.x - radius, position.y - radius, radius * 2, radius * 2);
        }
        else this.position=new Point(1700,1700);
    }
}
