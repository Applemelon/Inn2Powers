/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inn2powers.BLL;

import be.Company;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author janvanzetten
 */
public class CompanyListSorter {

    
    
    public List<Company> alphabetical(List<Company> unsortedList) {
        final int SORTERINGSRETNING = 1; //ret til -1 hvis den sortere i forkert rækkefølge
        List<Company> sortingList = new ArrayList<>();
        sortingList = unsortedList;
        boolean notSorted = true;

        while (notSorted) {
            notSorted = false;
            for (int i = 1; i <= sortingList.size(); i++) {
                Company firstElement = sortingList.get(i - 1);
                Company secondElement = sortingList.get(i);
                if (firstElement.getName().compareToIgnoreCase(secondElement.getName()) == SORTERINGSRETNING) {
                    sortingList.set(i, firstElement);
                    sortingList.set(i - 1, secondElement);
                    notSorted = true;
                }
            }
        }
        return sortingList;
    }
}
