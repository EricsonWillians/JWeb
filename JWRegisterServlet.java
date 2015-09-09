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
 * @author EricsonWillians.
 * Developer at JWillians.
 */
public class JWRegisterServlet extends JWServlet {
 
    private static final long serialVersionUID = 1L;
    
    /**
     * Database connection related parameters.
     * @param driver String, Database driver.
     * @param dbURL String, Database URL.
     * @param dbUser String, Username used to connect to the database.
     * @param dbPassword String, Password of the username used to connect to the database.
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public JWRegisterServlet(String driver, String dbURL, String dbUser, String dbPassword) throws SQLException, ClassNotFoundException {
        super(driver, dbURL, dbUser, dbPassword);
    }
    
    /**
     * Returns true if the given username or email exists in the database.
     * @param username String
     * @param email String, email associated with the given username.
     * @return boolean
     * @throws SQLException
     */
    public boolean hasRecord(String username, String email) throws SQLException {
        String sql = "SELECT username, email FROM users WHERE username = ? OR email = ?";
        boolean result = false;
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setString(1, username);
            statement.setString(2, email);
            ResultSet rs = statement.executeQuery();
            result = rs.next();
        } catch (SQLException ex) {
            Logger.getLogger(JWRegisterServlet.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        return result;
    }
    
}
