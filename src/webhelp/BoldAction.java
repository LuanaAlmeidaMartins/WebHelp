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
import util.BoldStatus;

public class BoldAction extends HBox {

  WebEngine webEngine;
  Button bold;

  public BoldAction(WebView webView) {
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
        WebHelpBar.applyButtonStatus = new ApplyButtonStatus(doc);

        bold.setOnMouseClicked(new EventHandler<MouseEvent>() {
          @Override
          public void handle(MouseEvent event) {
            boldStatus.setBold();
            WebHelpBar.applyButtonStatus.setFontStyle(boldStatus.getBold(), boldStatus.isBold());
          }
        });
      }
    });

    this.getChildren().add(bold);
  }

}
