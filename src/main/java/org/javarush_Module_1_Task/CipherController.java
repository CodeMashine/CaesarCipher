package org.javarush_Module_1_Task;

import org.javarush_Module_1_Task.workers.Cipher;
import org.javarush_Module_1_Task.workers.FileManager;
import org.javarush_Module_1_Task.workers.Validator;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

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
		BufferedReader bufferedFileReader = fileManager.createReader(sourceDecryptAddress);
		int key = cipher.findKeyToDecode(bufferedFileReader);

		if ( key != -1 ) {
			try {
				bufferedFileReader.close();
			}catch (IOException e){
				throw new RuntimeException(e);
			}
			bufferedFileReader = fileManager.createReader(sourceDecryptAddress);
			BufferedWriter bufferedFileWriter = fileManager.createWriter(destDecryptAddress);
			cipher.encrypt(bufferedFileReader, key, bufferedFileWriter);
		} else {
			System.out.println("Взлом не удался");
		}
	}

	public void encrypt(String sourceEncryptAddress, String destEncryptAddress, int key) {
		BufferedReader fileReader = fileManager.createReader(sourceEncryptAddress);
		BufferedWriter fileWriter = fileManager.createWriter(destEncryptAddress);
		cipher.encrypt(fileReader, key, fileWriter);
	}

	public void decrypt(String sourceDecryptAddress, String destDecryptAddress, int key) {
		BufferedReader fileReader = fileManager.createReader(sourceDecryptAddress);
		BufferedWriter fileWriter = fileManager.createWriter(destDecryptAddress);
		cipher.decrypt(fileReader, key, fileWriter);
	}

}
