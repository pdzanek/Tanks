import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientSender extends Thread {
    final Socket s;
    final ObjectInputStream ois;
    final ObjectOutputStream oos;
    int connectionNumber;

    public ClientSender(Socket s, ObjectInputStream ois, ObjectOutputStream oos, int connectionNumber) {
        this.s = s;
        this.ois = ois;
        this.oos = oos;
        this.connectionNumber = connectionNumber;
    }
    @Override
    public void run() {
        GameStatus gs = Server.gs;
        while (true) {
            try {
                if(gs.checkForBullet(connectionNumber)) {
                    oos.writeObject(new ProtocolPacket(gs,gs.getBulletPosX(connectionNumber),gs.getBulletPosY(connectionNumber),gs.getBulletMovX(connectionNumber),gs.getBulletMovY(connectionNumber), connectionNumber,gs.getMessage(connectionNumber)));
                    gs.eraseBullet(connectionNumber);
                }else {
                    oos.writeObject(new ProtocolPacket(gs, 0, 0, 0, 0, connectionNumber, gs.getMessage(connectionNumber)));
                }
                Thread.sleep(20);
            } catch (IOException | InterruptedException e) {
                //
            }
        }
    }
}
