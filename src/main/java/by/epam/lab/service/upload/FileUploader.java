package by.epam.lab.service.upload;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.commons.fileupload.FileItem;

import by.epam.lab.exception.ServiceException;

/**
 * Designed to work with file uploading process in singleton style.
 */
public class FileUploader {

	private static final ReentrantLock lock = new ReentrantLock();
	private static final String FULL_SAVE_PATH = "fullSavePath";
	private static final String RELATIVE_SAVE_PATH = "relativeSavePath";

	private static FileUploader instance;
	private static AtomicBoolean instanceCreated = new AtomicBoolean(false);

	private String fullSavePath;
	private String relativeSavePath;

	public static FileUploader getInstance() {

		if (!instanceCreated.get()) {
			lock.lock();
			try {
				if (instance == null) {
					instance = new FileUploader();
					instanceCreated.set(true);
				}
			} finally {
				lock.unlock();
			}
		}

		return instance;
	}

	private FileUploader() {
		initializeData();
	}

	public String getFullSavePath() {
		return fullSavePath;
	}

	public String getRelativeSavePath() {
		return relativeSavePath;
	}

	/**
	 * Designed for file uploading data initialization.
	 */
	private void initializeData() {

		Class<? extends FileUploader> aClass = this.getClass();
		ClassLoader classLoader = aClass.getClassLoader();
		InputStream inputStream = classLoader.getResourceAsStream("image_upload.properties");

		Properties property = new Properties();
		try {
			property.load(inputStream);
		} catch (IOException e) {
			throw new IllegalArgumentException("File with properties not found! " + e.getMessage(), e);
		}

		fullSavePath = property.getProperty(FULL_SAVE_PATH);
		relativeSavePath = property.getProperty(RELATIVE_SAVE_PATH);
	}

	/**
	 * Upload file to destination directory.
	 *
	 * @param fileItem a {@link FileItem} object that contains file that should be
	 *                 written.
	 * @return a {@link String} object that contains written file name.
	 * @throws ServiceException Signals that service exception of some sort has
	 *                          occurred.
	 */
	public String upload(FileItem fileItem) throws ServiceException {

		String fileName = fileItem.getName();
		String pathname = fullSavePath + fileName;

		try {
			File uploadedFile = new File(pathname);
			fileItem.write(uploadedFile);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}

		return fileName;
	}
}
