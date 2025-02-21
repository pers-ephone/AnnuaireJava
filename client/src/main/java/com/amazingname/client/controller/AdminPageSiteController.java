package com.amazingname.client.controller;

import com.amazingname.client.Main;
import com.amazingname.client.model.Site;
import com.amazingname.client.service.SiteService;
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

public class AdminPageSiteController {

    @FXML private TextField cityField;
    @FXML private TableView<Site> dataTable;
    @FXML private TableColumn<Site, Integer> idColumn;
    @FXML private TableColumn<Site, String> cityColumn;
    @FXML private Button addButton;
    @FXML private Button saveButton;
    @FXML private Button deleteButton;

    private final SiteService siteService = new SiteService();

    @FXML
    public void initialize() {
        // define tableview columns
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        cityColumn.setCellValueFactory(new PropertyValueFactory<>("city"));

        // fill tableview
        try {
            List<Site> sites = siteService.getAllSites();
            dataTable.getItems().setAll(sites);
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
    public void goToAdminPageService(ActionEvent event) {
        try {
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            Main.navigateTo(stage, "AdminPageService.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void addSite(ActionEvent event) {
        try {
            String city = cityField.getText();

            Site site = new Site();
            site.setCity(city);

            siteService.createSite(site);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Succès");
            alert.setHeaderText(null);
            alert.setContentText("Site ajouté avec succès.");
            alert.showAndWait();

            // refresh the table
            dataTable.getItems().setAll(
                siteService.getAllSites()
                );
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Une erreur s'est produite lors de l'ajout du site.");
            alert.showAndWait();
            e.printStackTrace();
        }
    }

    @FXML
    public void saveSite(ActionEvent event) {
        try {
            if (dataTable.getSelectionModel().getSelectedItem() != null) {
                Site selectedSite = dataTable.getSelectionModel().getSelectedItem();
                String city = cityField.getText();

                Site site = new Site();
                site.setId(selectedSite.getId());
                site.setCity(city);

                siteService.updateSite(site);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Succès");
                alert.setHeaderText(null);
                alert.setContentText("Site sauvegardé avec succès.");
                alert.showAndWait();

                // refresh the table
                List<Site> sites = siteService.getAllSites();
                dataTable.getItems().setAll(sites);

                saveButton.setVisible(false);
                deleteButton.setVisible(false);
            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Une erreur s'est produite lors de la sauvegarde du site.");
            alert.showAndWait();
            e.printStackTrace();
        }
    }

    @FXML
    public void deleteSite(ActionEvent event) {
        try {
            if (dataTable.getSelectionModel().getSelectedItem() != null) {
                Site selectedSite = dataTable.getSelectionModel().getSelectedItem();
                siteService.deleteSiteById((long) selectedSite.getId());

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Succès");
                alert.setHeaderText(null);
                alert.setContentText("Site supprimé avec succès.");
                alert.showAndWait();

                // refresh the table
                List<Site> sites = siteService.getAllSites();
                dataTable.getItems().setAll(sites);

                saveButton.setVisible(false);
                deleteButton.setVisible(false);
            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Une erreur s'est produite lors de la suppression du site.");
            alert.showAndWait();
            e.printStackTrace();
        }
    }

    @FXML
    public void handleRowClick(MouseEvent event) {
        // show the save and delete buttons when a row is selected
        if (dataTable.getSelectionModel().getSelectedItem() != null) {
            Site selectedSite = dataTable.getSelectionModel().getSelectedItem();
            cityField.setText(selectedSite.getCity());

            saveButton.setVisible(true);
            deleteButton.setVisible(true);
        } else {
            saveButton.setVisible(false);
            deleteButton.setVisible(false);
        }
    }

}
