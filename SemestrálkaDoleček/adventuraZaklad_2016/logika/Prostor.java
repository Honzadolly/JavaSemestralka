package logika;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Trida Prostor - popisuje jednotlivé prostory (místnosti) hry
 *
 * Tato třída je součástí jednoduché textové hry.
 *
 * "Prostor" reprezentuje jedno místo (místnost, prostor, ..) ve scénáři hry.
 * Prostor může mít sousední prostory připojené přes východy. Pro každý východ
 * si prostor ukládá odkaz na sousedící prostor.
 *
 * @author Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova
 * @version pro školní rok 2016/2017
 */
public class Prostor {

    private String nazev;
    private String popis;
    private Set<Prostor> vychody;   // obsahuje sousední místnosti
    private List<Item> listItemu; //seznam itemu, ktere jsou v mistnosti
    private List<Postava> listPostav; //seznam postav v mistnosti
    private boolean locked;
    /**
     * Vytvoření prostoru se zadaným popisem, např. "kuchyň", "hala", "trávník
     * před domem"
     *
     * @param nazev nazev prostoru, jednoznačný identifikátor, jedno slovo nebo
     * víceslovný název bez mezer.
     * @param popis Popis prostoru.
     */
    public Prostor(String nazev, String popis, boolean zamceny) {
        this.nazev = nazev;
        this.popis = popis;
        this.locked = zamceny;
        vychody = new HashSet<>();
        listItemu = new ArrayList<>();
        listPostav = new ArrayList<>();
    }

    /**
     * Definuje východ z prostoru (sousední/vedlejsi prostor). Vzhledem k tomu,
     * že je použit Set pro uložení východů, může být sousední prostor uveden
     * pouze jednou (tj. nelze mít dvoje dveře do stejné sousední místnosti).
     * Druhé zadání stejného prostoru tiše přepíše předchozí zadání (neobjeví se
     * žádné chybové hlášení). Lze zadat též cestu ze do sebe sama.
     *
     * @param vedlejsi prostor, který sousedi s aktualnim prostorem.
     *
     */
    public void setVychod(Prostor vedlejsi) {
        vychody.add(vedlejsi);
    }

    /**
     * Metoda equals pro porovnání dvou prostorů. Překrývá se metoda equals ze
     * třídy Object. Dva prostory jsou shodné, pokud mají stejný název. Tato
     * metoda je důležitá z hlediska správného fungování seznamu východů (Set).
     *
     * Bližší popis metody equals je u třídy Object.
     *
     * @param o object, který se má porovnávat s aktuálním
     * @return hodnotu true, pokud má zadaný prostor stejný název, jinak false
     */  
      @Override
    public boolean equals(Object o) {
        // porovnáváme zda se nejedná o dva odkazy na stejnou instanci
        if (this == o) {
            return true;
        }
        // porovnáváme jakého typu je parametr 
        if (!(o instanceof Prostor)) {
            return false;    // pokud parametr není typu Prostor, vrátíme false
        }
        // přetypujeme parametr na typ Prostor 
        Prostor druhy = (Prostor) o;

        //metoda equals třídy java.util.Objects porovná hodnoty obou názvů. 
        //Vrátí true pro stejné názvy a i v případě, že jsou oba názvy null,
        //jinak vrátí false.

       return (java.util.Objects.equals(this.nazev, druhy.nazev));       
    }

    /**
     * metoda hashCode vraci ciselny identifikator instance, ktery se pouziva
     * pro optimalizaci ukladani v dynamickych datovych strukturach. Pri
     * prekryti metody equals je potreba prekryt i metodu hashCode. Podrobny
     * popis pravidel pro vytvareni metody hashCode je u metody hashCode ve
     * tride Object
     */
    @Override
    public int hashCode() {
        int vysledek = 3;
        int hashNazvu = java.util.Objects.hashCode(this.nazev);
        vysledek = 37 * vysledek + hashNazvu;
        return vysledek;
    }
      

    /**
     * Vrací název prostoru (byl zadán při vytváření prostoru jako parametr
     * konstruktoru)
     *
     * @return název prostoru
     */
    public String getNazev() {
        return nazev;       
    }

    /**
     * Vrací "dlouhý" popis prostoru, který může vypadat následovně: Jsi v
     * mistnosti/prostoru vstupni hala budovy VSE na Jiznim meste. vychody:
     * chodba bufet ucebna
     *
     * @return Dlouhý popis prostoru
     */
    public String dlouhyPopis() {
        return "Jsi v mistnosti " + nazev + ".\n\n" + popis + ".\n"
                + popisVychodu();
    }

