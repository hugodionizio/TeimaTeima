/*
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */ 

package acesso;

/*
 * SimpleTableDemo.java requires no other files.
 */

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Entrada extends JPanel {
    private boolean DEBUG = false;

    public Entrada() {
        super(new GridLayout(1,0));

        String[] columnNames = {"Vend", 
        						"Acerto",
        						"Nome",
        						"Palpites",
        						"Sorteio"
        						};
        
        String vend = "VEND 001 EUDES 230";
		String acerto = "02 QUADR";
		String nome = "PEDRO (PITER LANCHES)";
		int[] palpitesV = new int[] {3, 15, 22, 45, 67};
		String palpites = palpitesV[0] + " " + palpitesV[1] + " " + palpitesV[2] +
				" " + palpitesV[3] + " " + palpitesV[4] + " ";
		String[] sorteioV = new String[] {"10/12/2013", "03-19-45-49-80"};
		String sorteio = sorteioV[0] + " (" + sorteioV[1] + ")";

		Object[][] data = new Object[2][5];
		  data[0] = new Object[] { vend, acerto, nome, palpites, sorteio };
		  
		vend = "VEND 028 CEICAO 22"; acerto = "02 QUADR"; nome = "EDILSON CRUZ (9978-4813)";
		palpitesV[0] = 2; palpitesV[1] = 3; palpitesV[2] = 22; palpitesV[3] = 45; palpitesV[4] = 67;
		palpites = palpitesV[0] + " " + palpitesV[1] + " " + palpitesV[2] +
				" " + palpitesV[3] + " " + palpitesV[4] + " ";
		sorteioV[0] = "17/12/2013"; sorteioV[1] = "22-23-24-44-67";
		sorteio = sorteioV[0] + " (" + sorteioV[1] + ")";
		data[1] = new Object[] { vend, acerto, nome, palpites, sorteio };

        final JTable table = new JTable(data, columnNames);
        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        table.setFillsViewportHeight(true);

        if (DEBUG) {
            table.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    printDebugData(table);
                }
            });
        }

        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table);

        //Add the scroll pane to this panel.
        add(scrollPane);
    }

    private void printDebugData(JTable table) {
        int numRows = table.getRowCount();
        int numCols = table.getColumnCount();
        javax.swing.table.TableModel model = table.getModel();

        System.out.println("Value of data: ");
        for (int i=0; i < numRows; i++) {
            System.out.print("    row " + i + ":");
            for (int j=0; j < numCols; j++) {
                System.out.print("  " + model.getValueAt(i, j));
            }
            System.out.println();
        }
        System.out.println("--------------------------");
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("Lotadinha");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        Entrada newContentPane = new Entrada();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
