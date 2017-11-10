/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inn2powers.GUI;

import be.Company;
import be.Relation;
import inn2powers.BLL.BLLManager;
import inn2powers.BLL.SearchCompany;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javax.management.relation.Role;

/**
 * FXML Controller class
 *
 * @author Alex
 */
public class MainWindowController implements Initializable {

    @FXML
    private ComboBox<String> comboSearchType;
    @FXML
    private TextField txtFirmaerSelected;
    @FXML
    private ComboBox<String> comboOverbrancherSelected;
    @FXML
    private ComboBox<String> comboUnderbrancherSelected;
    @FXML
    private Button btnFiltrer;
    @FXML
    private ListView listFirmaer;
    @FXML
    private Accordion accordian;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        comboSearchType.setItems(FXCollections.observableArrayList("Firmaer", "Overbrancher", "Underbrancher"));
        comboSearchType.setVisibleRowCount(3);

    }

    MainWindowModel MWModel;
    BLLManager BLLM;

    public MainWindowController() throws IOException {
        this.BLLM = new BLLManager();
        this.MWModel = new MainWindowModel();
    }

    @FXML
    private void handleSearchType() {
        try {/*da der er i BLLManager er brugt ioExeption, skal der her anvendes en try catch,
            ved at indsætte metoden i en try/catch fanger fejlen hvis der smides en ioexeption inde
            i BLLManger*/
            //If "Firmaer" is selected in comboSearchType...
            if (comboSearchType.getSelectionModel().getSelectedIndex() == 0) {
                txtFirmaerSelected.setVisible(true);
                comboOverbrancherSelected.setVisible(false);
                comboUnderbrancherSelected.setVisible(false);
            }
            //If "Overbrancher" is selected in comboSearchType...
            if (comboSearchType.getSelectionModel().getSelectedIndex() == 1) {
                txtFirmaerSelected.setVisible(false);
                comboOverbrancherSelected.setVisible(true);
                comboUnderbrancherSelected.setVisible(false);

                //det er den vi fylder ind i vores combox så den viser en liste af string object.
                ObservableList<String> ol = FXCollections.observableArrayList();
                // fylder listen med businessRoles
                ol.addAll(BLLM.getBusinessRoles());
                //fylder listen ind i vores combobox
                comboOverbrancherSelected.setItems(ol);
            }
            //If "Underbrancher" is selected in comboSearchType...
            if (comboSearchType.getSelectionModel().getSelectedIndex() == 2) {
                txtFirmaerSelected.setVisible(false);
                comboOverbrancherSelected.setVisible(false);
                comboUnderbrancherSelected.setVisible(true);

                //det er den vi fylder ind i vores combox så den viser en liste af string object.
                ObservableList<String> ol = FXCollections.observableArrayList();
                // fylder listen med businessRoles
                ol.addAll(BLLM.getSupplyChainCategories());
                //fylder listen ind i vores combobox
                comboUnderbrancherSelected.setItems(ol);
            }
        } catch (Exception ex) {

        }
    }

    @FXML
    private void handleButton() {
        if (!comboOverbrancherSelected.getSelectionModel().isEmpty()) {
            if (comboOverbrancherSelected.isVisible()) {
                ObservableList<String> ol = FXCollections.observableArrayList();
                List<Company> companies = BLLM.getCompanysFromBusinessRole(comboOverbrancherSelected.getValue());
                for (Company company : companies) {
                    ol.add(company.getName());
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
                    accordian.getPanes().add(gridTitlePane);

                    listFirmaer.setItems(ol);
                }
            }
        }
        //under brancher
        if (!comboUnderbrancherSelected.getSelectionModel().isEmpty()) {
            if (comboUnderbrancherSelected.isVisible()) {
                ObservableList<String> ol = FXCollections.observableArrayList();
                List<Company> companies = BLLM.getCompaniesFromCategories(comboUnderbrancherSelected.getValue());
                for (Company company : companies) {
                    ol.add(company.getName());
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

                    accordian.getPanes().add(gridTitlePane);
                    listFirmaer.setItems(ol);
                }
            }
        }
    }

    @FXML
    public void searchBar() {
        ObservableList<String> ol = FXCollections.observableArrayList();
        FilteredList<String> filteredList;
        filteredList = new FilteredList<>(ol);
        listFirmaer.setItems(filteredList);

        filteredList.setPredicate(new Predicate<String>() {
            @Override
            public boolean test(String t) {
                return false;
            }
        });
    }

}
