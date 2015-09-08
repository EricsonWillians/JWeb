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

package JWeb;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.RequestDispatcher;
import java.sql.*;
import java.util.HashMap;
import java.util.ArrayList;

public class JWServlet extends HttpServlet {
    
    private static final long serialVersionUID = 1L;
    private String dbDriver;
    private String dbURL;
    private String dbUser;
    private String dbPassword;
    private Connection connection;
    private HashMap<String, Object> requestData;
    private RequestDispatcher requestDispatcher;
    
    public JWServlet(String driver, String dbURL, String dbUser, String dbPassword) throws SQLException, ClassNotFoundException {
        this.dbDriver = driver;
        this.dbURL = dbURL;
        this.dbUser = dbUser;
        this.dbPassword = dbPassword;
        Class.forName(this.dbDriver);
        this.connection = DriverManager.getConnection(this.dbURL, this.dbUser, this.dbPassword);
    }
    
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
    
    public Object getParam(String paramName) {
        return requestData.get(paramName);
    }
    
    public ArrayList<Object> getParams() {
        ArrayList<Object> params = new ArrayList();
        requestData.keySet().stream().forEach((key) -> {
            params.add(requestData.get(key));
        });
        return params;
    }

    public String getDbDriver() {
        return dbDriver;
    }

    public void setDbDriver(String dbDriver) {
        this.dbDriver = dbDriver;
    }

    public String getDbURL() {
        return dbURL;
    }

    public void setDbURL(String dbURL) {
        this.dbURL = dbURL;
    }

    public String getDbUser() {
        return dbUser;
    }

    public void setDbUser(String dbUser) {
        this.dbUser = dbUser;
    }

    public String getDbPassword() {
        return dbPassword;
    }

    public void setDbPassword(String dbPassword) {
        this.dbPassword = dbPassword;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public HashMap<String, Object> getRequestData() {
        return requestData;
    }

    public void setRequestData(HashMap<String, Object> requestData) {
        this.requestData = requestData;
    }

    public RequestDispatcher getRequestDispatcher() {
        return requestDispatcher;
    }

    public void setRequestDispatcher(RequestDispatcher requestDispatcher) {
        this.requestDispatcher = requestDispatcher;
    }
    
}