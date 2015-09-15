package com.jwillians.jweb;

/*

JWeb - Request-based Java Web Framework
Copyright (C) <2015>  <Ericson Willians - JWillians>

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>. 

*/

import java.net.URI;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.UriBuilder;

/**
 * @author EricsonWillians
 */
public class JWForgotServlet extends JWServlet {

    private String email;
    private HashMap<String, String> paramNames;
    
    /**
     * Database connection related parameters.
     * @param driver String, Database driver.
     * @param dbURL String, Database URL.
     * @param dbUser String, Username used to connect to the database.
     * @param dbPassword String, Password of the username used to connect to the database.
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public JWForgotServlet(String driver, String dbURL, String dbUser, String dbPassword) throws SQLException, ClassNotFoundException {
        super(driver, dbURL, dbUser, dbPassword);
        paramNames = new HashMap();
        paramNames.put("USERNAME_SECRET_KEY", "DEFAULT");
        paramNames.put("PASSWORD_SECRET_KEY", "DEFAULT");
        paramNames.put("USERNAME", "DEFAULT");
        paramNames.put("PASSWORD", "DEFAULT");
    }
    
    /**
     * Returns a generic Object[] with the SecretKey used in the encryption in the first index and the encrypted information as a String in the second index.
     * @param var String
     * @return Object[]
     */
    public Object[] encrypt(String var) {
        try {
            KeyGenerator keyGen = KeyGenerator.getInstance("AES");
            keyGen.init(128);
            SecretKey secretKey = keyGen.generateKey();
            Cipher AESCipher = Cipher.getInstance("AES");
            String userName;
            if (isThereSuchEmail()) {
                String sql = "SELECT " + var + " FROM users WHERE email = ?";
                PreparedStatement statement = getConnection().prepareStatement(sql);
                statement.setString(1, getEmail());
                ResultSet rs = statement.executeQuery();
                while (rs.next()) {
                    userName = rs.getString(1);
                    if (userName != null) {
                        byte[] byteText = userName.getBytes();
                        AESCipher.init(Cipher.ENCRYPT_MODE, secretKey);
                        byte[] byteCipherText = AESCipher.doFinal(byteText);
                        return new Object[] {secretKey, Base64.getEncoder().encodeToString(byteCipherText)};
                    }
                }
            }
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | SQLException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException ex) {
            Logger.getLogger(JWForgotServlet.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        return null;
    }
    
    /**
     * Returns the given decrypted information as a string, given its secret key.
     * @param secretKey SecretKey
     * @param encryptedText String
     * @return String
     */
    public String decrypt(SecretKey secretKey, String encryptedText) {
        byte[] bytePlainText = null;
        try {
            KeyGenerator keyGen = KeyGenerator.getInstance("AES");
            keyGen.init(128);
            Cipher AESCipher = Cipher.getInstance("AES");
            AESCipher.init(Cipher.DECRYPT_MODE, secretKey);
            bytePlainText = AESCipher.doFinal(Base64.getDecoder().decode(encryptedText));
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException ex) {
            Logger.getLogger(JWForgotServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new String(bytePlainText);
    }
    
    /**
     * Returns an URI formed with the given link of the forgot servlet and the given encrypted username and password.
     * @param servletLink
     * @param userName Object[] (SecretKey in the first index, and String in the second).
     * @param password Object[] (SecretKey in the first index, and String in the second).
     * @return URI
     */
    public URI generateRecoverLink(String servletLink, Object[] userName, Object[] password) {
        String userEncodedKey = Base64.getEncoder().encodeToString(((SecretKey) userName[0]).getEncoded());
        String passEncodedKey = Base64.getEncoder().encodeToString(((SecretKey) password[0]).getEncoded());
        UriBuilder builder = UriBuilder.fromPath(servletLink).
                queryParam(getParamNames().get("USERNAME_SECRET_KEY"), userEncodedKey).
                queryParam(getParamNames().get("USERNAME"), userName[1]).
                queryParam(getParamNames().get("PASSWORD_SECRET_KEY"), passEncodedKey).
                queryParam(getParamNames().get("PASSWORD"), password[1]);
        URI uri = builder.build();
        return uri;
    }
    
    /**
     * Returns a HashMap with the decoded parameters as values, and their respective parameter names as keys.
     * @param request HttpServletRequest
     * @return HashMap|String, String|
     */
    public HashMap<String, String> decodeParams(HttpServletRequest request) {
        HashMap<String, String> resultHash = new HashMap();
        ArrayList<SecretKey> secretKeys = new ArrayList();
        getParamNames().keySet().stream().forEach((key) -> {
            byte[] decodedKey = Base64.getDecoder().decode(request.getParameter(getParamNames().get(key)));
            resultHash.put(getParamNames().get(key), decrypt(new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES"), request.getParameter(getParamNames().get(key))));
        });
        return resultHash;
    }
    
    /**
     * Returns true if the given email exists in the database.
     * @param email String
     * @return boolean
     * @throws SQLException 
     */
    private boolean isThereSuchEmail() throws SQLException {
        String sql = "SELECT email FROM users WHERE email = ?";
        boolean result = false;
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setString(1, getEmail());
            ResultSet rs = statement.executeQuery();
            result = rs.next();
        } catch (SQLException ex) {
            Logger.getLogger(JWForgotServlet.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        return result;
    }

    /**
     * Returns the email used to change the password.
     * @return String
     */
    public String getEmail() {
        return email;
    }

    /**
     * Returns the email used to change the password.
     * @param email String
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Returns the HashMap with the parameter names used to generate the recovery link.
     * @return HashMap|String, String|
     */
    public HashMap<String, String> getParamNames() {
        return paramNames;
    }

    /**
     * Sets the HashMap with the parameter names used to generate the recovery link.
     * @param paramNames HashMap|String, String|
     */
    public void setParamNames(HashMap<String, String> paramNames) {
        this.paramNames = paramNames;
    }
    
}
