public class Message {
    String message="";
    int connectionNumber;
    Message(int connectionNumber){
        this.connectionNumber=connectionNumber;
    }
    public int getConnectionNumber(){
        return connectionNumber;
    }
}
