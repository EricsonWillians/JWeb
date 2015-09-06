package JWeb;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JWRegisterServlet extends JWServlet {
 
    private static final long serialVersionUID = 1L;
    
    public JWRegisterServlet(String driver, String dbURL, String dbUser, String dbPassword) throws SQLException, ClassNotFoundException {
        super(driver, dbURL, dbUser, dbPassword);
    }
    
    public boolean hasRecord(String username, String email) throws SQLException {
        String sql = "SELECT username, email FROM users WHERE username = ? OR email = ?";
        boolean result = false;
        try (PreparedStatement statement = this.connection.prepareStatement(sql)) {
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
