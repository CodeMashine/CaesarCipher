package org.javarush_Module_1_Task;

import java.nio.channels.FileChannel;
import java.util.Scanner;

public class CaesarCipher {
	char[] alphabet = {'А', 'Б', 'В', 'Г', 'Д', 'Е', 'Ё', 'Ж', 'З',
			'И', 'Й', 'К', 'Л', 'М', 'Н', 'О', 'П', 'Р', 'С', 'Т', 'У', 'Ф', 'Х', 'Ц', 'Ч', 'Ш', 'Щ',
			'Ъ', 'Ы', 'Ь', 'Э', 'Я', 'а', 'б', 'в', 'г', 'д', 'е', 'ё', 'ж', 'з',
			'и', 'й', 'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ',
			'ъ', 'ы', 'ь', 'э', 'я', '.', ',', '«', '»', '"', '\'', ':', '!', '?', ' ', '0', '1',
			'2', '3', '4', '5', '6', '7', '8', '9'};

	String[] templatePhrases = {" и " , " в " , " к " , ",а" , ", а" , " или " , " это "};

	Cipher cipher = new Cipher(alphabet , templatePhrases);
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

	public void brutForce(){
		String sourceDecryptAddress = "E:\\java\\CaesarCipher\\CaesarCipher\\src\\main\\java\\sourceText\\тест кодированный.txt";
		String destDecryptAddress = "E:\\java\\CaesarCipher\\CaesarCipher\\src\\main\\java\\resultText\\тест декодированный тест взлома.txt";

		boolean resultCheck = fileManager.checkExisting(sourceDecryptAddress);

		if (resultCheck) {
			FileChannel fileSourceChannel = fileManager.readFileChannel(sourceDecryptAddress);
			FileChannel fileDestChannel = fileManager.writeFileChannel(destDecryptAddress);
			int key = cipher.findKeyToDecode(fileSourceChannel);

			try{
				fileSourceChannel.position(0);
			}catch (Exception e){
				throw new RuntimeException(e);
			}
			if (key != -1) {
				cipher.decrypt(fileSourceChannel, key, fileDestChannel);
			}else{
				System.out.println("Взлом не удался");
			}
		}
	}

	public void encrypt() {
		String sourceEncryptAddress = "E:\\java\\CaesarCipher\\CaesarCipher\\src\\main\\java\\sourceText\\тест исходный.txt";
		String destEncryptAddress = "E:\\java\\CaesarCipher\\CaesarCipher\\src\\main\\java\\resultText\\тест кодированный.txt";
		int key = 68;


		boolean resultCheck = fileManager.checkExisting(sourceEncryptAddress);
		if ( resultCheck ) {
			FileChannel fileSourceChannel = fileManager.readFileChannel(sourceEncryptAddress);
			FileChannel fileDestChannel = fileManager.writeFileChannel(destEncryptAddress);

			cipher.encrypt(fileSourceChannel, key, fileDestChannel);
		}
	}


	public void decrypt() {
		String sourceDecryptAddress = "E:\\java\\CaesarCipher\\CaesarCipher\\src\\main\\java\\sourceText\\тест кодированный.txt";
		String destDecryptAddress = "E:\\java\\CaesarCipher\\CaesarCipher\\src\\main\\java\\resultText\\тест декодированный.txt";
		int key = 68;

		boolean resultCheck = fileManager.checkExisting(sourceDecryptAddress);
		if ( resultCheck ) {
			FileChannel fileSourseChannel = fileManager.readFileChannel(sourceDecryptAddress);
			FileChannel fileDestChannel = fileManager.writeFileChannel(destDecryptAddress);
			cipher.decrypt(fileSourseChannel, key, fileDestChannel);
		}


	}


	public void enterSourseFile() {
		System.out.println("");
	}


}
