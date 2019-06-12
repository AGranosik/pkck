package pl.lodz.p.pkck;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.lodz.p.pkck.utils.FxUtils;
import pl.lodz.p.pkck.view.MainView;
import pl.lodz.p.pkck.view.View;

public class App extends Application {

    private static final Logger logger = LoggerFactory.getLogger(App.class);

    private static View mainView;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            mainView = new MainView("/views/MainScene.fxml");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage(), e);
        }

        mainView.getController().setStage(primaryStage);

        primaryStage.setTitle("XML Application");
        primaryStage.setScene(mainView.getScene());
        primaryStage.setOnCloseRequest(e -> Platform.exit());
        FxUtils.setStageIcon(primaryStage);
        primaryStage.show();
    }
}
