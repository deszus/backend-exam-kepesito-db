package hu.nive.ujratervezes.jurassic;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JurassicPark {

    private String dbUrl;
    private String dbUser;
    private String dbPassword;

    public JurassicPark(String dbUrl, String dbUser, String dbPassword) {
        this.dbUrl = dbUrl;
        this.dbUser = dbUser;
        this.dbPassword = dbPassword;
    }

    public List<String> checkOverpopulation() {
        List<String> result = new ArrayList<>();
        try (Connection DBcon = DriverManager.getConnection(dbUrl, dbUser, dbPassword)) {
            String sql = "SELECT * FROM dinosaur WHERE expected < actual ORDER BY breed";
            PreparedStatement st = DBcon.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                result.add(rs.getString("breed"));
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return result;
    }
}
