package org.esg.services;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

public class ParserTest {

  @Test
  @DisplayName("The parser should return the correct number of results for any csv file")
  public void parseCsv() throws IOException, IOException {

    Parser parser = new Parser();
    BufferedReader br = new BufferedReader(new FileReader("src/main/resources/customer.csv"));
    List<String[]> results = parser.parseCsv(br, csvValues -> csvValues);
    int expectedSize = 3;

    assertEquals(results.size(), expectedSize);
  }
}