package logika;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/*******************************************************************************
 * Testovací třída ProstorTest slouží ke komplexnímu otestování
 * třídy Prostor
 *
 * @author    Jarmila Pavlíčková
 * @version   pro skolní rok 2016/2017
 */
public class ProstorTest
{
    //== Datové atributy (statické i instancí)======================================

    //== Konstruktory a tovární metody =============================================
    //-- Testovací třída vystačí s prázdným implicitním konstruktorem ----------

    //== Příprava a úklid přípravku ================================================

    /***************************************************************************
     * Metoda se provede před spuštěním každé testovací metody. Používá se
     * k vytvoření tzv. přípravku (fixture), což jsou datové atributy (objekty),
     * s nimiž budou testovací metody pracovat.
     */
    @Before
    public void setUp() {
    }

    /***************************************************************************
     * Úklid po testu - tato metoda se spustí po vykonání každé testovací metody.
     */
    @After
    public void tearDown() {
    }

    //== Soukromé metody používané v testovacích metodách ==========================

    //== Vlastní testovací metody ==================================================

    /**
     * Testuje, zda jsou správně nastaveny průchody mezi prostory hry. Prostory
     * nemusí odpovídat vlastní hře, 
     */
    @Test
    public  void testLzeProjit() {		
        Prostor prostor1 = new Prostor("hala", "vstupní hala budovy VŠE na Jižním městě", false);
        Prostor prostor2 = new Prostor("bufet", "bufet, kam si můžete zajít na svačinku", false);
        prostor1.setVychod(prostor2);
        prostor2.setVychod(prostor1);
        assertEquals(prostor2, prostor1.vratSousedniProstor("bufet"));
        assertEquals(null, prostor2.vratSousedniProstor("pokoj"));
    }

    Prostor prostor1 = new Prostor("kuchyn", "vstupní hala budovy VŠE na Jižním městě", false);
    Prostor prostor2 = new Prostor("koupelna", "bufet, kam si můžete zajít na svačinku", false);

    Item item1 = new Item("kladivo");
    Item item2 = new Item("mobil");

    Postava postava1 = new Postava("Recepcni");


    /**
     * metoda overujici prikazy
     *      addToListItemu
     *      jeVMistnosti
     *      addToListNPC
     *      getListPostav
     *      getListItemu
     *      findNPC
     *      odemkniMistnost
     *      setPopis
     */
    @Test
    public void pridanePrikazy() {
        List templistPostav= new ArrayList();
        List temlistItemu = new ArrayList();
        temlistItemu.add(item1);
        templistPostav.add(postava1);

        Prostor prostor3 = new Prostor("koupelna", "ahoj", true);

        assertEquals(false, prostor1.jeVMistnosti(item2));


        prostor1.addToListItemu(item1);
        prostor1.addToListNPC(postava1);
        assertEquals(templistPostav, prostor1.getListPostav());
        assertEquals(temlistItemu, prostor1.getListItemu());
        assertEquals(true, prostor1.jeVMistnosti(item1));

        assertEquals(postava1, prostor1.findNPC("Recepcni"));
        prostor3.odemkniMistnost();
        prostor3.setPopis("popis zmenen");
        assertEquals(false, prostor3.isLocked());
        assertEquals("popis zmenen", prostor3.getPopis());

    }
}
