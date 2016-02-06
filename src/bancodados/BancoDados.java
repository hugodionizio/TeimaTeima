package bancodados;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import bancodados.dao.AcertoDao;
import bancodados.dao.CreateTables;
import bancodados.dao.LoteriaDao;
import bancodados.dao.NomeDao;
import bancodados.dao.PalpiteDao;
import bancodados.dao.SorteioDao;
import bancodados.dao.VendaDao;
import bancodados.dml.InsertTables;
import bancodados.model.Acerto;
import bancodados.model.Loteria;
import bancodados.model.Nome;
import bancodados.model.Palpite;
import bancodados.model.Sorteio;
import bancodados.model.Venda;
import bancodados.view.ListTables;
import documentos.Planilha;

public class BancoDados {
	static boolean vendas;
	static boolean nomes;
	static boolean concursos;
	static boolean sorteios;
	static boolean palpites;
	static boolean acertos;
	
	public BancoDados() throws ParseException, FileNotFoundException, IOException {
		criarTabelas();

		if(!vendas)
			inserirVendas();
		listarVendas();

		if(!nomes)
			inserirNomes();
		listarNomes();

		if(!concursos)
			inserirConcursos();
		listarConcursos();

		if(!sorteios)
			inserirSorteios();
		listarSorteios();

		if(!palpites)
			inserirPalpites();
		listarPalpites();

		if(!acertos) {
			inserirAcertos();
			String[] args = null;
			new Planilha().criarAcertos();
		}
		listarAcertos();
	}

	private static void criarTabelas() {
		vendas = new CreateTables().createTableVendas();
		nomes = new CreateTables().createTableNomes();
		concursos = new CreateTables().createTableLoteria();
		sorteios = new CreateTables().createTableSorteios();
		palpites = new CreateTables().createTablePalpites();
		acertos = new CreateTables().createTableAcertos();
	}

	private static void inserirVendas() {
		VendaDao daoVenda = new VendaDao();
	
		Venda v1 = new Venda("Administrador");
		daoVenda.save(v1);
	
		Venda v2 = new Venda("Fulano");
		daoVenda.save(v2);
	
		Venda v3 = new Venda("Sicrano");
		daoVenda.save(v3);
	
		Venda v4 = new Venda("Beltrano");
		daoVenda.save(v4);
	}

	private static void listarVendas() {
		List<Venda> vendas = new VendaDao().findVendas();
		for (Venda venda : vendas) {
			System.out.println("TeimaTeima.............:\n" + venda.toString());
		}
	}

	private static void inserirNomes() {
		NomeDao daoNome = new NomeDao();

		Nome n = new Nome("Pedrinho", 18, "Pedrinho Marcenaria", "Rua das Marcenarias",
				"(84) 99999-9999", "pedrinho@pedrinhomarcenaria.com");
		daoNome.save(n);

		n = new Nome("Luizinho", 40, "Luizinho Tratores", "Avenida dos Tratores", "(84) 88888-8888",
				"luizinho@luizinhotratores.com");
		daoNome.save(n);

		n = new Nome("Zezinho", 35, "Zezinho Tijolos", "Avenida dos Tijolos",
				"(84) 77777-7777", "zezinho@zezinhotijolos.com");
		daoNome.save(n);

		n = new Nome("Joãozinho", 30, "Joãozinho Compressores", "Rua dos Compressores",
				"(84) 66666-6666", "joaozinho@joaozinhocompressores.com");
		daoNome.save(n);
	}

	private static void listarNomes() {
		List<Nome> nomes = new NomeDao().findNomes();
		for (Nome nome : nomes) {
			System.out.println("TeimaTeima.............:\n" + nome.toString());
		}
	}

	private static void inserirConcursos() {
		LoteriaDao daoConcurso = new LoteriaDao();
		int[][] cv = { { 14, 21, 25, 26, 47 }, { 23, 40, 42, 75, 77 },
				{ 17, 26, 29, 46, 70 }, { 10, 21, 22, 36, 63 },
				{ 21, 38, 49, 61, 79 }, { 17, 21, 44, 70, 71 },
				{ 34, 44, 46, 63, 74 }, { 9, 15, 23, 31, 54 },
				{ 3, 19, 45, 49, 80 }, { 22, 23, 24, 44, 67 } };
	
		Loteria c;
		for (int i = 0; i < cv.length; i++) {
			c = new Loteria(cv[i]);
			daoConcurso.save(c);
		}
	}

