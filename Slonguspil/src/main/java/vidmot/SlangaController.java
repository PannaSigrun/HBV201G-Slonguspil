package vidmot;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import vinnsla.Leikur;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class SlangaController implements Initializable {

    @FXML
    private GridPane fxBord;
    @FXML
    private Button fxNyrLeikurButton;
    @FXML
    private Button fxTeningurButton;

    private Leikur leikur;
    private List<Node> reitir; //Tilviksbreyta fyrir reitina

    private int whiteRedBlueYellow = 0;
    final String[] myndir = {"one", "two", "three", "four", "five", "six"};
    final String[] raudarmyndir = {"redone", "redtwo", "redthree", "redfour", "redfive", "redsix"};
    final String[] blaarmyndir =  {"blueone", "bluetwo", "bluethree", "bluefour", "bluefive", "bluesix"};
    final String[] gularmyndir =  {"yellowone", "yellowtwo", "yellowthree", "yellowfour", "yellowfive", "yellowsix"};

    String[] teningaMyndir = myndir;

    public static final String SLANGA = "slanga";
    public static final String STIGI = "stigi";

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        leikur = new Leikur(24);
        reitir = fxBord.getChildren(); //Röðin er sú sama og í fxml skránni

        //Binda fxNyrLeikur disableProperty við stöðuna á leiknum (leik lokið eða ekki) í Leikur
        fxNyrLeikurButton.disableProperty().bind(leikur.leikLokidProperty().not());

        //Binda fxTeningur diableProperty við fxNyrLeikur disableProperty
        fxTeningurButton.disableProperty().bind(fxNyrLeikurButton.disableProperty().not());

        //Binda fxTeningur við töluna á teningnum við myndina sem birtist á teningnum
        //Listener fyrir teninginn
        leikur.getTeningur().addListener((obs, gamlaGildi, nyttGildi) -> {
            fxTeningurButton.getStyleClass().remove(teningaMyndir[gamlaGildi.intValue() - 1]);
            fxTeningurButton.getStyleClass().add(teningaMyndir[nyttGildi.intValue() - 1]);
        });

        // bindur myndir leikmanna við reitina
        String[] leikmadurStill = {"leikmadur0", "leikmadur1"};
        leikur.getLeikmadur(0).getStadaProperty().addListener((obs, gamlaGildi, nyttGildi) -> {
            reitir.get(gamlaGildi.intValue() - 1).getStyleClass().remove(leikmadurStill[0]);
            reitir.get(nyttGildi.intValue() - 1).getStyleClass().add(leikmadurStill[0]);
        });

        leikur.getLeikmadur(1).getStadaProperty().addListener((obs, gamlaGildi, nyttGildi) -> {
            reitir.get(gamlaGildi.intValue() - 1).getStyleClass().remove(leikmadurStill[1]);
            reitir.get(nyttGildi.intValue() - 1).getStyleClass().add(leikmadurStill[1]);
        });
    }

    @FXML
    protected void nyrLeikurHandler(ActionEvent event) {
        leikur.nyrLeikur();
    }

    @FXML
    protected void teningurHandler(ActionEvent event) {
        leikur.leikaLeik();
    }

    @FXML
    protected void onBreytaLitClick(ActionEvent event) {
        //Breytir um lit á teningnum
        whiteRedBlueYellow += 1;
        if (whiteRedBlueYellow == 4) {
            whiteRedBlueYellow = 0;
        }
        fxTeningurButton.getStyleClass().remove(teningaMyndir[leikur.getTeningur().get() - 1]);

        if (whiteRedBlueYellow == 0) {
            teningaMyndir = myndir;
        } else if (whiteRedBlueYellow == 1) {
            teningaMyndir = raudarmyndir;
        } else if (whiteRedBlueYellow == 2) {
            teningaMyndir = blaarmyndir;
        } else if (whiteRedBlueYellow == 3) {
            teningaMyndir = gularmyndir;
        }

        fxTeningurButton.getStyleClass().add(teningaMyndir[leikur.getTeningur().get() - 1]);
    }

    /**
     * Setja myndir fyrir slöngu eða stiga á reitina
     */
    private void setjaStigaSlanga() {
        List<Node> reitir = fxBord.getChildren();
        for (Node r : reitir) {
            int i = reitir.indexOf(r);
            int gildi = leikur.getSlongurOgStigar().lookup(i + 1);
            if (gildi != 0) {
                if (gildi < i + 1) {
                    r.getStyleClass().add(SLANGA);
                } else if (gildi > i + 1) {
                    r.getStyleClass().add(STIGI);
                }
            }
        }
    }
}