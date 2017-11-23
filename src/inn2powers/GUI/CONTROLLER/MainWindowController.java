/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inn2powers.GUI.CONTROLLER;

import be.Company;
import inn2powers.BE.CompanyCountryFilter;
import inn2powers.BE.CompanySMEFilter;
import inn2powers.BE.ICompanyFilter;
import static inn2powers.BE.SME.*;
import inn2powers.BLL.BLLException;
import inn2powers.BLL.BLLManager;
import inn2powers.GUI.MODEL.MainWindowModel;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Accordion;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Alex
 */
public class MainWindowController implements Initializable
{

    @FXML
    private ComboBox<String> comboSearch;
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
    @FXML
    private ImageView imgLogo;
    @FXML
    private TextField txtMaxRange;
    @FXML
    private VBox hboxFilter;
    @FXML
    private CheckBox chkIsSME;
    @FXML
    private CheckBox chkIsNotSME;
    @FXML
    private CheckBox chkUnknown;
    @FXML
    private ArrayList<CheckBox> smeChkLst;
    @FXML
    private ArrayList<CheckBox> countryChkLst;

    private MainWindowModel MWModel;
    private BLLManager BLLM;
    private int caretPosition = 0;
    private int modifierKeyCount = 0;
    private List<CompanySMEFilter> smeFilter;
    private List<CompanyCountryFilter> countryFilter;

    /**
     * Constructor which initiates variables.
     *
     * @throws IOException
     */
    public MainWindowController() throws IOException, BLLException
    {
        this.BLLM = new BLLManager();
        this.MWModel = new MainWindowModel();
        this.smeFilter = new ArrayList<>();
        this.countryFilter = new ArrayList<>();
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
        imgLogo.setImage(MWModel.getLogo());

        comboSearch.setItems(MWModel.getObsProposals());

        // Check for modifier key press. example: Ctrl, Alt, Shift.
        comboSearch.setOnKeyPressed(event ->
        {
            if (event.getCode().isModifierKey())
            {
                modifierKeyCount++;
            }
        });

        comboSearchType.setItems(FXCollections.observableArrayList("Firmaer", "Overbrancher", "Underbrancher"));
        comboSearchType.setVisibleRowCount(3);
        comboSearchType.getSelectionModel().selectFirst();

        comboOverbrancherSelected.setItems(MWModel.getObsBusinessRoles());
        comboOverbrancherSelected.getSelectionModel().selectFirst();

        comboUnderbrancherSelected.setItems(MWModel.getObsSupplyChainCategories());
        comboUnderbrancherSelected.getSelectionModel().selectFirst();

        smeChkLst.add(chkIsSME);
        smeChkLst.add(chkIsNotSME);
        smeChkLst.add(chkUnknown);

        fillAccordion(MWModel.getCompanies());
        fillCountryFilter(MWModel.getCountries());
    }

    /**
     * Gets proposals based on user input and add to combobox.
     *
     * @param event
     */
    @FXML
    private void handleProposal(KeyEvent event)
    {
        // Check if modifier key is released.
        if (event.getCode().isModifierKey())
        {
            modifierKeyCount--;
        }
        // Check if key release is letter, backspace, space or delete and if no modifier key is pressed.
        else if ((event.getCode().isLetterKey() || (event.getCode() == KeyCode.BACK_SPACE || event.getCode() == KeyCode.DELETE || event.getCode() == KeyCode.SPACE)) && modifierKeyCount == 0)
        {
            // Get current position for caret.
            caretPosition = comboSearch.getEditor().getCaretPosition();

            // Hide dropdown menu.
            comboSearch.hide();

            // Get current text.
            String str = comboSearch.getEditor().getText();

            // Clear items in comboBox.
            comboSearch.getItems().clear();

            // If the current text is not null.
            if (str != null)
            {
                // If the current text is longer than 0.
                if (str.length() > 0)
                {
                    // Set text in comboBox.
                    comboSearch.getEditor().setText(str);

                    // If the caret position is shorter than the text length.
                    if (caretPosition <= str.length())
                    {
                        // Set caret position to saved caret position.
                        comboSearch.getEditor().positionCaret(caretPosition);
                    }
                    else
                    {
                        // Set caret position to end of text.
                        comboSearch.getEditor().positionCaret(str.length());
                    }
                }

                MWModel.updateProposal(str);

                // If any result, show dropdown menu.
                if (comboSearch.getItems().size() > 0)
                {
                    comboSearch.show();
                }
            }
        }
    }

