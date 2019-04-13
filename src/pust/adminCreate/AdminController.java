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
public class AdminController implements Initializable {
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

    ObservableList<AdminUserTable> oblist = FXCollections.observableArrayList();
    ArrayList<String> adminUsers;
    private String userSelect;
    Alert alert = new Alert(Alert.AlertType.INFORMATION);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        sql = new AdminDatabase();
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
            alert.setTitle("Warning");
            alert.setHeaderText("");
            alert.setContentText("you forgot to chose a Password or Account name. Please retry.");
            alert.showAndWait();
        } else {
            if (accPass.getText().equals(accConfPass.getText())) {
                sql.createUsersSQL(accName.getText(), accPass.getText());
                sql.grantOptionsSQL(grant(select,insert,update,delete,grantOption),accName.getText());
                alert.setTitle("Success");
                alert.setHeaderText("");
                alert.setContentText("Account created successfully.");
                alert.showAndWait();
            } else {
                alert.setTitle("Warning");
                alert.setHeaderText("");
                alert.setContentText("Password don´t match.");
                alert.showAndWait();

            }
        }
        accName.clear();
        accPass.clear();
        accConfPass.clear();
        accPoliceRole.setSelected(true);
        roleSelect();
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
        }catch (IndexOutOfBoundsException e){
            e.printStackTrace();
        }
    }

    @FXML
    private void roleSelect(){
        if (accPoliceRole.isSelected()){
            select.setSelected(true);
            insert.setSelected(true);
            update.setSelected(true);
            delete.setSelected(false);
            delete.setDisable(true);
            grantOption.setSelected(false);
            grantOption.setDisable(true);
        }
        if (accPoliceChiefRole.isSelected()){
            select.setSelected(true);
            insert.setSelected(true);
            update.setSelected(true);
            delete.setDisable(false);
            delete.setSelected(true);
            grantOption.setDisable(true);
            grantOption.setSelected(false);
        }

        if (accITrole.isSelected()){
            select.setSelected(true);
            insert.setSelected(true);
            update.setSelected(true);
            delete.setDisable(false);
            delete.setSelected(true);
            grantOption.setSelected(true);
            grantOption.setDisable(false);
        }
    }

    private String grant(CheckBox select, CheckBox insert, CheckBox update, CheckBox delete, CheckBox grantOption){
        StringJoiner sj = new StringJoiner(",");

        if (select.isSelected()){
            sj.add("SELECT");
        }
        if (insert.isSelected()){
            sj.add("INSERT");
        }
        if (update.isSelected()){
            sj.add("UPDATE");
        }
        if (delete.isSelected()){
            sj.add("DELETE");
        }
        if (grantOption.isSelected()){
            sj.add("GRANT OPTION");
        }
        return sj.toString();
    }

    private void updateList(){
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
