import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MessageTest {

    @Test
    void getConnectionNumber() {
        int connectionNumber=4;
        Message msg = new Message(connectionNumber);
        assertEquals(msg.getConnectionNumber(),connectionNumber);
    }
}