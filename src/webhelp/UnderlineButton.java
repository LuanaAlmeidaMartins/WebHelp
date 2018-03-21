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
import util.UnderlineStatus;

public class UnderlineButton extends HBox {

  WebEngine webEngine;
  Button underline;

  public UnderlineButton(WebView webView) {
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
        WebHelpBar.applyButtonStatus = new ApplyButtonStatus(doc);

        underline.setOnMouseClicked(new EventHandler<MouseEvent>() {
          @Override
          public void handle(MouseEvent event) {
            underlineStatus.setUnderline();
            WebHelpBar.applyButtonStatus.setFontStyle(underlineStatus.getUnderline(),
                underlineStatus.isUnderline());
          }
        });
      }
    });
    this.getChildren().add(underline);
  }

}
