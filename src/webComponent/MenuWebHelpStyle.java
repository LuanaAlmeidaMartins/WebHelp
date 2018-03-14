package webComponent;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuButton;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MenuWebHelpStyle {

	Properties p = new Properties();
	
	/**
	 * Construtor carrega o arquivo properties "styleProperties.txt" que contem o estilo do MenuBar
	 * */
	public MenuWebHelpStyle() {
		try {
			p.load(new FileInputStream(/*System.getProperty("user.dir") + "/styleProperties.txt"*/"styleProperties.txt"));
		} catch (FileNotFoundException e) {
			System.out.println("Arquivo styleProperties.txt nao encontrado");
		} catch (IOException e) {
			System.out.println("IO");
		}
	}
	
	/**
	 * Insere o icone no menu e as propriedades de cada subitem do menu
	 * */
	public void estilo(SplitMenuButton menu) {
		for(int i = 0; i < menu.getItems().size();i++) {
			menu.getItems().get(i).setStyle(p.getProperty(menu.getItems().get(i).getText().replaceAll("\\s+",".")));
			if(p.getProperty(menu.getItems().get(i).getText().replaceAll("\\s+","."))==null) {
				menu.getItems().get(i).setStyle(p.getProperty("default"));
			}
		}
		File file = new File(p.getProperty(menu.getId()));
		Image image = new Image(file.toURI().toString(), 28, 28, false, false);
		menu.setGraphic(new ImageView(image));
	}
	
	public void estilo(Button button) {
		File file = new File(p.getProperty(button.getId()));
		Image image = new Image(file.toURI().toString(), 28, 28, false, false);
		button.setGraphic(new ImageView(image));
	}
	
	public void estilo(ColorPicker colorPicker) {
		colorPicker.setStyle(p.getProperty("colorpicker"));
	}
}
