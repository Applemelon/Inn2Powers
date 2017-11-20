/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inn2powers.DAL;

import be.Company;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author janvanzetten
 */
public class AddALLCompaniesToDatabase {

    public AddALLCompaniesToDatabase() throws IOException {
        DALManager dal = new DALManager();
        List<Company> companies = dal.getAllCompanies();
        addCompanies(companies);
    }

    private void addCompanies(List<Company> companies) {
        DatabaseAcces DA = new DatabaseAcces();
        for (Company company : companies) {
            DA.addCompany(company);
        }
    }
    
    
    
}
