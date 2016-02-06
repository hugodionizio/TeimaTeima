package bancodados.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import bancodados.model.Loteria;
import bancodados.model.Sorteio;
import bancodados.view.ListTables;

public class SorteioDao extends GenericDao {

	public boolean save(Sorteio sorteio) {
		String data = sorteio.getData();
		String initDateFormat = "dd/MM/yyyy";
		String endDateFormat = "yyyy-MM-dd";

		Date initDate = null;
		try {
			initDate = new SimpleDateFormat(initDateFormat).parse(data);
		} catch (ParseException e) {
			System.out.println(e);
		}
		if (initDate != null) {
			SimpleDateFormat formatter = new SimpleDateFormat(endDateFormat);
			String dataDerby = formatter.format(initDate);

			String sql = "INSERT INTO SORTEIOS(dezena1, dezena2, dezena3,"
					+ " dezena4, dezena5, data) VALUES (?,?,?,?,?,?)";
			return save(sql, sorteio.getSorteio(0), sorteio.getSorteio(1),
					sorteio.getSorteio(2), sorteio.getSorteio(3),
					sorteio.getSorteio(4), dataDerby);
		} else {
			return false;
		}
	}

	public boolean update(Sorteio sorteio) {
		String data = sorteio.getData();
		String initDateFormat = "dd/MM/yyyy";
		String endDateFormat = "yyyy-MM-dd";

		Date initDate = null;
		try {
			initDate = new SimpleDateFormat(initDateFormat).parse(data);
		} catch (ParseException e) {
			System.out.println(e);
		}
		SimpleDateFormat formatter = new SimpleDateFormat(endDateFormat);
		String dataDerby = formatter.format(initDate);

		String sql = "UPDATE SORTEIOS "
				+ "SET dezena1 = ?, dezena2 = ?, dezena3 = ?, dezena4 = ?,"
				+ " dezena5 = ?, data = ? " + "where id_sorteio = ?";
		return update(sql, sorteio.getSorteio(0), sorteio.getSorteio(1),
				sorteio.getSorteio(2), sorteio.getSorteio(3),
				sorteio.getSorteio(4), dataDerby, sorteio.getId_sorteio());
	}

	public void delete(Sorteio sorteio) {
		String sql = "DELETE FROM SORTEIOS WHERE id_sorteio = ? ";
		delete(sql, sorteio.getId_sorteio());
	}

