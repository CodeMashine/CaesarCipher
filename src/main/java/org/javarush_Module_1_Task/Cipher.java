package org.javarush_Module_1_Task;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.StandardCharsets;

public class Cipher {
	private final char[] alphabet;

	public Cipher(char[] alphabet) {
		this.alphabet = alphabet;
	}


	public void encrypt(FileChannel sourceChannel, int key, FileChannel destChannel) {
		ByteBuffer byteInputBuffer = ByteBuffer.allocate(1024);

		ByteBuffer byteOutputBuffer = ByteBuffer.allocate(1024);

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

		int outputIndex = inletIndex + key;

		if ( outputIndex > alphabet.length - 1 ) {
			outputIndex = outputIndex - alphabet.length - 1;
		}
		return outputIndex;
	}

	private char getOutputChar(int outputCharIndex) {
		char letter = alphabet[ outputCharIndex ];
		return letter;
	}


}
