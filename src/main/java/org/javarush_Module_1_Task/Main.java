package org.javarush_Module_1_Task;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
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


