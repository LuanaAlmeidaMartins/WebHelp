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
import status.CharSpacingStatus;
import webhelp.WebHelpStyle;

import org.w3c.dom.Document;

import apply.ApplyStyle;

public class CharSpacing extends HBox implements Types {

  private WebEngine webEngine;
  private SplitMenuButton charSpacing;
  private RadioMenuItem smallItem, mediumItem, bigItem;

  public CharSpacing(WebView webView) {
    this.webEngine = webView.getEngine();
    createButton();
    actionButton();
  }

  private void actionButton() {
    CharSpacingStatus charSpacingStatus = new CharSpacingStatus();

    webEngine.getLoadWorker().stateProperty().addListener((obs, oldValue, newValue) -> {
      if (newValue == State.SUCCEEDED) {
        Document doc = webEngine.getDocument();
        ApplyStyle charStyle = new ApplyStyle(doc);

        charSpacing.setOnMouseClicked(new EventHandler<MouseEvent>() {
          @Override
          public void handle(MouseEvent event) {
            charSpacingStatus.setStatusMenu();
            charStyle.addFontStyle(
                charSpacingStatus.getStringToAdd(none),
                charSpacingStatus.isActive());
            charStyle.applyStyle();
          }
        });

        smallItem.setOnAction(actionEvent -> {
          charSpacingStatus.setStatusSubMenu();
          charStyle.removeFontStyle(charSpacingStatus.getStringToRemove());
          charStyle.addFontStyle(
              charSpacingStatus.getStringToAdd(small),
              charSpacingStatus.isActive());
          charStyle.applyStyle();
        });

        mediumItem.setOnAction(actionEvent -> {
          charSpacingStatus.setStatusSubMenu();
          charStyle.removeFontStyle(charSpacingStatus.getStringToRemove());
          charStyle.addFontStyle(
              charSpacingStatus.getStringToAdd(medium),
              charSpacingStatus.isActive());
          charStyle.applyStyle();
        });

        bigItem.setOnAction(actionEvent -> {
          charSpacingStatus.setStatusSubMenu();
          charStyle.removeFontStyle(charSpacingStatus.getStringToRemove());
          charStyle.addFontStyle(
              charSpacingStatus.getStringToAdd(big),
              charSpacingStatus.isActive());
          charStyle.applyStyle();
        });
      }
    });
    this.getChildren().add(charSpacing);
  }

  private void createButton() {
    smallItem = new RadioMenuItem("Pequeno");
    mediumItem = new RadioMenuItem("M é d i o");
    bigItem = new RadioMenuItem("G  r  a  n  d  e");

    charSpacing = new SplitMenuButton();
    charSpacing.setId("charSpacing");
    charSpacing.getItems().addAll(smallItem, mediumItem, bigItem);

    ToggleGroup charSpacingGroup = new ToggleGroup();
    charSpacingGroup.getToggles().addAll(smallItem, mediumItem, bigItem);

    WebHelpStyle webHelpStyle = new WebHelpStyle();
    webHelpStyle.estilo(charSpacing);
  }
}
