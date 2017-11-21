/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inn2powers.DAL;

import be.Company;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
    
    public List<Company> getAllCompaniesFromDatabase() throws SQLServerException, SQLException{
        try (Connection con = dbConnector.getConnection()) {
            String sql = "SELECT * FROM Company";
            
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            List<Company> allCompanies = new ArrayList<>(); // I Prepare a list for holding my returned companies
            while (rs.next()) //While there are companies (rows) in the result set:
            {
                Company company = getCompanyFromResultSetRow(rs);
                allCompanies.add(company);
            }
            //I return all the found companies:
            return allCompanies;
        }
    }

    private Company getCompanyFromResultSetRow(ResultSet rs) throws SQLException {
        //I extract the data from the current row in the resultset:
        int id = rs.getInt("Id");
        String name = rs.getString("Name");
        String country = rs.getString("Country");
        String address = rs.getString("Address");
        String supply = rs.getString("SupplyChainCat");
        String business = rs.getString("BusinessRole");
        double lat = rs.getDouble("Lat");
        double lng = rs.getDouble("Lng");
        int isSME = rs.getInt("IsSME");
        //I create the company object and add it to my list of results:
        Company company = new Company(id, name, country, address, business, supply, business, lat, lng, isSME);
        return company;
    }

}
