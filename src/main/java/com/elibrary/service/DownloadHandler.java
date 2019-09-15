package com.elibrary.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

public class DownloadHandler {

	public static void downloadFile(HttpServletResponse response, File file) {
		if (file != null) {
			// For browser download::
//			response.addHeader("Content-Disposition", "attachment;filename=" + file.getName());
			// To open in tab::
			response.addHeader("Content-Disposition", "inline;filename=" + file.getName());
			// Try with resources-- Closes the Closeable Objects
			try (FileInputStream is = new FileInputStream(file); OutputStream os = response.getOutputStream();) {
				byte[] out = new byte[4096];
				while (is.read(out, 0, 4096) != -1) {
					os.write(out, 0, 4096);
				}
				os.flush();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}
}
