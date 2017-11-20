/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inn2powers.DAL;

import be.Company;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author janvanzetten
 */
public class DatabaseAcces {

    DataBaseConector dbConnector;

    public DatabaseAcces() throws IOException {
        this.dbConnector = new DataBaseConector();
    }

    public Company addCompany(int ID, String name, String address, String country, String website, String supplyChainCat, String businessRole, double lat, double lng, int isSME) throws SQLException {
        try (Connection con = dbConnector.getConnection()) {
            String sql = "INSERT INTO Company1 VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

            PreparedStatement statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            statement.setInt(1, ID);
            statement.setString(2, name);
            statement.setString(3, country);
            statement.setString(4, address);
            statement.setString(5, website);
            statement.setString(6, supplyChainCat);
            statement.setString(7, businessRole);
            statement.setDouble(8, lat);
            statement.setDouble(9, lng);
            statement.setInt(10, isSME);

            if (statement.executeUpdate() == 1) {
                //Good
                ResultSet rs = statement.getGeneratedKeys();
                rs.next();
                int id = rs.getInt(1);
                Company c = new Company(id, name, country, address, website, supplyChainCat, businessRole, lat, lng, isSME);
                return c;
            }
            throw new RuntimeException("Can't create company");
        }
    }

}
