/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import jakarta.servlet.http.Part;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import java.io.OutputStream;
import java.util.concurrent.CompletableFuture;

/**
 *
 * @author Hanami
 */
public class FileUtil {

    public static File saveImage(Part part) {
        File folderUpload = new File("F:\\orangeshop12\\orangeshop12\\orangeshop3HANHCONCHUANGU\\orangeshop\\build\\web\\assets\\img");
        // kiem tra folder da ton tai chua
        if (!folderUpload.exists()) {
            folderUpload.mkdirs(); // folder not exists
        }

        File file = new File("F:\\orangeshop12\\orangeshop12\\orangeshop3HANHCONCHUANGU\\orangeshop\\build\\web\\assets\\img", part.getSubmittedFileName());
        // kiem tra file xem da ton tai chua
        if (!file.exists()) {
            try {
                part.write(file.getAbsolutePath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return file;

    }

    public static File saveFileToBuild(Part part) {
        File folderUpload = new File("F:\\orangeshop12\\orangeshop12\\orangeshop3HANHCONCHUANGU\\orangeshop\\build\\web\\assets\\img");
        // kiem tra folder da ton tai chua
        if (!folderUpload.exists()) {
            folderUpload.mkdirs();
        }
        File file = new File("F:\\orangeshop12\\orangeshop12\\orangeshop3HANHCONCHUANGU\\orangeshop\\build\\web\\assets\\img", part.getSubmittedFileName());
        // kiem tra file xem da ton tai chua
        if (!file.exists()) {
            try {
                part.write(file.getAbsolutePath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return file;
    }

    public static CompletableFuture<File> uploadFileAsync(Part part, String destinationPath) {
        return CompletableFuture.supplyAsync(() -> {
            File file = new File(destinationPath, part.getSubmittedFileName()); 
            try ( OutputStream outputStream = new FileOutputStream(file)) {
                part.write(outputStream.toString());
            } catch (IOException e) {
                // Handle error appropriately, e.g., throw an exception or log the error
                throw new RuntimeException("Failed to upload file asynchronously", e);
            }
            return file;
        });
    }
}