	private static void listarConcursos() {
		List<Loteria> concursos = new LoteriaDao().findConcursos();
		for (Loteria concurso : concursos) {
			System.out.println("TeimaTeima.............:\n" + concurso.toString());
		}
	}

	private static void inserirSorteios() throws ParseException {
		SorteioDao daoSorteio = new SorteioDao();
		int[][] sv = {
				{ 14, 21, 25, 26, 47 },
				{ 23, 40, 42, 75, 77 },
				{ 17, 26, 29, 46, 70 },
				{ 10, 21, 22, 36, 63 },
				{ 21, 38, 49, 61, 79 },
				{ 17, 21, 44, 70, 71 },
				{ 34, 44, 46, 63, 74 },
				{ 9, 15, 23, 31, 54 },
				{ 3, 19, 45, 49, 80 },
				{ 22, 23, 24, 44, 67 } };
	
		String[] dv = {
				"13/08/2013",
				"20/08/2013",
				"27/08/2013",
				"03/09/2013",
				"10/09/2013",
				"17/09/2013",
				"24/09/2013",
				"01/10/2013",
				"10/12/2013",
				"17/12/2013" };
		
		Sorteio s;
		for (int i = 0; i < sv.length; i++) {
			s = new Sorteio(sv[i], dv[i]);
			daoSorteio.save(s);
		}
	
		s = new Sorteio(true);
		daoSorteio.save(s);
	}

	private static void listarSorteios() {
		List<Sorteio> sorteios = new SorteioDao().findSorteios();
		for (Sorteio sorteio : sorteios) {
			System.out.println("TeimaTeima.............:\n" + sorteio.toString());
		}
	}

	private static void inserirPalpites() {
		PalpiteDao daoPalpite = new PalpiteDao();

		int[] pv = { 03, 15, 22, 45, 67 };
		Palpite p = new Palpite(pv, 1, 1, 1);
		daoPalpite.save(p);

		pv = new int[] { 9, 15, 23, 26, 34 };
		p = new Palpite(pv, 2, 2, 3);
		daoPalpite.save(p);

		pv = new int[] { 03, 06, 23, 45, 68 };
		p = new Palpite(pv, 3, 3, 5);
		daoPalpite.save(p);

		pv = new int[] { 01, 05, 12, 29, 35 };
		p = new Palpite(pv, 3, 4, 8);
		daoPalpite.save(p);
	}

	private static void listarPalpites() {
		List<Palpite> palpites = new PalpiteDao().findPalpites();
		for (Palpite palpite : palpites) {
			System.out.println("TeimaTeima.............:\n" + palpite.toString());
		}
	}

	private static void inserirAcertos() {
		AcertoDao daoAcertos = new AcertoDao();
		PalpiteDao daoPalpite = new PalpiteDao();
		SorteioDao daoSorteio = new SorteioDao();
		int numSorteios = daoSorteio.findNumSorteios();
		int numPalpites = daoPalpite.findNumPalpites();

		for (int j = 0; j < numSorteios; j++) {
			for (int i = 0; i < numPalpites; i++) {
				Acerto a = new Acerto(i, j);
				if(a.getJogo() > 0) {
					System.out.println("Acerto "+a);
					daoAcertos.save(a);								
				}
			}			
		}
	}

	private static void listarAcertos() {
		List<Acerto> acertos = new AcertoDao().findAcertos();
		for (Acerto acerto : acertos) {
			System.out.println("TeimaTeima.............:\n" + acerto.toString());
		}
	}
	
	public static void main (String[] args) {
		// Vendedores
		VendaDao.demoVendedor();

		// Concorrentes
		NomeDao.demoConcorrentes();

		// Loteria
		LoteriaDao.demoLoteria();

		// Sorteios
		SorteioDao.demoSorteios();

		// Palpites
		PalpiteDao.demoPalpites();

		// Acertos
		AcertoDao.demoAcertos();
	}
}