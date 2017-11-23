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
public interface ICompanyFilter
{

    /**
     * Boolean method to filter out companies.
     *
     * @param company
     * @return true if criteria is met.
     */
    public boolean meetsCriteria(Company company);
}
