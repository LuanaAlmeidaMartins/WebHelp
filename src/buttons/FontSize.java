package buttons;

import javafx.concurrent.Worker.State;
import javafx.event.EventHandler;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import status.FontSizeStatus;
import webhelp.WebHelpStyle;

import org.w3c.dom.Document;

import apply.ApplyStyle;;

public class FontSize extends HBox implements Types{

  private WebEngine webEngine;
  private SplitMenuButton fontSize;
  private RadioMenuItem smallItem, mediumItem, bigItem, hugeItem;

  public FontSize(WebView webView) {
    this.webEngine = webView.getEngine();
    createButton();
    actionButton();
  }

  private void actionButton() {
    FontSizeStatus fontSizeStatus = new FontSizeStatus();

    webEngine.getLoadWorker().stateProperty().addListener((obs, oldValue, newValue) -> {
      if (newValue == State.SUCCEEDED) {
        Document doc = webEngine.getDocument();
        ApplyStyle sizeStyle = new ApplyStyle(doc);

        fontSize.setOnMouseClicked(new EventHandler<MouseEvent>() {
          @Override
          public void handle(MouseEvent event) {
            fontSizeStatus.setStatusMenu();
            sizeStyle.addFontStyle(
                fontSizeStatus.getArrayStringToAdd(none),
                fontSizeStatus.isActive());
            sizeStyle.applyStyle();
          }
        });

        hugeItem.setOnAction(actionEvent -> {
          fontSizeStatus.setStatusSubMenu();
          sizeStyle.addFontStyle(
              fontSizeStatus.getArrayStringToAdd(huge),
              fontSizeStatus.isActive());
          sizeStyle.applyStyle();
        });

        bigItem.setOnAction(actionEvent -> {
          fontSizeStatus.setStatusSubMenu();
          sizeStyle.addFontStyle(
              fontSizeStatus.getArrayStringToAdd(big),
              fontSizeStatus.isActive());
          sizeStyle.applyStyle();
        });

        mediumItem.setOnAction(actionEvent -> {
          fontSizeStatus.setStatusSubMenu();
          sizeStyle.addFontStyle(
              fontSizeStatus.getArrayStringToAdd(medium),
              fontSizeStatus.isActive());
          sizeStyle.applyStyle();
        });

        smallItem.setOnAction(actionEvent -> {
          fontSizeStatus.setStatusSubMenu();
          sizeStyle.addFontStyle(
              fontSizeStatus.getArrayStringToAdd(small),
              fontSizeStatus.isActive());
          sizeStyle.applyStyle();
        });
      }
    });

    this.getChildren().add(fontSize);
  }

  void createButton() {
    smallItem = new RadioMenuItem("Pequeno");
    mediumItem = new RadioMenuItem("M�dio");
    bigItem = new RadioMenuItem("Grande");
    hugeItem = new RadioMenuItem("Enorme");

    fontSize = new SplitMenuButton();
    fontSize.setId("fontSize");
    fontSize.getItems().addAll(smallItem, mediumItem, bigItem, hugeItem);

    ToggleGroup fontSizeGroup = new ToggleGroup();
    fontSizeGroup.getToggles().addAll(smallItem, mediumItem, bigItem, hugeItem);

    WebHelpStyle webHelpStyle = new WebHelpStyle();
    webHelpStyle.estilo(fontSize);
  }
}
