import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    static ObjectInputStream ois;
        static ObjectOutputStream oos;
        static int connectionNumber;

    public static void main(String[] args) {
        try {

            InetAddress ip = InetAddress.getByName("localhost");
            Socket s = new Socket(ip, 2222);
            oos = new ObjectOutputStream(s.getOutputStream());
            oos.writeObject(new ProtocolPacket(""));
            oos.flush();
            ois = new ObjectInputStream(s.getInputStream());

            while (connectionNumber == 0) {
                ProtocolPacket packet = (ProtocolPacket) ois.readObject();
                connectionNumber = Integer.parseInt(packet.getMessage());
                System.out.println("Waiting for connectionNumber");
            }
            System.out.println("Connection Number=" + connectionNumber);
            oos.writeObject(new ProtocolPacket("connected"));
        } catch (ConnectException e) {
            System.out.println("Serwer niedostępny...");
            System.exit(1);
        } catch (UnknownHostException ignored) {
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        JFrame frame = new JFrame("Tanks");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setSize(1280, 720);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        Tanks game = new Tanks(1280, 720);
        Thread listenerThread=new Thread(new MulticastListener(game,oos,ois));
        listenerThread.start();
        Thread senderThread=new Thread(new ServerSender(game,oos,ois));
        senderThread.start();
        Chat chat = new Chat(game);
        Thread chatThread = new Thread(chat);
        chatThread.start();
        frame.add(game, BorderLayout.CENTER);
        game.setChat(chat);
        game.start();
        frame.setVisible(true);

    }
}
