package logika;


/**
 * Trida item reprezentuje jednotlive itemy v prubehu cele hry
 */
public class Item {
    private String nazev;
    private boolean status;

    /**
     * konstruktor tridy
     * @param nazev
     */
    public Item(String nazev) {
        this.nazev = nazev;
    }


    /**
     * @return nazev tridy
     */
    public String getNazev() {
        return nazev;
    }
}
