package logika;

public class PrikazRozbehnout implements IPrikaz{
    private static final String NAZEV = "rozbehnout";
    private HerniPlan plan;
    private Kapsy kapsy;

    private Hra hra;

    /**
     * Konstruktor třídy
     *
     * @param plan herní plán, ve kterém se bude ve hře "chodit"
     */

    public PrikazRozbehnout(HerniPlan plan, Hra hra) {
        this.plan = plan;
        this.kapsy = plan.getKapsy();
        this.hra = hra;
    }

    /**
     * prikaz umozni rozebehnout se k pani vedouci
     * pri urcitych okolnostech muze dojit k ukonceni hry se spatnym koncem
     *      - pokud mame skleneny pullitr a pani vedouci zahne na stejnou stranu jako my
     *
     * @return zpráva, terou vypíše hra hráči.
     */

    @Override
    public String provedPrikaz(String... parametry) {
        //overi, zda je postava v mistnosti, pokud ne, hrac o tomto prikazu nevi
        Postava postava = plan.getAktualniProstor().findNPC("vedouci");
        if(parametry.length != 1){
            return "Šparně zadaný příkaz!";
        }
        String mujTah = parametry[0];
        String tahVedouci;

        //vygenerovani nahodneho cisla 1-100, nasledne prirazeni leve a prave strany
        double cisTah = Math.random() * 100;
        if (cisTah <= 50) {
            tahVedouci = "leva";
        } else{
            tahVedouci = "prava";
        }

        if (postava != null) {
            if(kapsy.isInKapsy("klicPipa")){
                //pokud se shoduji vybery uzivatele a nahodny vyber
                if (tahVedouci.equals(mujTah)){
                    //jiny pripad pro plastovy pullitr a sklenenypullitr
                    if(kapsy.isInKapsy("plastovyPullitr")){
                        return "Sakra, paní vedoucí zvolila stejnou stranu jako ty...\n" +
                                "Paní Vecoucí: \"A mám tě ty holomku...\"" +
                                "\n\tpřitom co se tě snažila chytnout, tak do tebe vrazila." +
                                "\nTobě se vysmekl z ruky půllitr a spadl na zem. \n" +
                                "Protože byl ale plastový, tak se mu nic nestalo, sebral si ho a běžíš dál...\n" +
                                "\n\tnyní zadej příkaz || pouzij [klicPipa] ||, abys unikl a dostal se k pípě.";
                    }else if (kapsy.isInKapsy("sklenenyPullitr")){
                        hra.setKonecHry(true);
                        return "Sakra, paní vedoucí zvolila stejnou stranu jako ty...\n" +
                                "Paní Vecoucí: \"A mám tě ty holomku...\"" +
                                "\n\tpřitom co se tě snažila chytnout, tak do tebe vrazila." +
                                "\nTobě se vysmekl z ruky půllitr a spadl na zem. \n" +
                                "protože sis vybral půllitr skleněný, tak se ti rozbil..." +
                                "\ntímto pro tebe hra bohužel končí...";
                    }else{
                        return "Sakra, paní vedoucí zvolila stejnou stranu jako ty...\n" +
                                "Paní Vecoucí: \"A mám tě ty holomku...\"" +
                                "\n\tpřitom co se tě snažila chytnout, tak do tebe vrazila." +
                                "\nTy ses ale v klidu oklepal a běžel dál ke dveřím!" +
                                "\n\tnyní zadej příkaz || pouzij [klicPipa] ||, abys unikl a dostal se k pípě.";
                    }
                }
                return "Paní vedoucí si nakonec obelstil z" + mujTah +
                        "\n Doběhl si ke dveřím a můžeš je rychle odemknout!" +
                        "\n\tdveře odemkni příkazem || pouzij [klicPipa] ||";
            }
            //v pripade, ze nemame klic od pipy v inventari
            return "Nemáš klíč, musel si ho někde nechat! Takže je zbytečný k těm dveřím běhat...\n";
        }
        return "Tento příkaz neznám.\n";



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

