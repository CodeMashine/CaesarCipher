package org.javarush_Module_1_Task;

import java.nio.channels.FileChannel;

public class CipherController {

	Cipher cipher;
	FileManager fileManager;
	Validator validator;

	public CipherController(Cipher cipher, FileManager fileManager, Validator validator) {
		this.cipher = cipher;
		this.fileManager = fileManager;
		this.validator = validator;
	}


	public void brutForce(String sourceDecryptAddress, String destDecryptAddress) {
//		E:\java\CaesarCipher\CaesarCipher\src\main\java\sourceText\тест кодированный.txt
//		E:\java\CaesarCipher\CaesarCipher\src\main\java\resultText\тест взлом.txt
		FileChannel fileSourceChannel = fileManager.createReadFileChannel(sourceDecryptAddress);
		FileChannel fileDestChannel = fileManager.createWriteFileChannel(destDecryptAddress);
		int key = cipher.findKeyToDecode(fileSourceChannel);

		try {
			fileSourceChannel.position(0);
		} catch ( Exception e ) {
			throw new RuntimeException(e);
		}

		if ( key != -1 ) {
			cipher.decrypt(fileSourceChannel, key, fileDestChannel);
		} else {
			System.out.println("Взлом не удался");
		}
	}

	public void encrypt(String sourceEncryptAddress, String destEncryptAddress, int key) {
//		E:\java\CaesarCipher\CaesarCipher\src\main\java\sourceText\тест исходный.txt
//		E:\java\CaesarCipher\CaesarCipher\src\main\java\resultText\тест декодированный.txt
		FileChannel fileSourceChannel = fileManager.createReadFileChannel(sourceEncryptAddress);
		FileChannel fileDestChannel = fileManager.createWriteFileChannel(destEncryptAddress);
		cipher.encrypt(fileSourceChannel, key, fileDestChannel);
	}


	public void decrypt(String sourceDecryptAddress, String destDecryptAddress, int key) {
//		E:\java\CaesarCipher\CaesarCipher\src\main\java\sourceText\тест кодированный.txt
//		E:\java\CaesarCipher\CaesarCipher\src\main\java\resultText\тест декодированный тест взлома.txt
		FileChannel fileSourceChannel = fileManager.createReadFileChannel(sourceDecryptAddress);
		FileChannel fileDestChannel = fileManager.createWriteFileChannel(destDecryptAddress);
		cipher.decrypt(fileSourceChannel, key, fileDestChannel);
	}

}
