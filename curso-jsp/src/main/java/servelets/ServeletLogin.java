package servelets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ModelLogin;


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
		
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		
		if(login != null && !login.isEmpty() && password != null && !password.isEmpty()) {
			
			ModelLogin modelLogin = new ModelLogin();
			modelLogin.setLogin(login);
			modelLogin.setPassword(password);
			
			if(modelLogin.getLogin().equals("admin") 
					&& modelLogin.getPassword().equals("admin")) {
				request.getSession().setAttribute("user", modelLogin.getLogin());
				RequestDispatcher redirect = request.getRequestDispatcher("principal/principal.jsp");
				redirect.forward(request, response);
				
			}else {
				
				RequestDispatcher redirect = request.getRequestDispatcher("index.jsp");
				request.setAttribute("msg", "Enter the login and password correctly!");
				redirect.forward(request, response);
			}
			
		}else {
			
			RequestDispatcher redirect = request.getRequestDispatcher("index.jsp");
			request.setAttribute("msg", "Enter the login and password correctly!");
			redirect.forward(request, response);
			
		}

	
	}

}
