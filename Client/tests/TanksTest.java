import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TanksTest {

    @Test
    void run() {
        Tanks game = new Tanks(1280,720);
        assertEquals(5,5);
    }
}