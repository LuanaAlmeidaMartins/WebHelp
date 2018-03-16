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
import util.FontFamilyStatus;
import util.ApplyButtonStatus;
import util.FontFamilyStatus.FontFamilyEnum;

public class FontFamilyAction extends HBox{

	private WebEngine webEngine;
	private SplitMenuButton fontFamilyMenu;
	private RadioMenuItem openDyslexic, openSans, comicSans, georgia;
	
	
	public FontFamilyAction(WebView webView) {
		this.webEngine = webView.getEngine();
		createButton();
		actionButton();
	}

	private void actionButton() {
		FontFamilyStatus fontFamilyStatus = new FontFamilyStatus();
		
		webEngine.getLoadWorker().stateProperty().addListener((obs, oldValue, newValue) -> {
			if (newValue == State.SUCCEEDED) {
				Document doc = webEngine.getDocument();
				WebHelpBar.applyButtonStatus = new ApplyButtonStatus(doc);

				fontFamilyMenu.setOnMouseClicked(new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {
						fontFamilyStatus.setFontFamily();
						WebHelpBar.applyButtonStatus.setFontStyle(fontFamilyStatus.getFamilyFont(FontFamilyEnum.none.getFont()),
								fontFamilyStatus.isFontFamily());
					}
				});

				openDyslexic.setOnAction(actionEvent -> {
					WebHelpBar.applyButtonStatus.removeFontStyle(fontFamilyStatus.getRemoveOption());
					WebHelpBar.applyButtonStatus.setFontStyle(fontFamilyStatus.getFamilyFont(FontFamilyEnum.openDyslexic.getFont()),
							fontFamilyStatus.isFontFamily());
				});

				openSans.setOnAction(actionEvent -> {
					fontFamilyStatus.setOptionFamily();
					WebHelpBar.applyButtonStatus.removeFontStyle(fontFamilyStatus.getRemoveOption());
					WebHelpBar.applyButtonStatus.setFontStyle(fontFamilyStatus.getFamilyFont(FontFamilyEnum.openSans.getFont()),
							fontFamilyStatus.isFontFamily());
				});

				comicSans.setOnAction(actionEvent -> {
					fontFamilyStatus.setOptionFamily();
					WebHelpBar.applyButtonStatus.removeFontStyle(fontFamilyStatus.getRemoveOption());
					WebHelpBar.applyButtonStatus.setFontStyle(fontFamilyStatus.getFamilyFont(FontFamilyEnum.comicSans.getFont()),
							fontFamilyStatus.isFontFamily());
				});

				georgia.setOnAction(actionEvent -> {
					fontFamilyStatus.setOptionFamily();
					WebHelpBar.applyButtonStatus.removeFontStyle(fontFamilyStatus.getRemoveOption());
					WebHelpBar.applyButtonStatus.setFontStyle(fontFamilyStatus.getFamilyFont(FontFamilyEnum.georgia.getFont()),
							fontFamilyStatus.isFontFamily());
				});
			}});
		this.getChildren().add(fontFamilyMenu);
	}

	private void createButton() {
		openDyslexic = new RadioMenuItem("Open Dyslexic");
		openSans = new RadioMenuItem("Open Sans");
		comicSans = new RadioMenuItem("Comic Sans");
		georgia = new RadioMenuItem("Georgia");
		
		fontFamilyMenu = new SplitMenuButton();
		fontFamilyMenu.setId("fontFamily");
		fontFamilyMenu.getItems().addAll(openDyslexic, openSans, comicSans, georgia);
		
		ToggleGroup fontFamilyGroup = new ToggleGroup();
		fontFamilyGroup.getToggles().addAll(openDyslexic, openSans, comicSans, georgia);
		
		MenuWebHelpStyle webHelpStyle = new MenuWebHelpStyle();
		webHelpStyle.estilo(fontFamilyMenu);
	}
}
