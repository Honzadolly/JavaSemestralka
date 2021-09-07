package logika;

/**
 * Prikaz slouzici k prohledavani mistnosti
 */
public class PrikazHledej implements IPrikaz{
    private static final String NAZEV = "hledej";
    private HerniPlan plan;
    private Kapsy kapsy;

    /**
     *  Konstruktor třídy
     *
     *  @param plan herní plán, ve kterém se bude ve hře "chodit"
     */

    public PrikazHledej(HerniPlan plan) {
        this.plan = plan;
        this.kapsy = plan.getKapsy();
    }

    /**
     *  prohledá místnosti a vrátí itemy v textu
     *  @return zpráva, terou vypíše hra hráči.
     */

    @Override
    public String provedPrikaz(String... parametry) {
        // pokud je zadaný znak navíc
        if (!(parametry.length == 0)) {
            return "zadal si příznak navíc!";
        }
        String result = null;
        //zapíše itemy, která se nacházejí v místnosti
        for (Item itemVMistnsoti: plan.getAktualniProstor().getListItemu()) {
            //tyto dva příkazy slouží pro upravení tvaru stringu
            if (result == null) {
                result = "\t - " + itemVMistnsoti.getNazev() + "\n";
            } else {
                result += "\t - " + itemVMistnsoti.getNazev() + "\n";
                }
            }
        //pokud je místnost prázdná, ve stringu zůstává null
        if (result == null) {
            return "Místnost je prázdná.";
        }
        return "V místnosti se nachází: \n" + result;

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

