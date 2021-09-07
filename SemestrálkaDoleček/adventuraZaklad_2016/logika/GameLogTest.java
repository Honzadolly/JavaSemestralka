package logika;

import org.junit.Test;

import static org.junit.Assert.*;

public class GameLogTest {
    /**
     * test appendToLog a getLog
     */
    @Test
    public void GameLog(){
        GameLog log = new GameLog();
        log.appendToLog("Pepa");
        assertEquals("Pepa\n", log.getLog());
        log.appendToLog("je");
        log.appendToLog("doma.");
        assertEquals("Pepa\nje\ndoma.\n", log.getLog());
    }

}