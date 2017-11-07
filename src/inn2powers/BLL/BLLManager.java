/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inn2powers.BLL;

import be.Company;
import inn2powers.DAL.DALManager;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 *
 * @author janvanzetten
 */
public class BLLManager {
    DALManager DALM;
    
    public BLLManager() throws IOException {
        this.DALM = new DALManager();
    }
    
    public String[] getBusinessRoles() {
        return DALM.getBusinessRoles();
    }
    
    public String[] getSupplyChainCategories (){
        return DALM.getSupplyChainCategories();
    }
    
    /**
     * returns all the companies with a given role, also cheks if it is a valid role will write to the console if not
     * @param Role the role the companies should have as a String
     */
    public void getCompanysFromBusinessRole(String Role){
        for (int i = 0; i < getBusinessRoles().length; i++) {
            if (getBusinessRoles()[i].equals(Role)){
                getListofBusiniessesFromRole(Role);
                return;
            }
            
        }
        System.out.println("Not a known business Role");
    }

    /**
     * returns all the companies with a given role
     * @param Role the role the companies should have as a String
     * @return 
     */
    private List<Company> getListofBusiniessesFromRole(String Role) {
        List<Company>  allCompanies = DALM.getAllCompanies();
        List<Company> roleCompanies = new ArrayList<>();
           for (Company company : allCompanies) {
            if (company.getBuisnessRole().equals(Role)){
                roleCompanies.add(company);
            }
        }
           return roleCompanies;
    }
}
