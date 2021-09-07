package logika;

import java.util.List;

/**
 * Prikaz mluv slouzi k navazani interakce mezi jednotlivymi postavami
 */
public class PrikazMluv implements IPrikaz{
    private static final String NAZEV = "mluv";
    private HerniPlan plan;
    private Kapsy kapsy;

    /**
     * Konstruktor třídy
     * @param plan herní plán, ve kterém se bude ve hře "chodit"
     */

    public PrikazMluv(HerniPlan plan) {
        this.plan = plan;
        this.kapsy = plan.getKapsy();
    }

    /**
     * naváže interakci s postavou
     * @return zpráva, terou vypíše hra hráči.
     */

    @Override
    public String provedPrikaz(String... parametry) {
        if ((parametry.length > 0)) {
            return "Špatně zadaný příkaz mluv!";
        }
        List<Postava> postavy = plan.getAktualniProstor().getListPostav();
        for (Postava tempPostava : postavy){
            //umozneni interace pouze s postavou recepcni a uklizecka
            switch (tempPostava.getName()){
                case "uklizecka":
                    return "Ty: \"Omlouvám se, ale hledám tu klíče...\"\n" +
                            "Uklízečka: \"Myslíš tyhle klíče?\"\n\t" +
                            "zamávala uklizecka visačkou na krku.\n" +
                            "Uklízečka: \"Jestli je chceš, tak si je musíš vybojovat, ale pokud nemáš smeták, tak nemáš šanci!\"" +
                            "\n\n\t Nyní můžeš využít příkaz ||| bojuj |||";
                case "recepcni":
                    return "Ty: \"No dobře, jak chceš... Co musím udělat?\n" +
                            "Pan Recepční: \"Je to jednoduché, dáme si kámen, nůžky papír a klíč je tvůj...\"" +
                            "\n\n\t odemknul se ti příkaz ||| zahraj [kamen / nuzky / papir] |||";
                default:
                    return "V této místnosti se nenachází žádná postava, se kterou by se dalo mluvit!";
            }
        }
        //pokud je zadan prikaz jinde, než kde se nachazeji prave tyto postavy
        return "Není s kým si promluvit!";
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





