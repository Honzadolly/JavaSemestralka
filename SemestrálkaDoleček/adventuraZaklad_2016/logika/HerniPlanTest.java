package logika;

import org.junit.Test;

import static org.junit.Assert.*;

public class HerniPlanTest {

    HerniPlan herniPlan = new HerniPlan();
    Prostor prostor1 = new Prostor("koupelna", "nic tu neni", false);
    Prostor prostor2 = new Prostor("kumbal", "nic tu neni", false);
    Prostor prostor3 = new Prostor("spiz", "nic tu neni", false);

    Item item1 = new Item("kladivo");
    Item item2 = new Item("kolo");
    Item item3 = new Item("stul");


    /**
     * test overi funkcnost metod
     *      addToListPokoju
     *      isInListPokoju
     *      getProstor
     */
    @Test
    public void isInListPokoju() {
        herniPlan.adToListPokoju(prostor1);
        assertEquals(false, herniPlan.isInListPokoju("kumbal"));
        assertEquals(true, herniPlan.isInListPokoju("koupelna"));
        //kontrola pro pokoj, ktery neni v seznamu pokoju
        assertEquals(null, herniPlan.getProstor("spiz"));
        //tento uz v seznamu je
        assertEquals(prostor1, herniPlan.getProstor("koupelna"));
    }
}