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
import status.ParagraphSpacingStatus;
import webhelp.WebHelpStyle;

import org.w3c.dom.Document;

import apply.ApplyStyle;

public class ParagraphSpacing extends HBox implements Types{

  private WebEngine webEngine;
  private SplitMenuButton paragraphSpacing;
  private RadioMenuItem simpleParagraph, paragraph15, paragraph5, doubleParagraph;

  public ParagraphSpacing(WebView webView) {
    this.webEngine = webView.getEngine();
    createButton();
    actionButton();
  }

  private void actionButton() {
    ParagraphSpacingStatus spacingStatus = new ParagraphSpacingStatus();
    
    webEngine.getLoadWorker().stateProperty().addListener((obs, oldValue, newValue) -> {
      if (newValue == State.SUCCEEDED) {
        Document doc = webEngine.getDocument();
        ApplyStyle spacingStyle = new ApplyStyle(doc);

        paragraphSpacing.setOnMouseClicked(new EventHandler<MouseEvent>() {
          
          @Override
          public void handle(MouseEvent event) {
            spacingStatus.setStatusMenu();
            spacingStyle.addFontStyle(
                spacingStatus.getStringToAdd(none),
                spacingStatus.isActive());
            spacingStyle.applyStyle();
          }
        });

        simpleParagraph.setOnAction(actionEvent -> {
          spacingStatus.setStatusSubMenu();
          spacingStyle.removeFontStyle(spacingStatus.getStringToRemove());
          spacingStyle.addFontStyle(
              spacingStatus
                  .getStringToAdd(simpleSpacing),
              spacingStatus.isActive());
          spacingStyle.applyStyle();
        });

        paragraph15.setOnAction(actionEvent -> {
          spacingStatus.setStatusSubMenu();
          spacingStyle.removeFontStyle(spacingStatus.getStringToRemove());
          spacingStyle.addFontStyle(
              spacingStatus
                  .getStringToAdd(spacing115),
              spacingStatus.isActive());
          spacingStyle.applyStyle();
        });

        paragraph5.setOnAction(actionEvent -> {
          spacingStatus.setStatusSubMenu();
          spacingStyle.removeFontStyle(spacingStatus.getStringToRemove());
          spacingStyle.addFontStyle(
              spacingStatus
                  .getStringToAdd(spacing15),
              spacingStatus.isActive());
          spacingStyle.applyStyle();
        });

        doubleParagraph.setOnAction(actionEvent -> {
          spacingStatus.setStatusSubMenu();
          spacingStyle.removeFontStyle(spacingStatus.getStringToRemove());
          spacingStyle.addFontStyle(
              spacingStatus
                  .getStringToAdd(doubleSpacing),
              spacingStatus.isActive());
          spacingStyle.applyStyle();
        });
      }
    });
    this.getChildren().add(paragraphSpacing);
  }

  private void createButton() {
    simpleParagraph = new RadioMenuItem("Simples");
    paragraph15 = new RadioMenuItem("1.15");
    paragraph5 = new RadioMenuItem("1.5");
    doubleParagraph = new RadioMenuItem("Duplo");

    paragraphSpacing = new SplitMenuButton();
    paragraphSpacing.setId("paragraphSpacing");
    paragraphSpacing.getItems().addAll(simpleParagraph, paragraph15, paragraph5, doubleParagraph);

    ToggleGroup paragraphSpacingGroup = new ToggleGroup();
    paragraphSpacingGroup.getToggles().addAll(simpleParagraph, paragraph15, paragraph5,
        doubleParagraph);

    WebHelpStyle webHelpStyle = new WebHelpStyle();
    webHelpStyle.estilo(paragraphSpacing);
  }

}
