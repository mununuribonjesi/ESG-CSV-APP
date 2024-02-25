package org.esg.services;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import org.esg.domain.Customer;
import org.junit.Test;
import org.mockito.Mockito;

public class PostalServiceTest {

  @Test
  public void testSendCustomerCsvFile() throws IOException, InterruptedException {
    CustomerService mockCustomerService = Mockito.mock(CustomerService.class);
    HttpClient mockHttpClient = Mockito.mock(HttpClient.class);
    PostalService postalService = new PostalService(mockCustomerService, mockHttpClient);

    BufferedReader mockBufferedReader = Mockito.mock(BufferedReader.class);
    List<Customer> customers = List.of(buildCustomer(), buildCustomer());

    when(mockCustomerService.parseCsv(mockBufferedReader)).thenReturn(customers);

    HttpResponse mockResponse = Mockito.mock(HttpResponse.class);
    when(mockResponse.body()).thenReturn("Customer Details Saved Successfully");
    when(mockHttpClient.send(any(), any())).thenReturn(mockResponse);
    postalService.sendCustomerCsvFile(mockBufferedReader);

    verify(mockHttpClient, times(customers.size())).send(any(), any());
  }

  @Test
  public void getCustomerDetailsByReferenceTest() throws IOException, InterruptedException {
    CustomerService mockCustomerService = Mockito.mock(CustomerService.class);
    HttpClient mockHttpClient = Mockito.mock(HttpClient.class);
    PostalService postalService = new PostalService(mockCustomerService, mockHttpClient);

    HttpResponse mockResponse = Mockito.mock(HttpResponse.class);
    when(mockResponse.body()).thenReturn("Mocked Response Body");

    when(mockHttpClient.send(any(), any())).thenReturn(mockResponse);
    postalService.getCustomerDetailsByReference(123L);

    verify(mockHttpClient, times(1)).send(any(HttpRequest.class), any());
  }

  private Customer buildCustomer() {
    return mock(Customer.class);
  }
}