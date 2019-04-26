package pust.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import pust.model.AppConstant;
import pust.model.admin_create.AdminCreateModel;
import pust.model.admin_create.AdminDatabase;
import pust.model.admin_create.AdminUserTable;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import java.net.URL;

import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * @author Sebastian Norén <s.norén@gmail.com>
 */
public class AdminScreenController implements Initializable {
    private AdminDatabase sql;
    private AdminCreateModel adCrMo;
    @FXML
    private TextField accFirstName, accLastName, accPass, accConfPass, accSSN, accMail;
    @FXML
    private TableView<AdminUserTable> adminTable;
    @FXML
    private TableColumn<AdminUserTable, String> col_userAcc;
    @FXML
    private CheckBox select, insert, update, delete, grantOption;
    @FXML
    private RadioButton accPoliceRole, accPoliceChiefRole, accITrole;
    @FXML
    private Label labWarFirstname, labWarPass, labWarLastname, labWarConfPass, labelActiveUser, labWarSSN;
    @FXML
    private ImageView profileImg;
    @FXML
    private Image image;
    private ObservableList<AdminUserTable> oblist = FXCollections.observableArrayList();
    private FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter("Image Files (.jpg /.png)", "*.jpg", "*.png");
    private FileChooser fc = new FileChooser();
    BufferedImage bImg = null;
    private String userSelect, policeRole;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        sql = new AdminDatabase();
        adCrMo = new AdminCreateModel();
        fc.getExtensionFilters().add(imageFilter);
        sql.connect();
        col_userAcc.setCellValueFactory(new PropertyValueFactory<>("users"));
        adminTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        adminTable.getSelectionModel().setCellSelectionEnabled(true);
        labelActiveUser.setText(AppConstant.getCurrentUser());
        updateList();
        roleSelect();

    }

    @FXML
    private void createAccBtn() {
        String strFirstName = accFirstName.getText();
        String strLastName = accLastName.getText();
        String strSSN = accSSN.getText();
        String strPass = accPass.getText();
        String strConfPass = accConfPass.getText();
        String strMail = accMail.getText();
        if (emptyFieldsCheck()) {
            displayWarnings(strFirstName, strLastName, strSSN, strPass, strConfPass);
        } else {
            if (strPass.equals(strConfPass)) {
                String randGenUserName = adCrMo.generateNewAccName(strFirstName, strLastName);
                int policeID = adCrMo.generatePoliceId();
                if (sql.insertPoliceSQL(policeID, strSSN, randGenUserName, strMail, policeRole)) {
                    sql.createUsersSQL(randGenUserName, strPass);
                    sql.grantOptionsSQL(adCrMo.grant(select, insert, update, delete, grantOption), randGenUserName);
                    saveImage(randGenUserName);
                    resetTextfields();
                    roleSelect();
                    updateList();
                    image = new Image("photo.jpg", 176.0, 224.0, false, true);
                    profileImg.setImage(image);
                    adCrMo.alertCreateComplete(randGenUserName, policeID);
                }
            } else {
                labWarPass.setText("Password don´t match!");
                labWarConfPass.setText("Password don´t match!");
            }
        }
    }

    @FXML
    private void deleteUser() {
        sql.removeUsersSQL(userSelect);
        updateList();
    }

    @FXML
    private void col_click() {
        try {
            TablePosition tablePosition = adminTable.getSelectionModel().getSelectedCells().get(0);
            int row = tablePosition.getRow();
            AdminUserTable item = adminTable.getItems().get(row);
            TableColumn tableColumn = tablePosition.getTableColumn();
            userSelect = (String) tableColumn.getCellObservableValue(item).getValue();
            System.err.println(userSelect);
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void roleSelect() {
        if (accPoliceRole.isSelected()) {
            delete.setSelected(false);
            delete.setDisable(true);
            grantOption.setSelected(false);
            grantOption.setDisable(true);
            policeRole = "Police officer";

        }
        if (accPoliceChiefRole.isSelected()) {
            delete.setDisable(false);
            delete.setSelected(true);
            grantOption.setDisable(true);
            grantOption.setSelected(false);
            policeRole = "Police chief";
        }
        if (accITrole.isSelected()) {
            delete.setDisable(false);
            delete.setSelected(true);
            grantOption.setSelected(true);
            grantOption.setDisable(false);
            policeRole = "IT-administrator";
        }
        select.setSelected(true);
        insert.setSelected(true);
        update.setSelected(true);
    }

    @FXML
    private void returnLogin(ActionEvent actionEvent) {
        SceneSwitch sceneSwitch = new SceneSwitch();
        sceneSwitch.goToLogin(actionEvent);
    }

    @FXML
    private void typingReset() {
        resetLabels();
    }

    @FXML
    private void browseImg() {
        File file = fc.showOpenDialog(null);
        try {
            if (file != null) {
                String strImage = file.toURI().toString();
                image = new Image(strImage, 176.0, 224.0, false, true);
                profileImg.setImage(image);
                strImage = strImage.replace("file:/", "");
                System.out.println(strImage);
                bImg = ImageIO.read(new FileInputStream(strImage));
            }

        } catch (IOException e) {
            //e.printStackTrace();
        }
    }

    private void updateList() {
        adminTable.getItems().clear();
        ArrayList<String> adminUsers = sql.getUsersAdmin();
        for (String g : adminUsers) {
            oblist.add(new AdminUserTable(g));
        }
        adminTable.setItems(oblist);
        resetLabels();
    }

    private void resetLabels() {
        labWarFirstname.setText("");
        labWarLastname.setText("");
        labWarSSN.setText("");
        labWarPass.setText("");
        labWarConfPass.setText("");
    }

    private void resetTextfields() {
        accFirstName.clear();
        accLastName.clear();
        accSSN.clear();
        accMail.clear();
        accPass.clear();
        accConfPass.clear();
        accPoliceRole.setSelected(true);
    }

    private boolean emptyFieldsCheck() {
        return accFirstName.getText().isEmpty() ||
                accLastName.getText().isEmpty() ||
                accSSN.getText().isEmpty() ||
                accPass.getText().isEmpty() ||
                accConfPass.getText().isEmpty();
    }

    private void displayWarnings(String strFirstName, String strLastName, String strSSN, String strPass, String strConfPass) {
        if (strFirstName.isEmpty()) {
            labWarFirstname.setText("You need to fill out this field!");
        }
        if (strLastName.isEmpty()) {
            labWarLastname.setText("You need to fill out this field!");
        }
        if (strSSN.isEmpty() || strSSN.length() < 12 || strSSN.length() > 12) {
            labWarSSN.setText("SSN needs to be 12 characters!");
        }
        if (strPass.isEmpty()) {
            labWarPass.setText("Needs a password!");
        }
        if (strConfPass.isEmpty()) {
            labWarConfPass.setText("Confirm your password!");
        }
    }

    private void saveImage(String randGenUserName) {

        //TODO Funkar bara om det inte är mellanrum Mappar(filer??
        String str = AppConstant.SAVE_FOLDER_PATH + randGenUserName + ".png";
        try {
            if (bImg != null) {
                BufferedImage resized = adCrMo.resize(bImg, 224, 176);
                File outputFile = new File(str);
                ImageIO.write(resized, "png", outputFile);
                bImg = null;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
