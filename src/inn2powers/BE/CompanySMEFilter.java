/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inn2powers.BE;

import be.Company;

/**
 *
 * @author Asbamz
 */
public class CompanySMEFilter implements ICompanyFilter
{

    private final int sme;

    /**
     * Company SME filter.
     *
     * @param sme to filter.
     */
    public CompanySMEFilter(int sme)
    {
        this.sme = sme;
    }

    /**
     * Criteria for company is that it is the SME of the filter.
     *
     * @param company Company.
     * @return true if same SME as filter.
     */
    @Override
    public boolean meetsCriteria(Company company)
    {
        return company.getIsSME() == sme;
    }

}
