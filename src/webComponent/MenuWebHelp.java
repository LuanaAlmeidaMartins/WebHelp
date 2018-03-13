package webComponent;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MenuWebHelp {

	Properties p = new Properties();
	
	/**
	 * Construtor carrega o arquivo properties "styleProperties.txt" que contem o estilo do MenuBar
	 * */
	public MenuWebHelp() {
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
	public void estilo(Menu tamanhoMenu) {
		for(int i = 0; i < tamanhoMenu.getItems().size();i++) {
			tamanhoMenu.getItems().get(i).setStyle(p.getProperty(tamanhoMenu.getItems().get(i).getText().replaceAll("\\s+",".")));
			if(p.getProperty(tamanhoMenu.getItems().get(i).getText().replaceAll("\\s+","."))==null) {
				tamanhoMenu.getItems().get(i).setStyle(p.getProperty("default"));
			}
		}
		File file = new File(p.getProperty(tamanhoMenu.getId()));
		Image image = new Image(file.toURI().toString(), 48, 48, false, false);
		tamanhoMenu.setGraphic(new ImageView(image));
	}
	
	public void estilo(Label label) {
		File file = new File(p.getProperty(label.getId()));
		Image image = new Image(file.toURI().toString(), 48, 48, false, false);
		label.setGraphic(new ImageView(image));
	}
	
	public void estilo(ColorPicker colorPicker) {
		colorPicker.setStyle(p.getProperty("colorpicker"));
	}

	public void estilo(MenuButton menuButton) {
		File file = new File(p.getProperty(menuButton.getId()));
		Image image = new Image(file.toURI().toString(), 48, 48, false, false);
		menuButton.setGraphic(new ImageView(image));
		
	}
}
