package logika;


import java.util.ArrayList;
import java.util.List;

/**
 *  Class HerniPlan - třída představující mapu a stav adventury.
 * 
 *  Tato třída inicializuje prvky ze kterých se hra skládá:
 *  vytváří všechny prostory,
 *  propojuje je vzájemně pomocí východů 
 *  a pamatuje si aktuální prostor, ve kterém se hráč právě nachází.
 *
 *@author     Jan Doleček
 *@version    LS 2020/2021
 */
public class HerniPlan {

    private Prostor aktualniProstor;
    private Kapsy kapsy;
    private List<Prostor> listPokoju = new ArrayList<>();


     /**
     *  Konstruktor který vytváří jednotlivé prostory a propojuje je pomocí východů.
     *  Jako výchozí aktuální prostor nastaví halu.
     */
    public HerniPlan() {
        zalozProstoryHry();
        kapsy = new Kapsy();



    }
    /**
     *  Vytváří jednotlivé prostory a propojuje je pomocí východů.
     *  Jako výchozí aktuální prostor nastaví domeček.
     */
    private void zalozProstoryHry() {
        // vytvářejí se jednotlivé prostory a pridani do listPokoju
        Prostor pokoj = new Prostor("pokoj", "Máš hrozný sucho v krku, zkus se porozhlédnout," +
                "jestli tu nenajdeš nějaké zbytky piva, ať se pořádně probereš!\n", false);
        listPokoju.add(pokoj);
        Prostor kuchyn = new Prostor("kuchyn","Kuchyňka především slouží jako spojnice pokoje, koupelny a chodby.\n\n" +
                "Já: \"Tyjo, ještě se necítím úplně tak fresh... snad nebude takový problém najít ten pokoj...\"\n" +
                "\"Joo a vlastně si musím vzít s sebou ten půllitr z koupelny.\"\n", false);
        listPokoju.add(kuchyn);
        Prostor koupelna = new Prostor("koupelna", "Konečně ses dostal do koupelny, kam sis večer dal vypláchnutý půllitr." +
                "\nJeden si teď musíš vzít. Mít u sebe ale oba dva je přeci zbytečné.\n", true);
        listPokoju.add(koupelna);
        Prostor chodba = new Prostor("chodba","Tato temná chodba se táhne od pokoje, až k výtahu." +
                "\nleží v ní i nějaký věci, které by se mohl itřeba hodit.", false);
        listPokoju.add(chodba);
        Prostor uklizeciMistnost = new Prostor("uklizecka","uklizecí místnost. \n" +
                "Všude je samé harampádí, okolo pavučiny a jedno světlo bliká... Hledáš, kde by mohl být klíč od koupelny, ale v tom se najednou ozve rachot...\n" +
                "Uklízečka: \"Co děláš v mém kumbálu? Co si to dovoluješ ty spratku!!!" +
                "\n\n\tPro navázání interakce zadej příkaz || mluv ||\n", false);
        listPokoju.add(uklizeciMistnost);
        Prostor vytah = new Prostor("vytah", "Já: \"Tase ta hrozná výtahovka... " +
                "Už by s tím mohli přestat...\"", false);
        listPokoju.add(vytah);
        Prostor hala = new Prostor("hala","Po včerejší párty ani stopy, vypadá to hodně dobře.", false);
        listPokoju.add(hala);
        Prostor vratnice = new Prostor("vratnice", "Vrátnice" +
                "Pan Vrátný: \"Aaale ty holomku, snad si nejdeš pro tohle...\"\n\t" +
                "Ukázal na staré klíše od pokoje s pípou\n" +
                "\n\tPro navázání interakce zadej příkaz mluv\"\n", false);
        listPokoju.add(vratnice);
        Prostor vecerka = new Prostor("vecerka","Večerka, plná lahodných dobrot.", false);
        listPokoju.add(vecerka);
        Prostor bajnaMistnost = new Prostor("pipa","To je ona... Pípa.\nA dokonce to vypadá, že není prázdná.\n" +
                "\tpivo si načepuješ příkazem || nacepuj ||",true);
        listPokoju.add(bajnaMistnost);


        //pokoj vychody
        pokoj.setVychod(kuchyn);

        //kuchyn vychody
        kuchyn.setVychod(pokoj);
        kuchyn.setVychod(koupelna);
        kuchyn.setVychod(chodba);

        //koupelna vychody
        koupelna.setVychod(kuchyn);

        //chodba vychody
        chodba.setVychod(kuchyn);
        chodba.setVychod(uklizeciMistnost);
        chodba.setVychod(vytah);

        //uklizeciMistnost vychody
        uklizeciMistnost.setVychod(chodba);

        //vztah vychody
        vytah.setVychod(chodba);
        vytah.setVychod(hala);

        //hala vychody
        hala.setVychod(vytah);
        hala.setVychod(vratnice);
        hala.setVychod(vecerka);
        hala.setVychod(bajnaMistnost);

        //vratnice vychody
        vratnice.setVychod(hala);

        //vecerka vychody
        vecerka.setVychod(hala);

        //bajnaMistnost vychody
        bajnaMistnost.setVychod(hala);


        aktualniProstor = pokoj;  // hra začíná v pokoji

        //Vlozeni itemu do mistnosti
        pokoj.addToListItemu(new Item("bunda"));
        pokoj.addToListItemu(new Item("propiska"));
        pokoj.addToListItemu(new Item("kapesníky"));

        kuchyn.addToListItemu(new Item("bageta"));
        kuchyn.addToListItemu(new Item("varecka"));

        koupelna.addToListItemu(new Item("plastovyPullitr"));
        koupelna.addToListItemu(new Item("sklenenyPullitr"));
        koupelna.addToListItemu(new Item("rucnik"));
        koupelna.addToListItemu(new Item("kartacekNaZuby"));

        chodba.addToListItemu(new Item("smetak"));
        chodba.addToListItemu(new Item("mop"));
        chodba.addToListItemu(new Item("hadr"));

        vytah.addToListItemu(new Item("brozura"));
        hala.addToListItemu(new Item("nalepka"));


        //postavy
        Postava uklizecka = new Postava("uklizecka");
        Postava recepcni = new Postava("recepcni");
        Postava vedouci = new Postava("vedouci");

        uklizeciMistnost.addToListNPC(uklizecka);
        vratnice.addToListNPC(recepcni);
        hala.addToListNPC(vedouci);

    }



