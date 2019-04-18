package pust.model.adminCreate;

import javafx.scene.control.CheckBox;

import java.security.SecureRandom;
import java.util.StringJoiner;

/**
 * @author Sebastian Norén <s.norén@gmail.com>
 */
public class AdminCreateModel {

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

    public String generateNewAccName(String firstname, String lastname) {
        StringJoiner sj = new StringJoiner("");
        SecureRandom rand = new SecureRandom();
        int num = rand.nextInt(1000);
        sj.add(firstname.substring(0, 3).toLowerCase());
        sj.add(lastname.substring(0, 3).toLowerCase());
        sj.add(String.valueOf(num));
        return sj.toString();
    }
}
