package org.javarush_Module_1_Task.worker;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;


public class Cipher {
	private final Alphabet alphabet;

	private final String[] templatePhrases;

	public Cipher(String[] templatePhrases, Alphabet alphabetInstance) {
		this.templatePhrases = templatePhrases;
		this.alphabet = alphabetInstance;
	}


	public int findKeyToDecode(BufferedReader bufferedFileReader) {
		StringBuilder stringToBrut = new StringBuilder();
		StringBuilder stringPretender = new StringBuilder();

		try {
			for ( int i = 0; i < 3; i++ ) {
				String inlet ;
				if ((inlet = bufferedFileReader.readLine()) != null) {
					stringToBrut.append(inlet);
				}
			}

			for ( int keyPretender = 0; keyPretender < alphabet.getLength(); keyPretender++ ) {
				stringPretender.setLength(0);

				for ( int j = 0; j < stringToBrut.length(); j++ ) {
					char currentChar = stringToBrut.charAt(j);
					if ( alphabet.contains(currentChar) ) {
						int currentIndex = alphabet.getIndex(currentChar);
						int outputIndex = Math.floorMod(currentIndex + keyPretender, alphabet.getLength());
						char shiftedChar = alphabet.getChar(outputIndex);
						stringPretender.append(shiftedChar);
					} else {
						stringPretender.append(currentChar);
					}
				}

				for ( int j = 0; j < templatePhrases.length; j++ ) {
					if ( stringPretender.toString().contains(templatePhrases[ j ]) ) {
						return keyPretender;
					}
				}
			}


		} catch ( IOException e ) {
			throw new RuntimeException(e);
		}
		return -1;
	}

	public void decrypt(BufferedReader bufferedFileReader, int key, BufferedWriter bufferedFileWriter) {
		encrypt(bufferedFileReader, -key, bufferedFileWriter);
	}


	public void encrypt(BufferedReader bufferedFileReader, int key, BufferedWriter bufferedFileWriter) {
		int byteValue;
		try {
			while ( (byteValue = bufferedFileReader.read()) != -1 ) {
				char currentChar = (char) byteValue;

				if ( alphabet.contains(currentChar) ) {
					int currentIndex = alphabet.getIndex(currentChar);
					int outputIndex = Math.floorMod(currentIndex + key, alphabet.getLength());
					char outputChar = alphabet.getChar(outputIndex);
					bufferedFileWriter.write(outputChar);
				} else {
					bufferedFileWriter.write(currentChar);
				}
			}

			bufferedFileReader.close();
			bufferedFileWriter.close();
		} catch ( IOException e ) {
			throw new RuntimeException(e);
		}


	}

}
