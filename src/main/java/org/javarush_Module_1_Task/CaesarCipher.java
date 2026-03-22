package org.javarush_Module_1_Task;

import java.nio.channels.FileChannel;
import java.util.Scanner;

public class CaesarCipher {
	char[] alphabet = {'А', 'Б', 'В', 'Г', 'Д', 'Е', 'Ж', 'З',
			'И', 'К', 'Л', 'М', 'Н', 'О', 'П', 'Р', 'С', 'Т', 'У', 'Ф', 'Х', 'Ц', 'Ч', 'Ш', 'Щ',
			'Ъ', 'Ы', 'Ь', 'Э', 'Я', 'а', 'б', 'в', 'г', 'д', 'е', 'ж', 'з',
			'и', 'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ',
			'ъ', 'ы', 'ь', 'э', 'я', '.', ',', '«', '»', '"', '\'', ':', '!', '?', ' ', '0', '1',
			'2', '3', '4', '5', '6', '7', '8', '9'};

	Cipher cipher = new Cipher(alphabet);
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

	public  void encrypt() {
//		System.out.println("Ведите адрес фаила источника");
//		String sourceAddress = scanner.nextLine();
//
//		System.out.println("Ведите адрес фаила назначения");
//		String destAddress = scanner.nextLine();
//
//		System.out.println("Ведите ключ шифрования");
//		int key = scanner.nextInt();

		String sourceEncryptAddress = "E:\\java\\CaesarCipher\\CaesarCipher\\src\\main\\java\\sourceText\\test.txt";
		String destEncryptAddress = "E:\\java\\CaesarCipher\\CaesarCipher\\src\\main\\java\\resultText\\testEncript.txt";
		int key = 8 ;



		boolean resultCheck = fileManager.checkExisting(sourceEncryptAddress);
		if ( resultCheck ) {
			FileChannel fileSourseChannel = fileManager.readFileChannel(sourceEncryptAddress);
			FileChannel fileDestChannel = fileManager.writeFileChannel(destEncryptAddress);

			cipher.encrypt(fileSourseChannel, key, fileDestChannel);
		}
	}


	public  void decrypt() {
		String sourceDecryptAddress = "E:\\java\\CaesarCipher\\CaesarCipher\\src\\main\\java\\sourceText\\testDecode.txt";
		String destDecryptAddress = "E:\\java\\CaesarCipher\\CaesarCipher\\src\\main\\java\\resultText\\testDecode.txt";
		int key = 8 ;

		boolean resultCheck = fileManager.checkExisting(sourceDecryptAddress);
		if ( resultCheck ) {
			FileChannel fileSourseChannel = fileManager.readFileChannel(sourceDecryptAddress);
			FileChannel fileDestChannel = fileManager.writeFileChannel(destDecryptAddress);

//			cipher.encrypt(fileSourseChannel, -key, fileDestChannel);
			cipher.decript (fileSourseChannel ,  key , fileDestChannel);
		}



	}


	public void enterSourseFile() {
		System.out.println("");
	}


}
