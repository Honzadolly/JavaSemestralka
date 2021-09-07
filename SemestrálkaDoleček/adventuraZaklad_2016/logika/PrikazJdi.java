package logika;

/**
 *  Třída PrikazJdi implementuje pro hru příkaz jdi.
 *  Tato třída je součástí jednoduché textové hry.
 *  
 *@author     Jarmila Pavlickova, Luboš Pavlíček
 * @updatedBy Jan Dolecek
 *@version    pro školní rok 2021
 */
class PrikazJdi implements IPrikaz {
    private static final String NAZEV = "jdi";
    private HerniPlan plan;
    private Kapsy kapsy;

    
    /**
    *  Konstruktor třídy
    *  
    *  @param plan herní plán, ve kterém se bude ve hře "chodit" 
    */    
    public PrikazJdi(HerniPlan plan) {
        this.plan = plan;
        this.kapsy = plan.getKapsy();
    }

    /**
     *  Provádí příkaz "jdi". Zkouší se vyjít do zadaného prostoru. Pokud prostor
     *  existuje, vstoupí se do nového prostoru. Pokud zadaný sousední prostor
     *  (východ) není, vypíše se chybové hlášení.
     *
     *@param parametry - jako  parametr obsahuje jméno prostoru (východu),
     *                         do kterého se má jít.
     *@return zpráva, kterou vypíše hra hráči
     */ 
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            // pokud chybí druhé slovo (sousední prostor), tak ....
            return "Kam mám jít? Musíš zadat jméno východu";
        }


        if(plan.isInListPokoju(parametry[0])){
            String smer = parametry[0];
            // zkoušíme přejít do sousedního prostoru
            Prostor sousedniProstor = plan.getAktualniProstor().vratSousedniProstor(smer);
            if ((sousedniProstor == null)) {
                return "Tam se odsud jít nedá!";
            }

            String jmenoProstoru = sousedniProstor.getNazev();

            if(sousedniProstor.isLocked()){
                //osetrime pripad zamceni pro jednotlive prostory, bude vracet specificke popisy
                switch (jmenoProstoru){
                    case "koupelna":
                        return "To snad ne. Koupelna je zamčená a nemáš klíče...\n" +
                                "\tNejspíš ti je sebrala paní uklízečka s kolegiňkou. Musíš jí najít a získat je!";
                    case "pipa":
                        return "Místnost s pípou je zamčená.\n" +
                                "Bež se podívat za panem vrátným a zkus je od něj získat.";
                    default:
                        return "Je zamčeno a nemáš klíče!";
                }
            }
            //pokud chceme jit do vecerky, dle stavu inventare jsou nastaveny popisky, ktere se zobrazi
            if(parametry[0].equals("vecerka")){
                if(kapsy.isInKapsy("dvacetKorun")){
                    plan.getProstor("vecerka").setPopis("Přišel si do večerky, kde je velký výběr zboží.\n" +
                            "mezi zbožím je i nějaké pivo... Zkontroluj si kapsy, jestli náhodou nemáš peníze.\n");
                }else {
                    plan.getProstor("vecerka").setPopis("Přišel si do večerky, kde je velký výběr zboží.\n" +
                            "mezi zbožím je i nějaké pivo... Ty ale nemáš žádné peíze, takže bohužel budeš muset" +
                            "\nve své cestě k pípě pokračovat.\n");
                }
            }
            plan.setAktualniProstor(sousedniProstor);
            return sousedniProstor.dlouhyPopis();


        }
        return "Pokoj s tímto názvem neexistuje!\n";
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
