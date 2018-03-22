package buttons;

import javafx.concurrent.Worker.State;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import webhelp.WebHelpStyle;

//import org.w3c.dom.Document;

public class Ruler extends HBox {

  WebEngine webEngine;
  private Button ruler;

  public Ruler(WebView webView) {
    this.webEngine = webView.getEngine();
    createButton();
    actionButton();

  }

  private void actionButton() {
    webEngine.getLoadWorker().stateProperty().addListener((obs, oldValue, newValue) -> {
      if (newValue == State.SUCCEEDED) {
        //Document doc = webEngine.getDocument();

        ruler.setOnMouseClicked(new EventHandler<MouseEvent>() {
          @Override
          public void handle(MouseEvent event) {

          }
        });
      }
    });

    this.getChildren().add(ruler);
  }

  private void createButton() {
    ruler = new Button();
    ruler.setId("ruler");

    WebHelpStyle webHelpStyle = new WebHelpStyle();
    webHelpStyle.estilo(ruler);
  }
}
