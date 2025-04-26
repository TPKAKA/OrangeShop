/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import jakarta.servlet.http.Part;
import java.io.File;

/**
 *
 * @author ADMIN
 */
public class DownloadFile {
    public String extractFileName(Part newsImage) {
        String name = newsImage.getHeader("content-disposition");
        String[] items = name.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length() - 1);
            }
        }
        return "";
    }

    public File getFolderUpload(String s) {
        File folderUpload = new File("F:\\orangeshop12\\orangeshop12\\orangeshop3HANHCONCHUANGU\\orangeshop\\web\\assets\\img\\" + s);
        if (!folderUpload.exists()) {
            folderUpload.mkdirs();
        }
        return folderUpload;
    }
}
