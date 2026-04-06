package org.javarush_Module_1_Task;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Gui {

	public Scene createScene() {
		BorderPane mainPane = createMainPane();
		VBox encryptPane = createEncryptPane();
		VBox decryptPane = createDecryptPane();
		VBox brutForcePane = createBrutForcePane();

		mainPane.setVisible(false);
		encryptPane.setVisible(false);
		decryptPane.setVisible(false);

		StackPane main = new StackPane();
		main.getChildren().addAll(mainPane, encryptPane, decryptPane, brutForcePane);

		Scene primaryScene = new Scene(main, 800, 600);
		return primaryScene;
	}

	public BorderPane createMainPane() {
//		StackPane pane = new StackPane();

		Label h1 = new Label("Шифр Цезаря");

		Button encryptButton = createNavigateToEncryptButton();

		Button decryptButton = createNavigateToDecryptButton();

		Button brutForceButton = createNavigateToBrutforceButton();

		HBox buttonBox = new HBox(20);
		buttonBox.setAlignment(Pos.CENTER);
		buttonBox.setStyle("-fx-padding: 20; -fx-background-color: #ecf0f1;");
		buttonBox.getChildren().addAll(encryptButton, decryptButton, brutForceButton);

		BorderPane borderPane = new BorderPane();
		borderPane.setCenter(h1);
		borderPane.setBottom(buttonBox);


//		pane.getChildren().addAll(borderPane);

		return borderPane;
	}

	private VBox createEncryptPane() {
		EventHandler<ActionEvent> handler = new EventHandler<>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				System.out.println("encrypt");
			}
		};

		VBox encryptPane = createEncryptDecryptPane("Encrypt", "Encrypt", handler, true);
		return encryptPane;
	}

	private VBox createDecryptPane() {
		EventHandler<ActionEvent> handler = new EventHandler<>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				System.out.println("decrypt");
			}
		};

		VBox encryptPane = createEncryptDecryptPane("Decrypt", "Decrypt", handler, true);
		return encryptPane;
	}

	private VBox createBrutForcePane() {
		EventHandler<ActionEvent> handler = new EventHandler<>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				System.out.println("brut force");
			}
		};

		VBox encryptPane = createEncryptDecryptPane("Brut Force", "Brut Force", handler, false);
		return encryptPane;
	}

	private VBox createEncryptDecryptPane(String title, String buttonName, EventHandler<ActionEvent> eventHandler, boolean isEncryptDecrypt) {
		VBox pane = new VBox(15);

		pane.setStyle("-fx-padding: 20; -fx-background-color: #f0f8ff;");

		Label titleScene = new Label(title);
		titleScene.setStyle("-fx-font-size: 18; -fx-font-weight: bold;");


		HBox fieldBox1 = new HBox(15);
		Label sourseLabel = new Label("Source");
		TextField sourceField = new TextField();
		fieldBox1.getChildren().addAll(sourseLabel, sourceField);


		HBox fieldBox2 = new HBox(15);
		Label targetLabel = new Label("Destination");
		TextField targetField = new TextField();
		fieldBox2.getChildren().addAll(targetLabel, targetField);

		HBox fieldBox3 = new HBox(15);
		if ( isEncryptDecrypt ) {
			Label keyLabel = new Label("Key");
			TextField keyField = new TextField();
			fieldBox3.getChildren().addAll(keyLabel, keyField);
		}

		HBox fieldBox4 = new HBox(15);
		Button workButton = new Button(buttonName);
		workButton.setOnAction(eventHandler);

		Button returnButton = new Button("Домой");
		returnButton.setOnAction(e -> {
			System.out.println("return");
		});
		fieldBox4.getChildren().addAll(workButton, returnButton);


//		pane.getChildren().addAll(titleScene, sourseLabel, sourceField, targetLabel, targetField, keyLabel, keyField, workButton, returnButton);
		pane.getChildren().addAll(fieldBox1, fieldBox2, fieldBox3, fieldBox4);

		return pane;
	}

//	public VBox createBrutForcePane() {
//		return null;
//	}

	;


	public Button createNavigateToEncryptButton() {
		String name = "Encrypt";

		EventHandler<ActionEvent> handler = new EventHandler<>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				System.out.println("navigate to encrypt");
			}
		};

		Button button = createButton(name, handler);

		return button;
	}

	public Button createNavigateToDecryptButton() {
		String name = "Decrypt";

		EventHandler<ActionEvent> handler = new EventHandler<>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				System.out.println("navigate to decrypt");
			}
		};

		Button button = createButton(name, handler);

		return button;
	}

	public Button createNavigateToBrutforceButton() {
		String name = "BrutForce";

		EventHandler<ActionEvent> handler = new EventHandler<>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				System.out.println("navigate to BrutForce");
			}
		};

		Button button = createButton(name, handler);

		return button;
	}


	public Button createButton(String name, EventHandler<ActionEvent> handler) {
		Button button = new Button(name);
		button.setOnAction(handler);
		return button;
	}
}
