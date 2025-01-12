package org.javafxports.hellofx;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import com.gluonhq.charm.down.Platform;
import com.gluonhq.charm.down.Services;
import com.gluonhq.charm.down.plugins.PicturesService;

public class MainController {

    @FXML
    private ImageView imageView;

    @FXML
    void btnLoadOnAction(ActionEvent event) {
        if (Platform.isDesktop()) {
            try {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Select an Image");

                fileChooser.getExtensionFilters().addAll(
                        new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif")
                );

                File selectedFile = fileChooser.showOpenDialog(null);

                if (selectedFile != null) {
                    Image image = new Image(selectedFile.toURI().toString());
                    imageView.setImage(image);
                }
            } catch (Exception ex) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
            }
        } else if (Platform.isAndroid()) {
            Services.get(PicturesService.class).ifPresent(service ->
                    service.loadImageFromGallery().ifPresent(image ->
                            imageView.setImage(image)));

//            Services.get(PicturesService.class).ifPresent(service ->
//                    service.takePhoto(true).ifPresent(image ->
//                            imageView.setImage(image)));
        }
    }

    @FXML
    void initialize() {
    }
}
