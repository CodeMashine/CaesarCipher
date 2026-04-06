package org.javarush_Module_1_Task;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Gui {

	private BorderPane mainPane;
	private GridPane encryptPane;
	private GridPane decryptPane;
	private GridPane brutForcePane;
	private ArrayList<Pane> panes = new ArrayList<>();

	public Gui() {
		mainPane = createMainPane();
		encryptPane = createEncryptPane();
		decryptPane = createDecryptPane();
		brutForcePane = createBrutForcePane();
		panes.addAll(Arrays.asList(mainPane, encryptPane, decryptPane, brutForcePane));
	}


	public Scene createScene() {
		encryptPane.setVisible(false);
		decryptPane.setVisible(false);
		brutForcePane.setVisible(false);

		StackPane main = new StackPane();
		main.getChildren().addAll(mainPane, encryptPane, decryptPane, brutForcePane);

		Scene primaryScene = new Scene(main, 800, 600);
		return primaryScene;
	}

	public BorderPane createMainPane() {
		Label h1 = new Label("Шифр Цезаря");

		Button encryptButton = createNavigateButton("Encrypt", 1);

		Button decryptButton = createNavigateButton("Decrypt", 2);

		Button brutForceButton = createNavigateButton("BrutForce", 3);

		HBox buttonBox = new HBox(20);
		buttonBox.setAlignment(Pos.CENTER);
		buttonBox.setStyle("-fx-padding: 20; -fx-background-color: #ecf0f1;");
		buttonBox.getChildren().addAll(encryptButton, decryptButton, brutForceButton);

		BorderPane borderPane = new BorderPane();
		borderPane.setCenter(h1);
		borderPane.setBottom(buttonBox);
		return borderPane;
	}

	private GridPane createEncryptPane() {
		EventHandler<ActionEvent> handler = new EventHandler<>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				System.out.println("encrypt");
			}
		};

		GridPane encryptPane = createCustomPane("Encrypt", "Encrypt", handler, true);
		return encryptPane;
	}

	private GridPane createDecryptPane() {
		EventHandler<ActionEvent> handler = new EventHandler<>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				System.out.println("decrypt");
			}
		};

		GridPane encryptPane = createCustomPane("Decrypt", "Decrypt", handler, true);
		return encryptPane;
	}

	private GridPane createBrutForcePane() {
		EventHandler<ActionEvent> handler = new EventHandler<>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				System.out.println("brut force");
			}
		};

		GridPane encryptPane = createCustomPane("Brut Force", "Brut Force", handler, false);
		return encryptPane;
	}

	private GridPane createCustomPane(String title, String buttonName, EventHandler<ActionEvent> eventHandler, boolean isEncryptDecrypt) {
		GridPane pane = new GridPane();
		pane.setHgap(10);
		pane.setVgap(10);
		pane.setPadding(new Insets(25, 25, 25, 25));

		pane.setStyle("-fx-padding: 20; -fx-background-color: #f0f8ff;");

		for ( int i = 0; i < 5; i++ ) {
			ColumnConstraints col = new ColumnConstraints();
			RowConstraints row = new RowConstraints();
			col.setPercentWidth(20);
			row.setPercentHeight(20);
			pane.getColumnConstraints().add(col);
			pane.getRowConstraints().add(row);
		}

		Label titleScene = new Label(title);
		titleScene.setStyle("-fx-font-size: 18; -fx-font-weight: bold;");
		titleScene.setAlignment(Pos.CENTER);
		pane.add(titleScene, 2, 0);

		Label sourseLabel = new Label("Source");
		pane.add(sourseLabel, 1, 1);
		TextField sourceField = new TextField();
		pane.add(sourceField, 2, 1);

		Label targetLabel = new Label("Destination");
		pane.add(targetLabel, 1, 2);
		TextField targetField = new TextField();
		pane.add(targetField, 2, 2);

		if ( isEncryptDecrypt ) {
			Label keyLabel = new Label("Key");
			pane.add(keyLabel, 1, 3);
			TextField keyField = new TextField();
			pane.add(keyField, 2, 3);
		}

		Button workButton = new Button(buttonName);
		workButton.setOnAction(eventHandler);
		pane.add(workButton, 1, 4);


		Button returnButton = createNavigateButton("Домой", 0);
		pane.add(returnButton, 3, 4);

		return pane;
	}

	private Button createNavigateButton(String name, int paneIndex) {
		EventHandler<ActionEvent> handler = new EventHandler<>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				showPane(paneIndex);
			}
		};

		return createButton(name, handler);
	}


	private Button createButton(String name, EventHandler<ActionEvent> handler) {
		Button button = new Button(name);
		button.setOnAction(handler);
		return button;
	}

	private void showPane(int paneIndex) {
		for ( int i = 0; i < panes.size(); i++ ) {
			Pane pane = panes.get(i);
			if ( i == paneIndex ) {
				pane.setVisible(true);
			} else {
				pane.setVisible(false);
			}
		}
	}


}
