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
public class CompanyDistanceFilter implements ICompanyFilter
{

    private final double latitude;
    private final double longitude;
    private final double range;

    /**
     * Company distance filter.
     *
     * @param latitude source latitude.
     * @param longitude source longtitude.
     * @param range max range between source and target.
     */
    public CompanyDistanceFilter(double latitude, double longitude, double range)
    {
        this.latitude = latitude;
        this.longitude = longitude;
        this.range = range;
    }

    /**
     * Criteria for company is that it is within range of source coordinates.
     *
     * @param company Company.
     * @return true if the company is within range.
     */
    @Override
    public boolean meetsCriteria(Company company)
    {
        double R = 6371e3; // metres
        double φ1 = Math.toRadians(latitude);
        double φ2 = Math.toRadians(company.getLat());
        double Δφ = Math.toRadians(company.getLat() - latitude);
        double Δλ = Math.toRadians(company.getLng() - longitude);

        double a = Math.sin(Δφ / 2) * Math.sin(Δφ / 2) + Math.cos(φ1) * Math.cos(φ2) * Math.sin(Δλ / 2) * Math.sin(Δλ / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        double d = R * c;

        return d <= range;
    }

}
