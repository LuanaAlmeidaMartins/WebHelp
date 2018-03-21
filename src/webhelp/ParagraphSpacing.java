package webhelp;

import javafx.concurrent.Worker.State;
import javafx.event.EventHandler;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import org.w3c.dom.Document;

import util.ApplyButtonStatus;
import util.LineSpacingStatus.LineSpacingEnum;
import util.ParagraphSpacingStatus;
import util.ParagraphSpacingStatus.ParagraphSpacingEnum;

public class ParagraphSpacing extends HBox {

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
        WebHelpBar.applyButtonStatus = new ApplyButtonStatus(doc);

        paragraphSpacing.setOnMouseClicked(new EventHandler<MouseEvent>() {
          @Override
          public void handle(MouseEvent event) {
            spacingStatus.setParagraphSpacing();
            WebHelpBar.applyButtonStatus.setFontStyle(
                spacingStatus.getParagraphSpacing(LineSpacingEnum.none.getLineSpacing()),
                spacingStatus.isParagraphSpacing());
          }
        });

        simpleParagraph.setOnAction(actionEvent -> {
          spacingStatus.setOptionParagraphSpacing();
          WebHelpBar.applyButtonStatus.removeFontStyle(spacingStatus.getRemoveOption());
          WebHelpBar.applyButtonStatus.setFontStyle(
              spacingStatus
                  .getParagraphSpacing(ParagraphSpacingEnum.simpleParagraph.getParagraphSpacing()),
              spacingStatus.isParagraphSpacing());
        });

        paragraph15.setOnAction(actionEvent -> {
          spacingStatus.setOptionParagraphSpacing();
          WebHelpBar.applyButtonStatus.removeFontStyle(spacingStatus.getRemoveOption());
          WebHelpBar.applyButtonStatus.setFontStyle(
              spacingStatus
                  .getParagraphSpacing(ParagraphSpacingEnum.paragraph15.getParagraphSpacing()),
              spacingStatus.isParagraphSpacing());
        });

        paragraph5.setOnAction(actionEvent -> {
          spacingStatus.setOptionParagraphSpacing();
          WebHelpBar.applyButtonStatus.removeFontStyle(spacingStatus.getRemoveOption());
          WebHelpBar.applyButtonStatus.setFontStyle(
              spacingStatus
                  .getParagraphSpacing(ParagraphSpacingEnum.paragraph5.getParagraphSpacing()),
              spacingStatus.isParagraphSpacing());
        });

        doubleParagraph.setOnAction(actionEvent -> {
          spacingStatus.setOptionParagraphSpacing();
          WebHelpBar.applyButtonStatus.removeFontStyle(spacingStatus.getRemoveOption());
          WebHelpBar.applyButtonStatus.setFontStyle(
              spacingStatus
                  .getParagraphSpacing(ParagraphSpacingEnum.doubleParagraph.getParagraphSpacing()),
              spacingStatus.isParagraphSpacing());
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
