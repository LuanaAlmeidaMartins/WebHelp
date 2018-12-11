package buttons;

import javafx.concurrent.Worker.State;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import status.UnderlineStatus;
import webhelp.WebHelpStyle;

import org.w3c.dom.Document;

import apply.ApplyStyle;

public class Underline extends HBox implements Types{

  WebEngine webEngine;
  Button underline;

  public Underline(WebView webView) {
    this.webEngine = webView.getEngine();
    createButton();
    actionButton();
  }

  void createButton() {
    underline = new Button();
    underline.setId("underline");

    WebHelpStyle webHelpStyle = new WebHelpStyle();
    webHelpStyle.estilo(underline);

  }

  public void actionButton() {
    UnderlineStatus underlineStatus = new UnderlineStatus();

    webEngine.getLoadWorker().stateProperty().addListener((obs, oldValue, newValue) -> {
      if (newValue == State.SUCCEEDED) {
        Document doc = webEngine.getDocument();
        ApplyStyle underlineStyle = new ApplyStyle(doc);

        underline.setOnMouseClicked(new EventHandler<MouseEvent>() {
          @Override
          public void handle(MouseEvent event) {
            underlineStatus.setStatusMenu();
            underlineStyle.addFontStyle(underlineStatus.getStringToAdd(none),
                underlineStatus.isActive());
            underlineStyle.applyStyle();
          }
        });
      }
    });
    this.getChildren().add(underline);
  }

}
