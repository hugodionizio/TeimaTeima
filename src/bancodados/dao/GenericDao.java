package bancodados.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class GenericDao {
    private Connection connection;

    protected GenericDao() {
		this.connection = ConnectionDataBase.getConnection();
    }

    protected Connection getConnection() {
        return connection;
    }

    protected void shutdown() throws SQLException {
	    getConnection().createStatement().executeUpdate("SHUTDOWN");
	}

	protected boolean save(String insertSql, Object... parametros) {
        try {
            PreparedStatement pstmt = 
				getConnection().prepareStatement(insertSql);

            for (int i = 0; i < parametros.length; i++) {
                pstmt.setObject(i+1, parametros[i]);
            }

	    pstmt.execute();
	    pstmt.close();
	    
	    return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    protected int findNum(String findSql) {
		int num = 0;
	
		String sql = "SELECT * FROM "+findSql;
		try {
			PreparedStatement pstmt = getConnection().prepareStatement(sql);
	
			ResultSet rs = pstmt.executeQuery();
	
			while(rs.next())
				num++;
			
			rs.close();
			pstmt.close();
			return num;
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
    
    protected int count(String countSql, Object... parametros) {
		int num = 0;
	
		try {
			PreparedStatement pstmt = getConnection().prepareStatement(countSql);

            for (int i = 0; i < parametros.length; i++) {
                pstmt.setObject(i+1, parametros[i]);
            }

			ResultSet rs = pstmt.executeQuery();
	
			while(rs.next())
				num++;
			
			rs.close();
			pstmt.close();
			return num;
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	protected boolean update(String updateSql, Object... parametros) {
	try {
	    PreparedStatement pstmt = 
			connection.prepareStatement(updateSql);

            for (int i = 0; i < parametros.length; i++) {
                pstmt.setObject(i+1, parametros[i]);
            }

	    pstmt.execute();
	    pstmt.close();
	    
	    return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    protected boolean delete(String deleteSql, Object... parametros) {
        try {
            PreparedStatement pstmt = 
				getConnection().prepareStatement(deleteSql);

            for (int i = 0; i < parametros.length; i++) {
                pstmt.setObject(i+1, parametros[i]);
            }

            pstmt.execute();
            pstmt.close();
            
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

	protected boolean truncate(String table) {
		String sql = "TRUNCATE TABLE "+table;
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

	protected boolean drop(String table) {
		String sql = "DROP TABLE "+table;
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
}