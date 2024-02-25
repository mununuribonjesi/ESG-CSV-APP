package org.esg.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import org.esg.domain.Customer;


public class CustomerService {

  private final Parser parser;

  public CustomerService(Parser parser) {
    this.parser = parser;
  }

  public List<Customer> parseCsv(BufferedReader bufferedReader) throws IOException {

    return parser.parseCsv(bufferedReader, CustomerService::mapParserResultsToCustomer);
  }

  private static Customer mapParserResultsToCustomer(String[] csvValues) {

    String customerRef = csvValues[0].trim();
    String customerName = csvValues[1].trim();
    String addressLine1 = csvValues[2].trim();
    String addressLine2 = csvValues[3].trim();
    String town = csvValues[4].trim();
    String county = csvValues[5].trim();
    String country = csvValues[6].trim();
    String postcode = csvValues[7].trim();

    return new Customer(Long.parseLong(customerRef), customerName, addressLine1, addressLine2, town, county, country, postcode);
  }
}
