package com.amazingname.client.service;

import com.amazingname.client.model.Employee;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EmployeeService {

    private static final String BASE_URL = "http://localhost:8080/api/employees";
    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;

    public EmployeeService() {
        this.httpClient = HttpClient.newHttpClient();
        this.objectMapper = new ObjectMapper();
    }

    public List<Employee> getAllEmployees() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL))
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        return objectMapper.readValue(response.body(), new TypeReference<List<Employee>>() {});
    }

    public Employee getEmployeeById(Long id) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/" + id))
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        return objectMapper.readValue(response.body(), Employee.class);
    }

    public List<Employee> searchEmployees(String search) throws IOException, InterruptedException {
        if (search == null || search.isEmpty()) {
            return new ArrayList<>();
        }

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/search/" + search))
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        return objectMapper.readValue(response.body(), new TypeReference<List<Employee>>() {});
    }

    public List<Employee> filterEmployees(String selectedSite, String selectedService) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL))
                .GET()
                .build();

        // get all
        HttpResponse<String> strResponse = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        List<Employee> employees = objectMapper.readValue(strResponse.body(), new TypeReference<List<Employee>>() {});

        // filter after based on selectedSite and selectedService
        return employees.stream()
                .filter(emp -> selectedSite.equals("Tous") ||
                        (emp.getSite() != null && emp.getSite().getCity() != null && emp.getSite().getCity().equalsIgnoreCase(selectedSite)))
                .filter(emp -> selectedService.equals("Tous") ||
                        (emp.getService() != null && emp.getService().getName() != null && emp.getService().getName().equalsIgnoreCase(selectedService)))
                .collect(Collectors.toList());

    }

    public Employee createEmployee(Employee employee) throws IOException, InterruptedException {
        // Create a JSON object node and populate it with employee data, excluding the id if it is null
        ObjectNode employeeNode = objectMapper.valueToTree(employee);
        if (employee.getId() == null) {
            employeeNode.remove("id");
        }

        String requestBody = objectMapper.writeValueAsString(employeeNode);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        return objectMapper.readValue(response.body(), Employee.class);
    }

    public Employee updateEmployee(Employee employee) throws IOException, InterruptedException {
        String requestBody = objectMapper.writeValueAsString(employee);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL))
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        return objectMapper.readValue(response.body(), Employee.class);
    }

    public void deleteEmployeeById(Long id) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/" + id))
                .DELETE()
                .build();

        httpClient.send(request, HttpResponse.BodyHandlers.discarding());
    }
}
