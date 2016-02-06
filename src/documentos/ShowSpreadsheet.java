package documentos;

import javax.swing.JFrame;

import org.jopendocument.model.OpenDocument;
import org.jopendocument.panel.ODSViewerPanel;
import org.jopendocument.print.DefaultDocumentPrinter;

public class ShowSpreadsheet {

	public static void main(String[] args) {
		// Load the spreadsheet.
		  final OpenDocument doc = new OpenDocument();
		  doc.loadFrom("resultado.ods");

		  // Show time !
		  final JFrame mainFrame = new JFrame("Resultado");
		  DefaultDocumentPrinter printer = new DefaultDocumentPrinter();
		    
		  ODSViewerPanel viewerPanel = new ODSViewerPanel(doc, printer, true);

		  mainFrame.setContentPane(viewerPanel);
		  mainFrame.pack();
		  mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		  mainFrame.setLocation(10, 10);
		  mainFrame.setVisible(true);
	}

}
