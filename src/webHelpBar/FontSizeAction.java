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
import util.FontSizeStatus;
import util.FontSizeStatus.FontSizeEnum;

public class FontSizeAction extends HBox{
	
	private WebEngine webEngine;
	private SplitMenuButton fontSize;
	private RadioMenuItem pequeno, medio, grande, enorme;
	
	
	public FontSizeAction(WebView webView) {
		this.webEngine = webView.getEngine();
		createButton();
		actionButton();
	}
	
	private void actionButton() {
		FontSizeStatus fontSizeStatus = new FontSizeStatus();
		
		webEngine.getLoadWorker().stateProperty().addListener((obs, oldValue, newValue) -> {
			if (newValue == State.SUCCEEDED) {
				Document doc = webEngine.getDocument();
				WebHelpBar.applyButtonStatus = new ApplyButtonStatus(doc);
				
				fontSize.setOnMouseClicked(new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {
						fontSizeStatus.setFontSize();
						WebHelpBar.applyButtonStatus.setFontStyle(fontSizeStatus.getSizeFont(FontSizeEnum.none.getFont()),
								fontSizeStatus.isFontSize());
					}
				});
				
				enorme.setOnAction(actionEvent -> {
					fontSizeStatus.setOptionSize();
					WebHelpBar.applyButtonStatus.setFontStyle(fontSizeStatus.getSizeFont(FontSizeEnum.enorme.getFont()),
							fontSizeStatus.isFontSize());
				});
		
				grande.setOnAction(actionEvent -> {
					fontSizeStatus.setOptionSize();
					WebHelpBar.applyButtonStatus.setFontStyle(fontSizeStatus.getSizeFont(FontSizeEnum.grande.getFont()),
							fontSizeStatus.isFontSize());
				});
		
				medio.setOnAction(actionEvent -> {
					fontSizeStatus.setOptionSize();
					WebHelpBar.applyButtonStatus.setFontStyle(fontSizeStatus.getSizeFont(FontSizeEnum.medio.getFont()),
							fontSizeStatus.isFontSize());
				});
		
				pequeno.setOnAction(actionEvent -> {
					fontSizeStatus.setOptionSize();
					WebHelpBar.applyButtonStatus.setFontStyle(fontSizeStatus.getSizeFont(FontSizeEnum.pequeno.getFont()),
							fontSizeStatus.isFontSize());
				});
			}
		});
		
		this.getChildren().add(fontSize);
	}

	void createButton(){
		pequeno = new RadioMenuItem("Pequeno");
		medio = new RadioMenuItem("Médio");
		grande = new RadioMenuItem("Grande");
		enorme = new RadioMenuItem("Enorme");
		
		fontSize = new SplitMenuButton();
		fontSize.setId("fontSize");
		fontSize.getItems().addAll(pequeno, medio, grande, enorme);
		
		ToggleGroup fontSizeGroup = new ToggleGroup();
		fontSizeGroup.getToggles().addAll(pequeno, medio, grande, enorme);
		
		MenuWebHelpStyle webHelpStyle = new MenuWebHelpStyle();
		webHelpStyle.estilo(fontSize);
	}
}
