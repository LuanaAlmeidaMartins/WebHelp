package buttons;

import javafx.concurrent.Worker.State;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import persistence.ColorConverter;
import status.RulerStatus;
import webhelp.WebHelpStyle;

//import org.w3c.dom.Document;

public class Ruler extends HBox {
  
  private WebView webView;
  private WebEngine webEngine;
  private Canvas ruler;
  
  private SplitMenuButton rulerButton;
  private RadioMenuItem small, medium, big, enormous;

  
  public Ruler(WebView webView, Canvas ruler) {
    this.webView = webView;
    this.webEngine = webView.getEngine();
    this.ruler = ruler;
    createButton();
    actionButton();
    
  }
  
  private void actionButton() {
    RulerStatus rulerStatus = new RulerStatus();
    
    webEngine.getLoadWorker().stateProperty().addListener((obs, oldValue, newValue) -> {
      if (newValue == State.SUCCEEDED) {
            rulerButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
              @Override
              public void handle(MouseEvent event) {
            
               rulerStatus.setStatusMenu();
               createruler(rulerStatus.getStringToAdd(2), rulerStatus.isActive());
               
              }
            });

            small.setOnAction(actionEvent -> {
              rulerStatus.setStatusSubMenu();
              createruler(rulerStatus.getStringToAdd(1), rulerStatus.isActive());
            });

            medium.setOnAction(actionEvent -> {
              rulerStatus.setStatusSubMenu();
              createruler(rulerStatus.getStringToAdd(2), rulerStatus.isActive());
            });

            big.setOnAction(actionEvent -> {
              rulerStatus.setStatusSubMenu();
              createruler(rulerStatus.getStringToAdd(3), rulerStatus.isActive());
            });

            enormous.setOnAction(actionEvent -> {
              rulerStatus.setStatusSubMenu();
              rulerStatus.isActive();
              createruler(rulerStatus.getStringToAdd(4), rulerStatus.isActive());
            });
          }
        });
  }

  private void createButton() {
    small = new RadioMenuItem("Pequeno");
    medium = new RadioMenuItem("Médio");
    big = new RadioMenuItem("Grande");
    enormous = new RadioMenuItem("Enorme");

    rulerButton = new SplitMenuButton();
    rulerButton.setId("ruler");
    rulerButton.getItems().addAll(small, medium, big, enormous);

    ToggleGroup paragraphSpacingGroup = new ToggleGroup();
    paragraphSpacingGroup.getToggles().addAll(small, medium, big, enormous);

    WebHelpStyle webHelpStyle = new WebHelpStyle();
    webHelpStyle.estilo(rulerButton);

    this.getChildren().addAll(rulerButton);
    
  }

  public void createruler(double num, boolean actived) {
    System.out.println(actived);
    
//    System.out.println(ruler.getHeight());
//    )ruler.setHeight(300);
//    System.out.println(ruler.getHeight());
    
      GraphicsContext gc = ruler.getGraphicsContext2D();
      ruler.setOpacity(0.8);
      gc.setFill(Color.color(0.0,0.0,0.0));
      
      if(actived==true) {
      gc.fillRect(0,-20, 1600,700);
      gc.clearRect(0,80, 1600,num);
     
    }else {
      gc.clearRect(0,-20, 1600,700);
    }
      gc.fill();
  }
  
}
