import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/*import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.html.HtmlParser;
import org.apache.tika.sax.BodyContentHandler;
*/
import org.xml.sax.SAXException;

public class HtmlParse {

   public static void main(final String[] args) throws IOException,SAXException
//   ,	TikaException
   {
	   
	   //BodyContentHandler handler = new BodyContentHandler(-1);

	   /*InputStream stream = ContentHandlerExample.class.getResourceAsStream("test.doc");
	   AutoDetectParser parser = new AutoDetectParser();
	   Metadata metadata = new Metadata();
	   try {
	       parser.parse(stream, handler, metadata);
	       return handler.toString();
	   } finally {
	       stream.close();
	   }*/


      //detecting the file type
      //BodyContentHandler handler = new BodyContentHandler();
      /*Metadata metadata = new Metadata();
      FileInputStream inputstream = new FileInputStream(new File("D_QUINA.HTM"));
      ParseContext pcontext = new ParseContext();
      
      //Html parser 
      HtmlParser htmlparser = new HtmlParser();
      htmlparser.parse(inputstream, handler, metadata,pcontext);
      System.out.println("Contents of the document:" + handler.toString());
      System.out.println("Metadata of the document:");
      String[] metadataNames = metadata.names();
      
      for(String name : metadataNames) {
         System.out.println(name + ":   " + metadata.get(name));  
      }*/
   }
}