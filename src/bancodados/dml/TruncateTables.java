package bancodados.dml;

import bancodados.dao.AcertoDao;
import bancodados.dao.LoteriaDao;
import bancodados.dao.NomeDao;
import bancodados.dao.PalpiteDao;
import bancodados.dao.SorteioDao;
import bancodados.dao.VendaDao;

public class TruncateTables {

	public static void main(String[] args) {
		boolean truncateTables = false;
		
		if (truncateTables) {
			if((new VendaDao()).truncate())
				System.out.println("Vendedores zerado.");

			if((new NomeDao()).truncate())
				System.out.println("Concorrentes zerado.");

			if((new PalpiteDao()).truncate())
				System.out.println("Palpites zerado.");

			if((new LoteriaDao()).truncate())
				System.out.println("Loteria zerada.");

			if((new SorteioDao()).truncate())
				System.out.println("Sorteios zerado.");

			if((new AcertoDao()).truncate())
				System.out.println("Acertos zerado.");
		}
	}
}