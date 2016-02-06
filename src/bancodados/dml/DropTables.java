package bancodados.dml;

import bancodados.dao.AcertoDao;
import bancodados.dao.LoteriaDao;
import bancodados.dao.NomeDao;
import bancodados.dao.PalpiteDao;
import bancodados.dao.SorteioDao;
import bancodados.dao.VendaDao;

public class DropTables {

	public static void main(String[] args) {
		boolean dropTables = true;

		if (dropTables) {
			if((new AcertoDao()).drop())
				System.out.println("Acertos removido.");			

			if((new PalpiteDao()).drop())
				System.out.println("Palpites removido.");

			if((new SorteioDao()).drop())
				System.out.println("Sorteios removido.");

			if((new LoteriaDao()).drop())
				System.out.println("Loteria removida.");

			if((new NomeDao()).drop())
				System.out.println("Concorrentes removido.");

			if((new VendaDao()).drop())
				System.out.println("Vendedores removido.");
		}
	}
}