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

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.RequestDispatcher;
import java.sql.*;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

/**
 * A JWServlet is an HttpServlet with default connection to a database.
 * It also has a default JWHTMLPageMap object, which is used to store JWHTMLPage objects, which are used in the HttpServletResponse to the client.
 * It also provides a default RequestDispatcher object, to dispatch the client to a specific resource in the server.
 * @author EricsonWillians
 */
public class JWServlet extends HttpServlet {
    
    private static final long serialVersionUID = 1L;
    private String dbDriver;
    private String dbURL;
    private String dbUser;
    private String dbPassword;
    private Connection connection;
    private HashMap<String, Object> requestData;
    private JWHTMLPageMap pageMap;
    private RequestDispatcher requestDispatcher;
    
    /**
     * It sets up the HttpServlet with connection to the database.
     * @param driver The database driver.
     * @param dbURL The database uniform resource locator.
     * @param dbUser The username used to connect to the database.
     * @param dbPassword The password of the username used to connect to the database.
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public JWServlet(String driver, String dbURL, String dbUser, String dbPassword) throws SQLException, ClassNotFoundException {
        this.dbDriver = driver;
        this.dbURL = dbURL;
        this.dbUser = dbUser;
        this.dbPassword = dbPassword;
        Class.forName(this.dbDriver);
        this.connection = DriverManager.getConnection(this.dbURL, this.dbUser, this.dbPassword);
        pageMap = new JWHTMLPageMap();
    }
    
    /**
     * Fetches all the parameters with the given parameter names, storing them inside the JWServlet inner HashMap (requestData).
     * @param request
     * HttpServletRequest object.
     * @param paramNames 
     * Array of String objects (Parameter names).
     */
    public void fetchParams(HttpServletRequest request, String[] paramNames) {
        java.util.Date date = new java.util.Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        HashMap<String, Object> hashMap = new HashMap();
        for (String s : paramNames) {
            hashMap.put(s, request.getParameter(s));
        }
        hashMap.put("timestamp", timestamp);
        requestData = hashMap;
    }
    
    /**
     * Dispatches the client to the given destination (Resource).
     * @param request HttpServletRequest object.
     * @param response HttpServletResponse object.
     * @param destiny  String
     */
    public void dispatchClient(HttpServletRequest request, HttpServletResponse response, String destiny) {
        setRequestDispatcher(request.getRequestDispatcher(destiny));
        try {
            getRequestDispatcher().forward(request, response);
        } catch (ServletException | IOException ex) {
            Logger.getLogger(JWServlet.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }
    
    /**
     * Prints the desired page in the client (Web browser), given a response object.
     * @param response HttpServletResponse object.
     * @param pageName String.
     */
    public void print(HttpServletResponse response, String pageName) {
        try {
            response.setCharacterEncoding("UTF-8");
            PrintWriter pw = response.getWriter();
            pw.write(getPageMap().get(pageName).toString());
        } catch (IOException ex) {
            Logger.getLogger(JWServlet.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }
    
    /**
     * Prints the given page in the client (Web browser), given a response object.
     * @param response HttpServletResponse object.
     * @param page JWHTMLPage object.
     */
    public void print(HttpServletResponse response, JWHTMLPage page) {
        try {
            response.setCharacterEncoding("UTF-8");
            PrintWriter pw = response.getWriter();
            pw.write(page.toString());
        } catch (IOException ex) {
            Logger.getLogger(JWServlet.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }
    
    /**
     * Returns the given parameter associated with the parameter name.
     * @param paramName String, the parameter name.
     * @return Object
     */
    public Object getParam(String paramName) {
        return requestData.get(paramName);
    }
    
    /**
     * Returns an ArrayList of all parameters within requestData (The values).
     * @return ArrayList|Object|
     */
    public ArrayList<Object> getParams() {
        ArrayList<Object> params = new ArrayList();
        requestData.keySet().stream().forEach((key) -> {
            params.add(requestData.get(key));
        });
        return params;
    }

    /**
     * Returns the database driver.
     * @return String
     */
    public String getDbDriver() {
        return dbDriver;
    }

    /**
     * Sets the database driver.
     * @param dbDriver String
     */
    public void setDbDriver(String dbDriver) {
        this.dbDriver = dbDriver;
    }

    /**
     * Returns the database Uniform Resource Locator.
     * @return String
     */
    public String getDbURL() {
        return dbURL;
    }

    /**
     * Sets the database Uniform Resource Locator.
     * @param dbURL String
     */
    public void setDbURL(String dbURL) {
        this.dbURL = dbURL;
    }

    /**
     * Returns the username used to connect to the database.
     * @return String
     */
    public String getDbUser() {
        return dbUser;
    }

    /**
     * Sets the username used to connect to the database.
     * @param dbUser String
     */
    public void setDbUser(String dbUser) {
        this.dbUser = dbUser;
    }

    /**
     * Returns the password of the username used to connect to the database.
     * @return String
     */
    public String getDbPassword() {
        return dbPassword;
    }

    /**
     * Sets the password of the username used to connect to the database.
     * @param dbPassword String
     */
    public void setDbPassword(String dbPassword) {
        this.dbPassword = dbPassword;
    }

    /**
     * Returns the connection object.
     * @return Connection object.
     */
    public Connection getConnection() {
        return connection;
    }

    /**
     * Sets the connection object.
     * @param connection Connection object.
     */
    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    /**
     * Returns the HashMap with all the parameters extracted from the Request object as values.
     * @return HashMap|String, Object|
     */
    public HashMap<String, Object> getRequestData() {
        return requestData;
    }

    /**
     * Sets the HashMap with all the parameters extracted from the Request object as values.
     * @param requestData HashMap|String, Object|
     */
    public void setRequestData(HashMap<String, Object> requestData) {
        this.requestData = requestData;
    }

    /**
     * Returns the request dispatcher associated with the JWServlet.
     * @return RequestDispatcher
     */
    public RequestDispatcher getRequestDispatcher() {
        return requestDispatcher;
    }

    /**
     * Sets the request dispatcher associated with the JWServlet.
     * @param requestDispatcher RequestDispatcher
     */
    public void setRequestDispatcher(RequestDispatcher requestDispatcher) {
        this.requestDispatcher = requestDispatcher;
    }

    /**
     * Gets the page map associated with the JWServlet.
     * @return JWHTMLPageMap object.
     */
    public JWHTMLPageMap getPageMap() {
        return pageMap;
    }

    /**
     * Sets the page map associated with the JWServlet.
     * @param pageMap JWHTMLPageMap object.
     */
    public void setPageMap(JWHTMLPageMap pageMap) {
        this.pageMap = pageMap;
    }
    
}