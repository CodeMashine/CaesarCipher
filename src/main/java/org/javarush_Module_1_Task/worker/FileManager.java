package org.javarush_Module_1_Task.worker;

import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

public class FileManager {

	public FileChannel createReadFileChannel(String address) {
		try {
			RandomAccessFile aFile = new RandomAccessFile(address, "r");
			FileChannel channel = aFile.getChannel();
			return channel;
		} catch ( Exception e ) {
			throw new RuntimeException(e);
		}
	}

	public FileChannel createWriteFileChannel(String address) {
		try {
			RandomAccessFile aFile = new RandomAccessFile(address, "rw");
			FileChannel channel = aFile.getChannel();
			return channel;
		} catch ( Exception e ) {
			throw new RuntimeException(e);
		}
	}
}
