package pust.controller.main_window;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.net.URL;
import java.util.ResourceBundle;

public class GoogleMapsController implements Initializable {
    @FXML
    private static WebEngine webEngine;
    @FXML
    private WebView webView;
    @FXML
    private TextField textBox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        webEngine = webView.getEngine();
        loadHtmlGoogle();
    }

    public void loadHtmlGoogle() {

        URL url = this.getClass().getResource("/html/google.html");
        webEngine.load(url.toString());
    }

    public void goToLocation() {
        //set marker
        //set text

        webEngine.executeScript("document.findLocation(\"" + textBox.getText() + "\")");
        Log log = new Log();
        log.saveToFile("SEARCHED GOOGLE MAPS LOCATION FOR: " + textBox.getText());
    }

    public static void goToCrimeLocation(String crime) {

        RandomCrimeSpot randomCrimeSpot = new RandomCrimeSpot();
        Log log = new Log();

        switch (crime) {
            case "Aggravated":
                webEngine.executeScript("document.crimeLocation(\"" + randomCrimeSpot.getCrimeMark()[0].getLatLong() + "\",\"" + randomCrimeSpot.getCrimeMark()[0].getMarkerDescription() + "\")");
                log.saveToFile("OPENED CRIME LOCATION AT: " + randomCrimeSpot.getCrimeMark()[0].getLatLong());
                break;
            case "Vandalism":
                webEngine.executeScript("document.crimeLocation(\"" + randomCrimeSpot.getCrimeMark()[1].getLatLong() + "\",\"" + randomCrimeSpot.getCrimeMark()[1].getMarkerDescription() + "\")");
                log.saveToFile("OPENED CRIME LOCATION AT: " + randomCrimeSpot.getCrimeMark()[1].getLatLong());
                break;
            case "Theft":
                webEngine.executeScript("document.crimeLocation(\"" + randomCrimeSpot.getCrimeMark()[2].getLatLong() + "\",\"" + randomCrimeSpot.getCrimeMark()[2].getMarkerDescription() + "\")");
                log.saveToFile("OPENED CRIME LOCATION AT: " + randomCrimeSpot.getCrimeMark()[2].getLatLong());
                break;
        }
    }
}
