package logika;

public class PrikazSeber implements IPrikaz{
    private static final String NAZEV = "seber";
    private HerniPlan plan;
    private Kapsy kapsy;

    public PrikazSeber(HerniPlan plan) {
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
            // pokud chybí druhé slovo (věc, kterou chcete pouužít), tak ....
            return "Nevím co mám sebrat... Musíš zadat název věci";
        }
        Item item = plan.getAktualniProstor().findItem(parametry[0]);


        //overi, zda je item v mistnosti
        if (!(plan.getKapsy().isInKapsy(parametry[0]))) {
            if (plan.getAktualniProstor().jeVMistnosti(item)) {
                //switch pro zajisteni pripadu jednotlivych moznosti
                switch (parametry[0]) {
                    //pokud je priznak bunda, navysi se sloty v kapse o 5
                    case "bunda":
                        kapsy.setSize();
                        kapsy.vloz(new Item("dvacetKorun"));
                        //odstrani bundu z mistnosti
                        plan.getAktualniProstor().removeFromListItemu(item);
                        return "Oblékl sis bundu, ta ti navíc umožnila pobrat více věcí, jelikož má také kapsy. \n\t ...v bundě se už něco ale může také nacházet.";
                    case "sklenenyPullitr":
                        //v kapse muze byt pouze jeden pullitr zaroven
                        if(!(kapsy.isInKapsy("plastovyPullitr"))){
                            //overeni, zda je v kapsach misto
                            if (kapsy.getTotalSize() > kapsy.getSize()) {
                                kapsy.vloz(item);
                                //odstrani predmet z mistnosti
                                plan.getAktualniProstor().removeFromListItemu(item);
                                //nastavi popis kuchyne tak, aby opet byl lepsi kontext
                                plan.getAktualniProstor().setPopis("Vešel jsi do koupelny, kde byli 2 půllitry.");
                                kapsy.setPullitrTrue();
                                return "Do kapsy sis dal: " + item.getNazev();
                            }
                            return "Máš plné kapsy, musíš něco zahodit!";
                        }
                        return "V kapse můžeš mít pouze jeden půllitr, druhý musíš zahodit!";
                    case "plastovyPullitr":
                        //v kapse muze byt pouze jeden pullitr zaroven
                        if(!(kapsy.isInKapsy("sklenenyPullitr"))){
                            //overeni, zda je v kapse misto
                            if (kapsy.getTotalSize() > kapsy.getSize()) {
                                kapsy.vloz(item);
                                //odstrani predmet z mistnosti
                                plan.getAktualniProstor().removeFromListItemu(item);
                                //nastavi popis kuchyne tak, aby opet byl lepsi kontext
                                plan.getAktualniProstor().setPopis("Vešel jsi do koupelny, kde byli 2 půllitry.");
                                kapsy.setPullitrTrue();
                                return "Do kapsy sis dal: " + item.getNazev();
                            }
                            return "Máš plné kapsy, musíš něco zahodit!";
                        }
                        return "V kapse můžeš mít pouze jeden půllitr, druhý musíš zahodit!";
                    default:
                        //overi, zda je misto v kapsach
                        if (kapsy.getTotalSize() > kapsy.getSize()) {
                            kapsy.vloz(item);
                            //odstrani predmet z mistnosti
                            plan.getAktualniProstor().removeFromListItemu(item);
                            return "Do kapsy sis dal: " + item.getNazev();
                        }
                        return "Máš plné kapsy, musíš něco zahodit!";
                }
            }
            return "Item nenalezen!";
        }
        return "Tento item už máš v kapse!";
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
