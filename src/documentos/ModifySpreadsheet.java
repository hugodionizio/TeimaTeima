package documentos;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;

import org.jopendocument.dom.OOUtils;
import org.jopendocument.dom.spreadsheet.Sheet;
import org.jopendocument.dom.spreadsheet.SpreadSheet;

public class ModifySpreadsheet {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		// Load the file.
		 File file = new File("resultado.ods");
		 final Sheet sheet = SpreadSheet.createFromFile(file).getSheet(0);
		 // Change date.
		 sheet.getCellAt("I10").setValue(new Date());
		 // Change strings.
		 sheet.setValueAt("Filling test", 1, 1);
		 sheet.getCellAt("B27").setValue("On site support");
		 // Change number.
		 sheet.getCellAt("F24").setValue(3);
		 // Or better yet use a named range
		 // (relative to the first cell of the range, wherever it might be).
		 sheet.getSpreadSheet().getTableModel("Products").setValueAt(1, 5, 4);
		 // Save to file and open it.
		 File outputFile = new File("resultado.ods");
		 OOUtils.open(sheet.getSpreadSheet().saveAs(outputFile));
	 }

}
