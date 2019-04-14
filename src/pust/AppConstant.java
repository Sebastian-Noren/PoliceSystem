package pust;

/*
This class can hold all variable or methods that can be used everywhere in the program.
 */

public class AppConstant {
    static final String SOFTWARE_NAME = "PUST GIS";
    public static final String DATABASE_NAME = "sys"; // TODO Change to new database name
    public static final String DATABASE_HOST = "localhost"; // TODO Change Server.
    private static String CURRENT_USER =""; //Save the current user in the program
    private static String CURRENT_USER_PASS = ""; //save the current password in the program


    public static String getCurrentUser() {
        return CURRENT_USER;
    }

    public static void setCurrentUser(String currentUser) {
        CURRENT_USER = currentUser;
    }

    public static String getCurrentUserPass() {
        return CURRENT_USER_PASS;
    }

    public static void setCurrentUserPass(String currentUserPass) {
        CURRENT_USER_PASS = currentUserPass;
    }
}

