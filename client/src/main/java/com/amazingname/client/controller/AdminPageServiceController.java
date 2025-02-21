package com.amazingname.client.controller;

import com.amazingname.client.Main;
import com.amazingname.client.model.Service;
import com.amazingname.client.service.ServiceService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class AdminPageServiceController {

    @FXML private TextField nameField;
    @FXML private TableView<Service> dataTable;
    @FXML private TableColumn<Service, Integer> idColumn;
    @FXML private TableColumn<Service, String> nameColumn;
    @FXML private Button addButton;
    @FXML private Button saveButton;
    @FXML private Button deleteButton;

    private final ServiceService serviceService = new ServiceService();

    @FXML
    public void initialize() {
        // define tableview columns
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        // fill tableview
        try {
            List<Service> services = serviceService.getAllServices();
            dataTable.getItems().setAll(services);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        // hide selected-only buttons
        saveButton.setVisible(false);
        deleteButton.setVisible(false);
    }

    @FXML
    public void goBack(ActionEvent event) {
        try {
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            Main.navigateTo(stage, "MainSearchPage.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void goToAdminPageEmployee(ActionEvent event) {
        try {
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            Main.navigateTo(stage, "AdminPageEmployee.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void goToAdminPageSite(ActionEvent event) {
        try {
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            Main.navigateTo(stage, "AdminPageSite.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void addService(ActionEvent event) {
        try {
            String name = nameField.getText();

            Service service = new Service();
            service.setName(name);

            serviceService.createService(service);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Succès");
            alert.setHeaderText(null);
            alert.setContentText("Service ajouté avec succès.");
            alert.showAndWait();

            // refresh the table
            dataTable.getItems().setAll(
                serviceService.getAllServices()
                );
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Une erreur s'est produite lors de l'ajout du service.");
            alert.showAndWait();
            e.printStackTrace();
        }
    }

    @FXML
    public void saveService(ActionEvent event) {
        try {
            if (dataTable.getSelectionModel().getSelectedItem() != null) {
                Service selectedService = dataTable.getSelectionModel().getSelectedItem();
                String name = nameField.getText();

                Service service = new Service();
                service.setId(selectedService.getId());
                service.setName(name);

                serviceService.updateService(service);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Succès");
                alert.setHeaderText(null);
                alert.setContentText("Service sauvegardé avec succès.");
                alert.showAndWait();

                // refresh the table
                List<Service> services = serviceService.getAllServices();
                dataTable.getItems().setAll(services);

                saveButton.setVisible(false);
                deleteButton.setVisible(false);
            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Une erreur s'est produite lors de la sauvegarde du service.");
            alert.showAndWait();
            e.printStackTrace();
        }
    }

    @FXML
    public void deleteService(ActionEvent event) {
        try {
            if (dataTable.getSelectionModel().getSelectedItem() != null) {
                Service selectedService = dataTable.getSelectionModel().getSelectedItem();
                serviceService.deleteServiceById((long) selectedService.getId());

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Succès");
                alert.setHeaderText(null);
                alert.setContentText("Service supprimé avec succès.");
                alert.showAndWait();

                // refresh the table
                List<Service> services = serviceService.getAllServices();
                dataTable.getItems().setAll(services);

                saveButton.setVisible(false);
                deleteButton.setVisible(false);
            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Une erreur s'est produite lors de la suppression du service.");
            alert.showAndWait();
            e.printStackTrace();
        }
    }

    @FXML
    public void handleRowClick(MouseEvent event) {
        // show the save and delete buttons when a row is selected
        if (dataTable.getSelectionModel().getSelectedItem() != null) {
            Service selectedService = dataTable.getSelectionModel().getSelectedItem();
            nameField.setText(selectedService.getName());

            saveButton.setVisible(true);
            deleteButton.setVisible(true);
        } else {
            saveButton.setVisible(false);
            deleteButton.setVisible(false);
        }
    }

}
