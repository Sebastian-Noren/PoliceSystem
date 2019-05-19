package pust.controller.main_window;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.net.URL;
import java.util.ResourceBundle;

public class GoogleMapsController implements Initializable {
    @FXML
    private WebEngine webEngine;
    @FXML
    private WebView webView;
    @FXML
    private Button currentCrime;

    @FXML
    private TextField textBox;

    private int i = 0;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        webEngine = webView.getEngine();
        loadHtmlGoogle();

        currentCrime.setStyle("-fx-background-color: red;");


    }

    public void loadHtmlGoogle() {

        URL url = this.getClass().getResource("/HTML/google.html");
        webEngine.load(url.toString());
    }


    public void goToLocation() {
        //set marker
        //set text

        webEngine.executeScript("document.findLocation(\"" + textBox.getText() + "\")");
        Log log = new Log();
        log.saveToFile("SEARCHED GOOGLE MAPS LOCATION FOR: " + textBox.getText());
    }

    public void goToCrimeLocation() {
        RandomCrimeSpot randomCrimeSpot = new RandomCrimeSpot();
        Log log = new Log();
        i++;


        switch (i) {
            case 1:
                webEngine.executeScript("document.crimeLocation(\"" + randomCrimeSpot.getCrimeMark()[0].getLatLong() + "\",\"" + randomCrimeSpot.getCrimeMark()[0].getMarkerDescription() + "\")");
                log.saveToFile("OPENED CRIME LOCATION AT: " + randomCrimeSpot.getCrimeMark()[0].getLatLong());
                break;
            case 2:
                webEngine.executeScript("document.crimeLocation(\"" + randomCrimeSpot.getCrimeMark()[1].getLatLong() + "\",\"" + randomCrimeSpot.getCrimeMark()[1].getMarkerDescription() + "\")");
                log.saveToFile("OPENED CRIME LOCATION AT: " + randomCrimeSpot.getCrimeMark()[1].getLatLong());
                break;
            case 3:
                currentCrime.setStyle("-fx-border-color: white;");
                break;
        }


    }


}
