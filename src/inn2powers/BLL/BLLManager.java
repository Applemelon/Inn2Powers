/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inn2powers.BLL;

import be.Company;
import be.Relation;
import inn2powers.DAL.DALException;
import inn2powers.DAL.DALManager;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author janvanzetten
 */
public class BLLManager
{

    DALManager DALM;

    public BLLManager() throws BLLException
    {
        try
        {
            this.DALM = new DALManager();
        }
        catch (IOException ex)
        {
            throw new BLLException(ex);
        }
    }

    public List<Company> getAllCompanies()
    {
        return DALM.getAllCompanies();
    }

    public String[] getBusinessRoles()
    {
        return DALM.getBusinessRoles();
    }

    public String[] getSupplyChainCategories()
    {
        return DALM.getSupplyChainCategories();
    }

    /**
     * returns all the companies with a given role, also cheks if it is a valid
     * role will write to the console and return null if not
     *
     * @param Role the role the companies should have as a String
     * @return a list of companies
     */
    public List<Company> getCompanysFromBusinessRole(String Role)
    {
        for (int i = 0; i < getBusinessRoles().length; i++)
        {
            if (getBusinessRoles()[i].equals(Role))
            {
                return getListofBusiniessesFromRole(Role);
            }

        }
        System.out.println("Not a known business Role");
        return new ArrayList<Company>();
    }

    /**
     * returns all the companies with a given role
     *
     * @param Role the role the companies should have as a String
     * @return a List of companies
     */
    private List<Company> getListofBusiniessesFromRole(String Role)
    {
        List<Company> allCompanies = DALM.getAllCompanies();
        List<Company> roleCompanies = new ArrayList<>();
        for (Company company : allCompanies)
        {
            if (company.getBuisnessRole().equals(Role))
            {
                roleCompanies.add(company);
            }
        }
        return roleCompanies;
    }

    /**
     * returns all the companies with a given supplyChainCategori, also cheks if
     * it is a valid supplyChainCategori will write to the console and return
     * null if not
     *
     * @param supplyChainCategori the supplyChainCategori the companies should
     * have as a String
     * @return a list of companies
     */
    public List<Company> getCompaniesFromCategories(String Category)
    {
        for (int i = 0; i < getSupplyChainCategories().length; i++)
        {
            if (getSupplyChainCategories()[i].equals(Category))
            {
                return getListOfCompaniesFromCategory(Category);
            }
        }
        return null;
    }

    /*
     * Return all companies with a given Supply Chain Categori
     *
     * @param Category
     * @return list of
     */
    private List<Company> getListOfCompaniesFromCategory(String Category)
    {
        List<Company> allCompanies = DALM.getAllCompanies();
        List<Company> categoryCompanies = new ArrayList<>();
        for (Company company : allCompanies)
        {
            if (company.getSupplyChainCategoriy().equals(Category))
            {
                categoryCompanies.add(company);
            }
        }
        return categoryCompanies;
    }

    public List<Relation> findRelationTo(Company startCompany, Company targetCompany) throws BLLException
    {
        try
        {
            ReleationshipLogicTest RLT = new ReleationshipLogicTest();
            return RLT.findRelationTo(startCompany, targetCompany);
        }
        catch (IOException ex)
        {
            throw new BLLException(ex);
        }
    }
}
