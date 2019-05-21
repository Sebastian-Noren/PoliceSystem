package pust.model.utility;

/*
This class can hold all variable or methods that can be used everywhere in the program.
 */

import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import pust.model.entity.Employee;
import pust.model.entity.Person;
import pust.model.entity.PersonalNumber;
import pust.model.entity.Suspect;
import pust.model.enumerations.*;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import static pust.model.enumerations.Build.*;
import static pust.model.enumerations.Color.eyeColor.*;
import static pust.model.enumerations.Ethnicity.*;
import static pust.model.enumerations.Gender.FEMALE;
import static pust.model.enumerations.Gender.MALE;
import static pust.model.enumerations.Title.*;

public class AppConstant {
    private static final Logger LOGGER = Logger.getLogger(AppConstant.class.getName());
    private static final String SOFTWARE_NAME = "PUST GIS";
    private static final String DATABASE_NAME = "pustgis";
    private static final String DATABASE_HOST = "localhost";
    private static String CURRENT_USER = ""; //Save the current user in the program
    public static String SAVE_FOLDER_PATH = "src/pust/images/";
    private static boolean SSN_CHECK = false;
    public static Person person;
    public static Suspect suspect;
    public static Employee employee;

    public static boolean isSsnCheck() {
        return SSN_CHECK;
    }

    public static void setSsnCheck(boolean ssnCheck) {
        SSN_CHECK = ssnCheck;
    }

    public static String getSOFTWARE_NAME() {
        return SOFTWARE_NAME;
    }

    public static String getDatabaseName() {
        return DATABASE_NAME;
    }

    public static String getDatabaseHost() {
        return DATABASE_HOST;
    }

    public static String getCurrentUser() {
        return CURRENT_USER;
    }

    public static void setCurrentUser(String currentUser) {
        CURRENT_USER = currentUser;
    }

    public static boolean isFemale(int serialNumber) {
        return serialNumber % 2 == 0;
    }

    public static boolean parseToBoolean(int value) {
        return value == 1;
    }

    public static PersonalNumber parsePersonalNumber(String ssn) {
        int year;
        int month;
        int day;
        int serialNumber;
        int controlNumber;
        StringBuilder sb = new StringBuilder();
        char[] ssnArray = ssn.toCharArray();
        for (int i = 0; i < 4; i++) {
            sb.append(ssnArray[i]);
        }
        year = Integer.valueOf(sb.toString());
        sb = new StringBuilder();
        for (int i = 4; i < 6; i++) {
            sb.append(ssnArray[i]);
        }
        month = Integer.valueOf(sb.toString());
        sb = new StringBuilder();
        for (int i = 6; i < 8; i++) {
            sb.append(ssnArray[i]);
        }
        day = Integer.valueOf(sb.toString());
        sb = new StringBuilder();
        for (int i = 8; i < 11; i++) {
            sb.append(ssnArray[i]);
        }
        serialNumber = Integer.valueOf(sb.toString());
        sb = new StringBuilder();
        sb.append(ssnArray[ssnArray.length - 1]);
        controlNumber = Integer.valueOf(sb.toString());

        return new PersonalNumber(year, month, day, serialNumber, controlNumber);
    }

    public static String parseGenderToString(Enum gender) {
        if (gender.equals(FEMALE)) {
            return "F";
        } else {
            return "M";
        }
    }

    public static String parseBuildToString(Build build) {
        switch (build) {
            case THIN:
                return "T";
            case NORMAL:
                return "N";
            case MUSCULAR:
                return "M";
            case EXTENSIVE:
                return "F";
            default:
                LOGGER.log(Level.FINER, "No build type found matching the cases");
                return null;

        }
    }

    public static Build parseBuildToEnum(String build) {
        switch (build) {
            case "T":
                return THIN;
            case "N":
                return NORMAL;
            case "M":
                return MUSCULAR;
            case "F":
                return EXTENSIVE;
            default:
                LOGGER.log(Level.FINER, "No build type found matching the cases");
                return null;
        }
    }

    public static Title parseJobTitleToEnum(String jobtitle) {
        switch (jobtitle) {
            case "ASSISTANT":
                return ASSISTANT;
            case "INSPECTOR":
                return INSPECTOR;
            case "SUPERINTENDENT":
                return SUPERINTENDENT;
            case "CHIEFSUPERINTENDENT":
                return CHIEFSUPERINTENDENT;
            case "DISTRICTPOLICECOMMISSIONER":
                return DISTRICTPOLICECOMMISSIONER;
            case "ITADMINISTRATOR":
                return ITADMINISTRATOR;
            case "ITCOORDINATOR":
                return ITCOORDINATOR;
            default:
                LOGGER.log(Level.FINER, "No job title found matching the cases");
                return null;
        }
    }

