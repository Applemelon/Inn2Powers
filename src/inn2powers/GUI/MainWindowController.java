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
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

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

    @FXML
    private void handleSearchType() {
        try {/*da der er i BLLManager er brugt ioExeption, skal der her anvendes en try catch,
            ved at indsætte metoden i en try/catch fanger fejlen hvis der smides en ioexeption inde
            i BLLManger*/
            BLLManager bm = new BLLManager();// laver et nyt BLLManeger object.
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
                ol.addAll(bm.getBusinessRoles());
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
                ol.addAll(bm.getSupplyChainCategories());
                //fylder listen ind i vores combobox
                comboUnderbrancherSelected.setItems(ol);
            }
        } catch (Exception ex) {

        }
    }

    @FXML
    private void handleFiltrer() {
//        try {
//            SearchCompany sc = new SearchCompany();
//            List<Relation> relations = null;
//            Company c = sc.findCompany(txtFirmaerSelected.getText());
//            if (comboSearchType.getSelectionModel().getSelectedIndex() == 0) {
////                companies = new ArrayList<Company>();
////                companies.add(c);
//            } else if (comboSearchType.getSelectionModel().getSelectedIndex() == 1) {
//                //relations = sc.findRelations(c);
//            } else if (comboSearchType.getSelectionModel().getSelectedIndex() == 2) {
//
//            }
//        } catch (Exception ex) {
//
//        }
    }
}
