package pust.controller.main_window;


import javafx.stage.FileChooser;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ApplyForPassportController {


    public void uploadImage() {
        FileChooser fileChooser = new FileChooser();
        File selectedImage = fileChooser.showOpenDialog(null);
        List<File> uploadedImage = new ArrayList<>();
        uploadedImage.add(selectedImage);

    }

}
