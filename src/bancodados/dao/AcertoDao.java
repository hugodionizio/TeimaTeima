package bancodados.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import bancodados.model.Acerto;
import bancodados.model.Loteria;
import bancodados.model.Palpite;
import bancodados.model.Sorteio;
import bancodados.view.ListTables;

public class AcertoDao extends GenericDao {

	public void save(Acerto acerto) {
		String sql = "INSERT INTO ACERTOS(tipoAcerto, acerto1, acerto2, acerto3, acerto4, acerto5,"
				+ " numeroAcertos, jogo, id_concorrente, id_sorteio, id_palpite) VALUES (?,?,?,?,?,?,?,?,?,?,?)";

		save(sql, acerto.getTipoAcerto(), acerto.getAcertos(0),
				acerto.getAcertos(1), acerto.getAcertos(2),
				acerto.getAcertos(3), acerto.getAcertos(4), acerto.getNumero(),
				acerto.getJogo(), acerto.getId_concorrente(),
				acerto.getId_sorteio(), acerto.getId_palpite());
	}

	public static int acumular(Sorteio sorteio, Palpite palpite, Acerto acerto,
			int jogo) {
		int continuar = jogo;

		if (acerto.getJogo() == jogo) {
			int numAcertos = 0;
			boolean[] acertos = acerto.getAcertos();
			if (acertos == null) {
				acertos = new boolean[5];
				acerto.setId_concorrente(palpite.getId_concorrente());
				acerto.setId_sorteio(sorteio.getId_sorteio());
				acerto.setId_palpite(palpite.getId_palpite());
			}
			for (int i = 0; i < 5; i++) {
				for (int j = 0; j < 5; j++) {
					if (sorteio.getSorteio(i) == palpite.getPalpite(j))
						acertos[j] = true;
				}
			}
			for (int i = 0; i < acertos.length; i++) {
				if (acertos[i] == true)
					numAcertos++;
			}

			acerto.setAcertos(acertos);
			acerto.setNumero(numAcertos);

			if (numAcertos == 5 && acerto.getJogo() == jogo)
				continuar++;

			if (numAcertos > 0)
				switch (numAcertos) {
				case 1:
					acerto.setTipo("PIO");
					break;

				case 2:
					acerto.setTipo("DUQUE");
					break;

				case 3:
					acerto.setTipo("TERNO");
					break;

				case 4:
					acerto.setTipo("QUADR");
					break;

				case 5:
					acerto.setTipo("QUINA");
					break;

				default:
					acerto.setTipo("NADA");
					break;
				}			
			
			if (acerto.getNumero() > 0 && (new AcertoDao()).findAcertoById(palpite) == null) {
				(new AcertoDao()).save(acerto);
				System.out.println("Salvo: " + acerto.toString());
			} else if (acerto.getNumero() > 0) {
				acerto.setId_acerto((new AcertoDao()).findAcertoById(palpite).getId_acerto());
				(new AcertoDao()).update(acerto);
				System.out.println("Atualizado: " + acerto.toString());
			}
		}
		return continuar;
	}
	
	public static void contar() {
		List<Palpite> palpites = (new PalpiteDao()).findPalpites();
		Acerto acerto;
		int i;
		int jogo;
		
		for (Sorteio sorteio : (new SorteioDao()).findSorteios()) {
			if(sorteio.getSorteio(0) != 0) {
				i = 0;
				jogo = (new AcertoDao()).findUltimaAcumulacao();
				if (jogo < 1) {
					jogo = 1;
				}
				for (Palpite palpite : palpites) {
					acerto = (new AcertoDao()).findAcertoById(palpite);
					if(acerto == null) {
						acerto = new Acerto();
						acerto.setJogo(jogo);
					}
					if (sorteio.getId_sorteio() >= palpite.getId_sorteio())
						jogo = AcertoDao.acumular(sorteio, palpite, acerto, jogo);
					i++;
				}
				if (jogo < (new AcertoDao()).findUltimaAcumulacao()) {
					i = 0;
					for (Palpite palpite : palpites) {
						acerto = (new AcertoDao()).findAcertoById(palpite);
						if (sorteio.getId_sorteio() >= palpite.getId_sorteio())
							AcertoDao.acumular(sorteio, palpite, acerto, jogo);
						i++;
					}
				}				
			}
		}
	}

