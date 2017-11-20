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
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    CompanyReader CReader;
    DatabaseAcces DBACS;
    
    List<Company> allCompanies;

    public DALManager() throws IOException
    {
        this.CDAO = new CompanyDAO();
        this.RDAO = new RelationDAO();
        CReader = new CompanyReader();
        DBACS = new DatabaseAcces();
        //allCompanies = CReader.getAllCompanies();
        allCompanies = CDAO.getAllCompanies();
    }

    /**
     * return all the companies in the database
     *
     * @return a list of companies
     */
    public List<Company> getAllCompanies()
    {
        return allCompanies;
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
        return CDAO.getCompanyBusinessRoles();
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
    
    public void addCompany(String name, String address, String country, String website, String supplyChainCat, String businessRole, double lat, double lng, int isSME){
        try {
            DBACS.addCompany(name, address, country, website, supplyChainCat, businessRole, lat, lng, isSME);
        } catch (SQLException ex) {
            //ADD NEW DAL EXEPTION
        }
    }
    
    public void addRelation(Relation relation){
        //TODO add implementation
    }
    
    
}
