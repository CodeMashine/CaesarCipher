package org.javarush_Module_1_Task.workers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileManager {

	public BufferedReader createReader(String address) {
		try {
			BufferedReader reader = Files.newBufferedReader(Path.of(address));
			return reader;
		} catch ( Exception e ) {
			throw new RuntimeException(e);
		}
	}

	public BufferedWriter createWriter(String address) {
		try {
			BufferedWriter writer = Files.newBufferedWriter(Path.of(address));
			return writer;
		} catch ( Exception e ) {
			throw new RuntimeException(e);
		}
	}
}
