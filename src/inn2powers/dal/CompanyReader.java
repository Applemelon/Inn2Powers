/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inn2powers.dal;

import be.Company;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author janvanzetten
 */
public class CompanyReader {

    private File companiesCSV = new File("companies.csv");
    private Scanner scanner;

    
    public CompanyReader() throws FileNotFoundException {
        scanner = new Scanner(new BufferedReader(new FileReader(companiesCSV)));
    }

    /**
     * Reads all the companies from the file and return them as a list
     * @return a List of Companies
     */
    @SuppressWarnings("empty-statement")
    public List<Company> getAllCompanies() {
        List<Company> returnList = new ArrayList<>();
        scanner.nextLine();
        while (scanner.hasNext()) {
            String lineInput = scanner.nextLine();
            String[] dataInput1 = lineInput.split(",");
            
            //Til løsning af firmanavne med komma
            String[] dataInput;
                if(dataInput1[1].startsWith("\"")){
                    String correction = (dataInput1[1].replace("\"", "") + "," + dataInput1[2].replace("\"", ""));
                    dataInput = new String[8];
                    int j = 0;
                    for (int i = 0; i < dataInput.length; i++) {
                        
                        dataInput[i] = dataInput1[j];
                        if (j == 0)
                            j = 2;
                        else
                            j++;
                    }
                    dataInput[1] = correction;
                }else{
                    dataInput = dataInput1;
                }
            
            
            Company company = new Company(Integer.parseInt(dataInput[0]), dataInput[1], dataInput[2], dataInput[3], dataInput[4], "no supplyChainCategoriy error", "no buisnessRole Error", Double.parseDouble(dataInput[5]), Double.parseDouble(dataInput[6]), Integer.parseInt(dataInput[7]));
       
            returnList.add(company);
        }
        
        return returnList;
    }

}
