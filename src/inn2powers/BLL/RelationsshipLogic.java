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
 * @author janvanzetten
 */
public class RelationsshipLogic {

    private DALManager dm;

    public RelationsshipLogic() throws IOException {
        dm = new DALManager();
    }

    public List<Relation> findRelationTo(Company startCompany, Company targetCompany) {
        List<Relation> relations = dm.getAllRelations();
        List<Company> LayerOne = new ArrayList<>();
        List<Relation> LayerOneR = new ArrayList<>();
        List<Company> LayerTwo = new ArrayList<>();
        List<Relation> LayerTwoR = new ArrayList<>();

        //first Layer check
        for (Relation relation : relations) {
            Company check = getFreeCompanyRelation(startCompany, relation);
            if (check != null) {
                LayerOne.add(check);
                LayerOneR.add(relation);
                if (checkRelation(targetCompany, relation)) {
                    List<Relation> list = new ArrayList<>();
                    list.add(relation);
                    return list;
                }
            }
        }

        //layer two
        for (int i = 0; i < LayerOne.size(); i++) {
            for (Relation relation : relations) {
                Company check = getFreeCompanyRelation(LayerOne.get(i), relation);
                if (check != null) {
                    LayerTwo.add(check);
                    LayerTwoR.add(relation);
                    if (checkRelation(targetCompany, relation)) {
                        List<Relation> list = new ArrayList<>();
                        list.add(LayerOneR.get(i));
                        list.add(relation);
                        return list;
                    }
                }
            }
        }
        
        
        //Layer three
        for (int i = 0; i < LayerTwoR.size(); i++) {
            for (Relation relation : relations) {
                Company check = getFreeCompanyRelation(LayerTwo.get(i), relation);
                if (check != null) {
                    LayerTwo.add(check);
                    LayerTwoR.add(relation);
                    if (checkRelation(targetCompany, relation)) {
                        List<Relation> list = new ArrayList<>();
                        list.add(LayerOneR.get(i));
                        list.add(relation);
                        return list;
                    }
                }
            }
            
        }

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

}
