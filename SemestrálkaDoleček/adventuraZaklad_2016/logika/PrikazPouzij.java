package logika;

public class PrikazPouzij implements IPrikaz{
    private static final String NAZEV = "pouzij";
    private HerniPlan plan;
    private Kapsy kapsy;
    private Hra hra;

    public PrikazPouzij(HerniPlan plan, Hra hra) {
        this.plan = plan;
        this.kapsy = plan.getKapsy();
        this.hra = hra;
    }

    /**
     *  použije item, pokud se nachází v kapse a je možné ho použít.
     *  pokud je vyuzito dvacet korun ve vecerce, hra konci dříve s dobrým koncem (EasterEgg)
     *  @return zpráva, terou vypíše hra hráči.
     */

    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            // pokud chybí druhé slovo (věc, kterou chcete pouzit), tak ....
            return "Nevím co mám použít... Musíš zadat název věci";
        }
        Item item = kapsy.findItem(parametry[0]);

        //kontrola, zda predmet je v kapse
        if(kapsy.isInKapsy(parametry[0])) {
            //v case se nachazi seznam predmetu, ktere lze vyuzit
            switch (parametry[0]) {
                //chceme odemknout koupelnu
                case "klicKoupelna":
                    if (plan.getAktualniProstor().getNazev().equals("kuchyn")) {
                        Prostor pokoj = plan.getProstor("koupelna");
                        pokoj.odemkniMistnost();
                        kapsy.odstran(item);
                        return "Odemkl jsi zámek a nyní máš přístup do koupelny!" +
                            "\n\tvstoupis prikazem || jdi koupelna ||";
                    }
                    return "Nejsi u vchodu do koupelny, ten je v kuchyni!";
                //odemknuti pipy
                case "klicPipa":
                    if (plan.getAktualniProstor().getNazev().equals("hala")) {
                        Prostor pokoj = plan.getProstor("hala");
                        pokoj.odemkniMistnost();
                        kapsy.odstran(item);
                        return "Odemkl jsi zámek a nyní máš přístup k pípě!" +
                                "\n\tvstoupis prikazem || jdi pipa ||";
                    }
                    return "Nejsi u vchodu k pípě, ten je v hale!";
                //ukonceni hry drive koupenim piva
                case "dvacetKorun":
                    if (plan.getAktualniProstor().getNazev().equals("vecerka")) {
                        plan.getProstor("vecerka");
                        hra.setKonecHry(true);
                        return "Já: \"Dobrý den, poprosil bych jednou jedenáctku z chlaďáku.\"\n" +
                                "Paní Prodavačka: \"Jasný, bude to dvacet korun...\"" +
                                "\n\n\tkoupil sis pivo, takže už nepotřebuješ hledat místnost s pípou.\n" +
                                "\ttímto pro tebe hra končí uspěšně a dokonce si byl schopen najít EasterEgg";
                    }
                    return "Nejsi u večerky";

                default:
                    return "Tento item nejde použít!";
            }
        }else {
            return "Tento item nemáš v kapsách!";
        }



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
