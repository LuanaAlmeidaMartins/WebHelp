package webhelp;

import javafx.geometry.Orientation;
import javafx.scene.control.Separator;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.web.WebView;
import util.ApplyButtonStatus;

public class WebHelpBar extends HBox {

  static ApplyButtonStatus applyButtonStatus;

  public WebHelpBar(WebView webView) {

    // Set spacing and the style-properties of the webhelp
    this.setSpacing(2);
    this.setStyle("-fx-padding: 4; -fx-border-insets: 2;");

    // Create Buttons and MenuButtons
    BoldAction bold = new BoldAction(webView);
    FontFamilyAction fontFamily = new FontFamilyAction(webView);
    FontSizeAction fontSize = new FontSizeAction(webView);
    UnderlineButton underline = new UnderlineButton(webView);
    Align align = new Align(webView);
    Italic italic = new Italic(webView);
    LineSpacing lineSpacing = new LineSpacing(webView);
    ParagraphSpacing paragraphSpacing = new ParagraphSpacing(webView);
    CharSpacing charSpacing = new CharSpacing(webView);
    LetterColor letterColor = new LetterColor(webView);
    BackgroundColor backgroundColor = new BackgroundColor(webView);
    HighLight highLight = new HighLight(webView);
    Ruler ruler = new Ruler(webView);

    // Add the Children to the Navigation Bar
    this.getChildren().addAll(fontFamily, fontSize, new Separator(Orientation.VERTICAL), bold,
        italic, underline, new Separator(Orientation.VERTICAL), align,
        new Separator(Orientation.VERTICAL), lineSpacing, paragraphSpacing, charSpacing,
        new Separator(Orientation.VERTICAL), letterColor, backgroundColor,
        new Separator(Orientation.VERTICAL), highLight, ruler);
  }
}
