/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inn2powers.GUI;

import be.Company;
import be.Relation;
import inn2powers.BLL.Filter;
import inn2powers.BLL.SearchCompany;
import inn2powers.BLL.BLLManager;
import java.util.List;

/**
 *
 * @author janvanzetten
 */
public class MainWindowModel
{

    SearchCompany searchCompany;
    BLLManager bm;
    Filter filter;

    /**
     * Find the company of given name.
     *
     * @param name of company
     * @return company if found, else null.
     */
    public Company findCompany(String name) throws Exception
    {
        return searchCompany.findCompany(name);
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
        return searchCompany.findRelation(c1, c2);
    }

    /**
     * Find all relation paths for a company.
     *
     * @param c Company.
     * @return A list of Relation lists which represents paths.
     */
    public List<List<Relation>> findRelations(Company c)
    {
        return searchCompany.findRelations(c);
    }

    /**
     * Sorts out lists larger than depth.
     *
     * @param c Company
     * @param depth Larger than 0.
     * @return All Companies in depth.
     */
    public List<Company> findRelationsByDepth(Company c, int depth)
    {
        return filter.byDepth(findRelations(c), depth);
    }

    /**
     * Finds companies with given industry.
     *
     * @param c Company.
     * @param r Role.
     * @return All companies, somehow connected, with given industry.
     */
    public List<Company> findCompaniesByRole(Company c, String r)
    {
        return bm.getCompanysFromBusinessRole(r);
    }
}
