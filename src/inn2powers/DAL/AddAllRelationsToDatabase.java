/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inn2powers.DAL;

import be.Relation;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author janvanzetten
 */
public class AddAllRelationsToDatabase {

    public AddAllRelationsToDatabase() throws IOException, SQLException, DALException {
        DALManager dal = new DALManager();
        List<Relation> relations = dal.getAllRelations();
        addRealtions(relations);
    }

    private void addRealtions(List<Relation> relations) throws SQLException, IOException {
        DatabaseAcces DA = new DatabaseAcces();
        for (int i = 1; i< relations.size(); i++) {
            
            Relation relation = relations.get(i);

            DA.addRelation(relation.getSource(), relation.getTarget(), relation.getType(), relation.getStrength());
        }
    }

}
