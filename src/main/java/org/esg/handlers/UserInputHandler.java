package org.esg.handlers;

import java.util.Scanner;

public class UserInputHandler {
  private final Scanner scanner;

  public UserInputHandler(Scanner scanner) {
    this.scanner = scanner;
  }

  public char getChoice(char exitCharacter) {
    System.out.println("Select an action:");
    System.out.println("1. Process CSV file");
    System.out.println("2. Get Customer Details by Ref");
    System.out.println("Enter '" + exitCharacter + "' to exit");
    return scanner.nextLine().toUpperCase().charAt(0);
  }
}
