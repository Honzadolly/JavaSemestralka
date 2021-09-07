package logika;

/**
 *  Třída Hra - třída představující logiku adventury.
 * 
 *  Toto je hlavní třída  logiky aplikace.  Tato třída vytváří instanci třídy HerniPlan, která inicializuje mistnosti hry
 *  a vytváří seznam platných příkazů a instance tříd provádějící jednotlivé příkazy.
 *  Vypisuje uvítací a ukončovací text hry.
 *  Také vyhodnocuje jednotlivé příkazy zadané uživatelem.
 *
 *@author     Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova
 *@version    pro školní rok 2016/2017
 */

public class Hra implements IHra {
    private SeznamPrikazu platnePrikazy;    // obsahuje seznam přípustných příkazů
    private HerniPlan herniPlan;
    private boolean konecHry = false;
    private GameLog log;


    /**
     *  Vytváří hru a inicializuje místnosti (prostřednictvím třídy HerniPlan) a seznam platných příkazů.
     */
    public Hra() {
        herniPlan = new HerniPlan();
        platnePrikazy = new SeznamPrikazu();
        platnePrikazy.vlozPrikaz(new PrikazNapoveda(platnePrikazy));
        platnePrikazy.vlozPrikaz(new PrikazJdi(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazSeber(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazObsahKapes(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazHledej(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazZahod(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazPouzij(herniPlan, this));
        platnePrikazy.vlozPrikaz(new PrikazZahraj(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazMluv(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazBojuj(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazRozbehnout(herniPlan, this));
        platnePrikazy.vlozPrikaz(new PrikazNacepuj(herniPlan, this));
        platnePrikazy.vlozPrikaz(new PrikazUloz(this));
        platnePrikazy.vlozPrikaz(new PrikazNacti(this));
        platnePrikazy.vlozPrikaz(new PrikazKonec(this));
        this.log = new GameLog();
    }

    /**
     *  Vrátí úvodní zprávu pro hráče.
     */
    public String vratUvitani() {
        return "=============================================================================================================" +
                "\nVítejte ve hře!\n\n" +
                "\t\tJedenáctka Jižní město\n\n" +
                "\tNapište 'nápověda', pokud si nevíte rady, jak hrát dál.\n" +
                "\nZde jsou uvedeny základní příkazy sloužící k pohybu po mapě:" +
                "\n\t jdi [mistnost] ||| hledej ||| kapsy ||| kapsy [volno/obsazeno] ||| seber [predmet] ||| zahod [predmet] ||| uloz |||"  +
                "\n\n\tPokud se ve tvém počítači nachází nějaký uložený postup, je možné ho načíst příkazem ||| nacti |||" +
                "\n\nV průběhu hry se ale mohou odemknout i příkazy nové!" +
                "\n\nPřeji příjemnou zábavu a šťastou hru" +
                "\n---------" +
                "\n\nProbudil ses ve svém pokoji a v krku máš sucho. Před očima máš jasnou vidinu vychlazené\n" +
                "jedenáctky, a to rovnou z čepu!" +
                "\nÚkol je jasný, najdi pokoj, ve kterém se nachází pípa. V té zbývá ještě pár doušků oné pochutiny.\n" +
                "Ale pozor, tvá cesta bude plná strastí a utrpení.\n" +
                "Jednou z mnoha překážek je například Paní uklízečka spolu se svou kolegyňkou. Ta ti ukradla klíče\n" +
                "od koupelny, ve které máš své oblíbené půllitry.\n\n" +
                "Celé hra je zakončena úspěšným nalezením bájné místnosti a přelstěním paní vedoucí, která se\n" +
                "potuluje po areálu a snaží se k oné pípě dostat dříve než ty!\n\n" +
                "V průběhu hry můžeš narazit na takzvané „Easter eggs“, ty dokážou okolnosti hry hodně i pozměnit.\n" +
                "==============================================================================================================\n" +
                herniPlan.getAktualniProstor().dlouhyPopis();
    }

    /**
     *  Vrátí závěrečnou zprávu pro hráče.
     */
    public String vratEpilog() {
        return "\n==================================================================================\n" +
                "\n\tMoc děkuji za hraní hry, pokud se Vám líbila, příští rok očekávejte update ve\n" +
                "\tformě grafického prostředí! Na to si ale musíte počkat.\n" +
                "\t\t=======================\n" +
                "\t\t     Naviděnou!!      \n" +
                "\t\t======================";
    }

    /** 
     * Vrací true, pokud hra skončila.
     */
     public boolean konecHry() {
        return konecHry;
    }

    /**
     *  Metoda zpracuje řetězec uvedený jako parametr, rozdělí ho na slovo příkazu a další parametry.
     *  Pak otestuje zda příkaz je klíčovým slovem  např. jdi.
     *  Pokud ano spustí samotné provádění příkazu.
     *
     *@param  radek  text, který zadal uživatel jako příkaz do hry.
     *@return          vrací se řetězec, který se má vypsat na obrazovku
     */
     public String zpracujPrikaz(String radek) {
        String [] slova = radek.split("[ \t]+");
        String slovoPrikazu = slova[0];
        String []parametry = new String[slova.length-1];
        for(int i=0 ;i<parametry.length;i++){
           	parametry[i]= slova[i+1];  	
        }
        String textKVypsani=" .... ";
        if (platnePrikazy.jePlatnyPrikaz(slovoPrikazu)) {
            IPrikaz prikaz = platnePrikazy.vratPrikaz(slovoPrikazu);
            textKVypsani = prikaz.provedPrikaz(parametry);
        }
        else {
            textKVypsani="Nevím co tím myslíš? Tento příkaz neznám. ";
        }
        return textKVypsani;
    }
    
    
     /**
     *  Nastaví, že je konec hry, metodu využívá třída PrikazKonec,
     *  mohou ji použít i další implementace rozhraní Prikaz.
     *  
     *  @param  konecHry  hodnota false= konec hry, true = hra pokračuje
     */
    void setKonecHry(boolean konecHry) {
        this.konecHry = konecHry;
    }
    
     /**
     *  Metoda vrátí odkaz na herní plán, je využita hlavně v testech,
     *  kde se jejím prostřednictvím získává aktualní místnost hry.
     *  
     *  @return     odkaz na herní plán
     */
     public HerniPlan getHerniPlan(){
        return herniPlan;
     }

     public GameLog getGameLog(){
         return this.log;
     }
    
}

