package pust.model.admin_create;

import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.security.SecureRandom;
import java.util.StringJoiner;


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

    public int generatePoliceId() {
        SecureRandom rand = new SecureRandom();
        int num = 10000 + rand.nextInt(90000);
        return num;
    }

    public BufferedImage resize(BufferedImage img, int height, int width) {
        Image tmp = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resized.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        return resized;
    }

}
