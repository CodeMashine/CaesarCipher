package org.javarush_Module_1_Task;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.StandardCharsets;

public class Cipher {
	private final char[] alphabet;

	private final String[] templatePhrases;

	public Cipher(char[] alphabet, String[] templatePhrases) {
		this.alphabet = alphabet;
		this.templatePhrases = templatePhrases;
	}


	public int findKeyToDecode(FileChannel sourceChannel) {
		ByteBuffer byteInputBuffer = ByteBuffer.allocate(256);
		try {
			sourceChannel.read(byteInputBuffer);
			CharsetDecoder decoder = StandardCharsets.UTF_8.newDecoder();

			byteInputBuffer.flip();

			CharBuffer inputCharBuffer = decoder.decode(byteInputBuffer);

			for ( int i = 1; i < alphabet.length; i++ ) {
				String decodedText = getDecodeText(inputCharBuffer, -i);
				inputCharBuffer.position(0);
				for ( String phrase : templatePhrases ) {
					int index = decodedText.indexOf(phrase);
					if ( index != -1 ) {
						return i;
					}
				}
			}
		} catch ( Exception e ) {
			throw new RuntimeException(e);
		}
		return -1;
	}


	private String getDecodeText(CharBuffer inputCharBuffer, int key) {
		StringBuilder output = new StringBuilder();
		while ( inputCharBuffer.hasRemaining() ) {
			char inputChar = inputCharBuffer.get();
			int outputIndex = getOutputIndex(inputChar, key);
			char outputChar = getOutputChar(outputIndex);
			output.append(outputChar);
		}
		return output.toString();
	}


	public void decrypt(FileChannel sourceChannel, int key, FileChannel destChannel) {
		encrypt(sourceChannel, -key, destChannel);
	}


	public void encrypt(FileChannel sourceChannel, int key, FileChannel destChannel) {

		ByteBuffer byteInputBuffer = ByteBuffer.allocate(1024);

		ByteBuffer byteOutputBuffer;

		try {
			CharsetDecoder decoder = StandardCharsets.UTF_8.newDecoder();
			CharsetEncoder encoder = StandardCharsets.UTF_8.newEncoder();

			int bytesRead = sourceChannel.read(byteInputBuffer);

			while ( bytesRead != -1 ) {
				byteInputBuffer.flip();

				CharBuffer inputCharBuffer = decoder.decode(byteInputBuffer);

				CharBuffer outputCharBuffer = CharBuffer.allocate(inputCharBuffer.length());

				while ( inputCharBuffer.hasRemaining() ) {
					char inputChar = inputCharBuffer.get();
					int outputIndex = getOutputIndex(inputChar, key);
					char outputChar = getOutputChar(outputIndex);
					outputCharBuffer.put(outputChar);
				}
				outputCharBuffer.flip();
				byteInputBuffer.clear();
				byteOutputBuffer = encoder.encode(outputCharBuffer);
				destChannel.write(byteOutputBuffer);
				byteOutputBuffer.clear();
				bytesRead = sourceChannel.read(byteInputBuffer);
			}


		} catch ( Exception e ) {
			throw new RuntimeException(e);
		}
	}

	private int getOutputIndex(char letter, int key) {
		int inletIndex = 0;

		boolean isFound = false;

		for ( int i = 0; i < alphabet.length; i++ ) {
			char current = alphabet[ i ];

			if ( current == letter ) {
				inletIndex = i;
				isFound = true;
				break;
			}
		}

		if ( !isFound ) {
			return 68;
		}

		int outputIndex = Math.floorMod(inletIndex + key, alphabet.length);

		return outputIndex;
	}

	private char getOutputChar(int outputCharIndex) {
		char letter = alphabet[ outputCharIndex ];
		return letter;
	}


}
