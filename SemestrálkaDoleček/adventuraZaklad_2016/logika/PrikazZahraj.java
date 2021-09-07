package logika;

public class PrikazZahraj implements IPrikaz {
    private static final String NAZEV = "zahraj";
    private HerniPlan plan;
    private Kapsy kapsy;

    /**
     * Konstruktor třídy
     *
     * @param plan herní plán, ve kterém se bude ve hře "chodit"
     */

    public PrikazZahraj(HerniPlan plan) {
        this.plan = plan;
        this.kapsy = plan.getKapsy();
    }

    /**
     * zahraje kamen, nuzky nebo papir overi, zda hrac vyhral.
     *
     * @return zpráva, terou vypíše hra hráči.
     */

    @Override
    public String provedPrikaz(String... parametry) {
        //overi, zda je postava v mistnosti, pokud ne, hrac o tomto prikazu nevi
        Postava postava = plan.getAktualniProstor().findNPC("recepcni");
        String tah;
        if (postava == null) {
            return "Tento příkaz neznám.";
        }

        //overi, zda se jiz klic nenachazi v kapse
        if (!(kapsy.isInKapsy("klic"))) {
            //overi, zda klic jiz jednou nebyl ziskany
            if (!(kapsy.getKlic())) {
                // pokud chybí druhé slovo (tah, který chcete zahrát), nebo je slovo navic, tak ....
                if (!(parametry.length == 1)) {
                    return "Nevím co mám zahrát... zadej kámen, nůžky nebo papír!";
                }
                //nasledujici dva bloky if slouzi k overeni, zda bylo zadane spravne slovo
                boolean result = false;
                if (parametry[0].equals("nuzky") || parametry[0].equals("papir") || parametry[0].equals("kamen")) {
                    result = true;
                }

                if (!(result)) {
                    return "Nevím co mám zahrát... zadej kámen, nůžky nebo papír!";
                }
                String mujTah = parametry[0];

                //generovani nahodneho cisla mezi 1 a 100
                double cisTah = Math.random() * 100;
                //prirazeni nazvu tahu k cislu
                if (cisTah <= 33) {
                    tah = "kamen";
                } else if (cisTah > 33 && cisTah < 66) {
                    tah = "nuzky";
                } else {
                    tah = "papir";
                }

                //pripady, ktere mohou nastat
                if (tah.equals(mujTah)) {
                    return "Pan Recepční: \"Je to nerozhodně, oba jsme dali " + tah + "\"";
                    //prohra
                } else if (tah.equals("kamen") && mujTah.equals("nuzky")) {
                    return "Pan Recepční: \" HaHaHaaa... Prohrál si, kámen otupil tvé nůžky! Musíš hrát znovu...\"";
                } else if (tah.equals("nuzky") && mujTah.equals("papir")) {
                    return "Pan Recepční: \"Prohrál si, nůžky rozstříhaly tvůj papír! Musíš hrát znovu...\"";
                } else if (tah.equals("papir") && mujTah.equals("kamen")) {
                    return "Pan Recepční: \"Prohrál si, papír zabalil tvůj kámen! Musíš hrát znovu...\"";
                    //vyhra
                } else if (mujTah.equals("nuzky") && tah.equals("papir")) {
                    kapsy.vloz(new Item("klicPipa"));
                    kapsy.setKlicTrue();
                    //nastaveni noveho popisu mistnosti
                    plan.getAktualniProstor().setPopis("Pan Recepční: \"O klíč už jsme jednou hráli... musel si ho někde nechat!");
                    plan.getProstor("hala").setPopis("Paní Vedoucí: \"Kam si jako myslíš, že jdeš?\"" +
                            "\"Nemysli si, že se k tý pípě dostaneš! Včera už jste tady udělali pěknej bordel...\"" +
                            "\n\n Sakra... Co teď? nezbývá nic jiného, než se rozeběhnout a doufat..." +
                            "\n\totevřel se ti příkaz rozbehnout [leva/prava]");
                    return "Vyhrál si, tvoje nůžky rozstříhaly papír!\n" +
                            "Pan Recepční: \"Sakra, tak tohle jsem nečekal...\n\t" +
                            "tady máš ten hlíč a už mě neotravuj\"\n\n" +
                            "Vložil sis do kapsy klíč.";
                } else if (mujTah.equals("papir") && tah.equals("kamen")) {
                    kapsy.vloz(new Item("klicPipa"));
                    kapsy.setKlicTrue();
                    //nastaveni noveho popisu mistnosti
                    plan.getAktualniProstor().setPopis("Pan Recepční: \"O klíč už jsme jednou hráli... musel si ho někde nechat!");
                    plan.getProstor("hala").setPopis("Paní Vedoucí: \"Kam si jako myslíš, že jdeš?\"" +
                            "\"Nemysli si, že se k tý pípě dostaneš! Včera už jste tady udělali pěknej bordel...\"" +
                            "\n\n Sakra... Co teď? nezbývá nic jiného, než se rozeběhnout a doufat..." +
                            "\n\totevřel se ti příkaz rozbehnout [leva/prava]");
                    return "Vyhrál si, tvůk papír obalil kámen!\n" +
                            "Pan Recepční: \"Sakra, tak tohle jsem nečekal...\n\t" +
                            "tady máš ten hlíč a už mě neotravuj\"\n\n" +
                            "Vložil sis do kapsy klíč.";
                } else if (mujTah.equals("kamen") && tah.equals("nuzky")) {
                    kapsy.vloz(new Item("klicPipa"));
                    kapsy.setKlicTrue();
                    //nastaveni noveho popisu mistnosti
                    plan.getAktualniProstor().setPopis("Pan Recepční: \"O klíč už jsme jednou hráli... musel si ho někde nechat!");
                    plan.getProstor("hala").setPopis("Paní Vedoucí: \"Kam si jako myslíš, že jdeš?\"" +
                            "\"Nemysli si, že se k tý pípě dostaneš! Včera už jste tady udělali pěknej bordel...\"" +
                            "\n\n Sakra... Co teď? nezbývá nic jiného, než se rozeběhnout a doufat..." +
                            "\n\totevřel se ti příkaz rozbehnout [leva/prava]");
                    return "Vyhrál si, tvůj kámen otupil nůžky!\n" +
                            "Pan Recepční: \"Sakra, tak tohle jsem nečekal...\n\t" +
                            "tady máš ten hlíč a už mě neotravuj\"\n\n" +
                            "Vložil sis do kapsy klíč.";
                }
            }
            //navratova hodnota v pripade, ze jiz klic mate v inventari klic
            return "Pan Recepční: \"Jak říkám... o klíč už jsme jednou hráli... musel si ho někde nechat!";
        }
        //navratova hodnota, pokud klic jiz jednou byl ziskan
        return "Již nemůžeš hrát, klíč už si dávno získal!";
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



