package webHelpBar;

import org.w3c.dom.Document;

import javafx.concurrent.Worker.State;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import util.ApplyButtonStatus;
import util.BoldStatus;
import util.LetterColorStatus;
import util.FontFamilyStatus.FontFamilyEnum;

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
				letterColorStatus.setColor();
				letterColorStatus.setColorName(converterColor(colorPicker.getValue()));
				WebHelpBar.applyButtonStatus.setFontStyle(letterColorStatus.getColor(),
						letterColorStatus.isColor());
			    });
			}
		});
	}

	private String converterColor(Color color) {
		return colorChanelToHex(color.getRed())
                + colorChanelToHex(color.getGreen())
                + colorChanelToHex(color.getBlue())
                + colorChanelToHex(color.getOpacity());
		
	}
	
	private static String colorChanelToHex(double chanelValue) {
        String rtn = Integer.toHexString((int) Math.min(Math.round(chanelValue * 255), 255));
        if (rtn.length() == 1) {
            rtn = "0" + rtn;
        }
        return rtn;
    }

	private void createButton() {
		colorPicker = new ColorPicker();
		colorPicker.setPrefHeight(36);
		colorPicker.setPrefWidth(68);
		colorPicker.getStyleClass().addAll("color-picker", "split-button");

		
		MenuWebHelpStyle webHelpStyle = new MenuWebHelpStyle();
		webHelpStyle.estilo(colorPicker);

		this.getChildren().add(colorPicker);
	}
}
