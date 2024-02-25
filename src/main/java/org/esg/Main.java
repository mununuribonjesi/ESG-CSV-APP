package org.esg;

import java.io.IOException;
import java.net.http.HttpClient;
import java.util.Scanner;
import org.esg.handlers.CustomerActionHandler;
import org.esg.handlers.UserInputHandler;
import org.esg.services.CustomerService;
import org.esg.services.Parser;
import org.esg.services.PostalService;

public class Main {

  public static void main(String[] args) throws IOException, InterruptedException {
    Scanner scanner = new Scanner(System.in);
    char exitCharacter = 'Q';

    UserInputHandler userInputHandler = new UserInputHandler(scanner);
    Parser parser = new Parser();
    CustomerService customerService = new CustomerService(parser);
    PostalService postalService = new PostalService(customerService, HttpClient.newHttpClient());

    CustomerActionHandler customerActionHandler = new CustomerActionHandler(postalService);
    Main main = new Main(userInputHandler, customerActionHandler);

    main.run(exitCharacter);
  }

  private final UserInputHandler userInputHandler;
  private final CustomerActionHandler customerActionHandler;

  public Main(UserInputHandler userInputHandler, CustomerActionHandler customerActionHandler) {
    this.userInputHandler = userInputHandler;
    this.customerActionHandler = customerActionHandler;
  }

  public void run(char exitCharacter) throws IOException, InterruptedException {
    while (true) {
      char choice = userInputHandler.getChoice(exitCharacter);

      if (choice == exitCharacter) {
        System.out.println("Exiting...");
        break;
      }

      customerActionHandler.handleAction(choice);
    }
  }
}
