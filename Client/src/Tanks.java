import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;

public class Tanks extends JPanel {
    public int width, height;
    Tanks thisGame = this;
    private int tickrate=30;
    static public PlayerTank player;
    private boolean isRunning = true, isPaused = false;
    private long lastUpdate;
    volatile java.util.List<OtherTank> otherTanks;
    volatile java.util.List<Bullet> bullets;
    private Block[] blocks;
    volatile boolean positionChanged;

    public Tanks(int width, int height){
        positionChanged=true;
        this.width = width;
        this.height = height;
        player=new PlayerTank(this,Client.connectionNumber);
        otherTanks=new ArrayList<>();
        otherTanks.add(new OtherTank());
        otherTanks.add(new OtherTank());
        otherTanks.add(new OtherTank());
        createBlocks(5,5);
        bullets=new ArrayList<>();
        setFocusable(true);
        run();
        this.addKeyListener(new KeyAdapter() {
            long lastBulletFired=0;
            public void keyPressed(KeyEvent e){
                if(e.getKeyCode()==KeyEvent.VK_SPACE && isRunning) {
                    if(Math.abs(lastBulletFired-System.currentTimeMillis())>1000) {
                        if(!(player.position.x>1300 && player.position.y>1300)) {
                            bullets.add(new Bullet(thisGame, player.direction, player.position));
                            positionChanged=true;
                            lastBulletFired = System.currentTimeMillis();
                        }
                    }
                }
                if(e.getKeyCode()==KeyEvent.VK_ESCAPE) togglePause();
                if(e.getKeyCode()==KeyEvent.VK_Q) quit();
                if(e.getKeyCode()==KeyEvent.VK_UP){
                    if(player.position.y<-330){}
                    else player.position.y=player.position.y-5;
                    player.direction="up";
                    positionChanged=true;
                }
                if(e.getKeyCode()==KeyEvent.VK_DOWN){
                    if(player.position.y>300){}
                    else player.position.y=player.position.y+5;
                    player.direction="down";
                    positionChanged=true;
                }
                if(e.getKeyCode()==KeyEvent.VK_LEFT){
                    if(player.position.x<-610){}
                    else player.position.x=player.position.x-5;
                    player.direction="left";
                    positionChanged=true;
                }
                if(e.getKeyCode()==KeyEvent.VK_RIGHT){
                    if(player.position.x>605){}
                    else player.position.x=player.position.x+5;
                    player.direction="right";
                    positionChanged=true;
                }
            }
        });
    }

    public void run() {
        gameThread.start();
    }

    public void togglePause() {
        isPaused = !isPaused;
    }

    public void quit() {
        isRunning=false;
    }

    Thread gameThread = new Thread(() -> {
        //init
        isRunning = true;
        isPaused = false;
        lastUpdate=System.nanoTime();

        //gameloop
        while (isRunning) {
            try {
                if (isPaused) {
                    lastUpdate=System.nanoTime();
                    Thread.sleep(1);
                } else {
                    tick();
                    lastUpdate=System.nanoTime();
                    Thread.sleep((long)(1000.0/tickrate));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    });

    private void createBlocks(int rows, int columns) {
        blocks = new Block[rows * columns];
        int gap = 178;
        float h =50;
        float w = ((float) width / columns/5);
        for (int x = 0; x < columns; x++) {
            for (int y = 0; y < rows; y++) {
                Block b = new Block();
                b.position.x=(int)(x*(w+gap*1.1)-width/2+100);
                b.position.y=(int)(y*(h+gap)-height/2+100);
                b.height=(int)h;
                b.width=(int)w;

                blocks[y * columns + x]=b;
            }
        }
    }


    public void tick(){
        double deltatime = (System.nanoTime()-lastUpdate)/1000000.0;
        for(Bullet b:bullets)
        b.tick(deltatime);
        repaint();
    }

        public void paint(Graphics g) {
            g.setColor(Color.gray);
            g.fillRect(0, 0, width, height);
            g.setColor(Color.black);
            if(!isRunning) {
                g.drawString("Game is not running", 100,100);
            }else if (isPaused) {
                g.drawString("Game Paused", 100,100);
            }

            g.translate(width/2,height/2);
            if(blocks!=null){
                for(Block b:blocks) b.render(g);
            }
            try {
                player.render(g);
                for(int i=0;i<otherTanks.size();i++)
                    otherTanks.get(i).render(g);
            }catch(Exception e){
                System.out.println(e.getMessage());
            }

                //check if bullet exists
                for (Iterator<Bullet> iterator = bullets.iterator(); iterator.hasNext(); ) {
                    Bullet b = iterator.next();
                    if (b.position.equals(new Point(1700,1700))) {
                        iterator.remove();
                    }else b.render(g);
            }
            g.setFont(new Font("TimesRoman", Font.PLAIN, 70));
            if(player.position.x>1300 && player.position.y>1300) g.drawString("Your tank is destroyed", -400,-000);
        }

    public void start() {
    }
}
