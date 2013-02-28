import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

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
 */


public class SearchAndExtract extends titleExtractor{
	
	titleExtractor doc = new titleExtractor();
	File folder;
	
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
	            
	            System.out.println("\n\n");
	            System.out.println("Full URL:"+fileEntry.getAbsolutePath().toString());//display URL
	            String relURL = fileEntry.getAbsolutePath().toString().replace(folderURL,"");
	            System.out.println("Relative URL:"+relURL);//display relative URL
	            String title=doc.getTitle();//gets title of HTML document
	            System.out.println(title==null ? "(none)" : title);//prints title
	    		String text=doc.getText();//prints document text
	    		System.out.println(text==null ? "(none)" : text);
	        }
	    }
	}

	public static void main(String[] args) throws MalformedURLException, IOException {  
	
	String folder = "/home/blackdragon/workspace/project-mtks/WebContent/mtks-nosql-1/javaFiles/sampleHTML";
	SearchAndExtract list = new SearchAndExtract();
	list.extractRecursively(folder);
	
	}
}
