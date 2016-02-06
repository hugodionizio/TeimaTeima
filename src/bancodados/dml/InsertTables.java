package bancodados.dml;

import bancodados.dao.VendaDao;
import bancodados.model.Venda;

public class InsertTables {

	public static void insertTables(String[] args) {
		(new VendaDao()).save((new Venda("Administrador")));
		(new VendaDao()).save((new Venda("Fulano")));
	}
}
