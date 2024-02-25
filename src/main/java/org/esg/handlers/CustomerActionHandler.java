package org.esg.handlers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import org.esg.services.PostalService;

public class CustomerActionHandler {

    private final PostalService postalService;

    public CustomerActionHandler(PostalService postalService) {
      this.postalService = postalService;
    }

    public void handleAction(char choice) throws IOException, InterruptedException {
      switch (choice) {
        case '1':
          processCsvFile();
          break;
        case '2':
          Long customerRef = getCustomerReference();
          getCustomerDetailsByReference(customerRef);
          break;
        default:
          System.out.println("Invalid choice");
      }
    }

    private void processCsvFile() throws IOException {
      try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/customer.csv"))) {
        postalService.sendCustomerCsvFile(br);
      }
    }

    private void getCustomerDetailsByReference(Long customerRef) throws IOException, InterruptedException {
      postalService.getCustomerDetailsByReference(customerRef);
    }

    private Long getCustomerReference() {
      Scanner scanner = new Scanner(System.in);
      System.out.println("Enter customer reference:");
      return Long.parseLong(scanner.nextLine());
    }
}
