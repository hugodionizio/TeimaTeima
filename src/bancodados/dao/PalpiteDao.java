package bancodados.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.SpinnerNumberModel;

import bancodados.model.Acerto;
import bancodados.model.Loteria;
import bancodados.model.Nome;
import bancodados.model.Palpite;
import bancodados.model.Sorteio;
import bancodados.model.Venda;
import bancodados.view.ListTables;

public class PalpiteDao extends GenericDao {

	public boolean save(Palpite palpite) {
		String sql = "INSERT INTO PALPITES(palpite1, palpite2, palpite3,"
				+ " palpite4, palpite5, id_vendedor, id_concorrente, id_sorteio) VALUES (?,?,?,?,?,?,?,?)";
		return save(sql, palpite.getPalpite(0), palpite.getPalpite(1),
				palpite.getPalpite(2), palpite.getPalpite(3),
				palpite.getPalpite(4), palpite.getId_vendedor(),
				palpite.getId_concorrente(), palpite.getId_sorteio());
	}

	public void update(Palpite palpite) {
		String sql = "UPDATE PALPITES "
				+ "SET palpite1 = ?, palpite2 = ?, palpite3 = ?, palpite4 = ?,"
				+ " palpite5 = ?, id_vendedor = ?, id_concorrente = ?, id_sorteio = ? "
				+ "where id_palpite = ?";
		update(sql, palpite.getPalpite(0), palpite.getPalpite(1),
				palpite.getPalpite(2), palpite.getPalpite(3),
				palpite.getPalpite(4), palpite.getId_vendedor(),
				palpite.getId_concorrente(), palpite.getId_sorteio(),
				palpite.getId_palpite());
	}

	public void delete(Palpite palpite) {
		String sql = "DELETE FROM PALPITES WHERE id_palpite = ? ";
		delete(sql, palpite.getId_palpite());
	}

