package LocationSearch;
import java.awt.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CSVReader {

    
	public ArrayList<String> CsvReader(){
		String csvFile = "/Users/Philipps/Desktop/lucene/opengeodb.csv";
	    String line = "";
	    String cvsSplitBy = ";";
		ArrayList<String> list = new ArrayList();
		 try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

	            while ((line = br.readLine()) != null) {

	                // use comma as separator
	                String[] country = line.split(cvsSplitBy);
	                if(country[0].contains("/")){
	                	
	                } else {
	               	list.add(country[0]);
	                }
	            }

	        } catch (IOException e) {
	            e.printStackTrace();
	        }

		
		return list;
	}
}
