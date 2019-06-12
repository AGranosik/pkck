package pl.lodz.p.pkck.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

public class MainView extends View {

    public MainView(final String loaderSourcePath) throws IOException {
        FXMLLoader loader = new FXMLLoader(MainView.class.getResource(loaderSourcePath));
        Parent graphRoot = loader.load();
        scene = new Scene(graphRoot);
        controller = loader.getController();
    }

}
