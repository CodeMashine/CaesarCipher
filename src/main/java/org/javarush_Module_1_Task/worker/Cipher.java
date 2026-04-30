package org.javarush_Module_1_Task.worker;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class Cipher {
	private final char[] alphabet;
	private final Alphabet alphabetInstance;

	private final String[] templatePhrases;

	public Cipher(char[] alphabet, String[] templatePhrases , Alphabet alphabetInstance) {
		this.alphabet = alphabet;
		this.templatePhrases = templatePhrases;
		this.alphabetInstance = alphabetInstance;
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

		ByteBuffer byteInputBuffer = ByteBuffer.allocate(8192);

		ByteBuffer byteOutputBuffer;

		try {
//			CharsetDecoder decoder = StandardCharsets.UTF_8.newDecoder();
			Charset charset = StandardCharsets.UTF_8;
			CharsetDecoder decoder = charset.newDecoder();
//			CharsetEncoder encoder = StandardCharsets.UTF_8.newEncoder();
			CharsetEncoder encoder = charset.newEncoder();

			int bytesRead = sourceChannel.read(byteInputBuffer);

			while ( bytesRead != -1 ) {
				byteInputBuffer.flip();

				CharBuffer inputCharBuffer = decoder.decode(byteInputBuffer);
//				ByteBuffer inputCharBuffer = byteInputBuffer ;

//				CharBuffer outputCharBuffer = CharBuffer.allocate(inputCharBuffer.length());
				CharBuffer outputCharBuffer = CharBuffer.allocate(inputCharBuffer.limit());

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

//				destChannel.write(outputCharBuffer);


				byteOutputBuffer.clear();
				bytesRead = sourceChannel.read(byteInputBuffer);
			}

		} catch ( Exception e ) {
			throw new RuntimeException(e);
		}finally {
			try {
				sourceChannel.close();
				destChannel.close();
			}catch( Exception e ) {
				throw new RuntimeException(e);
			}
		}
	}

	private int getOutputIndex(char letter, int key) {

		if(alphabetInstance.mapAlphabet.containsKey(letter)) {
			int index =  alphabetInstance.getIndexfromChar(letter);
			return Math.floorMod(index + key, alphabetInstance.getLength());
		}

		return

	}

	private char getOutputChar(int outputCharIndex) {
		char letter = alphabetInstance.getCharFromIndex(outputCharIndex);
		return letter;
	}


}
