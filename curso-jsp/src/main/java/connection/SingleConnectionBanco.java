package connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class SingleConnectionBanco {
	
	private static String url = "jdbc:postgresql://localhost:5432/curso-jsp?autoReconnect=true";
	private static String user = "postgres";
	private static String password = "admin";
	private static Connection connection = null;
	
	/*M�todo utilizado para retornar a conex�o*/
	public static Connection getConnection() {
		return connection;
	}
	
	/*A seguir duas formas de garantir a conex�o com o banco de dados*/
	
	/*Se a classe for chamada de forma direta vai ser feita a conex�o*/
	static { 
		conectar();
	}
	
	public SingleConnectionBanco() { /*Quando for criada uma isnt�ncia da classe vai fazer a conex�o com o banco */
		
		conectar();
	}
	
	private static void conectar() {
		try {
			
			if(connection == null) {
				Class.forName("org.postgresql.Driver");/*Carrega o driver de conex�o do banco*/
				connection = DriverManager.getConnection(url, user, password);
				connection.setAutoCommit(false); /*Para n�o efetuar altera��es no banco sem nosso comando*/
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
