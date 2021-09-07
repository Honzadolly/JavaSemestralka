package logika;

import java.util.ArrayList;
import java.util.List;

/**
 * tridy kapsy funguje jako prostor, kam se ukladaji jednotlive
 * itemy v prubehu hrani. (inventar)
 */
public class Kapsy {
    private List<Item> itemy;
    private int size = 2;
    private boolean klic = false;
    private boolean klicKoupelna = false;
    private boolean pullitr = false;


    /**
     * konstruktor tridy
     */
    public Kapsy(){
        this.itemy = new ArrayList<>();
    }

    /**
     * vklada item do pole kapsy
     * @param item
     */
    public void vloz(Item item) {
        itemy.add(item);
    }

    /**
     * odstraní item z kapsy
     * @param item
     */
    public void odstran(Item item){
        itemy.remove(item);
    }

    /**
     * metoda navraci seznam predmetu, ktere se aktualne nachazeji v kapsach
     * @return seznam predmetu, ktere se nachazeji v kapse
     */
    public List<String> obsah(){
        List result = new ArrayList();
        for (Item item : itemy){
            result.add(item.getNazev());
        }
        return result;
    }

    /**
     * metoda navy3uje velikost kapes o 5
     */
    public void setSize(){
        size += 5;
    }

    /**
     * metoda pocet zaplnenych mist v kapsach
     * @return velikost zaplneneho pole
     */
    public int getSize() {
        return itemy.size();
    }

    /**
     * metoda vraci celkovou velikost kapes
     * @return promennou size
     */
    public int getTotalSize(){
        return size;
    }

    /**
     * metoda po zadani stringu vraci item, ktery odpovida tomuto stringu, pokud neni
     * v kapsach, vraci null
     * @param name
     * @return item se jmenem name
     */
    public Item findItem(String name){
        for (Item tempItem : itemy){
            if (tempItem.getNazev().equals(name)){
                return tempItem;
            }
        }
        return null;
    }

    /**
     * metoda po zadani stringu vraci boolean, zda je takovy predmet v kapsach
     * @param name
     * @return true/false
     */
    public boolean isInKapsy(String name){
        for (Item item : itemy){
            if (item.getNazev().equals(name)){
                return true;
            }
        }
        return false;
    }

    /**
     * metoda nastavi atribut klic(pipa) na true, mohu pak korigovat, zda jiz byl klic sebran
     */
    public void setKlicTrue(){
        this.klic = true;
    }

    /**
     * metoda vrati, zda jiz klic(pipa) byl sebran(true) ci ne(false)
     * @return false/true
     */
    public boolean getKlic(){
        return this.klic;
    }

    /**
     * metoda nastavi atribut klicKoupelna na true, mohu pak korigovat, zda byl klic sebran
     */
    public void setKlicKoupelnaTrue(){
        this.klicKoupelna = true;
    }

    /**
     * metoda vrati, zda jiz klicKoupelna byl sebran(true) ci ne(false)
     * @return true/false
     */
    public boolean getKlicKoupelna(){
        return this.klicKoupelna;
    }

    /**
     * metoda nastavi atrribut pullitr na true, umožní korigovat stav sebrání půllitru
     */
    public void setPullitrTrue(){
        this.pullitr = true;
    }

    /**
     * metoda vraci, zda se v iventari nachazi(true) ci nenachazi(false) nejaky z pullitru
     * @return true/false
     */
    public boolean getPullitr(){
        for (Item pullitr : itemy){
            String nazev = pullitr.getNazev();
            if (nazev.equals("sklenenyPullitr") || nazev.equals("plastovyPullitr")){
                return true;
            }
        }
        return false;
    }



}
