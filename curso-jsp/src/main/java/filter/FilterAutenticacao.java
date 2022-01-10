package filter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import connection.SingleConnectionBanco;


@WebFilter(urlPatterns = {"/principal/*"}) //Intercepta todas as requisi��es que vier do projeto ou mapeamento
public class FilterAutenticacao implements Filter {
	
	private static Connection connection;


    public FilterAutenticacao() {
    }

	
	public void destroy() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
			throws IOException, ServletException {
		
		try {
			HttpServletRequest req = (HttpServletRequest)request;
			HttpSession session = req.getSession();
			
			String usuarioLogado = (String) session.getAttribute("user");
			
			String urlParaAutenticar = req.getServletPath(); //url que est� sendo acessada
			
			/*Validar se est� logado, se n�o redireciona para a tela de login*/
			if((usuarioLogado == null || usuarioLogado.equals("null"))  && 
					!urlParaAutenticar.equalsIgnoreCase("/principal/ServeletLogin") ) { //N�o est� logado
				
				RequestDispatcher redireciona = request.getRequestDispatcher("/index.jsp?url="+urlParaAutenticar);
				request.setAttribute("msg", "Por favor realize o login");
				redireciona.forward(request, response);
				return; //Parar a execu��o e redirecionar para o login
				
			}else {
				
				chain.doFilter(request, response);
			}
			
			connection.commit(); /*Deu tudo certo faz o commit das altera��es no banco de dados*/
			
	  }catch(Exception e) {
		  e.printStackTrace();
		  
		  RequestDispatcher redirect = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", e.getMessage());
			redirect.forward(request, response);
		  
		  try {
			connection.rollback();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	  }
		  
	}
	public void init(FilterConfig fConfig) throws ServletException {
		
		connection = SingleConnectionBanco.getConnection();
	}

}
