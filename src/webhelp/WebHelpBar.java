package webhelp;

import apply.GeneralStyle;
import buttons.Align;
import buttons.BackgroundColor;
import buttons.Bold;
import buttons.CharSpacing;
import buttons.FontFamily;
import buttons.FontSize;
import buttons.HighLight;
import buttons.Italic;
import buttons.Leitor;
import buttons.LetterColor;
import buttons.LineSpacing;
import buttons.Overlay;
import buttons.ParagraphSpacing;
import buttons.Ruler;
import buttons.Underline;
import javafx.geometry.Orientation;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Separator;
import javafx.scene.layout.HBox;
import javafx.scene.web.WebView;

public class WebHelpBar extends HBox {

  static GeneralStyle generalStyle;
  Canvas overlay;

  public WebHelpBar(WebView webView, Canvas overlay) {
    this.overlay = overlay;
    this.setSpacing(2);
    this.setStyle("-fx-padding: 0, 0, 0, 0;");

    // Create Buttons and MenuButtons
    Bold bold = new Bold(webView);
    FontFamily fontFamily = new FontFamily(webView);
    FontSize fontSize = new FontSize(webView);
    Underline underline = new Underline(webView);
    Align align = new Align(webView);
    Italic italic = new Italic(webView);
    LineSpacing lineSpacing = new LineSpacing(webView);
    ParagraphSpacing paragraphSpacing = new ParagraphSpacing(webView);
    CharSpacing charSpacing = new CharSpacing(webView);
    LetterColor letterColor = new LetterColor(webView);
    BackgroundColor backgroundColor = new BackgroundColor(webView);
    Leitor leitor = new Leitor(webView, this.overlay);
    Ruler ruler = new Ruler(webView, this.overlay);
    Overlay overlay2 = new Overlay(webView, this.overlay);
    HighLight highLight = new HighLight(webView, this.overlay);

    // Add the Children to the Navigation Bar
    this.getChildren().addAll(fontFamily, fontSize, new Separator(Orientation.VERTICAL), bold,
        italic, underline, new Separator(Orientation.VERTICAL), align,
        new Separator(Orientation.VERTICAL), lineSpacing, paragraphSpacing, charSpacing,
        new Separator(Orientation.VERTICAL), letterColor, backgroundColor,
        new Separator(Orientation.VERTICAL), ruler, overlay2,  highLight, leitor);
  }

}
