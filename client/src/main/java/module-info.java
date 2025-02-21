module com.amazingname.client {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;

    opens com.amazingname.client to javafx.fxml;
    opens com.amazingname.client.controller to javafx.fxml;
    exports com.amazingname.client;
    exports com.amazingname.client.model;
}