    /**
     *  Metoda vrací odkaz na aktuální prostor, ve ktetém se hráč právě nachází.
     *
     *@return     aktuální prostor
     */
    
    public Prostor getAktualniProstor() {
        return aktualniProstor;
    }
    
    /**
     *  Metoda nastaví aktuální prostor, používá se nejčastěji při přechodu mezi prostory
     *
     *@param  prostor nový aktuální prostor
     */
    public void setAktualniProstor(Prostor prostor) {
        aktualniProstor = prostor;
    }

    /**
     * metoda dle nazvu prostoru nastavi aktualni prostor - lepsi testovani hry
     * @param prostor - nazev prostoru
     */
    public void setAktualniProstorString(String prostor) {
        for (Prostor tempProstor : listPokoju){
            if (tempProstor.getNazev().equals(prostor)){
                aktualniProstor = tempProstor;
            }
        }
    }


    /**
     * @return kapsy
     */
    public Kapsy getKapsy(){
        return kapsy;
    }

    /**
     * @param kapsy - kapsy, ktere nastavi this.kapsy
     */
    public void setKapsy(Kapsy kapsy){
        this.kapsy = kapsy;
    }

    /**
     * @return seznam pokoju
     */
    public List<Prostor> listPokoju(){
        return this.listPokoju;
    }

    /**
     * metoda po zadani jmena mistnosti zjisti, zda je(true) tento pokoj vubec ve hre
     * @param jmeno - jmeno mistnosti
     * @return true/false
     */
    public boolean isInListPokoju(String jmeno){
        for (Prostor tempPokoj : listPokoju){
            if(tempPokoj.getNazev().equals(jmeno)){
                return true;
            }
        }
        return false;
    }

    /**
     * metoda po zadani jmena vraci konkretni prostor
     * @param name - jmeno prostoru
     * @return prostor
     */
    public Prostor getProstor(String name){
        for (Prostor tempProstor : listPokoju){
            if (tempProstor.getNazev().equals(name)){
                return tempProstor;
            }
        }
        return null;
    }
    public void adToListPokoju(Prostor pokoj){
        this.listPokoju.add(pokoj);
    }


}