	public List<Sorteio> findSorteios() {
		List<Sorteio> sorteios = new ArrayList<Sorteio>();
		String sql = "SELECT * FROM SORTEIOS";
		try {
			PreparedStatement pstmt = getConnection().prepareStatement(sql);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				Sorteio sorteio = new Sorteio();
				sorteio.setId_sorteio(rs.getInt("id_sorteio"));
				sorteio.setSorteio(rs.getInt("dezena1"), 0);
				sorteio.setSorteio(rs.getInt("dezena2"), 1);
				sorteio.setSorteio(rs.getInt("dezena3"), 2);
				sorteio.setSorteio(rs.getInt("dezena4"), 3);
				sorteio.setSorteio(rs.getInt("dezena5"), 4);
				sorteio.setData(rs.getString("data"));
				sorteios.add(sorteio);
			}
			rs.close();
			pstmt.close();
			return sorteios;
		} catch (SQLException e) {
			System.out.println(e);
		}
		return null;
	}

	public int findNumSorteios() {
		return findNum("SORTEIOS");
	}

	public int findDezena(int id, int pos) {
		int dezena = 0;

		String sql = "SELECT * FROM SORTEIOS";
		try {
			PreparedStatement pstmt = getConnection().prepareStatement(sql);

			ResultSet rs = pstmt.executeQuery();

			for (int i = 1; i < findNum("PALPITES"); i++) {
				rs.next();
				if (i == id)
					dezena = rs.getInt("dezena" + (pos + 1));
				// System.out.println("Sorteio "+id+" = "+dezena);
			}

			rs.close();
			pstmt.close();
			return dezena;
		} catch (SQLException e) {
			System.out.print("Dezena " + (pos + 1) + " do Sorteio " + (id + 1)
					+ " não encontrada");
		}
		return dezena;
	}

	public Sorteio backSorteio(int id) {
		Sorteio sorteio = new Sorteio();
		String sql = "SELECT * FROM SORTEIOS";
		try {
			PreparedStatement pstmt = getConnection().prepareStatement(sql);

			ResultSet rs = pstmt.executeQuery();

			int num = 0;

			while (rs.next() && id != rs.getInt("id_sorteio")) {
				num++;
			}
			sorteio.setId_sorteio(rs.getInt("id_sorteio"));
			sorteio.setSorteio(rs.getInt("dezena1"), 0);
			sorteio.setSorteio(rs.getInt("dezena2"), 1);
			sorteio.setSorteio(rs.getInt("dezena3"), 2);
			sorteio.setSorteio(rs.getInt("dezena4"), 3);
			sorteio.setSorteio(rs.getInt("dezena5"), 4);
			sorteio.setData(rs.getString("data"));

			rs.close();
			pstmt.close();
			return sorteio;
		} catch (SQLException e) {
			System.out.println(e);
		}
		return null;
	}

	public boolean truncate() {
		return truncate("SORTEIOS");
	}

	public boolean drop() {
		return drop("SORTEIOS");
	}

	public static void ddlSorteios() {
		(new CreateTables()).createTableSorteios();

		SorteioDao daoSorteio = new SorteioDao();

		Sorteio sorteio = new Sorteio(new int[] { 14, 21, 25, 26, 47 },
				"13/08/2013");
		daoSorteio.save(sorteio);
		ListTables.showSorteios();

		sorteio = new Sorteio(new int[] { 23, 40, 42, 75, 77 }, "20/08/2013");
		daoSorteio.save(sorteio);
		ListTables.showSorteios();
	}

	public static void dmlSorteios() {
		Sorteio sorteio = new Sorteio();
		SorteioDao daoSorteio = new SorteioDao();

		sorteio.setData("27/08/2013");
		sorteio.setId_sorteio(1);
		daoSorteio.update(sorteio);
		ListTables.showSorteios();

		daoSorteio.delete(sorteio);

		sorteio.setSorteio(17, 0);
		sorteio.setSorteio(26, 1);
		sorteio.setSorteio(29, 2);
		sorteio.setSorteio(46, 3);
		sorteio.setSorteio(70, 4);
		sorteio.setId_sorteio(2);
		daoSorteio.update(sorteio);
		ListTables.showSorteios();

		if ((new SorteioDao()).truncate())
			System.out.println("Sorteios zerados.");
		ListTables.showSorteios();

		if ((new SorteioDao()).drop())
			System.out.println("Sorteios removidos.");
	}

	public static void demoSorteios() {
		ddlSorteios();
		dmlSorteios();
	}

	public String cadastrarSorteio(Sorteio sorteio) {
		String result;

		(new CreateTables()).createTableSorteios();
		Sorteio ultimoSorteio = findSorteios().get(findSorteios().size()-1);
		if (ultimoSorteio.getSorteio(0) != 0) {
			if ((new SorteioDao()).save(sorteio))
				result = sorteio.toString();
			else
				result = "Erro de salvamento";
		} else {
			sorteio.setId_sorteio(ultimoSorteio.getId_sorteio());
			if ((new SorteioDao()).update(sorteio))
				result = sorteio.toString();
			else
				result = "Erro de preenchimento";
		}

		return result;
	}

	public static String sortearConcurso() {
		String result;
		Sorteio sorteio = new Sorteio();

		LoteriaDao daoLoteria = new LoteriaDao();
		Random sorteioEscolhido = new Random();

		int tamanho = daoLoteria.findConcursos().size();
		int temp = sorteioEscolhido.nextInt(tamanho);
		for (int i = 0; i < 5; i++) {
			sorteio.setSorteio(daoLoteria.findConcursos().get(temp)
					.getConcurso(i), i);
		}

		SorteioDao daoSorteio = new SorteioDao();
		List<Sorteio> sorteios = daoSorteio.findSorteios();
		Sorteio ultimoSorteio = sorteios.get(daoSorteio.findNumSorteios() - 1);
		if (ultimoSorteio.getSorteio(0) != 0) {
			daoSorteio.save(sorteio);
			result = "Salvo: " + sorteio.toString();
		} else {
			sorteio.setId_sorteio(ultimoSorteio.getId_sorteio());
			daoSorteio.update(sorteio);
			result = "Atualizado: " + sorteio.toString();
		}

		return result;
	}

	public static String sortearDezenas() {
		Sorteio sorteio = new Sorteio();
		String result;

		LoteriaDao daoLoteria = new LoteriaDao();
		Random sorteioEscolhido = new Random();

		int tamanho = daoLoteria.findConcursos().size();
		int temp;
		for (int i = 0; i < 5; i++) {
			do {
				temp = sorteioEscolhido.nextInt(tamanho);
				sorteio.setSorteio(daoLoteria.findConcursos().get(temp)
						.getConcurso(i), i);
			} while (i > 0
					&& sorteio.getSorteio(i) <= sorteio.getSorteio(i - 1));
		}

		SorteioDao daoSorteio = new SorteioDao();
		List<Sorteio> sorteios = daoSorteio.findSorteios();
		Sorteio ultimoSorteio = sorteios.get(daoSorteio.findNumSorteios() - 1);
		if (ultimoSorteio.getSorteio(0) != 0) {
			daoSorteio.save(sorteio);
			result = "Salvo: " + sorteio.toString();
		} else {
			sorteio.setId_sorteio(ultimoSorteio.getId_sorteio());
			daoSorteio.update(sorteio);
			result = "Atualizado: " + sorteio.toString();
		}

		return result;
	}

	public static void novo() {
		SorteioDao daoSorteio = new SorteioDao();
		List<Sorteio> sorteios = daoSorteio.findSorteios();
		Sorteio ultimoSorteio = sorteios.get(daoSorteio.findNumSorteios() - 1);
		if (ultimoSorteio.getSorteio(0) != 0)
			daoSorteio.save(new Sorteio());
	}
}