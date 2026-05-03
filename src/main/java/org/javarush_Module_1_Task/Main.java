package org.javarush_Module_1_Task;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.javarush_Module_1_Task.worker.Alphabet;
import org.javarush_Module_1_Task.worker.Cipher;
import org.javarush_Module_1_Task.worker.FileManager;
import org.javarush_Module_1_Task.worker.Validator;
import org.w3c.dom.ls.LSOutput;

public class Main extends Application {
	static char[] alphabet = {'А', 'Б', 'В', 'Г', 'Д', 'Е', 'Ё', 'Ж', 'З', 'И', 'Й', 'К', 'Л', 'М', 'Н', 'О', 'П', 'Р', 'С', 'Т', 'У', 'Ф', 'Х', 'Ц', 'Ч', 'Ш', 'Щ', 'Ъ', 'Ы', 'Ь', 'Э', 'Я', 'а', 'б', 'в', 'г', 'д', 'е', 'ё', 'ж', 'з', 'и', 'й', 'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ', 'ъ', 'ы', 'ь', 'э', 'я', '.', ',', '«', '»', '"', '\'', ':', '!', '?', ' ', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

	static String[] templatePhrases = {" и ", " в ", " к ", ",а", ", а", " или ", " это " , " не " , " у "};


	@Override
	public void start(Stage stage) {
		Alphabet alphabetInstance = new Alphabet(alphabet);
		Cipher cipher = new Cipher(templatePhrases,alphabetInstance);
		FileManager fileManager = new FileManager();
		Validator validator = new Validator(alphabet.length);
		CipherController caesarController = new CipherController(cipher, fileManager, validator);
		Gui gui = new Gui(caesarController);

		Scene guiScene = gui.createScene();
		stage.setScene(guiScene);
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}


}


