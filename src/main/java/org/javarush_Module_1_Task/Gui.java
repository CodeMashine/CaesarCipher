package org.javarush_Module_1_Task;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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

import java.util.HashMap;

public class Gui {
	private CipherController cipherController;
	private BorderPane mainPane;
	private GridPane encryptPane;
	private GridPane decryptPane;
	private GridPane brutForcePane;

	private HashMap<PANES, Pane> panes = new HashMap<>();

	public Gui(CipherController cipherController) {
		this.cipherController = cipherController;
		mainPane = createMainPane();
		encryptPane = createEncryptPane();
		decryptPane = createDecryptPane();
		brutForcePane = createBrutForcePane();

		panes.put(PANES.ENCRYPT, encryptPane);
		panes.put(PANES.DECRYPT, decryptPane);
		panes.put(PANES.BRUT_FORCE, brutForcePane);
		panes.put(PANES.MAIN, mainPane);
	}


	public Scene createScene() {
		mainPane.setVisible(true);
		encryptPane.setVisible(false);
		decryptPane.setVisible(false);
		brutForcePane.setVisible(false);

		StackPane main = new StackPane();
		main.getChildren().addAll(mainPane, encryptPane, decryptPane, brutForcePane);

		Scene primaryScene = new Scene(main, 800, 600);
		return primaryScene;
	}

	public BorderPane createMainPane() {
		Label h1 = new Label("Caesar Cipher");

		Button encryptButton = createNavigateButton("Encrypt", PANES.ENCRYPT);

		Button decryptButton = createNavigateButton("Decrypt", PANES.DECRYPT);

		Button brutForceButton = createNavigateButton("BrutForce", PANES.BRUT_FORCE);

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
		GridPane encryptPane = createCustomPane("Encrypt", "Encrypt", PANES.ENCRYPT);
		return encryptPane;
	}

	private GridPane createDecryptPane() {
		GridPane decryptPane = createCustomPane("Decrypt", "Decrypt", PANES.DECRYPT);
		return decryptPane;
	}

	private GridPane createBrutForcePane() {
		GridPane brutForcePane = createCustomPane("Brut Force", "Brut Force", PANES.BRUT_FORCE);
		return brutForcePane;
	}

	private GridPane createCustomPane(String title, String buttonName, PANES paneType) {
		GridPane pane = new GridPane();
		pane.setHgap(10);
		pane.setVgap(10);
		pane.setPadding(new Insets(25, 25, 25, 25));

		pane.setStyle("-fx-padding: 20; -fx-background-color: #f0f8ff;");

		for ( int i = 0; i < 10; i++ ) {
			ColumnConstraints col = new ColumnConstraints();
			col.setPercentWidth(10);
			pane.getColumnConstraints().add(col);
		}

		for ( int i = 0; i < 6; i++ ) {
			RowConstraints row = new RowConstraints();
			row.setPercentHeight(20);
			pane.getRowConstraints().add(row);
		}

		Label titleScene = new Label(title);
		titleScene.setStyle("-fx-font-size: 18; -fx-font-weight: bold;");
//		titleScene.setAlignment(Pos.CENTER);
		pane.add(titleScene, 4, 0, 3, 1);

		FieldBlock sourceBlock = new FieldBlock("Source", 1, 1, 6, 2);

		sourceBlock.insert(pane);

		FieldBlock destinationBlock = new FieldBlock("Destination", 1, 2, 6, 2);

		destinationBlock.insert(pane);

		FieldBlock keyBlock = new FieldBlock("Key", 1, 3, 1, 2);

		keyBlock.insert(pane);

		if ( paneType == PANES.BRUT_FORCE ) {
			keyBlock.setVisible(false);
		}

		Button workButton = new Button(buttonName);

		workButton.setOnAction(e -> {
			String source = sourceBlock.getText();
			String destination = destinationBlock.getText();
			String key = keyBlock.getText();

			boolean isSourceExist = cipherController.validator.checkExistingFile(source);
			boolean isValidDestPath = cipherController.validator.validateDestinationPath(destination);

			if ( !isSourceExist ) {
				sourceBlock.setErrorFieldMessage("Source is not exist");
				return;
			}

			if ( !isValidDestPath ) {
				destinationBlock.setErrorFieldMessage("Invalid destination path");
				return;
			}

			if ( paneType != PANES.BRUT_FORCE ) {
				key = keyBlock.getText();
				boolean isValidKey = cipherController.validator.checkKey(key);
				if ( !isValidKey ) {
					keyBlock.setErrorFieldMessage("Invalid key");
					return;
				}
			}

			if ( paneType == PANES.ENCRYPT ) {
				int keyValue = Integer.parseInt(key);
				cipherController.encrypt(source, destination, keyValue);
			} else if ( paneType == PANES.DECRYPT ) {
				int keyValue = Integer.parseInt(key);
				cipherController.decrypt(source, destination, keyValue);
			} else {
				cipherController.brutForce(source, destination);
			}
		});

		pane.add(workButton, 3, 4, 2, 1);

		Button returnButton = createNavigateButton("Home", PANES.MAIN);
		pane.add(returnButton, 6, 4, 2, 1);

		return pane;
	}

	private Button createNavigateButton(String name, PANES paneName) {
		EventHandler<ActionEvent> handler = new EventHandler<>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				showPane(paneName);
			}
		};

		return createButton(name, handler);
	}


	private Button createButton(String name, EventHandler<ActionEvent> handler) {
		Button button = new Button(name);
		button.setOnAction(handler);
		return button;
	}

	private void showPane(PANES paneName) {
		for ( PANES pane : panes.keySet() ) {
			if ( pane == paneName ) {
				panes.get(pane).setVisible(true);
			} else {
				panes.get(pane).setVisible(false);
			}
		}
	}

	private class FieldBlock {
		private int row;
		private int startCol;
		private int errorFieldLength;
		private int addressFieldLength;
		private Label nameLabel;
		private TextField addressField;
		private Label errorLabel;

		public FieldBlock(String name, int col, int row, int addressFieldLength, int errorFieldLength) {
			this.nameLabel = new Label(name);
			this.addressField = new TextField();
			this.errorLabel = new Label("");
			this.addressFieldLength = addressFieldLength;
			this.errorFieldLength = errorFieldLength;
			this.row = row;
			this.startCol = col;
		}

		public void insert(GridPane pane) {
			resetErrorLabelOnMouseClicked();
			pane.add(nameLabel, startCol, row);
			pane.add(addressField, ++startCol, row, addressFieldLength, 1);
			pane.add(errorLabel, startCol + addressFieldLength, row, errorFieldLength, 1);
		}

		private void resetErrorLabelOnMouseClicked() {
			addressField.setOnMouseClicked(event -> {
				errorLabel.setText("");
			});
		}

		public void setErrorFieldMessage(String message) {
			errorLabel.setText(message);
		}

		public String getText() {
			return addressField.getText();
		}

		public void setVisible(boolean visible) {
			nameLabel.setVisible(visible);
			addressField.setVisible(visible);
			errorLabel.setVisible(visible);
		}
	}
}


enum PANES {
	MAIN,
	ENCRYPT,
	DECRYPT,
	BRUT_FORCE,
}
