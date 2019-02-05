import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;

public class Tanks extends JPanel {
    int width, height;
    private Tanks thisGame = this;
    private int tickrate=30;
    static PlayerTank player;
    boolean isRunning = true, isPaused = false;
    private long lastUpdate;
    volatile java.util.List<OtherTank> otherTanks;
    volatile java.util.List<Bullet> bullets;
    Block[] blocks;
    volatile boolean change;
    volatile boolean isBulletFired=false;
    volatile Bullet bulletFired;
    volatile public String messageToSend="";
    Chat chat;

    Tanks(int width, int height){
        change=true;
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
                            bulletFired= new Bullet(thisGame,player.direction,player.position);
                            change=true;
                            isBulletFired=true;
                            lastBulletFired = System.currentTimeMillis();
                        }
                    }
                }
                if(e.getKeyCode()==KeyEvent.VK_ESCAPE) togglePause();
                if(e.getKeyCode()==KeyEvent.VK_Q) quit();
                if(e.getKeyCode()==KeyEvent.VK_UP){
                    if (player.position.y >= -330) {
                        player.position.y=player.position.y-5;
                    }
                    player.direction="up";
                    change=true;
                }
                if(e.getKeyCode()==KeyEvent.VK_DOWN){
                    if (player.position.y <= 300) {
                        player.position.y=player.position.y+5;
                    }
                    player.direction="down";
                    change=true;
                }
                if(e.getKeyCode()==KeyEvent.VK_LEFT){
                    if (player.position.x >= -610) {
                        player.position.x=player.position.x-5;
                    }
                    player.direction="left";
                    change=true;
                }
                if(e.getKeyCode()==KeyEvent.VK_RIGHT){
                    if (player.position.x <= 605) {
                        player.position.x=player.position.x+5;
                    }
                    player.direction="right";
                    change=true;
                }
            }
        });
    }

    private void run() {
        gameThread.start();
    }

    void togglePause() {
        isPaused = !isPaused;
    }

    void quit() {
        isRunning=false;
    }

    private Thread gameThread = new Thread(() -> {
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

    void createBlocks(int rows, int columns) {
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


    private void tick(){
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
        }

    void start() {
    }
    void setChat(Chat chat){
        this.chat=chat;
    }
}
