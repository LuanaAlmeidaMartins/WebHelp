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
import util.BackgroundStatus;

public class BackgroundColor extends HBox{

	WebEngine webEngine;
	private ColorPicker colorPickerBackground;

	public BackgroundColor(WebView webView) {
		this.webEngine = webView.getEngine();
		createButton();
		actionButton();
	}

	private void actionButton() {
		BackgroundStatus backgroundStatus = new BackgroundStatus();
		ColorConverter color = new ColorConverter();
		
		webEngine.getLoadWorker().stateProperty().addListener((obs, oldValue, newValue) -> {
			if (newValue == State.SUCCEEDED) {
				Document doc = webEngine.getDocument();
				WebHelpBar.applyButtonStatus = new ApplyButtonStatus(doc);
				
				colorPickerBackground.addEventHandler(MouseEvent.MOUSE_CLICKED,
						new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent e) {
						backgroundStatus.setColor();
						WebHelpBar.applyButtonStatus.setBackgroundStyle(backgroundStatus.getColor(),
								backgroundStatus.isColor());
					}
				});

				colorPickerBackground.setOnAction((ActionEvent t) -> {
					System.out.println("setOption");
					backgroundStatus.setOptionBackgroundSpacing();
					backgroundStatus.setBackgroundColorName(color.converterColor(colorPickerBackground.getValue()));
					WebHelpBar.applyButtonStatus.setBackgroundStyle(backgroundStatus.getColor(), 
							backgroundStatus.isColor());
				});
			}
		});
		this.getChildren().add(colorPickerBackground);
	}

	private void createButton() {
		colorPickerBackground = new ColorPicker();
		colorPickerBackground.setId("background");
		colorPickerBackground.setPrefHeight(36);
		colorPickerBackground.setPrefWidth(68);
		colorPickerBackground.getStyleClass().addAll("color-picker", "split-button");

		MenuWebHelpStyle webHelpStyle = new MenuWebHelpStyle();
		webHelpStyle.estilo(colorPickerBackground);
	}
}
