/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package productManageController;

import dal.ShopDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import model.Image;
import model.Product;
import model.ProductCategory;
import utils.DownloadFile;
import utils.Validator;

/**
 *
 * @author ADMIN
 */
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2,
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 20)
@WebServlet(name = "ProductForm", urlPatterns = {"/productform"})
public class ProductForm extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        ShopDAO dao = new ShopDAO();
        List<ProductCategory> categoryList = dao.getAllCategory();
        request.setAttribute("categoryList", categoryList);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        ShopDAO dao = new ShopDAO();
        String productId = request.getParameter("id");
        if (productId != null && !productId.isEmpty()) {
            int id = Integer.parseInt(productId);
            Product product = dao.getProductById(productId);
            List<Image> images = dao.getImageByProductId(id);
            request.setAttribute("product", product);
            request.setAttribute("images", images);
        }

        request.setAttribute("id", productId);
        request.setAttribute("verifyName", request.getParameter("verifyName"));
        request.setAttribute("verifyImg", request.getParameter("verifyImg"));
        request.setAttribute("verifyDescription", request.getParameter("verifyDescription"));
        request.setAttribute("verifyPrice", request.getParameter("verifyPrice"));
        request.setAttribute("verifyAddQuantity", request.getParameter("verifyAddQuantity"));

        request.getRequestDispatcher("productform.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

        String category = request.getParameter("category");
        String name = request.getParameter("productName");
        String description = request.getParameter("productDescription");
        String price = request.getParameter("price");
        if (price != null) {
            price = price.replaceAll(",", "");
        }
        String addQuantity = request.getParameter("addQuantity");
        if (addQuantity != null) {
            addQuantity = addQuantity.replaceAll(",", "");
        }

        Validator validator = new Validator();
        DownloadFile dow = new DownloadFile();
        List<Part> imagesPath = new ArrayList<>();
        List<String> imagesName = new ArrayList<>();
        for (int i = 0; i <= 4; i++) {
            Part part = request.getPart("image" + i);
            if (part != null) {
                String imgName = dow.extractFileName(part);
                if (!imgName.isEmpty()) {
                    imgName = imgName.replaceAll(" ", "");
                    imagesPath.add(part);
                    imagesName.add(imgName);
                }
            }
        }

        //validate
        String verifyName = null, verifyImg = null, verifyDescription = null,
                verifyPrice = null, verifyAddQuantity = null;
        verifyName = validator.checkString(name);
        for (int i = 0; i < imagesPath.size(); i++) {
            if (validator.checkImage(imagesPath.get(i)) != null) {
                verifyImg = validator.checkImage(imagesPath.get(i));
            }
        }
        if (imagesPath.isEmpty()) {
            verifyImg = "Choose at least one image";
        }
        verifyDescription = validator.checkString(description);
        BigDecimal productPrice = new BigDecimal(price);
        verifyPrice = validator.checkNumber(productPrice);
        if (addQuantity != null && !addQuantity.isEmpty()) {
            verifyAddQuantity = validator.checkNumber(Integer.parseInt(addQuantity));
        }

        ShopDAO dao = new ShopDAO();
        String productId = request.getParameter("id");

        if (verifyName != null || verifyImg != null || verifyDescription != null
                || verifyPrice != null || verifyAddQuantity != null) {
            request.setAttribute("verifyName", verifyName);
            request.setAttribute("verifyImg", verifyImg);
            request.setAttribute("verifyDescription", verifyDescription);
            request.setAttribute("verifyPrice", verifyPrice);
            request.setAttribute("verifyAddQuantity", verifyAddQuantity);
            if (productId == null || productId.isEmpty()) {
                request.getRequestDispatcher("productform.jsp").forward(request, response);
            } else {
                request.setAttribute("id", productId);
                response.sendRedirect("productform");
            }
        } else {
            try {
                for (int i = 0; i < imagesPath.size(); i++) {
                    imagesPath.get(i).write(dow.getFolderUpload("shop").getAbsolutePath() + File.separator + imagesName.get(i));
                }
            } catch (IOException e) {

            }
            if (productId == null || productId.isEmpty()) {
                try {
                    dao.addProduct(Integer.parseInt(category), name, description, productPrice, Integer.parseInt(addQuantity));
                    int maxProductId = dao.getMaxProductId();
                    for (String s : imagesName) {
                        dao.addProductImages(maxProductId, s);
                    }
                } catch (NumberFormatException e) {

                }
            } else {
                try {
                    dao.updateProduct(Integer.parseInt(productId), Integer.parseInt(category), name, description, productPrice, Integer.parseInt(addQuantity));
                    dao.deleteOldProductImages(Integer.parseInt(productId));
                    for (String s : imagesName) {
                        dao.addProductImages(Integer.parseInt(productId), s);
                    }
                } catch (NumberFormatException e) {

                }
            }
            response.sendRedirect("productmanagement");
        }

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
