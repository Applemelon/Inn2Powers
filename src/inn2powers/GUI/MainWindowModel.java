/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inn2powers.GUI;

import be.Company;
import be.Relation;
import inn2powers.BE.Industry;
import inn2powers.BLL.Filter;
import inn2powers.BLL.SearchCompany;
import inn2powers.BLL.SearchIndustry;
import java.util.List;

/**
 *
 * @author janvanzetten
 */
public class MainWindowModel
{

    SearchCompany searchCompany;
    SearchIndustry searchIndustry;
    Filter filter;

    /**
     * Find the company of given name.
     *
     * @param name of company
     * @return company if found, else null.
     */
    public Company findCompany(String name)
    {
        return searchCompany.findCompany(name);
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
     * @param i Industry.
     * @return All companies, somehow connected, with given industry.
     */
    public List<Company> findRelationsByIndustry(Company c, Industry i)
    {
        return searchIndustry.findCompanies(c, i);
    }
}
