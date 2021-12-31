package servelets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/ServeletLogin") /*Mapeamento de URL que vem da tela*/
public class ServeletLogin extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    
    public ServeletLogin() {
        
    }

    /*Recebe os dados pela url por meio de parâmetros*/
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/*Recebe os dados enviados por um formuláiro*/
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Nome: "+request.getParameter("nome"));
		System.out.println("Idade: "+request.getParameter("idade"));
		
		int status = response.getStatus();
		System.out.println("Status da requisição: "+status);
	
	}

}
