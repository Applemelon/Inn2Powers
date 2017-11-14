/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inn2powers.DAL;

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
    public List<Company> getAllCompanies() {
        List<Company> returnList = new ArrayList<>();
        scanner.nextLine();
        while (scanner.hasNext()) {
            String lineInput = scanner.nextLine();
            String[] dataInput = lineInput.split(",");
            Company company = new Company(Integer.parseInt(dataInput[0]), dataInput[1], dataInput[2], dataInput[3], dataInput[4], dataInput[5], dataInput[6], Double.parseDouble(dataInput[7]), Double.parseDouble(dataInput[8]), Integer.parseInt(dataInput[9]));
            returnList.add(company);
        }
        
        return returnList;
    }

}
