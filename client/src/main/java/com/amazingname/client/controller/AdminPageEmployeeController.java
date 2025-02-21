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
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.StringConverter;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class AdminPageEmployeeController {

    @FXML private TextField firstNameField;
    @FXML private TextField lastNameField;
    @FXML private TextField emailField;
    @FXML private TextField landlinePhoneField;
    @FXML private TextField mobilePhoneField;
    @FXML private ComboBox<Site> siteComboBox;
    @FXML private ComboBox<Service> serviceComboBox;
    @FXML private TableView<Employee> dataTable;
    @FXML private TableColumn<Employee, Integer> idColumn;
    @FXML private TableColumn<Employee, String> firstNameColumn;
    @FXML private TableColumn<Employee, String> lastNameColumn;
    @FXML private TableColumn<Employee, String> emailColumn;
    @FXML private TableColumn<Employee, String> landlinePhoneColumn;
    @FXML private TableColumn<Employee, String> mobilePhoneColumn;
    @FXML private TableColumn<Employee, String> siteColumn;
    @FXML private TableColumn<Employee, String> serviceColumn;
    @FXML private Button addButton;
    @FXML private Button saveButton;
    @FXML private Button deleteButton;

    private final EmployeeService employeeService = new EmployeeService();
    private final SiteService siteService = new SiteService();
    private final ServiceService serviceService = new ServiceService();

    @FXML
    public void initialize() {
        // define tableview columns
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        landlinePhoneColumn.setCellValueFactory(new PropertyValueFactory<>("landlinePhone"));
        mobilePhoneColumn.setCellValueFactory(new PropertyValueFactory<>("mobilePhone"));
        siteColumn.setCellValueFactory(cellData -> {
            Site site = cellData.getValue().getSite();
            return site != null ? site.cityProperty() : null;
        });
        serviceColumn.setCellValueFactory(cellData -> {
            Service service = cellData.getValue().getService();
            return service != null ? service.nameProperty() : null;
        });

        // fill tableview
        try {
            List<Employee> employees = employeeService.getAllEmployees();
            dataTable.getItems().setAll(employees);

            List<Site> sites = siteService.getAllSites();
            siteComboBox.getItems().setAll(sites);
            siteComboBox.setConverter(new StringConverter<>() {
                @Override
                public String toString(Site site) {
                    return site != null ? site.getCity() : "";
                }

                @Override
                public Site fromString(String string) {
                    return sites.stream().filter(site -> site.getCity().equals(string)).findFirst().orElse(null);
                }
            });

            // fill comboboxes
            List<Service> services = serviceService.getAllServices();
            serviceComboBox.getItems().setAll(services);
            serviceComboBox.setConverter(new StringConverter<>() {
                @Override
                public String toString(Service service) {
                    return service != null ? service.getName() : "";
                }

                @Override
                public Service fromString(String string) {
                    return services.stream().filter(service -> service.getName().equals(string)).findFirst().orElse(null);
                }
            });
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
    public void goToAdminPageService(ActionEvent event) {
        try {
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            Main.navigateTo(stage, "AdminPageService.fxml");
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
    public void addEmployee(ActionEvent event) {
        try {
            String firstName = firstNameField.getText();
            String lastName = lastNameField.getText();
            String email = emailField.getText();
            String landlinePhone = landlinePhoneField.getText();
            String mobilePhone = mobilePhoneField.getText();
            Site site = siteComboBox.getValue();
            Service service = serviceComboBox.getValue();

            Employee employee = new Employee();
            employee.setFirstName(firstName);
            employee.setLastName(lastName);
            employee.setEmail(email);
            employee.setLandlinePhone(landlinePhone);
            employee.setMobilePhone(mobilePhone);
            employee.setSite(site);
            employee.setService(service);

            employeeService.createEmployee(employee);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Succès");
            alert.setHeaderText(null);
            alert.setContentText("Employé ajouté avec succès.");
            alert.showAndWait();

            // refresh the table
            dataTable.getItems().setAll(
                employeeService.getAllEmployees()
                );
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Une erreur s'est produite lors de l'ajout de l'employé.");
            alert.showAndWait();
            e.printStackTrace();
        }
    }

    @FXML
    public void saveEmployee(ActionEvent event) {
        try {
            if (dataTable.getSelectionModel().getSelectedItem() != null) {
                Employee selectedEmployee = dataTable.getSelectionModel().getSelectedItem();
                String firstName = firstNameField.getText();
                String lastName = lastNameField.getText();
                String email = emailField.getText();
                String landlinePhone = landlinePhoneField.getText();
                String mobilePhone = mobilePhoneField.getText();
                Site site = siteComboBox.getValue();
                Service service = serviceComboBox.getValue();

                Employee employee = new Employee();
                employee.setId(selectedEmployee.getId());
                employee.setFirstName(firstName);
                employee.setLastName(lastName);
                employee.setEmail(email);
                employee.setLandlinePhone(landlinePhone);
                employee.setMobilePhone(mobilePhone);
                employee.setSite(site);
                employee.setService(service);

                employeeService.updateEmployee(employee);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Succès");
                alert.setHeaderText(null);
                alert.setContentText("Employé sauvegardé avec succès.");
                alert.showAndWait();

                // refresh the table
                List<Employee> employees = employeeService.getAllEmployees();
                dataTable.getItems().setAll(employees);

                saveButton.setVisible(false);
                deleteButton.setVisible(false);
            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Une erreur s'est produite lors de la sauvegarde de l'employé.");
            alert.showAndWait();
            e.printStackTrace();
        }
    }

    @FXML
    public void deleteEmployee(ActionEvent event) {
        try {
            if (dataTable.getSelectionModel().getSelectedItem() != null) {
                Employee selectedEmployee = dataTable.getSelectionModel().getSelectedItem();
                employeeService.deleteEmployeeById((long) selectedEmployee.getId());

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Succès");
                alert.setHeaderText(null);
                alert.setContentText("Employé supprimé avec succès.");
                alert.showAndWait();

                // refresh the table
                List<Employee> employees = employeeService.getAllEmployees();
                dataTable.getItems().setAll(employees);

                saveButton.setVisible(false);
                deleteButton.setVisible(false);
            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Une erreur s'est produite lors de la suppression de l'employé.");
            alert.showAndWait();
            e.printStackTrace();
        }
    }

    @FXML
    public void handleRowClick(MouseEvent event) {
        // show the save and delete buttons when a row is selected
        if (dataTable.getSelectionModel().getSelectedItem() != null) {
            Employee selectedEmployee = dataTable.getSelectionModel().getSelectedItem();
            firstNameField.setText(selectedEmployee.getFirstName());
            lastNameField.setText(selectedEmployee.getLastName());
            emailField.setText(selectedEmployee.getEmail());
            landlinePhoneField.setText(selectedEmployee.getLandlinePhone());
            mobilePhoneField.setText(selectedEmployee.getMobilePhone());
            siteComboBox.setValue(selectedEmployee.getSite());
            serviceComboBox.setValue(selectedEmployee.getService());

            saveButton.setVisible(true);
            deleteButton.setVisible(true);
        } else {
            saveButton.setVisible(false);
            deleteButton.setVisible(false);
        }
    }

}
