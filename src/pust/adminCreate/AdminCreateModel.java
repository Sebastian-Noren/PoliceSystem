package pust.adminCreate;

import javafx.scene.control.*;

import java.util.ArrayList;
import java.util.StringJoiner;

public class AdminCreateModel {

    private Alert alert = new Alert(Alert.AlertType.INFORMATION);

    public String grant(CheckBox select, CheckBox insert, CheckBox update, CheckBox delete, CheckBox grantOption) {
        StringJoiner sj = new StringJoiner(",");
        if (select.isSelected()) {
            sj.add("SELECT");
        }
        if (insert.isSelected()) {
            sj.add("INSERT");
        }
        if (update.isSelected()) {
            sj.add("UPDATE");
        }
        if (delete.isSelected()) {
            sj.add("DELETE");
        }
        if (grantOption.isSelected()) {
            sj.add("GRANT OPTION");
        }
        return sj.toString();
    }

    public void roleSelectPolice(CheckBox select, CheckBox insert, CheckBox update, CheckBox delete, CheckBox grantOption) {
        select.setSelected(true);
        insert.setSelected(true);
        update.setSelected(true);
        delete.setSelected(false);
        delete.setDisable(true);
        grantOption.setSelected(false);
        grantOption.setDisable(true);
    }

    public void roleSelectPoliceChief(CheckBox select, CheckBox insert, CheckBox update, CheckBox delete, CheckBox grantOption) {
        select.setSelected(true);
        insert.setSelected(true);
        update.setSelected(true);
        delete.setDisable(false);
        delete.setSelected(true);
        grantOption.setDisable(true);
        grantOption.setSelected(false);
    }

    public void roleSelectITadmin(CheckBox select, CheckBox insert, CheckBox update, CheckBox delete, CheckBox grantOption) {
        select.setSelected(true);
        insert.setSelected(true);
        update.setSelected(true);
        delete.setDisable(false);
        delete.setSelected(true);
        grantOption.setSelected(true);
        grantOption.setDisable(false);
    }

    public void alert(){
        alert.setTitle("Warning");
        alert.setHeaderText("");
        alert.setContentText("you forgot to chose a Password or Account name. Please retry.");
        alert.showAndWait();
    }


}
