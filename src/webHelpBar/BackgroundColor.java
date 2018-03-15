package webHelpBar;

import javafx.scene.layout.HBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class BackgroundColor extends HBox{

	WebEngine webEngine;

	public BackgroundColor(WebView webView) {
		this.webEngine = webView.getEngine();
		createButton();
		actionButton();
	}

	private void actionButton() {
		// TODO Auto-generated method stub
		
	}

	private void createButton() {
		// TODO Auto-generated method stub
		
		MenuWebHelpStyle webHelpStyle = new MenuWebHelpStyle();
		//webHelpStyle.estilo();
	}
}
