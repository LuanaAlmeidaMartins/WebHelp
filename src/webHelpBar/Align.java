package webHelpBar;

import org.w3c.dom.Document;

import javafx.concurrent.Worker.State;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import util.AlignStatus;
import util.ApplyButtonStatus;

public class Align extends HBox{

	WebEngine webEngine;
	Button align;
	
	public Align(WebView webView) {
		this.webEngine = webView.getEngine();
		createButton();
		actionButton();
	}
	
	void createButton(){
		align = new Button();
		align.setId("align");
		
		MenuWebHelpStyle webHelpStyle = new MenuWebHelpStyle();
		webHelpStyle.estilo(align);
	}

	public void actionButton() {	
		
		AlignStatus alignStatus = new AlignStatus();
		
		webEngine.getLoadWorker().stateProperty().addListener((obs, oldValue, newValue) -> {
			if (newValue == State.SUCCEEDED) {
				Document doc = webEngine.getDocument();
				WebHelpBar.applyButtonStatus = new ApplyButtonStatus(doc);
				
				align.setOnMouseClicked(new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {
						alignStatus.setAlign();
						WebHelpBar.applyButtonStatus.setFontStyle(alignStatus.getAlign(), alignStatus.isAlign());
					}
				});
			}
		});
		this.getChildren().add(align);
	}

}
