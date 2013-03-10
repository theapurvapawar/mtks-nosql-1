import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import test.VoldemortClient; //importing voldemort client class from test package
import org.json.JSONObject;

/**
 * 
 * @author Apurva Pawar
 *
 */

/*
 * Class which extends the titleExtractor Class.
 * This class has a function which recursively lists all files within a folder.
 * The titleExtractor class helps to extract the required data from each file
 * like relative URL, Title of Document, and certain text from document.
 * Imports VoldemortClient class from test package for backend dump initialization
 * updated to parse only html documents
 * Requires JSON-lib-2.4-jdk15.jar for creating JSON objects and converting JSON to String/String to JSON
 * Successfully built temporary back end database with this code 
 */


public class SearchAndExtract extends titleExtractor {
	
	VoldemortClient KVpair = new VoldemortClient("tcp://localhost:6666","backend");//start server on command line
	//mention IP/Port and store name above
	titleExtractor doc = new titleExtractor();
	File folder;
	int j=0;//just a counter to display no. of documents extracted
	JSONObject json = new JSONObject();
	
	public void extractRecursively(String folderURL) throws MalformedURLException, IOException {
	    
		folder = new File(folderURL);
		
		for (final File fileEntry : folder.listFiles()) {
	        if (fileEntry.isDirectory()) {
	        	extractRecursively(fileEntry.toString()); // Recursively Searches the mentioned folder
	        } else {
	            doc.getURL(fileEntry.getAbsolutePath().toString());//gets document URL
	            
	            /*
	             * Prints required extracted stuff from each URL
	             * Required data is stored in 'relURL', 'title' and 'text' variables
	             * for further database processing 
	             */
	            
	            String extension = "";

	            int i = fileEntry.getAbsolutePath().toString().lastIndexOf('.');
	            if (i > 0) {
	                extension = fileEntry.getAbsolutePath().toString().substring(i+1);//check extension
	            }
	            j++;
	            
	            if(extension.equals("html"))//condition to parse only html
	            {
	            	
	            System.out.println("\n\n");
	            System.out.println("File No: "+j);
	            System.out.println("Full URL:"+fileEntry.getAbsolutePath().toString());//display URL
	            
	            
	            String relURL = fileEntry.getAbsolutePath().toString().replace(folderURL,"");
	            System.out.println("Relative URL:"+relURL);//display relative URL
	            
	            
	            String title=doc.getTitle();//gets title of HTML document
	            json.put("title", title);//puts title in json object	            
	            System.out.println(title==null ? "(none)" : title);//prints title
	            
	            
	    		String text=doc.getText();
	    		json.put("text", text);//puts text in json object
	    		System.out.println(text==null ? "(none)" : text);//prints document text
	    		
	    		String jsonstring = json.toString();//converts json object to string
	    		KVpair.putValueFor(relURL, jsonstring); //key/value are string in voldemort store
	            }
	        }
	    }
	}

	public static void main(String[] args) throws MalformedURLException, IOException {  
	
	String folder = "/media/FreeAgent GoFlex Drive/Wikipedia pages/pages/";
	SearchAndExtract list = new SearchAndExtract();
	list.extractRecursively(folder);
	
	}
}
