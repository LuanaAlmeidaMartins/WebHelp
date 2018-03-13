package webComponent;

import java.util.ArrayList;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import javafx.application.Application;
import javafx.concurrent.Worker.State;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import persistence.Font;
import persistence.Font.FontEnum;
import persistence.Options;
import util.AlignStatus;
import util.BoldStatus;
import util.FontFamilyStatus;
import util.FontFamilyStatus.FontFamilyEnum;
import util.ItalicStatus;
import util.Status;
import util.UnderlineStatus;

public class Main extends Application {

	ArrayList<Font> fontSizes = new ArrayList<Font>();
	Element element;
	
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

		// Create the WebHelp menu
		MenuBar menuBar = new MenuBar();
		menuBar.prefWidthProperty().bind(stage.widthProperty());

		MenuWebHelp webHelp = new MenuWebHelp();
		
		Menu tamanhoMenu = new Menu();
		tamanhoMenu.setId("fontSize");
		ToggleGroup tGroup = new ToggleGroup();
		RadioMenuItem pequeno = new RadioMenuItem("Pequeno");
		RadioMenuItem medio = new RadioMenuItem("Médio");
		RadioMenuItem grande = new RadioMenuItem("Grande");
		RadioMenuItem enorme = new RadioMenuItem("Enorme");
		tGroup.getToggles().addAll(pequeno, medio, grande, enorme);
		tamanhoMenu.getItems().addAll(pequeno, medio, grande, enorme);
		
		
		
		Label fontFamily = new Label();
		fontFamily.setId("fontFamily");
		Menu fontFamilyMenu = new Menu();
		fontFamilyMenu.setGraphic(fontFamily);
		RadioMenuItem openDyslexic = new RadioMenuItem("Open Dyslexic");
		RadioMenuItem openSans = new RadioMenuItem("Open Sans");
		RadioMenuItem comicSans = new RadioMenuItem("Comic Sans");
		RadioMenuItem georgia = new RadioMenuItem("Georgia");
		ToggleGroup fontFamilyGroup = new ToggleGroup();
		fontFamilyGroup.getToggles().addAll(openDyslexic, openSans, comicSans, georgia);
		fontFamilyMenu.getItems().addAll(openDyslexic, openSans, comicSans, georgia);
		FontFamilyStatus fontFamilyStatus = new FontFamilyStatus();
		
	    
		Label bold = new Label();
		bold.setId("bold");
		Menu boldMenu = new Menu();
		boldMenu.setGraphic(bold);
		BoldStatus boldStatus = new BoldStatus();
		
		
		Label italic = new Label();
		italic.setId("italic");
		Menu italicMenu = new Menu();
		italicMenu.setGraphic(italic);
		ItalicStatus italicStatus = new ItalicStatus();
		
		
		Label underline = new Label();
		Menu underlineMenu = new Menu();
		underline.setId("underline");
		underlineMenu.setGraphic(underline);
		UnderlineStatus underlineStatus = new UnderlineStatus();
		
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
		align.setId("align");
		alignMenu.setGraphic(align);
		AlignStatus alignStatus = new AlignStatus();
		
		
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
		webHelp.estilo(fontFamily);
		webHelp.estilo(bold);
		webHelp.estilo(align);
		webHelp.estilo(italic);
		webHelp.estilo(charSpacingMenu);
		webHelp.estilo(paragraphSpacingMenu);
		webHelp.estilo(lineSpacingMenu);
		webHelp.estilo(colorPicker);	
		
		
		menuBar.getMenus().addAll(fontFamilyMenu, tamanhoMenu, boldMenu, italicMenu, underlineMenu, alignMenu,
				lineSpacingMenu, paragraphSpacingMenu, charSpacingMenu, fontColorMenu);
		
		
		
		// Click Items
		webEngine.getLoadWorker().stateProperty().addListener((obs, oldValue, newValue) -> {
			if (newValue == State.SUCCEEDED) {
				Document doc = webEngine.getDocument();
				Status status = new Status(doc);
				
				underline.setOnMouseClicked(new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {
						underlineStatus.setUnderline();
						status.setFontStyle(underlineStatus.getUnderline(), underlineStatus.isUnderline());
					}
				});
				
				bold.setOnMouseClicked(new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {
						boldStatus.setBold();
						status.setFontStyle(boldStatus.getBold(), boldStatus.isBold());
					}
				});
				
				italic.setOnMouseClicked(new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {
						italicStatus.setItalic();
						status.setFontStyle(italicStatus.getItalic(), italicStatus.isItalic());
					}
				});
				
				align.setOnMouseClicked(new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {
						alignStatus.setAlign();
						status.setFontStyle(alignStatus.getAlign(), alignStatus.isAlign());
					}
				});
				
				fontFamily.setOnMouseClicked(new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {
						fontFamilyStatus.setFontFamily();
						status.setFontStyle(fontFamilyStatus.getFamilyFont(FontFamilyEnum.none.getFont()), fontFamilyStatus.isFontFamily());
					}
				});
				
				openDyslexic.setOnAction(actionEvent -> {
					System.out.println(fontFamilyStatus.setOptionFamily());
					status.setFontStyle(fontFamilyStatus.getFamilyFont(FontFamilyEnum.openDyslexic.getFont()), fontFamilyStatus.isFontFamily());
				});
				
				openSans.setOnAction(actionEvent -> {
					fontFamilyStatus.setOptionFamily();
					status.setFontStyle(fontFamilyStatus.getFamilyFont(FontFamilyEnum.openSans.getFont()), fontFamilyStatus.isFontFamily());
				});
				
				comicSans.setOnAction(actionEvent -> {
					fontFamilyStatus.setOptionFamily();
					status.setFontStyle(fontFamilyStatus.getFamilyFont(FontFamilyEnum.comicSans.getFont()), fontFamilyStatus.isFontFamily());
				});
				
				georgia.setOnAction(actionEvent -> {
					fontFamilyStatus.setOptionFamily();
					status.setFontStyle(fontFamilyStatus.getFamilyFont(FontFamilyEnum.georgia.getFont()), fontFamilyStatus.isFontFamily());
				});

				enorme.setOnAction(actionEvent -> {
					Options options = new Options();
					fontSizes = options.FillOptions(FontEnum.enorme.getFont());
					Tamanho(doc);
				});
				
				grande.setOnAction(actionEvent -> {
					Options options = new Options();
					fontSizes = options.FillOptions(FontEnum.grande.getFont());
					Tamanho(doc);
				});
				
				medio.setOnAction(actionEvent -> {
					Options options = new Options();
					fontSizes = options.FillOptions(FontEnum.medio.getFont());
					Tamanho(doc);
				});
				
				pequeno.setOnAction(actionEvent -> {
					Options options = new Options();
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
		
		for (int j = 0; j < fontSizes.size(); j++) {
			for (int i = 0; i < doc.getElementsByTagName(fontSizes.get(j).getTagName()).getLength(); i++) {
				element = (Element) doc.getElementsByTagName(fontSizes.get(j).getTagName()).item(i);
				element.setAttribute("style", "font-size: " + fontSizes.get(j).getSize());
				for (int k = 0; k < element.getChildNodes().getLength(); k++) {
					if (!element.getChildNodes().item(k).getNodeName().equals("#text")) {
						Element subElement = (Element) element.getChildNodes().item(k);
						subElement.setAttribute("style", "font-size: "+fontSizes.get(j).getSize());
					}
				}
			}
		}

	}

	public static void main(String[] args) {
		launch(args);
	}
}