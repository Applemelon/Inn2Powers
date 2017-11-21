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
public class SearchCompany {

    private DALManager dm;

    public SearchCompany() throws IOException {
        this.dm = new DALManager();
    }

    /**
     * Find the company of given name.
     *
     * @param name of company
     * @return company if found, else null.
     */
    public Company findCompany(String name) throws BLLException {
        List<Company> companies = dm.getAllCompanies();
        for (Company company : companies) {
            if (company.getName().equalsIgnoreCase(name)) {
                return company;
            }
        }

        throw new BLLException("Company " + name + " not found!");
    }

    /**
     * Get relation path between two companies.
     *
     * @param c1 source company.
     * @param c2 target company.
     * @return List of relations.
     */
    public List<Relation> findRelation(Company c1, Company c2) throws BLLException {
        List<List<Relation>> listOfRelations = findRelations(c1);

        for (List<Relation> relations : listOfRelations) {
            if (!relations.isEmpty()) {
                if (relations.get(0).getSource().getId() == c1.getId()) {
                    if (relations.get(relations.size() - 1).getTarget().getId() == c2.getId()) {
                        return relations;
                    }
                }
            }
        }

        throw new BLLException("Relation between " + c1.getName() + " and " + c2.getName() + " was not found!");
    }

    /**
     * Find all relation paths for a company.
     *
     * @param c Company.
     * @return A list of Relation lists which represents paths.
     */
    public List<List<Relation>> findRelations(Company c) {
        List<Relation> relations = dm.getAllRelations();
        List<List<Relation>> results = new ArrayList<>();
        List<Relation> tmp = new ArrayList();
        List<Relation> found = new ArrayList();

        System.out.println("Start " + c.getId() + " Search.");
        results.addAll(findRelationsRecursion(c, tmp, found, relations, -1));

        return results;
    }

    /**
     * Find all relation paths for a company.
     *
     * @param c Company.
     * @param depth depth of search. If -1 the search is unlimited.
     * @return A list of Relation lists which represents paths.
     */
    public List<List<Relation>> findRelationsInDepth(Company c, int depth) {
        List<Relation> relations = dm.getAllRelations();
        List<List<Relation>> results = new ArrayList<>();
        List<Relation> tmp = new ArrayList();
        List<Relation> found = new ArrayList();

        System.out.println("Start " + c.getId() + " Search with " + depth + " in depth.");
        results.addAll(findRelationsRecursion(c, tmp, found, relations, depth));

        return results;
    }

    /**
     * Recursive method which iterate through all relations and find all
     * possible combinations.
     *
     * @param c Company to use as source.
     * @param tmpResults List used to store temporary result.
     * @param foundRelations List used to store found relations.
     * @param allRelations List of all relations.
     * @param depth depth of search. If -1 the search is unlimited.
     * @return List of a List of relation. Each list of relations is a path.
     */
    private List<List<Relation>> findRelationsRecursion(Company c, List<Relation> tmpResults, List<Relation> foundRelations, List<Relation> allRelations, int depth) {
        List<List<Relation>> results = new ArrayList<>();

        // Iterate through all relations.
        for (Relation relation : allRelations) {

            if (depth == 0) {
                break;
            }
            // Only runs if the source of the relation is the same as the company given.
            if (relation.getSource().getId() == c.getId()) {
                // Checks if the relation is already found.
                boolean isFound = false;
                for (Relation foundResult : foundRelations) {
                    if (foundResult.getSource().getId() == relation.getTarget().getId()) {
                        isFound = true;
                    }
                }

                // If the relation to the company have not been used.
                if (!isFound) {
                    // Make new array with the same values of temporary result.
                    List<Relation> result = new ArrayList<>();
                    result.addAll(tmpResults);

                    // Add new relation.
                    result.add(relation);

                    // Add new relation to found relations.
                    foundRelations.add(relation);

                    // Add result to results.
                    results.add(result);

                    int newDepth = depth != -1 ? depth - 1 : depth;

                    // Run recursive method with new company and temporary result.
                    results.addAll(findRelationsRecursion(relation.getTarget(), result, foundRelations, allRelations, newDepth));
                }
            }
        }

        return results;
    }
}
