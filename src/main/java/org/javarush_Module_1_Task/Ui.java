package org.javarush_Module_1_Task;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Ui extends Application {

	private Parent createContent() {
		Button buttonEncrypt = new Button("Encrypt");
		Button buttonDecrypt = new Button("Encrypt");
		Button button = new Button("Encrypt");

		Parent root = new StackPane(buttonEncrypt);

		return root;
	}

	@Override
	public void start(Stage stage) {
		Scene primaryStage = new Scene(createContent(), 800, 600);
		stage.setScene(primaryStage);
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
