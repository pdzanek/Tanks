import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class ServerSender implements Runnable {
    private ObjectOutputStream oos;
    private ObjectInputStream ois;
    Boolean shouldRun=true;
    private Tanks game;
    ServerSender(Tanks game, ObjectOutputStream oos, ObjectInputStream ois){
        this.oos=oos;
        this.ois=ois;
        this.game=game;
    }
    @Override
    public void run() {
        ProtocolPacket packet;
        System.out.println("Sender: Thread Running");
        while (shouldRun) {
            try {
                if (game.change) {
                    game.change = false;
                   if(game.isBulletFired) {
                       packet = new ProtocolPacket(game.player,game.bulletFired,game.messageToSend);
                       game.messageToSend="";
                   }else{
                       packet = new ProtocolPacket(game.player,game.messageToSend);
                       game.messageToSend="";
                   }
                    game.isBulletFired=false;
                    oos.writeObject(packet);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}