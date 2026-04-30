package org.javarush_Module_1_Task;

import org.javarush_Module_1_Task.worker.Cipher;
import org.javarush_Module_1_Task.worker.FileManager;
import org.javarush_Module_1_Task.worker.Validator;

import java.io.BufferedReader;
import java.io.BufferedWriter;
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
//		E:\java\CaesarCipher\CaesarCipher\src\main\java\resultText\тест кодированный.txt
//		E:\java\CaesarCipher\CaesarCipher\src\main\java\resultText\тест взлом.txt
		BufferedReader bufferedFileReader = fileManager.createReader(sourceDecryptAddress);
		BufferedWriter bufferedFileWriter = fileManager.createWriter(destDecryptAddress);
		int key = cipher.findKeyToDecode(bufferedFileReader);

		if ( key != -1 ) {
			cipher.decrypt(bufferedFileReader, key, bufferedFileWriter);
		} else {
			System.out.println("Взлом не удался");
		}
	}

	public void encrypt(String sourceEncryptAddress, String destEncryptAddress, int key) {
//		E:\java\CaesarCipher\CaesarCipher\src\main\java\sourceText\тест исходный.txt
//		E:\java\CaesarCipher\CaesarCipher\src\main\java\resultText\тест кодированный.txt
		BufferedReader fileReader = fileManager.createReader(sourceEncryptAddress);
		BufferedWriter fileWriter = fileManager.createWriter(destEncryptAddress);
		cipher.encrypt(fileReader, key, fileWriter);
	}

	public void decrypt(String sourceDecryptAddress, String destDecryptAddress, int key) {
//		E:\java\CaesarCipher\CaesarCipher\src\main\java\sourceText\тест кодированный.txt
//		E:\java\CaesarCipher\CaesarCipher\src\main\java\resultText\тест декодированный тест взлома.txt
		BufferedReader fileReader = fileManager.createReader(sourceDecryptAddress);
		BufferedWriter fileWriter = fileManager.createWriter(destDecryptAddress);
		cipher.decrypt(fileReader, key, fileWriter);
	}

}
