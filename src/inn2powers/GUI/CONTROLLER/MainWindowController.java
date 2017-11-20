/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inn2powers.GUI.CONTROLLER;

import be.Company;
import inn2powers.BLL.BLLManager;
import inn2powers.GUI.MODEL.MainWindowModel;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Accordion;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author Alex
 */
public class MainWindowController implements Initializable
{

    @FXML
    private ComboBox<String> txtSearch;
    @FXML
    private ComboBox<String> comboSearchType;
    @FXML
    private TextField txtFirmaerSelected;
    @FXML
    private ComboBox<String> comboOverbrancherSelected;
    @FXML
    private ComboBox<String> comboUnderbrancherSelected;
    @FXML
    private Accordion accordion;

    MainWindowModel MWModel;
    BLLManager BLLM;

    /**
     * Constructor which initiates variables.
     *
     * @throws IOException
     */
    public MainWindowController() throws IOException
    {
        this.BLLM = new BLLManager();
        this.MWModel = new MainWindowModel();
    }

    /**
     * Initialise the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        txtSearch.setItems(MWModel.getObsProposals());

        comboSearchType.setItems(FXCollections.observableArrayList("Firmaer", "Overbrancher", "Underbrancher"));
        comboSearchType.setVisibleRowCount(3);

        comboOverbrancherSelected.setItems(MWModel.getObsBusinessRoles());

        comboUnderbrancherSelected.setItems(MWModel.getObsSupplyChainCategories());
    }

    /**
     * Gets proposals based on user input and add to combobox.
     *
     * @param event
     */
    @FXML
    private void handleProposal(KeyEvent event)
    {
        txtSearch.getItems().clear();

        String str = txtSearch.getPromptText();

        if (str.length() == 1)
        {
            List<Company> companies = MWModel.getCompanies();
            for (Company company : companies)
            {
                if (company.getName().charAt(0) == str.charAt(0))
                {
                    txtSearch.getItems().add(company.getName());
                }
            }
        }
        else if (!str.isEmpty())
        {
            List<Company> companies = MWModel.getCompanies();
            for (Company company : companies)
            {
                if (company.getName().contains(str))
                {
                    txtSearch.getItems().add(company.getName());
                }
            }
        }
    }

    /**
     * Change to wanted input by combobox selection.
     */
    @FXML
    private void handleSearchType()
    {
        switch (comboSearchType.getSelectionModel().getSelectedIndex())
        {
            //If "Firmaer" is selected in comboSearchType...
            case 0:
            {
                txtFirmaerSelected.setVisible(true);
                comboOverbrancherSelected.setVisible(false);
                comboUnderbrancherSelected.setVisible(false);
                break;
            }
            //If "Overbrancher" is selected in comboSearchType...
            case 1:
            {
                txtFirmaerSelected.setVisible(false);
                comboOverbrancherSelected.setVisible(true);
                comboUnderbrancherSelected.setVisible(false);
                break;
            }
            //If "Underbrancher" is selected in comboSearchType...
            case 2:
            {
                txtFirmaerSelected.setVisible(false);
                comboOverbrancherSelected.setVisible(false);
                comboUnderbrancherSelected.setVisible(true);
                break;
            }
            default:
                break;
        }
    }

    /**
     * Handles search
     */
    @FXML
    private void handleSearch()
    {
        if (!comboOverbrancherSelected.getSelectionModel().isEmpty())
        {
            if (comboOverbrancherSelected.isVisible())
            {
                MWModel.updateForBusinessRoles(comboOverbrancherSelected.getValue());
                fillAccordion(MWModel.getCompanies());
            }
        }
        else if (!comboUnderbrancherSelected.getSelectionModel().isEmpty())
        {
            if (comboUnderbrancherSelected.isVisible())
            {
                MWModel.updateForSupplyChainCategories(comboUnderbrancherSelected.getValue());
                fillAccordion(MWModel.getCompanies());
            }
        }
    }

    /**
     * Fill out accordion with companies and information.
     *
     * @param companies to fill.
     */
    private void fillAccordion(List<Company> companies)
    {
        accordion.getPanes().clear();
        for (Company company : companies)
        {
            TitledPane gridTitlePane = new TitledPane(company.getName(), new AnchorPane());
            GridPane grid = new GridPane();
            grid.setVgap(4);
            grid.setPadding(new Insets(5, 5, 5, 5));
            grid.add(new Label("Country:  "), 0, 0);
            grid.add(new Label(company.getCountry()), 1, 0);
            grid.add(new Label("Address:  "), 0, 1);
            grid.add(new Label(company.getAddress()), 1, 1);
            grid.add(new Label("Website:  "), 0, 2);
            grid.add(new Label(company.getWebsite()), 1, 2);
            grid.add(new Label("Supply Chain Categories:  "), 0, 3);
            grid.add(new Label(company.getSupplyChainCategoriy()), 1, 3);
            grid.add(new Label("Business Roles:  "), 0, 4);
            grid.add(new Label(company.getBuisnessRole()), 1, 4);

            gridTitlePane.setContent(grid);
            accordion.getPanes().add(gridTitlePane);
        }
    }
}
