package logika;

public class PrikazObsahKapes implements IPrikaz {

    private static final String NAZEV = "kapsy";
    private HerniPlan plan;
    private Kapsy kapsy;

    /**
     *  Konstruktor třídy
     *
     *  @param plan herní plán, ve kterém se bude ve hře "chodit"
     */

    public PrikazObsahKapes(HerniPlan plan) {
        this.plan = plan;
        this.kapsy = plan.getKapsy();
    }


    /**
     *
     * @param parametry - priznak parametry ovlivni, co bude vypsano
     *
     * @return obsah = vypsany nazvy itemu
     *         obsah volno = vypise, kolik mista zbyva
     *         obsah obsazeno = vypise kolik mista je zaplneno
     */
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 1){
            String volba = parametry[0];
            //pokud je priznak volno, vypisuje pocet volnych mist
            if(volba.equals("volno")){
                int total = kapsy.getTotalSize() - kapsy.getSize();
                return "Místa v kapsách:\n\t- " + total;
            //pokud je priznak obsazeno, vypise poe obsazenych mist
            }else if(volba.equals("obsazeno")){
                return "Věcí v kapsách:\n\t- "  + kapsy.getSize();

            //pokud je zadán příznak ve špatném tvaru, příkaz na to upozorní
            }else if (volba != "volno" && volba != "obsazeno") {
                return "Špatně zadaný příznak!";
            }

        //pokud chybí příznak, vypíše pouze obsah inventáře
        } else if (parametry.length == 0) {
            String result = null;
            for (String part : kapsy.obsah()) {
                if (result == null) {
                    result = "- " + part;
                } else {
                    result += "\n\t- " + part;
                }
            }
            if (result == null) {
                return "Máš prázdné kapsy.";
            }
            return "Obsah kapes:\n\t" + result;
        }
        // pokud obsahuje nějaké další slovo, tak ....
        return "Napsal si slovo navíc!";


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



