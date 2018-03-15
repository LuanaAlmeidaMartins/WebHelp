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
import util.LineSpacingStatus;
import util.ApplyButtonStatus;
import util.LineSpacingStatus.LineSpacingEnum;

public class LineSpacing extends HBox{

	private WebEngine webEngine;
	private RadioMenuItem lineSimples, line15, line5, linhaDupla;
	private SplitMenuButton lineSpacingMenu;
	
	public LineSpacing(WebView webView) {
		this.webEngine = webView.getEngine();
		createButton();
		actionButton();
	}
	
	void createButton(){
		lineSpacingMenu = new SplitMenuButton();
		lineSpacingMenu.setId("lineSpacing");
		lineSimples = new RadioMenuItem("Simples");
		line15 = new RadioMenuItem("1.15");
		line5 = new RadioMenuItem("1.5");
		linhaDupla = new RadioMenuItem("Duplo");
		lineSpacingMenu.getItems().addAll(lineSimples, line15, line5, linhaDupla);
		
		ToggleGroup lineSpacingGroup = new ToggleGroup();
		lineSpacingGroup.getToggles().addAll(lineSimples, line15, line5, linhaDupla);

		MenuWebHelpStyle webHelpStyle = new MenuWebHelpStyle();
		webHelpStyle.estilo(lineSpacingMenu);
	}

	public void actionButton() {	
		LineSpacingStatus lineSpacingStatus = new LineSpacingStatus();
		
		webEngine.getLoadWorker().stateProperty().addListener((obs, oldValue, newValue) -> {
			if (newValue == State.SUCCEEDED) {
				Document doc = webEngine.getDocument();
				WebHelpBar.applyButtonStatus = new ApplyButtonStatus(doc);
				
				lineSpacingMenu.setOnMouseClicked(new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {
						lineSpacingStatus.setLineSpacing();
						WebHelpBar.applyButtonStatus.setFontStyle(lineSpacingStatus.getLineSpacing(LineSpacingEnum.none.getLineSpacing()),
								lineSpacingStatus.isLineSpacing());
					}
				});

				lineSimples.setOnAction(actionEvent -> {
					lineSpacingStatus.setOptionLineSpacing();
					WebHelpBar.applyButtonStatus.setFontStyle(lineSpacingStatus.getLineSpacing(LineSpacingEnum.lineSimples.getLineSpacing()),
							lineSpacingStatus.isLineSpacing());
				});
				
				line15.setOnAction(actionEvent -> {
					lineSpacingStatus.setOptionLineSpacing();
					WebHelpBar.applyButtonStatus.setFontStyle(lineSpacingStatus.getLineSpacing(LineSpacingEnum.line15.getLineSpacing()),
							lineSpacingStatus.isLineSpacing());
				});
				
				line5.setOnAction(actionEvent -> {
					lineSpacingStatus.setOptionLineSpacing();
					WebHelpBar.applyButtonStatus.setFontStyle(lineSpacingStatus.getLineSpacing(LineSpacingEnum.line5.getLineSpacing()),
							lineSpacingStatus.isLineSpacing());
				});
				
				linhaDupla.setOnAction(actionEvent -> {
					lineSpacingStatus.setOptionLineSpacing();
					WebHelpBar.applyButtonStatus.setFontStyle(lineSpacingStatus.getLineSpacing(LineSpacingEnum.linhaDupla.getLineSpacing()),
							lineSpacingStatus.isLineSpacing());
				});
			}
		});
		this.getChildren().add(lineSpacingMenu);
	}

}
