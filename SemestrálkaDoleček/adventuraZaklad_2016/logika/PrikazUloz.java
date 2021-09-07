package logika;

import java.io.IOException;
import java.util.Scanner;

/**
 * prikz uloz slouzi k ulozeni veskereho postupu, ktereho uzivatel dosahl do souboru GameLog.txt
 */
public class PrikazUloz implements IPrikaz{
    private static final String NAZEV = "uloz";
    private Hra hra;
    private GameLog log;

    public PrikazUloz(Hra hra) {
        this.hra = hra;


    }

    /**
     *  uloží průbeh hry do textoveho dokumentu.
     *  hrac je dotazan na cestu a misto, kam je nasledne ulozen soubor GameLog.txt
     *  @return zpráva, terou vypíše hra hráči.
     */
    public String provedPrikaz(String... parametry) {
        if (!(parametry.length == 0)) {
            // pokud chybí druhé slovo
            return "Špatně zadaný příkaz uloz";
        }
        Scanner sc = new Scanner(System.in);
        System.out.println("Kam si přejete uložit postup?");
        String where = sc.nextLine();
        try {
            this.log = this.hra.getGameLog();
            log.saveLog(where);
        }catch (IOException e){
            e.printStackTrace();
        }
        return "Uloženo";

    }

    /**
     *  Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *
     *  @ return nazev prikazu
     */

    public String getNazev() {
        return NAZEV;
    }
}
