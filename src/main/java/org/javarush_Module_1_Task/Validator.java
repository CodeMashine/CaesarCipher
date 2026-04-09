package org.javarush_Module_1_Task;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Validator {
	private int possibleKeyValue;

	Validator(int possibleKeyValue) {
		this.possibleKeyValue = possibleKeyValue - 1;
	}

	public boolean validateDestinationPath(String destinationPath) {
		if (destinationPath == null || destinationPath.isEmpty()) {
			return false;
		}
		if(destinationPath.length() > 256){
			return false;
		}

		Path path = Path.of(destinationPath);

		String fileName = path.getFileName().toString();
		Path fileRoot = path.getParent();

		if (!Files.isDirectory(fileRoot) ) {
			return false;
		}

		boolean isValidFileName = validateFileName(fileName);

		if ( !isValidFileName ) {
			return false;
		}

		boolean isValidDestinationPath = path.isAbsolute();
		if ( !isValidDestinationPath ) {
			return false;
		}

		return true;
	}

	public boolean validateFileName(String fileName) {
		boolean isEndsTXT = fileName.endsWith(".txt");
		if ( !isEndsTXT ) {
			return false;
		}

		String name = fileName.substring(0, fileName.length() - 4);

		if ( name.matches(".*[\\\\/:*?\"<>|].*") ) {
			return false;
		}
		if(name.startsWith(".")||name.endsWith(".") ||name.startsWith(" ") ||name.endsWith(" ") ) {
			return false;
		}

		return true;
	}


	public boolean checkExistingFile(String address) {
		Path path = Path.of(address);
		return Files.exists(path);
	}

	public boolean checkKey(String key) {
		int keyPretend;
		try {
			keyPretend = Integer.parseInt(key);
		} catch ( NumberFormatException e ) {
			return false;
		}

		if ( keyPretend > possibleKeyValue || keyPretend < 0 ) {
			return false;
		}

		return true;
	}


}
