/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inn2powers.dal;

import be.Company;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author janvanzetten
 */
public class AddALLCompaniesToDatabase {

    public AddALLCompaniesToDatabase() throws IOException, SQLException {
        DALManager dal = new DALManager();
        List<Company> companies = dal.getAllCompanies();
        addCompanies(companies);
    }

    private void addCompanies(List<Company> companies) throws IOException, SQLException {
        DatabaseAcces DA = new DatabaseAcces();
        for (int i = 1; i< companies.size(); i++) {
            
            Company company = companies.get(i);
            
            
            DA.addCompany(company.getId(),company.getName(), company.getAddress(), company.getCountry(), company.getWebsite(), company.getSupplyChainCategoriy(), company.getBuisnessRole(), company.getLat(), company.getLng(), company.getIsSME());
        }
    }
    
    
    
}