    public static Gender parseGenderToEnum(String gender) {
        if (gender.equals("F")) {
            return FEMALE;
        } else {
            return MALE;
        }
    }

    public static Ethnicity parseEthnicityToEnum(String ethnicity) {
        switch (ethnicity) {
            case "European":
                return EUROPEAN;
            case "African":
                return AFRICAN;
            case "Asian":
                return ASIAN;
            case "Hispanic":
                return HISPANIC;
            case "Arab":
                return ARAB;
            case "White and Black":
                return WHITEANDBLACK;
            case "White and Black Caribbean":
                return WHITEANDBLACKCARIBBEAN;
            case "White and Asian":
                return WHITEANDASIAN;
            case "Other Mixed":
                return OTHERMIXED;
            case "Indian":
                return INDIAN;
            case "Pakistani":
                return PAKISTANI;
            case "Bangladeshi":
                return BANGLADESHI;
            case "Carribean":
                return CARRIBEAN;
            case "Any other group":
                return ANYOTHERGROUP;
            default:
                LOGGER.log(Level.FINER, "No ethnicity found matching the cases");
                return null;
        }
    }

    public static Color.eyeColor parseEyeColour(String eyeCol) {
        switch (eyeCol) {
            case "Green":
                return GREEN;
            case "Blue":
                return BLUE;
            case "Brown":
                return BROWN;
            case "Black":
                return BLACK;
            case "Grey":
                return GREY;
            case "Red":
                return RED;
            default:
                LOGGER.log(Level.FINER, "No eye colour found matching the cases");
                return null;
        }
    }

    public static Color.hairColor parseHairColour(String hairCol) {
        switch (hairCol) {
            case "Black":
                return Color.hairColor.BLACK;
            case "Brown":
                return Color.hairColor.BROWN;
            case "Blonde":
                return Color.hairColor.BLONDE;
            case "White":
                return Color.hairColor.WHITE;
            case "DeepBrown":
                return Color.hairColor.DEEPBROWN;
            case "Grey":
                return Color.hairColor.GREY;
            case "LightGrey":
                return Color.hairColor.LIGHTGREY;
            case "DarkGrey":
                return Color.hairColor.DARKGREY;
            case "Biscuit":
                return Color.hairColor.BISCUIT;
            case "Pink":
                return Color.hairColor.PINK;
            case "Green":
                return Color.hairColor.GREEN;
            case "Blue":
                return Color.hairColor.BLUE;
            case "Red":
                return Color.hairColor.RED;
            default:
                LOGGER.log(Level.FINER, "No hair colour found matching the cases");
                return null;
        }
    }

    public static void alertBoxInformation(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image(AppConstant.class.getResource("/image/smallSwepustlogg.png").toString()));
        alert.initStyle(StageStyle.DECORATED);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.getDialogPane().getStylesheets().add(AppConstant.class.getResource("/view/basicStyleSheet.css").toExternalForm());
        alert.showAndWait();
    }

    public static void alertBoxWarning(String titel, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image(AppConstant.class.getResource("/image/smallSwepustlogg.png").toString()));
        alert.initStyle(StageStyle.DECORATED);
        alert.setTitle(titel);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.getDialogPane().getStylesheets().add(AppConstant.class.getResource("/view/basicStyleSheet.css").toExternalForm());
        alert.show();
    }

    public static void switchScene(Event event, String changeScene) {
        try {
            Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Parent root = FXMLLoader.load(AppConstant.class.getResource(changeScene));
            Image image = new Image(AppConstant.class.getResourceAsStream("/image/swepustlogg.png"));
            primaryStage.getIcons().add(image);
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle(AppConstant.getSOFTWARE_NAME());
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String monthToString(int month) {
        switch (month) {
            case 1:
                return "January";
            case 2:
                return "February";
            case 3:
                return "Mars";
            case 4:
                return "April";
            case 5:
                return "May";
            case 6:
                return "June";
            case 7:
                return "July";
            case 8:
                return "August";
            case 9:
                return "September";
            case 10:
                return "October";
            case 11:
                return "November";
            case 12:
                return "December";
            default:
                return "ERROR";
        }
    }

    public static String dateOfBirth(String ssn) {
        int year;
        int month;
        int day;

        StringBuilder sb = new StringBuilder();
        char[] ssnArray = ssn.toCharArray();
        for (int i = 0; i < 4; i++) {
            sb.append(ssnArray[i]);
        }
        year = Integer.valueOf(sb.toString());
        sb = new StringBuilder();
        for (int i = 4; i < 6; i++) {
            sb.append(ssnArray[i]);
        }
        month = Integer.valueOf(sb.toString());
        sb = new StringBuilder();
        for (int i = 6; i < 8; i++) {
            sb.append(ssnArray[i]);
        }
        day = Integer.valueOf(sb.toString());

        return String.format("%d %s, %d", day, AppConstant.monthToString(month), year);
    }
}

