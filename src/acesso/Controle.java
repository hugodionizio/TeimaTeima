package acesso;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
 
 
public class Controle extends JFrame {
     
    private String[] columnNames = 
        {"Vend", "Acerto", "Nome", "Palpites", "Sorteio"};
     
    private Object[][] data;
     
    private JButton jbtAddRow = new JButton("Novo sorteio");
    private JButton jbtSave = new JButton("Salvar alterações");
    private JButton jbtLoad = new JButton("Carregar sorteios");
         
    private DefaultTableModel tableModel = new DefaultTableModel(data, columnNames);
    private JTable myJTable = new JTable(tableModel);
     
    private JFileChooser myJFileChooser = new JFileChooser(new File("."));
     
    private void saveTable() {
        if (myJFileChooser.showSaveDialog(this) ==
                JFileChooser.APPROVE_OPTION ) {
            saveTable(myJFileChooser.getSelectedFile());
        }
    }
     
    private void saveTable(File file) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(
                    new FileOutputStream(file));
                out.writeObject(tableModel.getDataVector());
                out.close();
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
    }
     
    private void loadTable() {
        if (myJFileChooser.showOpenDialog(this) ==
                JFileChooser.APPROVE_OPTION )
            loadTable(myJFileChooser.getSelectedFile());
    }
     
    private void loadTable(File file) {
        try {
            ObjectInputStream in = new ObjectInputStream(
            new FileInputStream(file));
            Vector<?> rowData = (Vector<?>)in.readObject();
            Iterator<?> itr = rowData.iterator();
            while(itr.hasNext()) {
                tableModel.addRow((Vector<?>) itr.next());
            }
            in.close();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
     
    public Controle() {
         
        add(jbtAddRow, BorderLayout.NORTH);
         
        add(new JScrollPane(myJTable), BorderLayout.CENTER);
         
        JPanel panel = new JPanel(new java.awt.GridLayout(1, 2));
        panel.add(jbtSave);
        panel.add(jbtLoad);
        add(panel, BorderLayout.SOUTH);
         
        jbtAddRow.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (myJTable.getSelectedRow() >= 0 )
                    tableModel.insertRow (myJTable.getSelectedRow(),
                        new java.util.Vector());
                else
                    tableModel.addRow(new java.util.Vector());
            }
        });
         
        jbtSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveTable();
            }
        });
 
        jbtLoad.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loadTable();
            }
        });
         
        // Set combo box as the editor for the vend column
        TableColumn vendColumn = myJTable.getColumn("Vend");
         
        // Create a combo box for acerto
        JComboBox<String> jcboAcerto = new JComboBox<String>();
        jcboAcerto.addItem("03 TERN0");
        jcboAcerto.addItem("04 QUADR");
        jcboAcerto.addItem("05 QUINA");
                         
        // Set combo box as the editor for the acerto column
        TableColumn acertoColumn = myJTable.getColumn("Acerto");
        acertoColumn.setCellEditor(
        new DefaultCellEditor(jcboAcerto));

        // Set combo box as the editor for the nome column
        TableColumn nomeColumn = myJTable.getColumn("Nome");

        // Set combo box as the editor for the palpites column
        TableColumn palpitesColumn = myJTable.getColumn("Palpites");

        // Set combo box as the editor for the sorteio column
        TableColumn sorteioColumn = myJTable.getColumn("Sorteio");
    }
 
    public static void main(String[] args) {
     
        Controle frame = new Controle();
        frame.setTitle("Lotadinha");
        frame.setSize(800, 400);
        frame.setLocationRelativeTo(null); // Center the frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
 
    }
 
}
