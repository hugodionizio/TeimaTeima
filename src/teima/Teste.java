package teima;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Teste {

	public static void testar(String[] args) throws ParseException {
		String date = "01/10/2015";
	    String initDateFormat = "dd/MM/yyyy";
	    String endDateFormat = "yyyy-MM-dd";

	    Date initDate = new SimpleDateFormat(initDateFormat).parse(date);
		SimpleDateFormat formatter = new SimpleDateFormat(endDateFormat);
	    String parsedDate = formatter.format(initDate);
	    
	    System.out.printf(parsedDate);
	}

}
