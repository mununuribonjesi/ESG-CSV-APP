package org.esg.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Parser {

  public <T> List<T> parseCsv(BufferedReader bufferedReader, Function<String[], T> mappingFunction)
    throws IOException {

    List<T> result = new ArrayList<>();

    String line;

    bufferedReader.readLine();

    while ((line = bufferedReader.readLine()) != null) {
      String[] csvValues = line.split(",");
      T object = mappingFunction.apply(csvValues);
      result.add(object);
    }

    return result;
  }
}
