package org.javarush_Module_1_Task.worker;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;


public class Cipher {
	private final char[] alphabet;
	private final Alphabet alphabetInstance;

	private final String[] templatePhrases;

	public Cipher(char[] alphabet, String[] templatePhrases, Alphabet alphabetInstance) {
		this.alphabet = alphabet;
		this.templatePhrases = templatePhrases;
		this.alphabetInstance = alphabetInstance;
	}


	public int findKeyToDecode(BufferedReader bufferedFileReader) {
		StringBuilder stringToBrut = new StringBuilder();
		StringBuilder stringPretender = new StringBuilder();

		try {
			for ( int i = 0; i < 10; i++ ) {
				stringToBrut.append(bufferedFileReader.readLine());
			}

			for ( int i = 0; i < alphabetInstance.getLength(); i++ ) {

				for ( int j = 0; j < stringToBrut.length(); j++ ) {
					char currentChar = stringToBrut.charAt(j);
					if ( alphabetInstance.contains(currentChar) ) {
						int currentIndex = alphabetInstance.getIndex(currentChar);
						int outputIndex = Math.floorMod(currentIndex + i, alphabetInstance.getLength());
						char shiftedChar = alphabetInstance.getChar(outputIndex);
						stringPretender.append(shiftedChar);
					} else {
						stringPretender.append(currentChar);
					}
				}

				for ( int j = 0; j < templatePhrases.length; j++ ) {
					if ( stringPretender.toString().equals(templatePhrases[ j ]) ) {
						return j;
					}

					stringPretender.setLength(0);
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

				if ( alphabetInstance.contains(currentChar) ) {
					int currentIndex = alphabetInstance.getIndex(currentChar);
					int outputIndex = Math.floorMod(currentIndex + key, alphabetInstance.getLength());
					char outputChar = alphabetInstance.getChar(outputIndex);
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
