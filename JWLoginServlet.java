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

/**
 *
 * @author EricsonWillians
 * Developer at JWillians.
 */
public class JWLoginServlet extends JWServlet {
    
    private static final long serialVersionUID = 1L;
    private String username;
    private String password;
    
    /**
     * Database connection related parameters.
     * @param driver
     * Database driver.
     * @param dbURL
     * Database URL.
     * @param dbUser
     * Username used to connect to the database.
     * @param dbPassword
     * Password of the username used to connect to the database.
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public JWLoginServlet(String driver, String dbURL, String dbUser, String dbPassword) throws SQLException, ClassNotFoundException {
        super(driver, dbURL, dbUser, dbPassword);
    }
    
    /**
     * Is there such username in the database?
     * @return boolean
     * Returns true if the username associated with the JWLoginServlet object exists in the Database.
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
<<<<<<< HEAD
     *
     * @return
=======
     * Get username.
     * @return String
>>>>>>> master
     * Returns the username associated with the JWLoginServlet object (Used in the web app).
     */
    public String getUsername() {
        return username;
    }

    /**
     * Set username.
     * @param username
     * Sets the username associated with the JWLoginServlet object (Used in the web app).
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
<<<<<<< HEAD
     *
     * @return
=======
     * Get password.
     * @return String
>>>>>>> master
     * Returns the username's password associated with the JWLoginServlet object (Used in the web app).
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set password.
     * @param password
     * Sets the username's password associated with the JWLoginServlet object (Used in the web app).
     */
    public void setPassword(String password) {
        this.password = password;
    }
    
}
