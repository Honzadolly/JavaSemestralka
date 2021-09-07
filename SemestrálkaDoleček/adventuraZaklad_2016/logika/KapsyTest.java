package logika;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class KapsyTest {
    Kapsy kapsy = new Kapsy();
    Item item1 = new Item("noha");
    Item item2 = new Item("kolo");
    Item item3 = new Item("bok");
    Item item4 = new Item("sklenenyPullitr");
    Item item5 = new Item("plastovyPullitr");

    List<String> list1 = new ArrayList<>();


    /**
     * Metoda otestuje zakladni operace
     *      vloz
     *      odstran
     *      obsah
     *      findItem
     */
    @Test
    public void zakladniOperace(){
        //add, remove, size
        assertEquals(0, kapsy.getSize());
        kapsy.vloz(item1);
        assertEquals(1, kapsy.getSize());
        kapsy.vloz(item2);
        assertEquals(2, kapsy.getSize());
        kapsy.odstran(item1);
        assertEquals(1, kapsy.getSize());
        list1.add(item2.getNazev());

        assertEquals(list1, kapsy.obsah());
        assertEquals(item2, kapsy.findItem("kolo"));

        assertEquals(false, kapsy.isInKapsy("noha"));
        assertEquals(true, kapsy.isInKapsy("kolo"));
        kapsy.odstran(item2);
        assertEquals(false, kapsy.isInKapsy("kolo"));
    }

    /**
     * metoda otestuje operace tykajici se velikosti kapes
     *      getSize
     *      getTotalSize
     *      setSize
     */
    @Test
    public void sizes() {
        assertEquals(0, kapsy.getSize());
        kapsy.vloz(item1);
        assertEquals(1, kapsy.getSize());
        assertEquals(2, kapsy.getTotalSize());
        kapsy.setSize();
        assertEquals(7, kapsy.getTotalSize());
    }

    /**
     * metoda otestuje operace tykajici se boolean promennych
     *      getKey
     *      setKey
     *      getKeyKoupelna
     *      setKeyKoupelna
     *      getPullitr
     */
    @Test
    public void keyTests() {
        assertEquals(false, kapsy.getKlic());
        assertEquals(false, kapsy.getKlicKoupelna());
        kapsy.setKlicTrue();
        kapsy.setKlicKoupelnaTrue();
        assertEquals(true, kapsy.getKlic());
        assertEquals(true, kapsy.getKlicKoupelna());
        kapsy.vloz(item4);
        assertEquals(true, kapsy.getPullitr());
        kapsy.odstran(item4);
        assertEquals(false, kapsy.getPullitr());
    }

}