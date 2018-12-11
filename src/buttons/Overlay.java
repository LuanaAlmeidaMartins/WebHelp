package buttons;

import javafx.concurrent.Worker.State;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import principal.Main;
import status.OverlayColorStatus;
import webhelp.WebHelpStyle;

public class Overlay extends HBox {

  private WebEngine webEngine;
  private ColorPicker overlayButton;
  private Canvas overlay;

  public Overlay(WebView webView, Canvas overlay) {
    this.webEngine = webView.getEngine();
    this.overlay = overlay;
    createButton();
    actionButton();
  }

  private void actionButton() {
    OverlayColorStatus overlayStatus = new OverlayColorStatus();

    webEngine.getLoadWorker().stateProperty().addListener((obs, oldValue, newValue) -> {
      if (newValue == State.SUCCEEDED) {
        
        overlayButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

          @Override
          public void handle(MouseEvent e) {
            if (!e.getPickResult().toString().contains("arrow-button")) {
              overlay.setVisible(true);
              overlayButton.hide();
              overlayStatus.setStatusMenu();
              createOverlay(overlayStatus, overlayStatus.isActive());
            }
            
            overlayButton.setOnAction((ActionEvent t) -> {
              overlay.setVisible(true);
              overlayStatus.setStatusSubMenu();
              overlayStatus.setColor(overlayButton.getValue());
              createOverlay(overlayStatus, overlayStatus.isActive());
            });
          }
        });
      }
    });
  }

  private void createButton() {
    overlayButton = new ColorPicker();
    overlayButton.setId("overlay");

    WebHelpStyle webHelpStyle = new WebHelpStyle();
    webHelpStyle.estilo(overlayButton);

    this.getChildren().addAll(overlayButton);
  }

  public void createOverlay(OverlayColorStatus color, boolean actived) {
    GraphicsContext gc = overlay.getGraphicsContext2D();
    overlay.setOpacity(0.5);
    
    if (actived == true) {
      gc.setFill(Color.color(color.getRed(), color.getGreen(), color.getBlue()));
      System.out.println(color.getRed()+" " +color.getGreen()+"  " +color.getBlue());
      gc.fillRect(0, -20, 1600, 700);
    } 
    else {
      gc.clearRect(0, -20, 1600, 700);
    }
    gc.fill();
  }

}
