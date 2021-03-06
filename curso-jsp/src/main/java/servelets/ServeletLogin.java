package servelets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DAOLoginRepository;
import model.ModelLogin;


@WebServlet(urlPatterns = {"/ServeletLogin", "/principal/ServeletLogin"}) /*Mapeamento de URL que vem da tela*/
public class ServeletLogin extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
	private DAOLoginRepository daoLoginRepository = new DAOLoginRepository();
    
    public ServeletLogin() {
        
    }

    /*Recebe os dados pela url por meio de par?metros*/
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String acao = request.getParameter("acao");
		
		if(acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("logout") ) {
			request.getSession().invalidate();
			RequestDispatcher redireciona = request.getRequestDispatcher("index.jsp");
			redireciona.forward(request, response);
		}
		else {			
			doPost(request,response);
		}
		
	}

	/*Recebe os dados enviados por um formul?iro*/
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		String url = request.getParameter("url");
		
		if(login != null && !login.isEmpty() && password != null && !password.isEmpty()) {
			
			ModelLogin modelLogin = new ModelLogin();
			modelLogin.setLogin(login);
			modelLogin.setSenha(password);
			
			if(daoLoginRepository.validarAutenticacao(modelLogin)) { /*SIMULANDO LOGIN*/
				
				request.getSession().setAttribute("user", modelLogin.getLogin());
				
				if(url == null || url.equals("null")) {
					
					url = "principal/principal.jsp";
				}
				
				RequestDispatcher redirect = request.getRequestDispatcher(url);
				redirect.forward(request, response);
				
			}else {
				
				RequestDispatcher redirect = request.getRequestDispatcher("/index.jsp");
				request.setAttribute("msg", "Informe login e senha corretamente!");
				redirect.forward(request, response);
			}
			
		}else {
			
			RequestDispatcher redirect = request.getRequestDispatcher("index.jsp");
			request.setAttribute("msg", "Informe login e senha corretamente!");
			redirect.forward(request, response);
			
		}

	}catch(Exception e) {
		e.printStackTrace();
		RequestDispatcher redirect = request.getRequestDispatcher("erro.jsp");
		request.setAttribute("msg", e.getMessage());
		redirect.forward(request, response);
		
	}
		
	}

}
