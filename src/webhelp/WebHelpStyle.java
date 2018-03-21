package webhelp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class WebHelpStyle {

  Properties property = new Properties();

  /**
   * Construtor carrega o arquivo properties com o estilo do MenuBar
   */
  public WebHelpStyle() {
    try {
      property.load(new FileInputStream(/*
                                         * System.getProperty("user.dir") + "/styleProperties.txt"
                                         */"styleProperties.txt"));
    } catch (FileNotFoundException e) {
      System.out.println("Arquivo styleProperties.txt nao encontrado");
    } catch (IOException e) {
      System.out.println("IO");
    }
  }

  /**
   * Insere o icone no menu e as propriedades de cada subitem do menu
   */
  public void estilo(SplitMenuButton menu) {
    for (int i = 0; i < menu.getItems().size(); i++) {
      menu.getItems().get(i)
          .setStyle(property.getProperty(menu.getItems().get(i).getText().replaceAll("\\s+", ".")));
      if (property.getProperty(menu.getItems().get(i).getText().replaceAll("\\s+", ".")) == null) {
        menu.getItems().get(i).setStyle(property.getProperty("default"));
      }
    }
    File file = new File(property.getProperty(menu.getId()));
    Image image = new Image(file.toURI().toString(), 28, 28, false, false);
    menu.setGraphic(new ImageView(image));
  }

  public void estilo(Button button) {
    File file = new File(property.getProperty(button.getId()));
    Image image = new Image(file.toURI().toString(), 28, 28, false, false);
    button.setGraphic(new ImageView(image));
  }

  public void estilo(ColorPicker colorPicker) {
    colorPicker.setPrefHeight(36);
    colorPicker.setPrefWidth(68);
    colorPicker.getStyleClass().addAll("color-picker", "split-button");
    colorPicker.setStyle(property.getProperty(colorPicker.getId()));
  }
}
