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
import status.LineSpacingStatus;
import webhelp.WebHelpStyle;

import org.w3c.dom.Document;

import apply.ApplyStyle;

public class LineSpacing extends HBox implements Types{

  private WebEngine webEngine;
  private RadioMenuItem simpleLine, line115, line15, doubleLine;
  private SplitMenuButton lineSpacingMenu;

  public LineSpacing(WebView webView) {
    this.webEngine = webView.getEngine();
    createButton();
    actionButton();
  }

  void createButton() {
    lineSpacingMenu = new SplitMenuButton();
    lineSpacingMenu.setId("lineSpacing");
    simpleLine = new RadioMenuItem("Simples");
    line115 = new RadioMenuItem("1.15");
    line15 = new RadioMenuItem("1.5");
    doubleLine = new RadioMenuItem("Duplo");
    lineSpacingMenu.getItems().addAll(simpleLine, line115, line15, doubleLine);

    ToggleGroup lineSpacingGroup = new ToggleGroup();
    lineSpacingGroup.getToggles().addAll(simpleLine, line115, line15, doubleLine);

    WebHelpStyle webHelpStyle = new WebHelpStyle();
    webHelpStyle.estilo(lineSpacingMenu);
  }

  public void actionButton() {
    LineSpacingStatus lineSpacingStatus = new LineSpacingStatus();

    webEngine.getLoadWorker().stateProperty().addListener((obs, oldValue, newValue) -> {
      if (newValue == State.SUCCEEDED) {
        Document doc = webEngine.getDocument();
        ApplyStyle spacingStyle = new ApplyStyle(doc);

        lineSpacingMenu.setOnMouseClicked(new EventHandler<MouseEvent>() {
          @Override
          public void handle(MouseEvent event) {
            lineSpacingStatus.setStatusMenu();
            spacingStyle.addFontStyle(
                lineSpacingStatus.getStringToAdd(none),
                lineSpacingStatus.isActive());
            spacingStyle.applyStyle();
          }
        });

        simpleLine.setOnAction(actionEvent -> {
          lineSpacingStatus.setStatusSubMenu();
          spacingStyle.removeFontStyle(lineSpacingStatus.getStringToRemove());
          spacingStyle.addFontStyle(
              lineSpacingStatus.getStringToAdd(simpleSpacing),
              lineSpacingStatus.isActive());
          spacingStyle.applyStyle();
        });

        line115.setOnAction(actionEvent -> {
          lineSpacingStatus.setStatusSubMenu();
          spacingStyle.removeFontStyle(lineSpacingStatus.getStringToRemove());
          spacingStyle.addFontStyle(
              lineSpacingStatus.getStringToAdd(spacing115),
              lineSpacingStatus.isActive());
          spacingStyle.applyStyle();
        });

        line15.setOnAction(actionEvent -> {
          lineSpacingStatus.setStatusSubMenu();
          spacingStyle.removeFontStyle(lineSpacingStatus.getStringToRemove());
          spacingStyle.addFontStyle(
              lineSpacingStatus.getStringToAdd(spacing15),
              lineSpacingStatus.isActive());
          spacingStyle.applyStyle();
        });

        doubleLine.setOnAction(actionEvent -> {
          lineSpacingStatus.setStatusSubMenu();
          spacingStyle.removeFontStyle(lineSpacingStatus.getStringToRemove());
          spacingStyle.addFontStyle(
              lineSpacingStatus.getStringToAdd(doubleSpacing),
              lineSpacingStatus.isActive());
          spacingStyle.applyStyle();
        });
      }
    });
    this.getChildren().add(lineSpacingMenu);
  }

}
