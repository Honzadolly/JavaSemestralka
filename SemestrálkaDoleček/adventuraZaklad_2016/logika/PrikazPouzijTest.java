package logika;

import org.junit.Test;

import static org.junit.Assert.*;

public class PrikazPouzijTest {
    private Hra hra1;

    String spatne = "Nevím co mám použít... Musíš zadat název věci";

    String klicKoupelna = "Odemkl jsi zámek a nyní máš přístup do koupelny!" +
            "\n\tvstoupis prikazem || jdi koupelna ||";

    String neUKoupelny = "Nejsi u vchodu do koupelny, ten je v kuchyni!";

    String klicPipa = "Odemkl jsi zámek a nyní máš přístup k pípě!" +
            "\n\tvstoupis prikazem || jdi pipa ||";

    String neUPipy = "Nejsi u vchodu k pípě, ten je v hale!";

    String dvacka = "Já: \"Dobrý den, poprosil bych jednou jedenáctku z chlaďáku.\"\n" +
            "Paní Prodavačka: \"Jasný, bude to dvacet korun...\"" +
            "\n\n\tkoupil sis pivo, takže už nepotřebuješ hledat místnost s pípou.\n" +
            "\ttímto pro tebe hra končí uspěšně a dokonce si byl schopen najít EasterEgg";

    String neUVecerky = "Nejsi u večerky";

    String unusable = "Tento item nejde použít!";

    String neVKapse = "Tento item nemáš v kapsách!";

    @Test
    public void testPouzij(){
        hra1 = new Hra();
        //spatne zadane
        assertEquals(spatne, hra1.zpracujPrikaz("pouzij"));

        //klic koupelna bez klice
        assertEquals(neVKapse, hra1.zpracujPrikaz("pouzij klicKoupelna"));

        //klic koupelna
        hra1.getHerniPlan().getKapsy().vloz(new Item("klicKoupelna"));
        assertEquals(neUKoupelny, hra1.zpracujPrikaz("pouzij klicKoupelna"));
        hra1.getHerniPlan().setAktualniProstorString("kuchyn");
        assertEquals(klicKoupelna, hra1.zpracujPrikaz("pouzij klicKoupelna"));

        //klic Pipa
        hra1.getHerniPlan().getKapsy().vloz(new Item("klicPipa"));
        assertEquals(neUPipy, hra1.zpracujPrikaz("pouzij klicPipa"));
        hra1.getHerniPlan().setAktualniProstorString("hala");
        assertEquals(klicPipa, hra1.zpracujPrikaz("pouzij klicPipa"));

        //vecerka
        hra1.getHerniPlan().getKapsy().vloz(new Item("tricetKorun"));
        hra1.getHerniPlan().getKapsy().vloz(new Item("dvacetKorun"));
        assertEquals(unusable, hra1.zpracujPrikaz("pouzij tricetKorun"));
        assertEquals(neUVecerky, hra1.zpracujPrikaz("pouzij dvacetKorun"));
        hra1.getHerniPlan().setAktualniProstorString("vecerka");
        assertEquals(dvacka, hra1.zpracujPrikaz("pouzij dvacetKorun"));



    }

}