package org.javarush_Module_1_Task;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	Gui gui = new Gui();


	@Override
	public void start(Stage stage) {
		Scene guiScene = gui.createScene();
		stage.setScene(guiScene);
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}


}


