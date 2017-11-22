/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inn2powers.DAL;

import be.Company;
import be.Relation;
import dal.CompanyDAO;
import dal.RelationDAO;
import java.io.IOException;
import java.sql.SQLException;
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
    CompanyReader CReader;
    DatabaseAcces DBACS;
    
    List<Company> allCompanies;

    public DALManager() throws IOException
    {
        this.CDAO = new CompanyDAO();
        //this.RDAO = new RelationDAO();
        //CReader = new CompanyReader();
        DBACS = new DatabaseAcces();
        //allCompanies = CReader.getAllCompanies();//the CSV reader
        //allCompanies = CDAO.getAllCompanies();
    }

    /**
     * return all the companies in the database
     *
     * @return a list of companies
     * @throws inn2powers.DAL.DALException
     */
    public List<Company> getAllCompanies() throws DALException
    {
        //return CDAO.getAllCompanies();
        try {
            //return allCompanies;
            return DBACS.getAllCompaniesFromDatabase();
        } catch (SQLException ex) {
            throw new DALException(ex.getMessage());
        }
        
    }

    /**
     * Get a company by its ID
     *
     * @param id the id as an int
     * @return
     * @throws inn2powers.DAL.DALException
     */
    public Company getCompany(int id) throws DALException
    {
        try {
            return DBACS.getCompanybyId(id);
        } catch (SQLException ex) {
            throw new DALException(ex.getMessage());
        }
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
     * @throws inn2powers.DAL.DALException
     */
    public List<Relation> getAllRelations() throws DALException
    {
        try {
            return DBACS.getAllRelationsFromDatabase();
        } catch (SQLException ex) {
            throw new DALException(ex.getMessage());
        }
        //return RDAO.getAllRelations();
    }
    
    /**
     * adds a company
     * @param ID this has to be unique
     * @param name
     * @param address 
     * @param country
     * @param website
     * @param supplyChainCat
     * @param businessRole
     * @param lat
     * @param lng
     * @param isSME
     * @return returns the made company with the auto generated ID
     * @throws inn2powers.DAL.DALException
     */
    
    public Company addCompany(int ID,String name, String address, String country, String website, String supplyChainCat, String businessRole, double lat, double lng, int isSME) throws DALException{
        try {
            return DBACS.addCompany(ID,name, address, country, website, supplyChainCat, businessRole, lat, lng, isSME); //ADD NEW DAL EXEPTION
        } catch (SQLException ex) {
            throw new DALException(ex.getMessage());
        }
        
    }
    /**
     * add a relation
     * @param sourceCompany
     * @param targetCompany
     * @param type
     * @param Strength
     * @return the relation as an object
     * @throws inn2powers.DAL.DALException
     */
    public Relation addRelation(Company sourceCompany, Company targetCompany,String type,  String Strength) throws DALException{
        try {
            return DBACS.addRelation(sourceCompany, targetCompany, type, Strength);
        } catch (SQLException ex) {
            throw new DALException(ex.getMessage());
        }
    }
    
    /**
     * removes the company with the given id
     * @param id the id of the company
     * @return true if succesful remove else false
     * @throws inn2powers.DAL.DALException
     */
    public boolean removeCompany(int id) throws DALException{
        
        try {
            return DBACS.removeCompanybyID(id);
        } catch (SQLException ex) {
            throw new DALException(ex.getMessage());
        }
        
    }
}
