package principal;

import buttons.Overlay;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.VBoxBuilder;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import navigation.NavigationBar;
import webhelp.WebHelpBar;


import javafx.scene.shape.Rectangle;
import javafx.scene.shape.RectangleBuilder;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextBuilder;
import javafx.scene.paint.Color;

public class Main extends Application {

  @SuppressWarnings("static-access")
  @Override
  public void start(final Stage stage) {

    // Set application icon
    stage.getIcons().add(new Image("webhelp.png"));

    // Set application home page
    String homePageUrl = "https://www.w3.org/TR/DOM-Level-2-Core/core.html#ID-F68F082";
    // String homePageUrl = "https://pt.wikipedia.org/wiki/Flor";

    // Create a WebView
    final WebView browser = new WebView();
    browser.setStyle("fx-padding:100;");

    // Get WebEngine via WebView and load home page
    final WebEngine webEngine = browser.getEngine();
    
    // When the website content is bigger than display area, the scroll pane is enabled
    ScrollPane scrollPane = new ScrollPane();
    scrollPane.setContent(new Group());
    // Create the navigation bar
    NavigationBar navigationBar = new NavigationBar(browser, homePageUrl);

    // Create the WebHelp bar
    Canvas overlay = new Canvas(1300,600);
    WebHelpBar webHelpBar = new WebHelpBar(browser, overlay);
    overlay.setAccessibleText("aa");
    StackPane stack = new StackPane();
    stack.getChildren().addAll(browser, overlay);
    stack.setMargin(browser, new Insets(12, 12, 10, 28));
    
    // Create the VBox, add the navigation, menu and webview to the VBox and

    VBox root = new VBox();
    root.getChildren().addAll(navigationBar, webHelpBar);
    root.getChildren().addAll(stack);
    

    // Create the Scene, add the Scene to the Stage and display the Stage
    Scene scene = new Scene(root,1600,900);
  
   
    // root.getChildren().add(g);
    stage.setScene(scene);
  //  webEngine.load(homePageUrl);
    stage.show();
  }
  


  public static void main(String[] args) {
    launch(args);
  }
}