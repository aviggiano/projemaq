import java.io.File;
import java.util.ArrayList;


public class ARM {
	public ArrayList<String> readData (File f) {
		GParser gParser = new GParser();
		return gParser.readData(f);
	}

}
