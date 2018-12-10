package navigation;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebHistory;
import javafx.scene.web.WebView;

public class NavigationBar extends HBox {
  /**
   * Create the navigation bar with the webhelp back, forward and reload
   * 
   * @param webView
   *          webView created to show a web page
   * @param homePageUrl
   *          content to be loaded into webView
   */

  public NavigationBar(WebView webView, String homePageUrl) {

    // Set Spacing
    this.setSpacing(4);

    // Create the TextField
    TextField pageUrl = new TextField();
    pageUrl.setText(homePageUrl);

    // Let the TextField grow horizontallly
    HBox.setHgrow(pageUrl, Priority.ALWAYS);

    Button backButton = new Button("Back");
    backButton.setDisable(true);

    Button forwardButton = new Button("Front");
    forwardButton.setDisable(true);

    // Create the WebEngine
    WebEngine webEngine = webView.getEngine();

    // Add an ActionListener to navigate to the entered URL
    pageUrl.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        webEngine.load(pageUrl.getText());
        pageUrl.setOnKeyPressed(new EventHandler<KeyEvent>() {

          @Override
          public void handle(KeyEvent key) {
            if (key.getCode().equals(KeyCode.ENTER)) {
              webEngine.load(pageUrl.getText());
            }
          }
        });
      }
    });

    // Update the stage title when a new web page title is available
    webEngine.locationProperty().addListener(new ChangeListener<String>() {
      public void changed(ObservableValue<? extends String> ov, final String oldvalue,
          final String newvalue) {
        // Set the Title of the Stage
        pageUrl.setText(newvalue);
      }
    });

    Button refreshButton = new Button("Reload");
    // Add an ActionListener for the Refresh Button
    refreshButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        webEngine.reload();
      }
    });

    WebHistory history = webView.getEngine().getHistory();
    history.currentIndexProperty().addListener(new ChangeListener<Number>() {
      public void changed(ObservableValue<? extends Number> ov, final Number oldvalue,
          final Number newvalue) {
        int currentIndex = newvalue.intValue();
        if (currentIndex <= 0) {
          backButton.setDisable(true);
        } else {
          backButton.setDisable(false);
        }
        if (currentIndex <= history.getEntries().size() - 2) {
          forwardButton.setDisable(false);
        } else {
          forwardButton.setDisable(true);
        }
      }
    });

    // Add an ActionListener for the Back Button
    backButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        history.go(-1);
      }
    });

    // Add an ActionListener for the Home Button
    forwardButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        history.go(1);
      }
    });
    this.getChildren().addAll(backButton, forwardButton, refreshButton, pageUrl);
    webEngine.load(homePageUrl);
  }
}
