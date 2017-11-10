/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inn2powers.DAL;

import be.Company;
import be.Relation;
import bll.Inn2PowerException;
import dal.CompanyDAO;
import dal.RelationDAO;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author janvanzetten
 */
public class DALManager
{

    // Company Data Access Object
    CompanyDAO CDAO;
    // Relation Data Access Object
    RelationDAO RDAO;

    public DALManager() throws IOException
    {
        this.CDAO = new CompanyDAO();
        this.RDAO = new RelationDAO();
    }

    /**
     * return all the companies in the database
     *
     * @return a list of companies
     */
    public List<Company> getAllCompanies()
    {
        return CDAO.getAllCompanies();
    }

    /**
     * Get a company by its ID
     *
     * @param id the id as an int
     * @return
     * @throws Inn2PowerException
     */
    public Company getCompany(int id) throws Inn2PowerException
    {
        return CDAO.getCompanyById(id);
    }

    /**
     * get all the buisness roles known
     *
     * @return String array with the diffrent roles
     */
    public String[] getBusinessRoles()
    {
        return CDAO.getCompanyBuisnessRoles();
    }

    /**
     * get all the supply chain catagories (a lot)
     *
     * @return String array withe the catagories
     */
    public String[] getSupplyChainCategories()
    {
        return CDAO.getSupplyChainCategories();
    }

    /**
     * get all relations objects
     *
     * @return a list with the objects
     */
    public List<Relation> getAllRelations()
    {
        return RDAO.getAllRelations();
    }
}
