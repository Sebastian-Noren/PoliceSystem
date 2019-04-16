package pust.adminCreate;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import pust.AppConstant;
import pust.DatabaseConnection;
import pust.SceneSwitch;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.StringJoiner;

/**
 * @author Sebastian Norén <s.norén@gmail.com>
 */
public class AdminScreenController implements Initializable {
    private AdminDatabase sql;
    @FXML
    private TextField accName, accPass, accConfPass;
    @FXML
    private TableView<AdminUserTable> adminTable;
    @FXML
    private TableColumn<AdminUserTable, String> col_userAcc;
    @FXML
    private CheckBox select, insert, update, delete, grantOption;
    @FXML
    private RadioButton accPoliceRole, accPoliceChiefRole, accITrole;

    private ObservableList<AdminUserTable> oblist = FXCollections.observableArrayList();
    private ArrayList<String> adminUsers;
    private String userSelect;
    private AdminCreateModel adminCreateModel;;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        sql = new AdminDatabase();
        adminCreateModel = new AdminCreateModel();
        sql.connect();
        col_userAcc.setCellValueFactory(new PropertyValueFactory<>("users"));
        adminTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        adminTable.getSelectionModel().setCellSelectionEnabled(true);
        updateList();
        roleSelect();
    }

    @FXML
    private void createAccBtn() {
        if (accName.getText().isEmpty() || accPass.getText().isEmpty() || accConfPass.getText().isEmpty()) {
            adminCreateModel.alert();
        } else {
            if (accPass.getText().equals(accConfPass.getText())) {
                sql.createUsersSQL(accName.getText(), accPass.getText());
                sql.grantOptionsSQL(adminCreateModel.grant(select, insert, update, delete, grantOption), accName.getText());
            }
        }
        accName.clear();
        accPass.clear();
        accConfPass.clear();
        accPoliceRole.setSelected(true);
        roleSelect();
        updateList();
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
            adminCreateModel.roleSelectPolice(select, insert, update, delete, grantOption);
        }
        if (accPoliceChiefRole.isSelected()) {
            adminCreateModel.roleSelectPoliceChief(select, insert, update, delete, grantOption);
        }

        if (accITrole.isSelected()) {
            adminCreateModel.roleSelectITadmin(select, insert, update, delete, grantOption);
        }
    }

    private void updateList() {
        adminTable.getItems().clear();
        adminUsers = sql.getUsersAdmin();
        for (String g : adminUsers) {
            oblist.add(new AdminUserTable(g));
        }
        adminTable.setItems(oblist);
    }

    public void returnLogin(ActionEvent actionEvent) {
        SceneSwitch sceneSwitch = new SceneSwitch();
        sceneSwitch.goToLogin(actionEvent);
    }
}
