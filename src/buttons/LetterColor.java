package buttons;

import javafx.concurrent.Worker.State;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ColorPicker;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import org.w3c.dom.Document;

import apply.ApplyStyle;
import persistence.ColorConverter;
import status.LetterColorStatus;
import webhelp.WebHelpStyle;

public class LetterColor extends HBox implements Types{

  WebEngine webEngine;
  private ColorPicker colorPicker;

  public LetterColor(WebView webView) {
    this.webEngine = webView.getEngine();
    createButton();
    actionButton();
  }

  private void actionButton() {
    LetterColorStatus letterColorStatus = new LetterColorStatus();
    ColorConverter color = new ColorConverter();

    webEngine.getLoadWorker().stateProperty().addListener((obs, oldValue, newValue) -> {
      if (newValue == State.SUCCEEDED) {
        Document doc = webEngine.getDocument();
        ApplyStyle letterStyle = new ApplyStyle(doc);

        colorPicker.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

          @Override
          public void handle(MouseEvent e) {

            if (!e.getPickResult().toString().contains("arrow-button")) {
              colorPicker.hide();
              letterColorStatus.setStatusMenu();
              letterStyle.addFontStyle(letterColorStatus.getStringToAdd(none),
                  letterColorStatus.isActive());
              letterStyle.applyStyle();
            }
          }
        });

        colorPicker.setOnAction((ActionEvent t) -> {
          letterColorStatus.setStatusSubMenu();
          letterStyle.removeFontStyle(letterColorStatus.getStringToRemove());
          letterColorStatus.setColorName(color.converterColor(colorPicker.getValue()));
          letterStyle.addFontStyle(letterColorStatus.getStringToAdd(none),
              letterColorStatus.isActive());
          letterStyle.applyStyle();
        });
      }
    });
  }

  private void createButton() {
    colorPicker = new ColorPicker();
    colorPicker.setId("letterColor");

    WebHelpStyle webHelpStyle = new WebHelpStyle();
    webHelpStyle.estilo(colorPicker);

    this.getChildren().add(colorPicker);
  }
}
