package buttons;

import java.util.Locale;
import javax.speech.Central;
import javax.speech.synthesis.Synthesizer;
import javax.speech.synthesis.SynthesizerModeDesc;
import javafx.concurrent.Worker.State;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import webhelp.WebHelpStyle;

public class Leitor extends HBox {

  private WebEngine webEngine;
  private Button leitor;
  
  private Canvas canvas;
  private WebView webView;

  public Leitor(WebView webView, Canvas canvas) {
    this.webView = webView;
    this.webEngine = webView.getEngine();
    this.canvas = canvas;
    createButton();
    actionButton();
  }

  void createButton() {
    leitor = new Button();
    leitor.setId("leitor");

    WebHelpStyle webHelpStyle = new WebHelpStyle();
    webHelpStyle.estilo(leitor);
  }

  public void actionButton() {

    webEngine.getLoadWorker().stateProperty().addListener((obs, oldValue, newValue) -> {
      if (newValue == State.SUCCEEDED) {
        canvas.setVisible(false);
        
        leitor.setOnMouseClicked(new EventHandler<MouseEvent>() {
          @Override
          public void handle(MouseEvent event) {
            configurarLeitor();
          }
        });
      }
    });
    this.getChildren().add(leitor);
  }

  private void configurarLeitor() {
    try {
      System.setProperty("freetts.voices",
          "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
      Central.registerEngineCentral("com.sun.speech.freetts.jsapi.FreeTTSEngineCentral");
      Synthesizer synthesizer = Central.createSynthesizer(new SynthesizerModeDesc(Locale.US));
      synthesizer.allocate();
      synthesizer.speakPlainText(
          webView.getEngine().executeScript("window.getSelection();").toString(), null);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
