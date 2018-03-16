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
import util.CharSpacingStatus;
import util.CharSpacingStatus.CharSpacingEnum;

public class CharSpacing extends HBox{

	private WebEngine webEngine;
	private SplitMenuButton charSpacing;
	private RadioMenuItem small, medium, big;

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
				WebHelpBar.applyButtonStatus = new ApplyButtonStatus(doc);

				charSpacing.setOnMouseClicked(new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {
						charSpacingStatus.setCharSpacing();
						WebHelpBar.applyButtonStatus.setFontStyle(charSpacingStatus.getCharSpacing(CharSpacingEnum.none.getCharSpacing()),
								charSpacingStatus.isCharSpacing());
					}
				});

				small.setOnAction(actionEvent -> {
					charSpacingStatus.setOptionCharSpacing();
					WebHelpBar.applyButtonStatus.removeFontStyle(charSpacingStatus.getRemoveOption());
					WebHelpBar.applyButtonStatus.setFontStyle(charSpacingStatus.getCharSpacing(CharSpacingEnum.small.getCharSpacing()),
							charSpacingStatus.isCharSpacing());
				});

				medium.setOnAction(actionEvent -> {
					charSpacingStatus.setOptionCharSpacing();
					WebHelpBar.applyButtonStatus.removeFontStyle(charSpacingStatus.getRemoveOption());
					WebHelpBar.applyButtonStatus.setFontStyle(charSpacingStatus.getCharSpacing(CharSpacingEnum.medium.getCharSpacing()),
							charSpacingStatus.isCharSpacing());
				});

				big.setOnAction(actionEvent -> {
					charSpacingStatus.setOptionCharSpacing();
					WebHelpBar.applyButtonStatus.removeFontStyle(charSpacingStatus.getRemoveOption());
					WebHelpBar.applyButtonStatus.setFontStyle(charSpacingStatus.getCharSpacing(CharSpacingEnum.big.getCharSpacing()),
							charSpacingStatus.isCharSpacing());
				});
			}
		});
		this.getChildren().add(charSpacing);
	}

	private void createButton() {	
		small = new RadioMenuItem("Pequeno");
		medium = new RadioMenuItem("M é d i o");
		big = new RadioMenuItem("G  r  a  n  d  e");

		charSpacing = new SplitMenuButton();
		charSpacing.setId("charSpacing");
		charSpacing.getItems().addAll(small, medium, big);

		ToggleGroup charSpacingGroup = new ToggleGroup();
		charSpacingGroup.getToggles().addAll(small, medium, big);

		MenuWebHelpStyle webHelpStyle = new MenuWebHelpStyle();
		webHelpStyle.estilo(charSpacing);	
	}
}
