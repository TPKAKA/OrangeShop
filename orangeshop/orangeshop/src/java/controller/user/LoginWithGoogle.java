/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.user;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import dal.UserDAO;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.GooglePP;
import model.User;
import model.UserRole;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;
import utils.Constants;
import utils.MD5;

/**
 *
 * @author ADMIN
 */
public class LoginWithGoogle extends HttpServlet {
    // Call UserDAO
    public static UserDAO uDAO = new UserDAO();

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
        // authorization code -> OAuth 2.0
        String code = request.getParameter("code");
        try {
            if (code == null || code.isEmpty()) {
                // Nếu mã truy cập (authorization code) không tồn tại hoặc rỗng, chuyển hướng đến trang đăng nhập
                request.getRequestDispatcher("logout").forward(request, response);
            } else {
                // Lấy mã thông báo truy cập (access token) từ mã truy cập (authorization code) 
                String accessToken = getToken(code);
                // Lấy thông tin người dùng từ mã thông báo truy cập (access token) 
                GooglePP googlePP = getUserInfo(accessToken);
                request.setAttribute("id", googlePP.getId());
                request.setAttribute("name", googlePP.getName());
                request.setAttribute("email", googlePP.getEmail());
                request.setAttribute("picture", googlePP.getPicture());
                if (uDAO.getUserByEmail(googlePP.getEmail()) != null) {
                    // Nếu người dùng đã tồn tại trong cơ sở dữ liệu, lưu thông tin người dùng vào session
                    request.getSession().setAttribute("customer", uDAO.getUserByEmail(googlePP.getEmail()));
                    request.getSession().setAttribute("user_id", uDAO.getUserByEmail(googlePP.getEmail()).getUserID());
                    User u = uDAO.getUserByEmail(googlePP.getEmail());
                    if (u.getGmailID() == null) {
                        // Nếu chưa lưu Google ID cho người dùng, cập nhật thông tin người dùng với Google ID
                        User user = new User(u.getUserID(),3, googlePP.getEmail(), "", "", "",
                                googlePP.getName(), googlePP.getPicture(), Date.valueOf(LocalDate.now()), "",0,
                                googlePP.getId(), "", 1, "", "", new UserRole(3, ""));
                        uDAO.UpdateUserAfterLoginGG(user);
                    }
                } else {
                    // Nếu người dùng chưa tồn tại trong cơ sở dữ liệu, tạo tài khoản mới và lưu thông tin người dùng vào session
                    String defaultPass = "";
                    try {
                        User user = new User(0,3, googlePP.getEmail(), null, new MD5().getMD5Password(defaultPass), null, 
                                googlePP.getName(), googlePP.getPicture(), Date.valueOf(LocalDate.now()), null,0,
                                googlePP.getId(), null, 1, null, null, new UserRole(3, ""));
                        uDAO.insertSocialUser(user);
                        user = uDAO.getUserByEmail(googlePP.getEmail());
                        request.getSession().setAttribute("customer", user);
                        request.getSession().setAttribute("user_id", user.getUserID());
                    } catch (NoSuchAlgorithmException ex) {
                        Logger.getLogger(LoginWithGoogle.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                // Chuyển hướng đến trang chủ
                response.sendRedirect("home");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Use Libs: gson-2.8.2, httpclient-4.5.5, httpcore-4.4.9, commons-logging-1.2, fluent-hc-4.5.5
    // Get Token and UserInfo From Google
    public static String getToken(String code) throws ClientProtocolException, IOException {
        // Gọi API để lấy mã thông báo (access token) từ mã truy cập (authorization code)
        String response = Request.Post(Constants.GOOGLE_LINK_GET_TOKEN) // get ma thong bao : token tu Google OAuth 2.0.
                // Thiết lập dữ liệu form cho yêu cầu POST
                .bodyForm(Form.form().add("client_id", Constants.GOOGLE_CLIENT_ID)
                        .add("client_secret", Constants.GOOGLE_CLIENT_SECRET)
                        .add("redirect_uri", Constants.GOOGLE_REDIRECT_URI).add("code", code)
                        .add("grant_type", Constants.GOOGLE_GRANT_TYPE).build())
                // Yêu cầu POST và trả về string
                .execute().returnContent().asString();
        // Chuyển response từ string JSON thành đối tượng JsonObject 
        JsonObject jobj = new Gson().fromJson(response, JsonObject.class);
        String accessToken = jobj.get("access_token").toString().replaceAll("\"", "");
        return accessToken;
    }

    public static GooglePP getUserInfo(final String accessToken) throws ClientProtocolException, IOException {
        // Lấy thông tin người dùng từ mã thông báo truy cập (access token)
        String link = Constants.GOOGLE_LINK_GET_USER_INFO + accessToken;
        String response = Request.Get(link).execute().returnContent().asString();

        GooglePP googlepp = new Gson().fromJson(response, GooglePP.class);

        return googlepp;
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

}
