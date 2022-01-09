package connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class SingleConnectionBanco {
	
	private static String url = "jdbc:postgresql://localhost:5432/curso-jsp?autoReconnect=true";
	private static String user = "postgres";
	private static String password = "admin";
	private static Connection connection = null;
	
	/*Método utilizado para retornar a conexão*/
	public static Connection getConnection() {
		return connection;
	}
	
	/*A seguir duas formas de garantir a conexão com o banco de dados*/
	
	/*Se a classe for chamada de forma direta vai ser feita a conexão*/
	static { 
		conectar();
	}
	
	public SingleConnectionBanco() { /*Quando for criada uma isntância da classe vai fazer a conexão com o banco */
		
		conectar();
	}
	
	private static void conectar() {
		try {
			
			if(connection == null) {
				Class.forName("org.postgresql.Driver");/*Carrega o driver de conexão do banco*/
				connection = DriverManager.getConnection(url, user, password);
				connection.setAutoCommit(false); /*Para não efetuar alterações no banco sem nosso comando*/
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
