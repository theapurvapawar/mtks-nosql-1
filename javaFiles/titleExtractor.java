import net.htmlparser.jericho.*;
import java.net.*;

/**
 * @author Apurva Pawar
 */

/*
 *	Reads/Parses HTML document and extracts title
 *	Tested on sample Wikipedia Page
 *	Requires jericho-html-3.3.jar
 *	For more information, refer - http://jericho.htmlparser.net/docs/index.html
 */
 


public class titleExtractor {
	public static void main(String[] args) throws Exception {
		String sourceUrlString="/home/blackdragon/workspace/htmlParser/bin/data/Wiki";//change path to desired HTML page
		if (args.length==0)
		  System.err.println("Using default argument of \""+sourceUrlString+'"');
		else
			sourceUrlString=args[0];
		if (sourceUrlString.indexOf(':')==-1) sourceUrlString="file:"+sourceUrlString;
		MicrosoftConditionalCommentTagTypes.register();
		PHPTagTypes.register();
		PHPTagTypes.PHP_SHORT.deregister(); // remove PHP short tags for this example otherwise they override processing instructions
		MasonTagTypes.register();
		Source source=new Source(new URL(sourceUrlString));

		// Call fullSequentialParse manually as most of the source will be parsed.
		//source.fullSequentialParse();

		System.out.println("Document title:");
		String title=getTitle(source);
		System.out.println(title==null ? "(none)" : title);

		
  }

	private static String getTitle(Source source) {
		Element titleElement=source.getFirstElement(HTMLElementName.TITLE);
		if (titleElement==null) return null;
		// TITLE element never contains other tags so just decode it collapsing whitespace:
		return CharacterReference.decodeCollapseWhiteSpace(titleElement.getContent());
	}

	
}