    /**
     * Change text in comboBox on selection from comboBox dropdown menu.
     *
     * @param event
     */
    @FXML
    private void handleProposalSelection(ActionEvent event)
    {
        // Get selected item.
        String str = comboSearch.getSelectionModel().getSelectedItem();

        // If the selected item is not null.
        if (str != null)
        {
            // If the item is longer than 0.
            if (str.length() > 0)
            {
                // Set text in comboBox and set caret position to end of text.
                comboSearch.getEditor().setText(str);
                comboSearch.getEditor().positionCaret(str.length());
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
                System.out.println("case 0");
                txtFirmaerSelected.setVisible(true);
                comboOverbrancherSelected.setVisible(false);
                comboUnderbrancherSelected.setVisible(false);
                break;
            }
            //If "Overbrancher" is selected in comboSearchType...
            case 1:
            {
                System.out.println("case 1");
                txtFirmaerSelected.setVisible(false);
                comboOverbrancherSelected.setVisible(true);
                comboUnderbrancherSelected.setVisible(false);
                break;
            }
            //If "Underbrancher" is selected in comboSearchType...
            case 2:
            {
                System.out.println("case 2");
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
        if (comboOverbrancherSelected.isVisible())
        {
            if (!comboOverbrancherSelected.getSelectionModel().isEmpty())
            {
                MWModel.updateForBusinessRoles(comboOverbrancherSelected.getValue());
                fillAccordion(MWModel.getCompanies());
            }
        }
        else if (comboUnderbrancherSelected.isVisible())
        {
            if (!comboUnderbrancherSelected.getSelectionModel().isEmpty())
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

        updateCompanyFilters();

        for (Company company : companies)
        {
            boolean smeCriteria = true;
            boolean countryCriteria = true;
            if (smeFilter.size() > 0)
            {
                smeCriteria = false;
                for (ICompanyFilter filter : smeFilter)
                {
                    if (filter.meetsCriteria(company))
                    {
                        smeCriteria = true;
                        break;
                    }
                }
            }
            if (countryFilter.size() > 0)
            {
                countryCriteria = false;
                for (ICompanyFilter filter : countryFilter)
                {
                    if (filter.meetsCriteria(company))
                    {
                        countryCriteria = true;
                        break;
                    }
                }
            }

            if (smeCriteria && countryCriteria)
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

    private void fillCountryFilter(List<String> countries)
    {
        for (String country : countries)
        {
            CheckBox chk = new CheckBox(country);
            countryChkLst.add(chk);
            hboxFilter.getChildren().add(chk);
        }
    }

    private void updateCompanyFilters()
    {
        smeFilter.clear();
        countryFilter.clear();
        for (CheckBox checkBox : smeChkLst)
        {
            if (checkBox.isSelected())
            {
                if (checkBox == chkIsSME)
                {
                    smeFilter.add(new CompanySMEFilter(ISSME));
                }
                if (checkBox == chkIsNotSME)
                {
                    smeFilter.add(new CompanySMEFilter(ISNOTSME));
                }
                if (checkBox == chkUnknown)
                {
                    smeFilter.add(new CompanySMEFilter(UNKNOWN));
                }
            }
        }
        for (CheckBox checkBox : countryChkLst)
        {
            if (checkBox.isSelected())
            {
                countryFilter.add(new CompanyCountryFilter(checkBox.getText()));
            }
        }
    }
}
