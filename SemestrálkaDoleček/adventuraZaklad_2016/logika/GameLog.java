package logika;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * metoda postupne zapisuje prikazy zadavane do hry
 */
public class GameLog {
    private StringBuilder log;

    public GameLog(){
        this.log = new StringBuilder();
    }

    public void appendToLog(String text){
        this.log.append(text + "\n");
    }
    public String getLog(){
        return this.log.toString();
    }
    public void saveLog(String path) throws IOException {
        path = path.concat("\\GameLog.txt");
        Files.writeString(Paths.get(path), getLog().toString());
    }


}
