/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javafxdatabaseprogramming;

/**
 *  * Data access object
 *
 * @author Diles
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BukuDao {
    private static final Logger logger = Logger.getLogger(BukuDao.class.getName());
    
    public void saveBuku(String isbn, String judul, String pengarang, String tahunTerbit, String penerbit) throws SQLException {
	Connection connection = null;
	PreparedStatement statement = null;
	ResultSet resultSet = null;
            try {
		connection = dbConnection.getDBConnection();
		connection.setAutoCommit(false);
		String query = "INSERT INTO buku(isbn, judul, pengarang, tahunTerbit, penerbit) VALUES(?, ?, ?, ?, ?)";
		statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
		int counter = 1;
		statement.setString(counter++, isbn);
		statement.setString(counter++, judul);
		statement.setString(counter++, pengarang);
		statement.setString(counter++, tahunTerbit);
		statement.setString(counter++, penerbit);
		statement.executeUpdate();
		connection.commit();
                        
            } catch (SQLException exception) {
		logger.log(Level.SEVERE, exception.getMessage());
		if (null != connection) {
                    connection.rollback();
		}
	} finally {
		if (null != resultSet) {
                    resultSet.close();
		}

		if (null != statement) {
                    statement.close();
		}

		if (null != connection) {
                    connection.close();
		}
            }
    }
    
    public void updateBuku(String isbn, String judul, String pengarang, String tahunTerbit, String penerbit) throws SQLException {
	Connection connection = null;
	PreparedStatement statement = null;
	ResultSet resultSet = null;
            try {
		connection = dbConnection.getDBConnection();
		connection.setAutoCommit(false);
		String query = "UPDATE buku SET judul = ?, pengarang = ?, tahunTerbit = ?, penerbit = ? WHERE isbn = ?";
		statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
		int counter = 1;
		statement.setString(counter++, judul);
		statement.setString(counter++, pengarang);
		statement.setString(counter++, tahunTerbit);
		statement.setString(counter++, penerbit);
		statement.setString(counter++, isbn);
		statement.executeUpdate();
		connection.commit();
                        
            } catch (SQLException exception) {
		logger.log(Level.SEVERE, exception.getMessage());
		if (null != connection) {
                    connection.rollback();
		}
	} finally {
		if (null != resultSet) {
                    resultSet.close();
		}

		if (null != statement) {
                    statement.close();
		}

		if (null != connection) {
                    connection.close();
		}
            }
    }
    
    public void deleteBuku(String isbn) throws SQLException {
	Connection connection = null;
	PreparedStatement statement = null;
	ResultSet resultSet = null;
            try {
		connection = dbConnection.getDBConnection();
		connection.setAutoCommit(false);
		String query = "DELETE FROM buku WHERE isbn = ?";
		statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
		int counter = 1;
		statement.setString(counter++, isbn);
		statement.executeUpdate();
		connection.commit();
                        
            } catch (SQLException exception) {
		logger.log(Level.SEVERE, exception.getMessage());
		if (null != connection) {
                    connection.rollback();
		}
	} finally {
		if (null != resultSet) {
                    resultSet.close();
		}

		if (null != statement) {
                    statement.close();
		}

		if (null != connection) {
                    connection.close();
		}
            }
    }
    
    public ArrayList<Buku> viewBuku() throws SQLException {
	Connection connection = null;
		PreparedStatement statement = null;
		ArrayList<Buku> allBuku = new ArrayList<>();

		try {
			connection = dbConnection.getDBConnection();
			connection.setAutoCommit(false);
			String query = "SELECT isbn, judul, pengarang, tahunterbit, penerbit FROM buku";
			statement = connection.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Buku buku = new Buku();
				buku.setISBN(resultSet.getString(1));
				buku.setJudul(resultSet.getString(2));
				buku.setPengarang(resultSet.getString(3));
				buku.setTahunTerbit(resultSet.getString(4));
				buku.setPenerbit(resultSet.getString(5));
				allBuku.add(buku);
			}
			
		} catch (SQLException exception) {
			logger.log(Level.SEVERE, exception.getMessage());
		} finally {
			if (null != statement) {
				statement.close();
			}

			if (null != connection) {
				connection.close();
			}
		}
                return (allBuku);
    }
}
