package documentos;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import org.jopendocument.dom.OOUtils;
import org.jopendocument.dom.spreadsheet.SpreadSheet;

import bancodados.dao.AcertoDao;
import bancodados.dao.CreateTables;
import bancodados.dao.LoteriaDao;
import bancodados.dao.NomeDao;
import bancodados.dao.PalpiteDao;
import bancodados.dao.SorteioDao;
import bancodados.dao.VendaDao;
import bancodados.model.Acerto;
import bancodados.model.Loteria;
import bancodados.model.Nome;
import bancodados.model.Palpite;
import bancodados.model.Sorteio;
import bancodados.model.Venda;

public class Planilha {

	public static void criarVendas() throws FileNotFoundException, IOException,
			ParseException {

		// Create the data to save.
		(new CreateTables()).createTableVendas();
		VendaDao daoVenda = new VendaDao();
		List<Venda> vendas = daoVenda.findVendas();
		final Object[][] dados = new Object[daoVenda.findNumVendas()][2];

		int i = 1;
		for (Venda venda : vendas) {
			dados[i - 1] = new Object[] { venda.getId_vendedor(),
					venda.getNomeVendedor() };
			i++;
		}

		String[] columns = new String[] { "Nº Vendedor", "Vendedor" };

		TableModel model = new DefaultTableModel(dados, columns);

		// Save the data to an ODS file and open it.
		final File file = new File("Vendedores.ods");
		SpreadSheet.createEmpty(model).saveAs(file);

		OOUtils.open(file);
	}

	public static void criarNomes() throws FileNotFoundException, IOException,
			ParseException {

		// Create the data to save.
		(new CreateTables()).createTableNomes();
		NomeDao daoNome = new NomeDao();
		List<Nome> nomes = daoNome.findNomes();
		final Object[][] dados = new Object[daoNome.findNumNomes()][6];

		int i = 1;
		for (Nome nome : nomes) {
			dados[i - 1] = new Object[] { nome.getId_concorrente(),
					nome.getNomeConcorrente(), nome.getReferencia(),
					nome.getEndereco(), nome.getFone(), nome.getEmail() };
			i++;
		}

		String[] columns = new String[] { "Nº Concorrente", "Nome",
				"Referência", "Endereço", "Telefone", "E-mail" };

		TableModel model = new DefaultTableModel(dados, columns);

		// Save the data to an ODS file and open it.
		final File file = new File("Nomes.ods");
		SpreadSheet.createEmpty(model).saveAs(file);

		OOUtils.open(file);
	}

	public static void criarLoteria() throws FileNotFoundException,
			IOException, ParseException {

		// Create the data to save.
		(new CreateTables()).createTableLoteria();
		LoteriaDao daoLoteria = new LoteriaDao();
		List<Loteria> concursos = daoLoteria.findConcursos();
		final Object[][] dados = new Object[daoLoteria.findNumConcursos()][2];

		int i = 1;
		for (Loteria concurso : concursos) {

			dados[i - 1] = new Object[] { concurso.getId_concurso(),
					concurso.getConcurso(0), concurso.getConcurso(1),
					concurso.getConcurso(2), concurso.getConcurso(3),
					concurso.getConcurso(4) };
			i++;
		}

		String[] columns = new String[] { "Concurso", "1ª Dezena", "2ª Dezena",
				"3ª Dezena", "4ª Dezena", "5ª Dezena" };

		TableModel model = new DefaultTableModel(dados, columns);

		// Save the data to an ODS file and open it.
		final File file = new File("Loteria.ods");
		SpreadSheet.createEmpty(model).saveAs(file);

		OOUtils.open(file);
	}

	public static void criarSorteios() throws FileNotFoundException,
			IOException, ParseException {

		// Create the data to save.
		String data;

		(new CreateTables()).createTableSorteios();
		SorteioDao daoSorteio = new SorteioDao();
		List<Sorteio> sorteios = daoSorteio.findSorteios();
		final Object[][] dados = new Object[daoSorteio.findNumSorteios()][7];

		int i = 1;
		for (Sorteio sorteio : sorteios) {
			String dataTmp = sorteio.getData();
			String initDateFormat = "yyyy-MM-dd";
			String endDateFormat = "dd/MM/yyyy";

			Date initDate = new SimpleDateFormat(initDateFormat).parse(dataTmp);
			SimpleDateFormat formatter = new SimpleDateFormat(endDateFormat);
			data = formatter.format(initDate);

			dados[i - 1] = new Object[] { sorteio.getId_sorteio(), data,
					sorteio.getSorteio(0), sorteio.getSorteio(1),
					sorteio.getSorteio(2), sorteio.getSorteio(3),
					sorteio.getSorteio(4) };
			i++;
		}

		String[] columns = new String[] { "Concurso", "Data", "1ª Dezena",
				"2ª Dezena", "3ª Dezena", "4ª Dezena", "5ª Dezena" };

		TableModel model = new DefaultTableModel(dados, columns);

		// Save the data to an ODS file and open it.
		final File file = new File("Sorteios.ods");
		SpreadSheet.createEmpty(model).saveAs(file);

		OOUtils.open(file);
	}

