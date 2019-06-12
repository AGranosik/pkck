package pl.lodz.p.pkck.view;

import javafx.scene.Scene;
import pl.lodz.p.pkck.controller.Controller;

public abstract class View {

    protected Scene scene;
    protected Controller controller;

    public Scene getScene() {
        return scene;
    }

    public Controller getController() {
        return controller;
    }

}
