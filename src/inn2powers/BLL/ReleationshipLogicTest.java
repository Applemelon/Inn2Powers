/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inn2powers.BLL;

import be.Company;
import be.Relation;
import com.sun.javafx.scene.control.skin.VirtualFlow;
import inn2powers.DAL.DALManager;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author janvanzetten
 */
public class ReleationshipLogicTest {

    List<List<List<Relation>>> paths;
    private DALManager dm;

    public ReleationshipLogicTest() throws IOException {
        paths = new ArrayList<>();
        dm = new DALManager();

    }

    public List<Relation> findRelationTo(Company startCompany, Company targetCompany) {
        List<Relation> relations = dm.getAllRelations();

        
        return null;
    }

    private boolean checkRelation(Company company, Relation relation) {
        return (company.equals(relation.getSource()) || company.equals(relation.getTarget()));
    }

    private Company getFreeCompanyRelation(Company company, Relation relation) {
        if (company.equals(relation.getSource())) {
            return relation.getTarget();
        } else if (company.equals(relation.getTarget())) {
            return relation.getSource();
        } else {
            return null;
        }
    }

    private void addLayer(Company company, List<Relation> relations, List<Relation> pathToCompany) {
        int layer = pathToCompany.size();
        List<List<Relation>> oportunities = new ArrayList<>();
        

        for (Relation relation : relations) {
            if (checkRelation(company, relation)) {
                List<Relation> path = new ArrayList<>();
                path.addAll(pathToCompany);
                path.add(relation);
                oportunities.add(path);
            }
        }
        
        paths.get(layer).addAll(oportunities);
        
        
    }

}
