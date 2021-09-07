package logika;

/**
 * Prikaz slouzici k boji s pani uklizeckou
 */
public class PrikazBojuj implements IPrikaz{
    private static final String NAZEV = "bojuj";
    private HerniPlan plan;
    private Kapsy kapsy;


    /**
     * konstruktor
     * @param plan
     */
    public PrikazBojuj(HerniPlan plan) {
        this.plan = plan;
        this.kapsy = plan.getKapsy();
    }


    /**
     * @param parametry počet parametrů závisí na konkrétním příkazu.
     * @return text, ktery se vypise hraci
     */
    @Override
    public String provedPrikaz(String... parametry) {
        //nastavi popis do kuchyne, aby se neobjevoval stejny text az se bude vracet
        plan.getProstor("kuchyn").setPopis("V kuchyni je vše při starém!\n");
        if ((parametry.length > 0)) {
            return "Špatně zadaný příkaz bojuj!";
        }
        //overi, zda je v dane mistnosti uklizecka, jinak nelze pouzit
        if(!kapsy.getKlicKoupelna()) {
            if (plan.getAktualniProstor().getNazev().equals("uklizecka")) {
                //overi, zda mate smetak, pokud ano, dostanete klic
                if (kapsy.isInKapsy("smetak")) {
                    //zjisti, zda je v kapsach misto
                    if(kapsy.getTotalSize() == kapsy.getSize()){
                        plan.getAktualniProstor().addToListItemu(new Item("klicKoupelna"));
                        kapsy.setKlicKoupelnaTrue();
                        return "Uklízečka: \"Pro tentokrá si vyhrál... Tady máš klíč a už mě neotravuj.\"\n" +
                                "Uklízečka: \"Hmmm... Vypadá to, že máš plný kapsy, tady leží, tak si ho pak vem...\"\n" +
                                "\tpoložila klíč na zem.";
                    }
                    kapsy.vloz(new Item("klicKoupelna"));
                    kapsy.setKlicKoupelnaTrue();
                    plan.getAktualniProstor().setPopis("\nUklízečka: \"Co tu chceš? Klíč už snad máš ne? A teď vypal, než ti poškrábu sporák!\"" +
                            "\n\"A jestli ho nemáš, tak si ho někde ztratil!\"");
                    return "Uklízečka: \"Pro tentokrá si vyhrál... Tady máš klíč a už mě neotravuj.\"\n" +
                            "\t Klíč od koupelny sis dal do kapsy..." +
                            "\n\t odejít můžeš pomocí příkazu || jdi chodba ||" +
                            "\n\t koupelnu pote odmenknes prikazem || pouzij [klicKoupelna] ||";
                }
                //pripad, kdy smetak nemame
                return "Uklízečka: \"Jsi úplně bez šance... Radši se ztrať, než ti ublížím.\"\n" +
                        "\n\t Abys porazil uklízečku, musíš najít smeták!" +
                        "\n\t odejít můžeš pomocí příkazu || jdi chodba ||";
            }
            return "Tento příkaz neznám!";
        }
        return "Uklízečka: \"Ale mazej, říkám, že ten klíč už nemám...\"";
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
