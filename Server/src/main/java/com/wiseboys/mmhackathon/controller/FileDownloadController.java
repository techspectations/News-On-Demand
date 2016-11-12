/**
 * 
 */
package com.wiseboys.mmhackathon.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.wiseboys.mmhackathon.utils.Constants;

/**
 * Controller for downloading a file
 * 
 * @author Irudaya Raj Nov 12, 2016 2016
 */
@Controller
public class FileDownloadController {

	@Autowired
	private ServletContext servletContext;

	/**
	 * Size of a byte buffer to read/write file
	 */
	private static final int BUFFER_SIZE = 4096;

	/**
	 * Path of the file to be downloaded, relative to application's directory
	 */
	private String filePath = "/downloads/SpringProject.zip";

	// Declaring API end points
	private final static String DOWNLOAD_FILE = Constants.NEWS_API_BASE_PATH + "/download/{filePath}";

	/**
	 * Method for handling file download request from client
	 * @throws FileNotFoundException 
	 */
	@RequestMapping(value = DOWNLOAD_FILE, method = RequestMethod.GET, produces = Constants.RESPONSE_DATA_TYPE)
	public void getArticlesByUser(HttpServletRequest request, HttpServletResponse response,
			@PathVariable String filePath) throws FileNotFoundException {
		// get absolute path of the application
		String appPath = request.getContextPath();
		System.out.println("appPath = " + appPath);

		// construct the complete absolute path of the file
		String fullPath = appPath + filePath;
		File downloadFile = new File(fullPath);
		FileInputStream inputStream = new FileInputStream(downloadFile);

		// get MIME type of the file
		String mimeType = servletContext.getMimeType(fullPath);
		if (mimeType == null) {
			// set to binary type if MIME mapping not found
			mimeType = "application/octet-stream";
		}
		System.out.println("MIME type: " + mimeType);

		// set content attributes for the response
		response.setContentType(mimeType);
		response.setContentLength((int) downloadFile.length());

		// set headers for the response
		String headerKey = "Content-Disposition";
		String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
		response.setHeader(headerKey, headerValue);

		// get output stream of the response
		OutputStream outStream;
		try {
			outStream = response.getOutputStream();
			byte[] buffer = new byte[BUFFER_SIZE];
			int bytesRead = -1;

			// write bytes read from the input stream into the output stream
			while ((bytesRead = inputStream.read(buffer)) != -1) {
				outStream.write(buffer, 0, bytesRead);
			}

			inputStream.close();
			outStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
