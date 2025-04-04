package vinnsla;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Leikur {
    private int MAX_REITIR;
    private Leikmadur leikmadur0;
    private Leikmadur leikmadur1;
    private Teningur teningur;
    private SlongurStigar slongurOgStigar;
    private final SimpleBooleanProperty leikLokid = new SimpleBooleanProperty(true);
    private final SimpleStringProperty sigurvegari = new SimpleStringProperty();
    private final SimpleIntegerProperty hverAAdGera = new SimpleIntegerProperty();
    private final SimpleStringProperty slangaEdaStigi = new SimpleStringProperty();

    public Leikur(int fljoldiReita) {
        MAX_REITIR = fljoldiReita;
        leikmadur0 = new Leikmadur("Leikmadur0");
        leikmadur1 = new Leikmadur("Leikmadur1");
        teningur = new Teningur();
        slongurOgStigar = new SlongurStigar();
    }

    /**
     * Hefur nýjan leik. Leikmenn settir á reit eitt
     */
    public void nyrLeikur() {
        leikmadur0.faera(1, MAX_REITIR);
        leikmadur1.faera(1, MAX_REITIR);
        hverAAdGera.set(0);
        leikLokid.set(false);
    }

    /**
     * Kastar tening, færir leikmann, setur næsta leikmann
     *
     * @return skilar true ef leik er lokið
     */
    public boolean leikaLeik() {
        teningur.kasta();
        if (hverAAdGera.get() == 0) {
            faeraLeikmann(leikmadur0);
            slangaEdaStigi.set(erSlangaEdaStigi(leikmadur0));
            erLeikmadurSigurvegar(leikmadur0);
            hverAAdGera.set(1);
        }
        else if (hverAAdGera.get() == 1) {
            faeraLeikmann(leikmadur1);
            slangaEdaStigi.set(erSlangaEdaStigi(leikmadur1));
            erLeikmadurSigurvegar(leikmadur1);
            hverAAdGera.set(0);
        }
        return leikLokid.get();
    }

    /**
     * Færir leikmann á næsta reit
     * @param leikmadur leikmaður sem á að færa
     */
    public void faeraLeikmann(Leikmadur leikmadur) {
        int nyrReitur = leikmadur.getStada() + teningur.getTeningur();
        leikmadur.faera(nyrReitur, MAX_REITIR);
    }

    /**
     * Athugar hvort það sé slanga eða stigi á reitnum sem leikmaðurinn stendur á
     * @param leikmadur leikmaður sem á að færa
     */
    public String erSlangaEdaStigi(Leikmadur leikmadur) {
        int nyrReitur = slongurOgStigar.athugaReit(leikmadur.getStada());
        if (nyrReitur < leikmadur.getStada() && nyrReitur != 0) {
            leikmadur.faera(nyrReitur, MAX_REITIR);
            return "slanga";
        } else if (nyrReitur > leikmadur.getStada()) {
            leikmadur.faera(nyrReitur, MAX_REITIR);
            return "stigi";
        }
        return "";
    }

    /**
     * Athugar hvort leikmaður sé sigurvegari
     * @param leikmadur leikmaður sem á að athuga
     */
    public void erLeikmadurSigurvegar(Leikmadur leikmadur) {
        if (leikmadur.getStada() == MAX_REITIR) {
            leikLokid.set(true);
            sigurvegari.set(leikmadur.getNafn());
        }
    }

    /**
     * Skilar nafni sigurvegara sem String breytu
     * @return nafn leikmanns
     */
    public String getSigurvegari() {
        return sigurvegari.get();
    }

    /**
     * Skilar booelan property sem segir til um hvort leik sé lokið
     * @return leikLokið SimpleBooleanProperty
     */
    public SimpleBooleanProperty leikLokidProperty() {
        return leikLokid;
    }

    /**
     * Skilar leikmanni 0 eða 1
     * @param i annað hvort 0 eða 1
     * @return leikmaður
     */
    public Leikmadur getLeikmadur(int i) {
        if (i == 0) {
            return leikmadur0;
        }
        else if (i == 1){
            return leikmadur1;
        }
        else {
            return null;
        }
    }

    /**
     * Skilar simple integer property teningsins
     * @return SimpleIntegerProperty
     */
    public IntegerProperty getTeningur() {
        return teningur.getTeningurProperty();
    }

    public SlongurStigar getSlongurOgStigar() {
        return slongurOgStigar;
    }
}
