package bancodados.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import bancodados.model.Loteria;
import bancodados.model.Nome;
import bancodados.view.ListTables;

;

public class LoteriaDao extends GenericDao {

	public boolean save(Loteria loteria) {
		String sql = "INSERT INTO LOTERIA(dezenaConcurso1, dezenaConcurso2, dezenaConcurso3,"
				+ " dezenaConcurso4, dezenaConcurso5) VALUES (?,?,?,?,?)";
		return save(sql, loteria.getConcurso(0), loteria.getConcurso(1),
				loteria.getConcurso(2), loteria.getConcurso(3),
				loteria.getConcurso(4));
	}

	public void update(Loteria loteria) {
		String sql = "UPDATE LOTERIA "
				+ "SET dezenaConcurso1 = ?, dezenaConcurso2 = ?, dezenaConcurso3 = ?, dezenaConcurso4 = ?,"
				+ " dezenaConcurso5 = ? " + " where id_concurso = ?";
		update(sql, loteria.getConcurso(0), loteria.getConcurso(1),
				loteria.getConcurso(2), loteria.getConcurso(3),
				loteria.getConcurso(4), loteria.getId_concurso());
	}

	public void delete(Loteria loteria) {
		String sql = "DELETE FROM LOTERIA WHERE id_concurso = ? ";
		delete(sql, loteria.getId_concurso());
	}

	public List<Loteria> findConcursos() {
		List<Loteria> concursos = new ArrayList<Loteria>();
		String sql = "SELECT * FROM LOTERIA";
		try {
			PreparedStatement pstmt = getConnection().prepareStatement(sql);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				Loteria concurso = new Loteria();
				concurso.setId_concurso(rs.getInt("id_concurso"));
				for (int i = 0; i < 5; i++) {
					concurso.setDezenaConcurso(
							rs.getInt(("dezenaConcurso" + (i + 1))), i);
				}
				concursos.add(concurso);
			}
			rs.close();
			pstmt.close();
			return concursos;
		} catch (SQLException e) {
			System.out.println(e);
		}
		return null;
	}

	public int findNumConcursos() {
		int numConcursos = 0;

		String sql = "SELECT * FROM LOTERIA";
		try {
			PreparedStatement pstmt = getConnection().prepareStatement(sql);

			ResultSet rs = pstmt.executeQuery();

			while(rs.next())
				numConcursos++;

			rs.close();
			pstmt.close();
			return numConcursos;
		} catch (SQLException e) {
			System.out.println(e);
		}
		return 0;
	}
	
	public int findDezenaConcurso(int concurso, int pos) {
		int dezena = 0;

		String sql = "SELECT * FROM LOTERIA WHERE id_concurso = "+concurso;
		try {
			PreparedStatement pstmt = getConnection().prepareStatement(sql);

			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				int posTmp = pos + 1;
				String dezenaS = "dezenaConcurso" + posTmp;

				dezena = rs.getInt(dezenaS);				
			}
			
			rs.close();
			pstmt.close();
			return dezena;
		} catch (SQLException e) {
			System.out.println(e);
		}
		return dezena;
	}

	public boolean truncate() {
		return truncate("LOTERIA");
	}

	public boolean drop() {
		return drop("LOTERIA");
	}
	
	public static void ddlLoteria() {
		(new CreateTables()).createTableLoteria();

		LoteriaDao daoLoteria = new LoteriaDao();

		Loteria concurso = new Loteria(new int[]{ 14, 21, 25, 26, 47 });
		daoLoteria.save(concurso);
		ListTables.showLoteria();

		concurso = new Loteria(new int[]{ 23, 40, 42, 75, 77 });
		daoLoteria.save(concurso);
		ListTables.showLoteria();		
	}
	
	public static void dmlLoteria() {
		Loteria concurso = new Loteria();
		LoteriaDao daoLoteria = new LoteriaDao();
		
		concurso.setDezenaConcurso(new int[]{ 17, 26, 29, 46, 70 });
		concurso.setId_concurso(1);
		daoLoteria.update(concurso);
		ListTables.showLoteria();

		daoLoteria.delete(concurso);

		concurso.setDezenaConcurso(10, 0);
		concurso.setDezenaConcurso(21, 1);
		concurso.setDezenaConcurso(22, 2);
		concurso.setDezenaConcurso(36, 3);
		concurso.setDezenaConcurso(63, 4);

		concurso.setId_concurso(2);
		daoLoteria.update(concurso);
		ListTables.showLoteria();

		if ((new LoteriaDao()).truncate())
			System.out.println("Loteria zerada.");
		ListTables.showLoteria();

		if ((new LoteriaDao()).drop())
			System.out.println("Loteria removida.");		
	}
	
	public static void demoLoteria() {
		ddlLoteria();
		dmlLoteria();
	}

	public String cadastrarConcurso(Loteria concurso) {
		String result;
		(new CreateTables()).createTableLoteria();
		if ((new LoteriaDao()).save(concurso))
			result = concurso.toString();
		else
			result = "Erro de salvamento";
		return result;
	}
}