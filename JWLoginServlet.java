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

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author EricsonWillians
 * Developer at JWillians.
 */
public class JWLoginServlet extends JWServlet {
    
    private static final long serialVersionUID = 1L;
    private String username;
    private String password;
    
    /**
     * Database connection related parameters.
     * @param driver String, Database driver.
     * @param dbURL String, Database URL.
     * @param dbUser String, Username used to connect to the database.
     * @param dbPassword String, Password of the username used to connect to the database.
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public JWLoginServlet(String driver, String dbURL, String dbUser, String dbPassword) throws SQLException, ClassNotFoundException {
        super(driver, dbURL, dbUser, dbPassword);
    }
    
    /**
     * Returns true if the username associated with the JWLoginServlet object exists in the Database.
     * @return boolean
     * @throws SQLException
     */
    public boolean isThereSuchUsername() throws SQLException {
        String sql = "SELECT username FROM users WHERE username = ?";
        boolean result = false;
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setString(1, username);
            ResultSet rs = statement.executeQuery();
            result = rs.next();
        } catch (SQLException ex) {
            Logger.getLogger(JWLoginServlet.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        return result;
    }

    /**
     * Sets the given attribute in the request's session.
     * @param request HttpServletRequest object.
     * @param key String.
     * @param value Object.
     */
    public void setSessionAttribute(HttpServletRequest request, String key, Object value) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.setAttribute(key, value);
        }
    }
    
    /**
     * Returns the username associated with the JWLoginServlet object (Used in the web app).
     * @return String
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username associated with the JWLoginServlet object (Used in the web app).
     * @param username String
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Returns the username's password associated with the JWLoginServlet object (Used in the web app).
     * @return String
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the username's password associated with the JWLoginServlet object (Used in the web app).
     * @param password String
     */
    public void setPassword(String password) {
        this.password = password;
    }
    
}
