package buttons;

import javafx.concurrent.Worker.State;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.RectangleBuilder;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import netscape.javascript.JSObject;
import persistence.ColorConverter;
import principal.Main;
import status.HighLightColorStatus;
import status.OverlayColorStatus;
import webhelp.WebHelpStyle;

public class Overlay extends HBox{
  
  private WebView webView;
  private WebEngine webEngine;
  private ColorPicker overlayButton;
  private StackPane stack ;
  
  public Overlay(WebView webView) {
    this.webView = webView;
    this.webEngine = webView.getEngine();
    createButton();
    actionButton();
    
  }
  
  private void actionButton() {
    OverlayColorStatus overlayColorStatus = new OverlayColorStatus();
    ColorConverter color = new ColorConverter();
    webEngine.getLoadWorker().stateProperty().addListener((obs, oldValue, newValue) -> {
      if (newValue == State.SUCCEEDED) {
        overlayButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
          @Override
          public void handle(MouseEvent e) {
            if ((e.getSceneX() >= 720 && e.getSceneX() <= 762)
                && (e.getSceneY() >= 50 && e.getSceneY() <= 82)) {
              overlayButton.hide();
              createOverlay(overlayColorStatus.getColor());
            } else {
              overlayButton.setOnAction((ActionEvent t) -> {
                overlayColorStatus.setColorName(color.converterColor(overlayButton.getValue()));
                createOverlay(overlayColorStatus.getColor());
              });
            }
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

  public Canvas createOverlay(String color) {
    System.out.println("color: "+color);
    Canvas overlay = new Canvas(1300,600);
    overlay.setOpacity(0.5);
    GraphicsContext gc = overlay.getGraphicsContext2D();
    gc.setFill(Color.RED);
    gc.fillRect(0,-20, 1600,700);
    gc.fill();
    
    stack.getChildren().addAll(webView, overlay);
    stack.setMargin(webView, new Insets(12, 12, 10, 28));
    
    return overlay;
  }
  
}
