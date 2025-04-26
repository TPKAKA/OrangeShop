/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

/**
 *
 * @author ADMIN
 */
import jakarta.servlet.http.Part;
import java.math.BigDecimal;

public class Validator {

    public String checkNumber(BigDecimal n) throws IllegalArgumentException {
        if (n == null) {
            return "Cannot be empty or contain only space";
        } else if (n.compareTo(BigDecimal.ZERO) <= 0) {
            return "Cannot be negative or equal to 0";
        }
        return null;
    }

    public String checkNumber(int n) throws IllegalArgumentException {
        if (n < 0) {
            return "Cannot be negative";
        }
        return null;
    }

    public String checkString(String s) throws IllegalArgumentException {
        if (s == null || s.trim().isEmpty()) {
            return "Cannot be empty or contain only space";
        }
        return null;
    }

    public String checkImage(Part newsImagePath) throws IllegalArgumentException {
        try {
            if (!newsImagePath.getSubmittedFileName().toLowerCase().endsWith(".png")
                    && !newsImagePath.getSubmittedFileName().toLowerCase().endsWith(".jpg")) {
                return "Must be in .png or .jpg format";
            }
            long fileSizeInBytes = newsImagePath.getSize();
            if (fileSizeInBytes > 2 * 1024 * 1024) {
                return "Cannot exceed 2MB";
            }
        } catch (Exception e) {
            return "Error when checking image file: " + e.getMessage();
        }
        return null;
    }

}
