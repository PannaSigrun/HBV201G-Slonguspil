package vinnsla;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.Random;

public class Teningur {
    //Stærð tenings er MAX
    private static final int MAX = 6;
    private final IntegerProperty talaProperty = new SimpleIntegerProperty(MAX);

    /**
     * Kastar tening þannig að fundinn sé tala af handahófi á bilinu 1 til MAX+1
     */
    public void kasta() {
        Random random = new Random();
        talaProperty.set(random.nextInt(MAX) + 1);
    }

    /**
     * Skilar núverandi tölu á teningi
     * @return tala sem er á teningi
     */
    public int getTeningur() {
        return talaProperty.get();
    }

    /**
     * Skilar simple integer property teningsins
     * @return SimpleIntegerProperty
     */
    public IntegerProperty getTeningurProperty() {
        return talaProperty;
    }
}
