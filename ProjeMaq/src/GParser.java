import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;


public class GParser {

	private ArrayList<String> lines;
	private String fileName;
	private String dir;
	private String extension;
	
	public ArrayList<String> readData(File f) {
		lines = new ArrayList<>();
		try {
			RandomAccessFile fichier = new java.io.RandomAccessFile(f, "r");

			String line = "";
			while ((line = fichier.readLine()) != null) // tant que la ligne n'est pas nulle
				lines.add(line);
			
			return lines;
		}

		catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

}
