/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

/**
 *
 * @author Dell E7450
 */
public class Constants {
// Google
    // Config and receive ID and Secret Google
    public static String GOOGLE_CLIENT_ID = "1023249071341-cuhk6ioa4frvop3r14jdmr8hvkckqit9.apps.googleusercontent.com";
    public static String GOOGLE_CLIENT_SECRET = "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";
    // Config URI redirect after Google authenticate
    public static String GOOGLE_REDIRECT_URI = "http://localhost:9999/TemplateAdminT1/Homemanagement/loginwithgoogle";
    // URL to request an access token from Google after the user has been authenticated.
    public static String GOOGLE_LINK_GET_TOKEN = "https://accounts.google.com/o/oauth2/token";
    // URL to get user information from Google's API using an access token.
    public static String GOOGLE_LINK_GET_USER_INFO = "https://www.googleapis.com/oauth2/v1/userinfo?access_token=";
    public static String GOOGLE_GRANT_TYPE = "authorization_code";
    
}
