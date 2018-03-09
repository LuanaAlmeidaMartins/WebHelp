package webComponent;

import java.io.File;
import java.util.ArrayList;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import javafx.application.Application;
import javafx.concurrent.Worker.State;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import persistence.Font;
import persistence.Font.FontEnum;
import persistence.FontStyle;
import persistence.Options;

public class Main extends Application {

	ArrayList<Font> fontSizes = new ArrayList<Font>();

	@Override
	public void start(final Stage stage) {

		// Set application icon
		stage.getIcons().add(new Image("webhelp.png"));

		// Set application home page
		String homePageUrl = "http://www.wordreference.com/enpt/underline";

		// Create a WebView
		final WebView browser = new WebView();

		// Get WebEngine via WebView and load home page
		final WebEngine webEngine = browser.getEngine();
		webEngine.load("http://www.wordreference.com/enpt/underline");

		// When the website content is bigger than display area, the scroll pane is
		// enabled
		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setContent(new Group());

		// Create the navigation bar
		NavigationBar navigationBar = new NavigationBar(browser, homePageUrl, true);

		// Create the WebHelp menu
		MenuBar menuBar = new MenuBar();
		menuBar.prefWidthProperty().bind(stage.widthProperty());

		Menu tamanhoMenu = new Menu();
		MenuItem pequeno = new MenuItem("Pequeno");
		MenuItem medio = new MenuItem("Médio");
		MenuItem grande = new MenuItem("Grande");
		MenuItem enorme = new MenuItem("Enorme");
		tamanhoMenu.getItems().addAll(pequeno, medio, grande, enorme);

		MenuWebHelp webHelp = new MenuWebHelp();
		webHelp.estilo(tamanhoMenu);

		Menu fontMenu = new Menu();
		CheckMenuItem openDyslexic = new CheckMenuItem("Open Dyslexic");
		MenuItem openSans = new MenuItem("Open Sans");
		MenuItem comicSans = new MenuItem("Comic Sans");
		MenuItem georgia = new MenuItem("Georgia");

		File file2 = new File("icons/font-family.png");
		Image image2 = new Image(file2.toURI().toString());
		fontMenu.setGraphic(new ImageView(image2));

		pequeno.setStyle("-fx-font-size: 14pt;");
		medio.setStyle("-fx-font-size: 18pt;");
		grande.setStyle("-fx-font-size: 22pt;");
		enorme.setStyle("-fx-font-size: 28pt;");

		openDyslexic.setStyle(
				"-fx-font-size: 14pt; -fx-font-family: 'opendyslexic'; src: url('OpenDyslexic-Regular.ttf');");
		openSans.setStyle("-fx-font-size: 14pt; -fx-font-family: 'opensans'; src: url('OpenSans-Regular.ttf');");
		comicSans.setStyle("-fx-font-size: 14pt; -fx-font-family: 'Comic Sans MS';");
		georgia.setStyle("-fx-font-size: 14pt; -fx-font-family: 'Georgia';");

		Label underline = new Label();
		File file3 = new File("icons/bold.png");
		Image image3 = new Image(file3.toURI().toString());
		underline.setGraphic(new ImageView(image3));

		Menu fileMenuButton = new Menu();
		fileMenuButton.setGraphic(underline);
		menuBar.getMenus().add(fileMenuButton);

		Label italic = new Label();
		File file4 = new File("icons/italic.png");
		Image image4 = new Image(file4.toURI().toString());
		italic.setGraphic(new ImageView(image4));

		Menu italicMenu = new Menu();
		italicMenu.setGraphic(italic);
		menuBar.getMenus().add(italicMenu);

		// Click Items
		webEngine.getLoadWorker().stateProperty().addListener((obs, oldValue, newValue) -> {
			if (newValue == State.SUCCEEDED) {
				Options options = new Options();
				Document doc = webEngine.getDocument();

				underline.setOnMouseClicked(new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {
						Element element;
						FontStyle font = new FontStyle();
						ArrayList<String> tags = font.getTagsName();

						for (int g = 0; g < tags.size(); g++) {
							for (int i = 0; i < doc.getElementsByTagName(font.getTagsName().get(g)).getLength(); i++) {
								element = (Element) doc.getElementsByTagName(tags.get(g)).item(i);
								element.setAttribute("style", "font-weight: normal;");
							}
						}
					}
				});

				italic.setOnMouseClicked(new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {
						Element element;
						for (int i = 0; i < doc.getElementsByTagName("i").getLength(); i++) {
							element = (Element) doc.getElementsByTagName("i").item(i);
							element.setAttribute("style", "font-style: normal;");
						}
					}
				});

				enorme.setOnAction(actionEvent -> {
					fontSizes = options.FillOptions(FontEnum.enorme.getFont());
					Tamanho(doc);
				});
				grande.setOnAction(actionEvent -> {
					fontSizes = options.FillOptions(FontEnum.grande.getFont());
					Tamanho(doc);
				});
				medio.setOnAction(actionEvent -> {
					fontSizes = options.FillOptions(FontEnum.medio.getFont());
					Tamanho(doc);
				});
				pequeno.setOnAction(actionEvent -> {
					fontSizes = options.FillOptions(FontEnum.pequeno.getFont());
					Tamanho(doc);
				});

			}
		});

		fontMenu.getItems().addAll(openDyslexic, openSans, comicSans, georgia);

		menuBar.getMenus().addAll(tamanhoMenu, fontMenu);

		// Create the VBox, add the navigationBar, menu and webview to the VBox and
		VBox root = new VBox(navigationBar, menuBar, browser);

		// Create the Scene, add the Scene to the Stage and display the Stage
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	private void Tamanho(Document doc) {
		Element element;

		for (int j = 0; j < fontSizes.size(); j++) {
			for (int i = 0; i < doc.getElementsByTagName(fontSizes.get(j).getTagName()).getLength(); i++) {
				element = (Element) doc.getElementsByTagName(fontSizes.get(j).getTagName()).item(i);
				element.setAttribute("style", "font-size: " + fontSizes.get(j).getSize());
				for (int k = 0; k < element.getChildNodes().getLength(); k++) {
					if (!element.getChildNodes().item(k).getNodeName().equals("#text")) {
						Element subElement = (Element) element.getChildNodes().item(k);
						subElement.setAttribute("style", "font-size: " + fontSizes.get(j).getSize());
					}
				}
			}
		}

	}

	public static void main(String[] args) {
		launch(args);
	}
}