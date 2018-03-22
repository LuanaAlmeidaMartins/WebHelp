package buttons;

import javafx.concurrent.Worker.State;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import status.BoldStatus;
import webhelp.WebHelpStyle;

import org.w3c.dom.Document;

import apply.ApplyStyle;

public class Bold extends HBox implements Types {

  WebEngine webEngine;
  Button bold;

  public Bold(WebView webView) {
    this.webEngine = webView.getEngine();
    createButton();
    actionButton();
  }

  void createButton() {
    bold = new Button();
    bold.setId("bold");
    WebHelpStyle webHelpStyle = new WebHelpStyle();
    webHelpStyle.estilo(bold);
  }

  public void actionButton() {

    BoldStatus boldStatus = new BoldStatus();

    webEngine.getLoadWorker().stateProperty().addListener((obs, oldValue, newValue) -> {
      if (newValue == State.SUCCEEDED) {
        Document doc = webEngine.getDocument();
        ApplyStyle boldStyle = new ApplyStyle(doc);

        bold.setOnMouseClicked(new EventHandler<MouseEvent>() {
          @Override
          public void handle(MouseEvent event) {
            boldStatus.setStatusMenu();
            boldStyle.addFontStyle(boldStatus.getStringToAdd(none),
                boldStatus.isActive());
            boldStyle.applyStyle();
          }
        });
      }
    });

    this.getChildren().add(bold);
  }

}
