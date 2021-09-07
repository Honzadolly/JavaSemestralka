package logika;

import org.junit.Test;

import static org.junit.Assert.*;

public class PrikazHledejTest {
    Hra hra = new Hra();


    /**
     * Otestuje funkčnost příkazu hledej
     */
    @Test
    public void testHledej(){
        hra.getHerniPlan().adToListPokoju(new Prostor("chodba", "chodba z pokoje", false));
        hra.getHerniPlan().setAktualniProstorString("chodba");
        assertEquals("zadal si příznak navíc!", hra.zpracujPrikaz("hledej sf"));
        assertEquals("Místnost je prázdná.", hra.zpracujPrikaz("hledej"));
        Item koste = new Item("koste");
        hra.getHerniPlan().getAktualniProstor().addToListItemu(koste);
        assertEquals("V místnosti se nachází: \n" + "\t - " + koste.getNazev() + "\n", hra.zpracujPrikaz("hledej"));
    }


}