package logika;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class PrikazNacti implements IPrikaz{
    private static final String NAZEV = "nacti";
    private Hra hra;
    private GameLog log;

    /**
     * prikaz nacti umozni nacist uzovateli dosavadni postup z textoveho dokumentu
     * @param hra
     */
    public PrikazNacti(Hra hra) {
        this.hra = hra;
    }

    /**
     *  nacte průbeh hry z textoveho dokumentu, aby hrac mohl pokracovat tam, kde skoncil.
     *  v prubehu je dotazan na cestu k souboru (musi obsahovat i nazev souboru)
     *  @return zpráva, terou vypíše hra hráči.
     *  */
    public String provedPrikaz(String... parametry) {
        if (!(parametry.length == 0)) {
            // pokud chybí druhé slovo, cesta k souboru
            return "Špatně zadaný příkaz nacti";
        }
        Scanner sc = new Scanner(System.in);
        System.out.println("Zadejte cestu, odkud si prejete nacist soubor (včetně názvu souboru).");
        String where = sc.nextLine();
        String pokoj;
        //program se pokusi nacist soubor ze zadane cesty
        //pote provede kazdy radek z ulozeneho souboru
        try {
            List<String> prikazy = Files.readAllLines(Paths.get(where));
            for (String prikaz : prikazy){
                hra.zpracujPrikaz(prikaz);
            }
            pokoj = hra.getHerniPlan().getAktualniProstor().getNazev();
        //pokud se nepovede nacitani
        }catch (NoSuchFileException e) {
            return "Spatna cesta";
        //opet pokud se nepovede nacteni
        } catch (IOException e) {
            e.printStackTrace();
            return "Spatna cesta";
        }
        //po nacteni uvede hraci aktualni mistnost
        return "načteno!\n Nacházíš se v místnosti:" + pokoj;
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
