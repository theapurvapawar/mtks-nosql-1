import net.htmlparser.jericho.*;

import java.io.IOException;
import java.net.*;



/**
 * @author Apurva Pawar
 */

/*
 *	Reads/Parses HTML document and extracts title and certain text from document
 *	Tested on sample Wikipedia Pages
 *	Requires jericho-html-3.3.jar
 *	For more information, refer - http://jericho.htmlparser.net/docs/index.html
 */
 


public class titleExtractor {
	
	private String sourceUrlString;
	private static Source source;
	
	void getURL(String url) throws MalformedURLException, IOException
	{
		sourceUrlString = url;
		if (sourceUrlString.indexOf(':')==-1) sourceUrlString="file:"+sourceUrlString;
		MicrosoftConditionalCommentTagTypes.register();
		PHPTagTypes.register();
		PHPTagTypes.PHP_SHORT.deregister(); // remove PHP short tags for this example otherwise they override processing instructions
		MasonTagTypes.register();
		source = new Source(new URL(sourceUrlString));
		source.fullSequentialParse();
	}


	private String getTitle() {
		Element titleElement=source.getFirstElement(HTMLElementName.TITLE);
		if (titleElement==null) return null;		
		String title = CharacterReference.decodeCollapseWhiteSpace(titleElement.getContent());
		title=title.replace(" - Wikipedia, the free encyclopedia",""); //removes ' - Wikipedia, the free encyclopedia' from title text
		return title;
	}
	
	private String getText()
	{
		String renderedText=source.getRenderer().toString();
		if (renderedText==null) return null;			
		return "..."+renderedText.substring(500, 750)+"..."; // gets char starting from 500 to 700
	}
	
	

	public static void main(String[] args) throws Exception {
		

		titleExtractor doc = new titleExtractor();
		doc.getURL("/home/blackdragon/workspace/htmlParser/bin/data/Street_Fighter"); //put URL here
		
		System.out.println("Document title:");
		String title=doc.getTitle();
		System.out.println(title==null ? "(none)" : title);
		
		
		System.out.println("\nSimple rendering of the HTML document:\n");
		String text=doc.getText();
		System.out.println(text==null ? "(none)" : text);

		
  }
}
