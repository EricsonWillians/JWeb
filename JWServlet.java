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
 * Developer at JWillians.
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
     * Database connection related parameters.
     * @param driver
     * Database driver.
     * @param dbURL
     * Database URL.
     * @param dbUser
     * Username used to connect to the database.
     * @param dbPassword
<<<<<<< HEAD
     * Password of the username used to the connect to the database.
=======
     * Password of the username used to connect to the database.
>>>>>>> master
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
    }
    
    /**
     * Fetches all the parameters with the given parameter names, storing them inside the JWServlet inner HashMap (requestData).
     * @param request
     * HttpServletRequest object.
<<<<<<< HEAD
     * @param paramNames
     * Array of String objects (The name of each parameter).
=======
     * @param paramNames 
     * Array of String objects (Parameter names).
>>>>>>> master
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
     * Get parameter.
     * @param paramName
<<<<<<< HEAD
     * @return 
=======
     * The parameter name.
     * @return Object
>>>>>>> master
     * Returns the given parameter associated with the parameter name.
     */
    public Object getParam(String paramName) {
        return requestData.get(paramName);
    }
    
    /**
<<<<<<< HEAD
     *
     * @return
     * Returns all parameters.
=======
     * Get parameters.
     * @return ArrayList|Object|.
     * Returns an ArrayList of all parameters within requestData (The values).
>>>>>>> master
     */
    public ArrayList<Object> getParams() {
        ArrayList<Object> params = new ArrayList();
        requestData.keySet().stream().forEach((key) -> {
            params.add(requestData.get(key));
        });
        return params;
    }

    /**
<<<<<<< HEAD
     *
     * @return
     * Returns the Database Driver.
=======
     * Get database driver.
     * @return String
     * Returns the database driver.
>>>>>>> master
     */
    public String getDbDriver() {
        return dbDriver;
    }

    /**
     * Set database driver.
     * @param dbDriver
     * Sets the database driver.
     */
    public void setDbDriver(String dbDriver) {
        this.dbDriver = dbDriver;
    }

    /**
<<<<<<< HEAD
     *
     * @return 
     * Returns the Database Uniform Resource Locator.
=======
     * Get database URL.
     * @return String
     * Returns the database Uniform Resource Locator.
>>>>>>> master
     */
    public String getDbURL() {
        return dbURL;
    }

    /**
     * Set database URL.
     * @param dbURL
     * Sets the database Uniform Resource Locator.
     */
    public void setDbURL(String dbURL) {
        this.dbURL = dbURL;
    }

    /**
<<<<<<< HEAD
     *
     * @return 
=======
     * Get database username.
     * @return String
>>>>>>> master
     * Returns the username used to connect to the database.
     */
    public String getDbUser() {
        return dbUser;
    }

    /**
     * Set database username.
     * @param dbUser
     * Sets the username used to connect to the database.
     */
    public void setDbUser(String dbUser) {
        this.dbUser = dbUser;
    }

    /**
     *
<<<<<<< HEAD
     * @return 
=======
     * @return
>>>>>>> master
     * Returns the password of the username used to connect to the database.
     */
    public String getDbPassword() {
        return dbPassword;
    }

    /**
     * Database password.
     * @param dbPassword
     * Sets the password of the username used to connect to the database.
     */
    public void setDbPassword(String dbPassword) {
        this.dbPassword = dbPassword;
    }

    /**
<<<<<<< HEAD
     *
     * @return
=======
     * Get connection.
     * @return Connection
>>>>>>> master
     * Returns the connection object.
     */
    public Connection getConnection() {
        return connection;
    }

    /**
     * Set connection.
     * @param connection
     * Sets the connection object.
     */
    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    /**
<<<<<<< HEAD
     *
     * @return 
=======
     * Get request data.
     * @return HashMap|String, Object|
>>>>>>> master
     * Returns the HashMap with all the parameters extracted from the Request object as values.
     */
    public HashMap<String, Object> getRequestData() {
        return requestData;
    }

    /**
     * Set request data.
     * @param requestData
     * Sets the HashMap with all the parameters extracted from the Request object as values.
     */
    public void setRequestData(HashMap<String, Object> requestData) {
        this.requestData = requestData;
    }

    /**
<<<<<<< HEAD
     *
     * @return 
=======
     * Get request dispatcher.
     * @return RequestDispatcher
>>>>>>> master
     * Returns the request dispatcher associated with the JWServlet.
     */
    public RequestDispatcher getRequestDispatcher() {
        return requestDispatcher;
    }

    /**
     * Set request dispatcher.
     * @param requestDispatcher
     * Sets the request dispatcher associated with the JWServlet.
     */
    public void setRequestDispatcher(RequestDispatcher requestDispatcher) {
        this.requestDispatcher = requestDispatcher;
    }
    
}