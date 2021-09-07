package logika;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/*******************************************************************************
 * Testovací třída HraTest slouží ke komplexnímu otestování
 * třídy Hra
 *
 * @author    Jarmila Pavlíčková
 * @version  pro školní rok 2016/2017
 */
public class HraTest {
    private Hra hra1;

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
        hra1 = new Hra();
    }

    /***************************************************************************
     * Úklid po testu - tato metoda se spustí po vykonání každé testovací metody.
     */
    @After
    public void tearDown() {
    }

    //== Soukromé metody používané v testovacích metodách ==========================

    //== Vlastní testovací metody ==================================================

    /***************************************************************************
     * Testuje průběh hry, po zavolání každěho příkazu testuje, zda hra končí
     * a v jaké aktuální místnosti se hráč nachází.
     * Při dalším rozšiřování hry doporučujeme testovat i jaké věci nebo osoby
     * jsou v místnosti a jaké věci jsou v batohu hráče.
     * 
     */
    @Test
    public void testPrubehHry() {
        assertEquals("pokoj", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("jdi kuchyn");
        assertEquals(false, hra1.konecHry());
        assertEquals("kuchyn", hra1.getHerniPlan().getAktualniProstor().getNazev());
        assertEquals(false, hra1.konecHry());
        assertEquals("To snad ne. Koupelna je zamčená a nemáš klíče...\n\tNejspíš ti je sebrala paní uklízečka s kolegiňkou. Musíš jí najít a získat je!", hra1.zpracujPrikaz("jdi koupelna"));
        hra1.zpracujPrikaz("jdi chodba");
        assertEquals("chodba", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("konec");
        assertEquals(true, hra1.konecHry());
    }

    /**
     * testuje prikaz seber, overi, zda pokud jsou kapsy plne,
     * tak se neprida vec
     */
    @Test
    public void testyKapsy(){
        assertEquals("pokoj", hra1.getHerniPlan().getAktualniProstor().getNazev());
        assertEquals("Item nenalezen!", hra1.zpracujPrikaz("seber auto"));
        assertEquals(2,hra1.getHerniPlan().getKapsy().getTotalSize());

        //testy volneho mista, prazdneho inventare
        hra1.zpracujPrikaz("seber propiska");
        hra1.zpracujPrikaz("seber kapesniky");
        hra1.zpracujPrikaz("jdi kuchyn");
        hra1.zpracujPrikaz("seber varecka");
        assertEquals("Máš plné kapsy, musíš něco zahodit!", hra1.zpracujPrikaz("seber bageta"));
        hra1.zpracujPrikaz("jdi pokoj");
        hra1.zpracujPrikaz("seber bunda");
        assertEquals("Item nenalezen!", hra1.zpracujPrikaz("seber bunda"));

        assertEquals(7,hra1.getHerniPlan().getKapsy().getTotalSize());
        assertEquals(3,hra1.getHerniPlan().getKapsy().getSize());
        assertEquals(4,(hra1.getHerniPlan().getKapsy().getTotalSize() - hra1.getHerniPlan().getKapsy().getSize()));

        //test zahozeni
        hra1.zpracujPrikaz("zahod varecka");
        assertEquals(5,(hra1.getHerniPlan().getKapsy().getTotalSize() - hra1.getHerniPlan().getKapsy().getSize()));
    }

    String epilog = "\n==================================================================================\n" +
            "\n\tMoc děkuji za hraní hry, pokud se Vám líbila, příští rok očekávejte update ve\n" +
            "\tformě grafického prostředí! Na to si ale musíte počkat.\n" +
            "\t\t=======================\n" +
            "\t\t     Naviděnou!!      \n" +
            "\t\t======================";


    @Test
    public void testTexty(){
        Hra hra = new Hra();
        hra.getHerniPlan().adToListPokoju(new Prostor("chodba", "chodba z pokoje", false));
        hra.getHerniPlan().setAktualniProstorString("chodba");
        String uvitani = "=============================================================================================================" +
                "\nVítejte ve hře!\n\n" +
                "\t\tJedenáctka Jižní město\n\n" +
                "\tNapište 'nápověda', pokud si nevíte rady, jak hrát dál.\n" +
                "\nZde jsou uvedeny základní příkazy sloužící k pohybu po mapě:" +
                "\n\t jdi [mistnost] ||| hledej ||| kapsy ||| kapsy [volno/obsazeno] ||| seber [predmet] ||| zahod [predmet] ||| uloz |||"  +
                "\n\n\tPokud se ve tvém počítači nachází nějaký uložený postup, je možné ho načíst příkazem ||| nacti |||" +
                "\n\nV průběhu hry se ale mohou odemknout i příkazy nové!" +
                "\n\nPřeji příjemnou zábavu a šťastou hru" +
                "\n---------" +
                "\n\nProbudil ses ve svém pokoji a v krku máš sucho. Před očima máš jasnou vidinu vychlazené\n" +
                "jedenáctky, a to rovnou z čepu!" +
                "\nÚkol je jasný, najdi pokoj, ve kterém se nachází pípa. V té zbývá ještě pár doušků oné pochutiny.\n" +
                "Ale pozor, tvá cesta bude plná strastí a utrpení.\n" +
                "Jednou z mnoha překážek je například Paní uklízečka spolu se svou kolegyňkou. Ta ti ukradla klíče\n" +
                "od koupelny, ve které máš své oblíbené půllitry.\n\n" +
                "Celé hra je zakončena úspěšným nalezením bájné místnosti a přelstěním paní vedoucí, která se\n" +
                "potuluje po areálu a snaží se k oné pípě dostat dříve než ty!\n\n" +
                "V průběhu hry můžeš narazit na takzvané „Easter eggs“, ty dokážou okolnosti hry hodně i pozměnit.\n" +
                "==============================================================================================================\n" +
                hra.getHerniPlan().getAktualniProstor().dlouhyPopis();



        assertEquals(epilog, hra.vratEpilog());
        assertEquals(uvitani, hra.vratUvitani());
    }



}
