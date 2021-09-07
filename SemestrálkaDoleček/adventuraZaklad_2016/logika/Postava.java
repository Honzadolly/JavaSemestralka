package logika;

/**
 * Trida slouzici pro reprezentaci postav
 */
public class Postava {
    private String name;

    /**
     * konstruktor pro vytvoreni postavy
     * @param name
     */
    public Postava(String name) {
        this.name = name;
    }

    /**
     * metoda vraci jmeno postavy
     * @return jmeno postavy
     */
    public String getName() {
        return name;
    }
}
