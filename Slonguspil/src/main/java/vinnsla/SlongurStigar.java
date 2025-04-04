package vinnsla;

import java.util.HashMap;

public class SlongurStigar {
    private final HashMap<Integer, Integer> slongurStigar;

    public SlongurStigar() {
        slongurStigar = new HashMap<>();
        //Stigar
        slongurStigar.put(3, 6);
        slongurStigar.put(12, 17);
        slongurStigar.put(9, 21);
        //Slongur
        slongurStigar.put(11, 2);
        slongurStigar.put(19, 8);
        slongurStigar.put(23, 13);
    }

    /**
     * Athugar hvort það sé slanga eða stigi á reit og skilar reitnum sem leikmaður lendir á
     * Ef það er ekki slanga eða stigi á reitnum þá skilar fallið tölunni 0
     * @return int reitur
     */
    public int athugaReit(int reitur) {
        return slongurStigar.getOrDefault(reitur, 0);
    }

    /**
     * Skilar stiga eða slöngu fyrir reit i
     * @param i númer reits
     * @return slanga eða stigi
     */
    public int lookup(int i) {
        return slongurStigar.getOrDefault(i, 0);
    }
}


