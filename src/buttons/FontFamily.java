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
import status.FontFamilyStatus;
import webhelp.WebHelpStyle;

import org.w3c.dom.Document;

import apply.ApplyStyle;

public class FontFamily extends HBox implements Types{

  private WebEngine webEngine;
  private SplitMenuButton fontFamilyMenu;
  private RadioMenuItem openDyslexicItem, openSansItem, comicSansItem, georgiaitem;

  public FontFamily(WebView webView) {
    this.webEngine = webView.getEngine();
    createButton();
    actionButton();
  }

  private void actionButton() {
    FontFamilyStatus fontFamilyStatus = new FontFamilyStatus();

    webEngine.getLoadWorker().stateProperty().addListener((obs, oldValue, newValue) -> {
      if (newValue == State.SUCCEEDED) {
        Document doc = webEngine.getDocument();
        ApplyStyle fontFamilyStyle = new ApplyStyle(doc);

        fontFamilyMenu.setOnMouseClicked(new EventHandler<MouseEvent>() {
          @Override
          public void handle(MouseEvent event) {
            fontFamilyStatus.setStatusMenu();
            fontFamilyStyle.addFontStyle(
                fontFamilyStatus.getStringToAdd(none),
                fontFamilyStatus.isActive());
            fontFamilyStyle.applyStyle();
          }
        });

        openDyslexicItem.setOnAction(actionEvent -> {
          fontFamilyStatus.setStatusSubMenu();
          fontFamilyStyle.removeFontStyle(fontFamilyStatus.getStringToRemove());
          fontFamilyStyle.addFontStyle(
              fontFamilyStatus.getStringToAdd(openDyslexic),
              fontFamilyStatus.isActive());
          fontFamilyStyle.applyStyle();
        });

        openSansItem.setOnAction(actionEvent -> {
          fontFamilyStatus.setStatusSubMenu();
          fontFamilyStyle.removeFontStyle(fontFamilyStatus.getStringToRemove());
          fontFamilyStyle.addFontStyle(
              fontFamilyStatus.getStringToAdd(openSans),
              fontFamilyStatus.isActive());
          fontFamilyStyle.applyStyle();
        });

        comicSansItem.setOnAction(actionEvent -> {
          fontFamilyStatus.setStatusSubMenu();
          fontFamilyStyle.removeFontStyle(fontFamilyStatus.getStringToRemove());
          fontFamilyStyle.addFontStyle(
              fontFamilyStatus.getStringToAdd(comicSans),
              fontFamilyStatus.isActive());
          fontFamilyStyle.applyStyle();
        });

        georgiaitem.setOnAction(actionEvent -> {
          fontFamilyStatus.setStatusSubMenu();
          fontFamilyStyle.removeFontStyle(fontFamilyStatus.getStringToRemove());
          fontFamilyStyle.addFontStyle(
              fontFamilyStatus.getStringToAdd(georgia),
              fontFamilyStatus.isActive());
          fontFamilyStyle.applyStyle();
        });
      }
    });
    this.getChildren().add(fontFamilyMenu);
  }

  private void createButton() {
    openDyslexicItem = new RadioMenuItem("Open Dyslexic");
    openSansItem = new RadioMenuItem("Open Sans");
    comicSansItem = new RadioMenuItem("Comic Sans");
    georgiaitem = new RadioMenuItem("Georgia");

    fontFamilyMenu = new SplitMenuButton();
    fontFamilyMenu.setId("fontFamily");
    fontFamilyMenu.getItems().addAll(openDyslexicItem, openSansItem, comicSansItem, georgiaitem);

    ToggleGroup fontFamilyGroup = new ToggleGroup();
    fontFamilyGroup.getToggles().addAll(openDyslexicItem, openSansItem, comicSansItem, georgiaitem);

    WebHelpStyle webHelpStyle = new WebHelpStyle();
    webHelpStyle.estilo(fontFamilyMenu);
  }
}
