package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DaoSessao {
	static final String url = "jdbc:postgresql://localhost:5432/hotel";

	public boolean inserir(String nome, String data, int quarto) {
		String sql = "INSERT INTO estadia (nome, data, quarto) VALUES ('" + nome + "', '" + data + "', " + quarto
				+ ")";

		try {
			Connection conexao = DriverManager.getConnection(url, "postgres", "leo");
			PreparedStatement inclusao = conexao.prepareStatement(sql);
			inclusao.execute();
			return true;
		} catch (SQLException e) {
			System.out.println(e);
			return false;
		}
	}

	public ResultSet listar() {
		String sql = "SELECT * FROM estadia;";

		try {
			Connection conexao = DriverManager.getConnection(url, "postgres", "leo");
			Statement listar = conexao.createStatement();
			ResultSet rs = listar.executeQuery(sql);
			return rs;
		} catch (SQLException e) {
			System.out.println(e);
			return null;
		}
	}
	
	public int excluir(String nome, String data, int quarto) {
		String sql = "DELETE FROM estadia WHERE nome = '" + nome + "' AND data = '" + data + "' AND quarto = " + quarto
				+ ";";

		try {
			Connection conexao = DriverManager.getConnection(url, "postgres", "leo");
			PreparedStatement exclusao = conexao.prepareStatement(sql);
			return exclusao.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println(e);
			return -1;
		}
	}
}