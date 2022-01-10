package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import connection.SingleConnectionBanco;
import model.ModelLogin;

public class DAOLoginRepository {

	private Connection connection;

	public DAOLoginRepository() {
		connection = SingleConnectionBanco.getConnection();
	}

	public boolean validarAutenticacao(ModelLogin modelLogin) throws Exception {


			String sql = "SELECT * FROM model_login WHERE login = ? AND password_b = ?";

			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, modelLogin.getLogin());
			statement.setString(2, modelLogin.getPassword());

			ResultSet result = statement.executeQuery();

			if (result.next()) {
				return true; /*autenticado*/
			}else {
				
				return false; /*não autenticado*/
			}


	}

}
