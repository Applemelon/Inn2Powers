/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inn2powers.BLL;

import be.Company;
import inn2powers.BE.Industry;
import inn2powers.DAL.DALManager;
import java.util.List;

/**
 *
 * @author Asbamz
 */
public class SearchIndustry
{

    private DALManager dm;

    /**
     * Finds companies with given industry.
     *
     * @param c Company.
     * @param i Industry.
     * @return All companies, somehow connected, with given industry.
     */
    public List<Company> FindCompanies(Company c, Industry i)
    {
        return null;
    }
}