	public static void criarPalpites() throws FileNotFoundException,
			IOException, ParseException {

		// Create the data to save
		(new CreateTables()).createTableVendas();
		(new CreateTables()).createTableNomes();
		(new CreateTables()).createTableSorteios();
		(new CreateTables()).createTablePalpites();
		PalpiteDao daoPalpite = new PalpiteDao();
		List<Palpite> palpites = daoPalpite.findPalpites();
		final Object[][] dados = new Object[daoPalpite.findNumPalpites()][9];

		int i = 1;
		for (Palpite palpite : palpites) {
			dados[i - 1] = new Object[] { palpite.getId_palpite(),
					palpite.getId_vendedor(), palpite.getId_concorrente(),
					palpite.getId_sorteio(), palpite.getPalpite(0),
					palpite.getPalpite(1), palpite.getPalpite(2),
					palpite.getPalpite(3), palpite.getPalpite(4) };
			i++;
		}

		String[] columns = new String[] { "Nº Palpite", "Vendedor",
				"Concorrente", "Sorteio", "1ª Dezena", "2ª Dezena",
				"3ª Dezena", "4ª Dezena", "5ª Dezena" };

		TableModel model = new DefaultTableModel(dados, columns);

		// Save the data to an ODS file and open it.
		final File file = new File("Palpites.ods");
		SpreadSheet.createEmpty(model).saveAs(file);

		OOUtils.open(file);
	}

	public static void criarAcertos() throws FileNotFoundException,
			IOException, ParseException {

		// Create the data to save.
		String vend;
		String acertoS;
		String nome;
		// int[] palpitesV = new int[] { 3, 15, 22, 45, 67 };
		String palpites;
		// String[] sorteioV = new String[] { "10/12/2013", "03-19-45-49-80" };
		String sorteio;

		(new CreateTables()).createTableVendas();
		(new CreateTables()).createTableNomes();
		(new CreateTables()).createTableSorteios();
		(new CreateTables()).createTableAcertos();
		AcertoDao daoAcerto = new AcertoDao();
		AcertoDao.contar();
		List<Acerto> acertos = new AcertoDao().findAcertos();
		PalpiteDao daoPalpite = new PalpiteDao();
		VendaDao daoVenda = new VendaDao();
		NomeDao daoNome = new NomeDao();
		SorteioDao daoSorteio = new SorteioDao();

		final Object[][] dados = new Object[daoAcerto.findNumAcertos()][5];

		int i = 0;
		for (Acerto acertoA : acertos) {
			int id_palpite = acertoA.getId_palpite();
			System.out.println("id_palpite = "+id_palpite);
			int id_vendedor;
			int id_concorrente = acertoA.getId_concorrente();
			int id_sorteio = acertoA.getId_sorteio();

			id_vendedor = daoPalpite.backPalpite(id_palpite).getId_vendedor();

			int prioridade = 5;
			switch(acertoA.getNumero()) {
				case 5: prioridade = 1; break;
				case 4: prioridade = 2; break;
				case 3: prioridade = 3; break;
				case 2: prioridade = 4; break;
				case 1: prioridade = 5; break;
			}
			vend = "VEND " + id_vendedor + " "
					+ daoVenda.backVenda(id_vendedor).getNomeVendedor() + " "
					+ daoVenda.countVendasBy(id_vendedor).toString();
			acertoS = "" + prioridade + " " + acertoA.getTipoAcerto();
			nome = daoNome.backNome(id_concorrente).getNomeConcorrente() + " (";
			if(daoNome.backNome(id_concorrente).getReferencia() != null)
				nome+= daoNome.backNome(id_concorrente).getReferencia();
			else
				if(daoNome.backNome(id_concorrente).getFone() != null)
					nome+= daoNome.backNome(id_concorrente).getFone();
				else
					if(daoNome.backNome(id_concorrente).getEndereco() != null)
						nome+= daoNome.backNome(id_concorrente).getEndereco();
					else {
						if (daoNome.backNome(id_concorrente).getEmail() != null)
							nome+= daoNome.backNome(id_concorrente).getEmail();
						else
							nome+= "desconhecido";
					}
			nome+= ")";
			palpites = "";
			for (int j = 0; j < 5; j++) {
				if (acertoA.getAcertos(j)) {
					palpites += "["
							+ daoPalpite.backPalpite(id_palpite).getPalpite(j)
							+ "]";
				} else
					palpites += daoPalpite.backPalpite(id_palpite)
							.getPalpite(j);

				if (j < 5 - 1)
					palpites += " ";
			}

			String data = daoSorteio.backSorteio(id_sorteio).getData();
			String initDateFormat = "yyyy-MM-dd";
			String endDateFormat = "dd/MM/yyyy";

			Date initDate = new SimpleDateFormat(initDateFormat).parse(data);
			SimpleDateFormat formatter = new SimpleDateFormat(endDateFormat);
			sorteio = formatter.format(initDate) + " (";
			for (int j = 0; j < 5; j++) {
				if(daoSorteio.backSorteio(id_sorteio) != null) {
					sorteio += daoSorteio.backSorteio(id_sorteio).getSorteio(j);
					if (j < 5 - 1)
						sorteio += " ";					
				}
				else break;
			}
			sorteio += ")";

			dados[i] = new Object[] { vend, acertoS, nome, palpites, sorteio };
			i++;
		}

		String[] columns = new String[] { "VEND", "ACERTO", "NOME", "PALPITES",
				"SORTEIO" };

		TableModel model = new DefaultTableModel(dados, columns);

		// MutableCell<SpreadSheet> model2 = new MutableCell<SpreadSheet>(null,
		// null, null);
		// model2.merge(1, 2);

		// Save the data to an ODS file and open it.
		final File file = new File("Acertos.ods");
		SpreadSheet.createEmpty(model).saveAs(file);

		OOUtils.open(file);
	}

