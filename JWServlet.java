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

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.RequestDispatcher;
import java.sql.*;
import java.util.HashMap;
import java.util.ArrayList;

/**
 *
 * @author EricsonWillians
 * JWServlet HttpServlet object.
 */
public class JWServlet extends HttpServlet {
    
    private static final long serialVersionUID = 1L;
    private String dbDriver;
    private String dbURL;
    private String dbUser;
    private String dbPassword;
    private Connection connection;
    private HashMap<String, Object> requestData;
    private RequestDispatcher requestDispatcher;
    
    /**
     *
     * @param driver
     * @param dbURL
     * @param dbUser
     * @param dbPassword
     * @throws SQLException
     * @throws ClassNotFoundException
     * Database connection related constructor parameters.
     */
    public JWServlet(String driver, String dbURL, String dbUser, String dbPassword) throws SQLException, ClassNotFoundException {
        this.dbDriver = driver;
        this.dbURL = dbURL;
        this.dbUser = dbUser;
        this.dbPassword = dbPassword;
        Class.forName(this.dbDriver);
        this.connection = DriverManager.getConnection(this.dbURL, this.dbUser, this.dbPassword);
    }
    
    /**
     *
     * @param request
     * @param paramNames
     * Fetches all the parameters with the given parameter names, storing them inside the JWServlet inner HashMap (requestData).
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
     *
     * @param paramName
     * @return Object
     * Returns the given parameter associated with the parameter name.
     */
    public Object getParam(String paramName) {
        return requestData.get(paramName);
    }
    
    /**
     *
     * @return ArrayList<Object>
     * Returns all parameters.
     */
    public ArrayList<Object> getParams() {
        ArrayList<Object> params = new ArrayList();
        requestData.keySet().stream().forEach((key) -> {
            params.add(requestData.get(key));
        });
        return params;
    }

    /**
     *
     * @return String
     * Returns the Database Driver.
     */
    public String getDbDriver() {
        return dbDriver;
    }

    /**
     *
     * @param dbDriver
     * Sets the Database Driver.
     */
    public void setDbDriver(String dbDriver) {
        this.dbDriver = dbDriver;
    }

    /**
     *
     * @return String
     * Returns the Database Uniform Resource Locator.
     */
    public String getDbURL() {
        return dbURL;
    }

    /**
     *
     * @param dbURL
     * Sets the Database Uniform Resource Locator.
     */
    public void setDbURL(String dbURL) {
        this.dbURL = dbURL;
    }

    /**
     *
     * @return String
     * Returns the username used to connect to the database.
     */
    public String getDbUser() {
        return dbUser;
    }

    /**
     *
     * @param dbUser
     * Sets the username used to connect to the database.
     */
    public void setDbUser(String dbUser) {
        this.dbUser = dbUser;
    }

    /**
     *
     * @return String
     * Returns the password of the username used to connect to the database.
     */
    public String getDbPassword() {
        return dbPassword;
    }

    /**
     *
     * @param dbPassword
     * Sets the password of the username used to connect to the database.
     */
    public void setDbPassword(String dbPassword) {
        this.dbPassword = dbPassword;
    }

    /**
     *
     * @return Connection
     * Returns the connection object.
     */
    public Connection getConnection() {
        return connection;
    }

    /**
     *
     * @param connection
     * Sets the connection object.
     */
    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    /**
     *
     * @return HashMap<String, Object>
     * Returns the HashMap with all the parameters extracted from the Request object as values.
     */
    public HashMap<String, Object> getRequestData() {
        return requestData;
    }

    /**
     *
     * @param requestData
     * Sets the HashMap with all the parameters extracted from the Request object as values.
     */
    public void setRequestData(HashMap<String, Object> requestData) {
        this.requestData = requestData;
    }

    /**
     *
     * @return RequestDispatcher
     * Returns the request dispatcher associated with the JWServlet.
     */
    public RequestDispatcher getRequestDispatcher() {
        return requestDispatcher;
    }

    /**
     *
     * @param requestDispatcher
     * Sets the request dispatcher associated with the JWServlet.
     */
    public void setRequestDispatcher(RequestDispatcher requestDispatcher) {
        this.requestDispatcher = requestDispatcher;
    }
    
}