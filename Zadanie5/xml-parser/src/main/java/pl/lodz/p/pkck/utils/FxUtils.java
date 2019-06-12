package pl.lodz.p.pkck.utils;

import javafx.scene.image.Image;
import javafx.stage.Stage;

public class FxUtils {

    private FxUtils() {
    }

    public static void setStageIcon(Stage stage) {
        Image icon = new Image(FxUtils.class.getClassLoader().getResourceAsStream("icon.png"));
        stage.getIcons().add(icon);
    }
}
