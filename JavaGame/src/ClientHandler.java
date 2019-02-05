import java.awt.*;
import java.io.*;
import java.net.Socket;

public class ClientHandler extends Thread{
    final Socket s;
    private final ObjectInputStream ois;
    private final ObjectOutputStream oos;
    private int connectionNumber;

    ClientHandler(Socket s, ObjectInputStream ois, ObjectOutputStream oos, int connectionNumber){
        this.s=s;
        this.ois=ois;
        this.oos=oos;
        this.connectionNumber=connectionNumber;
        String echo="";
        try{
        while(!echo.equals("connected")) {
            echo = "";
            oos.writeObject(new ProtocolPacket("" + connectionNumber));
            ProtocolPacket packet = (ProtocolPacket) ois.readObject();
            echo = packet.getMessage();
        }
        }catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        ClientSender clientSender = new ClientSender(s,ois,oos,connectionNumber);
        clientSender.start();
    }
    @Override
    public void run(){
        GameStatus gs=Server.gs;
        while(true){
            ProtocolPacket packet;
            try {
                packet= (ProtocolPacket) ois.readObject();
                if(packet!=null){
                    if(packet.bulletPositionX==0 && packet.bulletPositionY==0) {
                        gs.setTank(packet.connectionNumber, packet.tankColor, packet.direction, new Point(packet.positionX, packet.positionY));
                        if (!packet.message.equals("")) {
                            gs.setMessages("Player "+connectionNumber+" :"+packet.message);
                        }
                    }
                    else {
                        gs.setTankWithBullet(packet.connectionNumber, packet.tankColor, packet.direction, new Point(packet.positionX, packet.positionY), new Point(packet.bulletPositionX, packet.bulletPositionY), new Point(packet.bulletMovementX, packet.bulletMovementY));
                        if (!packet.message.equals("")) {
                            gs.setMessages("Player "+connectionNumber+" :"+packet.message);
                        }
                    }
                }
            } catch (IOException | ClassNotFoundException e) {
                //
            }
        }
    }
}