    /**
     * Vrací textový řetězec, který popisuje sousední východy, například:
     * "vychody: hala ".
     *
     * @return Popis východů - názvů sousedních prostorů
     */
    private String popisVychodu() {
        String vracenyText = "východy:";
        boolean slova = false;
        for (Prostor sousedni : vychody) {
            if (!slova) {
                vracenyText +=" " + sousedni.getNazev();
                slova = true;
            } else{
                vracenyText += ", " + sousedni.getNazev();
            }
        }
        return vracenyText;
    }

    /**
     * Vrací prostor, který sousedí s aktuálním prostorem a jehož název je zadán
     * jako parametr. Pokud prostor s udaným jménem nesousedí s aktuálním
     * prostorem, vrací se hodnota null.
     *
     * @param nazevSouseda Jméno sousedního prostoru (východu)
     * @return Prostor, který se nachází za příslušným východem, nebo hodnota
     * null, pokud prostor zadaného jména není sousedem.
     */
    public Prostor vratSousedniProstor(String nazevSouseda) {
        List<Prostor>hledaneProstory = 
            vychody.stream()
                   .filter(sousedni -> sousedni.getNazev().equals(nazevSouseda))
                   .collect(Collectors.toList());
        if(hledaneProstory.isEmpty()){
            return null;
        }
        else {
            return hledaneProstory.get(0);
        }
    }

    /**
     * Vrací kolekci obsahující prostory, se kterými tento prostor sousedí.
     * Takto získaný seznam sousedních prostor nelze upravovat (přidávat,
     * odebírat východy) protože z hlediska správného návrhu je to plně
     * záležitostí třídy Prostor.
     *
     * @return Nemodifikovatelná kolekce prostorů (východů), se kterými tento
     * prostor sousedí.
     */
    public Collection<Prostor> getVychody() {
        return Collections.unmodifiableCollection(vychody);
    }

    /**
     * metoda zjistuje, zda je mistnost zamcena ci ne podle statusu Locked u kazde mistnosti
     * @return true/false
     */
    public boolean isLocked() {
        return locked;
    }

    /**
     * metoda po zadani parametru item vrati zda v mistnosti je(true) ci neni (false)
     * @param item - vstupujici item
     * @return true/false
     */
    public boolean jeVMistnosti(Item item){
        for (Item temp : listItemu){
            if (temp.equals(item)){
                return true;
            }
        }
        return false;
    }

    /**
     * metoda po zadani itemu vklada item do mistnosti
     * @param item - item vstupujici do metody
     */
    public void addToListItemu(Item item){
        this.listItemu.add(item);
    }

    /**
     * metoda vklada postavu do mistnosti
     * @param postava - postava, kterou chceme pridat do mistnosti
     */
    public void addToListNPC(Postava postava){
        this.listPostav.add(postava);
    }

    /**
     * metoda vraci seznam itemu, ktere se nachazi v mistnosti
     * @return seznam itemu v mistnosti
     */
    public List<Item> getListItemu() {
        return listItemu;
    }

    /**
     * metoda vraci seznam postav, ktere se nachazeji v mistnosti
     * @return seznam postav v mistnosti
     */
    public List<Postava> getListPostav() {
        return listPostav;
    }

    /**
     * metoda vymaze ze seznamu itemu v mistnosti dany item
     * @param item - item, ktery cheme vyhodit z kapes
     */
    public void removeFromListItemu(Item item){
        listItemu.remove(item);
    }

    /**
     * @param name - jmeno predmetu, ktery chceme hledat
     * @return pokud najde item, vraci tento predmet ve tride item, pokud ne, vraci null
     */
    public Item findItem(String name){
        for (Item tempItem : listItemu){
            if (tempItem.getNazev().equals(name)){
                return tempItem;
            }
        }
        return null;
    }

    /**
     * @param name - jmeno npc, ktere chceme hledat
     * @return vrati postavu s danym jmenem, pripadne null
     */
    public Postava findNPC(String name){
        for (Postava tempPostava : listPostav){
            if (name.equals(tempPostava.getName())){
                return tempPostava;
            }
        }
        return null;
    }

    /**
     * metoda zmeni statu Locked na false a tim ji odemkne
     */
    public void odemkniMistnost(){
        this.locked = false;
    }

    /**
     * metoda slouzi hlavne ke zmene popisu jednotlivych mistnosti k tomu, aby pribeh nebyl tak staticky
     * @param popis - text, ktery chceme, aby se zobrazoval
     */
    public void setPopis(String popis){
        this.popis = popis;
    }

    public String getPopis(){
        return this.popis;
    }


}
