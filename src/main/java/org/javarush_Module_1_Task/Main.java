package org.javarush_Module_1_Task;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {
//
//	public static void main(String[] args) {
//		CaesarCipher caesarCipher = new CaesarCipher();
//		caesarCipher.uiGreeting();/
//		caesarCipher.encrypt();
//		caesarCipher.decrypt();
//		caesarCipher.brutForce();
//	}

	private Parent createContent() {
		Button buttonEncrypt = new Button("Encrypt");
		buttonEncrypt.setLayoutX(50);
		buttonEncrypt.setLayoutY(50);

		Button buttonDecrypt = new Button("Decrypt");
		Button buttonBrutForce = new Button("Brut Force");

		Parent root = new StackPane(buttonEncrypt , buttonDecrypt , buttonBrutForce);

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


