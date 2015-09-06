package JWeb;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.sql.*;
import java.util.HashMap;
import java.util.ArrayList;

public class JWServlet extends HttpServlet {
    
    private static final long serialVersionUID = 1L;
    protected String dbDriver;
    protected String dbURL;
    protected String dbUser;
    protected String dbPassword;
    protected Connection connection;
    protected HashMap<String, Object> requestData;
    
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
        Integer gender = null;
        HashMap<String, Object> hashMap = new HashMap();
        for (String s : paramNames) {
            hashMap.put(s, request.getParameter(s));
        }
        hashMap.put("timestamp", timestamp);
        this.requestData = hashMap;
    }
    
    public Object getParam(String paramName) {
        return this.requestData.get(paramName);
    }
    
    public ArrayList<Object> getParams() {
        ArrayList<Object> params = new ArrayList();
        this.requestData.keySet().stream().forEach((key) -> {
            params.add(this.requestData.get(key));
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

    public HashMap<String, Object> getRequestData() {
        return requestData;
    }

    public void setRequestData(HashMap<String, Object> requestData) {
        this.requestData = requestData;
    }
    
}