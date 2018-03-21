package webhelp;

import javafx.concurrent.Worker.State;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ColorPicker;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import org.w3c.dom.Document;

import persistence.ColorConverter;
import util.ApplyButtonStatus;
import util.HighLightColorStatus;

public class HighLight extends HBox {
  private WebEngine webEngine;
  private ColorPicker highlightButton;
  private WebView webView;

  public HighLight(WebView webView) {
    this.webView = webView;
    this.webEngine = webView.getEngine();
    createButton();
    actionButton();
  }

  public WebView atualiza() {
    return this.webView;
  }

  private void actionButton() {
    HighLightColorStatus highLightColorStatus = new HighLightColorStatus();
    ColorConverter color = new ColorConverter();
    webEngine.getLoadWorker().stateProperty().addListener((obs, oldValue, newValue) -> {
      if (newValue == State.SUCCEEDED) {
        Document doc = webEngine.getDocument();
        WebHelpBar.applyButtonStatus = new ApplyButtonStatus(doc);

        highlightButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
          @Override
          public void handle(MouseEvent e) {
            if ((e.getSceneX() >= 720 && e.getSceneX() <= 762)
                && (e.getSceneY() >= 50 && e.getSceneY() <= 82)) {
              highlightButton.hide();
              aplicarCor(highLightColorStatus.getColor());
            } else {
              highlightButton.setOnAction((ActionEvent t) -> {
                highLightColorStatus.setColorName(color.converterColor(highlightButton.getValue()));
                aplicarCor(highLightColorStatus.getColor());
              });
            }
          }
        });
      }
    });

  }

  private void aplicarCor(String string) {
    webView.getEngine().executeScript("var selection = window.getSelection();"
        + "var range = selection.getRangeAt(0);" + "var newNode = document.createElement(\"span\");"
        + "newNode.setAttribute(\"style\", \"background-color:" + string + ";\");"
        + "range.surroundContents(newNode); ");
  }

  private void createButton() {

    highlightButton = new ColorPicker();
    highlightButton.setId("highlight");

    WebHelpStyle webHelpStyle = new WebHelpStyle();
    webHelpStyle.estilo(highlightButton);

    this.getChildren().addAll(highlightButton);
  }
}
