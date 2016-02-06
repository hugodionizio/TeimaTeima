package bancodados.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bancodados.model.Venda;
import bancodados.view.ListTables;

public class VendaDao extends GenericDao {

	public void save(Venda venda) {
		String sql = "INSERT INTO VENDAS(nomeVendedor) VALUES (?)";
		save(sql, venda.getNomeVendedor());
	}
		
	public int findNumVendas() {
		return findNum("VENDAS");
	}
	
	public List<Venda> findVendas() {
		List<Venda> vendas = new ArrayList<Venda>();
		String sql = "SELECT * FROM VENDAS";
		try {
			PreparedStatement pstmt = getConnection().prepareStatement(sql);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				Venda venda = new Venda();
				venda.setId_vendedor(rs.getInt("id_vendedor"));
				venda.setNomeVendedor(rs.getString("nomeVendedor"));
				vendas.add(venda);
			}
			rs.close();
			pstmt.close();
			return vendas;
		} catch (SQLException e) {
			System.out.println(e);
		}
		return null;
	}
	
	public Venda backVenda(int id_venda) {
		Venda venda = new Venda();
		String sql = "SELECT * FROM VENDAS";
		try {
			PreparedStatement pstmt = getConnection().prepareStatement(sql);

			ResultSet rs = pstmt.executeQuery();

			int num = 1;
			
			while (rs.next() && id_venda != rs.getInt("id_vendedor")) {
				num++;
			}
			venda.setId_vendedor(rs.getInt("id_vendedor"));
			venda.setNomeVendedor(rs.getString("nomeVendedor"));

			rs.close();
			pstmt.close();
			return venda;
		} catch (SQLException e) {
			System.out.println(e);
		}
		return null;
	}

	public static int findByIndex(int index) {
		int idVendedor = 0;
		
		idVendedor = (new VendaDao()).findVendas().get(index).getId_vendedor();
		
		return idVendedor;
	}

	public int findByNomeVendedor(String nomeVendedor) {
		int idVendedor = 0;
		
		String sql = "SELECT * FROM VENDAS WHERE nomeVendedor = ? ";
		try {
			PreparedStatement pstmt = getConnection().prepareStatement(sql);
            pstmt.setObject(1, nomeVendedor);

			ResultSet rs = pstmt.executeQuery();

			rs.next();
			idVendedor = rs.getInt("id_vendedor");
			
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			System.out.println(e);
		}	

		return idVendedor;
	}

	public void update(Venda venda) {
		String sql = "UPDATE VENDAS " + "SET nomeVendedor = ?"
				+ "where id_vendedor = ?";
		update(sql, venda.getNomeVendedor(), venda.getId_vendedor());
	}

	public void delete(Venda venda) {
		String sql = "DELETE FROM VENDAS WHERE id_vendedor = ? ";
		delete(sql, venda.getId_vendedor());
	}

	public boolean truncate() {
		return truncate("VENDAS");
	}

	public boolean drop() {
		return drop("VENDAS");
	}
	
	public static void ddlInsertVendedor() {
		(new CreateTables()).createTableVendas();

		Venda venda;
		
		venda = new Venda("Administrador");
		(new VendaDao()).save(venda);
		ListTables.showVendedores();
		
		venda = new Venda("Fulano");
		(new VendaDao()).save(venda);
		ListTables.showVendedores();		
	}
	
	public static void dmlVendedor() {
		Venda venda = new Venda();
		
		venda.setNomeVendedor("Administração");
		venda.setId_vendedor(1);
		(new VendaDao()).update(venda);
		ListTables.showVendedores();

		(new VendaDao()).delete(venda);
		
		venda.setNomeVendedor("Sicrano");
		venda.setId_vendedor(2);
		(new VendaDao()).update(venda);
		ListTables.showVendedores();

		if((new VendaDao()).truncate())
			System.out.println("Vendedores zerado.");
		ListTables.showVendedores();
		
		if((new VendaDao()).drop())
			System.out.println("Vendedores removido.");		
	}
	
	public static void demoVendedor() {
		ddlInsertVendedor();
		dmlVendedor();
	}

	public Integer countVendasBy(int id_vendedor) {
		
		return count("SELECT * FROM PALPITES WHERE id_vendedor = ?", id_vendedor);		
	}
}