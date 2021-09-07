package logika;

/**
 * metoda v zaveru hry, pokud jsou splneny podminky, hra konci dobre
 * pokud podminky splnene nejsou, hra je ukoncena se spatnym koncem
 */
public class PrikazNacepuj implements IPrikaz{
    private static final String NAZEV = "nacepuj";
    private HerniPlan plan;
    private Kapsy kapsy;

    private Hra hra;

    /**
     * Konstruktor třídy
     *
     * @param plan herní plán, ve kterém se bude ve hře "chodit"
     */

    public PrikazNacepuj(HerniPlan plan, Hra hra) {
        this.plan = plan;
        this.kapsy = plan.getKapsy();
        this.hra = hra;
    }

    /**
     *
     * @return zpráva, terou vypíše hra hráči.
     */

    @Override
    public String provedPrikaz(String... parametry) {
        if(parametry.length!=0){
            return "Špatně zadaný příkaz!\n";
        }
        if (plan.getAktualniProstor().getNazev().equals("pipa")){
            if (kapsy.getPullitr()){
                hra.setKonecHry(true);
                return "Konečně si došel k pípě a mohl sis načepovat půllitr piva.\n" +
                        "\n\tTímto pro tebe hra končí, úspěšně jsi dokončil úkol a překonal veškeré nástrahy.";
            }
            hra.setKonecHry(true);
            return "Konečně si došel k pípě aby sis načepoval pivo. Nikde tu ale není půllitr... Zapomněl sis\n" +
                    "ho vzít.\n" +
                    "\n\tTímto pro tebe hra končí, bohužel neúspěšně... sice se ti povedlo překonat spoustu nástrah," +
                    "\nale zapomněl si na to nejdůležitější... a tím je právě nádoba, do které pivo načepuješ!";

            }
            return "Tento příkaz neznám!";
        }


    /**
     * Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *
     * @ return nazev prikazu
     */
    @Override
    public String getNazev() {
        return NAZEV;
    }
}
