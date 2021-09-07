package logika;

import org.junit.Test;

import static org.junit.Assert.*;

public class PrikazMluvTest {
    private Hra hra1;

    String spatne = "Špatně zadaný příkaz mluv!";

    String uklizecka = "Ty: \"Omlouvám se, ale hledám tu klíče...\"\n" +
            "Uklízečka: \"Myslíš tyhle klíče?\"\n\t" +
            "zamávala uklizecka visačkou na krku.\n" +
            "Uklízečka: \"Jestli je chceš, tak si je musíš vybojovat, ale pokud nemáš smeták, tak nemáš šanci!\"" +
            "\n\n\t Nyní můžeš využít příkaz ||| bojuj |||";

    String recepcni = "Ty: \"No dobře, jak chceš... Co musím udělat?\n" +
            "Pan Recepční: \"Je to jednoduché, dáme si kámen, nůžky papír a klíč je tvůj...\"" +
            "\n\n\t odemknul se ti příkaz ||| zahraj [kamen / nuzky / papir] |||";

    String nikdoNaMluveni = "V této místnosti se nenachází žádná postava, se kterou by se dalo mluvit!";

    String nidko = "Není s kým si promluvit!";


    /**
     * testy overi funkci prikazu mluv
     */
    @Test
    public void mluvTest(){
        hra1 = new Hra();

        //mistnosti s postavami
        hra1.getHerniPlan().setAktualniProstorString("uklizecka");
        assertEquals(uklizecka, hra1.zpracujPrikaz("mluv"));
        hra1.getHerniPlan().setAktualniProstorString("vratnice");
        assertEquals(recepcni, hra1.zpracujPrikaz("mluv"));

        //zde se nachazi postava vdouci, ale s tou se neda mluvit
        hra1.getHerniPlan().setAktualniProstorString("hala");
        assertEquals(nikdoNaMluveni, hra1.zpracujPrikaz("mluv"));

        //v mistnosti se nenachazi nikdo
        hra1.getHerniPlan().setAktualniProstorString("vytah");
        assertEquals(nidko, hra1.zpracujPrikaz("mluv"));


    }

}