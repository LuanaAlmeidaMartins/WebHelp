package webhelp;

import javafx.concurrent.Worker.State;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import org.w3c.dom.Document;

import util.ApplyButtonStatus;
import util.ItalicStatus;

public class Italic extends HBox {

  WebEngine webEngine;
  Button italic;

  public Italic(WebView webView) {
    this.webEngine = webView.getEngine();
    createButton();
    actionButton();
  }

  void createButton() {
    italic = new Button();
    italic.setId("italic");

    WebHelpStyle webHelpStyle = new WebHelpStyle();
    webHelpStyle.estilo(italic);
  }

  public void actionButton() {

    ItalicStatus italicStatus = new ItalicStatus();

    webEngine.getLoadWorker().stateProperty().addListener((obs, oldValue, newValue) -> {
      if (newValue == State.SUCCEEDED) {
        Document doc = webEngine.getDocument();
        WebHelpBar.applyButtonStatus = new ApplyButtonStatus(doc);

        italic.setOnMouseClicked(new EventHandler<MouseEvent>() {
          @Override
          public void handle(MouseEvent event) {
            italicStatus.setItalic();
            WebHelpBar.applyButtonStatus.setFontStyle(italicStatus.getItalic(),
                italicStatus.isItalic());
          }
        });
      }
    });
    this.getChildren().add(italic);
  }

}
