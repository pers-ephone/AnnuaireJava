package com.amazingname.client.controller;

import com.amazingname.client.Main;
import com.amazingname.client.model.Employee;
import com.amazingname.client.model.Service;
import com.amazingname.client.model.Site;
import com.amazingname.client.service.EmployeeService;
import com.amazingname.client.service.SiteService;
import com.amazingname.client.service.ServiceService;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class SecondSearchPageController {

    @FXML private ComboBox<String> siteComboBox;
    @FXML private ComboBox<String> serviceComboBox;
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
    private final SiteService siteService = new SiteService();
    private final ServiceService serviceAnService = new ServiceService();

    @FXML
    public void initialize() {
        siteComboBox.getItems().add("Tous");
        serviceComboBox.getItems().add("Tous");

        try {
            List<String> siteNames = siteService.getAllSites()
                    .stream().map(Site::getCity).toList();
            siteComboBox.getItems().addAll(siteNames);

            List<String> serviceNames = serviceAnService.getAllServices()
                    .stream().map(Service::getName).toList();
            serviceComboBox.getItems().addAll(serviceNames);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        siteComboBox.getSelectionModel().selectFirst();
        serviceComboBox.getSelectionModel().selectFirst();

        firstNameColumn = new TableColumn<>("Prénom");
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

        resultsTable.getColumns().addAll(firstNameColumn, lastNameColumn, emailColumn,
                landlinePhoneColumn, mobilePhoneColumn, siteColumn, serviceColumn);
        resultsTable.setPlaceholder(new Label("Aucun résultat trouvé"));

        // make columns fill the width of the table
        resultsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }

    @FXML
    public void handleFilter(ActionEvent event) throws IOException, InterruptedException {
        String selectedSite = siteComboBox.getValue();
        String selectedService = serviceComboBox.getValue();

        resultsTable.getItems().clear();

        List<Employee> results;
        try {
            results = employeeService.filterEmployees(selectedSite, selectedService);
        } catch (IOException | InterruptedException e) {
            resultsContainer.getChildren().add(new Label("Échec de la connexion"));
            e.printStackTrace();
            return;
        }

        if (!results.isEmpty()) {
            resultsTable.getItems().addAll(results);
        }
    }

    @FXML
    public void goBack(ActionEvent event) {
        try {
            Stage stage = (Stage) siteComboBox.getScene().getWindow();
            Main.navigateTo(stage, "MainSearchPage.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
