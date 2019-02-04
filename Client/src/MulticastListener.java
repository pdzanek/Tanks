import java.awt.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class MulticastListener implements Runnable {
    private ObjectOutputStream oos;
    private ObjectInputStream ois;
    Boolean shouldRun = true;
    Tanks game;

    MulticastListener(Tanks game, ObjectOutputStream oos, ObjectInputStream ois) {
        this.oos = oos;
        this.ois = ois;
        this.game = game;
    }

    @Override
    public void run() {
        System.out.println("Listener: Thread Running");
        ProtocolPacket packetReceived;
        while (shouldRun) {
            try {
                packetReceived = null;
                packetReceived = (ProtocolPacket) ois.readObject();
                if (packetReceived.positions != null) {
                    if(game.chat!=null){
                        if(!packetReceived.message.equals("")) {
                            game.chat.text += packetReceived.message+'\n';
                            game.chat.makeTextAreaGreatAgain();
                        }
                    }
                    if (packetReceived.bulletPositionX == 0 && packetReceived.bulletPositionY == 0) {
                    } else
                        game.bullets.add(new Bullet(game, new Point(packetReceived.bulletPositionX, packetReceived.bulletPositionY), new Point(packetReceived.bulletMovementX, packetReceived.bulletMovementY)));
                    for (int i = 0; i < packetReceived.positions.size(); i++) {
                        game.otherTanks.get(i).setPositionColorAndDirection(packetReceived.positions.get(i), packetReceived.colors.get(i), packetReceived.directions.get(i));
                    }
                    game.change=true;
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
