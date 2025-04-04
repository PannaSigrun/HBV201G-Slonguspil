package vinnsla;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Leikmadur {
    //GÆTI ÞURFT AÐ BÆTA VIÐ GETTERS OG SETTERS?
    private final StringProperty leikmadurProperty = new SimpleStringProperty();
    private final IntegerProperty stadaProperty = new SimpleIntegerProperty(2);

    public Leikmadur(String nafn) {
        leikmadurProperty.set(nafn);
        stadaProperty.set(0);
    }

    /**
     * Færir leikmann á reit reitur en markið er í reit max
     * @param reitur reitur sem leikmaður er færður á
     * @param max mark-reitur á borðinu
     */
    public void faera(int reitur, int max) {
        if (reitur >= max) {
            stadaProperty.set(max);
        }
        else {
            stadaProperty.set(reitur);
        }
    }

    /**
     * Skilar nafni leikamanns sem String breytu
     * @return nafn leikmanns
     */
    public String getNafn() {
        return leikmadurProperty.get();
    }

    /**
     * Skilar stöðu leikamanns sem int breytu
     * @return staða leikmanns
     */
    public int getStada() {
        return stadaProperty.get();
    }

    /**
     * Skilar stöðu leikamanns sem IntegerProperty breytu
     * @return staða leikmanns
     */
    public IntegerProperty getStadaProperty() {
        return stadaProperty;
    }
}