	public List<Palpite> findPalpites() {
		List<Palpite> palpites = new ArrayList<Palpite>();
		String sql = "SELECT * FROM PALPITES";
		try {
			PreparedStatement pstmt = getConnection().prepareStatement(sql);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				Palpite palpite = new Palpite();
				palpite.setId_palpite(rs.getInt("id_palpite"));
				palpite.setPalpite(rs.getInt("palpite1"), 0);
				palpite.setPalpite(rs.getInt("palpite2"), 1);
				palpite.setPalpite(rs.getInt("palpite3"), 2);
				palpite.setPalpite(rs.getInt("palpite4"), 3);
				palpite.setPalpite(rs.getInt("palpite5"), 4);
				palpite.setId_vendedor(rs.getInt("id_vendedor"));
				palpite.setId_concorrente(rs.getInt("id_concorrente"));
				palpite.setId_sorteio(rs.getInt("id_sorteio"));
				palpites.add(palpite);
			}

			rs.close();
			pstmt.close();
			return palpites;
		} catch (SQLException e) {
			System.out.println(e);
		}
		return null;
	}

	public int findIdSorteio(int id) {
		int idSorteio = 0;

		String sql = "SELECT * FROM PALPITES WHERE id_palpite = " + id;
		try {
			PreparedStatement pstmt = getConnection().prepareStatement(sql);

			ResultSet rs = pstmt.executeQuery();

			idSorteio = rs.getInt("id_sorteio");
			rs.close();
			pstmt.close();
			return idSorteio;
		} catch (SQLException e) {
			System.out.println(e);
		}
		return idSorteio;
	}

	public int findDezena(int id, int pos) {
		int dezena = 0;

		String sql = "SELECT * FROM PALPITES";
		try {
			PreparedStatement pstmt = getConnection().prepareStatement(sql);

			ResultSet rs = pstmt.executeQuery();

			// if() {
			// }
			for (int i = 1; i < findNum("PALPITES"); i++) {
				rs.next();
				if (i == id) {
					dezena = rs.getInt("palpite" + (pos + 1));
					// System.out.println("Palpite "+id+" = "+dezena);
				}
			}
			// while();
			// String dezenaS = ;

			rs.close();
			pstmt.close();
			return dezena;
		} catch (SQLException e) {
			// System.out.println(e);
			System.out.println("Dezena " + (pos + 1) + " do Palpite "
					+ (id + 1) + " não encontrada");
		}
		return dezena;
	}

	public int findConcorrente(int id) {
		int idConcorrente = 0;

		String sql = "SELECT * FROM PALPITES";
		try {
			PreparedStatement pstmt = getConnection().prepareStatement(sql);

			ResultSet rs = pstmt.executeQuery();

			for (int i = 1; i < findNum("PALPITES"); i++) {
				rs.next();
				if (i == id) {
					idConcorrente = rs.getInt("id_concorrente");
					// System.out.println("Palpite "+id+" = "+dezena);
				}
			}

			rs.close();
			pstmt.close();
			return idConcorrente;
		} catch (SQLException e) {
			System.out.println("Concorrente não encontrado");
		}
		return idConcorrente;
	}

	public int findNumPalpites() {
		return findNum("PALPITES");
	}

	public Palpite backPalpite(int id) {
		Palpite palpite = new Palpite();
		String sql = "SELECT * FROM PALPITES";
		try {
			PreparedStatement pstmt = getConnection().prepareStatement(sql);

			ResultSet rs = pstmt.executeQuery();

			int num = 0;

			while (rs.next() && id != rs.getInt("id_palpite")) {
				num++;
			}
			palpite.setId_palpite(rs.getInt("id_palpite"));
			palpite.setPalpite(rs.getInt("palpite1"), 0);
			palpite.setPalpite(rs.getInt("palpite2"), 1);
			palpite.setPalpite(rs.getInt("palpite3"), 2);
			palpite.setPalpite(rs.getInt("palpite4"), 3);
			palpite.setPalpite(rs.getInt("palpite5"), 4);
			palpite.setId_vendedor(rs.getInt("id_vendedor"));
			palpite.setId_concorrente(rs.getInt("id_concorrente"));
			palpite.setId_sorteio(rs.getInt("id_sorteio"));

			rs.close();
			pstmt.close();
			return palpite;
		} catch (SQLException e) {
			System.out.println(e);
		}
		return null;
	}

	public Palpite findById(Palpite palpite) {
		Palpite novoPalpite = new Palpite();

		String sql = "SELECT * FROM PALPITES WHERE id_concorrente = ? AND id_sorteio = ? ";
		try {
			PreparedStatement pstmt = getConnection().prepareStatement(sql);
			pstmt.setObject(1, palpite.getId_concorrente());
			pstmt.setObject(2, palpite.getId_sorteio());

			ResultSet rs = pstmt.executeQuery();

			rs.next();
			novoPalpite.setId_palpite(rs.getInt("id_palpite"));
			novoPalpite.setPalpite(rs.getInt("palpite1"), 0);
			novoPalpite.setPalpite(rs.getInt("palpite2"), 1);
			novoPalpite.setPalpite(rs.getInt("palpite3"), 2);
			novoPalpite.setPalpite(rs.getInt("palpite4"), 3);
			novoPalpite.setPalpite(rs.getInt("palpite5"), 4);
			novoPalpite.setId_vendedor(rs.getInt("id_vendedor"));
			novoPalpite.setId_concorrente(rs.getInt("id_concorrente"));
			novoPalpite.setId_sorteio(rs.getInt("id_sorteio"));

			rs.close();
			pstmt.close();
			return novoPalpite;
		} catch (SQLException e) {
			System.out.println(e);
		}
		return null;
	}

	public static String cadastrarPalpite(Venda vendedor, Nome concorrente,
			Palpite palpite) {
		Nome nome = concorrente;
		Venda venda = vendedor;
		Palpite palpiteTmp = new Palpite();
		String result = "Erro de gravação";
		int reincidencias = 0;
		boolean novoPalpite = true;

		VendaDao daoVenda = new VendaDao();
		if (daoVenda.findByNomeVendedor(venda.getNomeVendedor()) == 0)
			daoVenda.save(venda);

		NomeDao daoNome = new NomeDao();
		if (daoNome.findByNomeConcorrente(nome.getNomeConcorrente()) == 0)
			daoNome.save(nome);
		else
			reincidencias++;

		SorteioDao daoSorteio = new SorteioDao();
		List<Sorteio> sorteios = daoSorteio.findSorteios();
		Sorteio ultimoSorteio = sorteios.get(daoSorteio.findNumSorteios() - 1);
		if (ultimoSorteio.getSorteio(0) != 0)
			daoSorteio.save(new Sorteio());
		else
			reincidencias++;
		
		// Validação de palpite
		palpiteTmp.setId_vendedor(daoVenda.findByNomeVendedor(venda
				.getNomeVendedor()));
		palpiteTmp.setId_concorrente(daoNome.findByNomeConcorrente(nome
				.getNomeConcorrente()));
		palpiteTmp.setId_sorteio(sorteios.get(daoSorteio.findNumSorteios() - 1)
				.getId_sorteio());

	//	int tamanhoAcertos = (new AcertoDao()).findAcertos().size() - 1;
//		int ultimoJogo = (new AcertoDao()).findAcertos().get(tamanhoAcertos).getJogo();
		
		AcertoDao daoAcerto = new AcertoDao();
		int jogoUltimaQuina = daoAcerto.findJogoUltimaQuina();
		int idUltimoPalpiteUltimaQuina = daoAcerto.findIdUltimoPalpite(jogoUltimaQuina);
/*		List<Acerto> acertos = (new AcertoDao()).findAcertos();
		for (Acerto acerto : acertos) {
			if(acerto.getJogo() == jogoUltimaQuina) {
				int id_palpiteAcerto = acerto.getId_palpite();
				Palpite palpiteAux = (new PalpiteDao()).backPalpite(id_palpiteAcerto);
				
				if (eq(palpiteAux, palpite)) {
					novoPalpite = false;
					break;
				}
				else
					novoPalpite = true;
			}			
		}
*/
		PalpiteDao daoPalpite = new PalpiteDao();
		List<Palpite> palpites = daoPalpite.findPalpites();
		for (Palpite palpiteTeste : palpites) {
			if(palpiteTeste.getId_palpite() > idUltimoPalpiteUltimaQuina) {
				if (palpiteTeste.getId_concorrente() == palpiteTmp
						.getId_concorrente())
//					if (palpiteTeste.getId_sorteio() == palpiteTmp.getId_sorteio())
						if (eq(palpiteTeste, palpite)) {
							novoPalpite = false;
							break;
						}
						else
							novoPalpite = true;				
			}
		}
		if(!novoPalpite)
			reincidencias++;

		if (reincidencias < 3) {
			palpiteTmp.setPalpite(palpite.getPalpite());
			if (daoPalpite.save(palpiteTmp))
				result = palpiteTmp.toString();
		} else
			result = "Palpite já existe";

		return result;
	}

	public static boolean eq(Palpite palpiteTeste, Palpite palpite) {
		boolean result = false;

		int j = 0;
		for (int i = 0; i < 5; i++) {
			if (palpite.getPalpite(i) == palpiteTeste.getPalpite(i))
				j++;
		}
		if (j > 4)
			result = true;

		return result;
	}

	public boolean truncate() {
		return truncate("PALPITES");
	}

	public boolean drop() {
		return drop("PALPITES");
	}
	
	public static void ddlPalpites() {
		VendaDao.ddlInsertVendedor();
		NomeDao.ddlConcorrentes();
		SorteioDao.ddlSorteios();
		
		(new CreateTables()).createTablePalpites();

		PalpiteDao daoPalpite = new PalpiteDao();

		Palpite palpite = new Palpite(new int[]{ 14, 21, 25, 26, 47 }, 1, 1, 1);
		daoPalpite.save(palpite);
		ListTables.showPalpites();

		palpite = new Palpite(new int[]{ 23, 40, 42, 75, 77 }, 2, 2, 2);
		daoPalpite.save(palpite);
		ListTables.showPalpites();		
	}
	
	public static void dmlPalpites() {
		Palpite palpite = (new PalpiteDao()).backPalpite(1);
		PalpiteDao daoPalpite = new PalpiteDao();

		if (palpite != null) {
			palpite.setPalpite(new int[]{ 17, 26, 29, 46, 70 });
			daoPalpite.update(palpite);
			ListTables.showPalpites();

			daoPalpite.delete(palpite);			
		}

		palpite = (new PalpiteDao()).backPalpite(2);
		if (palpite != null) {
			palpite.setPalpite(10, 0);
			palpite.setPalpite(21, 1);
			palpite.setPalpite(22, 2);
			palpite.setPalpite(36, 3);
			palpite.setPalpite(63, 4);
			palpite.setId_palpite(2);
			ListTables.showPalpites();			
		}

		if ((new PalpiteDao()).truncate())
			System.out.println("Palpites zerado.");
		ListTables.showPalpites();

		if ((new PalpiteDao()).drop())
			System.out.println("Palpites removido.");
		
		VendaDao.dmlVendedor();
		NomeDao.dmlConcorrentes();
		SorteioDao.dmlSorteios();
	}

	public static void demoPalpites() {
		ddlPalpites();
		dmlPalpites();
	}
}