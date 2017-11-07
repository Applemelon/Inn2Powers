/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inn2powers.BLL;

import inn2powers.DAL.DALManager;
import java.io.IOException;

/**
 *
 * @author janvanzetten
 */
public class BLLManager {

    DALManager DALM;

    public BLLManager() throws IOException {
        this.DALM = new DALManager();
    }

    public String[] getBusinessRoles() {
        return DALM.getBusinessRoles();
    }

    public String[] getSupplyChainCategories() {
        return DALM.getSupplyChainCategories();
    }
}
