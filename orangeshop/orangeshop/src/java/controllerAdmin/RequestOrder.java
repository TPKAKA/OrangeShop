/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllerAdmin;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import model.KiotVietOrder;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 *
 * @author ACER
 */
@WebServlet(name = "RequestOrder", urlPatterns = {"/requestorder"})
public class RequestOrder extends HttpServlet {

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
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet RequestOrder</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RequestOrder at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accessToken = getAccessToken();
        System.out.println("AccessToken:" + accessToken);

        String[] split_json = accessToken.split(":");

        // Kiểm tra xem access token có được lấy không
        if (split_json.length >= 2 && split_json[1] != null && !split_json[1].isEmpty()) {

            String access_token = split_json[1].replaceAll(",\"expires_in\"", "").replaceAll("\"", "");

            System.out.println(access_token);
            String responseData = "";

            try ( CloseableHttpClient httpClient = HttpClients.createDefault()) {
                // Tạo yêu cầu GET với URL API
                HttpGet httpRequest = new HttpGet("https://public.kiotapi.com/orders");

                // Thiết lập các header
                httpRequest.setHeader("Retailer", "swp391orangeshopg5");
                httpRequest.setHeader("Authorization", "Bearer " + access_token);
                httpRequest.setHeader("Content-Type", "application/x-www-form-urlencoded");

                // Gửi yêu cầu và nhận phản hồi
                HttpResponse httpResponse = httpClient.execute(httpRequest);
                HttpEntity responseEntity = httpResponse.getEntity();

                // Kiểm tra xem phản hồi có dữ liệu không
                if (responseEntity != null) {
                    // Đọc dữ liệu từ phản hồi
                    responseData = EntityUtils.toString(responseEntity);
//                System.out.println(responseData);
                    List<String> list = new ArrayList<>();
                    String[] split_responseData = responseData.split("\\{\"id\"");
                    for (String part : split_responseData) {
                        part = part.trim();
                        list.add(part);

                    }

                    for (int i = 0; i < list.size(); i++) {
                        List<KiotVietOrder> listki = new ArrayList<>();
                        for (String o : list) {
                            String[] parts = o.split(",");
                            String totalValue = "";
                            String id = "";
                            String purchaseDate = "";
                            String customerName = "";
                            String totalPayment = "";
                            String statusValue = "";

                            for (String part : parts) {
                                if (part.startsWith("\"code\"")) {
                                    id = part.split(":")[1].replaceAll("\"", "").trim();
                                    System.out.println("Id: " + id);
                                } else if (part.startsWith("\"total\"")) {
                                    totalValue = part.split(":")[1].replaceAll("\"", "").trim();
                                    System.out.println("Total: " + totalValue);
                                } else if (part.startsWith("\"purchaseDate\"")) {
                                    purchaseDate = part.split(":")[1].replaceAll("\"", "").trim();
                                    System.out.println("Purchase Date: " + purchaseDate);
                                } else if (part.startsWith("\"customerName\"")) {
                                    customerName = part.split(":")[1].replaceAll("\"", "").trim();
                                    System.out.println("Customer Name: " + customerName);
                                } else if (part.startsWith("\"totalPayment\"")) {
                                    totalPayment = part.split(":")[1].replaceAll("\"", "").trim();
                                    System.out.println("Total Payment: " + totalPayment);
                                } else if (part.startsWith("\"statusValue\"")) {
                                    statusValue = part.split(":")[1].replaceAll("\"", "").trim();
                                    System.out.println("Status Value: " + statusValue);
                                }
                            }

                            // Create a new KiotVietOrder object and add it to the list
                            KiotVietOrder model = new KiotVietOrder(id, totalValue, purchaseDate, customerName, totalPayment, statusValue);
                            listki.add(model);
                            request.setAttribute("order", listki);
                        }                  

                    }

                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            System.out.println("Không thể lấy access token.");
        }

       request.getRequestDispatcher("requestavoice.jsp").forward(request, response);
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

    private static String getDataFromAPI(String accessToken) {
        String responseData = "";

        try ( CloseableHttpClient httpClient = HttpClients.createDefault()) {
            // Tạo yêu cầu GET với URL API
            HttpGet request = new HttpGet("https://public.kiotapi.com/orders");

            // Thiết lập các header
            request.setHeader("Retailer", "orangeg5");
            request.setHeader("Authorization", "Bearer " + accessToken);
            request.setHeader("Content-Type", "application/x-www-form-urlencoded");

            // Gửi yêu cầu và nhận phản hồi
            HttpResponse response = httpClient.execute(request);
            HttpEntity responseEntity = response.getEntity();

            // Kiểm tra xem phản hồi có dữ liệu không
            if (responseEntity != null) {
                // Đọc dữ liệu từ phản hồi
                responseData = EntityUtils.toString(responseEntity);
                System.out.println(responseData);
                String[] split_responseData = responseData.split(",");
                for (String part : split_responseData) {
                    // Loại bỏ khoảng trắng ở đầu và cuối chuỗi
                    part = part.trim();
                    // Kiểm tra xem phần hiện tại có phải là trường bạn quan tâm không
                    if (part.startsWith("\"total\"")) {
                        String a = part.split(":")[1].replaceAll("\"", "").trim();
//                        System.out.println("Total: " + a);
//                        request.setAttribute("totalValue", a);
                    } else if (part.startsWith("\"id\"")) {
                        String b = part.split(":")[1].replaceAll("\"", "").trim();
                        System.out.println("Id: " + b);
                    } else if (part.startsWith("\"purchaseDate\"")) {
                        String c = part.split(":")[1].replaceAll("\"", "").trim();
                        System.out.println("Purchase Date: " + c);
                    } else if (part.startsWith("\"customerName\"")) {
                        String d = part.split(":")[1].replaceAll("\"", "").trim();
                        System.out.println("Customer Name: " + d);
                    } else if (part.startsWith("\"totalPayment\"")) {
                        String e = part.split(":")[1].replaceAll("\"", "").trim();
                        System.out.println("Total Payment: " + e);
                    } else if (part.startsWith("\"statusValue\"")) {
                        String f = part.split(":")[1].replaceAll("\"", "").trim();
                        System.out.println("Status Value: " + f);
                    }

//            System.out.println(part.trim()); // trim() để loại bỏ các khoảng trắng dư thừa
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return responseData;
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
