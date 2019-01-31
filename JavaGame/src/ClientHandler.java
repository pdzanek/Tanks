import java.awt.*;
import java.io.*;
import java.net.Socket;

public class ClientHandler extends Thread{
    final Socket s;
    final ObjectInputStream ois;
    final ObjectOutputStream oos;
    int connectionNumber;

    public  ClientHandler(Socket s,ObjectInputStream ois, ObjectOutputStream oos, int connectionNumber){
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
            ProtocolPacket packet = new ProtocolPacket("");
            try {
                packet.position=null;
                packet= (ProtocolPacket) ois.readObject();
                if(packet!=null){
                        System.out.println(packet.bullets.size()+"");
                    gs.setTank(packet.connectionNumber, packet.tankColor, packet.direction, new Point(packet.positionX,packet.positionY),packet.bullets);
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
