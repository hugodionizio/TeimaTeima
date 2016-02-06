package bancodados.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CreateTables {
	private Connection connection;

	public CreateTables() {
		this.connection = ConnectionDataBase.getConnection();
	}

	public boolean createTableVendas() {
		String sql = null;
		try {
			sql = "CREATE TABLE Vendas ("
					+ "id_vendedor int not null GENERATED ALWAYS AS "
					+ "IDENTITY (START WITH 1, INCREMENT BY 1) PRIMARY KEY,"
					+ "nomeVendedor varchar(80)" + ")";

			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.execute();
			stmt.close();
			System.out.println("Tabela Vendas criada");
			return false;
		} catch (SQLException e) {
			System.out.println(e);
			return true;
		}
	}

	public boolean createTableNomes() {
		String sql = null;
		try {
			sql = "CREATE TABLE Nomes ("
					+ "id_concorrente int not null GENERATED ALWAYS AS "
					+ "IDENTITY (START WITH 1, INCREMENT BY 1) "
					+ "CONSTRAINT PK_NOMES PRIMARY KEY," + "email varchar(50),"
					+ "endereco varchar(50)," + "fone varchar(15),"
					+ "idade int," + "nomeConcorrente varchar(50),"
					+ "referencia varchar(20)" + ")";

			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.execute();
			stmt.close();
			System.out.println("Tabela Nomes criada");
			return false;
		} catch (SQLException e) {
			System.out.println(e);
			return true;
		}
	}

	public boolean createTablePalpites() {
		String sql = null;
		try {
			sql = "CREATE TABLE Palpites ("
					+ "id_palpite int not null GENERATED ALWAYS AS "
					+ "IDENTITY (START WITH 1, INCREMENT BY 1) "
					+ "CONSTRAINT PK_PALPITES PRIMARY KEY,"
					+ "palpite1 int,"
					+ "palpite2 int,"
					+ "palpite3 int,"
					+ "palpite4 int,"
					+ "palpite5 int,"
					+ "id_vendedor int,"
					+ "id_concorrente int,"
					+ "id_sorteio int,"
					+ "FOREIGN KEY(id_vendedor) REFERENCES Vendas (id_vendedor) ON DELETE CASCADE,"
					+ "FOREIGN KEY(id_concorrente) REFERENCES Nomes (id_concorrente) ON DELETE CASCADE,"
					+ "FOREIGN KEY(id_sorteio) REFERENCES Sorteios (id_sorteio) ON DELETE CASCADE"
					+ ")";

			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.execute();
			stmt.close();
			System.out.println("Tabela Palpites criada");
			return false;
		} catch (SQLException e) {
			System.out.println(e);
			return true;
		}
	}

	public boolean createTableLoteria() {
		String sql = null;
		try {
			sql = "CREATE TABLE Loteria ("
					+ "id_concurso int not null GENERATED ALWAYS AS "
					+ "IDENTITY (START WITH 1, INCREMENT BY 1) "
					+ "CONSTRAINT PK_LOTERIA PRIMARY KEY,"
					+ "dezenaConcurso1 int," + "dezenaConcurso2 int,"
					+ "dezenaConcurso3 int," + "dezenaConcurso4 int,"
					+ "dezenaConcurso5 int" + ")";

			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.execute();
			stmt.close();
			System.out.println("Tabela Loteria criada");
			return false;
		} catch (SQLException e) {
			System.out.println(e);
			return true;
		}
	}

	public boolean createTableSorteios() {
		String sql = null;
		try {
			sql = "CREATE TABLE Sorteios (" + "data date,"
					+ "id_sorteio int not null GENERATED ALWAYS AS "
					+ "IDENTITY (START WITH 1, INCREMENT BY 1) "
					+ "CONSTRAINT PK_SORTEIOS PRIMARY KEY," + "dezena1 int,"
					+ "dezena2 int," + "dezena3 int," + "dezena4 int,"
					+ "dezena5 int" + ")";

			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.execute();
			stmt.close();
			System.out.println("Tabela Sorteios criada");
			return false;
		} catch (SQLException e) {
			System.out.println(e);
			return true;
		}
	}

	public boolean createTableAcertos() {
		String sql = null;
		try {
			sql = "CREATE TABLE Acertos ("
					+ "tipoAcerto varchar(10),"
					+ "id_acerto int not null GENERATED ALWAYS AS "
					+ "IDENTITY (START WITH 1, INCREMENT BY 1) "
					+ "CONSTRAINT PK_ACERTOS PRIMARY KEY,"
					+ "acerto1 int,"
					+ "acerto2 int,"
					+ "acerto3 int,"
					+ "acerto4 int,"
					+ "acerto5 int,"
					+ "numeroAcertos int,"
					+ "jogo int,"
					+ "id_vendedor int,"
					+ "id_concorrente int,"
					+ "id_palpite int,"
					+ "id_sorteio int,"
					+ "FOREIGN KEY(id_vendedor) REFERENCES Vendas (id_vendedor) ON DELETE CASCADE,"
					+ "FOREIGN KEY(id_concorrente) REFERENCES Nomes (id_concorrente) ON DELETE CASCADE,"
					+ "FOREIGN KEY(id_palpite) REFERENCES Palpites (id_palpite) ON DELETE CASCADE,"
					+ "FOREIGN KEY(id_sorteio) REFERENCES Sorteios (id_sorteio) ON DELETE CASCADE"
					+ ")";

			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.execute();
			stmt.close();
			System.out.println("Tabela Acertos criada");
			return false;
		} catch (SQLException e) {
			System.out.println(e);
			return true;
		}
	}

	public static void createTables(String[] args) {
		(new CreateTables()).createTableVendas();
	}
}