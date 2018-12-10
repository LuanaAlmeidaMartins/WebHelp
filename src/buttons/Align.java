package buttons;

import javafx.concurrent.Worker.State;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import status.AlignStatus;
import webhelp.WebHelpStyle;

import org.w3c.dom.Document;

import apply.ApplyStyle;

public class Align extends HBox implements Types{

  WebEngine webEngine;
  Button align;

  public Align(WebView webView) {
    this.webEngine = webView.getEngine();
    createButton();
    actionButton();
//    "mbrola.base", "/home/luana/Desenvolvimento/Eclipse/workspace/Hello/mbrola"
  }

  void createButton() {
    align = new Button();
    align.setId("align");

    WebHelpStyle webHelpStyle = new WebHelpStyle();
    webHelpStyle.estilo(align);
  }

  public void actionButton() {

    AlignStatus alignStatus = new AlignStatus();

    webEngine.getLoadWorker().stateProperty().addListener((obs, oldValue, newValue) -> {
      if (newValue == State.SUCCEEDED) {
        Document doc = webEngine.getDocument();
        ApplyStyle apply = new ApplyStyle(doc);

        align.setOnMouseClicked(new EventHandler<MouseEvent>() {
          @Override
          public void handle(MouseEvent event) {
            alignStatus.setStatusMenu();
            apply.addFontStyle(alignStatus.getStringToAdd(none),
                alignStatus.isActive());
            apply.applyStyle();
          }
        });
      }
    });
    this.getChildren().add(align);
  }

}
