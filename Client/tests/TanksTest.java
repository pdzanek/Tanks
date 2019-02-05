import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TanksTest {
    @Test
    void run(){
        Tanks game = new Tanks(1280,720);
        game.start();
        assertTrue(game.isFocusable());
        assertTrue(game.isRunning);
    }

    @Test
    void createBlocks(){
        Tanks game = new Tanks(1280,720);
        game.start();
        game.createBlocks(5,5);
        assertEquals(game.blocks.length,5*5);
    }

    @Test
    void togglePause() {
        Tanks game = new Tanks(1280,720);
        game.start();
        assertFalse(game.isPaused);
        game.togglePause();
        assertTrue(game.isPaused);
    }

    @Test
    void quit() {
        Tanks game = new Tanks(1280,720);
        game.start();
        game.quit();
        assertFalse(game.isRunning);
    }

}