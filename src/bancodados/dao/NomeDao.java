package bancodados.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bancodados.model.Nome;
import bancodados.view.ListTables;

public class NomeDao extends GenericDao {

	public boolean save(Nome nome) {
		String sql = "INSERT INTO NOMES(nomeConcorrente, idade, referencia, endereco, fone, email) VALUES (?,?,?,?,?,?)";
		return save(sql, nome.getNomeConcorrente(), nome.getIdade(),
				nome.getReferencia(), nome.getEndereco(), nome.getFone(),
				nome.getEmail());
	}

	public boolean update(Nome nome) {
		String sql = "UPDATE NOMES SET "
				+ "nomeConcorrente = ?, idade = ?, referencia = ?, endereco = ?, fone = ?, email = ?"
				+ " where id_concorrente = ?";
		return update(sql, nome.getNomeConcorrente(), nome.getIdade(),
				nome.getReferencia(), nome.getEndereco(), nome.getFone(),
				nome.getEmail(), nome.getId_concorrente());
	}

	public boolean delete(Nome nome) {
		String sql = "DELETE FROM NOMES WHERE id_concorrente = ? ";
		return delete(sql, nome.getId_concorrente());
	}

	public List<Nome> findNomes() {
		List<Nome> nomes = new ArrayList<Nome>();
		String sql = "SELECT * FROM NOMES";
		try {
			PreparedStatement pstmt = getConnection().prepareStatement(sql);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				Nome nome = new Nome();
				nome.setId_concorrente(rs.getInt("id_concorrente"));
				nome.setNomeConcorrente(rs.getString("nomeConcorrente"));
				nome.setIdade(rs.getInt("idade"));
				nome.setReferencia(rs.getString("referencia"));
				nome.setEndereco(rs.getString("endereco"));
				nome.setFone(rs.getString("fone"));
				nome.setEmail(rs.getString("email"));
				nomes.add(nome);
			}
			rs.close();
			pstmt.close();
			return nomes;
		} catch (SQLException e) {
			System.out.println(e);
		}
		return null;
	}

	public static int findByIndex(int index) {
		int idConcorrente = 0;

		idConcorrente = (new NomeDao()).findNomes().get(index)
				.getId_concorrente();

		return idConcorrente;
	}

	public boolean truncateNomes() {
		String sql = "TRUNCATE TABLE NOMES";
		try {
			PreparedStatement pstmt = getConnection().prepareStatement(sql);
			pstmt.execute();
			pstmt.close();

			return true;
		} catch (SQLException e) {
			System.out.println(e);
		}
		return false;
	}

	public Nome backNome(int id) {
		Nome nome = new Nome();
		String sql = "SELECT * FROM NOMES";
		try {
			PreparedStatement pstmt = getConnection().prepareStatement(sql);

			ResultSet rs = pstmt.executeQuery();

			int num = 0;

			while (rs.next() && id != rs.getInt("id_concorrente")) {
				num++;
			}
			nome.setId_concorrente(rs.getInt("id_concorrente"));
			nome.setNomeConcorrente(rs.getString("nomeConcorrente"));
			nome.setIdade(rs.getInt("idade"));
			nome.setReferencia(rs.getString("referencia"));
			nome.setEndereco(rs.getString("endereco"));
			nome.setFone(rs.getString("fone"));
			nome.setEmail(rs.getString("email"));

			rs.close();
			pstmt.close();
			return nome;
		} catch (SQLException e) {
			System.out.println(e);
		}
		return null;
	}

	public int findNumNomes() {
		return findNum("NOMES");
	}

	public int findByNomeConcorrente(String nomeConcorrente) {
		int idConcorrente = 0;

		String sql = "SELECT * FROM NOMES WHERE nomeConcorrente = ? ";

		try {
			PreparedStatement pstmt = getConnection().prepareStatement(sql);
			pstmt.setObject(1, nomeConcorrente);

			ResultSet rs = pstmt.executeQuery();

			rs.next();
			idConcorrente = rs.getInt("id_concorrente");

			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			System.out.println(e);
		}

		return idConcorrente;
	}

	public boolean truncate() {
		return truncate("NOMES");
	}

	public boolean drop() {
		return drop("NOMES");
	}
	
	public static void ddlConcorrentes() {
		(new CreateTables()).createTableNomes();

		NomeDao daoNome = new NomeDao();

		Nome nome = new Nome("Pedro", 18, "Piter Lanches", "Rua dos Lanches",
				"(84) 99999-9999", "pedro@piterlanches.com");
		daoNome.save(nome);
		ListTables.showConcorrentes();

		nome = new Nome("Dede", 40, "Crep", "Rua do Crep", "(84) 88888-8888",
				"dede@crep.com");
		daoNome.save(nome);
		ListTables.showConcorrentes();
	}
	
	public static void dmlConcorrentes() {
		Nome nome = new Nome();
		NomeDao daoNome = new NomeDao();
		
		nome.setNomeConcorrente("Zé Maria");
		nome.setId_concorrente(1);
		daoNome.update(nome);
		ListTables.showConcorrentes();

		daoNome.delete(nome);

		nome.setNomeConcorrente("Wanderson");
		nome.setId_concorrente(2);
		daoNome.update(nome);
		ListTables.showConcorrentes();

		if ((new NomeDao()).truncate())
			System.out.println("Concorrentes zerado.");
		ListTables.showConcorrentes();

		if ((new NomeDao()).drop())
			System.out.println("Concorrentes removido.");
	}

	public static void demoConcorrentes() {
		ddlConcorrentes();
		dmlConcorrentes();
	}
}