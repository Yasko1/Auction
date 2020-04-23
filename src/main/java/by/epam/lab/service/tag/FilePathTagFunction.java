package by.epam.lab.service.tag;

import by.epam.lab.service.upload.FileUploader;
import com.google.common.base.Preconditions;

/**
 * Defines functional file path tag.
 */
public class FilePathTagFunction {

	private static FileUploader fileUploader = FileUploader.getInstance();

	/**
	 * The method makes up path to file by file name.
	 *
	 * @param fileName a {@link String} object that contain file name.
	 * @return a {@link String} object that contain path to file.
	 */
	public static String buildFilePath(String fileName) {

		Preconditions.checkNotNull(fileName, "Attribute or parameter is null!");

		String savePath = fileUploader.getRelativeSavePath();

		return savePath + fileName;
	}

}