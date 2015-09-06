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
