package logika;

import org.junit.Test;

import static org.junit.Assert.*;

public class PrikazBojujTest {
    private Hra hra1;


    String bezSmetak = "Uklízečka: \"Jsi úplně bez šance... Radši se ztrať, než ti ublížím.\"\n" +
            "\n\t Abys porazil uklízečku, musíš najít smeták!" +
            "\n\t odejít můžeš pomocí příkazu || jdi chodba ||";

    String seSmetakPlno = "Uklízečka: \"Pro tentokrá si vyhrál... Tady máš klíč a už mě neotravuj.\"\n" +
            "Uklízečka: \"Hmmm... Vypadá to, že máš plný kapsy, tady leží, tak si ho pak vem...\"\n" +
            "\tpoložila klíč na zem.";

    String seSmetak = "Uklízečka: \"Pro tentokrá si vyhrál... Tady máš klíč a už mě neotravuj.\"\n" +
            "\t Klíč od koupelny sis dal do kapsy..." +
            "\n\t odejít můžeš pomocí příkazu || jdi chodba ||" +
            "\n\t koupelnu pote odmenknes prikazem || pouzij [klicKoupelna] ||";

    String uzMasKlic = "Uklízečka: \"Ale mazej, říkám, že ten klíč už nemám...\"";

    String spatne = "Špatně zadaný příkaz bojuj!";

    String jinaMistnost = "Tento příkaz neznám!";



    @Test
    public void testBojuj(){
        hra1 = new Hra();

        hra1.getHerniPlan().setAktualniProstorString("uklizecka");
        //spatne zadany prikaz
        assertEquals(spatne, hra1.zpracujPrikaz("bojuj e"));
        //v inventari neni smetak
        assertEquals(bezSmetak, hra1.zpracujPrikaz("bojuj"));


        hra1.getHerniPlan().setAktualniProstorString("chodba");
        assertEquals(jinaMistnost, hra1.zpracujPrikaz("bojuj"));

        hra1.zpracujPrikaz("seber smetak");
        hra1.getHerniPlan().setAktualniProstorString("uklizecka");

        //v inventari je smetak
        assertEquals(seSmetak, hra1.zpracujPrikaz("bojuj"));

        hra1.getHerniPlan().getKapsy().vloz(new Item("koberec"));
        hra1.getHerniPlan().getKapsy().vloz(new Item("hrnek"));
        hra1.getHerniPlan().getKapsy().vloz(new Item("kolo"));


        //jiz mas klic
        assertEquals(uzMasKlic, hra1.zpracujPrikaz("bojuj"));
    }
}