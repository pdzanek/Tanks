import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class MulticastListener implements Runnable {
    private ObjectOutputStream oos;
    private ObjectInputStream ois;
    Boolean shouldRun=true;
    Tanks game;
    MulticastListener(Tanks game, ObjectOutputStream oos, ObjectInputStream ois){
        this.oos=oos;
        this.ois=ois;
        this.game=game;
    }
@Override
    public void run(){
    System.out.println("Listener: Thread Running");
    ProtocolPacket packetReceived;
    while(shouldRun){
    try{
           packetReceived=null;
           packetReceived = (ProtocolPacket) ois.readObject();
           if (packetReceived.positions != null) {
               for (int i = 0; i < packetReceived.positions.size(); i++) {
                   game.otherTanks.get(i).setPositionColorAndDirection(packetReceived.positions.get(i),packetReceived.colors.get(i),packetReceived.directions.get(i));
               }
           }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
