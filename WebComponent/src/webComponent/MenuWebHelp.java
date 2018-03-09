package webComponent;

import java.io.File;

import javafx.scene.control.Menu;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MenuWebHelp {


	public void estilo(Menu tamanhoMenu) {
		File file = new File("icons/font-size.png");
        Image image = new Image(file.toURI().toString());
        tamanhoMenu.setGraphic(new ImageView(image));
	}

}
