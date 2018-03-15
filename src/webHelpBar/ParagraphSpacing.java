package webHelpBar;

import org.w3c.dom.Document;

import javafx.concurrent.Worker.State;
import javafx.event.EventHandler;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import util.ApplyButtonStatus;
import util.LineSpacingStatus.LineSpacingEnum;
import util.ParagraphSpacingStatus;
import util.ParagraphSpacingStatus.ParagraphSpacingEnum;

public class ParagraphSpacing extends HBox{
	
	private WebEngine webEngine;
	private SplitMenuButton paragraphSpacing;
	private RadioMenuItem simpleParagraph, paragraph15, paragraph5, doubleParagraph;

	public ParagraphSpacing(WebView webView) {
		this.webEngine = webView.getEngine();
		createButton();
		actionButton();
	}

	private void actionButton() {
		ParagraphSpacingStatus paragraphSpacingStatus = new ParagraphSpacingStatus();
		webEngine.getLoadWorker().stateProperty().addListener((obs, oldValue, newValue) -> {
			if (newValue == State.SUCCEEDED) {
				Document doc = webEngine.getDocument();
				WebHelpBar.applyButtonStatus = new ApplyButtonStatus(doc);
				
				paragraphSpacing.setOnMouseClicked(new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {
						paragraphSpacingStatus.setParagraphSpacing();
						WebHelpBar.applyButtonStatus.setFontStyle(paragraphSpacingStatus.getParagraphSpacing(LineSpacingEnum.none.getLineSpacing()),
								paragraphSpacingStatus.isParagraphSpacing());
					}
				});

				simpleParagraph.setOnAction(actionEvent -> {
					paragraphSpacingStatus.setOptionParagraphSpacing();
					WebHelpBar.applyButtonStatus.setFontStyle(paragraphSpacingStatus.getParagraphSpacing(ParagraphSpacingEnum.simpleParagraph.getParagraphSpacing()),
							paragraphSpacingStatus.isParagraphSpacing());
				});
				
				paragraph15.setOnAction(actionEvent -> {
					paragraphSpacingStatus.setOptionParagraphSpacing();
					WebHelpBar.applyButtonStatus.setFontStyle(paragraphSpacingStatus.getParagraphSpacing(ParagraphSpacingEnum.paragraph15.getParagraphSpacing()),
							paragraphSpacingStatus.isParagraphSpacing());
				});
				
				paragraph5.setOnAction(actionEvent -> {
					paragraphSpacingStatus.setOptionParagraphSpacing();
					WebHelpBar.applyButtonStatus.setFontStyle(paragraphSpacingStatus.getParagraphSpacing(ParagraphSpacingEnum.paragraph5.getParagraphSpacing()),
							paragraphSpacingStatus.isParagraphSpacing());
				});
				
				doubleParagraph.setOnAction(actionEvent -> {
					paragraphSpacingStatus.setOptionParagraphSpacing();
					WebHelpBar.applyButtonStatus.setFontStyle(paragraphSpacingStatus.getParagraphSpacing(ParagraphSpacingEnum.doubleParagraph.getParagraphSpacing()),
							paragraphSpacingStatus.isParagraphSpacing());
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
		paragraphSpacingGroup.getToggles().addAll(simpleParagraph, paragraph15, paragraph5, doubleParagraph);
		
		MenuWebHelpStyle webHelpStyle = new MenuWebHelpStyle();
		webHelpStyle.estilo(paragraphSpacing);
	}
	
	
}
