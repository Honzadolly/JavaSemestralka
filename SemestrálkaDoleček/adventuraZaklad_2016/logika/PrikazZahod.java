package logika;

public class PrikazZahod implements IPrikaz{
    private static final String NAZEV = "zahod";
    private HerniPlan plan;
    private Kapsy kapsy;

    /**
     *  Konstruktor třídy
     *
     *  @param plan herní plán, ve kterém se bude ve hře "chodit"
     */

    public PrikazZahod(HerniPlan plan) {
        this.plan = plan;
        this.kapsy = plan.getKapsy();
    }

    /**
     *  vloží item do kapsy".
     *  @return zpráva, terou vypíše hra hráči.
     */

    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            // pokud chybí druhé slovo (věc, kterou chcete zahodit), tak ....
            return "Nevím co mám zahodit... Musíš zadat název věci";
        }

        Item item = kapsy.findItem(parametry[0]);
        //kontrola, zda je takovýto předmět v kapse
        if (!(item == null)){
            //odhodi predmet do mistnosti
            plan.getAktualniProstor().addToListItemu(item);
            kapsy.odstran(item);
            return "Vyhodil si z kapsy tento předmět: " + item.getNazev();
        }
        return "Tento předmět nemáš v kapse!";


    }

    /**
     *  Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *
     *  @ return nazev prikazu
     */
    @Override
    public String getNazev() {
        return NAZEV;
    }

}
