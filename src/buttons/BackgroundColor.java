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
import status.BackgroundStatus;
import webhelp.WebHelpStyle;

public class BackgroundColor extends HBox implements Types {

  WebEngine webEngine;
  private ColorPicker colorPickerBackground;

  public BackgroundColor(WebView webView) {
    this.webEngine = webView.getEngine();
    createButton();
    actionButton();
  }

  private void actionButton() {
    BackgroundStatus backgroundStatus = new BackgroundStatus();
    ColorConverter color = new ColorConverter();

    webEngine.getLoadWorker().stateProperty().addListener((obs, oldValue, newValue) -> {
      if (newValue == State.SUCCEEDED) {
        Document doc = webEngine.getDocument();
        ApplyStyle backgroundStyle = new ApplyStyle(doc);

        colorPickerBackground.addEventHandler(MouseEvent.MOUSE_CLICKED,
            new EventHandler<MouseEvent>() {
              @Override
              public void handle(MouseEvent e) {
                if (!e.getPickResult().toString().contains("arrow-button")) {
                  colorPickerBackground.hide();
                  backgroundStatus.setStatusMenu();
                  backgroundStyle.setBackgroundStyle(backgroundStatus.getStringToAdd(none),
                      backgroundStatus.isActive());
                  backgroundStyle.applyStyle();
                }
              }
            });

        colorPickerBackground.setOnAction((ActionEvent t) -> {
          backgroundStatus.setStatusSubMenu();
          backgroundStatus.setColorName(color.converterColor(colorPickerBackground.getValue()));
          backgroundStyle.setBackgroundStyle(backgroundStatus.getStringToAdd(none),
              backgroundStatus.isActive());
          backgroundStyle.applyStyle();
        });
      }
    });
    this.getChildren().add(colorPickerBackground);
  }

  private void createButton() {
    colorPickerBackground = new ColorPicker();
    colorPickerBackground.setId("background");

    WebHelpStyle webHelpStyle = new WebHelpStyle();
    webHelpStyle.estilo(colorPickerBackground);
  }
}
