/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inn2powers.BLL;

import be.Company;
import be.Relation;

import inn2powers.DAL.DALManager;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Asbamz
 */
public class SearchCompany
{

    private DALManager dm;

    public SearchCompany() throws IOException
    {
        this.dm = new DALManager();
    }

    /**
     * Find the company of given name.
     *
     * @param name of company
     * @return company if found, else null.
     */
    public Company findCompany(String name) throws Exception
    {
        List<Company> companies = dm.getAllCompanies();
        for (Company company : companies)
        {
            if (company.getName().equalsIgnoreCase(name))
            {
                return company;
            }
        }

        throw new Exception("Company " + name + " not found!");
    }

    /**
     * Get relation path between two companies.
     *
     * @param c1 source company.
     * @param c2 target company.
     * @return List of relations.
     */
    public List<Relation> findRelation(Company c1, Company c2)
    {
        List<Relation> relations = dm.getAllRelations();
        List<Relation> results = new ArrayList<>();

        for (Relation relation : relations)
        {
            if (relation.getSource() == c1)
            {
                if (relation.getTarget() == c2)
                {
                    results.add(relation);
                }
            }
        }

        return results;
    }

    /**
     * Find all relation paths for a company.
     *
     * @param c Company.
     * @return A list of Relation lists which represents paths.
     */
    public List<List<Relation>> findRelations(Company c)
    {
        List<Relation> relations = dm.getAllRelations();
        List<List<Relation>> results = new ArrayList<>();

        for (Relation relation : relations)
        {
            if (relation.getSource() == c)
            {
                results.addAll(findRelations(relation.getTarget()));
            }
        }

        return results;
    }
}
