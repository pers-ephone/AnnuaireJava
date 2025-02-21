package com.amazingname.client.controller;

import com.amazingname.client.Main;
import com.amazingname.client.model.Employee;
import com.amazingname.client.service.EmployeeService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class MainSearchPageController {

    @FXML private TextField searchField;
    @FXML private Label resultCountLabel;
    @FXML private VBox resultsContainer;
    @FXML private TableView<Employee> resultsTable;
    @FXML private TableColumn<Employee, String> firstNameColumn;
    @FXML private TableColumn<Employee, String> lastNameColumn;
    @FXML private TableColumn<Employee, String> emailColumn;
    @FXML private TableColumn<Employee, String> landlinePhoneColumn;
    @FXML private TableColumn<Employee, String> mobilePhoneColumn;
    @FXML private TableColumn<Employee, String> siteColumn;
    @FXML private TableColumn<Employee, String> serviceColumn;

    private final EmployeeService employeeService = new EmployeeService();

    @FXML
    public void initialize() {
        // column header
        firstNameColumn = new TableColumn<>("Prénom");
        // column value is retrieved by calling getFirstName() on the Employee object
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameColumn = new TableColumn<>("Nom");
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        emailColumn = new TableColumn<>("Email");
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        landlinePhoneColumn = new TableColumn<>("Téléphone fixe");
        landlinePhoneColumn.setCellValueFactory(new PropertyValueFactory<>("landlinePhone"));
        mobilePhoneColumn = new TableColumn<>("Téléphone mobile");
        mobilePhoneColumn.setCellValueFactory(new PropertyValueFactory<>("mobilePhone"));
        siteColumn = new TableColumn<>("Site");
        siteColumn.setCellValueFactory(cellData -> cellData.getValue().getSite().cityProperty());
        serviceColumn = new TableColumn<>("Service");
        serviceColumn.setCellValueFactory(cellData -> cellData.getValue().getService().nameProperty());

        // add columns to the table
        resultsTable.getColumns().addAll(firstNameColumn, lastNameColumn, emailColumn,
                landlinePhoneColumn, mobilePhoneColumn, siteColumn, serviceColumn);
        resultsTable.setPlaceholder(new Label("Aucun résultat trouvé"));

        // make columns fill the width of the table
        resultsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }

    // key combination listener: Ctrl + A  -> AdminConnectionPage
    @FXML
    public void handleKeyPressed(KeyEvent event) {
        if (event.isControlDown() && event.getCode() == KeyCode.A) {
            try {
                Stage stage = (Stage) resultsTable.getScene().getWindow();
                Main.navigateTo(stage, "AdminConnectionPage.fxml");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    public void handleSearch(ActionEvent event) throws IOException, InterruptedException {
        String query = searchField.getText();
        List<Employee> results;
        try {
            results = employeeService.searchEmployees(query);
        } catch (IOException | InterruptedException e) {
            resultCountLabel.setText("Échec de la connexion");
            e.printStackTrace(); // log err for debugging
            return;
        }

        // display the number of results
        resultCountLabel.setText(results.size() + " résultats trouvés");
        resultsTable.getItems().clear();

        if (!results.isEmpty()) {
            resultsTable.getItems().addAll(results);
        }
    }

    @FXML
    public void goToSecondSearch(ActionEvent event) {
        try {
            Stage stage = (Stage) searchField.getScene().getWindow();
            Main.navigateTo(stage, "SecondSearchPage.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
