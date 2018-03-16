package webHelpBar;

import org.w3c.dom.Document;

import javafx.concurrent.Worker.State;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ColorPicker;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import persistence.ColorConverter;
import util.ApplyButtonStatus;
import util.LetterColorStatus;

public class LetterColor extends HBox{

	WebEngine webEngine;
	private ColorPicker colorPicker;

	public LetterColor(WebView webView) {
		this.webEngine = webView.getEngine();
		createButton();
		actionButton();
	}

	private void actionButton() {
		LetterColorStatus letterColorStatus = new LetterColorStatus();
		ColorConverter color = new ColorConverter();
		
		webEngine.getLoadWorker().stateProperty().addListener((obs, oldValue, newValue) -> {
			if (newValue == State.SUCCEEDED) {
				Document doc = webEngine.getDocument();
				WebHelpBar.applyButtonStatus = new ApplyButtonStatus(doc);
				
				colorPicker.addEventHandler(MouseEvent.MOUSE_CLICKED,
						new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent e) {
						letterColorStatus.setColor();
						WebHelpBar.applyButtonStatus.setFontStyle(letterColorStatus.getColor(),
								letterColorStatus.isColor());
					}
				});

				colorPicker.setOnAction((ActionEvent t) -> {
					System.out.println("eita");
					letterColorStatus.setOptionColorSpacing();
					WebHelpBar.applyButtonStatus.removeFontStyle(letterColorStatus.getRemoveOption());
					letterColorStatus.setColorName(color.converterColor(colorPicker.getValue()));
					WebHelpBar.applyButtonStatus.setFontStyle(letterColorStatus.getColor(),
							letterColorStatus.isColor());
				});
			}
		});
	}

	private void createButton() {
		colorPicker = new ColorPicker();
		colorPicker.setId("letterColor");
		colorPicker.setPrefHeight(36);
		colorPicker.setPrefWidth(68);
		colorPicker.getStyleClass().addAll("color-picker", "split-button");

		MenuWebHelpStyle webHelpStyle = new MenuWebHelpStyle();
		webHelpStyle.estilo(colorPicker);

		this.getChildren().add(colorPicker);
	}
}
