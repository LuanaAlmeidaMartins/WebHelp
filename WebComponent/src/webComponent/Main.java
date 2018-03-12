package webComponent;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import groovy.ui.SystemOutputInterceptor;
import javafx.application.Application;
import javafx.concurrent.Worker.State;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import persistence.Font;
import persistence.Font.FontEnum;
import persistence.FontStyle;
import persistence.Options;

public class Main extends Application {

	ArrayList<Font> fontSizes = new ArrayList<Font>();
	Element element;
	
	@Override
	public void start(final Stage stage) {

		// Set application icon
		stage.getIcons().add(new Image("webhelp.png"));

		// Set application home page
		String homePageUrl = "https://www.w3.org/TR/DOM-Level-2-Core/core.html#ID-F68F082";

		// Create a WebView
		final WebView browser = new WebView();

		// Get WebEngine via WebView and load home page
		final WebEngine webEngine = browser.getEngine();
		webEngine.load("https://www.w3.org/TR/DOM-Level-2-Core/core.html#ID-F68F082");

		// When the website content is bigger than display area, the scroll pane is enabled
		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setContent(new Group());

		// Create the navigation bar
		NavigationBar navigationBar = new NavigationBar(browser, homePageUrl, true);

		// Create the WebHelp menu
		MenuBar menuBar = new MenuBar();
		menuBar.prefWidthProperty().bind(stage.widthProperty());

		MenuWebHelp webHelp = new MenuWebHelp();
		
		Menu tamanhoMenu = new Menu();
		tamanhoMenu.setId("fontSize");
		MenuItem pequeno = new MenuItem("Pequeno");
		MenuItem medio = new MenuItem("Médio");
		MenuItem grande = new MenuItem("Grande");
		MenuItem enorme = new MenuItem("Enorme");
		tamanhoMenu.getItems().addAll(pequeno, medio, grande, enorme);
		
		
		Menu fontMenu = new Menu();
		fontMenu.setId("fontFamily");
		CheckMenuItem openDyslexic = new CheckMenuItem("Open Dyslexic");
		CheckMenuItem openSans = new CheckMenuItem("Open Sans");
		CheckMenuItem comicSans = new CheckMenuItem("Comic Sans");
		CheckMenuItem georgia = new CheckMenuItem("Georgia");
	    fontMenu.getItems().addAll(openDyslexic, openSans, comicSans, georgia);

	    
		Label bold = new Label();
		bold.setId("bold");
		Menu boldMenu = new Menu();
		boldMenu.setGraphic(bold);

		
		Label italic = new Label();
		italic.setId("italic");
		Menu italicMenu = new Menu();
		italicMenu.setGraphic(italic);
		
		
		Label underline = new Label();
		Menu underlineMenu = new Menu();
		underline.setId("underline");
		underlineMenu.setGraphic(underline);
		
		Menu charSpacingMenu = new Menu();
		charSpacingMenu.setId("charSpacing");
		CheckMenuItem charPequeno = new CheckMenuItem("Pequeno");
		CheckMenuItem charMedio = new CheckMenuItem("M é d i o");
		CheckMenuItem charGrande = new CheckMenuItem("G  r  a  n  d  e");
		charSpacingMenu.getItems().addAll(charPequeno, charMedio, charGrande);

		Menu lineSpacingMenu = new Menu();
		lineSpacingMenu.setId("lineSpacing");
		CheckMenuItem lineSimples = new CheckMenuItem("Simples");
		CheckMenuItem line15 = new CheckMenuItem("1.15");
		CheckMenuItem line5 = new CheckMenuItem("1.5");
		CheckMenuItem linhaDupla = new CheckMenuItem("Duplo");
		lineSpacingMenu.getItems().addAll(lineSimples, line15, line5, linhaDupla);
	    
		
		Menu paragraphSpacingMenu = new Menu();
		paragraphSpacingMenu.setId("paragraphSpacing");
		CheckMenuItem paragraphSimples = new CheckMenuItem("Simples");
		CheckMenuItem paragraph15 = new CheckMenuItem("1.15");
		CheckMenuItem paragraph5 = new CheckMenuItem("1.5");
		CheckMenuItem paragraphDupla = new CheckMenuItem("Duplo");
		paragraphSpacingMenu.getItems().addAll(paragraphSimples, paragraph15, paragraph5, paragraphDupla);
	
		Label align = new Label();
		Menu alignMenu = new Menu();
		alignMenu.setId("align");
		alignMenu.setGraphic(align);
		
		final ColorPicker colorPicker = new ColorPicker();
		colorPicker.setPrefHeight(48);
		colorPicker.getStyleClass().add("button");

		
		
		Menu fontColorMenu = new Menu("",colorPicker);
		
		//fontColorMenu.setGraphic(fontColor);
		//fontColorMenu.getItems().add(a);

		
		//    colorPicker.setValue(Color.RED);

//		    final Text text = new Text("");
//		    text.setFill(colorPicker.getValue());

			
			
//		   colorPicker.setOnAction((ActionEvent t) -> {
//		      //text.setFill(colorPicker.getValue());
//		    });
//       
		
		webHelp.estilo(tamanhoMenu);
		webHelp.estilo(underline);
		webHelp.estilo(fontMenu);
		webHelp.estilo(bold);
		webHelp.estilo(italic);
		webHelp.estilo(charSpacingMenu);
		webHelp.estilo(paragraphSpacingMenu);
		webHelp.estilo(lineSpacingMenu);
		webHelp.estilo(colorPicker);
		
		menuBar.getMenus().addAll(fontMenu, tamanhoMenu, boldMenu, italicMenu, underlineMenu, alignMenu,
				lineSpacingMenu, paragraphSpacingMenu, charSpacingMenu, fontColorMenu);
		
		FontStyle font = new FontStyle();
		ArrayList<String> tags = font.getTagsName();
		
		
		
		// Click Items
		webEngine.getLoadWorker().stateProperty().addListener((obs, oldValue, newValue) -> {
			if (newValue == State.SUCCEEDED) {
				Options options = new Options();
				Document doc = webEngine.getDocument();
			
				underline.setOnMouseClicked(new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {
					//	Element element;
						for (int g = 0; g < tags.size(); g++) {
							for (int i = 0; i < doc.getElementsByTagName(font.getTagsName().get(g)).getLength(); i++) {
								element = (Element) doc.getElementsByTagName(tags.get(g)).item(i);
								element.setAttribute("style", "text-decoration: none;");
							}
						}
					}
				});
				
				bold.setOnMouseClicked(new EventHandler<MouseEvent>() {

					@Override
					public void handle(MouseEvent event) {
						
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
				//		Element element;
						for (int g = 0; g < tags.size(); g++) {
							for (int i = 0; i < doc.getElementsByTagName(font.getTagsName().get(g)).getLength(); i++) {
								element = (Element) doc.getElementsByTagName(tags.get(g)).item(i);
								element.setAttribute("style", "font-style: normal;");
							}
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

		
		
		

		// Create the VBox, add the navigationBar, menu and webview to the VBox and
		VBox root = new VBox(navigationBar, menuBar, browser);

		// Create the Scene, add the Scene to the Stage and display the Stage
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	private void Tamanho(Document doc) {
	//	Element element;

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