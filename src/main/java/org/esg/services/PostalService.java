package org.esg.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import org.esg.domain.Customer;

public class PostalService {

  private final CustomerService customerService;
  private final HttpClient httpClient;

  public PostalService(CustomerService customerService, HttpClient httpClient) {
    this.customerService = customerService;
    this.httpClient = httpClient;
  }

  public void sendCustomerCsvFile(BufferedReader bufferedReader) throws IOException {
    List<Customer> customers = customerService.parseCsv(bufferedReader);
    customers.forEach(this::sendPostRequest);
  }

  public void getCustomerDetailsByReference(Long customerRef) throws IOException, InterruptedException {
    String apiUrl = "http://localhost:8080/v1/customer" + "/" + customerRef;
    HttpRequest request = buildGetHttpRequest(apiUrl);
    HttpResponse<String> response = sendHttpRequest(request);
    System.out.println(response.body());
  }

  private void sendPostRequest(Customer customer) {
    try {
      String customerBody = convertCustomerToJsonString(customer);
      String apiUrl = "http://localhost:8080/v1/customer";
      HttpRequest request = buildPostHttpRequest(apiUrl, customerBody);
      HttpResponse<String> response = sendHttpRequest(request);
      System.out.println(response.body());
    } catch (IOException | InterruptedException e) {
      System.out.println("message" + e.getLocalizedMessage());
      throw new RuntimeException(e);
    }
  }

  private HttpResponse<String> sendHttpRequest(HttpRequest request) throws IOException, InterruptedException {
    return httpClient.send(request, HttpResponse.BodyHandlers.ofString());
  }

  private HttpRequest buildPostHttpRequest(String apiUrl, String customerBody) {
    return HttpRequest.newBuilder()
      .uri(URI.create(apiUrl))
      .header("Content-Type", "application/json")
      .POST(HttpRequest.BodyPublishers.ofString(customerBody)).build();
  }

  private HttpRequest buildGetHttpRequest(String apiUrl) {
    return HttpRequest.newBuilder()
      .uri(URI.create(apiUrl))
      .header("Content-Type", "application/json")
      .GET()
      .build();
  }

  private String convertCustomerToJsonString(Customer customer) {
    try {
      ObjectMapper mapper = new ObjectMapper();
      return mapper.writeValueAsString(customer);
    } catch (JsonProcessingException e) {
      throw new RuntimeException("Error converting Customer to JSON string", e);
    }
  }
}
