package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connection.SingleConnectionBanco;
import model.ModelLogin;

public class DAOUsuarioRepository {
	
	private Connection connection;
	
	public DAOUsuarioRepository() {
		
		connection = SingleConnectionBanco.getConnection();
	
	}
	
	public ModelLogin gravarUsuario(ModelLogin objeto) throws Exception {
		
		
		String sql = "INSERT INTO model_login (login,senha,email,nome) values (?, ?, ?, ?) ";
		
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, objeto.getLogin());
		preparedStatement.setString(2, objeto.getSenha());
		preparedStatement.setString(3, objeto.getEmail());
		preparedStatement.setString(4, objeto.getNome());
		
		preparedStatement.execute();
		
		connection.commit();
		
		return this.consultaUsuario(objeto.getLogin());
		
		
	}
	
	public ModelLogin consultaUsuario(String login) throws SQLException {
		
		ModelLogin modelLogin = new ModelLogin();
		
		String sql = "select * from model_login where login = '"+login+"' ";
		
		PreparedStatement prepared = connection.prepareStatement(sql);
		
		ResultSet result = prepared.executeQuery();
		
		while(result.next()) {
			
				modelLogin.setLogin(result.getString("login"));
				modelLogin.setSenha(result.getString("senha"));
				modelLogin.setId(result.getLong("id"));
				modelLogin.setEmail(result.getString("email"));
				modelLogin.setNome(result.getString("nome"));
		}
		
		
		return modelLogin;
		
	}
	
	public boolean validarLogin(String login) throws Exception {
		
		String sql = "select count(1) > 0 as existe from model_login where upper(login) = upper('"+login+"');";
		PreparedStatement statement = connection.prepareStatement(sql);
		
		ResultSet resultado = statement.executeQuery();
		
		resultado.next();
		return resultado.getBoolean("existe");
	}

}
