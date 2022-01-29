package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import connection.SingleConnectionBanco;
import model.ModelLogin;

public class DAOUsuarioRepository {
	
	private Connection connection;
	
	public DAOUsuarioRepository() {
		
		connection = SingleConnectionBanco.getConnection();
	
	}
	
	public void gravarUsuario(ModelLogin objeto) {
		
		try {
		String sql = "INSERT INTO model_login (login,senha,email,nome) values (?, ?, ?, ?) ";
		
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, objeto.getLogin());
		preparedStatement.setString(2, objeto.getSenha());
		preparedStatement.setString(3, objeto.getEmail());
		preparedStatement.setString(4, objeto.getNome());
		
		preparedStatement.execute();
		
		connection.commit();
		
		
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
