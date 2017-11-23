/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inn2powers.be;

import be.Company;

/**
 *
 * @author Asbamz
 */
public class CompanyCountryFilter implements ICompanyFilter
{

    private final String country;

    /**
     * Company country filter.
     *
     * @param country to filter.
     */
    public CompanyCountryFilter(String country)
    {
        this.country = country;
    }

    /**
     * Criteria for company is that it is in the filter country.
     *
     * @param company Company.
     * @return true if same country as filter.
     */
    @Override
    public boolean meetsCriteria(Company company)
    {
        return company.getCountry().equalsIgnoreCase(country);
    }

}
