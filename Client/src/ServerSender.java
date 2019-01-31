import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class ServerSender implements Runnable {
    private ObjectOutputStream oos;
    private ObjectInputStream ois;
    Boolean shouldRun=true;
    Tanks game;
    ServerSender(Tanks game, ObjectOutputStream oos, ObjectInputStream ois){
        this.oos=oos;
        this.ois=ois;
        this.game=game;
    }
    @Override
    public void run() {
        System.out.println("Sender: Thread Running");
        int i = 0;
        while (shouldRun) {
            i++;
            try {
                if (game.positionChanged) {
                    game.positionChanged = false;
                   ProtocolPacket packet = new ProtocolPacket(game.bullets,game.player,"chat message");
                    oos.writeObject(packet);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}