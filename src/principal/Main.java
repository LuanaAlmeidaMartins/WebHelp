package principal;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import navigationBar.NavigationBar;
import webHelpBar.WebHelpBar;

public class Main extends Application {

	@Override
	public void start(final Stage stage) {

		// Set application icon
		stage.getIcons().add(new Image("webhelp.png"));

		// Set application home page
		String homePageUrl = "https://www.w3.org/TR/DOM-Level-2-Core/core.html#ID-F68F082";
		//String homePageUrl = "https://pt.wikipedia.org/wiki/Flor";

		// Create a WebView
		final WebView browser = new WebView();

		// Get WebEngine via WebView and load home page
		final WebEngine webEngine = browser.getEngine();
		webEngine.load(homePageUrl);

		// When the website content is bigger than display area, the scroll pane is enabled
		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setContent(new Group());

		// Create the navigation bar
		NavigationBar navigationBar = new NavigationBar(browser, homePageUrl, true);

		// Create the WebHelp bar
		WebHelpBar webHelpBar = new WebHelpBar(browser);
		
		// Create the VBox, add the navigationBar, menu and webview to the VBox and
		VBox root = new VBox(navigationBar, webHelpBar, browser);

		// Create the Scene, add the Scene to the Stage and display the Stage
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}