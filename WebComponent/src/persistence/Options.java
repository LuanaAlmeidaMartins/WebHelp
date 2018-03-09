package persistence;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Options {
	
	ArrayList<Font> enorme = new ArrayList<Font>();
	ArrayList<Font> grande = new ArrayList<Font>();
	ArrayList<Font> medio = new ArrayList<Font>();
	ArrayList<Font> pequeno = new ArrayList<Font>();

	@SuppressWarnings("resource")
	public ArrayList<Font> FillOptions(int type) {
		try {
			String array[] = new String[5];
			
		    FileReader file = new FileReader(/*System.getProperty("user.dir") + "/fontSize.txt"*/"fontSize.txt");
		    BufferedReader read= new BufferedReader(file);
		      
		    String readLine = "";
		    int i = 0;
	        while ((readLine = read.readLine()) != null) {
	            	array = readLine.split(" ");
	            	enorme.add(new Font(array[i], array[i+type]));
	            }
		} catch (Exception e) {
			System.err.println("File not found");
		}
		return enorme;
	}
	
	public void print() {
		for (int i = 0; i < enorme.size(); i++) {
			System.out.println("Enorme\n"+enorme.get(i).tagName+"  "+enorme.get(i).getSize());
		}
		System.out.println();
	}
}
