package persistence;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import status.FontSize;

public class Options {

  ArrayList<FontSize> enorme = new ArrayList<FontSize>();
  ArrayList<FontSize> grande = new ArrayList<FontSize>();
  ArrayList<FontSize> medio = new ArrayList<FontSize>();
  ArrayList<FontSize> pequeno = new ArrayList<FontSize>();

  @SuppressWarnings("resource")
  public ArrayList<FontSize> fillOptions(int type) {
    try {
      String[] array = new String[5];

      FileReader file = new FileReader(/*
                                        * System.getProperty("user.dir") + "/fontSize.txt"
                                        */"fontSize.txt");
      BufferedReader read = new BufferedReader(file);

      String readLine = "";
      int i = 0;
      while ((readLine = read.readLine()) != null) {
        array = readLine.split(" ");
        enorme.add(new FontSize(array[i], array[i + type]));
      }
    } catch (Exception e) {
      System.err.println("File not found");
    }
    return enorme;
  }

}
