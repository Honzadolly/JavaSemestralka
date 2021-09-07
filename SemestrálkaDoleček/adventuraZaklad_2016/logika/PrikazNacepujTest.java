package logika;

import org.junit.Test;

import static org.junit.Assert.*;

public class PrikazNacepujTest {
    private Hra hra1;

    String spatne = "Špatně zadaný příkaz!\n";

    String jinde = "Tento příkaz neznám!";;

    String sPullitrem = "Konečně si došel k pípě a mohl sis načepovat půllitr piva.\n" +
            "\n\tTímto pro tebe hra končí, úspěšně jsi dokončil úkol a překonal veškeré nástrahy.";

    String bezPullitru = "Konečně si došel k pípě aby sis načepoval pivo. Nikde tu ale není půllitr... Zapomněl sis\n" +
            "ho vzít.\n" +
            "\n\tTímto pro tebe hra končí, bohužel neúspěšně... sice se ti povedlo překonat spoustu nástrah," +
            "\nale zapomněl si na to nejdůležitější... a tím je právě nádoba, do které pivo načepuješ!";

    /**
     * testy overi prikaz nacepuj
     */
    @Test
    public void nacepujTest(){
        hra1 = new Hra();
        //spatne zadano a pote bez pullitru
        hra1.getHerniPlan().setAktualniProstorString("pipa");
        assertEquals(spatne, hra1.zpracujPrikaz("nacepuj s"));
        assertEquals(bezPullitru, hra1.zpracujPrikaz("nacepuj"));

        //zadano jinde
        hra1.getHerniPlan().setAktualniProstorString("koupelna");
        assertEquals(jinde, hra1.zpracujPrikaz("nacepuj"));

        //zadano s pullitrem
        hra1.zpracujPrikaz("seber sklenenyPullitr");
        hra1.getHerniPlan().setAktualniProstorString("pipa");
        assertEquals(sPullitrem, hra1.zpracujPrikaz("nacepuj"));
    }

}