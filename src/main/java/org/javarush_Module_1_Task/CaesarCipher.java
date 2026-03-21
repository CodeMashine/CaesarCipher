package org.javarush_Module_1_Task;

import java.nio.channels.FileChannel;
import java.util.Scanner;

public class CaesarCipher {
	 static final char[] ALPHABET = {'А', 'Б', 'В', 'Г', 'Д', 'Е', 'Ж', 'З',
			 'И', 'К', 'Л', 'М', 'Н', 'О', 'П', 'Р', 'С', 'Т', 'У', 'Ф', 'Х', 'Ц', 'Ч', 'Ш', 'Щ',
			 'Ъ', 'Ы', 'Ь', 'Э', 'Я' ,'а', 'б', 'в', 'г', 'д', 'е', 'ж', 'з',
			'и', 'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ',
			'ъ', 'ы', 'ь', 'э', 'я', '.', ',', '«', '»', '"', '\'', ':', '!', '?', ' '};


	Cipher cipher = new Cipher();
	FileManager fileManager = new FileManager();
	Scanner scanner = new Scanner(System.in);

	public void uiGreeting() {


		System.out.println("Вас приветствует программа шифратор Цезаря");
		System.out.println("--------------------------------------------");
		System.out.println("1. Шифровка");
		System.out.println("2. Расшифровка с известным ключом");
		System.out.println("3. Взлом");

		String command = scanner.nextLine();

		if ( command.equals("1") ) {
			encrypt();
		}

	}

	private void encrypt() {
		System.out.println("Ведите адрес фаила источника");
		String sourseAddress = scanner.nextLine();
		System.out.println("Ведите ключ шифрования");
		int key = scanner.nextInt();
		System.out.println("Ведите адрес фаила назначения");
		String destAddress = scanner.nextLine();

		boolean resultCheck = fileManager.checkExisting(sourseAddress);
		if ( resultCheck ) {
			FileChannel fileSourseChannel = fileManager.readFileChannel(sourseAddress);
			FileChannel fileDestChannel = fileManager.writeFileChannel(destAddress);
			cipher.encrypt(fileSourseChannel , key , fileDestChannel);
		}
	}


	public void enterSourseFile() {
		System.out.println("");
	}


}
