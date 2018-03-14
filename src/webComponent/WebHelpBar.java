package webComponent;

import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javafx.concurrent.Worker.State;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.Separator;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
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
import util.LineSpacingStatus;
import util.LineSpacingStatus.LineSpacingEnum;

public class WebHelpBar extends HBox {

	ArrayList<Font> fontSizes = new ArrayList<Font>();

	public WebHelpBar(WebView webView) {

		WebEngine webEngine = webView.getEngine();
		MenuWebHelpStyle webhelp = new MenuWebHelpStyle();

		// Set spacing and the style-properties of the WebHelpBar
		this.setSpacing(2);
		this.setStyle("-fx-padding: 4; -fx-border-insets: 2;");

		// Font family options
		SplitMenuButton fontFamilyMenu = new SplitMenuButton();
		fontFamilyMenu.setId("fontFamily");
		RadioMenuItem openDyslexic = new RadioMenuItem("Open Dyslexic");
		RadioMenuItem openSans = new RadioMenuItem("Open Sans");
		RadioMenuItem comicSans = new RadioMenuItem("Comic Sans");
		RadioMenuItem georgia = new RadioMenuItem("Georgia");
		ToggleGroup fontFamilyGroup = new ToggleGroup();
		fontFamilyGroup.getToggles().addAll(openDyslexic, openSans, comicSans, georgia);
		fontFamilyMenu.getItems().addAll(openDyslexic, openSans, comicSans, georgia);
		FontFamilyStatus fontFamilyStatus = new FontFamilyStatus();
		webhelp.estilo(fontFamilyMenu);

		// Font size options
		SplitMenuButton fontSizeMenu = new SplitMenuButton();
		fontSizeMenu.setId("fontSize");
		ToggleGroup fontSizeGroup = new ToggleGroup();
		RadioMenuItem pequeno = new RadioMenuItem("Pequeno");
		RadioMenuItem medio = new RadioMenuItem("Médio");
		RadioMenuItem grande = new RadioMenuItem("Grande");
		RadioMenuItem enorme = new RadioMenuItem("Enorme");
		fontSizeGroup.getToggles().addAll(pequeno, medio, grande, enorme);
		fontSizeMenu.getItems().addAll(pequeno, medio, grande, enorme);
		webhelp.estilo(fontSizeMenu);

		// Bold option
		Button bold = new Button();
		bold.setId("bold");
		webhelp.estilo(bold);
		BoldStatus boldStatus = new BoldStatus();

		// Underline Option
		Button underline = new Button();
		underline.setId("underline");
		webhelp.estilo(underline);
		UnderlineStatus underlineStatus = new UnderlineStatus();

		// Italic option
		Button italic = new Button();
		italic.setId("italic");
		webhelp.estilo(italic);
		ItalicStatus italicStatus = new ItalicStatus();

		// Align option
		Button align = new Button();
		align.setId("align");
		webhelp.estilo(align);
		AlignStatus alignStatus = new AlignStatus();

		// Spacing options between lines
		SplitMenuButton lineSpacingMenu = new SplitMenuButton();
		lineSpacingMenu.setId("lineSpacing");
		RadioMenuItem lineSimples = new RadioMenuItem("Simples");
		RadioMenuItem line15 = new RadioMenuItem("1.15");
		RadioMenuItem line5 = new RadioMenuItem("1.5");
		RadioMenuItem linhaDupla = new RadioMenuItem("Duplo");
		lineSpacingMenu.getItems().addAll(lineSimples, line15, line5, linhaDupla);
		ToggleGroup lineSpacingGroup = new ToggleGroup();
		lineSpacingGroup.getToggles().addAll(lineSimples, line15, line5, linhaDupla);
		LineSpacingStatus lineSpacingStatus = new LineSpacingStatus();
		webhelp.estilo(lineSpacingMenu);

		// Spacing options between paragraphs
		SplitMenuButton paragraphSpacingMenu = new SplitMenuButton();
		paragraphSpacingMenu.setId("paragraphSpacing");
		RadioMenuItem paragraphSimples = new RadioMenuItem("Simples");
		RadioMenuItem paragraph15 = new RadioMenuItem("1.15");
		RadioMenuItem paragraph5 = new RadioMenuItem("1.5");
		RadioMenuItem paragraphDupla = new RadioMenuItem("Duplo");
		paragraphSpacingMenu.getItems().addAll(paragraphSimples, paragraph15, paragraph5, paragraphDupla);
		ToggleGroup paragraphSpacingGroup = new ToggleGroup();
		paragraphSpacingGroup.getToggles().addAll(paragraphSimples, paragraph15, paragraph5, paragraphDupla);
		webhelp.estilo(paragraphSpacingMenu);

		// Spacing options between characters
		SplitMenuButton charSpacingMenu = new SplitMenuButton();
		charSpacingMenu.setId("charSpacing");
		RadioMenuItem charPequeno = new RadioMenuItem("Pequeno");
		RadioMenuItem charMedio = new RadioMenuItem("M é d i o");
		RadioMenuItem charGrande = new RadioMenuItem("G  r  a  n  d  e");
		charSpacingMenu.getItems().addAll(charPequeno, charMedio, charGrande);
		ToggleGroup charSpacingGroup = new ToggleGroup();
		charSpacingGroup.getToggles().addAll(charPequeno, charMedio, charGrande);
		webhelp.estilo(charSpacingMenu);

		final ColorPicker colorPicker = new ColorPicker();
		colorPicker.setPrefHeight(48);
		colorPicker.getStyleClass().add("button");
		// Men fontColorMenu = new Menu("",colorPicker);
		// //fontColorMenu.setGraphic(fontColor);
		// //fontColorMenu.getItems().add(a);

		webEngine.getLoadWorker().stateProperty().addListener((obs, oldValue, newValue) -> {
			if (newValue == State.SUCCEEDED) {
				Document doc = webEngine.getDocument();
				Status status = new Status(doc);

				fontFamilyMenu.setOnMouseClicked(new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {
						fontFamilyStatus.setFontFamily();
						status.setFontStyle(fontFamilyStatus.getFamilyFont(FontFamilyEnum.none.getFont()),
								fontFamilyStatus.isFontFamily());
					}
				});

				openDyslexic.setOnAction(actionEvent -> {
					System.out.println(fontFamilyStatus.setOptionFamily());
					status.setFontStyle(fontFamilyStatus.getFamilyFont(FontFamilyEnum.openDyslexic.getFont()),
							fontFamilyStatus.isFontFamily());
				});

				openSans.setOnAction(actionEvent -> {
					fontFamilyStatus.setOptionFamily();
					status.setFontStyle(fontFamilyStatus.getFamilyFont(FontFamilyEnum.openSans.getFont()),
							fontFamilyStatus.isFontFamily());
				});

				comicSans.setOnAction(actionEvent -> {
					fontFamilyStatus.setOptionFamily();
					status.setFontStyle(fontFamilyStatus.getFamilyFont(FontFamilyEnum.comicSans.getFont()),
							fontFamilyStatus.isFontFamily());
				});

				georgia.setOnAction(actionEvent -> {
					fontFamilyStatus.setOptionFamily();
					status.setFontStyle(fontFamilyStatus.getFamilyFont(FontFamilyEnum.georgia.getFont()),
							fontFamilyStatus.isFontFamily());
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

				underline.setOnMouseClicked(new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {
						underlineStatus.setUnderline();
						status.setFontStyle(underlineStatus.getUnderline(), underlineStatus.isUnderline());
					}
				});

				align.setOnMouseClicked(new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {
						alignStatus.setAlign();
						status.setFontStyle(alignStatus.getAlign(), alignStatus.isAlign());
					}
				});
				
				lineSpacingMenu.setOnMouseClicked(new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {
						lineSpacingStatus.setLineSpacing();
						status.setFontStyle(lineSpacingStatus.getLineSpacing(LineSpacingEnum.none.getLineSpacing()),
								lineSpacingStatus.isLineSpacing());
					}
				});

				lineSimples.setOnAction(actionEvent -> {
					lineSpacingStatus.setOptionLineSpacing();
					status.setFontStyle(lineSpacingStatus.getLineSpacing(LineSpacingEnum.lineSimples.getLineSpacing()),
							lineSpacingStatus.isLineSpacing());
				});
				
				line15.setOnAction(actionEvent -> {
					lineSpacingStatus.setOptionLineSpacing();
					status.setFontStyle(lineSpacingStatus.getLineSpacing(LineSpacingEnum.line15.getLineSpacing()),
							lineSpacingStatus.isLineSpacing());
				});
				
				line5.setOnAction(actionEvent -> {
					lineSpacingStatus.setOptionLineSpacing();
					status.setFontStyle(lineSpacingStatus.getLineSpacing(LineSpacingEnum.line5.getLineSpacing()),
							lineSpacingStatus.isLineSpacing());
				});
				
				linhaDupla.setOnAction(actionEvent -> {
					lineSpacingStatus.setOptionLineSpacing();
					status.setFontStyle(lineSpacingStatus.getLineSpacing(LineSpacingEnum.linhaDupla.getLineSpacing()),
							lineSpacingStatus.isLineSpacing());
				});
			}
		});

		// Add the Children to the Navigation Bar
		this.getChildren().addAll(fontFamilyMenu, fontSizeMenu, new Separator(Orientation.VERTICAL), bold, italic,
				underline, new Separator(Orientation.VERTICAL), align, new Separator(Orientation.VERTICAL),
				lineSpacingMenu, paragraphSpacingMenu, charSpacingMenu);
		// return toolBar;
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
}
