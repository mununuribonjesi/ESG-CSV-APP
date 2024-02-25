package org.esg.services;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import org.esg.domain.Customer;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;

public class CustomerServiceTest {

  @DisplayName("It should return the correct customer object")
  @Test
  public void testParseCustomerCsv() throws IOException {

      Parser parser = new Parser();
      CustomerService customerService = new CustomerService(parser);
      BufferedReader br = new BufferedReader(new FileReader("src/main/resources/customer1.csv"));

      List<Customer> actual = customerService.parseCsv(br);
      Assertions.assertEquals(1, actual.size());
  }
}