	public void update(Acerto acerto) {
		String sql = "UPDATE ACERTOS SET tipoAcerto = ?, acerto1 = ?, acerto2 = ?, acerto3 = ?, acerto4 = ?, acerto5 = ?,"
				+ " numeroAcertos = ?, jogo = ?, id_concorrente = ?, id_sorteio = ?, id_palpite = ? where id_acerto = ?";

		save(sql, acerto.getTipoAcerto(), acerto.getAcertos(0),
				acerto.getAcertos(1), acerto.getAcertos(2),
				acerto.getAcertos(3), acerto.getAcertos(4), acerto.getNumero(),
				acerto.getJogo(), acerto.getId_concorrente(),
				acerto.getId_sorteio(), acerto.getId_palpite(),
				acerto.getId_acerto());
	}

	public List<Acerto> findAcertos() {
		List<Acerto> acertos = new ArrayList<Acerto>();
		String sql = "SELECT * FROM ACERTOS";
		try {
			PreparedStatement pstmt = getConnection().prepareStatement(sql);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				Acerto acerto = new Acerto();
				acerto.setId_acerto(rs.getInt("id_acerto"));
				acerto.setJogo(rs.getInt("jogo"));
				acerto.setNumero(rs.getInt("numeroAcertos"));
				acerto.setTipo(rs.getString("tipoAcerto"));
				acerto.setId_concorrente(rs.getInt("id_concorrente"));
				acerto.setId_sorteio(rs.getInt("id_sorteio"));
				acerto.setId_palpite(rs.getInt("id_palpite"));
				boolean[] acertosV = new boolean[5];
				for (int i = 0; i < 5; i++) {
					acertosV[i] = rs.getBoolean("acerto" + (i + 1));
				}
				acerto.setAcertos(acertosV);
				acertos.add(acerto);
			}
			rs.close();
			pstmt.close();
			return acertos;
		} catch (SQLException e) {
			System.out.println(e);
		}
		return null;
	}

	public int findUltimaAcumulacao() {
		int ultimaAcumulacao = 0;

		try {
			String sql = "SELECT * FROM ACERTOS";
			PreparedStatement pstmt = getConnection().prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();

			for (int i = 0; i < findNum("ACERTOS") - 1; i++) {
				rs.next();
			}
			if (rs.next()) {
				ultimaAcumulacao = rs.getInt("jogo");
			}

			rs.close();
			pstmt.close();
			return ultimaAcumulacao;
		} catch (SQLException e) {
			System.out.println(e);
		}
		return ultimaAcumulacao;
	}

	public Acerto findAcertoById(Palpite palpite) {

		try {
			String sql = "SELECT * FROM ACERTOS WHERE id_palpite = ?";
			PreparedStatement pstmt = getConnection().prepareStatement(sql);
			pstmt.setObject(1, palpite.getId_palpite());

			ResultSet rs = pstmt.executeQuery();

			rs.next();
			Acerto acerto = new Acerto();
			acerto.setId_acerto(rs.getInt("id_acerto"));
			acerto.setJogo(rs.getInt("jogo"));
			acerto.setNumero(rs.getInt("numeroAcertos"));
			acerto.setTipo(rs.getString("tipoAcerto"));
			acerto.setId_concorrente(rs.getInt("id_concorrente"));
			acerto.setId_palpite(rs.getInt("id_palpite"));
			acerto.setId_sorteio(rs.getInt("id_sorteio"));
			boolean[] acertosV = new boolean[5];
			for (int i = 0; i < 5; i++) {
				acertosV[i] = rs.getBoolean("acerto" + (i + 1));
			}
			acerto.setAcertos(acertosV);

			rs.close();
			pstmt.close();
			return acerto;
		} catch (SQLException e) {
			if (e.getErrorCode() != 20000) {
				System.out.print(e.getErrorCode()+": ");
				System.out.println(e);				
			}
		}
		return null;
	}

	public int tipo(int id) {
		int tipo = 0;

		String sql = "SELECT * FROM ACERTOS WHERE id_acerto = " + id;
		try {
			PreparedStatement pstmt = getConnection().prepareStatement(sql);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				tipo = rs.getInt("numeroAcertos");
			}

			rs.close();
			pstmt.close();
			return tipo;
		} catch (SQLException e) {
			System.out.println(e);
		}
		return tipo;
	}

	public int findInAcerto(int id, String coluna) {
		int valor = 0;

		String sql = "SELECT * FROM ACERTOS";
		try {
			PreparedStatement pstmt = getConnection().prepareStatement(sql);

			ResultSet rs = pstmt.executeQuery();

			for (int i = 1; i < findNum("ACERTOS"); i++) {
				rs.next();
				if (i == id) {
					valor = rs.getInt(coluna);
					// System.out.println("Palpite "+id+" = "+dezena);
				}
			}
			rs.close();
			pstmt.close();
			return valor;
		} catch (SQLException e) {
			System.out.println(e);
		}
		return valor;
	}

	public int findNumAcertos() {
		return findNum("ACERTOS");
	}

	public static int verificarPalpite(Sorteio sorteio, Palpite palpite) {
		int acertos = 0;

		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if (sorteio.getSorteio(i) == palpite.getPalpite(j))
					acertos++;
			}
		}

		return acertos;
	}

	public void delete(Acerto acerto) {
		String sql = "DELETE FROM ACERTOS WHERE id_acerto = ? ";
		delete(sql, acerto.getId_acerto());
	}

	public boolean truncate() {
		return truncate("ACERTOS");
	}

	public boolean drop() {
		return drop("ACERTOS");
	}

	public static void demoAcertos() {
		PalpiteDao.ddlPalpites();

		(new CreateTables()).createTableAcertos();

		AcertoDao daoAcerto = new AcertoDao();

		Acerto acerto = new Acerto(1, 0, new boolean[] { false, false, false,
				false, false }, "NADA", 1, 1, 1);
		daoAcerto.save(acerto);
		ListTables.showAcertos();

		acerto = new Acerto(1, 0, new boolean[] { false, false, false, false,
				false }, "NADA", 2, 2, 2);
		daoAcerto.save(acerto);
		ListTables.showAcertos();

		acerto.setAcertos(new boolean[] { false, true, false, false, false });
		acerto.setTipo("PIO");
		acerto.setId_acerto(1);
		daoAcerto.update(acerto);
		ListTables.showAcertos();

		daoAcerto.delete(acerto);

		acerto.setAcerto(true, 0);
		acerto.setAcerto(true, 1);
		acerto.setAcerto(true, 2);
		acerto.setAcerto(true, 3);
		acerto.setAcerto(true, 4);
		acerto.setId_acerto(2);
		acerto.setTipo("QUINA");
		daoAcerto.update(acerto);
		ListTables.showAcertos();

		if ((new AcertoDao()).truncate())
			System.out.println("Acertos zerado.");
		ListTables.showAcertos();

		if ((new AcertoDao()).drop())
			System.out.println("Acerto removido.");

		PalpiteDao.dmlPalpites();
	}

	public int findJogoUltimaQuina() {
		int ultimaQuina = 0;
		String sql = "SELECT * FROM ACERTOS WHERE tipoAcerto = ?";

		try {
			PreparedStatement pstmt = getConnection().prepareStatement(sql);
			pstmt.setObject(1, "QUINA");

			ResultSet rs = pstmt.executeQuery();

			while(rs.next())
				ultimaQuina = rs.getInt("jogo");

			rs.close();
			pstmt.close();
			return ultimaQuina;
		} catch (SQLException e) {
			if (e.getErrorCode() != 20000) {
				System.out.print(e.getErrorCode()+": ");
				System.out.println(e);				
			}
		}
		
		return ultimaQuina;
	}

	public int findIdUltimoPalpite(int jogoUltimaQuina) {
		int id_palpite = 0;
		
		String sql = "SELECT * FROM ACERTOS WHERE jogo = ? ORDER BY id_palpite";

		try {
			PreparedStatement pstmt = getConnection().prepareStatement(sql);
			pstmt.setObject(1, jogoUltimaQuina);

			ResultSet rs = pstmt.executeQuery();

			while(rs.next())
				id_palpite = rs.getInt("id_palpite");

			rs.close();
			pstmt.close();
			return id_palpite;
		} catch (SQLException e) {
			if (e.getErrorCode() != 20000) {
				System.out.print(e.getErrorCode()+": ");
				System.out.println(e);				
			}
		}		
		
		return id_palpite;
	}
}