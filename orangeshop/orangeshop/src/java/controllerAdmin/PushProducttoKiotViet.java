/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllerAdmin;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 *
 * @author ACER
 */
@WebServlet(name = "PushProducttoKiotVite", urlPatterns = {"/pushproducttokiotviet"})
public class PushProducttoKiotViet extends HttpServlet {

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
        String accessToken = getAccessToken(); // Lấy access token
        System.out.println("Access Token: " + accessToken);
        String[] split_json = accessToken.split(":");
        String name = request.getParameter("name");
        String code = request.getParameter("code");
        String barCode = request.getParameter("barCode");
        double price = Double.parseDouble(request.getParameter("price"));

        String imageUrl = request.getParameter("imageUrl");

        if (split_json[1] != null && !split_json[1].isEmpty()) {

            String access_token = split_json[1].replaceAll(",\"expires_in\"", "").replaceAll("\"", "");

            System.out.println(access_token);
            String onHand = request.getParameter("onHand");
            createProduct(access_token, name, code, barCode, onHand, imageUrl, price); // Tạo mới hàng hóa
            response.sendRedirect("dashboard");
        } else {
            System.out.println("Không thể lấy access token.");
        }
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
    private static String getAccessToken() {
        String clientId = "e0c62969-ea6d-4e54-ae1f-af23dfb102c7";
        String clientSecret = "6CA42C844E52B4C9E29E616CB53156BB7CE0E8E3";
        String accessToken = "";

        try ( CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPost request = new HttpPost("https://id.kiotviet.vn/connect/token");

            // Tạo body cho yêu cầu
            String requestBody = "scopes=PublicApi.Access&grant_type=client_credentials&client_id="
                    + clientId + "&client_secret=" + clientSecret;

            System.out.println(requestBody);

            // Thiết lập body cho yêu cầu
            StringEntity entity = new StringEntity(requestBody);
            request.setHeader("Content-Type", "application/x-www-form-urlencoded");
            request.setEntity(entity);

            // Gửi yêu cầu và nhận phản hồi
            HttpResponse response = httpClient.execute(request);
            HttpEntity responseEntity = response.getEntity();

            // Đọc và trả về access token từ phản hồi
            accessToken = EntityUtils.toString(responseEntity);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return accessToken;
    }

    private static void createProduct(String accessToken, String name, String code, String barCode, String onHand, String imageUrl, double price) {
        System.out.println(accessToken);
        // Dữ liệu JSON của sản phẩm
        String jsonProductData = "{\n"
                + "    \"name\": \"" + name + "\",\n"
                + "    \"code\": \"" + code + "\",\n"
                + "    \"barCode\": \"" + barCode + "\",\n"
                + "    \"fullName\": \"\",\n"
                + "    \"categoryId\": 718386, \n"
                + "    \"allowsSale\": true,\n"
                + "    \"description\": \"\",\n"
                + "    \"hasVariants\": false,\n"
                + "    \"isProductSerial\": false,\n"
                + "    \"attributes\": [],\n"
                + "    \"unit\": \"\",\n"
                + "    \"masterProductId\": null,\n"
                + "    \"masterUnitId\": null,\n"
                + "    \"conversionValue\": 0.0,\n"
                + "    \"inventories\": [\n"
                + "        {\n"
                + "              \"branchId\": 78241,\n"
                + "            \"branchName\": \"Chi nhánh trung tâm\",\n"
                + "            \"onHand\": " + onHand + ",\n"
                + "            \"cost\": 0.0,\n"
                + "            \"reserved\": 0.0\n"
                + "        }\n"
                + "    ],\n"
                + "    \"basePrice\": " + price + ",\n"
                + "    \"weight\": 0.5,\n"
                + "    \"images\": [\"" + imageUrl + "\"]\n"
                + "}";
        System.out.println(jsonProductData);

        // Tạo một yêu cầu HTTP POST
        HttpPost request = new HttpPost("https://public.kiotapi.com/products");
        // Thiết lập header cho yêu cầu
        request.setHeader("Retailer", "swp391orangeshopg5");
        request.setHeader("Authorization", "Bearer " + accessToken);
        request.setHeader("Content-Type", "application/json");
        // Thiết lập dữ liệu JSON vào body của yêu cầu
        StringEntity entity = new StringEntity(jsonProductData, ContentType.APPLICATION_JSON);
        request.setEntity(entity);

        try ( CloseableHttpClient httpClient = HttpClients.createDefault()) {
         
            HttpResponse response = httpClient.execute(request);

     
            HttpEntity responseEntity = response.getEntity();
            String responseString = EntityUtils.toString(responseEntity);
            System.out.println("Response from server: " + responseString);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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

//    public static void main(String[] args) {
//        String accessToken = getAccessToken(); // Lấy access token
//        System.out.println("Access Token: " + accessToken);
//        String[] split_json = accessToken.split(":");
//        if (split_json[1] != null && !split_json[1].isEmpty()) {
////            System.out.println(split_json[1]);ggg
//
//            String access_token = split_json[1].replaceAll(",\"expires_in\"", "").replaceAll("\"", "");
//            System.out.println(access_token);
//            createProduct(access_token, "kaka", null); // Tạo mới hàng hóa
//        } else {
//            System.out.println("Không thể lấy access token.");
//        }
//    }
}
