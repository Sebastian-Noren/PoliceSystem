package pust.adminCreate;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

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
    ObservableList<AdminUserTable> oblist = FXCollections.observableArrayList();
    ArrayList<String> adminUsers;
    private String userSelect;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        sql = new AdminDatabase();
        sql.connect();
        col_userAcc.setCellValueFactory(new PropertyValueFactory<>("users"));
        adminTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        adminTable.getSelectionModel().setCellSelectionEnabled(true);
        updateList();
    }

    @FXML
    private void createAccBtn() {
        if (accName.getText().isEmpty() || accPass.getText().isEmpty() || accConfPass.getText().isEmpty()) {
            System.err.println("Textfield empty");
        } else {
            if (accPass.getText().equals(accConfPass.getText())) {
                sql.createUsersSQL(accName.getText(), accPass.getText());
            } else {
                System.err.println("Password not equal");
            }
        }
        accName.clear();
        accPass.clear();
        accConfPass.clear();
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


    private void updateList(){
        adminTable.getItems().clear();
        adminUsers = sql.getUsersAdmin();
        for (String g : adminUsers) {
            oblist.add(new AdminUserTable(g));
        }
        adminTable.setItems(oblist);
    }
}
