import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChatTest {

    @Test
    void makeTextAreaGreatAgain() {
        Tanks game = new Tanks(1280, 720);
        Chat chat = new Chat(game);
        chat.start();
        chat.text="testString";
        chat.makeTextAreaGreatAgain();
        assertEquals(chat.t1.getText(),"testString");
    }
}