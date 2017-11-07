/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inn2powers.GUI;




import java.net.URL;
import java.util.ResourceBundle;


import javafx.collections.FXCollections;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

/**
 * FXML Controller class
 *
 * @author Alex
 */
public class MainWindowController implements Initializable {

    @FXML
    private javafx.scene.control.TextField txtFirma;
    @FXML
    private ComboBox<String> comboSearchType;
    @FXML
    private javafx.scene.control.TextField txtFirmaerSelected;
    @FXML
    private ComboBox<String> comboOverbrancherSelected;
    @FXML
    private ComboBox<String> comboUnderbrancherSelected;
    
    /**
     * Initializes the controller class.
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
        }
        //If "Underbrancher" is selected in comboSearchType...
        if (comboSearchType.getSelectionModel().getSelectedIndex() == 2) {
            txtFirmaerSelected.setVisible(false);
            comboOverbrancherSelected.setVisible(false);
            comboUnderbrancherSelected.setVisible(true);            
        }
    }
}
