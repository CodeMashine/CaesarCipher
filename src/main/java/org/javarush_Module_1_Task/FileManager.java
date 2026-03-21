package org.javarush_Module_1_Task;

import java.io.RandomAccessFile;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.Channel;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileManager {

	public boolean checkExisting(String address) {
		Path path = Path.of(address);
		return Files.exists(path);
	}

	public FileChannel readFileChannel(String address) {
		try {
			RandomAccessFile aFile = new RandomAccessFile(address, "r");
			FileChannel channel = aFile.getChannel();
			return channel;
		} catch ( Exception e ) {
			throw new RuntimeException(e);
		}
	}



	public FileChannel writeFileChannel(String address) {
		try {
			RandomAccessFile aFile = new RandomAccessFile(address, "rw");
			FileChannel channel = aFile.getChannel();
			return channel;
		} catch ( Exception e ) {
			throw new RuntimeException(e);
		}
	}



}
