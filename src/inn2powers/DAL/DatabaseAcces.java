/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inn2powers.DAL;

import be.Company;
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

    public void addCompany(String name, String address, String country, String website, String supplyChainCat, String businessRole, double lat, double lng, int isSME) throws SQLException {
         try (Connection con = dbConnector.getConnection())
        {
            String sql = "INSERT INTO Company VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";

            PreparedStatement statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, company.getName());
            statement.setString(2, company.getCountry());
            statement.setString(3, company.getAddress());
            statement.setString(4, company.getWebsite());
            statement.setString(5, company.getSupplyChainCategoriy());
            statement.setString(6, company.getBuisnessRole());
            statement.setDouble(7, company.getLat());
            statement.setDouble(8, company.getLng());
            statement.setInt(9, company.getIsSME());

            if (statement.executeUpdate() == 1)
            {
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
    
    
}
