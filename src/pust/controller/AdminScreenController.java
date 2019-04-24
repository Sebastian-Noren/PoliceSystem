package pust.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import pust.SceneSwitch;
import pust.model.admin_create.AdminCreateModel;
import pust.model.admin_create.AdminDatabase;
import pust.model.admin_create.AdminUserTable;

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
    private TextField accFirstName, accLastName, accPass, accConfPass;
    @FXML
    private TableView<AdminUserTable> adminTable;
    @FXML
    private TableColumn<AdminUserTable, String> col_userAcc;
    @FXML
    private CheckBox select, insert, update, delete, grantOption;
    @FXML
    private RadioButton accPoliceRole, accPoliceChiefRole, accITrole;
    @FXML
    private Label labWarFirstname, labWarPass, labWarLastname, labWarConfPass;

    private ObservableList<AdminUserTable> oblist = FXCollections.observableArrayList();
    private String userSelect;
    private Alert alert = new Alert(Alert.AlertType.INFORMATION);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        sql = new AdminDatabase();
        adCrMo = new AdminCreateModel();
        sql.connect();
        col_userAcc.setCellValueFactory(new PropertyValueFactory<>("users"));
        adminTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        adminTable.getSelectionModel().setCellSelectionEnabled(true);
        updateList();
        roleSelect();
    }

    @FXML
    private void createAccBtn() {
        if (accFirstName.getText().isEmpty() || accLastName.getText().isEmpty() || accPass.getText().isEmpty() || accConfPass.getText().isEmpty()) {
            if (accFirstName.getText().isEmpty()) {
                labWarFirstname.setText("You need to fill out this field!");
            }
            if (accLastName.getText().isEmpty()) {
                labWarLastname.setText("You need to fill out this field!");
            }
            if (accPass.getText().isEmpty()) {
                labWarPass.setText("Needs a password!");
            }
            if (accConfPass.getText().isEmpty()) {
                labWarConfPass.setText("Confirm your password!");
            }
        } else {
            if (accPass.getText().equals(accConfPass.getText())) {
                String randGenUserName = adCrMo.generateNewAccName(accFirstName.getText(), accLastName.getText());
                sql.createUsersSQL(randGenUserName, accPass.getText());
                sql.grantOptionsSQL(adCrMo.grant(select, insert, update, delete, grantOption), randGenUserName);
                accFirstName.clear();
                accLastName.clear();
                accPass.clear();
                accConfPass.clear();
                accPoliceRole.setSelected(true);
                roleSelect();
                updateList();
                alert.setTitle("Account Created");
                alert.setHeaderText("");
                alert.setContentText("New account name is: " + randGenUserName);
                alert.showAndWait();
            } else {
                labWarPass.setText("Password don´t match!");
                labWarConfPass.setText("Password don´t match!");
            }
        }
    }

    @FXML
    private void refreshUsers() {
        updateList();
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

        }
        if (accPoliceChiefRole.isSelected()) {
            delete.setDisable(false);
            delete.setSelected(true);
            grantOption.setDisable(true);
            grantOption.setSelected(false);
        }
        if (accITrole.isSelected()) {

            delete.setDisable(false);
            delete.setSelected(true);
            grantOption.setSelected(true);
            grantOption.setDisable(false);
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

    private void updateList() {
        adminTable.getItems().clear();
        ArrayList<String> adminUsers = sql.getUsersAdmin();
        for (String g : adminUsers) {
            oblist.add(new AdminUserTable(g));
        }
        adminTable.setItems(oblist);
        labWarFirstname.setText("");
        labWarLastname.setText("");
        labWarPass.setText("");
        labWarConfPass.setText("");
    }
    @FXML
    private void typingReset() {
        labWarFirstname.setText("");
        labWarLastname.setText("");
        labWarPass.setText("");
        labWarConfPass.setText("");
    }

}
