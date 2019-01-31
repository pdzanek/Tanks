import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
    private static ServerSocket ss = null;
    private static int maxClientsCount;
    private static int portNumber;
    private static List<ClientHandler> clientsThreads = new ArrayList<>();
    private static int connectionNumber=0;
    static GameStatus gs;

    public static void main(String[] args) throws IOException {
        gs = new GameStatus();
        loadServerConfig();
        printServerDetails();
        ServerSocket ss =new ServerSocket(portNumber);
        while(clientsThreads.size()<maxClientsCount){
            Socket s=null;
            connectionNumber++;
            System.out.println("Next connectionNumber:"+connectionNumber);
            try{
                s=ss.accept();
                System.out.println("A new client is connected: "+s);
                ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
                ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
                System.out.println("Connected with:"+s.getPort());
                clientsThreads.add(new ClientHandler(s,ois,oos,connectionNumber));
                clientsThreads.get(clientsThreads.size() - 1).start();
            }catch (Exception e){
                s.close();
                e.printStackTrace();
            }
        }
    }

    public static void loadServerConfig(){
        LoadConfigFile loadConfigFile = new LoadConfigFile();
        portNumber=loadConfigFile.portNumber;
        maxClientsCount=loadConfigFile.maxClientsCount;
    }
    public static void printServerDetails(){
        System.out.println("Server is working on port: "+portNumber);
        System.out.println("Server Max Clitents Count is: "+maxClientsCount);
    }
}