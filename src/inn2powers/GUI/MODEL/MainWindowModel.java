/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inn2powers.GUI.MODEL;

import be.Company;
import be.Relation;
import inn2powers.BE.SME;
import inn2powers.BLL.BLLException;
import inn2powers.BLL.Filter;
import inn2powers.BLL.SearchCompany;
import inn2powers.BLL.BLLManager;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;

/**
 *
 * @author janvanzetten
 */
public class MainWindowModel
{

    private SearchCompany searchCompany;
    private BLLManager bm;
    private Filter filter;

    private List<Company> companies;

    private ObservableList<String> obsProposals;
    private ObservableList<String> obsBusinessRoles;
    private ObservableList<String> obsSupplyChainCategories;

    private Image logo;

    /**
     * Constructor initiates variables.
     */
    public MainWindowModel()
    {
        try
        {
            logo = new Image("inn2powers/media/inn2power.jpg");

            bm = new BLLManager();

            companies = new ArrayList<>();

            obsProposals = FXCollections.observableArrayList();

            obsBusinessRoles = FXCollections.observableArrayList();
            obsBusinessRoles.addAll(bm.getBusinessRoles());

            obsSupplyChainCategories = FXCollections.observableArrayList();
            obsSupplyChainCategories.addAll(bm.getSupplyChainCategories());
        }
        catch (BLLException ex)
        {
            //TO-DO
        }
    }

    /**
     * Gets current list of companies.
     *
     * @return list of Company objects.
     */
    public List<Company> getCompanies()
    {
        return companies;
    }

    /**
     * Update companies list by Business Roles.
     *
     * @param role wanted business role.
     */
    public void updateForBusinessRoles(String role)
    {
        companies.clear();
        companies.addAll(bm.getCompanysFromBusinessRole(role));
    }

    /**
     * Update companies list by Supply Chain Category.
     *
     * @param category wanted category.
     */
    public void updateForSupplyChainCategories(String category)
    {
        companies.clear();
        companies.addAll(bm.getCompaniesFromCategories(category));
    }

    /**
     * Gets the logo image.
     *
     * @return logo image.
     */
    public Image getLogo()
    {
        return logo;
    }

    /**
     * Gets an ObservableList of Proposals.
     *
     * @return ObservableList of String.
     */
    public ObservableList<String> getObsProposals()
    {
        return obsProposals;
    }

    /**
     * Gets an ObservableList of Business Roles.
     *
     * @return ObservableList of String.
     */
    public ObservableList<String> getObsBusinessRoles()
    {
        return obsBusinessRoles;
    }

    /**
     * Gets an ObservableList of Supply Chain Categories.
     *
     * @return ObservableList of String.
     */
    public ObservableList<String> getObsSupplyChainCategories()
    {
        return obsSupplyChainCategories;
    }

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
    public List<Relation> findRelation(Company c1, Company c2) throws Exception
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
     * Finds companies with given role.
     *
     * @param c Company.
     * @param r Role.
     * @return All companies with given role.
     */
    public List<Company> findCompaniesByRole(Company c, String r)
    {
        return bm.getCompanysFromBusinessRole(r);
    }

    public void updateProposal(String str)
    {
        str = str.trim();
        // If the String is not empty.
        if (!str.isEmpty())
        {
            // Save companies to temporary arrayList.
            List<Company> tmpCompanies = new ArrayList();
            tmpCompanies.addAll(getCompanies());

            // First get company names beginning with the search.
            for (Iterator<Company> it = tmpCompanies.iterator(); it.hasNext();)
            {
                Company c = it.next();
                if (c.getName().length() >= str.length())
                {
                    if (c.getName().substring(0, str.length()).equalsIgnoreCase(str))
                    {
                        it.remove();
                        obsProposals.add(c.getName());
                    }
                }
            }

            // Get Company names containing search.
            for (Company company : tmpCompanies)
            {
                if (company.getName().toLowerCase().contains(str.toLowerCase()))
                {
                    obsProposals.add(company.getName());
                }
            }
        }
    }

    public List<String> getCountries()
    {
        List<String> countries = new ArrayList<>();
        countries.add("Denmark");
        countries.add("England");
        countries.add("Nederland");
        countries.add("Germany");
        countries.add("France");
        countries.add("Sweden");
        countries.add("Norway");
        countries.add("Finland");
        return countries;
    }
}
