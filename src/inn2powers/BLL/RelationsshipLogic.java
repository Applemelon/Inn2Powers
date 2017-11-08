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

        //first Layer check
        for (Relation relation : relations) {
            if ((startCompany.equals(relation.getSource()) && targetCompany.equals(relation.getTarget())) 
                    || (startCompany.equals(relation.getTarget()) && (targetCompany.equals(relation.getSource())))){
                List<Relation> list = new ArrayList<>();
                list.add(relation);
                return list;
            }

        }

        

    }

}
