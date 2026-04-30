package org.javarush_Module_1_Task.worker;

import java.util.HashMap;
import java.util.Map;

public class Alphabet {
	private char[] alphabet;
	public Map<Character, Integer> mapAlphabet = new HashMap<>();

	public Alphabet(char[] alphabet) {
		this.alphabet = alphabet;
		constructCharMap(alphabet);
	}

	private void constructCharMap(char[] alphabet) {
		for ( int i = 0; i < alphabet.length; i++ ) {
			Character currentChar = alphabet[ i ];
			mapAlphabet.put(currentChar, i);
		}
	}

	public int getLength() {
		return alphabet.length;
	}


	public char getCharFromIndex(int index) {
		return alphabet[ index ];
	}


	public Integer getIndexfromChar(char word) {
		return mapAlphabet.get(word);
	}
}
