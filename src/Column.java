//import org.apache.tika.parser.html.HtmlParser;
import com.kizna.html.*;


public class Column {
	
	public static void main(String[] args) {
	//	HtmlParser htmlparser = new HtmlParser();
		HTMLParser htmlparser = new HTMLParser("example.html");
		htmlparser.parse("example.html");
				
		System.out.print("Fim da conversão.");
	}
	
}
