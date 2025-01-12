package org.javafxports.hellofx;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

import com.gluonhq.charm.down.Platform;

import org.lsposed.hiddenapibypass.HiddenApiBypass;

public class App extends Application {

    static {
        if (Platform.isAndroid()) {
            HiddenApiBypass.addHiddenApiExemptions("L");
        }
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/javafxports/hellofx/main.fxml"));
        Parent root = loader.load();

        Scene scene;
        if (Platform.isDesktop()) {
            scene = new Scene(root);
        } else {
            Rectangle2D visualBounds = Screen.getPrimary().getVisualBounds();
            scene = new Scene(root, visualBounds.getWidth(), visualBounds.getHeight());
        }

        primaryStage.setTitle("JavaFX 8 with FXML");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}