	public static void testSpreadsheet(String[] args)
			throws FileNotFoundException, IOException {
		// Create the data to save.

		List<Acerto> acertos = new AcertoDao().findAcertos();
		for (Acerto acertoA : acertos) {
			System.out
					.println("TeimaTeima.............:\n" + acertoA.toString());
		}

		String vend = "VEND 002 FULANO 1";
		String acertoS = "02 QUADR";
		String nome = "PEDRINHO (PEDRINHO MARCENARIA)";
		int[] palpitesV = new int[] { 3, 15, 22, 45, 67 };
		String palpites = palpitesV[0] + " " + palpitesV[1] + " "
				+ palpitesV[2] + " " + palpitesV[3] + " " + palpitesV[4] + " ";
		String[] sorteioV = new String[] { "10/12/2013", "03-19-45-49-80" };
		String sorteio = sorteioV[0] + " (" + sorteioV[1] + ")";

		final Object[][] data = new Object[2][5];
		data[0] = new Object[] { vend, acertoS, nome, palpites, sorteio };

		vend = "VEND 003 SICRANO 1";
		acertoS = "02 QUADR";
		nome = "LUIZNHO (88888-8888)";
		palpitesV[0] = 2;
		palpitesV[1] = 3;
		palpitesV[2] = 22;
		palpitesV[3] = 45;
		palpitesV[4] = 67;
		palpites = palpitesV[0] + " " + palpitesV[1] + " " + palpitesV[2] + " "
				+ palpitesV[3] + " " + palpitesV[4] + " ";
		sorteioV[0] = "17/12/2013";
		sorteioV[1] = "22-23-24-44-67";
		sorteio = sorteioV[0] + " (" + sorteioV[1] + ")";
		data[1] = new Object[] { vend, acertoS, nome, palpites, sorteio };

		String[] columns = new String[] { "VEND", "ACERTO", "NOME", "PALPITES",
				"SORTEIO" };

		TableModel model = new DefaultTableModel(data, columns);

		// MutableCell<SpreadSheet> model2 = new MutableCell<SpreadSheet>(null,
		// null, null);
		// model2.merge(1, 2);

		// Save the data to an ODS file and open it.
		final File file = new File("Acertos.ods");
		SpreadSheet.createEmpty(model).saveAs(file);

		OOUtils.open(file);
	}
}
