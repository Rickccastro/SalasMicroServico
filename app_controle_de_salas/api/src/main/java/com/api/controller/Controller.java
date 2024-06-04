package com.api.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@RestController
public class Controller {

    @Autowired
    private DiscoveryClient discoveryClient;

    @Value("${spring.service-discovery-app}")
    private String applicationDiscoveryURI;

    @Value("${spring.application.name}")
    private String appName;

    private String serviceName;

    @RequestMapping("/service-instances/{applicationName}")
    public List<ServiceInstance> serviceInstancesByApplicationName(
            @PathVariable String applicationName) {
        return this.discoveryClient.getInstances(applicationName);
    }

    private URI validService(String serviceName) {
        try {
            List<ServiceInstance> instances = discoveryClient.getInstances(serviceName);
            return instances.isEmpty() ? null : instances.get(0).getUri();
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve service URI: " + e.getMessage(), e);
        }
    }

    /*construir requisicoes HTTP sem requestBody*/
    public HttpRequest buildRequestWithoutBody(URI uri, String endpoint, String method) throws URISyntaxException {
        return HttpRequest.newBuilder()
                .uri(new URI(uri.toString() + endpoint))
                .method(method, HttpRequest.BodyPublishers.noBody())
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .build();
    }
    /*construir requisicoes HTTP com requestBody*/
    public HttpRequest buildRequestWithBody(URI uri, String endpoint, String method,String requestBody) throws URISyntaxException {
        return HttpRequest.newBuilder()
                .uri(new URI(uri.toString() + endpoint))
               .method(method, HttpRequest.BodyPublishers.ofString(requestBody))
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .build();
    }

    


    /*CONTROLE-SALAS*/
    @PostMapping("/controle-salas/addSala")
    public String addSalaControleSalas(@RequestBody String requestBody) throws URISyntaxException {
        URI uri = validService("CONTROLE-SALAS");
        // Verifica se a URI é nula
        if (uri == null) {
            return "Failed to retrieve service URI";
        }
        // Constrói o endpoint
        String endpoint = "/addSala";

        // Constrói e envia a requisição HTTP
        try {
            HttpRequest request = buildRequestWithBody(uri, endpoint, "POST", requestBody);
            HttpResponse<String> response = HttpClient.newBuilder().build().send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (IOException | InterruptedException | URISyntaxException e) {
            throw new RuntimeException("Failed to send HTTP request: " + e.getMessage(), e);
        }
    }

    @GetMapping("/controle-salas/availableSalas")
    public String availableSalasControleSalas() {
        URI uri = validService("CONTROLE-SALAS");

        // Verifica se a URI é nula
        if (uri == null) {
            return "Failed to retrieve service URI";
        }

        // Constrói o endpoint
        String endpoint = "/availableSalas" ;

        // Constrói e envia a requisição HTTP
        try {
            HttpRequest request = buildRequestWithoutBody(uri, endpoint, "GET");
            HttpResponse<String> response = HttpClient.newBuilder().build().send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (IOException | InterruptedException | URISyntaxException e) {
            throw new RuntimeException("Failed to send HTTP request: " + e.getMessage(), e);
        }
    }

    @GetMapping("/controle-salas/statusSalas/nsala")
    public String getStatusSalas() {
        URI uri = validService("CONTROLE-SALAS");
        // Verifica se a URI é nula
        if (uri == null) {
            return "Failed to retrieve service URI";
        }

        // Constrói o endpoint
         String endpoint = "/statusSalas/nsala";

        // Constrói e envia a requisição HTTP
        try {
            HttpRequest request = buildRequestWithoutBody(uri, endpoint, "GET");
            HttpResponse<String> response = HttpClient.newBuilder().build().send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (IOException | InterruptedException | URISyntaxException e) {
            throw new RuntimeException("Failed to send HTTP request: " + e.getMessage(), e);
        }
    }

    @PatchMapping("/controle-salas/maintenceSala")
    public String maintenceSala(@RequestBody String requestBody) throws URISyntaxException {
        URI uri = validService("CONTROLE-SALAS");
        // Verifica se a URI é nula
        if (uri == null) {
            return "Failed to retrieve service URI";
        }

        // Constrói o endpoint
        String endpoint = "/maintenceSala";

        // Constrói e envia a requisição HTTP
        try {
            HttpRequest request = buildRequestWithBody(uri, endpoint, "PATCH", requestBody);
            HttpResponse<String> response = HttpClient.newBuilder().build().send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (IOException | InterruptedException | URISyntaxException e) {
            throw new RuntimeException("Failed to send HTTP request: " + e.getMessage(), e);
        }
    }

    /*USERS-SALAS*/

    @PostMapping("/user-salas/createUser")
    public String userSalasAdd(@RequestBody String requestBody) throws URISyntaxException {
        URI uri = validService("USERS-SALAS");
        // Verifica se a URI é nula
        if (uri == null) {
            return "Failed to retrieve service URI";
        }
        // Constrói o endpoint
        String endpoint = "/createUser";

        // Constrói e envia a requisição HTTP
        try {
            HttpRequest request = buildRequestWithBody(uri, endpoint, "POST", requestBody);
            HttpResponse<String> response = HttpClient.newBuilder().build().send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (IOException | InterruptedException | URISyntaxException e) {
            throw new RuntimeException("Failed to send HTTP request: " + e.getMessage(), e);
        }
    }

    @GetMapping("/user-salas/getProfessorList")
    public String usersalasProfessorList() {
        URI uri = validService("USERS-SALAS");

        // Verifica se a URI é nula
        if (uri == null) {
            return "Failed to retrieve service URI";
        }

        // Constrói o endpoint
        String endpoint = "/getProfessorList" ;

        // Constrói e envia a requisição HTTP
        try {
            HttpRequest request = buildRequestWithoutBody(uri, endpoint, "GET");
            HttpResponse<String> response = HttpClient.newBuilder().build().send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (IOException | InterruptedException | URISyntaxException e) {
            throw new RuntimeException("Failed to send HTTP request: " + e.getMessage(), e);
        }
    }

    /*NOTIFICATION-SALAS*/
    @PostMapping("/notification-salas/addNotification")
    public String addNotification(@RequestBody String requestBody) throws URISyntaxException {
        URI uri = validService("NOTIFICATION-SALAS");
        // Verifica se a URI é nula
        if (uri == null) {
            return "Failed to retrieve service URI";
        }
        // Constrói o endpoint
        String endpoint = "/addNotification";

        // Constrói e envia a requisição HTTP
        try {
            HttpRequest request = buildRequestWithBody(uri, endpoint, "POST", requestBody);
            HttpResponse<String> response = HttpClient.newBuilder().build().send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (IOException | InterruptedException | URISyntaxException e) {
            throw new RuntimeException("Failed to send HTTP request: " + e.getMessage(), e);
        }
    }

    /*RESERVA-SALAS*/
    @PostMapping("/reserva-salas/reserve")
    public String reserve(@RequestBody String requestBody) throws URISyntaxException {
        URI uri = validService("RESERVA-SALAS");
        if (uri == null) {
            return "Failed to retrieve service URI";
        }
        // Constrói o endpoint
        String endpoint = "/reserve";

        // Constrói e envia a requisição HTTP
        try {
            HttpRequest request = buildRequestWithBody(uri, endpoint, "POST", requestBody);
            HttpResponse<String> response = HttpClient.newBuilder().build().send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (IOException | InterruptedException | URISyntaxException e) {
            throw new RuntimeException("Failed to send HTTP request: " + e.getMessage(), e);
        }
    }

}

