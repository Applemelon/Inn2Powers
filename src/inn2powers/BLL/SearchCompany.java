/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inn2powers.BLL;

import be.Company;
import be.Relation;

import inn2powers.DAL.DALManager;
import java.util.List;

/**
 *
 * @author Asbamz
 */
public class SearchCompany
{

    private DALManager dm;

    /**
     * Find the company of given name.
     *
     * @param name of company
     * @return company if found, else null.
     */
    public Company findCompany(String name)
    {
        return null;
    }

    /**
     * Get relation path between two companies.
     *
     * @param c1 root company.
     * @param c2 other company.
     * @return List of relations.
     */
    public List<Relation> findRelation(Company c1, Company c2)
    {
        return null;
    }

    /**
     * Find all relation paths for a company.
     *
     * @param c Company.
     * @return A list of Relation lists which represents paths.
     */
    public List<List<Relation>> findRelations(Company c)
    {
        return null;
    }
}
