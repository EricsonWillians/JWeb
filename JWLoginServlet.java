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

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JWLoginServlet extends JWServlet {
    
    private static final long serialVersionUID = 1L;
    protected String username;
    protected String password;
    
    public JWLoginServlet(String driver, String dbURL, String dbUser, String dbPassword) throws SQLException, ClassNotFoundException {
        super(driver, dbURL, dbUser, dbPassword);
    }
    
    public boolean isThereSuchUsername() throws SQLException {
        String sql = "SELECT username FROM users WHERE username = ?";
        boolean result;
        try (PreparedStatement statement = this.connection.prepareStatement(sql)) {
            statement.setString(1, this.username);
            ResultSet rs = statement.executeQuery();
            result = rs.next();
        }
        return result;
    }
    
}
