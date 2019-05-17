package pust.model.login;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PopupControl;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;
import pust.controller.PopupController;

import java.io.IOException;
import java.util.HashMap;


public class LogInModel {

    PopupController pop = new PopupController();


    public LogInModel() throws IOException {
        FXMLLoader loader = FXMLLoader.load(getClass().getResource("/view/passwordPopup.fxml"));
    }


    // this method takes the count of failed log in attempts and displays appropriate messsage
    public String passwordCounter(int wrongPass) {
        if (wrongPass > 0 && wrongPass < 3) {
            return "Incorrect username or password";
        } else if (wrongPass >= 3) {
            return "warning";
        } else {
            return "";
        }
    }

    public void resetPassword() {

      Popup popup = new Popup();
      popup.show();
    }

   /* private HashMap<String, Object> showPopupWindow() {
        HashMap<String, Object> resultMap = new HashMap<String, Object>();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Popup.fxml"));
        // initializing the controller
        PopupController popupController = new PopupController();
        loader.setController(popupController);
        Parent layout;
        try {
            layout = loader.load();
            Scene scene = new Scene(layout);
            // this is the popup stage
            Stage popupStage = new Stage();
            // Giving the popup controller access to the popup stage (to allow the controller to close the stage)
            popupController.setStage(popupStage);
            if(this.main!=null) {
                popupStage.initOwner(main.getPrimaryStage());
            }
            popupStage.initModality(Modality.WINDOW_MODAL);
            popupStage.setScene(scene);
            popupStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return popupController.getResult();
    }*/


}

