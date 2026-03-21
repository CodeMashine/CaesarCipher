package org.javarush_Module_1_Task;

import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class Cipher {
	private StringBuilder cipherText = new StringBuilder();

	public void encrypt(FileChannel sourseChannel, int key, FileChannel destChannel) {
		ByteBuffer byteInputBuffer = ByteBuffer.allocate(1024);
		ByteBuffer byteOutputBuffer = ByteBuffer.allocate(1024);

		try {
			int bytesRead = sourseChannel.read(byteInputBuffer);
			while ( bytesRead != -1 ) {
				while ( byteInputBuffer.hasRemaining() ) {
					char current = byteInputBuffer.getChar();
					int outputIndex = getOutputIndex(current, key);
					byte outputByte = getOutputByte(outputIndex);
					byteOutputBuffer.put(outputByte);

				}
				destChannel.write(byteOutputBuffer);

				byteInputBuffer.clear();
				byteOutputBuffer.clear();
				bytesRead = sourseChannel.read(byteInputBuffer);

			}

			sourseChannel.close();
			destChannel.close();
		} catch ( Exception e ) {
			throw new RuntimeException(e);
		}
	}

	private int getOutputIndex(char letter, int key) {
		int inletIndex = 0;

		for ( int i = 0; i < CaesarCipher.ALPHABET.length; i++ ) {
			char current = CaesarCipher.ALPHABET[ i ];

			if ( current == letter ) {
				inletIndex = i;
				break;
			}else{
				inletIndex = 68;
			}
		}
		int outputIndex = inletIndex + key;

		if ( outputIndex > CaesarCipher.ALPHABET.length - 1 ) {
			outputIndex = outputIndex - CaesarCipher.ALPHABET.length - 1;
		}
		return outputIndex;
	}

	private byte getOutputByte(int outputCharIndex) {
		byte letter = (byte) CaesarCipher.ALPHABET[ outputCharIndex ];
		return letter;
	}


}
